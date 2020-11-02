package kr.or.ddit.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.or.ddit.board.repository.BoardRepository;
import kr.or.ddit.board.repository.BoardRepositoryI;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceI;

@Configuration
public class JavaSpringConfig {
	// boardRepository, boardService
	// 메서드 이름 : 스프링 빈의 이름이 된다. ( 따로 지정하는 방법도 존재한다. )
	
	// xml 로 작성하면 다음과 동일하다.
	// xml : <bean id="boardRepository" class="BoardRepository(파일이름)"/>
	@Bean
	public BoardRepositoryI boardRepository() {
		return new BoardRepository();
	}
	
	// xml 로 작성하면 다음과 동일하다.
	// xml : <bean id="boardService" class="BoardService"/>
	@Bean
	public BoardServiceI boardService() {
		BoardService boardService = new BoardService();
		// setter 또는 생성자를 이용하여 property 설정하기..
		boardService.setBoardRepository(boardRepository());
		/*우리가 봤을 때는 return new BoardRepository(); 이기 때문에 계속해서 객체가 생긴다고 
		생각할 수도 있지만, 결과적으로 Spring Container에서 처리하는 것이기 때문에 객체가
		중복해서 발생하지 않는다.
		이 클래스에서 사용되는 new 연산자도 마찬가지이다 ! 
		
		아래와 같이 직접 new 연산자를 통해 생성한 객체는 스프링 빈이 아니다.
		@Bean 어노테이션이 붙으 메서드를 호출해야 스프링 컨테이너에서 관리되는 스프링 빈을 얻을 수 있다.
		// boardService.setBoardRepository(new BoardRepository());
		*/
		
		return boardService;
	}
}