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
  .book spaan{
  margin-right: 20px;
}
</style>
<script>

  $(function() {
    fnDetail();
  })
  function fnDetail() {
    $('.book').click(function() {
      location.href = '${contextPath}/book/detail.do?=' + $(this).find('.book_no').text();
    })
  }

</script>
</head>
<body>

  <div><a href="${contextPath}/book/list.do">작성하러 가기</a></div>
  
  <div>
    <c:forEach items="${bookList}" var="book">\
     <div class="book">
      <span class="book_no">${book.bookNo}</span>
      <span>${book.title}</span>
      <span>${book.author}</span>
      <span>${book.price}</span>
      <span>${book.pubdate}</span>
     </div>
    </c:forEach>
  </div>
</body>
</html>