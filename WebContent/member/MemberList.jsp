<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="spms.vo.Member" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 목록</title>
</head>
<body>
	<h1>회원 목록</h1>
	<p><a href='add'>신규 회원</a></p>
	<hr/>
	<table border="1">
	<%
	ArrayList<Member> members = (ArrayList<Member>)request.getAttribute("members");
	
	for(Member member : members){
	%>
	<tr>
	<td><%=member.getNo() %></td>
	<td><a href='update?no=<%=member.getNo() %>' method="GET"><%=member.getName() %></a></td>
	<td><%=member.getEmail() %></td>
	<td><%=member.getCreateDate() %></td>
	<td><a href='delete?no=<%=member.getNo() %>' method='GET'>[삭제]</a></td>
	</tr>
	<%
	}
	%>
	</table>
</body>
</html>