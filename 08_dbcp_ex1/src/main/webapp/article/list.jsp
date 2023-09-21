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
  .article span {
  margin-right: 20px;
}
</style>
<script>
  $(function() {
    fnDetail();
  })

  function fnDetail() {
    $('.article').click(function() {
      location.href = '${contextPath}/article/getArticleDetail.do?article_no=' + $(this).find('.article_no').text();
    })
  }
</script>
</head>
<body>

  <div>
    <a href="${contextPath}/article/writeArticle.do">작성하러가기</a>
  </div>

  <hr>
  
 <div>
  <c:forEach items="${articleList}" var="article">
    <div class="article">
      <span class="article_no">${article.article_no}</span>
      <span>${article.title}</span>
      <span>${article.editor}</span>
      <span>${article.created}</span>
      <span>${article.hit}</span>
    </div>
  </c:forEach>
 </div>
 <div>${paging}</div>
</body>
</html>