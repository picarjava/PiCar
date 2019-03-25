<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.location.model.*"%>
<%
// 	此處為登入處理
//    String account = (String)session.getAttribute("account");                  // 從 session內取出 (key) account的值
    
//    使用濾器登入，以下程式碼可以省略------
//    if (account == null) {                                             // 如為 null, 代表此user未登入過 , 才做以下工作
//       session.setAttribute("location", request.getRequestURI());       //*工作1 : 同時記下目前位置 , 以便於login.html登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
//       response.sendRedirect(request.getContextPath()+"/front-end/login/login.html");   //*工作2 : 請該user去登入網頁(login.html) , 進行登入
//       return;
//     }
   
%>

<%
// 	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
	
// 		MemberService memberSvc = new MemberService();
// 		MemberVO memberVO = memberSvc.getOneMember(account);
		response.setHeader("Cache-Control","no-store"); //HTTP 1.1
		response.setHeader("Pragma","no-cache");        //HTTP 1.0
		response.setDateHeader ("Expires", 0);
// 		MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
		
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
#table1 {

background-color: ;
margin-bottom: 20px;
text-align: center;
font-family: Microsoft JhengHei;
}
/* table,tr,td{
width: 30%;
} */
table{
width:650px;
border:1px solid #888888;
}
td{
border-bottom:1px solid #888888;
padding:10px;
font-family: arial;
font-size:15px;
}
td:nth-child(1){
width:80px;
text-align:center;
}
td:nth-child(2){
width:100px;
}
td:nth-child(3){
width:250px;
}
tr{
/* background-color:#7788aa; */
color:#888888;
height: 20px;
}
tr:nth-child(even){
background-color:e8e8e8;
}
.box1 input[type="submit"]{
	border:0;
	background:none;
	display:block;
	margin :20px auto;
	text-align:center;
	border:2px solid #2ecc71;
	padding:14px 10px;
	
	outline:none;
	color:black;
	border-radius:24px;
	transition:2s;
	cursor:pointer;
}
.box1 input[type="submit"]:hover{
	background:#2ecc71;
}
#table2{
margin-top:150px;
margin-left:50px;
top:50%;
}
</style>
<body bgcolor="#11e1e9">
	<jsp:include page="/front-end/HomeMember/HeadMember.jsp" />
	<jsp:include page="/front-end/HomeMember/HeadMemberSetting.jsp" />
	<!-- <h3>listOneMemberByUpdate.jsp</h3> -->
	<!-- <a class="box" href=/PiCar/regna-master/homeindex.jsp> 請按此回首頁 </a><br> -->
	<%-- <a class="box" href=<%=request.getContextPath()%>/front-end/HomeMember/index.jsp> 請按此回會員首頁 </a> --%>
	<h3 align="center">Hello!!${memberVO.name}，這是你的個人資料</h3>
	<table align="center" border="1" id="table1"  >
		
<%-- 		<!-- <%=memberVO.getMemID()%> --> --%>
		<tr>
			<td>會員帳號</td>
			<td colspan="2">${memberVO.memID}</td>
			
		</tr>
		<tr>
			<td>會員姓名</td>
			<td>${memberVO.name}</td>
			<td rowspan="11">
			個人照片<br>
			<img src="<%=request.getServletContext().getContextPath()%>/front-end/member/member.do?memID=${memberVO.memID}"  width="200" height="200">
			</td>
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
			<td>信用卡號</td>
			<td>${memberVO.creditcard}</td>
		</tr>
		<tr>
			<td>一般代幣</td>
			<td>${memberVO.token}</td>
		</tr>
<!-- 		<tr> -->
<!-- 			<td>活動代幣</td> -->
<%-- 			<td>${memberVO.activityToken}</td> --%>
<!-- 		</tr> -->
		<tr>
			<td>寵物喜好</td>
			<td><c:choose>
				<c:when test="${memberVO.pet == '1'}"> 喜歡  </c:when>
				<c:when test="${memberVO.pet == '0'}">  不喜歡  </c:when>
			</c:choose></td>
		</tr>
		<tr>
			<td>抽菸設定</td>
			<td><c:choose>
				<c:when test="${memberVO.smoke == '1'}">抽菸  </c:when>
				<c:when test="${memberVO.smoke == '0'}">  不抽菸  </c:when>
			</c:choose></td>
		</tr>
		<tr>
			<td>性     別</td>
			<td><c:choose>
				<c:when test="${memberVO.gender == '1'}">男生  </c:when>
				<c:when test="${memberVO.gender == '0'}">女生  </c:when>
			</c:choose></td>
		</tr>
		
		<tr>
			<td>嬰兒座椅</td>
			<td><c:choose>
				<c:when test="${memberVO.babySeat == '1'}">需要  </c:when>
				<c:when test="${memberVO.babySeat == '0'}">不需要  </c:when>
			</c:choose></td>
		</tr>
		<tr>
			<td>驗證狀態</td>
			<td><c:choose>
				<c:when test="${memberVO.verified == '1'}">已經驗證  </c:when>
				<c:when test="${memberVO.verified == '0'}">尚未驗證  </c:when>
			</c:choose></td>
		</tr>
<!-- 		<tr> -->
<!-- 			<td>個人照片</td> -->
<%-- 			<td><img src="<%=request.getServletContext().getContextPath()%>/front-end/member/member.do?memID=${memberVO.memID}"  width='200' height="200" --%>
<!-- 			></td> -->
<!-- 		</tr> -->
	</table>
	
	<table>
		<tr>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/member/member.do" style="margin-bottom: 0px;" class="box1">
					<input type="submit" value="修改個人資料" >
					<input type="hidden" name="memID"  value="${memberVO.memID}">
					<input type="hidden" name="action"	value="getOne_For_Update"></FORM>
				</td>
				
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/storeRecord/storeRecord.do" style="margin-bottom: 0px;" class="box1">
						<input type="submit" value="代幣儲值">
						<input type="hidden" name="memID"  value="${memberVO.memID}">
						<input type="hidden" name="action"	value="addToken"></FORM>
					</td>
					
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/member/member.do" style="margin-bottom: 0px;" class="box1">
							<input type="submit" value="喜好設定">
							<input type="hidden" name="memID"  value="${memberVO.memID}">
							<input type="hidden" name="action"	value="getOne_For_Update_HOBBY">
						</FORM>
					</td>
					
					<td>
						<form action="<%=application.getContextPath()%>/location" method="POST" class="box1">
							<input type="hidden" name="memID" value="${locationVO.memID}"/>
							<input type="hidden" name="location" value="${locationVO.location}"/>
							<input type="hidden" name="action" value="getUpdateLocation"/>
							<input type="submit" value="常用地點"/>
						</form>
					</td>
					
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/member/memberSelf.do" style="margin-bottom: 0px;" class="box1">
							<input type="submit" value="修改密碼">
							<input type="hidden" name="memID"  value="${memberVO.memID}">
							<input type="hidden" name="action"	value="modify_password"></FORM>
						</td>
						
<!-- 						<td> -->
<!-- 							<form method="post" action="logoutHandler.do"> -->
<!-- 								<input type="submit" value="登出"> -->
<!-- 								<input type="hidden" name="logout"	value="logout"> -->
<!-- 							</form> -->
<!-- 						</td> -->
					</tr>
				</table>
			</body>
</html>