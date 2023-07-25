<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="helloServlet.servlet.domain.member.Member" %>
<%@ page import="helloServlet.servlet.domain.member.MemberRepository" %>
<%

    MemberRepository memberRepository = MemberRepository.getInstance();
    System.out.println("MemberSaveServlet.service");

    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));
    Member member = new Member(username, age);
    memberRepository.save(member);
%>
<html>
<head>
      <title>JSP Form</title>
</head>
<body>
성공
<ul>
    <li>id = <%=member.getId()%></li>
    <li>username = <%=member.getUsername()%></li>
    <li>age = <%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>