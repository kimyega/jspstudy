package util;

import java.util.Iterator;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PageVo {

    private int page;     // 현재 페이지 번호(요청 파라미터로 받는다.)
    private int total;    // 전체 항목의 개수(DB에서 구한 뒤 받는다.)
    private int display;  // 한 페이지에 표시할 항목의 개수(요청 파라미터로 받는다.)
    private int begin;    // 한 페이지에 표시되는 항목의 시작 번호(계산한다.)
    private int end;      // 한 페이지에 표시되는 항목의 종료 번호(계산한다.)
    
    private int totalPage;          // 전체 페이지의 개수 (계산한다.)
    private int pagePerBlock = 2;   // 한 블록에 표시되는 페이지의 개수 (임의로 정하기)
    private int beginPage;          // 한 블록에 표시되는 페이지의 시작 번호 (계산스)
    private int endPage;            // 한 블록에 표시되는 페이지의 종료 번호 (계산스)
    
    public void setPaging(int page, int total, int display) {
      
      // 받은 정보 저장
      this.page = page;
      this.total = total;
      this.display = display;
      
      // 계산한 정보 저장
      begin = (page - 1) * display + 1;
      end = begin + display - 1;
      
      if(end > total) {
        end = total;
      }
//      전체 페이지를 나타낼 때 필요한 정보
      totalPage = (int)Math.ceil((double)total / display);  
      
      // 각 블록의 시작과 종료 페이지 
      beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
      endPage = beginPage + pagePerBlock - 1;
      if(endPage > totalPage) {
        endPage = totalPage;
      }
    }
    public String getPaging(String url) {
      StringBuilder sb = new StringBuilder();
      
      sb.append("<div>");
      // 이전 블록
      if(beginPage == 1) {
        sb.append("<sapn>이전</span>");
      } else {
        sb.append("<a href=\"" + url + "?page=" + (beginPage - 1) + "\">이전</a>");
      }
      
      
      //페이지 번호
      for (int p = beginPage; p <= endPage; p++) {
        if(p == page) {
          sb.append("<span>" + p + "</span>");
        } else {
          sb.append("<a href=\"" + url + "?page=" + p + "\">" + p + "</a>");
        }
      }     
      // 다음 블록
      if(endPage == total) {
        sb.append("<sapn>다음</span>");
      } else {
        sb.append("<a href=\"" + url + "?page=" + (endPage + 1) + "\">다음</a>");
      }
      sb.append("</div>");
      return sb.toString();
    }
}
