package kr.or.ddit.mvc.redirect.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping(path="/redirect")
@Controller
public class RedirectController {
	private static final Logger logger = LoggerFactory.getLogger(RedirectController.class);
	
	// localhost/redirect/process => localhost/login/view로 다시 리다이렉트 처리 할 것
	// 요청 시 index.jsp로 리다이렉트 처리
	@RequestMapping(path="/process")
	public String process(Model model, HttpSession session, RedirectAttributes ra) { 
		logger.debug("Redirect Controller.process()");
		model.addAttribute("msg", "hello, Spring!");
		session.setAttribute("msg_s", "Hello, Spring!");
		// Session 객체에 저장하면 계속 남는다. 따라서 일회성으로 사용하기 위해선 지워야 한다..
		
		// 리다이렉트 된 페이지에서 일회에 한해 사용할 수 있는 속성
		// 얘를 작성하면 위의 모델에 추가한 속성이 GET 방식으로 안넘어간다..
		ra.addFlashAttribute("msg_ra", "Hello, Spring!");
		
		// 리다이렉트 시 모델 객체에 추가된 속성을 리다이렉트 주소의 파라미터로 추가한다(GET)
		// "redirect:/login/view" ==> /login/view?msg=hello, world!
		ra.addAttribute("msg_ra_attr","brown" );
		
		// 최종적으로 도착하는 로그인 페이지에서 model에 저장한 attribute를 사용할 수 있을까?
		
		return "redirect:/login/view";
	}
	
}
