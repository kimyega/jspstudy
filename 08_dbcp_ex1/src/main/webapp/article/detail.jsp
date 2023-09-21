<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<style>
  .link a {
    margin-right: 20px;
  }

</style>
<script>
  
  $(function() {
    fnDelete();
  })
  
  function fnDelete() {
    $('#delete_link').click(function(event) {
      if(!confirm('게시글을 삭제할까요?')){
        event.preventDefault();
        return;
      }
    })
  }
</script>
</head>
<body>

  <div class="link">
    <a href="${contextPath}/article/writeArticle.do">작성하러가기</a>
    <a href="${contextPath}/article/getArticleList.do">목록으로이동</a>
    <a href="${contextPath}/article/editArticle.do?article_no=${article.article_no}">수정하러가기</a>
    <a id="delete_link" href="${contextPath}/article/deleteArticle.do?article_no=${article.article_no}">삭제하기</a>
  </div>

  <hr>
  
 <div>
   <div>게시글번호: ${article.article_no}</div>
   <div>제목: ${article.title}</div>
   <div>작성자: ${article.editor}</div>
   <div>조회수: ${article.hit}</div>
   <div><pre>${article.content}</pre></div>
   <div>최종수정일: ${article.last_modified}</div>
   <div>최초작성일: ${article.created}</div>
 </div>
 <div>${paging}</div>
</body>
</html>