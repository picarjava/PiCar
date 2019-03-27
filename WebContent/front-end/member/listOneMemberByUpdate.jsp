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
<link rel="stylesheet" type="text/css"
	href="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script
	src="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.0/css/all.css"
	integrity="sha384-Mmxa0mLqhmOeaE8vgOSbKacftZcsNYDjQzuCOm6D02luYSzBG8vpaOykv9lFQ51Y"
	crossorigin="anonymous">
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
	
	font-family:Microsoft JhengHei;
	border:0;
	background: #ccc;
	display:block;
	margin :10px auto;
	text-align:center;
	border:2px solid #2ecc71;
	padding:5px 10px;	
	outline:none;
	color:black;
	border-radius:24px;
	transition:2s;
	cursor:pointer;

}
.box1 input[type="submit"]:hover{
	background:#2ecc71;
}
.box2 input[type="submit"]{
	
	font-family:Microsoft JhengHei;
	border:0;
	background: #ccc;
	display:block;
	margin :10px auto;
	text-align:center;
	border:2px solid #2ecc71;
	padding:5px 10px;	
	outline:none;
	color:black;
	border-radius:24px;
	transition:2s;
	cursor:pointer;
	position: absolute;
	right: 10%;
	bottom: 10%;
}
.box2 input[type="submit"]:hover{
	background:#2ecc71;
}
#table2{
margin-top:150px;
margin-left:50px;
top:50%;
}
#div1{
float:left;
margin-left:100px;
margin-top:50px;
}
#div2{
float:right;
margin-right:100px;
}
#div3{
float:right;
margin-right:100px;
}
#div4{
display:inline;
aligh:center;
margin-left:400px;
}
.services-half-width-text {
    text-align: center;
}
body .TitleText{
	text-align: -webkit-right;
	margin:auto;
	margin-top: 30px;
	color:#000;
	width: 400px;
}
body .InnerText{
    margin: auto;
    text-align: -webkit-auto;
    margin-top: 30px;
    font-size: 18px;
    margin-left: 40px;
}
#topright {
	position: absolute;
	right: 0px;
}
#Floatingwindow {
	position: fixed;
	background-color: #555;
	width: 800px;
    height: 500px;
	z-index: 995;
	left: 20%;
	top: 30%;
}

.span5 {

}
.AllText{	
	margin:auto;
}
.spacing{
	height: 5px;
	width: 700px;
	margin:auto;
	background-color: #ccc;
	margin-top: 40px;
}
.spacingTitle{
    height: 3px;
    width: 60px;
    background-color: #ddd;
    margin-top: 5px;
}
.spacingInner{
    height: 3px;
    width: 500px;
    background-color: #ddd;
    margin-top: 5px;
}
.Optionbutton{
    margin-bottom: 50px;
    margin-top: 20px;	
}
.OptionbuttonData{
	display: initial;
}
.OptionbuttonPassword{
	display: initial;
}
     #GIbutton {
    font-family: "Poppins", sans-serif;
    border-radius: 50px;
    padding: 5px 22px;
    background: #f5f5f5;
    color: #2dc997;
    font-style: italic;
    text-decoration: none;
    -moz-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    -webkit-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    border-radius: 50px;
    border: 2px solid #2dc997;
    0 1px 25px 0 rgba(0,0,0,.05) inset,: ;
    0 -1px 25px 0 rgba(0,0,0,.05) inset: ;
    -webkit-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
}
    #GIbutton:hover {
    font-family: "Poppins", sans-serif;
    border-radius: 50px;
       
    padding: 5px 22px;
    background: #2dc997;
    color: #f5f5f5;
    font-style: italic;
    text-decoration: none;
    -moz-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    -webkit-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    border-radius: 50px;
    border: 2px solid #2dc997;
    0 1px 25px 0: ;
    rgba(0,0,0,.05) inset,: ;
    0 -1px 25px 0 rgba(0,0,0,.05) inset: ;
    -webkit-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
}
form{margin:0px; display:inline-block }
</style>
<body bgcolor="#11e1e9">
	<jsp:include page="/front-end/HomeMember/HeadMember.jsp" />
	<jsp:include page="/front-end/HomeMember/HeadMemberSetUp.jsp" />
<%-- 	<jsp:include page="/front-end/HomeMember/HeadMemberSetting.jsp" /> --%>
<!-- 	<div id="div4" > -->
		
	<button id="GIbutton" type="button" onclick="StoreRecord()" id="StoreRecord" class="StoreRecord">
		儲值紀錄
	</button>
	
	<button id="GIbutton" type="button" onclick="DeductionRecord()" id="DeductionRecord()" class="DeductionRecord()">
		扣款紀錄
	</button>	
	
	<div id="Store" style="display: none">
		<div id="Floatingwindow" >
			<button type="button" onclick="StoreFork()" id="topright" class="">
				<i class="fas fa-times fa-2x"></i>
			</button>
			<div id="div2"><jsp:include page="/front-end/storeRecord/listOneStoreRecordMemberFrontPos.jsp"/></div> 
			<div>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/storeRecord/storeRecord.do" style="" class="box2" >
					<input type="submit" value="儲值">
					<input type="hidden" name="memID"  value="${memberVO.memID}">
					<input type="hidden" name="action"	value="addToken">
				</FORM>
			</div>
		</div>
	</div>		
	<div id="Deduction" style="display: none">
		<div id="Floatingwindow" >
			<button type="button" onclick="DeductionFork()" id="topright" class="">
				<i class="fas fa-times fa-2x"></i>
			</button>
			<div id="div2">
				<jsp:include page="/front-end/storeRecord/listOneStoreRecordMemberFrontNeg.jsp"/>
		 	</div> 
		</div>
	</div>	
	<%String lightBox =request.getParameter("lightBox");
	if(lightBox!=null){
		%>
		<script>
		$('#Store').show();
		</script>
	<%	
	}
	
	%>	
	
		<%String lightBox1 =request.getParameter("lightBox1");
	if(lightBox1!=null){
		%>
		<script>
		$('#Deduction').show();
		</script>
	<%	
	}
	
	%>	
	
	
	
	
	
	
		
	<!-- <h3>listOneMemberByUpdate.jsp</h3> -->
	<!-- <a class="box" href=/PiCar/regna-master/homeindex.jsp> 請按此回首頁 </a><br> -->
	<%-- <a class="box" href=<%=request.getContextPath()%>/front-end/HomeMember/index.jsp> 請按此回會員首頁 </a> --%>
	<div id=""><h3 align="center">Hello!!${memberVO.name}，這是你的個人資料</h3></div>
	
	<div class="spacing"></div>
	
	<div class="services-half-width container AllText">
		<div class="row">
			<div class="TitleText services-half-width-text span5" style="margin-top: 0px;">
				<div class="TitleText">會員帳號</div>
				<div class="spacingTitle"></div>
				<div class="TitleText">會員姓名</div>
				<div class="spacingTitle"></div>
				<div class="TitleText">會員生日</div>
				<div class="spacingTitle"></div>
				<div class="TitleText">會員信箱</div>
				<div class="spacingTitle"></div>
				<div class="TitleText">會員電話</div>
				<div class="spacingTitle"></div>
				<div class="TitleText">信用卡號</div>
				<div class="spacingTitle"></div>
				<div class="TitleText">剩餘金額</div>
				<div class="spacingTitle"></div>
				<div class="TitleText">寵物喜好</div>
				<div class="spacingTitle"></div>
				<div class="TitleText">抽菸設定</div>
				<div class="spacingTitle"></div>
				<div class="TitleText">會員性別</div>
				<div class="spacingTitle"></div>
				<div class="TitleText">嬰兒座椅</div>
				<div class="spacingTitle"></div>
<!-- 				<div class="TitleText">驗證狀態</div> -->
<!-- 				<div class="spacingTitle"></div> -->
			</div>
			
			<div class="InnerText services-half-width-text span7" style="margin-top: 0px;">
				<div class="InnerText">${memberVO.memID}</div>
				<div class="spacingInner"></div>
				<div class="InnerText">${memberVO.name}</div>
				<div class="spacingInner"></div>
				<div class="InnerText">${memberVO.birthday}</div>
				<div class="spacingInner"></div>
				<div class="InnerText">${memberVO.email}</div>
				<div class="spacingInner"></div>
				<div class="InnerText">${memberVO.phone}</div>
				<div class="spacingInner"></div>
				<div class="InnerText">${memberVO.creditcard}</div>
				<div class="spacingInner"></div>
				<div class="InnerText">${memberVO.token}</div>
				<div class="spacingInner"></div>
				<div class="InnerText">
					<c:choose>
						<c:when test="${memberVO.pet == '1'}"> 喜歡  </c:when>
						<c:when test="${memberVO.pet == '0'}">  不喜歡  </c:when>
					</c:choose>
				</div>
				<div class="spacingInner"></div>
				<div class="InnerText">
					<c:choose>
						<c:when test="${memberVO.smoke == '1'}">抽菸  </c:when>
						<c:when test="${memberVO.smoke == '0'}">  不抽菸  </c:when>
					</c:choose>
				</div>
				<div class="spacingInner"></div>
				<div class="InnerText">
					<c:choose>
						<c:when test="${memberVO.gender == '1'}">男生  </c:when>
						<c:when test="${memberVO.gender == '0'}">女生  </c:when>
					</c:choose>
				</div>
				<div class="spacingInner"></div>
				<div class="InnerText">
					<c:choose>
						<c:when test="${memberVO.babySeat == '1'}">需要  </c:when>
						<c:when test="${memberVO.babySeat == '0'}">不需要  </c:when>
					</c:choose>
				</div>
				<div class="spacingInner"></div>
<!-- 				<div class="InnerText"> -->
<%-- 					<c:choose> --%>
<%-- 						<c:when test="${memberVO.verified == '1'}">已經驗證  </c:when> --%>
<%-- 						<c:when test="${memberVO.verified == '0'}">尚未驗證  </c:when> --%>
<%-- 					</c:choose> --%>
<!-- 				</div> -->
<!-- 				<div class="spacingInner"></div> -->
				個人照片
			<img src="<%=request.getServletContext().getContextPath()%>/front-end/member/member.do?memID=${memberVO.memID}"  width='200' height="200" 
			>
			</div>
		</div>
	</div>
	<div class="Optionbutton">
		<div class="OptionbuttonData">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/member/member.do" style="margin-bottom: 0px;" class="box1" >
			<input type="submit" value="修改資料" >
			<input type="hidden" name="memID"  value="${memberVO.memID}">
			<input type="hidden" name="action"	value="getOne_For_Update"></FORM>
		</div>
	
		<div class="OptionbuttonPassword">	
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/member/memberSelf.do" style="margin-bottom: 0px;" class="box1">
			<input type="submit" value="修改密碼">
			<input type="hidden" name="memID"  value="${memberVO.memID}">
			<input type="hidden" name="action"	value="modify_password"></FORM>
		</div>	
	</div>
<!-- 	<table align="center" border="1" id="table1"  > -->
		
<%-- <%-- 		<!-- <%=memberVO.getMemID()%> --> --%> 
<!-- 		<tr> -->
<!-- 			<td>會員帳號</td> -->
<%-- 			<td colspan="2">${memberVO.memID}</td> --%>
			
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>會員姓名</td> -->
<%-- 			<td>${memberVO.name}</td> --%>
<!-- 			<td rowspan="11"> -->
<!-- 			個人照片<br> -->
<%-- 			<img src="<%=request.getServletContext().getContextPath()%>/front-end/member/member.do?memID=${memberVO.memID}"  width="200" height="200"> --%>
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>會員生日</td> -->
<%-- 			<td>${memberVO.birthday}</td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>會員信箱</td> -->
<%-- 			<td>${memberVO.email}</td> --%>
<!-- 		</tr> -->
<!-- 		<!-- 		<tr>	 --> 
<!-- 		<!-- 			<td>password</td> --> 
<%-- 		<%-- 			<td>${memberVO.password}</td> --%> 
<!-- 		<!-- 		</tr> --> 
<!-- 		<tr> -->
<!-- 			<td>會員電話</td> -->
<%-- 			<td>${memberVO.phone}</td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>信用卡號</td> -->
<%-- 			<td>${memberVO.creditcard}</td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>剩餘金額</td> -->
<%-- 			<td>${memberVO.token}</td> --%>
<!-- 		</tr> -->
<!-- <!-- 		<tr> --> 
<!-- <!-- 			<td>活動代幣</td> --> 
<%-- <%-- 			<td>${memberVO.activityToken}</td> --%> 
<!-- <!-- 		</tr> --> 
<!-- 		<tr> -->
<!-- 			<td>寵物喜好</td> -->
<%-- 			<td><c:choose> --%>
<%-- 				<c:when test="${memberVO.pet == '1'}"> 喜歡  </c:when> --%>
<%-- 				<c:when test="${memberVO.pet == '0'}">  不喜歡  </c:when> --%>
<%-- 			</c:choose></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>抽菸設定</td> -->
<%-- 			<td><c:choose> --%>
<%-- 				<c:when test="${memberVO.smoke == '1'}">抽菸  </c:when> --%>
<%-- 				<c:when test="${memberVO.smoke == '0'}">  不抽菸  </c:when> --%>
<%-- 			</c:choose></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>性     別</td> -->
<%-- 			<td><c:choose> --%>
<%-- 				<c:when test="${memberVO.gender == '1'}">男生  </c:when> --%>
<%-- 				<c:when test="${memberVO.gender == '0'}">女生  </c:when> --%>
<%-- 			</c:choose></td> --%>
<!-- 		</tr> -->
		
<!-- 		<tr> -->
<!-- 			<td>嬰兒座椅</td> -->
<%-- 			<td><c:choose> --%>
<%-- 				<c:when test="${memberVO.babySeat == '1'}">需要  </c:when> --%>
<%-- 				<c:when test="${memberVO.babySeat == '0'}">不需要  </c:when> --%>
<%-- 			</c:choose></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>驗證狀態</td> -->
<%-- 			<td><c:choose> --%>
<%-- 				<c:when test="${memberVO.verified == '1'}">已經驗證  </c:when> --%>
<%-- 				<c:when test="${memberVO.verified == '0'}">尚未驗證  </c:when> --%>
<%-- 			</c:choose></td> --%>
<!-- 		</tr> -->
<!-- <!-- 		<tr> --> 
<!-- 			<td>個人照片</td>  -->
<%-- 			<td><img src="<%=request.getServletContext().getContextPath()%>/front-end/member/member.do?memID=${memberVO.memID}"  width='200' height="200"  --%>
<!-- 			></td>  -->
<!-- <!-- 		</tr> --> 
<!-- 	</table></div> -->
	

<%-- 				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/member/member.do" style="margin-bottom: 0px;" class="box1"> --%>
<!-- 					<input type="submit" value="修改個人資料" > -->
<%-- 					<input type="hidden" name="memID"  value="${memberVO.memID}"> --%>
<!-- 					<input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
				
<%-- 					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/storeRecord/storeRecord.do" style="margin-bottom: 0px;" class="box1"> --%>
<!-- 						<input type="submit" value="代幣儲值"> -->
<%-- 						<input type="hidden" name="memID"  value="${memberVO.memID}"> --%>
<!-- 						<input type="hidden" name="action"	value="addToken"></FORM> -->
					
<%-- 						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/member/member.do" style="margin-bottom: 0px;" class="box1"> --%>
<!-- 							<input type="submit" value="喜好設定"> -->
<%-- 							<input type="hidden" name="memID"  value="${memberVO.memID}"> --%>
<!-- 							<input type="hidden" name="action"	value="getOne_For_Update_HOBBY"> -->
<!-- 						</FORM> -->
				
<%-- 						<form action="<%=application.getContextPath()%>/location" method="POST" class="box1"> --%>
<%-- 							<input type="hidden" name="memID" value="${locationVO.memID}"/> --%>
<%-- 							<input type="hidden" name="location" value="${locationVO.location}"/> --%>
<!-- 							<input type="hidden" name="action" value="getUpdateLocation"/> -->
<!-- 							<input type="submit" value="常用地點"/> -->
<!-- 						</form> -->
				
<%-- 						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/member/memberSelf.do" style="margin-bottom: 0px;" class="box1"> --%>
<!-- 							<input type="submit" value="修改密碼"> -->
<%-- 							<input type="hidden" name="memID"  value="${memberVO.memID}"> --%>
<!-- 							<input type="hidden" name="action"	value="modify_password"></FORM> -->
						
						
<!-- 						<td> -->
<!-- 							<form method="post" action="logoutHandler.do"> -->
<!-- 								<input type="submit" value="登出"> -->
<!-- 								<input type="hidden" name="logout"	value="logout"> -->
<!-- 							</form> -->
<!-- 						</td> -->

 

				
</body>
	<script>
		function StoreRecord(){
			$('#Store').show();
			$('#Deduction').hide();
		}
		function StoreFork(){
			$('#Store').hide();
		}
		
		function DeductionRecord(){
			$('#Deduction').show();
			$('#Store').hide();
		}
		function DeductionFork(){
			$('#Deduction').hide();
		}		
		function show(){
			$('#Store').show();			
		}
	</script>
</html>