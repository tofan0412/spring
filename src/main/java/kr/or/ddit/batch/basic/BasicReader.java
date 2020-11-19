package kr.or.ddit.batch.basic;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class BasicReader implements ItemReader<String> {
	private static final Logger logger = LoggerFactory.getLogger(BasicReader.class);
	private List<String> list;
	private int index = 0;
	private int count = 0;
	public BasicReader() {
		// list객체에 임의의 값 5개 생성
		list = new ArrayList<String>();
		list.add("brown");
		list.add("sally");
		list.add("cony");
		list.add("james");
		list.add("moon");
	}
	
	// return 값이 processor에게 전달
	// 더 이상 읽을 데이터가 없을 때, null을 반환한다. ==> spring batch 모듈에서 읽을 데이터가 없다고 인식
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		logger.debug("====read()====");
		
		if (index < list.size()) { 
			String item = list.get(index++);
			logger.debug("item : {}", item);
			return item;
		}
		
		else {	// 이 else문을 실행한다는 건, index가 5가 됐다는 것을 의미한다.
			logger.debug("count : {}", count);
			count++;
			index = 0;
			return null;
		}
	}

}
