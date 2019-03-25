<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.storeRecord.model.*"
	import="java.util.List"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<%
	//給小編的
	//以下為了用迴圈將儲值紀錄列出
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
	StoreRecordService storeRecordSvc = new StoreRecordService();	
	
	List<StoreRecordVO> list = storeRecordSvc.getMemStoreRecordPos(memberVO.getMemID());
	pageContext.setAttribute("list", list);

// 	List<StoreRecordVO> list = (List) request.getAttribute("list");
// 	StoreRecordVO storeRecordVO = (StoreRecordVO) request.getAttribute("storeRecordVO");
	//以下為了取得姓名
// 	MemberService memberSvc = new MemberService();
// 	MemberVO memberVO = memberSvc.getOneMember(storeRecordVO.getMemID());
	//以下為了得到加總金額
// 	Integer sumCount = (Integer) request.getAttribute("sumCount");
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
table{ 
	width:600px;
	border:1px solid #888888;  
 } 
td{
	border:1px solid #333333;
	padding:10px;
  	font-family: arial; 
  	font-size:15px;
  	width:200px;
  	align="center"
}
tr{
	align="center"	
}
#tr{
	background-color:#7788aa;
	color:#ffffff;  	
}
tr:nth-child(even){
	background-color:e8e8e8;	
}
</style>
<body bgcolor="">
<!-- 	<h3>listOneStoreRecordMember.jsp</h3> -->
<!-- 	<a href="select_page.jsp">回主頁面</a> -->
	<h1 align="center">
		${memberVO.memID}
		
		<%=memberVO.getName()%>
		你好，以下為您的儲值紀錄
	</h1>
	<div class="page1" align="center"><%@ include file="page1.file"%></div>
	<table align="center" border="1" id="table1">
		<tr id="tr">
			<td>儲值ID</td>
			<td>儲值金額</td>
			<td>儲值時間</td>
		</tr>
		<c:forEach var="storeRecordVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>

			<td>${storeRecordVO.storeID}</td>
			<td>${storeRecordVO.amount}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"  	value="${storeRecordVO.saveDate}" /></td>
		</tr>
		</c:forEach>
<!-- 		<tr> -->
<!-- 			<td colspan="1">總儲值金額</td> -->
<%-- 			<td>${memberVO.token}</td> --%>
			<%-- 			<c:forEach var="storeRecordVO" items="${list}" begin="0" end="0" --%>
			<%-- 				step="1"> --%>
			<%-- 				<td>${storeRecordDAO.getSumAmount(storeRecordVO.memID)}</td> --%>
			<%-- 			</c:forEach> --%>
<!-- 			<td></td> -->
<!-- 		</tr> -->


	</table>
		<div align="center"><%@ include file="page2.file"%></div>


</body>
</html>