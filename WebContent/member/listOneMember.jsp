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
<body>

	<table align="center" border="1" id="table1">
		<tr>
			<td>memID</td>
			<td>name</td>
			<td>mmail</td>
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
			<td><></td>
			<td><%=memberVO.getMemID()%>></td>
			<td><%=memberVO.getMemID()%>></td>
			<td><%=memberVO.getMemID()%>></td>
			<td><%=memberVO.getMemID()%>></td>
			<td><%=memberVO.getMemID()%>></td>
			<td><%=memberVO.getMemID()%>></td>
			<td><%=memberVO.getMemID()%>></td>
			<td><%=memberVO.getMemID()%>></td>
			<td><%=memberVO.getMemID()%>></td>
			<td><%=memberVO.getMemID()%>></td>
			<td><%=memberVO.getMemID()%>></td>
			<td><%=memberVO.getMemID()%>></td>
			<td><%=memberVO.getMemID()%>></td>
			<td><%=memberVO.getMemID()%>></td>
		</tr>

	</table>

</body>
</html>