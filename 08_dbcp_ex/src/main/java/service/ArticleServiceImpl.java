package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;
import domain.ArticleDto;
import repository.ArticleDao;
import util.PageVo;

public class ArticleServiceImpl implements ArticleService {

//모든 서비스가 공동으로 사용하는 BoardDao, PageVo 객체 가져오기
 private ArticleDao dao = ArticleDao.getDao();
 private PageVo pageVo = new PageVo();
 
 @Override
 public ActionForward addArticle(HttpServletRequest request) {
   
   String title = request.getParameter("title");
   String content = request.getParameter("content");
   String editor = request.getParameter("editor");
   
   ArticleDto dto = ArticleDto.builder()
                 .title(title)
                 .content(content)
                 .editor(editor)
                 .build();
   
   // BoardDao의 register 메소드 호출
   int registerResult = dao.addArticle(dto);
   
   // 등록 성공 1 실패 0
   String path = null;
   if(registerResult == 1) {
     path = request.getContextPath() + "/article/list.do";
   } else if(registerResult == 0) {
     path = request.getContextPath() + "/index.do";
   }
   
   // 어디로 어떻게 이동하는지 반환 insert 수행후에는 반드시 redirect 이동한다.
   return new ActionForward(path, true);
 }
 
 @Override
 public ActionForward getArticleList(HttpServletRequest request) {
  
   // 전달된 페이지 번호 확인 (페이지 번호의 전달이 없으면 1페이지를 연다.)
   Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
   int page = Integer.parseInt(opt.orElse("1"));
   
   int total = dao.getArticleCount();        // dao 필요함.
   
   int display = 5;      // 고정 값 사용 (원하면 파라미터로 받아 오는 것으로 변경도 가능함)
   
   // PageVo의 모든 정보 계산
   pageVo.setPaging(page, total, display);
   
   // 게시글 목록을 가져올때 사용할 변수들을 Map으로 만듬
   Map<String, Object> map = new HashMap<String, Object>();
   map.put("begin", pageVo.getBegin());
   map.put("end", pageVo.getEnd());
   
   // DB로부터 게시글 목록 가져오기
   List<ArticleDto> articleList = dao.getArticleList(map);
   
   // 게시글 목록과 paging을 request 저장뒤 forward
   request.setAttribute("articleList", articleList);
   request.setAttribute("paging", pageVo.getPaging(request.getContextPath() + "/article/list.do"));
   return new ActionForward("/article/list.jsp", false);
 }
 
 @Override
 public ActionForward getArticleDetail(HttpServletRequest request) {
   
   // 상세 조회할 게시글 번호
   Optional<String> opt = Optional.ofNullable(request.getParameter("article_no"));
   int article_no = Integer.parseInt(opt.orElse("0"));
   
   // DB로부터 게시글 가져오기
   ArticleDto article = dao.getArticleDetail(article_no);
   
   // 게시글을 /board/detail.jsp에 전달하기 위해서 forward 처리
   request.setAttribute("article", article);
   return new ActionForward("/article/detail.jsp", false);
 }
 @Override
 public ActionForward editArticle(HttpServletRequest request) {
   Optional<String> opt = Optional.ofNullable(request.getParameter("article_no"));
   int article_no = Integer.parseInt(opt.orElse("0"));
   
   ArticleDto article = dao.getArticleDetail(article_no);
   
   request.setAttribute("article", article);
   return new ActionForward("/article/edit.jsp", false);
 }
 @Override
 public ActionForward modifyArticle(HttpServletRequest request) {
   String title = request.getParameter("title");
   String content = request.getParameter("content");
   String editor = request.getParameter("editor");
   int article_no = Integer.parseInt(request.getParameter("article_no"));
   
   ArticleDto dto = ArticleDto.builder()
                   .title(title)
                   .content(content)
                   .editor(editor)
                   .article_no(article_no)
                   .build();
   
   int modifyResult = dao.modifyArticle(dto);
   
   String path = null;
   
   // 수정 성공 1 실패 0
   if(modifyResult == 1) {
     path = request.getContextPath() + "/article/detail.do?article_no=" + article_no;
   } else {
     path = request.getContextPath() + "/index.do";
   }
   // update 이후에는 redirect 한다.
   return new ActionForward(path, true);
 }
 @Override
 public ActionForward deleteArticle(HttpServletRequest request) {
   Optional<String> opt = Optional.ofNullable(request.getParameter("article_no"));
   int article_no = Integer.parseInt(opt.orElse("0"));
   
   int deleteResult = dao.deleteArticle(article_no);
   
   String path = null;
   if(deleteResult == 1) {
     path = request.getContextPath() + "/article/list.do";
   } else if(deleteResult == 0){
     path = request.getContextPath() + "/index.do";
   }
   return new ActionForward(path, true);
 }

}
