<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
	MemberDAO memberDAO = new MemberDAO();
	List<MemberVO> list = memberDAO.getAll();
	String lastMem = (list.get(list.size()-1).getMemID());
	pageContext.setAttribute("lastMem", lastMem);
	memberDAO.getGeneratedKeys();
	
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
 #table1 { 
	
 	background-color: ; 
 	margin-bottom: 20px; 
 	text-align: center; 
 	font-family: Microsoft JhengHei; 
 } 
table,tr,td{
width: 30%; 
}
</style>
<body bgcolor="#11e1e9">
<h3>listOneMemberByInsert.jsp</h3>
<a class="box" href=/PiCar/front-end/login/login.jsp> 請按此登入 </a>
	<h1 align="center">Hello!!<%=memberVO.getName()%>，這是您的個人資料，恭喜你成為會員，請於申請信箱取得帳號密碼</h1>
	<table align="center" border="1" id="table1">
		
		<tr>
			<td>會員姓名</td>
			<td>${memberVO.name}</td>
		</tr>
		<tr>	
			<td>會員生日</td>
			<td>${memberVO.birthday}</td>
		</tr>	
		<tr>
			<td>會員信箱</td>
			<td>${memberVO.email}</td>
		</tr>
<!-- 		<tr>	 -->
<!-- 			<td>password</td> -->
<%-- 			<td>${memberVO.password}</td> --%>
<!-- 		</tr> -->
		<tr>	
			<td>會員電話</td>
			<td>${memberVO.phone}</td>
		</tr>
		<tr>	
			<td>creditcard</td>
			<td>${memberVO.creditcard}</td>
		</tr>
		<tr>	
			<td>token</td>
			<td>${memberVO.token}</td>
		</tr>
		<tr>	
			<td>activityToken</td>
			<td>${memberVO.activityToken}</td>
		</tr>
		<tr>	
			<td>pet</td>
			<td><c:choose>
			  <c:when test="${memberVO.pet == '1'}"> 喜好寵物  </c:when>
			  <c:when test="${memberVO.pet == '0'}">  不喜好寵物  </c:when>			 
			</c:choose></td>
		</tr>
		<tr>
			<td>smoke</td>
			<td><c:choose>
			  <c:when test="${memberVO.smoke == '1'}">抽菸  </c:when>
			  <c:when test="${memberVO.smoke == '0'}">  不抽菸  </c:when>			 
			</c:choose></td>
		</tr>
		<tr>	
			<td>gender</td>
			<td><c:choose>
			  <c:when test="${memberVO.gender == '1'}">男生  </c:when>
			  <c:when test="${memberVO.gender == '0'}">女生  </c:when>			 
			</c:choose></td>
		</tr>	
		
		<tr>	
			<td>babySeat</td>
			<td><c:choose>
			  <c:when test="${memberVO.babySeat == '1'}">需要  </c:when>
			  <c:when test="${memberVO.babySeat == '0'}">不需要  </c:when>			 
			</c:choose></td>			
		</tr>
		<tr>	
			<td>verified</td>
			<td><c:choose>
			  <c:when test="${memberVO.verified == '1'}">已經驗證  </c:when>
			  <c:when test="${memberVO.verified == '0'}">尚未驗證  </c:when>			 
			</c:choose></td>
		</tr>
		<tr>
		<td>個人照片</td>
		<td><img src="http://localhost:8081/PiCar/front-end/member/member.do?memID=${lastMem}"  width='200' height="200"
		onerror="this.src='cat.jpg'"></td>
		</tr>
			
	</table>
	
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/member/member.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改個人資料"> -->
<%-- 			     <input type="hidden" name="memID"  value="${memberVO.memID}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
			
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="代幣儲值"> -->
<%-- 			     <input type="hidden" name="memID"  value="${memberVO.memID}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
			
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改個人資料"> -->
<%-- 			     <input type="hidden" name="memID"  value="${memberVO.memID}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
			

</body>
</html>