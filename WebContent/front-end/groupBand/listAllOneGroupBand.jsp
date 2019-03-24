<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.groupBand.model.*"%>
    <%@ page import="com.groupOrder.model.*"%>
    <%@ page import="java.util.*"%>
    <%@ page import="com.member.model.*"%>
 
     


    <%
    MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
    GroupOrderService groupOrderService =new GroupOrderService();
    List<GroupOrderVO> lists = groupOrderService.GET_one_groupid__state_men_id(memberVO.getMemID());
    GroupBandService groupBandSvc = new GroupBandService();
    List<GroupBandVO> list=new ArrayList<GroupBandVO>();

    for(GroupOrderVO listrer :lists){
    	
    GroupBandVO groupBandVO = groupBandSvc.getOneGroupBand(listrer.getGroupID());

    list.add(groupBandVO);
    }

    pageContext.setAttribute("list",list);
%>
<% String groupKind[] = {"揪團","長期揪團"}; %>
<!--     <link href="img/favicon.png" rel="icon"> -->
<!-- <link href="img/apple-touch-icon.png" rel="apple-touch-icon"> -->

<!-- <!-- Google Fonts --> -->
<!-- <link -->
<!-- 	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700" -->
<!-- 	rel="stylesheet"> -->

<!-- <!-- Bootstrap CSS File --> -->
<!-- <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet"> -->

<!-- <!-- Libraries CSS Files --> -->
<!-- <link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet"> -->
<!-- <link href="lib/animate/animate.min.css" rel="stylesheet"> -->

<!-- <!-- Main Stylesheet File --> -->
<!-- <link href="css/style.css" rel="stylesheet"> -->
<!-- <link rel="stylesheet" type="text/css"> -->
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>揪團管理</title>
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
#Floatingwindow {
    position: fixed;
    background-color: #555;
    width: 500px;
    height: 320px;
    z-index: 995;
    left: 40%;
    top: 30%;
    }
 #messageboards{
 	margin-top: 40px;
 }   
    
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin:auto;
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
  #topright{
    position: absolute;
    right: 0px;
  
  }
</style>
</head>

<body align="center">
<jsp:include page="/front-end/HomeMember/HeadMember.jsp" />
<jsp:include page="/front-end/HomeMember/HeadMemberGroup.jsp" />

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<div style="margin-top: 50px;">
<%@ include file="page1.file" %> 
<c:forEach var="GroupBandVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

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
<c:set var="id" scope="page" value="${GroupBandVO.groupLeader}" />
			<%
	String id = (String) pageContext.getAttribute("id");
	MemberService memberService = new MemberService();
	MemberVO memberVOs =  memberService.getOneMember(id);
	pageContext.setAttribute("memberVOs", memberVOs);
%>

<th>團長</th><th>${memberVOs.name}</th>
<th>上車地點</th><th>${GroupBandVO.startLoc}</th>
</tr>
	<tr>
<th>下車地點</th><th>${GroupBandVO.endLoc}</th>
<th>照片</th><th><img src="/PiCar/GroupBand?groupID=${GroupBandVO.groupID}" width="100px"   height="100px"></th>
<th>揪團類別</th><th>${GroupBandVO.groupType}</th>
<th>上車時間</th><th>${GroupBandVO.startTime}</th>
</tr>
	<tr>




<th>揪團種類</th><c:forEach var="mypurstatus" items="${groupKind}" varStatus="s">
		 <c:choose>
		 <c:when test="${GroupBandVO.groupKind-5 == s.index}">
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
</table>
<%@ include file="page2.file" %>


</body>
</html>