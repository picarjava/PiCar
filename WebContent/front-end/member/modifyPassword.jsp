<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.member.model.*" %>
<!DOCTYPE html>
<html>
<head>
<script src="jquery-1.12.4.min.js" ></script>
<meta charset="UTF-8">
<style>
body{
	margin:0;
	padding:0;
	font-family:sans-serif;
	background:#EEEFED;
}
.box{
	width:300px;
	padding:40px;
	position:absolute;
	top:50%;
	left:50%;
	transform:translate(-50%,-50%);
	background:#191919;
	text-align:center;
}
.box h1{
	color:white;
	text-transform:uppercase;
	font-weight:500;
	
}
.box input[type="text"],.box input[type="password"]{
	border:0;
	background:none;
	display:block;
	margin :20px auto;
	text-align:center;
	border:2px solid #3498db;
	padding:14px 10px;
	width:200px;
	outline:none;
	color:white;
	border-radius:24px;
	transition:2s;
}
.box input[type="text"]:focus,.box input[type="password"]:focus{
	width:280px;
	border-color: #2ecc71;
}
.box input[type="submit"]{
	border:0;
	background:none;
	display:block;
	margin :20px auto;
	text-align:center;
	border:2px solid #2ecc71;
	padding:14px 10px;
	
	outline:none;
	color:white;
	border-radius:24px;
	transition:2s;
	cursor:pointer;
}
.box input[type="submit"]:hover{
	background:#2ecc71;
}
.box1{
	width:100px;
	padding:40px;
	position:absolute;
	top:10%;
	left:10%;
	transform:translate(-50%,-50%);
	
	text-align:center;
	font-family:Microsoft JhengHei;
	border:0;
	background:none;
	display:block;
	margin :20px auto;
	text-align:center;
	border:2px solid #2ecc71;
	padding:14px 10px;
	
	outline:none;
	color:white;
	border-radius:24px;
	transition:2s;
	cursor:pointer;
}
.box1:hover{
	background:#2ecc71;
}
#errmsg{
	color:#2ecc71;
}
#men img{
width:200px;
height:200px;
text-align:center;

border-radius:100px;
margin:0 auto;
margain-top:-150px;

}

</style>

<% 
// response.setHeader("Cache-Control","no-store"); //HTTP 1.1
// response.setHeader("Pragma","no-cache");        //HTTP 1.0
// response.setDateHeader ("Expires", 0);

MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");

if (memberVO == null){
	response.sendRedirect("/PiCar/regna-master/homeindex.jsp");
}
%>


<title>Insert title here</title>
</head>
<body>

<div id="errmsg">
	<c:if test="${not empty errorMsgs}">
	<ul>	
		<c:forEach var="message" items="${errorMsgs}">
			<li>
				${message}
			</li>
		</c:forEach>	
	</ul>
	</c:if>
	</div>
	<form method="post" action="<%=request.getContextPath()%>/front-end/member/memberSelf.do" class="box" name="form1">
		<h1>修改密碼</h1>
				<div id="men"><img src="<%=request.getServletContext().getContextPath()%>/front-end/member/member.do?memID=${memberVO.memID}"  onerror="this.src='cat.jpg'"></div>
				<input type="password" name="password1"  placeholder="Password" value="">
			
				
				<input type="password" name="password2" placeholder="Password" value="">

				<input type="hidden" name="action"	value="modify_password2">
				<input type="submit" value="確認">
		        <img src="cat.jpg"	height="20" width="20" onClick="idwrite(this)">
		
		
		
	</form>
	<a class="box1" href=/PiCar/front-end/HomeMember/index.jsp> 請按此回首頁 </a>

</body>

<script >

	function idwrite(name) {
		form1.password1.value ="123456"
		form1.password2.value ="123456"
	}

	</script>

</html>