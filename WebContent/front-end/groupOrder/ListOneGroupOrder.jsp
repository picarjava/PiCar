<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.groupOrder.model.*"%>
     <%@ page import="java.util.*"%>
     <% GroupOrderVO groupOrderVO =(GroupOrderVO)  request.getAttribute("GroupOrderVO"); %>
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
<th></th>

</tr>



<tr></tr>
</table>



</body>
</html>