package filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class BoardFilter
 */
@WebFilter("*.do")
public class BoardFilter extends HttpFilter implements Filter {
       
  /**
   * @see HttpFilter#HttpFilter()
   */
  public BoardFilter() {
    super();
    // TODO Auto-generated constructor stub
  }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	  // 컨트롤러가 실행되기 이전에 처리되는 코드
	  HttpServletRequest req = (HttpServletRequest)request;
	  HttpServletResponse res = (HttpServletResponse)response;
	  
	  req.setCharacterEncoding("UTF-8");
	  
	  System.out.println(req.getRequestURI());
	  
	  Map<String, String[]> map = req.getParameterMap();
	  for(Entry<String, String[]> entry : map.entrySet()) {
	     System.out.println(entry.getKey() + ":" + Arrays.toString(entry.getValue()));
	  }
	  
	  chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	  System.out.println("::::: BoardFilter 시작 :::::");
	}

}
