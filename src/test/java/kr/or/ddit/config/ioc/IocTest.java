package kr.or.ddit.config.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceI;

public class IocTest {
	private static final Logger logger = LoggerFactory.getLogger(IocTest.class);
	
	public static void main(String[] args) {
		// 스프링 빈 사용설명서를 사용하여 스프링 컨테이너를 생성
		// 스프링 컨테이너로 : applicationContext
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("classpath:kr/or/ddit/config/spring/ioc/ioc.xml");
			
		// 스프링 컨테이너로 부터 스프링 빈을 받아서 사용 ( dependency look up, DL)
		BoardService boardService = context.getBean("boardService", BoardService.class);
		if (boardService.getBoardRepository() != null) {
			System.out.println("boardRepository가 null이 아니다.");
		}
		
		logger.debug("getBoard : {}",boardService.getBoard(1));
		
		// 스프링 빈 boardServiceC를 DL하여 getBoard(1) 메서드를 call..
		BoardServiceI boardServiceC = (BoardServiceI) context.getBean("boardServiceC");
		
		logger.debug("boardServiceC.getBoard(1) : {}", boardServiceC.getBoard(1));
	}
}
