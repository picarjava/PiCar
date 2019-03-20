<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.groupBand.model.*"%>
    <%@ page import="java.util.*"%>
     <%@ page import="com.member.model.*"%>
     <% String groupKind[] = {"揪團","長期揪團"}; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>SelectGroupBand.jsp</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<meta charset="BIG5">
</head>

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
	margin-top: 15px;
	margin-bottom: 15px;
  }
  
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
      height: 50px;
      width: 50px;
    padding: 5px;
    text-align: center;
  }
</style>
<body bgcolor='white'>


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
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getServletContext().getContextPath()%>/GroupBand"  enctype="multipart/form-data">
        <input type="radio" name="GROUP_KIND" value="5" checked
					onclick="groupif(this.value)">揪團
				<input type="radio" name="GROUP_KIND" value="6"
					onclick="groupif(this.value)">長期揪團<br>
        
        <b>團名</b>
         <input  type="TEXT" name="GROUP_NAME"><br><br>
         <b>上車地點</b>
         	  <select name="START_LOC">
         	  <option value="">請選擇</option>
			<option value="台北">台北市</option>
			<option value="桃園">桃園市</option>
			</select><br><br>
			<b>上車日期</b>
			<input name="START_TIME" id="start_date" type="text"
					 onchange="datestamps();" size="15"><br><br>
        <b>揪團類別</b>
        <select name="GROUP_TYPE">
         <option value="">請選擇</option>
			<option value="演唱會">演唱會</option>
			<option value="旅遊">旅遊</option>
			<option value="美食">美食</option>
			<option value="運動團">運動團</option>
			<option value="展覽">展覽</option>
			<option value="遊樂園">遊樂園</option>
			</select><br><br>
       
 
        <input type="hidden" name="action" value="listgroupBand_ByCompositeQuery">
        <input type="submit" value="送出">
        <input class="buttonS" type="reset" value="清除" />
    </FORM>
  </li>
</ul>

<%if(request.getAttribute("listgroupBand_ByCompositeQuery")!=null){%>
<jsp:include page="listgroupBand_ByCompositeQuery.jsp" />
<%}
else{
%>
<%GroupBandService groupBandService =new GroupBandService();
List<GroupBandVO> list = new ArrayList<GroupBandVO>();
list = groupBandService.getAllStartTime();
out.println("<h6>目前日期最近的五筆揪團</h6>");
for(GroupBandVO lists :list){
	%>
	
	<table>
	<tr>
<th>發起時間</th><th><%=lists.getLaunchTime()%></th>
<th>簡介</th><th><%=lists.getIntroduction()%></th>
<th>現在人數</th><th><%=lists.getCurrenTnum()%></th>
<th>上限人數</th><th><%=lists.getUpperLimit()%></th>
	</tr>
	<tr>
<th>下限人數</th><th><%=lists.getLowerLimit()%></th>
<th>團名</th><th><%=lists.getGroupName()%></th>

<c:set var="id" scope="page" value="<%=lists.getGroupLeader()%>" />
			<%
	String id = (String) pageContext.getAttribute("id");
	MemberService memberService = new MemberService();
	MemberVO memberVOS =  memberService.getOneMember(id);
	pageContext.setAttribute("memberVOS", memberVOS);
%>

<th>團長</th><th>${memberVOS.name}</th>
<th>上車地點</th><th><%=lists.getStartLoc()%></th>
</tr>
	<tr>
<th>下車地點</th><th><%=lists.getEndLoc()%></th>
<th>照片</th><th><img src="/PiCar/GroupBand?groupID=<%=lists.getGroupID()%>" width="100px"   height="100px"></th>
<th>揪團類別</th><th><%=lists.getGroupType()%></th>
<th>上車時間</th><th><%=lists.getStartTime()%></th>
</tr>
	<tr>
<th>揪團種類</th>
		 <th><%=groupKind[lists.getGroupKind()-5]%></th>
		
<th><FORM METHOD="post" ACTION="<%=request.getServletContext().getContextPath()%>/GroupBand" enctype="multipart/form-data" style="margin-bottom: 0px;">
	<input type="submit" value="進入揪團">
	<input type="hidden" name="groupLeader"  value="<%=lists.getGroupLeader()%>">
	<input type="hidden" name="startTime"  value="<%=lists.getStartTime()%>">
	<input type="hidden" name="groupID"  value="<%=lists.getGroupID()%>">
	<input type="hidden" name="memIDs" value="${memberVO.memID}" /> 
	<input type="hidden" name="action"	value="GroupJoin"></FORM>
</th>
</tr>	
</table>

	<%
}
}%>
<script>
$('#start_date').datetimepicker(
		{
			format : 'Y-m-d',
			onShow : function() {			
			},
			step: 5,
			timepicker : false,
					value :'',
			minDate:           '+1970-01-05', // 去除今日(不含)之前
			maxDate:           '+1970-01-20'  // 去除今日(不含)之後
		});
</script>
</body>
</html>