<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
	MemberService memberSvc = new MemberService();
	List<MemberVO> list = memberSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>listAllmember_byDAO.jsp</title>
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

 <h3>listAllmember_byDAO.jsp</h3>
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
			<td>個人照片</td>
			<td>修改(此為前端畫面，無此功能)</td>
			<td>刪除(此為前端畫面，無此功能)</td>
		</tr>

	<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${memberVO.memID}</td>
			<td>${memberVO.name}</td>
			<td>${memberVO.email}</td>
			<td>${memberVO.password}</td>
			<td>${memberVO.phone}</td>
			<td>${memberVO.creditcard}</td>
			<td><c:choose>
			  <c:when test="${memberVO.pet == '1'}"> 喜好寵物  </c:when>
			  <c:when test="${memberVO.pet == '0'}">  不喜好寵物  </c:when>			 
			</c:choose></td>		
				
			<td><c:choose>
			  <c:when test="${memberVO.smoke == '1'}">抽菸  </c:when>
			  <c:when test="${memberVO.smoke == '0'}">  不抽菸  </c:when>			 
			</c:choose></td>	
			
			<td><c:choose>
			  <c:when test="${memberVO.gender == '1'}">男生  </c:when>
			  <c:when test="${memberVO.gender == '0'}">女生  </c:when>			 
			</c:choose></td>
			
			<td>${memberVO.token}</td>
			<td>${memberVO.activityToken}</td>
			<td>${memberVO.birthday}</td>
			
			<td><c:choose>
			  <c:when test="${memberVO.verified == '1'}">已經驗證  </c:when>
			  <c:when test="${memberVO.verified == '0'}">尚未驗證  </c:when>			 
			</c:choose></td>
			
			<td><c:choose>
			  <c:when test="${memberVO.babySeat == '1'}">需要  </c:when>
			  <c:when test="${memberVO.babySeat == '0'}">不需要  </c:when>			 
			</c:choose></td>
			
			
			<td><img src="http://localhost:8081/PiCar/front-end/member/member.do?memID=${memberVO.memID}"  width='200' height="200" onerror="this.src='cat.jpg'"></td>
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
			</td>
			
		</form>		
		
		</tr>
	</c:forEach>
	
	</table>
	<div align="center"><%@ include file="page2.file"%></div>
</body>
</html>