package kr.or.ddit.config.ioc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"kr.or.ddit.board"})
// kr.or.ddit.board로 바꾸면 SqlSessionTemplate를 찾을 수 있다.

public class JavaSpringScanConfig {
	
}
