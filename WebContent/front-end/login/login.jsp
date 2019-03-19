<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ page import="com.member.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<style>
body{
	margin:0;
	padding:0;
	font-family:sans-serif;
	background:black;
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
</style>

<% 
response.setHeader("Cache-Control","no-store"); //HTTP 1.1
response.setHeader("Pragma","no-cache");        //HTTP 1.0
response.setDateHeader ("Expires", 0);

MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");

if (memberVO != null){
	response.sendRedirect("/PiCar/regna-master/homeindex.jsp");
}
%>


<title>Insert title here</title>
</head>
<body>
	<form method="post" action="loginHandler.do" class="box">
		<h1>Login</h1>
		
				<input type="text" name="account"  placeholder="Usernsme" value="">
			
				密碼
				<input type="password" name="password" placeholder="Password" value="">

			
				<input type="submit" value="Login">
		
		
		
		
	</form>
	<a class="box1" href=/PiCar/regna-master/homeindex.jsp> 請按此回首頁 </a>

</body>
</html>