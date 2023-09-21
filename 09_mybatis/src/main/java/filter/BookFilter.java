package filter;

import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.Arrays;
import java.util.Map;

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
 * Servlet Filter implementation class BookFilter
 */
@WebFilter("*.do")
public class BookFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public BookFilter() {
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
	  HttpServletRequest req = (HttpServletRequest)request;
	  HttpServletResponse res = (HttpServletResponse)response;
	  req.setCharacterEncoding("UTF-8");
	  
	  Map<String, String[]> map = req.getParameterMap();
	  for(Map.Entry<String, String[]> entry : map.entrySet()) {
	    System.out.println(entry.getKey() + ":" + Arrays.toString(entry.getValue()));
	  }
	  
	  
	  
	  chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
