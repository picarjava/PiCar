<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.admin.model.*"%>

<%
AdminVO adminVO = (AdminVO) request.getAttribute("adminVO"); 

%>

<html>
<head>
<title>PICAR BACK-END</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"
	name="viewport" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<!--     Fonts and icons     -->
<link rel="stylesheet" type="text/css"
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
<!-- Material Kit CSS -->
<link href="assets/css/material-dashboard.css" rel="stylesheet" />
<style>
table, tr, td, th {
	background-color: white;
	border: 1px solid #aaa;
	text-align: center;
	padding: 5px;
	margin-left: 10%;
	font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro',
		'Noto Sans CJK SC', monospace;
}

table {
	width: 80%;
}

.col-9 {
	margin-top: -15px;
	margin-left: -55px;
	margin-bottom: 1rem;
}

h4 {
	padding-left: 50%;
	font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro',
		'Noto Sans CJK SC', monospace;
}

h3 {
	font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro',
		'Noto Sans CJK SC', monospace;
}

#btn1 {
	padding: 20px;
	font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro',
		'Noto Sans CJK SC', monospace;
	margin-left: 48%;
}

.form-control {
	background: no-repeat center bottom, center calc(100% - 1px);
	border: 0;
	transition: background 0s ease-out;
	padding-left: 480px;
	padding-right: 0;
	border-radius: 0;
	font-size: 14px;
}

#s2 {
	font-size: 20px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>管理員資料修改 - update_Admin_input.jsp</h3>
				<h4>
					<a href="admin_select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h4>資料修改</h4>
	<%-- 錯誤表列 --%>
	<table>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
	</table>

	<FORM METHOD="post" ACTION="admin.do" name="form1">
		<table>
			<tr>
				<td>管理員編號<font color=red></font></td>
				<td><input class="form-control" type="text" size="45"
					value="<%=adminVO.getAdminID()%>"
					placeholder="Readonly input here..." readonly></td>
			</tr>
			<tr>
				<td>管理員姓名</td>
				<td><input class="form-control" type="TEXT" name="adminName"
					size="45" value="<%=adminVO.getAdminName()%>"
					placeholder="Readonly input here..." readonly /></td>
			</tr>
			
		
				
			<input type="hidden" name="password" size="45" value="<%=adminVO.getPassword()%>" />

		
			<tr>
				<td>在職狀態</td>
				<td><select name="isEmp" class="form-control form-control-lg" id="s2">
						<option value="1" ${(adminVO.isEmp=='1') ? 'selected' : ''}>在職
						<option value="0" ${(adminVO.isEmp=='0') ? 'selected' : ''}>離職
				</select></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> 
		<input type="hidden" name="adminID" value="<%=adminVO.getAdminID()%>"> 
		<input type="submit" value="送出修改" id="btn1">
	</FORM>
</body>

</html>