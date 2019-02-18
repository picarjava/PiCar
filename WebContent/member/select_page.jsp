<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>

<form method="post" action="member.do">
	<b>請輸入會員編號(EX:M001):</b>
	<input type="text" name="memberID">
	<input type="hidden" name="action" value="getOne_For_Display"><br>
	<input type="submit" value="submit">	
</form>

<ul>
<jsp:useBean id="memberdao" scope="page" class="com.member.model.MemberDAO" />

<li>
	<form method="post" action="member.do" >
		<b>選擇員工編號</b>
		<select size="1" name="memID">
			<c:forEach var="memberVO" items="${memberdao.all}" >
				<option value="${memberVO.memID}">${memberVO.memID}
			</c:forEach>
		
		
		</select>
	</form>

</li>
</ul>



</body>
</html>