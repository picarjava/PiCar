<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.storeRecord.model.*"
	import="java.util.List"%>
<%@ page import="com.member.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<%	
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
	StoreRecordService storeRecordSvc = new StoreRecordService();	
	
	List<StoreRecordVO> list = storeRecordSvc.getMemStoreRecordNeg(memberVO.getMemID());
	pageContext.setAttribute("list", list);
	
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	<h3>listOneStoreRecordMember.jsp</h3>
	<a class="box" href=/PiCar/regna-master/homeindex.jsp> 請按此回首頁 </a>
	<h1 align="center"> ${storeRecordVO.memID} <%=memberVO.getName() %>	你好，以下為您的扣款紀錄</h1>
	<table align="center" border="1" id="table1">
		<tr>
			<td>訂單ID</td>
			
			<td>扣款金額</td>
			<td>扣款時間</td>
		</tr>
		<c:forEach var="storeRecordVO" items="${list}">
			<tr>

				<td>${storeRecordVO.orderID}</td>				
				<td>${-storeRecordVO.amount}</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${storeRecordVO.saveDate}" /></td>				
			</tr>
		</c:forEach>
<!-- 		<tr> -->
<!-- 			<td colspan="1">總儲值金額</td>	 -->

<%-- 			<td><%=memberVO.getToken() %></td> --%>
<!-- 		</tr> -->
			

	</table>

	<h1 align="center">當前代幣餘額:<%=memberVO.getToken() %></h1>

</body>
</html>