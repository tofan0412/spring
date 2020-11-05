package kr.or.ddit.mvc.multiparam.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.model.Rangers;


@RequestMapping("/multi")
@Controller
public class MultiParamController {
	private static final Logger logger = LoggerFactory.getLogger(MultiParamController.class);
	
	// 여러 개의 인자를 받는 화면을 요청하는 메서드
	// https://localhost/multi/view
	@RequestMapping(path="/view")
	public String view() {
		return "multi/view"; // WEB-INF/views/multi/view.jsp를 호출한다는 뜻이다.
	}
	
	// 복수 개의 파라미터 전송을 처리하는 메서드
	// userid라는 이름의 파라미터로 복수개의 값이 전달된다. 
	@RequestMapping(path="/submit")
	public String submit(@RequestParam("userid") List<String> userlist,
						 @RequestParam("userid") String[] userArr,
						 Rangers rangers) {
		logger.debug("userid : {}", userlist);
		logger.debug("userid : {}", (Object)userArr);	// locback이 제대로 출력을 못함.. 실제로는 정상적으로 값이 잘 들어가 있다.
		logger.debug("rangers : {}", rangers);
		// object로 형변환하면 잘된다.
		
		return "multi/view"; 
	}
	
	
	
}
