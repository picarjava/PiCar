<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>SelectGroupBand.jsp</title>
</head>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
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
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>揪團</h3><h4>( MVC )</h4></td></tr>
</table>

<p>揪團</p>

<h3>揪團資料查詢:</h3>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllGroupBand.jsp'>List</a> AllGroupBand  <br><br></li>
 
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getServletContext().getContextPath()%>/GroupBand"  enctype="multipart/form-data">
        <b>輸入揪團編號 </b>
        <input type="text" name="groupID">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
</ul>

</body>
</html>