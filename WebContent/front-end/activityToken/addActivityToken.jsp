<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.activityToken.model.*" %>
<%@ page import="com.activity.model.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
  <meta charset="UTF-8"> 
  <title>Regna Bootstrap Template</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="" name="keywords">
  <meta content="" name="description">

  <!-- Favicons -->
  <link href="<%=request.getContextPath()%>/front-end/activityToken/img/favicon.png" rel="icon">
  <link href="<%=request.getContextPath()%>/front-end/activityToken/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700" rel="stylesheet">

  <!-- Bootstrap CSS File -->
  <link href="<%=request.getContextPath()%>/front-end/activityToken/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Libraries CSS Files -->
  <link href="<%=request.getContextPath()%>/front-end/activityToken/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/front-end/activityToken/lib/animate/animate.min.css" rel="stylesheet">

  <!-- Main Stylesheet File -->
  <link href="<%=request.getContextPath()%>/front-end/activityToken/css/style.css" rel="stylesheet">

	

  <!-- =======================================================
    Theme Name: Regna
    Theme URL: https://bootstrapmade.com/regna-bootstrap-onepage-template/
    Author: BootstrapMade.com
    License: https://bootstrapmade.com/license/
  ======================================================= -->
</head>

<body>
<%ActivityVO activityVO=(ActivityVO)request.getAttribute("activityVO");%>
<%ActivityTokenVO activityTokenVO=(ActivityTokenVO)request.getAttribute("activityToken");%>
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
		          <h3 class="section-title">請輸入活動代碼 </h3>
		        </div>	
			</div>      
	     </div>
	          <!-- 查詢nav bar開始 -->
	          <nav class="navbar navbar-light bg-light justify-content-center" >
	          <form class="form-inline" action="<%=request.getContextPath()%>/activityToken/ActivTokenServlet" method="post">
				<ul class="nav nav-tabs">
					<li class="nav-item">
					<p>會員編號</p>
					</li>
					<li class="nav-item">
					<input type="text" class="form-control mr-sm-2" name="memID" value="${activityTokenVO.memID}" placeholder="請輸入會員編號" >
					</li>
					<li class="nav-item">
					<p>活動序號</p>
					</li>
					<li class="nav-item">
					<input type="text" class="form-control mr-sm-2" name="activityCode" value="${activityVO.activityCode}" placeholder="請輸入活動序號" >
					</li>
				  	<li class="nav-item">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">立即領取活動代幣</button>
					</li>
				  </ul>
				  <!--隱藏的參數action讓controller抓-->
				   <input type="hidden" name="action" value="INSERT_F0R_GET_ONES_ALL">
				</form>
				
			  </nav>
		 </section>
				<!-- 查詢nav bar結束 -->
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
  <!--==========================
    底部
  ============================-->
  
  <a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>

  <!-- JavaScript Libraries -->
  <script src="<%=request.getContextPath()%>/front-end/activityToken/lib/jquery/jquery.min.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/activityToken/lib/jquery/jquery-migrate.min.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/activityToken/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/activityToken/lib/easing/easing.min.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/activityToken/lib/wow/wow.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD8HeI8o-c1NppZA-92oYlXakhDPYR7XMY"></script>

  <script src="<%=request.getContextPath()%>/front-end/activityToken/lib/waypoints/waypoints.min.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/activityToken/lib/counterup/counterup.min.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/activityToken/lib/superfish/hoverIntent.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/activityToken/lib/superfish/superfish.min.js"></script>

 

  <!-- Template Main Javascript File  -->
  <script src="js/main.js"></script>
  
</body>
</html>
