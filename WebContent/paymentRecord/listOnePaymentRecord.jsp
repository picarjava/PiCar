<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="UTF-8" import="com.paymentRecord.model.*"%>

<%
	PaymentRecordVO paymentRecordVO = (PaymentRecordVO) request.getAttribute("paymentRecordVO");
%>

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
<h3>listOneRate.jsp</h3>
<a href="select_page.jsp">回主頁面</a>
	<h1 align="center">Picar 撥款紀錄 by 查詢單筆</h1>
	<table align="center" border="1" id="table1">
		<tr>
			<td>撥款ID</td>
			<td>司機ID</td>
			<td>撥款金額</td>
			<td>撥款時間</td>			
		</tr>

		<tr>			
			<td><%=paymentRecordVO.getPaymentID()%></td>
			<td><%=paymentRecordVO.getDriverID()%></td>
			<td><%=paymentRecordVO.getPayAmount()%></td>
			<td><%=paymentRecordVO.getPayTime()%></td>
			
		</tr>

	</table>

</body>
</html>