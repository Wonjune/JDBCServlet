<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 수정</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<h1>회원 수정</h1>
<form action='update' method='post'>
<jsp:useBean id="member" scope="request" class="spms.vo.Member"/>
번호 : <input type='text' name='no' value=<%=request.getParameter("no") %> readonly /><br>
이름 : <input type='text' name='name' value=<%=member.getName() %> /><br>
이메일 : <input type='text' name='email' value=<%=member.getEmail() %>/><br>
비밀번호 : <input type='password' name='password' value=<%=member.getPassword() %>/><br>
가입일 : <%=member.getCreateDate() %><br>
수정일 : <%=member.getModifiedDate() %><br>
<input type='submit' value='수정'/> <input type='button' value='삭제' onclick='location.href=\"delete?no=" + no + "\"'/> <input type='button' value='취소' onclick='location.href=\"list\"'/>
</form>
<jsp:include page="/tail.jsp"/>
</body>
</html>