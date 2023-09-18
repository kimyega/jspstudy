<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="ex04_el.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <%--
    Jsp binding 영역 (어떤 값을 속성(Attribute)의 형태로 저장할 때 사용하는 영역)
    
    1. pageContext  : this,               현재 페이지에서만 접근할 수 있다.
    2. request      : HttpServletRequest, 응답 전까지 접근할 수 있다.
    3. session      : HttpSession,        브라우저를 닫기 전까지 접근할 수 있다.
    4. application  : ServletContext,     애플리케이션 종료 전까지 접근할 수 있다.
    
    Jsp binding 우선 순위 (동일한 이름의 속성이 서로 다른 영역에 존재하는 경우 먼저 사용되는 속성이 있다.)
    pageContext > request > session > application
    
    Jsp binding 영역에 저장된 속성(Attribute)은 모두 표현언어(EL)로 표현할 수 있다.
   --%>
   
   <%--
     표현언어(EL)
     1. Expression Language
     2. binding 영역에 저장된 값을 나타낼 때 사용할 수 있는 언어이다. 주로 속성 (Attribute)
     3. 형식
        ${값}
     4. Jsp 표현식 <%=값%>을 대체할 수 있다.
     5. binding 영역 EL 내장 객체
        1) pageContext  = pageScope
        2) request      = repuestScope
        3) session      = sessionScope
        4) application  = applicationScope
    --%>
    
    <%
      pageContext.setAttribute("msg", "pageContext");
      request.setAttribute("msg", "request");
      session.setAttribute("msg", "session");
      application.setAttribute("msg", "application");
    %>
    <%-- 우선순위 확인하기 --%>
    <div>${msg}</div>
    <%-- 각 영역의 속성 확인하기 --%>
    <div>${pageScope.msg}</div>
    <div>${requestScope.msg}</div>
    <div>${sessionScope.msg}</div>
    <div>${applicationScope.msg}</div>
    
    <%-- 객체 사용하기 --%>
    <%
      // 객체를 만든 뒤 EL 사용이 가능한 binding 영역에 넣는다.
      Person person = new Person();
      person.setName("이광수");
      person.setAge(35);
      pageContext.setAttribute("person", person);
    %>
    <div>이름 : ${person.name}</div>  <%-- person.getName() 방식으로 호출 --%>
    <div>나이 : ${person.age}</div>
    <%-- Map 사용하기 --%>
    <%
      // Map을 만든뒤 EL 사용이 가능한 binding 영역에 넣는다.
      Map<String,Object> book = new HashMap<>();
      book.put("title", "어린왕자");
      book.put("price", "15000");
      pageContext.setAttribute("book", book);
    %>
    <div>제목 : ${book.title}</div>    
    <div>가격 : ${book.price}</div>    
    
    <%--
      EL 연산자
      1. 산술 연산 
        1) +
        2) -
        3) *
        4) / 또는 div
        5) % 또는 mod
      
      2. 크기 비교
        1) >  gt
        2) <  lt
        3) >= ge
        4) <= le
        5) == eq
        6) != ne
      3. 논리 연산
        1) 논리 AND   : && 또는 and
        2) 논리 OR    : || 또는 or
        3) 논리 NOT   : !  또는 not
      4. 조건 연산
        (조건식) ? true일 떄 : false일 때
     --%>
    <%
      pageContext.setAttribute("a", 5);
      pageContext.setAttribute("b", 2);
    %>
    <div>a + b = ${a + b}</div>
    <div>a - b = ${a - b}</div>
    <div>a * b = ${a * b}</div>
    <div>a / b = ${a / b}</div>
    <div>a div b = ${a div b}</div>
    <div>a % b = ${a % b}</div>
    <div>a mod b = ${a mod b}</div>

    <div>a gt b = ${a gt b}</div>
    <div>a lt b = ${a lt b}</div>
    <div>a ge b = ${a ge b}</div>
    <div>a le b = ${a le b}</div>
    <div>a eq b = ${a eq b}</div>
    <div>a ne b = ${a ne b}</div>

    <div>and = ${a ne b and a gt b}</div>
    <div>or = ${a lt b or a eq b}</div>
    <div>! = ${!(a ne b)}</div>
    <div>not = ${not(a ne b)}</div>

    <%--
      request 사용시 주의사항
      
      1. 속성을 저장한 경우
        request.setAttribute("name", "이광수")  --->>> ${name}
      2. 파라미터를 저장한 경우
        /ContextPath/URL Mapping?name=이광수    --->>> ${param.name}
     --%>
    
</body>
</html>