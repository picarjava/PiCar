<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.groupBand.model.*"%>
    <%@ page import="java.util.*"%>
    <%@ page import="com.member.model.*"%>
    <%
    GroupBandVO groupBandVO =(GroupBandVO) request.getAttribute("GroupBandVO");
    Object GroupLeader =request.getAttribute("GroupLeader");  
    List<MemberVO> testList =(List)request.getAttribute("testList"); 
%>

    
<!DOCTYPE html>

<html>
<head>
<link href="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/img/favicon.png" rel="icon">
<link href="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<!-- <link -->
<!-- 	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700" -->
<!-- 	rel="stylesheet"> -->

<!-- Bootstrap CSS File -->
<link href="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Libraries CSS Files -->
<link href="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/lib/animate/animate.min.css" rel="stylesheet">

<!-- Main Stylesheet File -->
<!-- <link href="css/style.css" rel="stylesheet"> -->
<!-- <link rel="stylesheet" type="text/css" -->
<%-- 	href="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/datetimepicker/jquery.datetimepicker.css" /> --%>
<%-- <script src="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/datetimepicker/jquery.js"></script> --%>
<%-- <script src="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/datetimepicker/jquery.datetimepicker.full.js"></script> --%>
   
<meta charset="UTF-8">
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
  div {
    color: #fff;
  }
  .p-2 {
   margin: auto;
      width: 200px;
    height: 300px;
  }
  .marrgin{
     width: 150px;
    height: 200px;
    color: #fff;
    background-color: #fff;
    margin: auto;

  }
  .tentcenter{
      text-align: center;
       font-family: Microsoft JhengHei;
        font-weight: bold;
      margin-top: 25px;
  }
  .mar{
      margin-left: 5px;
  }
  .color1{
      background-color: #008ccc!important;
  }
    .color0{
      background-color: #FFB7DD!important;
  }
</style>
<style>

  table {
   margin: auto;
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
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
<div class="row">
<c:forEach var="MemberVO" items="${testList}" >
<div class="d-inline p-2 bg-primary text-white color${MemberVO.gender}">
<div class="marrgin"><img src="/PiCar/GroupBand?MEM_ID=${MemberVO.memID}"></div>
<div class="tentcenter" id="gender"><h4 >${MemberVO.name}<img class="mar" src="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/img/${MemberVO.gender}.png"  width="20px"   height="25px" ></h4></div>
<div class="tentcenter"></div>
</div>

</c:forEach>
</div>
<table>
<tr>
<th>揪團ID</th>
<th>留言板內容</th>
<th>發起時間</th>
<th>簡介</th>
<th>揪團種類</th>
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


</tr>


<tr>
	<td><%=groupBandVO.getGroupID()%></td>
	<td><%=groupBandVO.getContent()%></td>
	<td><%=groupBandVO.getLaunchTime()%></td>
	<td><%=groupBandVO.getIntroduction()%></td>
	<td><%=groupBandVO.getGroupKind()%></td>
	<td><%=groupBandVO.getCurrenTnum()%></td>
	<td><%=groupBandVO.getUpperLimit()%></td>
	<td><%=groupBandVO.getLowerLimit()%></td>
	<td><%=groupBandVO.getGroupName()%></td>
	<td><%=groupBandVO.getGroupLeader()%></td>
	<td><%=groupBandVO.getStartLoc()%></td>
	<td><%=groupBandVO.getEndLoc()%></td>
	<td><%=groupBandVO.getPrivates()%></td>
	<td><img src="/PiCar/GroupBand?groupID=<%=groupBandVO.getGroupID()%>" width="100px"   height="100px"></td>
	<td><%=groupBandVO.getGroupType()%></td>
	<td><%=groupBandVO.getTotalAmout()%></td>
	<td><%=groupBandVO.getStartTime()%></td>
	<td><%=groupBandVO.getRate()%></td>
	<td><%=groupBandVO.getNote()%></td>

</tr>
</table>
<%--判斷是否為團長 GroupBandServlet.java配合上689行--%>
<%if("true".equals(GroupLeader)){ %>
<h5>修改資料</h5>
<form action="<%=request.getServletContext().getContextPath()%>/GroupBand" method="POST" enctype="multipart/form-data"style="margin-bottom: 0px;">
	<input type="submit" value="修改">
	<input type="hidden" name="groupID"  value="${GroupBandVO.groupID}">
	<input type="hidden" name="action"	value="getOne_For_Update">
</FORM>
<h5>刪除資料</h5>
<FORM METHOD="post" ACTION="<%=request.getServletContext().getContextPath()%>/GroupBand" enctype="multipart/form-data" style="margin-bottom: 0px;">
	<input type="submit" value="刪除">
	<input type="hidden" name="groupID"  value="${GroupBandVO.groupID}">
	<input type="hidden" name="action"	value="delete"></FORM>
	<%}%>
<h5>加入揪團</h5>
<FORM METHOD="post" ACTION="<%=request.getServletContext().getContextPath()%>/GroupBand" enctype="multipart/form-data" style="margin-bottom: 0px;">
	<input type="submit" value="加入">
	<input type="hidden" name="groupID"  value="${GroupBandVO.groupID}">
	<input type="hidden" name="startTime"  value="<%=groupBandVO.getStartTime()%>">
	<input type="hidden" name="memIDs" value="${memberVO.memID }" /> 
	<input type="hidden" name="groupKind"  value="<%=groupBandVO.getGroupKind()%>">
	<input type="hidden" name="action"	value="GroupAdd">
	
	</FORM>	








</body>
</html>