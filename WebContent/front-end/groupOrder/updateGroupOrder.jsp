<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
          <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.groupOrder.model.*"%>
   
     <%@ page import="java.util.*"%>
     <% GroupOrderVO groupOrderVO =(GroupOrderVO)  request.getAttribute("groupOrderVO"); %>
<% String state[] = {"未成團","已成團","流團"}; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>updateGroupOrder.jsp</title>
<style>
table#table-1 {
	width: 450px;
	background-color: #008888;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>
</head>
<body bgcolor=#FFEE99>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<table id="table-1">
	<tr>
			<td>
				<h3>揪團資料修改 -updateGroupOrder.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="ListAllGroupOrder.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>
<h3>修改揪團資料</h3>
	<form action="<%=request.getServletContext().getContextPath()%>/GroupOrder" method="POST" enctype="multipart/form-data" name="form1">
	<table>
	<tr>
	<td>司機ID</td>
	<td><input type="text" name="driverID" value="<%=(groupOrderVO == null) ? "" : groupOrderVO.getDriverID()%>"></td>
	</tr>
	<tr>
	<td>會員ID</td>
	<td><input type="text" name="memID" value="<%=(groupOrderVO == null) ? "" : groupOrderVO.getMemID()%>"></td>
	</tr>
	<tr>
	<td>狀態</td>
	<td><select name="state">
	<%for(int a=0; a<state.length;a++)
	{%>
	<option value="<%=a%>" <% if(a==groupOrderVO.getState()){out.print("selected='selected'");}%>><%=state[a]%></option>			
	<%}	
	%>
	</select>
	</td>
	</tr>
	</table>
	</form>
</body>
</html>