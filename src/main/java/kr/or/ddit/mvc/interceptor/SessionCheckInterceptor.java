package kr.or.ddit.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.ddit.member.model.MemberVo;

public class SessionCheckInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 사용자가 정상적으로 접속 했는지를 체크한다. 
		// LoginController 컨트롤러를 통해 정상적으로 접속했을 경우
		// Session에 S_MEMBER 속성이 존재해야 함.
		HttpSession session = request.getSession();
		MemberVo memberVo = (MemberVo)session.getAttribute("S_MEMBER");
		
		if (memberVo == null) {
			// 로그인 페이지로 이동
			response.sendRedirect("/login/view");
			
			return false;
		}else {
			
		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}
	
	
}
