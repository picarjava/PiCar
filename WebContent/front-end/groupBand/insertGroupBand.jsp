<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.groupBand.model.*"%>

<%
GroupBandVO groupBandVO = (GroupBandVO) request.getAttribute("groupBandVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<style>
table#table-1 {
	width: 450px;
	background-color: #008888;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>
</head>
<body bgcolor=#aaaaaa>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<table id="table-1">
		<tr>
			<td>
				<h3>發起揪團 - insertGroupBand.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>
	<h3>發起揪團</h3>

	<form action="/PiCar/GroupBand" method="POST">
		<table>
		<tr>
		<td>團名</td>
		<td><input type="TEXT" name="groupName" size="25"
					value="<%=(groupBandVO == null) ? "團名為空" : groupBandVO.getGroupName()%>" /></td>
		</tr>
		<tr>
				<td>簡介:</td>
				
				<td><textarea name="introduction" id="note" rows="3" cols="50">
					<%=(groupBandVO == null) ? "簡介為空" : groupBandVO.getIntroduction()%> </textarea></td>
			</tr>
			
		
		</table>
		<label for="memId">團名</label> <input type="text" name="groupName"
			id="memID" /><br> <label for="note" name="">簡介 </label><br>
		

		<input type="hidden" name="action" value="insert" />

	</form>
</body>
</html>