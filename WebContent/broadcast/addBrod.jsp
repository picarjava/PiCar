<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.broadcast.model.*"%>

<%
String memID = "M001";//接session
BroadcastVO brodVO = (BroadcastVO) request.getAttribute("brodVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>推播資料新增 - addBrod.jsp</title>

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
		 <h3>推播資料新增 - addBrod.jsp</h3></td><td>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="broadcast.do" name="form1">
<table>
	<tr>
		<td>推播編號:</td>
		<td><input type="TEXT" name="msgID" size="45" 
			 value="<%= (brodVO==null)? "MSG" : brodVO.getMsgID()%>"disabled="disabled" /></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="memID" size="45"
			 value="<%= memID %>" disabled="disabled"/></td>
	</tr>
	<tr>
		<td>訊息內容:</td>
		<td><input type="TEXT" name="message" size="45"
			 value="<%= (brodVO==null)? "自由發揮" : brodVO.getMessage()%>" /></td>
	</tr>
	<tr>
		<td>是否已讀:</td>
		<td><input type="TEXT" name="confirm" size="45"
			 value="<%= (brodVO==null)? "0" : brodVO.getConfirmed()%>" /></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>


</html>