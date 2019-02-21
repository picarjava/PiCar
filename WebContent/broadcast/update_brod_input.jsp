<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.broadcast.model.*"%>

<%
BroadcastVO brodVO = (BroadcastVO) request.getAttribute("brodVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料修改 - update_emp_input.jsp</title>

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
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>員工資料修改 - update_emp_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="emp.do" name="form1">
<table>
	<tr>
		<td>推播編號:</td>
		<td><input type="TEXT" name="ename" size="45" 
			 value="<%= (brodVO==null)? "吳永志" : brodVO.getMsgID()%>" /></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="job" size="45"
			 value="<%= (brodVO==null)? "MANAGER" : brodVO.getMemID()%>" /></td>
	</tr>
	<tr>
		<td>訊息內容:</td>
		<td><input type="TEXT" name="sal" size="45"
			 value="<%= (brodVO==null)? "10000" : brodVO.getMessage()%>" /></td>
	</tr>
	<tr>
		<td>是否已讀:</td>
		<td><input type="TEXT" name="comm" size="45"
			 value="<%= (brodVO==null)? "100" : brodVO.getConfirmed()%>" /></td>
	</tr>



</table>
<br>
<input type="hidden" name="action" value="update"/>
<input type="submit" value="送出修改"></FORM>
</body>



</html>