<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="UTF-8" import="com.member.model.*"%>

<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<style>
#table1 {
	width: 1300px;
	background-color: #7afec6;
	margin-top: 100px;
	margin-bottom: 20px;
	text-align: center;
	font-family: Microsoft JhengHei;
}

</style>
<body bgcolor="#11e1e9">
<h3>listOneMember.jsp</h3>
<a href="select_page.jsp">回主頁面</a>
	<h1 align="center">Picar 員工資料 by 查詢單筆</h1>
	<table align="center" border="1" id="table1">
		<tr>
			<td>memID</td>
			<td>name</td>
			<td>email</td>
			<td>password</td>
			<td>phone</td>
			<td>creditcard</td>
			<td>pet</td>
			<td>smoke</td>
			<td>gender</td>
			<td>token</td>
			<td>activityToken</td>
			<td>birthday</td>
			<td>verified</td>
			<td>babySeat</td>
		</tr>

		<tr>			
			<td><%=memberVO.getMemID()%></td>
			<td><%=memberVO.getName()%></td>
			<td><%=memberVO.getEmail()%></td>
			<td><%=memberVO.getPassword()%></td>
			<td><%=memberVO.getPhone()%></td>
			<td><%=memberVO.getCreditcard()%></td>
			<td><%=memberVO.getPet()%></td>
			<td><%=memberVO.getSmoke()%></td>
			<td><%=memberVO.getGender()%></td>
			<td><%=memberVO.getToken()%></td>
			<td><%=memberVO.getActivityToken()%></td>
			<td><%=memberVO.getBirthday()%></td>
			<td><%=memberVO.getVerified()%></td>
			<td><%=memberVO.getBabySeat()%></td>
		</tr>

	</table>

</body>
</html>