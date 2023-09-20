package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import domain.ArticleDto;

public class ArticleDao {

//모든 메소드가 공동으로 사용할 객체선언
 private Connection con;
 private PreparedStatement ps;
 private ResultSet rs;
 
 // Connection Pool 관리 DataSource 객체 선언 
 private DataSource dataSource;
 
 // Singleton Pattern으로 Dao 객체 생성
 private static ArticleDao dao = new ArticleDao();
 private ArticleDao() {
   try {
     Context context = new InitialContext();
     Context env = (Context)context.lookup("java:comp/env");
     dataSource = (DataSource)env.lookup("jdbc/oraclexe");
   } catch(Exception e) {
     e.printStackTrace();
   }
 }
 public static ArticleDao getDao() {
   return dao;
 }
 public void close() {
   try {
     if(rs != null) rs.close();
     if(ps != null) ps.close();
     if(con != null) con.close();
     
   } catch (Exception e) {
     e.printStackTrace();
   }
 }
 
 public int addArticle(ArticleDto dto) {
   // 등록 결과 (insert 실행 결과는 삽입된 행의 개수이다.)
   int insertResult = 0;
   
   try {
     // Connection Pool 에서 Connection을 하나 받아온다.
     // Connection Pool 관리는 DataSource 객체가 수행한다.
     con = dataSource.getConnection();
     
     // 쿼리문 작성
     String sql = "INSERT INTO ARTICLE_T(ARTICLE_NO, TITLE, CONTENT, EDITOR, HIT, LASTMODIFIED, CREATED) VALUES(ARTICLE_SEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE, SYSDATE)";
     
     // ps 객체 생성 - 쿼리문 실행
     ps = con.prepareStatement(sql);
     
     // 쿼리문의 변수에 값을 전달
     ps.setString(1, dto.getTitle());    // 1번째 변수에 title 전달
     ps.setString(2, dto.getContent());    // 2번째 변수에 content 전달
     ps.setString(3, dto.getEditor());
     
     // 쿼리문의 실행
     insertResult = ps.executeUpdate();
     
   } catch (Exception e) {
     e.printStackTrace();
   } finally {
     close();
   }
   
   // 등록 결과 반환
   return insertResult;
 }
 
 // 게시글 개수 반환 메소드
 public int getArticleCount() {

   int count = 0;
   
   try {
     con = dataSource.getConnection();
     String sql = "SELECT COUNT(*) FROM ARTICLE_T";
     
     ps = con.prepareStatement(sql);
     rs = ps.executeQuery();
     if(rs.next()) {
//       rs.getInt("COUNT(*)"); 둘다 가능
       count = rs.getInt(1);
     }
   } catch (Exception e) {
     e.printStackTrace();
   } finally {
     close();
   }
   return count;
 }
 public List<ArticleDto> getArticleList(Map<String, Object> map){
   
   List<ArticleDto> list = new ArrayList<ArticleDto>();
   
   try {
     con = dataSource.getConnection();
     String sql = "SELECT A.ARTICLE_NO, A.TITLE, A.CONTENT, A.EDITOR, A.HIT, A.LASTMODIFIED, A.CREATED"
                + "  FROM (SELECT ROW_NUMBER() OVER (ORDER BY ARTICLE_NO DESC) AS RN, ARTICLE_NO, TITLE, CONTENT, EDITOR, HIT, LASTMODIFIED, CREATED"
                + "           FROM ARTICLE_T) A"
                + " WHERE A.RN BETWEEN ? AND ?";
     ps = con.prepareStatement(sql);
     ps.setInt(1, (int)map.get("begin"));
     ps.setInt(2, (int)map.get("end"));
     rs = ps.executeQuery();
     while(rs.next()) {
       ArticleDto dto = ArticleDto.builder()
           .article_no(rs.getInt(1))
           .title(rs.getString(2))
           .content(rs.getString(3))
           .editor(rs.getString(4))
           .hit(rs.getInt(5))
           .last_modified(rs.getDate(6))
           .created(rs.getDate(7))
           .build();
       list.add(dto);
     }
   } catch (Exception e) {
     e.printStackTrace();
   } finally {
     close();
   }
   return list;
   
 }
 // 
 public ArticleDto getArticleDetail(int article_no) {
   
   // 게시글
   ArticleDto dto = null;
   
   try {
     con = dataSource.getConnection();
     String sql = "SELECT ARTICLE_NO, TITLE, CONTENT, EDITOR, HIT, LASTMODIFIED, CREATED"
                + "  FROM ARTICLE_T"
                + " WHERE ARTICLE_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, article_no);
      rs = ps.executeQuery();
      if(rs.next()) {
        dto = ArticleDto.builder()
            .article_no(rs.getInt(1))
            .title(rs.getString(2))
            .content(rs.getString(3))
            .last_modified(rs.getDate(4))
            .created(rs.getDate(5))
            .build();
      }
      
     
     
   } catch (Exception e) {
     e.printStackTrace();
   } finally {
     close();
   }
   
   // 게시글 반환
   return dto;
 }

 public int modifyArticle(ArticleDto dto) {
   int modifyResult = 0;
   
   try {
     
     con = dataSource.getConnection();
     
     String sql = "UPDATE ARTICLE_T"
                + "   SET TITLE = ?, CONTENT = ?, EDITOR = ?, LASTMODIFIED = SYSDATE"
                + " WHERE ARTICLE_NO = ?";
     
     ps = con.prepareStatement(sql);
     ps.setString(1, dto.getTitle());
     ps.setString(2, dto.getContent());
     ps.setInt(3, dto.getArticle_no());
     ps.setString(4, dto.getEditor());
     
     modifyResult = ps.executeUpdate();
     
     
     
   } catch (Exception e) {
     e.printStackTrace();
   } finally {
     close();
   }
   
   
   return modifyResult;
 }
 public int deleteArticle(int article_no) {
   int deleteResult = 0;
   
   try {
     con = dataSource.getConnection();
     String sql = "DELETE FROM ARTICLE_T WHERE ARTICLE_NO = ?";
     ps = con.prepareStatement(sql);
     ps.setInt(1, article_no);
     deleteResult = ps.executeUpdate();
     
   } catch (Exception e) {
     e.printStackTrace();
   } finally {
     close();
   }
   return deleteResult;
 }
}
