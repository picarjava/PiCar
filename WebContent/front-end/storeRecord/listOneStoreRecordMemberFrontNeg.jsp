<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.storeRecord.model.*"
	import="java.util.List"%>
<%@ page import="com.member.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<%	
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
	StoreRecordService storeRecordSvc = new StoreRecordService();		
	List<StoreRecordVO> list = storeRecordSvc.getMemStoreRecordNeg(memberVO.getMemID());
	pageContext.setAttribute("list", list);	
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Andia - Responsive Agency Template</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,400">
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Droid+Sans">
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Lobster">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/HomeMember/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/HomeMember/assets/prettyPhoto/css/prettyPhoto.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/HomeMember/assets/css/flexslider.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/HomeMember/assets/css/font-awesome.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/HomeMember/assets/css/style.css">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/ico/apple-touch-icon-57-precomposed.png">
</head>
<style>
#table1{ 
	width:600px;
	border:1px solid #888888;
	
  
 } 
td{
	border:1px solid #333333;
	border-bottom:1px solid #888888;
	padding:10px;
  	font-family: arial; 
  	font-size:15px;
}
#tr{
	background-color:#7788aa;
	color:#ffffff;
	 
  	
}
tr:nth-child(even){
	background-color:e8e8e8;
	
}
</style>
<body bgcolor="#11e1e9">
<!-- 	<h3>listOneStoreRecordMemberNeg.jsp</h3> -->
<!-- 	<a class="box" href=/PiCar/regna-master/homeindex.jsp> 請按此回首頁 </a> -->
	<h3 align="center"> <%=memberVO.getName() %>	你好，以下為您的扣款紀錄</h3>
	<div class="page1" align="center"><%@ include file="page1.file"%></div>
	<table  align="center" id="table1">
		<tr id="tr">
			<td id="td">訂單ID</td>			
			<td id="td">扣款金額</td>
			<td id="td">扣款時間</td>
		</tr>
		<c:forEach var="storeRecordVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr>

				<td>${storeRecordVO.orderID}</td>				
				<td>${-storeRecordVO.amount}</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${storeRecordVO.saveDate}" /></td>				
			</tr>
		</c:forEach>
<!-- 		<tr> -->
<!-- 			<td colspan="1">總儲值金額</td>	 -->

<%-- 			<td><%=memberVO.getToken() %></td> --%>
<!-- 		</tr> -->
			

	</table>
			<div align="center"><%@ include file="page2.file"%></div>
<%-- 			<h3 align="center">目前餘額:<%=memberVO.getToken() %></h3> --%>

</body>
</html>