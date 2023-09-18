<%@page import="javax.swing.plaf.basic.BasicScrollPaneUI.VSBChangeListener"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.time.LocalDate"%>
<%@page import="ex05_jstl.BlogPost"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <%--
    <c:forEach>
    1. 반복문을 처리한다.
    2. 형식
      1) 일반 for문
        <c:forEach var="속성" begin="시작값" end="종료값" step="증가값">
          ${속성}
        </c:forEach>
      2) 향상 for문
        <c:forEach var="요소" items="배열 or 리스트">
          ${요소}
        </c:forEach> 
   --%>
   
   <%-- 1 ~ 5 출력 --%>
   <c:forEach var="plus" begin="1" end="5" step="1">
   <div>${plus}</div>
   </c:forEach>
   
   <%-- 5 ~ 1 출력 --%>
   <c:set var="minus" value="5"/>
   <c:forEach var="i" begin="1" end="5" step="1">
   <div>${minus}</div>
   <c:set var="minus" value="${minus - 1}"/>
   </c:forEach>
  
   <hr>
   
   <%--  <c:forEach> 태그와 배열 --%> 
   <%
    String[] seasons = {"봄", "여름", "가을", "겨울"};
    pageContext.setAttribute("seasons", seasons);
   %> 
    <c:forEach var="season" items="${seasons}" varStatus="vs">
      <div>인덱스 : ${vs.index}</div>
      <div>요소   : ${season}</div>
      <c:out value="${vs.index}"/>
      <c:out value="${vs.end}"/>
    </c:forEach>
    
   <hr>
   
   <%-- <c:forEach> 태그와 리스트 --%>
   <%
     List<String> jobs = Arrays.asList("PM", "기획자", "개발자", "퍼블리셔");
     pageContext.setAttribute("jobs", jobs);
   %> 
   <c:forEach var="job" items="${jobs}" varStatus="vs">
      <div>인덱스  : ${vs.index}</div>
      <div>직업    : ${job}</div>
   </c:forEach>    
   
   
   <hr>
   <%-- 임의의 BlogPost 3개를 List에 저장하고 화면에 출력하기 --%>
   <%
     LocalDate date1 = LocalDate.of(2023, 1, 23);  
     BlogPost blog1 = new BlogPost(1, "브이로그", 5000, date1);
     
     LocalDate date2 = LocalDate.of(2023, 4, 30);  
     BlogPost blog2 = new BlogPost(2, "코딩일기", 10000, date2);
     
     LocalDate date3 = LocalDate.of(2023, 5, 20);  
     BlogPost blog3 = new BlogPost(3, "맛집탐방", 7500, date3);
     
     List<BlogPost> posts = Arrays.asList(blog1, blog2, blog3);
     pageContext.setAttribute("posts", posts);
   %>   
   <c:forEach var="post" items="${posts}">
      <div class="blog">
        <span class="blogPostNo">${post.blogPostNo}번째</span>
        <span class="title">블로그 제목 :  '<a href="">${post.title}</a>'</span>
        <span class="hit">조회수 : '<fmt:formatNumber value="${post.hit}" pattern="#,##0"/>'</span>
        <span class="createdAt">작성 날짜 : '${post.createdAt}'</span>
      </div>
   </c:forEach>
   
</body>
</html>