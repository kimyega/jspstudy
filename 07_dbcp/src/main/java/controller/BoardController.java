package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.BoardService;
import service.BoardServiceImpl;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  // request.setCharacterEncoding("UTF-8");
	  response.setContentType("text/html; charset=UTF-8");
	  
	  String requestURI = request.getRequestURI();
	  String contextPath = request.getContextPath();
	  String urlMapping = requestURI.substring(contextPath.length());
	  
	  ActionForward af = null;
	  BoardService boardService = new BoardServiceImpl();
	  
	  switch(urlMapping) {
	  case "/board/write.do":
	    af = new ActionForward("/board/write.jsp", false);
	    break;
	  case "/index.do":
	    af = new ActionForward("/index.jsp", false);
	  case "/board/register.do":
	    af = boardService.register(request);
	    break;
	  case "/board/list.do":
	    af = boardService.getBoardList(request);
	    break;
	  case "/board/detail.do":
	    af = boardService.getBoardByNo(request);
	    break;
	  case "/board/edit.do":
	    af = boardService.edit(request);
	    break;
	  case "/board/modify.do":
	    af = boardService.modify(request);
	    break;
	  case "/board/delete.do":
	    af = boardService.delete(request);
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
