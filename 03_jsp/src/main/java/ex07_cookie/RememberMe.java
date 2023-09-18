package ex07_cookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RememberMe
 */
@WebServlet("/rememberMe")
public class RememberMe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RememberMe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.setCharacterEncoding("UTF-8");
	  
	  String id = request.getParameter("id");
	  String rememberMe = request.getParameter("remember_me");
	  
	  System.out.println(id + rememberMe); // checkbox의 체크가 없으면 null,  입력상자의 입력이 없으면 빈문자열 ("")
	  
	  // 아이디 기억하시지
	  Cookie cookie = null;
	  
	  if(rememberMe != null) { // if(remember_me.equals("on"))과 동일함
	    cookie = new Cookie("remember_me", id);
	    cookie.setMaxAge(60 * 60 * 24 * 15);
	  } else {
	    cookie = new Cookie("remember_id", "");
	    cookie.setMaxAge(0);
	  }
	  // 쿠키는 클라이언트에게 전달한다. (쿠키는 클라이언트가 저장한다.)
	  response.addCookie(cookie);
	  
	  // 메인 화면으로
	  response.sendRedirect(request.getContextPath() + "/ex07_cookie/main.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
