<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.admin.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

					<!-- 這支不用session! -->
<%
	AdminVO adminVO = (AdminVO)request.getAttribute("adminVO"); 
%>

<html>
<head>
<title>管理員資料 - listAdmin.jsp</title>
<jsp:include page="/back-end/kidHead.jsp" />
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
<body>
    <div class="wrapper ">
        <jsp:include page="/back-end/kidBodyLeft.jsp" />
        <div class="main-panel">
            <jsp:include page="/back-end/kidNavbar.jsp" />
            <div class="content">
                <div class="container-fluid">
                    <!-- your content here -->
                    <div class="container-fluid">
                        <div class="col-9">
						<table>
							<tr>
								<th>管理員編號</th>
								<th>管理員姓名</th>
								<th>密碼</th>
								<th>在職狀態</th>
							</tr>
							<tr>
								<td><%=adminVO.getAdminID()%></td>
								<td><%=adminVO.getAdminName()%></td>
								<td><%=adminVO.getPassword()%></td>
								<td><%=adminVO.getIsEmp()%></td>
							</tr>
						</table>
                   </div>
                </div>
                <jsp:include page="/back-end/kidFooter.jsp" />
            </div>
        </div>
    </div>    
</div>
</body>
</html>