<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="UTF-8" import="com.storeRecord.model.*"%>


<%
	StoreRecordVO storeRecordVO = (StoreRecordVO) request.getAttribute("storeRecordVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>listOnePaymentRecord.jsp</title>
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
<h3>listOnePaymentRecord.jsp</h3>
<a href="select_page.jsp">回主頁面</a>
	<h1 align="center">Picar 儲值紀錄 by 查詢單筆</h1>
	<table align="center" border="1" id="table1">
		<tr>
			<td>儲值ID</td>
			<td>會員ID</td>
			<td>撥款金額</td>
			<td>撥款時間</td>		
		</tr>

		<tr>		
			<td><%=storeRecordVO.getStoreID()%></td>	
			<td><%=storeRecordVO.getMemID()%></td>							
			<td><%=storeRecordVO.getAmount()%></td>			
			<td><%=storeRecordVO.getSaveDate()%></td>
			
		</tr>

	</table>

</body>
</html>