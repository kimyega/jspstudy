<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<style>
  table {
    border-collapse: collaspe;
  }

</style>
</head>
<body>

  <div>
    <h1>학생 관리</h1>
    <button type="button" id="btn_add">신규학생등록</button>
  </div>
  <hr>
  <div>
    <label>평균</label>
    <input type="text" name="begin" id="begin" placeholder="begin">
    <label>~</label>
    <input type="text" name="end" id="end" placeholder="end">
    <button type="button" id="btn_query">조회</button>
    <button type="button" id="btn_queryAll">전체조회</button>
  </div>
  <hr>
  <hr>
  <div>
    <table border="1">
      <caption>전체 학생 <span id="student_count"></span>명</caption>
      <thead>
        <tr>
          <td>학번</td>
          <td>성명</td>
          <td>국어</td>
          <td>영어</td>
          <td>수학</td>
          <td>평균</td>
          <td>학점</td>
          <td>버튼</td>
        </tr>
      </thead>
      <tbody id="student_list">
      </tbody>
    </table>
  </div>
</body>
</html>