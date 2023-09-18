package ex08_Cookie;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Cookie1
 */
@WebServlet("/cookie1")
public class Cookie1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cookie1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  /*
     * 쿠키 
     * 1. 클라이언트측에 저장되는 정보를 의미한다.
     * 2. 보안에는 취약하므로 개인 정보 같은 민감 정보는 저장하지 않는다.
     */
    Cookie cookie1 = new Cookie("name", "홍기동");
    Cookie cookie2 = new Cookie("age","20");
    Cookie cookie3 = new Cookie("address",URLEncoder.encode("서울특별시 종로구", "UTF-8"));
    
    // 쿠키가 저장될 경로 설정하기 (생략하면 ContextPath 경로에 저장한다.)
    cookie1.setPath("/servlet");
    cookie2.setPath("/servlet/cookie1");
    
    // 쿠키가 유지되는 시간 설정하기 (생략하면 세션쿠키, 브라우저를 닫으면 지워짐)
    cookie1.setMaxAge(60 * 60);
    cookie2.setMaxAge(60 * 60 * 24 * 7);
    // cookie3은 세션쿠키
    
    // 쿠키 브라우저에 저장 (응답으로 처리)
    response.addCookie(cookie1);
    response.addCookie(cookie2);
    response.addCookie(cookie3);
    
    // Cookie2 서블릿으로 redirect 이동
    response.sendRedirect("/servlet/cookie2");
    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
