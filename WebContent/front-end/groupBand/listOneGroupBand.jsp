<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.groupBand.model.*"%>
    <%@ page import="java.util.*"%>
    
    <%
    GroupBandVO groupBandVO =(GroupBandVO) request.getAttribute("GroupBandVO");
%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>listOneGroupBand.jsp</title>
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
<body>


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


<tr>
	<td><%=groupBandVO.getGroupID()%></td>
	<td><%=groupBandVO.getContent()%></td>
	<td><%=groupBandVO.getLaunchTime()%></td>
	<td><%=groupBandVO.getIntroduction()%></td>
	<td><%=groupBandVO.getGroupStatus()%></td>
	<td><%=groupBandVO.getCurrenTnum()%></td>
	<td><%=groupBandVO.getUpperLimit()%></td>
	<td><%=groupBandVO.getLowerLimit()%></td>
	<td><%=groupBandVO.getGroupName()%></td>
	<td><%=groupBandVO.getGroupLeader()%></td>
	<td><%=groupBandVO.getStartLoc()%></td>
	<td><%=groupBandVO.getEndLoc()%></td>
	<td><%=groupBandVO.getPrivates()%></td>
	<td><%=groupBandVO.getPhoto()%></td>
	<td><%=groupBandVO.getGroupType()%></td>
	<td><%=groupBandVO.getTotalAmout()%></td>
	<td><%=groupBandVO.getStartTime()%></td>
	<td><%=groupBandVO.getRate()%></td>
	<td><%=groupBandVO.getNote()%></td>
<td>
<form action="/PiCar/GroupBand" method="POST" enctype="multipart/form-data"style="margin-bottom: 0px;">
	<input type="submit" value="修改">
	<input type="hidden" name="groupID"  value="${GroupBandVO.groupID}">
	<input type="hidden" name="action"	value="getOne_For_Update">
</FORM>
</td>
<td>
<FORM METHOD="post" ACTION="/PiCar/GroupBand" enctype="multipart/form-data" style="margin-bottom: 0px;">
	<input type="submit" value="刪除">
	<input type="hidden" name="groupID"  value="${GroupBandVO.groupID}">
	<input type="hidden" name="action"	value="delete"></FORM>
</td>
</tr>

</table>



</body>
</html>