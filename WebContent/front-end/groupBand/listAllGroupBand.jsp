<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.groupBand.model.*"%>
    <%@ page import="java.util.*"%>
    
    <%
    GroupBandService groupBandSvc = new GroupBandService();
    List<GroupBandVO> list = groupBandSvc.getAll();
    pageContext.setAttribute("list",list);
%>

    <link href="img/favicon.png" rel="icon">
<link href="img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700"
	rel="stylesheet">

<!-- Bootstrap CSS File -->
<link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Libraries CSS Files -->
<link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="lib/animate/animate.min.css" rel="stylesheet">

<!-- Main Stylesheet File -->
<link href="css/style.css" rel="stylesheet">
<link rel="stylesheet" type="text/css">
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>listAllGroupBand.jsp</title>
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
<body align="center">


<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<a href="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/insertGroupBand.jsp" >發起揪團</a>
<a href="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/SelectGroupBand.jsp" >查詢單筆</a>

<table  class="table">
<tr>
<th>揪團ID</th>
<th>留言板內容</th>
<th>發起時間</th>
<th>簡介</th>
<th>成團狀態</th>
<th>現在人數</th>
<th>上限人數</th>
<th>下限人數</th>
<th>團名</th>
<th>團長</th>
<th>上車地點</th>
<th>下車地點</th>
<th>隱私設定</th>
<th>照片</th>
<th>揪團類別</th>
<th>總金額	</th>
<th>上車時間</th>
<th>評價分數</th>
<th>備註</th>
<th>修改資料</th>
<th>刪除資料</th>
</tr>
<%@ include file="page1.file" %> 
<c:forEach var="GroupBandVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<tr>
	<td>${GroupBandVO.groupID}</td>
	<td>${GroupBandVO.content}</td>
	<td>${GroupBandVO.launchTime}</td>
	<td>${GroupBandVO.introduction}</td>
	<td>${GroupBandVO.groupStatus}</td>
	<td>${GroupBandVO.currenTnum}</td>
	<td>${GroupBandVO.upperLimit}</td>
	<td>${GroupBandVO.lowerLimit}</td>
	<td>${GroupBandVO.groupName}</td>
	<td>${GroupBandVO.groupLeader}</td>
	<td>${GroupBandVO.startLoc}</td>
	<td>${GroupBandVO.endLoc}</td>
	<td>${GroupBandVO.privates}</td>
	<td><img src="/PiCar/GroupBand?groupID=${GroupBandVO.groupID}" width="100px"   height="100px"></td>
	<td>${GroupBandVO.groupType}</td>
	<td>${GroupBandVO.totalAmout}</td>
	<td>${GroupBandVO.startTime}</td>
	<td>${GroupBandVO.rate}</td>
	<td>${GroupBandVO.note}</td>
<td>
<form action="<%=request.getServletContext().getContextPath()%>/GroupBand" method="POST" enctype="multipart/form-data"style="margin-bottom: 0px;">
	<input type="submit" value="修改">
	<input type="hidden" name="groupID"  value="${GroupBandVO.groupID}">
	<input type="hidden" name="action"	value="getOne_For_Update">
</FORM>
</td>
<td>
<FORM METHOD="post" ACTION="<%=request.getServletContext().getContextPath()%>/GroupBand" enctype="multipart/form-data" style="margin-bottom: 0px;">
	<input type="submit" value="刪除">
	<input type="hidden" name="groupID"  value="${GroupBandVO.groupID}">
	<input type="hidden" name="action"	value="delete"></FORM>
</td>
</tr>
</c:forEach>
</table>
<%@ include file="page2.file" %>


</body>
</html>