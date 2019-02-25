<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.activity.model.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
  <meta charset="utf-8">
  <title>活動資訊管理</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="" name="keywords">
  <meta content="" name="description">

  <!-- Favicons -->
  <link href="<%=request.getContextPath()%>/back-end/activity/img/favicon.png" rel="icon">
  <link href="<%=request.getContextPath()%>/back-end/activity/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700" rel="stylesheet">

  <!-- Bootstrap CSS File -->
  <link href="<%=request.getContextPath()%>/back-end/activity/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Libraries CSS Files -->
  <link href="<%=request.getContextPath()%>/back-end/activity/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/back-end/activity/lib/animate/animate.min.css" rel="stylesheet">

  <!-- Main Stylesheet File -->
  <link href="<%=request.getContextPath()%>/back-end/activity/css/style.css" rel="stylesheet">



  <!-- =======================================================
    Theme Name: Regna
    Theme URL: https://bootstrapmade.com/regna-bootstrap-onepage-template/
    Author: BootstrapMade.com
    License: https://bootstrapmade.com/license/
  ======================================================= -->
</head>

<body>
<%LinkedList errorMsgs=(LinkedList)request.getAttribute("errorMsgs");%>
    <!-- 錯誤列表 -->
    <c:if test="${not empty errorMsgs}"><ul class="list-group">
		  <li class="list-group-item active">Opps!錯誤訊息回報</li>
		  <c:forEach var="massage" items="${errorMsgs}">
		  <li class="list-group-item">${massage}</li>
		  </c:forEach>
		</ul>
	</c:if>
    <!--==========================
      Contact Section
    ============================-->
    <section id="contact">
	     <div class="container wow fadeInUp">
	      	<div class="col-lg-12 col-md-12">
		        <div class="section-header">
		          <h3 class="section-title">活動資訊管理 </h3>
		        </div>	
			</div>      
	     </div>
	     <div class="container wow fadeInUp">
	        <div class="row justify-content-center">
	          <div class="col-lg-12 col-md-12">
	          <!-- 查詢nav bar開始 -->
	          <nav class="navbar navbar-light bg-light">
				<ul class="nav nav-tabs">
				  <li class="nav-item">
				    <form class="form-inline" action="<%=request.getContextPath()%>/activity/Activ_servlet.html" method="post">
					   <input class="form-control mr-sm-2" name="activityID" type="search" placeholder="請輸入活動編號" aria-label="Search">
					   <!--隱藏的參數action讓controller抓-->
		              	<input type="hidden" name="action" value="GET_ONE">
					   <button class="btn btn-outline-success my-2 my-sm-0" type="submit">查詢一筆活動</button>
					</form>
				  <li class="nav-item">
				   <form class="form-inline" action="<%=request.getContextPath()%>/back-end/activity/listAllActivity.jsp" method="post">
					   <button class="btn btn-outline-success my-2 my-sm-0" type="submit">查詢全部活動</button>
					</form>
				  </li>
				  <li class="nav-item">
				   <form class="form-inline" action="<%=request.getContextPath()%>/back-end/activity/addActivity.jsp" method="post">
					   <button class="btn btn-outline-success my-2 my-sm-0" type="submit">新增活動</button>
					</form>
				  </li> 
				</ul>
			  </nav>
				<!-- 查詢nav bar結束 -->
	          </div>
	          <div class="col-lg-12 col-md-12">
		          
	          </div>
	          
	          <div class="col-lg-12 col-md-12">
	            	
	          </div>
	        </div><!-- row結尾 -->
	      </div>
    </section><!-- #contact -->
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
  <script src="<%=request.getContextPath()%>/back-end/activity/lib/jquery/jquery.min.js"></script>
  <script src="<%=request.getContextPath()%>/back-end/activity/lib/jquery/jquery-migrate.min.js"></script>
  <script src="<%=request.getContextPath()%>/back-end/activity/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="<%=request.getContextPath()%>/back-end/activity/lib/easing/easing.min.js"></script>
  <script src="<%=request.getContextPath()%>/back-end/activity/lib/wow/wow.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD8HeI8o-c1NppZA-92oYlXakhDPYR7XMY"></script>

  <script src="<%=request.getContextPath()%>/back-end/activity/lib/waypoints/waypoints.min.js"></script>
  <script src="<%=request.getContextPath()%>/back-end/activity/lib/counterup/counterup.min.js"></script>
  <script src="<%=request.getContextPath()%>/back-end/activity/lib/superfish/hoverIntent.js"></script>
  <script src="<%=request.getContextPath()%>/back-end/activity/lib/superfish/superfish.min.js"></script>

  <!-- Contact Form JavaScript File -->
  <script src="<%=request.getContextPath()%>/back-end/activity/contactform/contactform.js"></script>

  <!-- Template Main Javascript File -->
  <script src="<%=request.getContextPath()%>/back-end/activity/js/main.js"></script>

</body>
</html>
