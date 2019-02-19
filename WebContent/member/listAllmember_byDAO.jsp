<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
	MemberDAO memberDAO = new MemberDAO();
	List<MemberVO> list = memberDAO.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>ALL MEMBER</title>
</head>

<style>
	#table1{
		width: 1100px;
		height: 350px;
		background-color:	#7afec6;
		margin-top: 50px;
		margin-bottom: 20px;
		text-align:center;
		font-family:Microsoft JhengHei;
	}

</style>
<h1 align="center">Picar 員工資料</h1>
<body bgcolor="#d1e9e9">

	<a align="right" href="select_page.jsp" >回主頁面</a><br><br>

	<table align="center" border="1" id="table1">
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
				
				
		<c:forEach var="memberVO" items="${list}">
			<tr>
				<td>${memberVO.memID}</td>
				<td>${memberVO.name}</td>
				<td>${memberVO.email}</td>
				<td>${memberVO.password}</td>
				<td>${memberVO.phone}</td>
				<td>${memberVO.creditcard}</td>
				<td>${memberVO.pet}</td>
				<td>${memberVO.smoke}</td>
				<td>${memberVO.gender}</td>
				<td>${memberVO.token}</td>
				<td>${memberVO.activityToken}</td>
				<td>${memberVO.birthday}</td>
				<td>${memberVO.verified}</td>
				<td>${memberVO.babySeat}</td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>