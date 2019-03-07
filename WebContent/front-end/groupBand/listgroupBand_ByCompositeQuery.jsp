<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  	  <%@ page import="java.util.*"%>
  	 
      <%@ page import="com.groupMem.model.*"%>

    <%@ page import="com.groupBand.model.*"%>
    
    <jsp:useBean id="listgroupBand_ByCompositeQuery" scope="request" type="java.util.List<GroupBandVO>" /> 
<%--     <jsp:useBean id="groupOrderService" scope="page" class="com.groupOrder.model.GroupOrderService" /> --%>
<% String groupKind[] = {"揪團","長期揪團"}; %>
<%request.setAttribute("groupKind", groupKind);%>
<!DOCTYPE html>
<html>
<head>
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
</style>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="page1_ByCompositeQuery.file" %>
<c:forEach var="GroupBandVO" items="${listgroupBand_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

<table>
	<tr>
<th>發起時間</th><th>${GroupBandVO.launchTime}</th>
<th>簡介</th><th>${GroupBandVO.introduction}</th>
<th>現在人數</th><th>${GroupBandVO.currenTnum}</th>
<th>上限人數</th><th>${GroupBandVO.upperLimit}</th>
	</tr>
	<tr>
<th>下限人數</th><th>${GroupBandVO.lowerLimit}</th>
<th>團名</th><th>${GroupBandVO.groupName}</th>
<th>團長</th><th>${GroupBandVO.groupLeader}</th>
<th>上車地點</th><th>${GroupBandVO.startLoc}</th>
</tr>
	<tr>
<th>下車地點</th><th>${GroupBandVO.endLoc}</th>
<th>隱私設定</th><th>${GroupBandVO.privates}</th>
<th>照片</th><th><img src="/PiCar/GroupBand?groupID=${GroupBandVO.groupID}" width="100px"   height="100px"></th>
<th>揪團類別</th><th>${GroupBandVO.groupType}</th>
</tr>
	<tr>
<th>上車時間</th><th>${GroupBandVO.startTime}</th>



<th>揪團種類</th><c:forEach var="mypurstatus" items="${groupKind}" varStatus="s">
		 <c:choose>
		 <c:when test="${GroupBandVO.groupKind == s.index}">
		 <th>${mypurstatus}</th>
		 </c:when>
		 </c:choose>
		 </c:forEach>
<th><FORM METHOD="post" ACTION="<%=request.getServletContext().getContextPath()%>/GroupBand" enctype="multipart/form-data" style="margin-bottom: 0px;">
	<input type="submit" value="進入揪團">
	<input type="hidden" name="groupLeader"  value="${GroupBandVO.groupLeader}">
	<input type="hidden" name="startTime"  value="${GroupBandVO.startTime}">
	<input type="hidden" name="groupID"  value="${GroupBandVO.groupID}">
	<input type="hidden" name="memIDs" value="${memberVO.memID}" /> 
	<input type="hidden" name="action"	value="GroupJoin"></FORM>
</th>
</tr>	
</table>
</c:forEach>
	<%@ include file="page2_ByCompositeQuery.file" %>
</body>
</html>