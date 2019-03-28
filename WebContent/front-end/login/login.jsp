<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ page import="com.member.model.*"%>
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
	response.sendRedirect("/PiCar/front-end/HomeMember/index.jsp");
}
%>


<title>Insert title here</title>
</head>
<body>
	<form method="post" action="loginHandler.do" class="box" name="form1">
		<h1>Login</h1>
		
				<input type="text" name="account"  placeholder="Usernsme" value="">
			
				密碼
				<input type="password" name="password" placeholder="Password" value="">

			
				<input type="submit" value="Login">
		
		
		
	<img src="PIC/M001.png"	height="20" width="20" onClick="idwrite1(this)">
	<img src="PIC/M002.png"	height="20" width="20" onClick="idwrite2(this)">	
	<img src="PIC/M003.jpg"	height="20" width="20" onClick="idwrite3(this)">	
	<img src="PIC/M004.jpg"	height="20" width="20" onClick="idwrite4(this)">	
	<img src="PIC/M005.png"	height="20" width="20" onClick="idwrite5(this)">	
	<img src="PIC/M006.jpg"	height="20" width="20" onClick="idwrite6(this)">		
	<img src="PIC/M007.jpg"	height="20" width="20" onClick="idwrite7(this)">	
	<img src="PIC/M008.png"	height="20" width="20" onClick="idwrite8(this)">	
	<img src="PIC/M009.png"	height="20" width="20" onClick="idwrite9(this)">	
	<img src="PIC/M010.png"	height="20" width="20" onClick="idwrite10(this)">	
	<img src="PIC/M011.png"	height="20" width="20" onClick="idwrite11(this)">	
	</form>
	
	<a class="box1" href=/PiCar/regna-master/homeindex.jsp> 請按此回首頁 </a>

</body>

<script>
	function idwrite1(name) {
		form1.account.value = "M001"
		form1.password.value = "123456"
	}
	function idwrite1(name) {
		form1.account.value = "M001"
		form1.password.value = "123456"
	}
	function idwrite2(name) {
		form1.account.value = "M002"
		form1.password.value = "123456"
	}
	function idwrite3(name) {
		form1.account.value = "M003"
		form1.password.value = "123456"
	}
	function idwrite4(name) {
		form1.account.value = "M004"
		form1.password.value = "123456"
	}
	function idwrite5(name) {
		form1.account.value = "M005"
		form1.password.value = "123456"
	}
	function idwrite6(name) {
		form1.account.value = "M006"
		form1.password.value = "123456"
	}
	function idwrite7(name) {
		form1.account.value = "M007"
		form1.password.value = "123456"
	}
	function idwrite8(name) {
		form1.account.value = "M008"
		form1.password.value = "123456"
	}
	function idwrite9(name) {
		form1.account.value = "M009"
		form1.password.value = "123456"
	}
	function idwrite10(name) {
		form1.account.value = "M0010"
		form1.password.value = "123456"
	}
	function idwrite11(name) {
		form1.account.value = "M0011"
		form1.password.value = "123456"
	}
</script>
</html>