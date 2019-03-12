<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="UTF-8" import="com.storeRecord.model.*"
	import="java.util.List" import="com.member.model.*"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	List<StoreRecordVO> list = (List) request.getAttribute("list");
	Integer sumCount = (Integer)request.getAttribute("sumCount");
	MemberVO memberVO = (MemberVO)session.getAttribute("memeberVO");
%>

<jsp:useBean id="storeRecordDAO" scope="page"
	class="com.storeRecord.model.StoreRecordDAO" />
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>listOneRate.jsp</title>
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
	<h3>listOneStoreRecordMemberBack.jsp</h3>
	<a href="select_page.jsp">回主頁面</a>
	<h1 align="center">Picar 儲值紀錄後台查詢，查個人</h1>
	<table align="center" border="1" id="table1">
		<tr>
			<td>儲值ID</td>
			<td>會員ID</td>
			<td>儲值金額</td>
			<td>儲值時間</td>
		</tr>
		<c:forEach var="storeRecordVO" items="${list}">
			<tr>

				<td>${storeRecordVO.storeID}</td>
				<td>${storeRecordVO.memID}</td>
				<td>${storeRecordVO.amount}</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${storeRecordVO.saveDate}" /></td>

			</tr>
		</c:forEach>
		<tr>
			<td colspan="2">總儲值金額</td>
			<td>${sumCount}</td>	
<%-- 			<c:forEach var="storeRecordVO" items="${list}" begin="0" end="0" --%>
<%-- 				step="1"> --%>
<%-- 				<td>${storeRecordDAO.getSumAmount(storeRecordVO.memID)}</td> --%>
<%-- 			</c:forEach> --%>
			<td></td>
		</tr>


	</table>



</body>
</html>