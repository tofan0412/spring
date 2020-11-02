package kr.or.ddit.config.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)	// 스프링의 테스트 코드를 작성하기 위해 필요한 어노테이션.
@ContextConfiguration(locations = {"classpath:kr/or/ddit/config/spring/ioc/ioc.xml"})	// 스프링 컨테이너에서 필요한 설명서를 알려줄 수 있다. 
public class IocDITest {
	
	// ioc.xml을 바탕으로 스프링 빈이 잘 생성되었는지를 확인하는 것이다.
	// setter-boardService, constructor-boardServiceC 주입 확인
	
	@Autowired // DI를 한 것. ApplicationContext 객체는 오직 하나만 존재한다.
	// 스프링 빈 중에 호환되는 타입의 빈이 있을 때 주입한다.
	ApplicationContext context;
	
	/*
	 * 공식적으로 우리가 ioc.xml에서 주입한 bean은 3개이다. 
	 * 이 중에서 boardServiceI 유형은 2개(boardService, boardServiceC)
	 * 따라서 주입을 받고 싶은 bean의 이름을 명확히 기재하는 reource 어노테이션을 이용한다.
	 * 
	 */
	@Resource(name="boardService")
	BoardService boardService;
	@Resource(name="boardServiceC")
	BoardService boardServiceC;
	
	@Resource(name="person")
	Person person;
	// Person Spring Bean의 age(value) , boardRepository(ref) 두 속성에 주입이 잘 되었는지 확인
	@Test
	public void valueRefTest() {
		/***Given***/
//		person = context.getBean("person", Person.class);
		// 어노테이션을 통해 지정하였으므로 위와 같은 과정을 반복할 필요가 없다.

		/***When***/
		
		/***Then***/
		assertEquals(30, person.getAge());
		assertEquals("내용", person.getBoardRepository().getBoard(1).getContent());
	}
	
	// 스프링 컨테이너를 주입받아 DL을 통해 boardService 스프링 빈이 제대로 생성되었는지를 확인
	
	
	@Test
	// 위 AutoWired 어노테이션을 통해 boardServie가 제대로 주입되었는지를 확인한다. 
	public void DIAutoWiredTest() {
		/***Given***/
		

		/***When***/

		/***Then***/
		assertNotNull(boardService);
	}
	
	
	@Test
	public void repositorySameTest() {
		/***Given***/
		
		/***When***/

		/***Then***/
		// boardService, boardServiceC 스프링 빈에 주입한 boardRepository 스프링빈은 
		// boardDao라는 동일한 빈이므로, 두 객체의 getter 메서드를 통해 얻어온 boardREpository 객체는 동일해야
		// 한다.

		// 실제로 동일한가?
		assertEquals(boardService.getBoardRepository(), boardServiceC.getBoardRepository());
	}
	
	
	@Test
	public void DItest() {
		/***Given***/
		

		/***When***/
		BoardServiceI boardService = 
				context.getBean("boardService", BoardServiceI.class);
		BoardVo boardVo = boardService.getBoard(1);
		/***Then***/
		assertEquals("첫번째 글", boardVo.getTitle()); 
		
	}

}
