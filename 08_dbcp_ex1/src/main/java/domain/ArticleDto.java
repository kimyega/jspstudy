package domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ArticleDto {
  private int article_no;
  private String title;
  private String content;
  private String editor;
  private int hit;
  private Date last_modified;
  private Date created;
  
}
