package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.ArticleService;
import service.ArticleServiceImpl;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("*.do")
public class ArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  response.setContentType("text/html; charset=UTF-8");
    
    String requestURI = request.getRequestURI();
    String contextPath = request.getContextPath();
    String urlMapping = requestURI.substring(contextPath.length());
    
    ActionForward af = null;
    ArticleService articleService = new ArticleServiceImpl();
    
    switch(urlMapping) {
    case "/article/writeArticle.do":
      af = new ActionForward("/article/writeArticle.jsp", false);
      break;
    case "/index.do":
      af = new ActionForward("/index.jsp", false);
    case "/article/addArticle.do":
      af = articleService.addArticle(request);
      break;
    case "/article/getArticleList.do":
      af = articleService.getArticleList(request);
      break;
    case "/article/getArticleDetail.do":
      af = articleService.getArticleDetail(request);
      break;
    case "/article/editArticle.do":
      af = articleService.editArticle(request);
      break;
    case "/article/modifyArticle.do":
      af = articleService.modifyArticle(request);
      break;
    case "/article/deleteArticle.do":
      af = articleService.deleteArticle(request);
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
