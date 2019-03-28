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
        <link rel="stylesheet" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/css/flexslider.css">
        <link rel="stylesheet" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/css/font-awesome.css">
        <link rel="stylesheet" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/css/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
           <link rel="shortcut icon" href="assets/ico/logoCAR.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/ico/apple-touch-icon-57-precomposed.png">

<style>
.offset1 {
        margin-top: 110px;
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
 .TOPMENU {

 }
 .TOPMENU:hover {
	
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
    
<%          
	 if(session.getAttribute("memberVO")!=null){
		 MemberVO memberVO = (MemberVO)session.getAttribute("memberVO"); 
	     DriverService driSrc = new DriverService(); 
	    
	     DriverVO driverVO  = driSrc.getOneDriverBymemID(memberVO.getMemID());	
	     if(driverVO!=null){
	         session.setAttribute("driverVO",driverVO);
	         }
	 }
     
%>

        <!-- Header -->
        <div class="container">
            <div class="header row">
                <div class="span12">
                    <div class="navbar">
                        <div class="navbar-inner">
                        
                         <div id="rigthtop"><form method="post"  action="logoutHandler.do" class="box1">			
				 
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
                            <div class="nav-collapse collapse" style="position:absolute;top:0%;left:23%;">
                                <ul class="nav pull-right">
                                    <li class="current-page widthes dropdown">
                                    <div class="rectangle"></div>
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/index.jsp"><i class="icon-home"></i><br />首頁</a>
                                    </li>
                                    <li class="widthes dropdown">
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/singleOrder/addReservation.jsp"><i class="fas fa-car"></i><br />預約叫車</a>
                                    </li>
                                    <li class="widthes dropdown">
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/SelectGroupBand.jsp"><i class="icon-comments"></i><br />揪團</a>
                                    </li>
                                    <li class="widthes dropdown">
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/singleOrder/listAllfutureTrip.jsp"><i class="icon-tasks"></i><br />訂單查詢</a>
                                    </li>
                                    <li class="widthes dropdown">
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/member/listOneMemberByUpdate.jsp"><i class="fas fa-cog"></i><br />設定</a>
                                    </li>
                                     
                                      <li class="widthes dropdown">
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/activityToken/addActivityToken.jsp"><i class="fas fa-coins"></i><br />免費代幣</a>
                                    </li>                  
                                    <li class="widthes dropdown">
						      <c:if test="${empty driverVO.driverID}">
						      <a href="<%=request.getServletContext().getContextPath()%>/front-end/driver/addDriver.jsp"><i class="icon-user"></i><br />成為司機</a>
						      </c:if>
						      <c:if test="${(not empty driverVO.driverID) and (driverVO.verified == 0)}">
						      <a href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/index.jsp"><i class="icon-user"></i><br />成為司機(待驗證)</a>
						      </c:if>
						      <c:if test="${(not empty driverVO.driverID) and (driverVO.verified == 1)}">
						      <a href="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/index.jsp"><i class="icon-user"></i><br />司機頁面</a>
						      </c:if>
                                    </li>
                                </ul>
                               
                            </div>
                        </div>
                        
                    </div>
                </div>
                
            </div>
            
        </div>

        <!-- Slider -->
        <div class="slider">
            <div class="container">
                <div class="row">
                    <div class="span10 offset1">
                        <div class="flexslider">
                            <ul class="slides">
                                <li data-thumb="assets/img/slider/1.jpg">
                                    <img src="assets/img/slider/1.jpg">
                                    <p class="flex-caption">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et. Lorem ipsum dolor sit amet, consectetur.</p>
                                </li>
                                <li data-thumb="assets/img/slider/2.jpg">
                                    <img src="assets/img/slider/2.jpg">
                                    <p class="flex-caption">Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.</p>
                                </li>
                                <li data-thumb="assets/img/slider/5.jpg">
                                    <img src="assets/img/slider/5.jpg">
                                    <p class="flex-caption">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et. Lorem ipsum dolor sit amet, consectetur.</p>
                                </li>
                                <li data-thumb="assets/img/slider/6.jpg">
                                    <img src="assets/img/slider/6.jpg">
                                    <p class="flex-caption">Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.</p>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Site Description -->
        <div class="presentation container">
            <h2>We are <span class="violet">PICAR</span>, a super cool appointment car system.</h2>
            <p>歡迎加入PICAR，我們提供優於業界的司機抽成，簡易四步驟立即開始接案</p>
        </div>

        <!-- Services -->
        <div class="what-we-do container">
            <div class="row">
                <div class="service span3">
                    <div class="icon-awesome">
                        <i class="icon-eye-open"></i>
                    </div>
                    <h4>步驟一、立即下載APP</h4>
                    <p>全台首創共乘叫車APP，一鍵下載即可享用簡單、方便的智慧接案系統。</p>
                    <a href="services.html">Read more</a>
                </div>
                <div class="service span3">
                    <div class="icon-awesome">
                        <i class="icon-table"></i>
                    </div>
                    <h4>步驟二、輕鬆接案</h4>
                    <p>開啟PICAR APP，即可承接即時叫車、預約叫車、揪團叫車等行程。</p>
                    <a href="services.html">Read more</a>
                </div>
                <div class="service span3">
                    <div class="icon-awesome">
                        <i class="icon-magic"></i>
                    </div>
                    <h4>步驟三、查看未來行程</h4>
                    <p>司機首頁點選「訂單管理」，快速排程您所承接的個人訂單與揪團訂單。</p>
                    <a href="services.html">Read more</a>
                </div>
                <div class="service span3">
                    <div class="icon-awesome">
                        <i class="icon-print"></i>
                    </div>
                    <h4>步驟四、智慧導航神助攻</h4>
                    <p>提供內建導航系統，幫助您順利前往載客點及乘客目的地。</p>
                    <a href="services.html">Read more</a>
                </div>
            </div>
        </div>

        <!-- Latest Work -->
        <div class="portfolio container">
            <div class="portfolio-title">
                <h3> 2019 Picar Top Drivers </h3>
                <h4>年度最佳司機評價排行</h4>
               
            </div>
            <div class="row">
                <div class="work span3">
                     
                    <img src="assets/img/portfolio/driver-1.jpg" alt="">
                    
                    <h4>No.1 第一名</h4>
                    <p>斯坦福<br>平均分數5分<br>目前接案數:28,165筆</p>
                    <div class="icon-awesome">
                        <a href="assets/img/portfolio/driver-1.jpg" rel="prettyPhoto"><i class="icon-search"></i></a>
                        <a href="portfolio.html"><i class="icon-link"></i></a>
                    </div>
                </div>
                <div class="work span3">
                    <img src="assets/img/portfolio/driver-2.jpg" alt="">
                    <h4>No.2 第二名</h4>
                    <p>約翰霍普金斯<br>平均分數4.9分<br>目前接案數:27,156筆</p>
                    <div class="icon-awesome">
                        <a href="assets/img/portfolio/work2.jpg" rel="prettyPhoto"><i class="icon-search"></i></a>
                        <a href="portfolio.html"><i class="icon-link"></i></a>
                    </div>
                </div>
                <div class="work span3">
                    <img src="assets/img/portfolio/driver-3.jpg" alt="">
                    <h4>No.3 第三名</h4>
                    <p>普林斯頓<br>平均分數4.8分<br>目前接案數:25,191筆</p>
                    <div class="icon-awesome">
                        <a href="assets/img/portfolio/work3.jpg" rel="prettyPhoto"><i class="icon-search"></i></a>
                        <a href="portfolio.html"><i class="icon-link"></i></a>
                    </div>
                </div>
                <div class="work span3">
                    <img src="assets/img/portfolio/driver-4.jpg" alt="">
                    <h4>No.4 第四名</h4>
                    <p>伯克利<br>平均分數3.9分<br>目前接案數:23,766筆</p>
                    <div class="icon-awesome">
                        <a href="assets/img/portfolio/work4.jpg" rel="prettyPhoto"><i class="icon-search"></i></a>
                        <a href="portfolio.html"><i class="icon-link"></i></a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer -->

        <!-- Footer -->
        <footer>
            <div class="container">
                <div class="row">
                    <div class="widget span3">
                        <h4>About Us</h4>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et.</p>
                        <p><a href="">Read more...</a></p>
                    </div>
                    <div class="widget span3">
                        <h4>Latest Tweets</h4>
                        <div class="show-tweets"></div>
                    </div>
                    <div class="widget span3">
                        <h4>Flickr Photos</h4>
                        <ul class="flickr-feed"></ul>
                    </div>
                    <div class="widget span3">
                        <h4>Contact Us</h4>
                        <p><i class="icon-map-marker"></i> Address: Via Principe Amedeo 9, 10100, Torino, TO, Italy</p>
                        <p><i class="icon-phone"></i> Phone: 0039 333 12 68 347</p>
                        <p><i class="icon-user"></i> Skype: Andia_Agency</p>
                        <p><i class="icon-envelope-alt"></i> Email: <a href="">contact@andia.co.uk</a></p>
                    </div>
                </div>
                <div class="footer-border"></div>
                <div class="row">
                    <div class="copyright span4">
                        <p>Copyright 2012 Andia - All rights reserved. Template by <a href="http://azmind.com">Azmind</a>.</p>
                    </div>
                    <div class="social span8">
                        <a class="facebook" href=""></a>
                        <a class="dribbble" href=""></a>
                        <a class="twitter" href=""></a>
                        <a class="pinterest" href=""></a>
                    </div>
                </div>
            </div>
        </footer>

        <!-- Javascript -->
        <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/js/jquery-1.8.2.min.js"></script>
        <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/js/jquery.flexslider.js"></script>
        <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/js/jquery.tweet.js"></script>
        <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/js/jflickrfeed.js"></script>
        <script src="http://maps.google.com/maps/api/js?sensor=true"></script>
        <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/js/jquery.ui.map.min.js"></script>
        <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/js/jquery.quicksand.js"></script>
        <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/prettyPhoto/js/jquery.prettyPhoto.js"></script>
        <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/js/scripts.js"></script>

    </body>
    <!--==========websocket推播 開始=============-->
 <script>
 	var MyPoint="/BroadcastServer/${memID}";
 	var host=window.location.host;
 	var path=window.location.pathname;
 	var webCtx=path.substring(0,path.indexOf('/',1));
 	var endPointURL="ws://"+window.location.host+webCtx+MyPoint;
 	
 	var webSocketTitle =document.getElementById("webSocketTitle"); //狀態標題
 	var statusOutput=document.getElementById("statusOutput");//狀態內容
 	
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
 		swal(message, "歡迎使用Picar智慧叫車系統", "success");
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




