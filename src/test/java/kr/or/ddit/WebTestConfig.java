package kr.or.ddit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= 
	{"classpath:kr/or/ddit/config/db/spring/root-context.xml",
	 "classpath:kr/or/ddit/config/db/spring/application-context.xml"})
@WebAppConfiguration
public class WebTestConfig {
	// 테스트 대상 : LoginController
		// 					--> memberService를 주입 받아야 함.
		//						--> MemberRepository를 주입받아야 함.
		// LoginController를 테스트하기 위해선 memberService, MemberRepository Bean이 등록된 파일이 필요.
		// 즉 service, repository 빈을 스캔하는 설정과 controller를 스캔하는 설정 두 개가 필요
		
		// Spring Framework의 컨트롤러 테스트 시나리오
		// 1. 웹 기반의 스프링 컨테이너를 구성 후
		// 2. dispatcherServlet 역할을 하는 객체를 생성
		// 3. dispatcherServlet 역할을 하는 객체를 통해 url, 파라미터 등을 첨부하여 요청 전송
		// 4. 응답이 원하는 형태로 나오는지 체크(viewName, model에 담긴 속성 등)
		@Autowired // 타입으로 적절한 spring Bean을 찾는 것이다.
		private WebApplicationContext context;
		
		public static MockMvc mockMvc;	// dispatcherServlet 역할을 하는 객체
		
		/*
		 * @Before(setup) ==> @Test ==> @After(tearDown)
		 */
		
		// 웹애플리케이션 환경에서 Spring을 테스트 하기 위한 환경 설정.
		@Before
		public void setup() {
			mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		}
		
		//get(), post() : get/post 요청
		// param(파라미터명, 파라미터값) : 요청시 보낼 파라미터
		// status() : Spring framework에 의해 요청이 처리되고 생성된 응답의 코드
		// view() : Spring framework에 의해 요청이 처리되는 과정에서 반환된 viewName
		// model() : 컨트롤러에서 설정한 속성값을 담는 객체
		// request() : 요청 객체
	
	@Ignore
	@Test
	public void test() {
		
	}

}
