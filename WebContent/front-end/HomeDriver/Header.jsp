<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,400">
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Droid+Sans">
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Lobster">
    <link rel="stylesheet" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/assets/prettyPhoto/css/prettyPhoto.css">
    <link rel="stylesheet" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/assets/css/flexslider.css">
    <link rel="stylesheet" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/assets/css/font-awesome.css">
    <link rel="stylesheet" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/assets/css/style.css">
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/assets/ico/apple-touch-icon-57-precomposed.png">

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
                            <a class="brand" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/index.jsp">Andia - a super cool design agency...</a>
                        </h1>
                        <div class="nav-collapse collapse">
                            <ul class="nav pull-right">
                                <li class="current-page widthes">
                                    <a href="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/index.jsp"><i class="icon-home"></i><br />司機首頁</a>
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