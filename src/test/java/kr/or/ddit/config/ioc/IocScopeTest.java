package kr.or.ddit.config.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.repository.BoardRepository;
import kr.or.ddit.board.repository.BoardRepositoryI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:kr/or/ddit/config/spring/ioc/scope.xml"})
public class IocScopeTest {
	private static final Logger logger = LoggerFactory.getLogger(IocScopeTest.class);
	
	@Autowired
	ApplicationContext context;
	
	@Resource(name="boardRepository")
	private BoardRepositoryI boardRepository;
	
	@Resource(name="boardRepository")
	private BoardRepositoryI boardRepository2;
	
	// boardRepository와 boardRepository2 모두 동일한 bean을 바라보고 있으므로, 같은 객체이다.
	// 기본적으로 객체를 재사용하는 singleton 패턴으로 객체가 설계..
	// bean은 id, name으로 관리되기 때문에 id,name이 다르면 서로 다른 bean임을 뜻한다. 
	
	@Resource(name="boardRepository_a")
	private BoardRepositoryI boardRepository_a;
	
	@Resource(name="boardRepository_p")
	private BoardRepositoryI boardRepository_p1;
	
	@Resource(name="boardRepository_p")
	private BoardRepositoryI boardRepository_p2;
	
	@Test
	public void singletonTest() {
		/***Given***/
		

		/***When***/

		/***Then***/
		assertEquals(boardRepository, boardRepository2);
		assertNotEquals(boardRepository, boardRepository_a);
		assertNotEquals(boardRepository2, boardRepository_a);
	}
	
	@Test
	public void prototypeTest() {
		/***Given***/
		

		/***When***/
		for (int i = 0 ; i < 10 ; i++) {
			BoardRepositoryI boardRepository = 
					context.getBean("boardRepository",BoardRepository.class);
			
			BoardRepositoryI boardRepository_p = 
					context.getBean("boardRepository_p",BoardRepository.class);
			
			logger.debug("singleton-boardRepository : {},"
					+ " prototype-singleton-boardRepository : {}", boardRepository, boardRepository_p);
		}
		/***Then***/
		assertNotEquals(boardRepository_p1, boardRepository_p2);
	}

}
