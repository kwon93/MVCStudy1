<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="helloServlet.servlet.domain.member.Member" %>
<%@ page import="helloServlet.servlet.domain.member.MemberRepository" %>
<%@ page import="java.util.List"%>
<%
MemberRepository memberRepository = MemberRepository.getInstance();
List<Member> members = memberRepository.findAll();
%>
<html>
<head>
      <title>JSP Form</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
<tbody>
<%
    for (Member member : members) {
        out.write("    <tr>");
        out.write("
        out.write("
        out.write("
        out.write("    </tr>");
        <td>" + member.getId() + "</td>");
        <td>" + member.getUsername() + "</td>");
        <td>" + member.getAge() + "</td>");
}
%>
  </tbody>
  </table>


</body>
</html>