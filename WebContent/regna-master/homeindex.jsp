<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Regna Bootstrap Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <!-- Favicons -->
    <link href="<%=request.getServletContext().getContextPath()%>/regna-master/img/favicon.png" rel="icon">
    <link href="<%=request.getServletContext().getContextPath()%>/regna-master/img/apple-touch-icon.png" rel="apple-touch-icon">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700" rel="stylesheet">
    <!-- Bootstrap CSS File -->
    <link href="<%=request.getServletContext().getContextPath()%>/regna-master/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Libraries CSS Files -->
    <link href="<%=request.getServletContext().getContextPath()%>/regna-master/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getServletContext().getContextPath()%>/regna-master/lib/animate/animate.min.css" rel="stylesheet">
    <!-- Main Stylesheet File -->
    <link href="<%=request.getServletContext().getContextPath()%>/regna-master/css/style.css" rel="stylesheet">｢
    <!-- =======================================================
    Theme Name: Regna
    Theme URL: https://bootstrapmade.com/regna-bootstrap-onepage-template/
    Author: BootstrapMade.com
    License: https://bootstrapmade.com/license/
  ======================================================= -->
    <style type="text/css">
    li {
        font-family: Microsoft JhengHei;
        font-weight: bold;
        bolder: bold;

    }

    #header #logo img {
        padding: 0px;

    }

    #header {
        padding: 20px;
        background: #000000;
    }

    .nav-menu a {
        font-family: Microsoft JhengHei;
        font-weight: bold;
        padding: 2px;
        font-size: 15px;
    }

    .nav-menu a.Signin {
        font-size: 16px;
        padding: 0px;
        font-size: larger;
    }

    .nav-menu li {
        margin-left: 30px;

    }

    .nav-menu .sig {
        margin-left: 100px;
    }

    .nav-menu a.sift {
        margin-left: 5px;

    }

    #about .about-container .icon-box .title a {
        font-family: Microsoft JhengHei;
        font-weight: bold;
        bolder: bold;


    }

    .section-title {
        font-family: Microsoft JhengHei;
        font-weight: bold;
        bolder: bold;

    }

    #services .title {
        font-family: Microsoft JhengHei;
        font-weight: bold;
        font-size: 25px;

    }

    #call-to-action .cta-btn {
        font-weight: bold;
        font-family: Microsoft JhengHei;
    }

    #call-to-action .cta-title {
        font-weight: bold;
        font-family: Microsoft JhengHei;


    }

    .pic img {
        opacity: 0.9;
        transform: scale(1, 1);
        transition: all 0.6s ease-out;
        border-color: #DDD;
    }

    .pic img:hover {
        opacity: 1;
        transform: scale(1.2, 1.2);
        border-color: #DDD;

    }

    .stylesbody {

        background-color: #DDDDDD;
        border: 2px;
        border-style: outset;


        margin: 2px;
    }

    .stylesbody:hover {

        background-color: #FFFFFF;
        border-left: 0px;
        border-top: 0px;
        border-right: 0px;
        border-bottom: -5px;
        border-width: -30px;
        border-color: #2dc997;
        box-shadow: 4px 4px 12px -2px rgba(20%, 20%, 20%, 0.2);

    }

    .stylestop {
        padding-top: 30px;
        border-color: #fff;

    }

    .btn-primary {
        border-color: #ccc;
        background-color: #fff;
        width: 100%;
        border: 2px border-color:#aaa;

    }

    .btn-primary:hover {
        background-color: #2dc997;
        border-color: #2dc997;

    }

    .btn-primary .tentsa:hover {
        color: #fff;
        font-family: Microsoft JhengHei;
        font-weight: bold;
        bolder: bold;
    }


    .btn {
        text-align: left;
    }

    .btn-primary:focus {
        background-color: #2dc997;
        border-color: #2dc997;
    }

    .btn-primary .tentsa:focus {
        color: #fff;
        font-family: Microsoft JhengHei;
        font-weight: bold;
        bolder: bold;
    }

    .btn-primary .tentsa {
        margin: 0px auto;
        padding: 2px;
         padding-left: : 15px;
        color: #333;
        font-family: Microsoft JhengHei;
        font-weight: bold;
    }
    #portfolio .portfolio-item{
        margin-bottom: 0px;
         margin-top: -20px;
    }
    .card-body{
        margin-bottom: 30px;
margin-top: -20px;
    }
    
  	#hero h1 {
  	    margin: 0px 0 10px 0;
  	}
    </style>
</head>

<body>
    <!--==========================
  Header
  ============================-->
    <header id="header">
        <div class="container">
            <div id="logo" class="pull-left">
                <a href="#hero" sytle="float:top;"><img src="img/LOGO5.png" alt="" title="" /></img></a>
                <!-- Uncomment below if you prefer to use a text logo -->
                <!--<h1><a href="#hero">Regna</a></h1>-->
            </div>
            <nav id="nav-menu-container">
                <ul class="nav-menu">
                    <li class="menu-active"><a href="#hero">最新消息</a></li>
                    <li><a href="#about">關於PICAR</a></li>
                    <li><a href="#facts">PICAR揪團愛地球</a></li>
                    <li><a href="#services">服務</a></li>
                    <li><a href="#call-to-action">下載</a></li>
                    <li class="portfolio"><a href="#team">搭乘方案</a>
                    <li><a href="#QA">Q&A</a></li>
                    </li>
                    <li class="sig"><a href="<%=request.getServletContext().getContextPath()%>/front-end/member/addMember.jsp" class="Signin "><i class="fas fa-user-plus"></i>註冊</a></li>
                    <li><a href="<%=request.getServletContext().getContextPath()%>/front-end/login/login.jsp" class="Signin sift"><i class="fas fa-child"></i>登入</a></li>
        </div>
        </ul>
        <ul class="justify-content-center">
        </ul>
        </div>
        </nav><!-- #nav-menu-container -->
    </header><!-- #header -->
    <!--==========================
    Hero Section
  ============================-->
    <section id="hero">
        <div class="hero-container">
            <h1>想要獲得免費代幣嗎??</h1>
            <h2>立即註冊PICAR，領取折扣，好理大放送，心動等妳行動</h2>
            <a href="<%=request.getServletContext().getContextPath()%>/front-end/member/addMember.jsp" class="btn-get-started">立即註冊</a>
        </div>
    </section><!-- #hero -->
    <main id="main">
        <!--==========================
      About Us Section
    ============================-->
        <section id="about">
            <div class="container">
                <div class="row about-container">
                    <div class="col-lg-6 content order-lg-1 order-2">
                        <h2 class="title">關於PICAR</h2>
                        <p>
                            近年來共享經濟的交通資源、電子支付與環保意識已成為全
                            球趨勢，我們將現有的交通模式以用電子支付的方式融入共乘的概念，達到資源共享、司機與乘客雙方互利與提升運輸資源的使用效率，永續發展為目標讓生活、環境變得更美好。
                        </p>
                        <div class="icon-box wow fadeInUp">
                            <div class="icon"><i class="fas fa-car-side"></i></div>
                            <h4 class="title"><a href="">智慧共乘：</a></h4>
                            <p class="description">一同分享一同分攤。您可設定喜好透過智能配對，與前往相同目的地的人們共乘享有更物美價廉的車</p>
                        </div>
                        <div class="icon-box wow fadeInUp" data-wow-delay="0.2s">
                            <div class="icon"><i class="fas fa-taxi"></i></div>
                            <h4 class="title"><a href="">電子支付</a></h4>
                            <p class="description">金流透明、無紙化、多種行動支付、虛擬代幣。
                            </p>
                        </div>
                        <div class="icon-box wow fadeInUp" data-wow-delay="0.4s">
                            <div class="icon"><i class="fas fa-car"></i></div>
                            <h4 class="title"><a href="">快速叫車：</a></h4>
                            <p class="description">操作便捷，立即享受值得信賴的搭乘服務。</p>
                        </div>
                    </div>
                    <div class="col-lg-6 background order-lg-2 order-1 wow fadeInRight"></div>
                </div>
            </div>
        </section><!-- #about -->
        <!--==========================
      Facts Section
    ============================-->
        <section id="facts">
            <div class="container wow fadeIn">
                <div class="section-header">
                    <h3 class="section-title">Picar揪團愛地球</h3>
                    <p class="section-description">共享資源的乘車APP，打造乘客、司機雙贏的經營模式，共享經濟促進人類邁向美好生活! <br>Picar 即時叫車獨家結合「共乘機制」，線上預約叫車更提供超省錢「揪團」功能，一起共乘搭車，環保節能、經濟又實惠!<br><br>
                        統計數據顯示全球目前已達三億使用者、台灣地區超過500多萬用戶:</p>
                </div>
                <div class="row counters">
                    <div class="col-lg-3 col-6 text-center">
                        <span data-toggle="counter-up">232</span>
                        <p>目前在線司機人數</p>
                    </div>
                    <div class="col-lg-3 col-6 text-center">
                        <span data-toggle="counter-up">521</span>
                        <p>目前在線乘客人數</p>
                    </div>
                    <div class="col-lg-3 col-6 text-center">
                        <span data-toggle="counter-up">1,463</span>
                        <p>目前可參加揪團數量</p>
                    </div>
                    <div class="col-lg-3 col-6 text-center">
                        <span data-toggle="counter-up">15</span>
                        <p>目前揪團成團數量</p>
                    </div>
                </div>
            </div>
        </section><!-- #facts -->
        <!--==========================
      Services Section
    ============================-->
        <section id="services">
            <div class="container wow fadeIn">
                <div class="section-header">
                    <h3 class="section-title">PICAR為你服務</h3>
                    <p class="section-description">Picar的誕生，帶來全新的乘車體驗，改變我們對於交通工具想法，能夠更安全迅速的抵達目的地</p>
                </div>
                <div class="row">
                    <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.2s">
                        <div class="box">
                            <div class="icon"><a href=""><i class="fas fa-users"></i></a></div>
                            <h4 class="title"><a href="">優惠</a></h4>
                            <p class="description">Voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.4s">
                        <div class="box">
                            <div class="icon"><a href=""><i class="fas fa-tachometer-alt"></i></a></div>
                            <h4 class="title"><a href="">便捷</a></h4>
                            <p class="description">At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.6s">
                        <div class="box">
                            <div class="icon"><a href=""><i class="fas fa-calendar-alt"></i></a></div>
                            <h4 class="title"><a href="">迅速</a></h4>
                            <p class="description">Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.2s">
                        <div class="box">
                            <div class="icon"><a href=""><i class="fas fa-comments"></i></a></div>
                            <h4 class="title"><a href="">訊息</a></h4>
                            <p class="description">Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.4s">
                        <div class="box">
                            <div class="icon"><a href=""><i class="fas fa-id-card"></i></a></div>
                            <h4 class="title"><a href="">司機</a></h4>
                            <p class="description">想成為斜槓人生的一員嗎？，想要改變生活嗎?想要認識朋友並走遍一個城市大街小巷，來成為Picar一員吧</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.6s">
                        <div class="box">
                            <div class="icon"><a href=""><i class="fas fa-home"></i></a></div>
                            <h4 class="title"><a href="">舒適</a></h4>
                            <p class="description">Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi</p>
                        </div>
                    </div>
                </div>
            </div>
        </section><!-- #services -->
        <!--==========================
    Call To Action Section
    ============================-->
        <section id="call-to-action">
            <div class="container wow fadeIn">
                <div class="row">
                    <div class="col-lg-9 text-center text-lg-left">
                        <h3 class="cta-title">免費下載 </h3>
                        <p class="cta-text">全台首創共乘叫車APP，一鍵下載即可享用簡單、方便、省車資的智慧交通系統。 Picar為您提供節能減碳的交通方式，更提供合理的乘客收費與司機抽成，立即下載搭乘Picar前往更好的台灣。</p>
                    </div>
                    <div class="col-lg-3 cta-btn-container text-center">
                        <a class="cta-btn align-middle " href="#">現在就加入Picar</a>
                    </div>
                </div>
            </div>
        </section><!-- #call-to-action -->
        <!--==========================
 
    ============================-->
        <section id="team">
            <div class="container wow fadeInUp">
                <div class="section-header">
                    <h3 class="section-title">搭乘方案</h3>
                    <p class="section-description">Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque</p>
                </div>
                <div class="row" id="portfolio-wrapper">
                    <div class="col-lg-3 col-md-6">
                        <div class="member">
                            <div class="pic"><img src="img/team-1.jpg" alt="" class="img-thumbnail"></div>
                            <h4>揪團</h4>
                            <span>Chief Executive Officer</span>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="member">
                            <div class="pic"><img src="img/team-2.jpg" alt="" class="img-thumbnail"></div>
                            <h4>共乘</h4>
                            <span>Product Manager</span>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="member">
                            <div class="pic"><img src="img/team-3.jpg" alt="" class="img-thumbnail"></div>
                            <h4>代駕</h4>
                            <span>CTO</span>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="member">
                            <div class="pic"><img src="img/team-4.jpg" alt="" class="img-thumbnail"></div>
                            <h4>預約</h4>
                            <span>Accountant</span>
                        </div>
                    </div>
                </div>
            </div>
        </section><!-- #team -->
        <!--==========================
      Contact Section
    ============================-->
        <section id="QA">
            <div id="google-map" data-latitude="40.713732" data-longitude="-74.0092704"></div>
        </section>
        <section id="portfolio">
            <div class="container wow fadeInUp">
                <div class="section-header">
                    <h3 class="section-title">Q&A</h3>
                    <p class="section-description">Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque</p>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <ul id="portfolio-flters">
                            <li data-filter=".filter-app, .filter-card, .filter-logo, .filter-web" class="filter-active">全部</li>
                           
                            <li data-filter=".filter-card">預約</li>
                            <li data-filter=".filter-logo">揪團</li>
                            <li data-filter=".filter-web">叫車</li>
                             <li data-filter=".filter-app">其他</li>
                        </ul>
                    </div>
                </div>

                <div class="row" id="portfolio-wrapper">
                    <div class="col-lg-12 col-md-12  portfolio-item filter-web">
                        <p>
                            <button class="btn btn-primary " type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                <h5 class="tentsa">及時叫車的好處?</h5>
                            </button>
                        </p>
                        <div class="collapse" id="collapseExample">
                            <div class="card card-body">
                                Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. Nihil 
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row" id="portfolio-wrapper">
                    <div class="col-lg-12 col-md-12  portfolio-item filter-logo">
                        <p>
                            <button class="btn btn-primary " type="button" data-toggle="collapse" data-target="#collapseExample1" aria-expanded="false" aria-controls="collapseExample">
                                <h5 class="tentsa">揪團的好處?</h5>
                            </button>
                        </p>
                        <div class="collapse" id="collapseExample1">
                            <div class="card card-body">
                                Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident.
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </section><!-- #contact -->
    </main>
    <!--==========================
    Footer
  ============================-->
    <footer id="footer">
        <div class="footer-top">
            <div class="container">
            </div>
        </div>
        <div class="container">
            <div class="copyright">
                &copy; Copyright <strong>Regna</strong>. All Rights Reserved
            </div>
            <div class="credits">
                <!--
          All the links in the footer should remain intact.
          You can delete the links only if you purchased the pro version.
          Licensing information: https://bootstrapmade.com/license/
          Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/buy/?theme=Regna
        -->
                Bootstrap Templates by <a href="https://bootstrapmade.com/">BootstrapMade</a>
            </div>
        </div>
    </footer><!-- #footer -->
    <a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>
    <!-- JavaScript Libraries -->
    <script src="<%=request.getServletContext().getContextPath()%>/regna-master/lib/jquery/jquery.min.js"></script>
    <script src="<%=request.getServletContext().getContextPath()%>/regna-master/lib/jquery/jquery-migrate.min.js"></script>
    <script src="<%=request.getServletContext().getContextPath()%>/regna-master/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getServletContext().getContextPath()%>/regna-master/lib/easing/easing.min.js"></script>
    <script src="<%=request.getServletContext().getContextPath()%>/regna-master/lib/wow/wow.min.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD8HeI8o-c1NppZA-92oYlXakhDPYR7XMY"></script>
    <script src="<%=request.getServletContext().getContextPath()%>/regna-master/lib/waypoints/waypoints.min.js"></script>
    <script src="<%=request.getServletContext().getContextPath()%>/regna-master/lib/counterup/counterup.min.js"></script>
    <script src="<%=request.getServletContext().getContextPath()%>/regna-master/lib/superfish/hoverIntent.js"></script>
    <script src="<%=request.getServletContext().getContextPath()%>/regna-master/lib/superfish/superfish.min.js"></script>
    <!-- Contact Form JavaScript File -->
    <script src="<%=request.getServletContext().getContextPath()%>/regna-master/contactform/contactform.js"></script>
    <!-- Template Main Javascript File -->
    <script src="<%=request.getServletContext().getContextPath()%>/regna-master/js/main.js"></script>
</body>

</html>