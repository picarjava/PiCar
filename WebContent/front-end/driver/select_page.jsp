<%@ page language= "java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Broadcast: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;z
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>司機管理首頁 Broadcast: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Broadcast: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllBrod.jsp'>List</a> all Brod.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="broadcast.do" >
        <b>輸入推播訊息編號 (如MSG001):</b>
        <input type="text" name="msgID">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="service" scope="page" class="com.broadcast.model.BroadcastService" />
   
  <li>
     <FORM METHOD="post" ACTION="broadcast.do" >
       <b>選擇推播訊息編號:</b>
       <select size="1" name="msgID">
         <c:forEach var="brod" items="${service.all}" > 
          <option value="${brod.msgID}">${brod.msgID}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
   

</ul>


<h3>司機管理</h3>

<ul>
  <li><a href='addDriver.jsp'>新增</a> 一筆司機資料</li>
</ul>

</body>
</html>