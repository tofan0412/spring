package kr.or.ddit.batch.yoghurt;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import kr.or.ddit.batch.yoghurt.model.CycleVo;
import kr.or.ddit.batch.yoghurt.model.DailyVo;

//Reader에서 가장 먼저 읽은걸 전달하기 때문에 generic의 첫번째 인자는 CycleVo이다.
// 출력 결과는 DailyVo를 요소로 갖는 List이므로, List<DailyVo>를 반환한다.
public class YoghurtProcessor implements ItemProcessor<CycleVo, List<DailyVo>>{
	private static final Logger logger = LoggerFactory.getLogger(YoghurtProcessor.class);
	
	// jobLauncher를 실행하면서 두번째 인자로 넣어준 jobParameter 값을 SPEL을 통해 주입한다.
	// 단 jobParameters에 접근하기 위해서는 해당 spring bean의 scope를 step으로 지정해야 한다. 
	@Value("#{jobParameters[ym]}")	// spel은 #을 사용한다. 
	private String ym = "202011";
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	@Override
	public List<DailyVo> process(CycleVo item) throws Exception {
		// 생성 월이 2020년 11월이다 ㄱ 
		/* cid=1, pid = 100, day=2, cnt = 3
			==> cid = 1 , pid=100, dt=20201102, cnt=3
			==> cid = 1 , pid=100, dt=20201109, cnt=3
			==> cid = 1 , pid=100, dt=202011016, cnt=3
			==> cid = 1 , pid=100, dt=202011023, cnt=3
			==> cid = 1 , pid=100, dt=202011030, cnt=3
			와 같은 처리를 해주는 batch를 만들 것이다 !
			필요한 것 : ????년 ??월에 대한 걸 만들어줄 지를 정하지 않았다. 년과 월을 지정해야 한다. 
		*/
		
		logger.debug("ym : {}, item : {}", ym, item);
		List<DailyVo> dailyVoList = new ArrayList<DailyVo>();
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.YEAR, Integer.parseInt(ym.substring(0,4)));	// 202011만 잘라내기
		calendar.set(calendar.MONTH, Integer.parseInt(ym.substring(4))-1 );	// 11
		// 현재 캘린더 객체에 설정된 월의 마지막 일을 알 수 있다.
		calendar.set(calendar.DAY_OF_MONTH, calendar.getActualMaximum(calendar.DAY_OF_MONTH));
		
		// 2020 11 30
		Date endTime = calendar.getTime();
		String endTimeStr = sdf.format(endTime);
		int endTimeInt = Integer.parseInt(endTimeStr);
		
		// 해당 월의 1일로 설정 ... => 2020. 11. 01로 설정된다.
		calendar.set(calendar.DAY_OF_MONTH, 1);
		Date dt = calendar.getTime();
		String dtStr = sdf.format(dt);
		int dtInt = Integer.parseInt(dtStr);
		
		
		while(endTimeInt >= dtInt) {
			
			if (item.getDay() == calendar.get(Calendar.DAY_OF_WEEK)) { 
				DailyVo dailyVo = new DailyVo();
				dailyVo.setCid(item.getCid());
				dailyVo.setPid(item.getPid());
				dailyVo.setDt(new SimpleDateFormat("yyyyMMdd").format(calendar.getTime()));
				dailyVo.setCnt(item.getCnt());
				dailyVoList.add(dailyVo);
			}
			// 다음 날짜로 설정
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
			dt = calendar.getTime();
			dtStr = sdf.format(dt);
			dtInt = Integer.parseInt(dtStr);
		}
		return dailyVoList;
	}
}
