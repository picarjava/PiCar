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
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/prettyPhoto/css/prettyPhoto.css">
        <link rel="stylesheet" href="assets/css/flexslider.css">
        <link rel="stylesheet" href="assets/css/font-awesome.css">
        <link rel="stylesheet" href="assets/css/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="assets/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">

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
    session.setAttribute("driverVO",driverVO);
    DriverVO drixx = (DriverVO)session.getAttribute("driverVO");
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
            <h2>We are <span class="violet">Andia</span>, a super cool design agency.</h2>
            <p>We design beautiful websites, logos and prints. Your project is safe with us.</p>
        </div>

        <!-- Services -->
        <div class="what-we-do container">
            <div class="row">
                <div class="service span3">
                    <div class="icon-awesome">
                        <i class="icon-eye-open"></i>
                    </div>
                    <h4>Beautiful Websites</h4>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et...</p>
                    <a href="services.html">Read more</a>
                </div>
                <div class="service span3">
                    <div class="icon-awesome">
                        <i class="icon-table"></i>
                    </div>
                    <h4>Responsive Layout</h4>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et...</p>
                    <a href="services.html">Read more</a>
                </div>
                <div class="service span3">
                    <div class="icon-awesome">
                        <i class="icon-magic"></i>
                    </div>
                    <h4>Awesome Logos</h4>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et...</p>
                    <a href="services.html">Read more</a>
                </div>
                <div class="service span3">
                    <div class="icon-awesome">
                        <i class="icon-print"></i>
                    </div>
                    <h4>High Res Prints</h4>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et...</p>
                    <a href="services.html">Read more</a>
                </div>
            </div>
        </div>

        <!-- Latest Work -->
        <div class="portfolio container">
            <div class="portfolio-title">
                <h3>Our Latest Work</h3>
            </div>
            <div class="row">
                <div class="work span3">
                     
                    <img src="assets/img/portfolio/work1.jpg" alt="">
                    
                    <h4>Lorem Website</h4>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor...</p>
                    <div class="icon-awesome">
                        <a href="assets/img/portfolio/work1.jpg" rel="prettyPhoto"><i class="icon-search"></i></a>
                        <a href="portfolio.html"><i class="icon-link"></i></a>
                    </div>
                </div>
                <div class="work span3">
                    <img src="assets/img/portfolio/work2.jpg" alt="">
                    <h4>Ipsum Logo</h4>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor...</p>
                    <div class="icon-awesome">
                        <a href="assets/img/portfolio/work2.jpg" rel="prettyPhoto"><i class="icon-search"></i></a>
                        <a href="portfolio.html"><i class="icon-link"></i></a>
                    </div>
                </div>
                <div class="work span3">
                    <img src="assets/img/portfolio/work3.jpg" alt="">
                    <h4>Dolor Prints</h4>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor...</p>
                    <div class="icon-awesome">
                        <a href="assets/img/portfolio/work3.jpg" rel="prettyPhoto"><i class="icon-search"></i></a>
                        <a href="portfolio.html"><i class="icon-link"></i></a>
                    </div>
                </div>
                <div class="work span3">
                    <img src="assets/img/portfolio/work4.jpg" alt="">
                    <h4>Sit Amet Website</h4>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor...</p>
                    <div class="icon-awesome">
                        <a href="assets/img/portfolio/work4.jpg" rel="prettyPhoto"><i class="icon-search"></i></a>
                        <a href="portfolio.html"><i class="icon-link"></i></a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Testimonials -->
        <div class="testimonials container">
            <div class="testimonials-title">
                <h3>Testimonials</h3>
            </div>
            <div class="row">
                <div class="testimonial-list span12">
                    <div class="tabbable tabs-below">
                        <div class="tab-content">
                            <div class="tab-pane active" id="A">
                                <img src="assets/img/testimonials/1.jpg" title="" alt="">
                                <p>"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et. Lorem ipsum dolor sit amet, consectetur..."<br /><span class="violet">Lorem Ipsum, dolor.co.uk</span></p>
                            </div>
                            <div class="tab-pane" id="B">
                                <img src="assets/img/testimonials/2.jpg" title="" alt="">
                                <p>"Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat..."<br /><span class="violet">Minim Veniam, nostrud.com</span></p>
                            </div>
                            <div class="tab-pane" id="C">
                                <img src="assets/img/testimonials/3.jpg" title="" alt="">
                                <p>"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et. Lorem ipsum dolor sit amet, consectetur..."<br /><span class="violet">Lorem Ipsum, dolor.co.uk</span></p>
                            </div>
                            <div class="tab-pane" id="D">
                                <img src="assets/img/testimonials/1.jpg" title="" alt="">
                                <p>"Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat..."<br /><span class="violet">Minim Veniam, nostrud.com</span></p>
                            </div>
                        </div>
                       <ul class="nav nav-tabs">
                           <li class="active"><a href="#A" data-toggle="tab"></a></li>
                           <li class=""><a href="#B" data-toggle="tab"></a></li>
                           <li class=""><a href="#C" data-toggle="tab"></a></li>
                           <li class=""><a href="#D" data-toggle="tab"></a></li>
                       </ul>
                   </div>
                </div>
            </div>
        </div>

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
        <script src="assets/js/jquery-1.8.2.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery.flexslider.js"></script>
        <script src="assets/js/jquery.tweet.js"></script>
        <script src="assets/js/jflickrfeed.js"></script>
        <script src="http://maps.google.com/maps/api/js?sensor=true"></script>
        <script src="assets/js/jquery.ui.map.min.js"></script>
        <script src="assets/js/jquery.quicksand.js"></script>
        <script src="assets/prettyPhoto/js/jquery.prettyPhoto.js"></script>
        <script src="assets/js/scripts.js"></script>

    </body>

</html>

