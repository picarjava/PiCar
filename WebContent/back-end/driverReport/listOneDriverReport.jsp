<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.driverReport.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	DriverReportVO driverReportVO = (DriverReportVO)request.getAttribute("driverReportVO"); //DriverReportServlet.java(Concroller), 存入req的DriverReportVO物件
%>

<html>
<head>
<title>檢舉司機資料 - listOneDriverReport.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
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
	width: 1000px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>檢舉司機資料 - listOneDriverReport.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>檢舉司機單號</th>
			<th>會員編號</th>
			<th>管理員編號</th>
			<th>訂單編號</th>
			<th>內容</th>
			<th>時間</th>
			<th>狀態</th>
		</tr>
		<tr>
			<td><%=driverReportVO.getDreportID()%></td>
			<td><%=driverReportVO.getMemID()%></td>
			<td><%=driverReportVO.getAdminID()%></td>
			<td><%=driverReportVO.getOrderID()%></td>
			<td><%=driverReportVO.getContent()%></td>
			<td><%=driverReportVO.getTime()%></td>
			<td><%=driverReportVO.getState()%></td>
		</tr>
	</table>

</body>
</html>