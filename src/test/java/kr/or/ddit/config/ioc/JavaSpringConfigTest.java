package kr.or.ddit.config.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.repository.BoardRepositoryI;
import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.ioc.JavaSpringConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JavaSpringConfig.class})
public class JavaSpringConfigTest {

	@Resource(name="boardRepository")
	private BoardRepositoryI boardRepository;
	
	@Resource(name="boardService")
	private BoardServiceI boardService;
	
	// boardRepository, boardService 스프링 빈이 정상적으로 등록되었는지 확인
	
	@Test
	public void beanTest() {
		/***Given***/
		
		/***When***/
		BoardVo boardVo = boardService.getBoard(1);
		/***Then***/
		assertNotNull(boardRepository);
		assertNotNull(boardService);
		assertEquals("내용", boardVo.getContent());
	}
	
	// ComponentScanTest에서 바뀐 부분 : ~.xml 을 읽은건지, 아니면 이를 대체하는 java 파일을 읽은 건지 !

}
