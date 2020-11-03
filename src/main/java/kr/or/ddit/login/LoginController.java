package kr.or.ddit.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// Controller를 참조하는 다른 클래스가 없기 때문에, ()안에 이름을 설정하지 않아도 된다.
// @WebServlet혹은 web.xml에서 url-mapping 통해 url을 등록 ..

@RequestMapping("/login") 
@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	// localhost/login/view 와 같이 요청하는 경우 하단의 메서드가 호출된다.
	@RequestMapping("/view.do")
	public String getView() {
		logger.debug("loginController getView() 호출됨.");
		return "login/view";
	}
}
