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
        <link rel="shortcut icon" href="assets/ico/logoCAR.png">
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
          box-shadow: 4px 4px 20px 0px rgba(20%,20%,20%,0.5);
}
.row {

}
</style>

    </head>

    <body>

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
                                <a class="brand" href="index.html">Picar - a super cool smart ride system...</a>
                            </h1>
                            <div class="nav-collapse collapse">
                                <ul class="nav pull-right">
                                    <li class="current-page widthes">
                                        <a href="index.html"><i class="icon-home"></i><br />司機首頁</a>
                                    </li>
                                    <li class="widthes">
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/singleOrder/listAllfutureDriverTask.jsp"><i class="icon-camera"></i><br />未來訂單</a>
                                    </li>
                                    <li class="widthes">
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/driver/homeDriverDataManagment.jsp"><i class="icon-camera"></i><br />司機資料</a>
                                    </li>
                                    <li class="widthes">
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/driver/setting.jsp"><i class="icon-camera"></i><br />喜好設定</a>
                                    </li>
                                    <li class="widthes">
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/singleOrder/listPastSingleDriverTask.jsp"><i class="icon-camera"></i><br />單人歷史</a>
                                    </li>
                                    <li class="widthes">
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/groupOrder/listPastGroupDriverTask.jsp"><i class="icon-camera"></i><br />揪團歷史</a>
                                    </li>
                                    <li class="widthes">
                                        <a href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/index.jsp"><i class="icon-camera"></i><br />會員首頁</a>
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
        <footer>
            <div class="container">
                <div class="row">
                    <div class="widget span3">
                        <h4>關於我們About Us</h4>
                        <p>共享資源的乘車APP，打造乘客、司機雙贏的經營模式，共享經濟促進人類邁向美好生活! Picar 即時叫車獨家結合「共乘機制」，線上預約叫車更提供超省錢「揪團」功能，一起共乘搭車，環保節能、經濟又實惠!</p>
                        <p><a href="">Read more...</a></p>
                    </div>
                    <div class="widget span3">
                        <h4>目前PICAR司機人數 Latest number of drivers</h4>
                        <p>已達321,684,123 人</p>
                    </div>
                    <div class="widget span3">
                        <h4>社群平台Flickr Photos</h4>
                        <ul class="flickr-feed"></ul>
                    </div>
                    <div class="widget span3">
                        <h4>聯絡我們Contact Us</h4>
                        <p><i class="icon-map-marker"></i> Address: TibaMe x 資策會中壢中心  320桃園市中壢區中大路300號</p>
                        <p><i class="icon-phone"></i> Phone: 03 425 7387</p>
                        <p><i class="icon-user"></i> Skype: TibaMe</p>
                        <p><i class="icon-envelope-alt"></i> Web: <a href="">chungli2.iiiedu.org.tw</a></p>
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

