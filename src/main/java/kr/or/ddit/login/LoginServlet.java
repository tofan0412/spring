package kr.or.ddit.login;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.service.BoardServiceI;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
    
   private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
   
   // 서블릿은 스프링에서 관리하지 않기 때문에 스프링 빈을 사용할 수 없다
   @Resource(name="boardService")
   private BoardServiceI boardService;
   
   @Override
   public void init() throws ServletException {
      
   }
   
   // login 화면을 클라이언트에게 응답으로 생성
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      logger.debug("loginServlet doGet");
      logger.debug("UNT_CD parameter : {}", request.getParameter("UNT_CD"));
      request.getRequestDispatcher("/login.jsp").forward(request, response);
      /*
      request.getServletContext().getNamedDispatcher("default").forward(request, response);
      위와 같이 작성하면 서버에게 위임할 수 있다. 단 서버마다 default Servlet의 이름이 다르기 때문에 주의해야 한다.
      (Tomcat에서는 dafault이지만, 다른 server에서는 default Servlet의 이름이 default가 아닐 수도 있다.
      */
   }
}

