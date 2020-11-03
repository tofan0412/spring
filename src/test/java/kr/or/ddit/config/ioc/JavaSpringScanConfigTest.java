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
import kr.or.ddit.ioc.JavaSpringConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JavaSpringScanConfig.class})
public class JavaSpringScanConfigTest {

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
	// 왜 xml 보다 java를 권장하는가 ? Eclipse와 같은 툴을 사용하지 않는 이상, xml(그냥 txt 파일이다.)의 오류를 발견하기는 어렵다.
	// 따라서 xml은 테스트 코드를 돌리거나 서버에서 돌릴 때 에러를 알 수 있다.
	// 이에 비해 java 파일은 코드로 작성되기 때문에 compile 시점에서 알 수 있다.
}
