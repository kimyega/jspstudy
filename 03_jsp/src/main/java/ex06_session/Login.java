package ex06_session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.setCharacterEncoding("UTF-8");
	  
	  String id = request.getParameter("id");
	  String pw = request.getParameter("pw");
	
	  // id와 pw가 동일하면 성공
	  if(id.equals(pw)) {
	    // 로그인 처리 : session에 id를 저장해 두기
	    HttpSession session = request.getSession();
	    session.setAttribute("id", id);
	    session.setMaxInactiveInterval(60 * 10);   // 10분 세션 유지 
	  }
	  
	  // 로그인 화면으로 되돌아가기
	  response.sendRedirect(request.getContextPath() + "/ex06_session/login.jsp");
	  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
