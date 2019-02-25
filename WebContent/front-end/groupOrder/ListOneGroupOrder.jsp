<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.groupOrder.model.*"%>
   
     <%@ page import="java.util.*"%>
     <% GroupOrderVO groupOrderVO =(GroupOrderVO)  request.getAttribute("groupOrderVO"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listOneGroupOrder.jsp</title>
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
   table {
	width: 800px;
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
  th{
  background-color: #00BBFF;
  }
  
</style>
</head>


<body  bgcolor=#FFEE99>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
<tr>
<th>訂單ID</th>
<th>司機ID</th>
<th>會員ID</th>
<th>狀態碼	</th>
<th>總金額	</th>
<th>建立時間</th>
<th>上車時間</th>
<th>下車時間</th>
<th>上車經度</th>
<th>上車緯度</th>
<th>下車經度</th>
<th>下車緯度</th>
<th>訂單種類</th>
<th>評價分數</th>
<th>備註</th>
</tr>
<tr>
<td><%=groupOrderVO.getGorderID()%></td>
<td><%=groupOrderVO.getDriverID()%></td>
<td><%=groupOrderVO.getMemID()%></td>
<td><%=groupOrderVO.getState()%></td>
<td><%=groupOrderVO.getTotalAmout()%></td>
<td><%=groupOrderVO.getLaunchTime()%></td>
<td><%=groupOrderVO.getStartLng()%></td>
<td><%=groupOrderVO.getEndTime()%></td>
<td><%=groupOrderVO.getStartLng()%></td>
<td><%=groupOrderVO.getStartLat()%></td>
<td><%=groupOrderVO.getEndLng()%></td>
<td><%=groupOrderVO.getEndLat()%></td>
<td><%=groupOrderVO.getOrderType()%></td>
<td><%=groupOrderVO.getRate()%></td>
<td><%=groupOrderVO.getNote()%></td>
</tr>

<form action="/PiCar/GroupOrder" method="POST" style="margin-bottom: 0px;">
	<input type="submit" value="修改">
	<input type="hidden" name="gorderID()"  value="${GroupOrderVO.gorderID}">
	<input type="hidden" name="action"	value="getOne_For_Update">
</FORM>
</td>
<td>
<FORM METHOD="post" ACTION="/PiCar/GroupOrder" style="margin-bottom: 0px;">
	<input type="submit" value="刪除">
	<input type="hidden" name="gorderID()"  value="${GroupOrderVO.gorderID}">
	<input type="hidden" name="action"	value="delete"></FORM>
</td>
</tr>

</table>






</body>
</html>