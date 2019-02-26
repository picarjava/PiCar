<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.groupReport.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	GroupReportVO groupReportVO = (GroupReportVO)request.getAttribute("groupReportVO"); 
%>

<html>
<head>
<title>檢舉揪團資料 - listGroupDriverReport.jsp</title>

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
				<h3>檢舉司機資料 - listOneGroupReport.jsp</h3>
				<h4>
					<a href="greport_select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>檢舉揪團單號</th>
			<th>會員編號</th>
			<th>揪團編號</th>
			<th>管理員編號</th>
			<th>檢舉內容</th>
			<th>檢舉日期</th>
			<th>處理狀態</th>
		</tr>
		<tr>
			<td><%=groupReportVO.getGreportID()%></td>
			<td><%=groupReportVO.getMemID()%></td>
			<td><%=groupReportVO.getGroupID()%></td>
			<td><%=groupReportVO.getAdminID()%></td>
			<td><%=groupReportVO.getContent()%></td>
			<td><%=groupReportVO.getTime()%></td>
			<td><%=groupReportVO.getState()%></td>
		</tr>
	</table>

</body>
</html>