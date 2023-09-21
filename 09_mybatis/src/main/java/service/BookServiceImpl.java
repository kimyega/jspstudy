package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;
import domain.BookDto;
import repository.BookDao;
import util.PageVo;

public class BookServiceImpl implements BookService {
  private BookDao dao = BookDao.getDao();
  private PageVo pageVo = new PageVo();
  
  @Override
  public ActionForward bookList(HttpServletRequest request) {
    Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(opt.orElse("1"));
    int total = dao.bookCount();
    int display = 5;
    
    pageVo.setPaging(page, total, display);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("begin", pageVo.getBegin());
    map.put("end", pageVo.getEnd());
    
    List<BookDto> bookList = dao.bookList(map);
    
    request.setAttribute("bookList", bookList);
    request.setAttribute("paging", pageVo.getPaging(request.getContextPath() + "/book/list.do"));
    
    return new ActionForward("/book/list.jsp", false);
  }
  
}
