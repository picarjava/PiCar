<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="UTF-8" import="com.rate.model.*"%>

<%
	RateVO rateVO = (RateVO) request.getAttribute("rateVO");
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
	<h1 align="center">Picar 資費資料 by 查詢單筆</h1>
	<table align="center" border="1" id="table1">
		<tr>
			<td>資費編號</td>
			<td>資費名稱</td>
			<td>資費(元/KM)</td>
			<td>基本費(元)</td>			
		</tr>

		<tr>			
			<td><%=rateVO.getRateID()%></td>
			<td><%=rateVO.getRateName()%></td>
			<td><%=rateVO.getRatePrice()%></td>
			<td><%=rateVO.getRateBasic()%></td>
			
		</tr>

	</table>

</body>
</html>