package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;
import domain.BoardDto;
import repository.BoardDao;
import util.PageVo;

public class BoardServiceImpl implements BoardService {
  
  // 모든 서비스가 공동으로 사용하는 BoardDao, PageVo 객체 가져오기
  private BoardDao dao = BoardDao.getDao();
  private PageVo pageVo = new PageVo();
  
  @Override
  public ActionForward register(HttpServletRequest request) {
    
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    
    BoardDto dto = BoardDto.builder()
                  .title(title)
                  .content(content)
                  .build();
    
    // BoardDao의 register 메소드 호출
    int registerResult = dao.register(dto);
    
    // 등록 성공 1 실패 0
    String path = null;
    if(registerResult == 1) {
      path = request.getContextPath() + "/board/list.do";
    } else if(registerResult == 0) {
      path = request.getContextPath() + "/index.do";
    }
    
    // 어디로 어떻게 이동하는지 반환 insert 수행후에는 반드시 redirect 이동한다.
    return new ActionForward(path, true);
  }
  
  @Override
  public ActionForward getBoardList(HttpServletRequest request) {
   
    // 전달된 페이지 번호 확인 (페이지 번호의 전달이 없으면 1페이지를 연다.)
    Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(opt.orElse("1"));
    
    int total = dao.getBoardCount();        // dao 필요함.
    
    int display = 5;      // 고정 값 사용 (원하면 파라미터로 받아 오는 것으로 변경도 가능함)
    
    // PageVo의 모든 정보 계산
    pageVo.setPaging(page, total, display);
    
    // 게시글 목록을 가져올때 사용할 변수들을 Map으로 만듬
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("begin", pageVo.getBegin());
    map.put("end", pageVo.getEnd());
    
    // DB로부터 게시글 목록 가져오기
    List<BoardDto> boardList = dao.getBoardList(map);
    
    // 게시글 목록과 paging을 request 저장뒤 forward
    request.setAttribute("boardList", boardList);
    request.setAttribute("paging", pageVo.getPaging(request.getContextPath() + "/board/list.do"));
    return new ActionForward("/board/list.jsp", false);
  }
  
  @Override
  public ActionForward getBoardByNo(HttpServletRequest request) {
    
    // 상세 조회할 게시글 번호
    Optional<String> opt = Optional.ofNullable(request.getParameter("board_no"));
    int board_no = Integer.parseInt(opt.orElse("0"));
    
    // DB로부터 게시글 가져오기
    BoardDto board = dao.getBoardByNo(board_no);
    
    // 게시글을 /board/detail.jsp에 전달하기 위해서 forward 처리
    request.setAttribute("board", board);
    return new ActionForward("/board/detail.jsp", false);
  }
  @Override
  public ActionForward edit(HttpServletRequest request) {
    Optional<String> opt = Optional.ofNullable(request.getParameter("board_no"));
    int board_no = Integer.parseInt(opt.orElse("0"));
    
    BoardDto board = dao.getBoardByNo(board_no);
    
    request.setAttribute("board", board);
    return new ActionForward("/board/edit.jsp", false);
  }
  @Override
  public ActionForward modify(HttpServletRequest request) {
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    int board_no = Integer.parseInt(request.getParameter("board_no"));
    
    BoardDto dto = BoardDto.builder()
                    .title(title)
                    .content(content)
                    .board_no(board_no)
                    .build();
    
    int modifyResult = dao.modify(dto);
    
    String path = null;
    
    // 수정 성공 1 실패 0
    if(modifyResult == 1) {
      path = request.getContextPath() + "/board/detail.do?board_no=" + board_no;
    } else {
      path = request.getContextPath() + "/index.do";
    }
    // update 이후에는 redirect 한다.
    return new ActionForward(path, true);
  }
  @Override
  public ActionForward delete(HttpServletRequest request) {
    Optional<String> opt = Optional.ofNullable(request.getParameter("board_no"));
    int board_no = Integer.parseInt(opt.orElse("0"));
    
    int deleteResult = dao.delete(board_no);
    
    String path = null;
    if(deleteResult == 1) {
      path = request.getContextPath() + "/board/list.do";
    } else if(deleteResult == 0){
      path = request.getContextPath() + "/index.do";
    }
    return new ActionForward(path, true);
  }

}
