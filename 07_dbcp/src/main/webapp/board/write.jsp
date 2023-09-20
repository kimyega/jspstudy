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
<script>
$(function() {
 fnBoardList();
 fnBoardRegister();  
})

 function fnBoardList() {
  $('#btn_list').click(function() {
    location.href = '${contextPath}/board/list.do';
  })
}
 function fnBoardRegister() {
  $('#frm_register').submit(function(event) {
    if($('#title').val() === ''){
      alert('제목은 필수입니다.');
      event.preventDefault();
      return;
    }
  })
 }
</script>
</head>
<body>

  <div>
    <form id="frm_register" method="post" action="${contextPath}/board/register.do">
      <div>
        <label for="title">제목</label>
        <input type="text" id="title" name="title">
      </div>
      <div>
        <textarea rows="5" cols="50" name="content"></textarea>
      </div>
      <div>
        <button type="submit">작성완료</button>
        <button type="reset">작성초기화</button>
        <button type="button" id="btn_list">목록으로 이동</button>
      </div>
    </form>
  </div>

</body>
</html>