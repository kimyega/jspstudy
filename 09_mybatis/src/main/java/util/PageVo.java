package util;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PageVo {
  private int page;
  private int total;
  private int display;
  private int begin;
  private int end;
  
  private int totalPage;
  private int pagePerBlock = 2;
  private int beginPage;
  private int endPage;
  
  public void setPaging(int page, int total, int display) {
    this.page = page;
    this.total = total;
    this.display = display;
    
    begin = (page - 1) * display + 1;
    end = begin + display - 1;
    
    if(end > total) {
      end = total;
    }
    
    totalPage = (int)Math.ceil((double)total / display);
    beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
    endPage = beginPage + pagePerBlock - 1;
    if(endPage > totalPage) {
      endPage = totalPage;
    }
  }
  public String getPaging(String url) {
    StringBuilder sb = new StringBuilder();
    
    sb.append("<div>");
    if(beginPage == 1) {
      sb.append("<span>이전</span>");
    } else { 
      sb.append("<a href=\"" + url + "?page=" + (beginPage - 1) +  "\">이전</a>");
    }
    // 페이지 번호
    for(int p = beginPage; p <= endPage; p++) {
      if(p == page) {
        sb.append("<span>" + p + "</span>");
      } else {
        sb.append("<a href=\"" + url + "?page=" + p + "\">" + p + "</a>");
      }
    }
    sb.append("</div>");
    return sb.toString();
  }
}
