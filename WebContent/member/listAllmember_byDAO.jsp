<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
#table1 {
	width: 1300px;
	height: 350px;
	background-color: #7afec6;
	margin-top: 20px;
	margin-bottom: 20px;
	text-align: center;
	font-family: Microsoft JhengHei;
}

table1, td {
	
}

.page1 {
	margin-left: 80px
}
</style>

<body bgcolor="#11e1e9">


	<div id="errmsg">
	<c:if test="${not empty errorMsgs}">
	<ul>	
		<c:forEach var="message" items="${errorMsgs}">
			<li>
				${message}
			</li>
		</c:forEach>	
	</ul>
	</c:if>
	</div>
	<h1 align="center">Picar 員工資料 by DAO</h1>
	<div class="page1">
		<a href="select_page.jsp">回主頁面</a>
	</div>
	<br>
	<br>

	<div class="page1"><%@ include file="page1.file"%></div>

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
			<td>修改</td>
			<td>刪除</td>
		</tr>

	<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
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
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="memID"  value="${memberVO.memID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
				<form method="post" action="member.do">
				<input type="submit" value="刪除">
				<input type="hidden" name="memID"  value="${memberVO.memID}">
				<input type="hidden" name="action" value="delete">
				</form>
			
			</td>
		</tr>
	</c:forEach>

	</table>
	<div align="center"><%@ include file="page2.file"%></div>
</body>
</html>