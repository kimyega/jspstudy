package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.MvcService;
import service.MvcServiceImpl;

/**
 * Servlet implementation class MvcController
 */
@WebServlet("*.do")
public class MvcController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MvcController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  request.setCharacterEncoding("UTF-8");
	  response.setContentType("text/html; charset=UTF-8");
	  
	  String requestURI = request.getRequestURI();
	  String contextPath = request.getContextPath();
	  String urlMapping = requestURI.substring(contextPath.length());
	  
	  MvcService mvcService = new MvcServiceImpl();
	  ActionForward af = null;
	  
	  switch(urlMapping) {
	  case "/getDate.do":
	    af = mvcService.getDate(request);
	    break;
	  case "/getTime.do":
	    af = mvcService.getTime(request);
	    break;
	  case "/getDateTime.do":
	    mvcService.getDateTime(request, response);
	    break;
	  }
	  if(af != null) {
	    if(af.isRedirect()) {
	       response.sendRedirect(af.getPath());
	    } else {
	      request.getRequestDispatcher(af.getPath()).forward(request, response);
	      
	    }
	  }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
