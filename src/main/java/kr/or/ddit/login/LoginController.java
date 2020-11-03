package kr.or.ddit.login;

import javax.annotation.Resource;

import org.apache.catalina.tribes.MembershipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

// Controller를 참조하는 다른 클래스가 없기 때문에, ()안에 이름을 설정하지 않아도 된다.
// @WebServlet혹은 web.xml에서 url-mapping 통해 url을 등록 ..

@RequestMapping("/login") 
@Controller
public class LoginController {
	@Resource(name="memberService")
	MemberServiceI memberService;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	// localhost/login/view 와 같이 요청하는 경우 하단의 메서드가 호출된다.
	@RequestMapping("/view.do")
	public String getView() {
		logger.debug("loginController getView() 호출됨.");
		return "login/view";
	}
	
	// 파라미터 이름과 동일한 이름의 메서드 인자를 선언하면 
	// 스프링 프레임워크가 자동으로 바인딩을 해준다.
	// 값을 담을 수 있는 객체를 메서드 인자로 선언한 경우에도, 필드명과 파라미터 명이
	// 동일하면 자동으로 바인딩 처리를 해준다.
	// 이때 값을 담는 객체를 Spring Framework에서는 command 객체라고 명명한다.
	@RequestMapping("/process")
	public String process(String userid, String pass, MemberVo memberVo) {
		logger.debug("loginController process() 호출. {} / {} / {}", userid, pass, memberVo);
		
		// memberService의 getMember() 메서드를 호출하여
		// DB에서 데이터를 조회하여 logger를 이용하여 console에 출력하는 부분까지 진행.
//		MemberService service = new MemberService();
//		MemberVo member = service.getMember(userid);
//		logger.debug("호출된 회원 : {}", member);
		
		// Service객체를 Spring Bean으로 바꿀 수 있을까?
		// 1. Spring Container 생성
//		ApplicationContext context = 
//				("kr/or/ddit/config/db/spring/member.xml");
//		MemberServiceI service_bean = (MemberService) context.getBean("memberService");
		
		// 2. Resource 사용
		
		MemberVo member = memberService.getMember(userid);
		logger.debug("호출된 회원 : {}", member);
		
		return "login/view";
	}
}
