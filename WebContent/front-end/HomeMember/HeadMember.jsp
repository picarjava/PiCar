<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.driver.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>


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
        <link rel="shortcut icon" href="assets/ico/favicon.ico">
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
</style>

    </head>

    <body>
    
        <%
    MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
    DriverService driSrc = new DriverService();
    
   
    DriverVO driverVO  = driSrc.getOneDriverBymemID(memberVO.getMemID());
    if(driverVO!=null){
    session.setAttribute("driverVO",driverVO);
    DriverVO drixx = (DriverVO)session.getAttribute("driverVO");
    }
	%>

        <!-- Header -->
        <div class="container">
            <div class="header row">
                <div class="span12">
                    <div class="navbar">
                        <div class="navbar-inner">
                            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </a>
                            <h1>
                                <a class="brand" href="index.html">Andia - a super cool design agency...</a>
                            </h1>
                            <div class="nav-collapse collapse">
                                <ul class="nav pull-right">
                                    <li class="current-page widthes">
                                        <a href="index.html"><i class="icon-home"></i><br />首頁</a>
                                    </li>
                                    <li class="widthes">
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/singleOrder/addReservation.jsp"><i class="fas fa-car"></i><br />預約叫車</a>
                                    </li>
                                    <li class="widthes">
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/SelectGroupBand.jsp"><i class="icon-comments"></i><br />糾團</a>
                                    </li>
                                    <li class="widthes">
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/singleOrder/listAllfutureTrip.jsp"><i class="icon-tasks"></i><br />訂單查詢</a>
                                    </li>
                                    <li class="widthes">
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/member/listOneMemberByUpdate.jsp"><i class="fas fa-cog"></i><br />設定</a>
                                    </li>
                                     <li class="widthes">
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/activityToken/listOnesAllActivityToken.jsp"><i class="fas fa-coins"></i><br />代幣管理</a>
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

</html>

