package ex07_binding;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Binding3
 */
@WebServlet("/binding3")
public class Binding3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Binding3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String msg1 = (String)request.getServletContext().getAttribute("msg");
	    System.out.println(msg1);
	    
	    String msg2 = (String)request.getSession().getAttribute("msg");
	    System.out.println(msg2);
	    
	    String msg3 = (String)request.getAttribute("msg");
	    System.err.println(msg3);
	    
	    response.sendRedirect("/servlet/binding4");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
