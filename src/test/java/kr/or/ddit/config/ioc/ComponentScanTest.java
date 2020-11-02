package kr.or.ddit.config.ioc;

import static org.junit.Assert.*;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.repository.BoardRepositoryI;
import kr.or.ddit.board.service.BoardServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:kr/or/ddit/config/spring/ioc/component-scan.xml"})
public class ComponentScanTest {
	@Resource(name="boardRepository")
	private BoardRepositoryI boardRepository;
	
	@Resource(name="boardService")
	private BoardServiceI boardService;
	
	// boardRepository, boardService 스프링 빈이 정상적으로 등록되었는지 확인
	
	@Test
	public void componentTest() {
		/***Given***/
		
		/***When***/
		BoardVo boardVo = boardService.getBoard(1);
		/***Then***/
		assertNotNull(boardRepository);
		assertNotNull(boardService);
		assertEquals("내용", boardVo.getContent());
	}
	
	// 차이점이 있다면, component-scan.xml에는 <bean> 태그가 존재하지 않는다는 점이다 ! 즉 사용자가 일일이
	// bean 태그를 이용하여 명시하지 않았으며, 어노테이션을 이용해 자동으로 검색하고 bean을 생성하였다..

}
