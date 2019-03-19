<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.admin.model.*"%>

<%
	AdminVO adminVO = (AdminVO) request.getAttribute("adminVO");
%>
<script src="randompsw.js"></script>
<html>
<head>
<title>PICAR BACK-END</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"
	name="viewport" />
<!--     Fonts and icons     -->
<link rel="stylesheet" type="text/css"
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
<!-- Material Kit CSS -->
<link href="assets/css/material-dashboard.css" rel="stylesheet" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>管理員資料新增 - addAdmin.jsp</title>

<style>
<
style>table, tr, td, th {
	border: 1px solid #aaa;
	text-align: center;
	padding: 5px;
	text-align: center;
	font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro',
		'Noto Sans CJK SC', monospace;
}

table {
	width: 100%;
	width: 800px;
	margin-top: 1px;
	margin-bottom: 1px;
}

#error {
	margin-left: 20px;
}

body {
	font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro',
		'Noto Sans CJK SC', monospace;
	margin-left: 150px;
}

h4 {
	font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro',
		'Noto Sans CJK SC', monospace;
}

h5 {
	margin-left: 20%;
	font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro',
		'Noto Sans CJK SC', monospace;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>管理員資料新增 - addAddmin.jsp</h3>
			</td>
			<td>
				<h5>
					<a href="admin_select_page.jsp">
					<img src="images/arrow.png" width="40" height="40" border="0">回首頁</a>
				</h5>
			</td>
		</tr>
	</table>

	<h4>資料新增</h4>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/admin/admin.do" name="form1">
		<table>
			<tr>
				<td>管理員姓名</td>
				<td><input type="TEXT" name="adminName" size="45"
					value="<%= (adminVO==null)? "Kev" : adminVO.getAdminName()%>" /></td>
			</tr>
			
			<tr>
				<td>電子信箱</td>
				<td><input type="TEXT" name="email" size="45"
					value="<%= (adminVO==null)? "jyunliou120@gmail.com" : adminVO.getEmail()%>" /></td>
			</tr>
			<tr>
				<td><input type=hidden name="password" size="45"
					value="<%= (adminVO==null)? "" : adminVO.getPassword()%>" /></td>
			</tr>
			<tr>
			
				<td><input type="hidden" name="isEmp" size="45"
					value="<%= (adminVO==null)? "1" : adminVO.getIsEmp()%>" /></td>
			</tr>


		</table>
		<br> 
		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="送出新增">
	</FORM>
</body>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</html>