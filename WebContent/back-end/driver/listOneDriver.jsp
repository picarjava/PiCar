<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.driver.model.*"%>
<%@ page import="java.util.*"%>
<html>
<head>
<meta charset="UTF-8">
<title>driver searching for admin</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<!-- Favicons -->
<link href="<%=request.getContextPath()%>/regna-master/img/favicon.png"
	rel="icon">
<link
	href="<%=request.getContextPath()%>/regna-master/img/apple-touch-icon.png"
	rel="apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700"
	rel="stylesheet">

<!-- Bootstrap CSS File -->
<link
	href="<%=request.getContextPath()%>/regna-master/lib/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Libraries CSS Files -->
<link
	href="<%=request.getContextPath()%>/regna-master/lib/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/regna-master/lib/animate/animate.min.css"
	rel="stylesheet">

<!-- Main Stylesheet File -->
<link href="<%=request.getContextPath()%>/regna-master/css/style.css"
	rel="stylesheet">

<!-- =======================================================
    Theme Name: Regna
    Theme URL: https://bootstrapmade.com/regna-bootstrap-onepage-template/
    Author: BootstrapMade.com
    License: https://bootstrapmade.com/license/
  ======================================================= -->
</head>
<body>
	<!--==========================
      Contact Section
    ============================-->
	<!-- 先取出VO -->
	<%DriverVO driverVO=(DriverVO)request.getAttribute("driverVO");%>

	<!-- 錯誤列表 -->
	<%LinkedList errorMsgs=(LinkedList<String>)request.getAttribute("errorMsgs");%>
	<c:if test="${not empty errorMsgs}">
		<ul class="list-group">
			<li class="list-group-item active">Opps!錯誤訊息回報</li>
			<c:forEach var="massage" items="${errorMsgs}">
				<li class="list-group-item">${massage}</li>
			</c:forEach>
		</ul>
	</c:if>
	<section id="contact">
		<div class="container wow fadeInUp">
			<div class="col-lg-12 col-md-12">
				<div class="section-header">
					<h3 class="section-title">司機資料查詢</h3>
					<div class="text-center">
						<form action="司機會員管理.jsp"><!-- 設返回頁面 -->
							<button type="submit">返回</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="container wow fadeInUp">
			<div class="row justify-content-center">
				<div class="col-lg-12 col-md-12">
					<div class="form">
						<!-- 活動表單開始 -->
						<form action="driver.do" method="post" role="form"	class="contactForm">
							<!-- <form action="<.%=request.getContextPath()%.>/activity/Activ_servlet.html" method="post" role="form" class="contactForm"> -->

							<div class="form-group">
								<p>會員編號</p>
								<input type="text" name="memID" readonly
									value="${driverVO.memID}" class="form-control" />
							</div>
							<div class="form-group">
								<p>車牌號碼</p>
								<input type="text" name="plateNum" class="form-control"
									value="${driverVO.plateNum}" readonly placeholder="請輸入車牌號碼" />
								<div class="card" style="width: 18rem;"></div>
							</div>
							<div class="form-group">
								<p>車型</p>
								<input type="text" name="carType" class="form-control"
									value="${driverVO.carType}" placeholder="請輸入車型" />
								<div class="card" style="width: 18rem;"></div>
							</div>
							<div class="form-group">
								<p>駕照</p>
								<div class="card" style="width: 18rem;">
									<img src="driver.do?driverID=D003&pic=1" width="300"
										height="150" class="card-img-top" alt="...">
								</div>
							</div>
							<div class="form-group">
								<p>良民證</p>
								<div class="card" style="width: 18rem;">
									<img src="driver.do?driverID=D003&pic=2" width="300"
										height="150" class="card-img-top" alt="...">
								</div>
							</div>

							<div class="form-group">
								<p>肇事紀錄</p>
								<div class="card" style="width: 18rem;">
									<img src="driver.do?driverID=D003&pic=3" width="300"
										height="150" class="card-img-top" alt="...">
								</div>
							</div>
							<div class="form-group">
								<p>身分證</p>
								<div class="card" style="width: 18rem;">
									<img src="driver.do?driverID=D003&pic=4" width="300"
										height="150" class="card-img-top" alt="...">
								</div>
							</div>

							<div class="form-group">
								<p>大頭照</p>
								<div class="card" style="width: 18rem;">
									<img src="driver.do?driverID=D003&pic=5" width="300"
										height="150" class="card-img-top" alt="...">
								</div>
							</div>

							<div class="form-group">
								<p>願意共乘載客</p>
								<input type="text" name="sharedCar" readonly
									value="${driverVO.sharedCar}" class="form-control" />
							</div>

							<div class="form-group">
								<p>可載寵物</p>
								<input type="text" name="pet" readonly value="${driverVO.pet}"
									class="form-control" />
							</div>

							<div class="form-group">
								<p>抽菸</p>
								<input type="text" name="smoke" readonly
									value="${driverVO.smoke}" class="form-control" />
							</div>

							<div class="form-group">
								<p>提供嬰兒座椅</p>
								<input type="text" name="babySeat" readonly
									value="${driverVO.babySeat}" class="form-control" />
							</div>

							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1">審核狀態(驗證用)</span>
								</div>
								<input type="text" class="form-control" placeholder="1"
									aria-label="Username" aria-describedby="basic-addon1" readonly>
							</div>

							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1">在線狀態(後台看的)</span>
								</div>
								<input type="text" class="form-control" placeholder="RAS-9958"
									aria-label="Username" aria-describedby="basic-addon1" readonly>
							</div>
							<!--  <div class="text-center"><button type="submit">確認修改</button></div>  -->
							<!--隱藏的參數action讓controller抓-->
							<!-- <input type="hidden" name="action" value="UPTDATE"> -->
						</form>
					</div>
				</div>
			</div>
			<!-- row結尾 -->
		</div>
	</section>
	<!-- #contact -->
	<!--==========================
    底部
  ============================-->
	<footer id="footer">
		<div class="footer-top">
			<div class="container"></div>
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
	</footer>
	<!-- #footer -->
	============================-->

	<a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>

	<!-- JavaScript Libraries -->
	<script
		src="<%=request.getContextPath()%>/regna-master/lib/jquery/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/regna-master/lib/jquery/jquery-migrate.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/regna-master/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/regna-master/lib/easing/easing.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/regna-master/lib/wow/wow.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD8HeI8o-c1NppZA-92oYlXakhDPYR7XMY"></script>

	<script src="<%=request.getContextPath()%>/regna-master/lib/waypoints/waypoints.min.js"></script>
	<script src="<%=request.getContextPath()%>/regna-master/lib/counterup/counterup.min.js"></script>
	<script src="<%=request.getContextPath()%>/regna-master/lib/superfish/hoverIntent.js"></script>
	<script src="<%=request.getContextPath()%>/regna-master/lib/superfish/superfish.min.js"></script>

	<!-- Contact Form JavaScript File -->
	<script
		src="<%=request.getContextPath()%>/regna-master/contactform/contactform.js"></script>

	<!-- Template Main Javascript File -->
	<script src="<%=request.getContextPath()%>/regna-master/js/main.js"></script>

</body>
</html>
