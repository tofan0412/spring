package kr.or.ddit.login.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberServiceI;

// Controller를 참조하는 다른 클래스가 없기 때문에, ()안에 이름을 설정하지 않아도 된다.
// @WebServlet혹은 web.xml에서 url-mapping 통해 url을 등록 ..

@SessionAttributes("rangers")// rangers라는 속성이 발생하면 이를 session에 저장할 것이다.
@RequestMapping("/login") 
@Controller
public class LoginController {
	@Resource(name="memberService")
	private MemberServiceI memberService;
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@ModelAttribute("rangers")
	public List<String>	ranger(){
		logger.debug("loginController ranger() 호출됨.");
		List<String> rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("sally");
		rangers.add("cony");

		return rangers;
	}
	
	
	// localhost/login/view 와 같이 요청하는 경우 하단의 메서드가 호출된다.
	// 요청 메서드가 GET일 때만 처리한다.
	// 복수의 요청 메서드 설정도 가능하다.
	@RequestMapping(path="/view", method = RequestMethod.GET)
	// @GetMapping(path="/view") 와 동일하다.
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
	public String process(@RequestParam(name="userid" , required=false, defaultValue="brown@line.kr") String user_id, String userid, String pass, 
			MemberVo member, HttpSession session, Model model) {
		
		logger.debug("loginController process() 호출. {} / {} / {}", userid, pass, member);
		logger.debug("파람 연결하기 : {} ", user_id);
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
		
		MemberVo dbMember = memberService.getMember(userid);
		logger.debug("호출된 회원 : {}", dbMember);
		
		// DB에서 조회한 사용자 정보가 존재하면 => main.jsp로 이동한다. 
		// 파라미터 memberVo를 사용해도 자동으로 바인딩 해주기 때문에 상관없다.
		if (dbMember != null && member.getPass().equals(dbMember.getPass())) {
			session.setAttribute("S_MEMBER", dbMember);
			
			// request.setAttribute("to_day", new Date())와 동일하다.
			model.addAttribute("to_day", new Date());
			return "main";	// viewName이 바로 main이다.
		}else{
			model.addAttribute("msg", "fail");
			
			return "login/view"; // viewName이 바로 view이다.
		}
	}
	
	@RequestMapping("/unt/{unt_cd}")
	public String untMain(@PathVariable("unt_cd") String unt_cd) {
		logger.debug("unt_cd : {}", unt_cd);
		return "main";
	}
	
	//localhost/login/mavView
	@RequestMapping("/mavView")
	public ModelAndView mavView(@ModelAttribute("rangers" ) List<String> rangers,
								@ModelAttribute("test") MemberVo modelVo) {
		ModelAndView mav = new ModelAndView();
		
		logger.debug("mavView rangers,test : {} , {}", rangers);
		
		// viewName을 설정할 수 있다.
		mav.setViewName("main"); // return "main"; 과 동일하다.  
		
		mav.getModel().put("msg", "success");
		mav.getModelMap().addAttribute("msg", "fail");
		
		//ModelAndView 객체를 통해 Model과 View를 동시에 설정할 수 있다.
		return mav;
	}
	
	
	
	
	
	
}
