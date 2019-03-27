<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
          <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.groupOrder.model.*"%>
   
     <%@ page import="java.util.*"%>
     <% GroupOrderVO groupOrderVO =(GroupOrderVO)  request.getAttribute("groupOrderVO"); %>
<% String state[] = {"未成團","已成團","流團"}; %>
<% String rate[] = {"0","1","2","3","4","5"}; %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<meta charset="utf-8">
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
	<form action="<%=request.getServletContext().getContextPath()%>/GroupOrder" method="POST">
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
	<tr>
		<td>總金額</td>
	<td><input type="text" name="totalAmout" value="<%=(groupOrderVO == null) ? "" : groupOrderVO.getTotalAmout()%>"></td>
	</tr>
	<tr>
	<td>評價</td>
	<td>
	<select name="rate">
	<%for(int a=0; a<rate.length;a++)
	{%>
	input chbox name="" value="rate[a]";
	
	<option value="<%=a%>" <% if(a==groupOrderVO.getRate()){out.print("selected='selected'");}%>><%=rate[a]%></option>			
	<%}	
	%>
	</select>
	</td>
	</tr>
	
		<tr>
		<td>備註</td>
		<td><input type="text" name="note" value="<%=(groupOrderVO == null) ? "" : groupOrderVO.getNote()%>"></td>
		</tr>

	</table>
	 
	  <input type="hidden" name="gorderID" value="<%=groupOrderVO.getGorderID()%>">
	  <input type="hidden" name="launchTime" value="<%=groupOrderVO.getLaunchTime()%>">
	  <input type="hidden" name="startTime" value="<%=groupOrderVO.getStartTime()%>">
	  <input type="hidden" name="endTime" value="<%=groupOrderVO.getEndTime()%>">
	  <input type="hidden" name="startLng" value="<%=groupOrderVO.getStartLng()%>">
	  <input type="hidden" name="startLat" value="<%=groupOrderVO.getStartLat()%>">
	  <input type="hidden" name="endLng" value="<%=groupOrderVO.getEndLng()%>">
	  <input type="hidden" name="endLat" value="<%=groupOrderVO.getEndLat()%>">
	  <input type="hidden" name="orderType" value="<%=groupOrderVO.getOrderType()%>">	  	
	  <input type="hidden" name="action" value="update" /> 
	<input type="submit" value="送出"/>
	<input type="reset" value="清除揪團" />
	</form>
</body>
</html>