<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="UTF-8" import="com.storeRecord.model.*" import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
List<StoreRecordVO> list= (List) request.getAttribute("list");
%>

<jsp:useBean id="storeRecordDAO" scope="page" class="com.storeRecord.model.StoreRecordDAO" />
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
<h3>listOneStoreRecordMember.jsp</h3>
<a href="select_page.jsp">回主頁面</a>
	<h1 align="center">Picar 撥款紀錄 by 查詢單筆(司機個人)</h1>
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
			<td>${storeRecordVO.saveDate}</td>
						
		</tr>		
</c:forEach>
		<tr>
		<td></td>
		<td></td>
		<c:forEach var="storeRecordVO" items="${list}" begin="1" end="1" step="1" >
 		<td>${storeRecordDAO.getSumAmount(storeRecordVO.memID)}</td>
		</c:forEach>
		<td></td>
		</tr>
		
		
	</table>
	
			

</body>
</html>