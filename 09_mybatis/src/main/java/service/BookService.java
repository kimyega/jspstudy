package service;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;

public interface BookService {

  public ActionForward bookList(HttpServletRequest request);
}
