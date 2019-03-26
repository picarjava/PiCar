<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.driver.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>

<!--引用SweetAlert2.js-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.all.min.js"></script>
<!-- 登入功能串接 ，將VOmemID指定給 memID-->
<%@ page import="com.member.model.MemberVO"%>
<%
if((MemberVO)session.getAttribute("memberVO")!=null){
	MemberVO memberVO=(MemberVO)session.getAttribute("memberVO");
	String memID=memberVO.getMemID();	
	session.setAttribute("memID",memID);
	
}
%> 

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <title>Andia - Responsive Agency Template</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">     
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <!-- CSS -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,400">
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Droid+Sans">
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Lobster">
        <link rel="stylesheet" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/prettyPhoto/css/prettyPhoto.css">
        <link rel="stylesheet" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/headcss/flexslider.css">
        <link rel="stylesheet" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/headcss/font-awesome.css">
        <link rel="stylesheet" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/headcss/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
<!--         拿調 -->
        <link rel="shortcut icon" href="assets/ico/logoCAR.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/ico/apple-touch-icon-57-precomposed.png">

<style>
.offset1 {
        margin-top: 70px;
    }
    
.navbar .nav.pull-right{
    margin-right: 100px;

}
.widthes{
width: 100px;

}
.header .navbar-inner {
  left: 50px;
    right: 50px;
}
.flex-control-thumbs img {
height: 145px;
}
#rigthtop{
float: right;
}
#p {
　font-family:Microsoft JhengHei;
	color:white;
	align:right;
}
.box1 input[type="submit"]{
	
	font-family:Microsoft JhengHei;
	border:0;
	background:#f5f5f5;
	display:block;
	margin :10px auto;
	text-align:center;
/* 	border:2px solid #2ecc71; */
	padding:5px 10px;	
	outline:none;
	color:black;
	border-radius:24px;
	transition:2s;
	cursor:pointer;
}
.box1 input[type="submit"]:hover{
	background:yellow;
}
</style>

    </head>

    <body onload="connect();" onunload="disconnect();">
    
<%-- <%--         <% --%> 
<!-- //     MemberVO memberVO = (MemberVO)session.getAttribute("memberVO"); -->
<!-- //     DriverService driSrc = new DriverService(); -->
    
   
<!-- //     DriverVO driverVO  = driSrc.getOneDriverBymemID(memberVO.getMemID()); -->
<!-- //     if(driverVO!=null){ -->
<!-- //     session.setAttribute("driverVO",driverVO); -->
<!-- //     DriverVO drixx = (DriverVO)session.getAttribute("driverVO"); -->
<!-- //     } -->
<%-- <%-- 	%> --%> 

        <!-- Header -->
        <div class="container">
            <div class="header row">
                <div class="span12">
                    <div class="navbar">
                            <div class="navbar-inner">
                              
                         <div id="rigthtop">
                <form method="post"  action="/PiCar/front-end/HomeMember/logoutHandler.do" class="box1">	
				<input type="submit" value="登出">	
				 <input type="hidden" name="logout"	value="logout">	
				</form>
               <p id="p">${memberVO.name}，你好</p>
               </div>
              
                            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </a>
                            <h1>
                                <a class="brand" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/index.jsp">Andia - a super cool design agency...</a>
                            </h1>
                            <div class="nav-collapse collapse" style="position:absolute;top:0%;left:23%;" >
                                <ul class="nav pull-right">
                                    <li class="current-page widthes">
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/index.jsp"><i class="icon-home"></i><br />首頁</a>
                                    </li>
                                    <li class="widthes">
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/singleOrder/addReservation.jsp"><i class="fas fa-car"></i><br />預約叫車</a>
                                    </li>
                                    <li class="widthes">
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/SelectGroupBand.jsp"><i class="icon-comments"></i><br />揪團</a>
                                    </li>
                                    <li class="widthes">
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/singleOrder/listAllfutureTrip.jsp"><i class="icon-tasks"></i><br />訂單查詢</a>
                                    </li>
                                    <li class="widthes">
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/member/listOneMemberByUpdate.jsp"><i class="fas fa-cog"></i><br />設定</a>
                                    </li>
                                      <li class="widthes">
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/activityToken/addActivityToken.jsp"><i class="fas fa-coins"></i><br />免費代幣</a>
                                    </li>
                                     
                                    <li class="widthes">
						      <c:if test="${empty driverVO.driverID}">
						      <a href="<%=request.getServletContext().getContextPath()%>/front-end/driver/addDriver.jsp"><i class="icon-user"></i><br />成為司機</a>
						      </c:if>
						      <c:if test="${not empty driverVO.driverID}">
						      <a href="<%=request.getServletContext().getContextPath()%>/front-end/driver/homeDriverDataManagment.jsp"><i class="icon-user"></i><br />司機頁面</a>
						      </c:if>
                                    </li>
                                    <li class="widthes">
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/member/logoutHandler.do"><i class="fas fa-coins"></i><br />登出</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

		<div style="margin-bottom: 150px;"></div>
        <!-- Slider -->
   

        <!-- Javascript -->
<%--         <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/js/jquery-1.8.2.min.js"></script> --%>
<%--         <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/bootstrap/js/bootstrap.min.js"></script> --%>
<%--         <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/js/jquery.flexslider.js"></script> --%>
<%--         <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/js/jquery.tweet.js"></script> --%>
<%--         <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/js/jflickrfeed.js"></script> --%>
<!--         <script src="http://maps.google.com/maps/api/js?sensor=true"></script> -->
<%--         <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/js/jquery.ui.map.min.js"></script> --%>
<%--         <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/js/jquery.quicksand.js"></script> --%>
<%--         <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/prettyPhoto/js/jquery.prettyPhoto.js"></script> --%>
<%--         <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/js/scripts.js"></script> --%>

    </body>
    
     <!--==========websocket推播 開始=============-->
 <script>
 	var MyPoint="/BroadcastServer/${memID}";
 	var host=window.location.host;
 	var path=window.location.pathname;
 	var webCtx=path.substring(0,path.indexOf('/',1));
 	var endPointURL="ws://"+window.location.host+webCtx+MyPoint;
 	
 	var webSocketTitle =document.getElementById("webSocketTitle"); //狀態標題
//  	var statusOutput=document.getElementById("statusOutput");//狀態內容
 	
 	var webSocket;
 	
 	
 	function connect(){
 		
 		//建立websocket物件
 		webSocket=new WebSocket(endPointURL);
 		
 		webSocket.onopen=function(event){
//  			updateStatus("WebSocket 成功連線");
 		};
 		
 		
 		var message=""; //本次連線的推播容器
 		webSocket.onmessage=function(event){
 			var jsonObj=JSON.parse(event.data);
 			message=jsonObj.message+"\r\n"+"<br>"+message;
//  			window.alert(message);
//  			updateStatus(message);
 			
 			swal(message, "請至【訂單查詢】確認", "success");
 			
//  			else{
//  				swal(message, "歡迎使用Picar智慧叫車系統", "success");
//  				count++;
//  			}
 		};
 		
 		webSocket.onclose=function(event){
//  			updateStatus("WebSocket已離線");
 		};
 	}
 	
 	function disconnect(){
 		webSocket.close();
 	}
 	
//  	function updateStatus(newStatus){
//  		statusOutput.innerHTML= newStatus;
//  	}
 	
 	
 </script>

 <!--==========websocket推播 結束============-->
    
</html>

