<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.driver.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>司機資料管理</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<!-- Favicons -->
<link href="img/favicon.png" rel="icon">
<link href="img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700"
	rel="stylesheet">

<!-- Bootstrap CSS File -->
<link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Libraries CSS Files -->
<link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="lib/animate/animate.min.css" rel="stylesheet">

<!-- Main Stylesheet File -->
<link href="css/style.css" rel="stylesheet">

<!-- =======================================================
    Theme Name: Regna
    Theme URL: https://bootstrapmade.com/regna-bootstrap-onepage-template/
    Author: BootstrapMade.com
    License: https://bootstrapmade.com/license/
  ======================================================= -->
</head>
<body>
	<!-- 先取出VO -->
	<%
		DriverVO driverVO = (DriverVO) request.getAttribute("driverVO");
	%>
	<!--==========================
      預約叫車
    ============================-->
	<section id="contact">
		<div class="container wow fadeInUp">
			<div class="section-header">
				<h3 class="section-title">司機資料管理(前端_已成為司機的)</h3>
				<p class="section-description">請查看與修改資料</p>
			</div>

			<button type="button" class="btn btn-dark ">返回</button>

		</div>

		<div class="container wow fadeInUp">
			<div class="row justify-content-center">
				<div class="col-lg-1 col-md-4">
					<div class="info"></div>
				</div>

				<div class="col-lg-9 col-md-8">
					<div class="form">
						<div id="sendmessage">Your message has been sent. Thank you!</div>
						<div id="errormessage"></div>
						<!-- 新增活動表單開始 -->
						<form method="post"
							action="<%=request.getContextPath()%>/driver/driver.do"
							role="form" class="contactForm" style="margin-bottom: 0px;">

							<div class="input-group mb-3 ">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1">會員編號</span>
								</div>
								<input type="text" class="form-control" placeholder="MEMID"
									aria-label="Username" aria-describedby="basic-addon1"
									disabled="disabled">
							</div>
							<div class="input-group mb-3 ">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1">司機編號</span>
								</div>
								<input type="text" class="form-control" placeholder="DRIVER1"
									aria-label="Username" aria-describedby="basic-addon1" readonly>
							</div>

							<div class="input-group mb-3 ">
								<div class="input-group-prepend ">
									<span class="input-group-text " id="basic-addon1">車牌號碼</span>
								</div>
								<input type="text" class="form-control" placeholder="RAS-9958"
									aria-label="Username" aria-describedby="basic-addon1" readonly>
							</div>

							<!-- 							cartype 
							licence
criminal
trafficre
idnum
photo-->
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1">評價分數</span>
								</div>
								<input type="text" class="form-control" placeholder="3"
									aria-label="Username" aria-describedby="basic-addon1" readonly>
							</div>

							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1">願意共乘載客</span>
								</div>
								<input type="text" class="form-control" placeholder="3"
									name="sharedCar" value="${driverVO.sharedCar}"
									aria-label="Username" aria-describedby="basic-addon1" readonly>
							</div>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1">可載寵物</span>
								</div>
								<input type="text" class="form-control" placeholder="3"
									name="pet" value="${driverVO.pet}" readonly
									aria-label="Username" aria-describedby="basic-addon1">
							</div>

							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1">抽菸</span>
								</div>
								<input type="text" class="form-control" placeholder="3"
									name="smoke" value="${driverVO.smoke}" readonly
									aria-label="Username" aria-describedby="basic-addon1">
							</div>

							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1">提供嬰兒座椅</span>
								</div>
								<input type="text" class="form-control" placeholder="3"
									name="babySeat" value="${driverVO.babySeat}" readonly
									aria-label="Username" aria-describedby="basic-addon1">
							</div>

							<!-- 							<div class="input-group mb-3"> -->
							<!-- 								<div class="input-group-prepend"> -->
							<!-- 									<span class="input-group-text" id="basic-addon1">審核狀態(驗證用)</span> -->
							<!-- 								</div> -->
							<!-- 								<input type="text" class="form-control" placeholder="1" -->
							<!-- 									aria-label="Username" aria-describedby="basic-addon1" readonly> -->
							<!-- 							</div> -->

							<!-- 							<div class="input-group mb-3"> -->
							<!-- 								<div class="input-group-prepend"> -->
							<!-- 									<span class="input-group-text" id="basic-addon1">在線狀態(後台看的)</span> -->
							<!-- 								</div> -->
							<!-- 								<input type="text" class="form-control" placeholder="RAS-9958" -->
							<!-- 									aria-label="Username" aria-describedby="basic-addon1" readonly> -->
							<!-- 							</div> -->

							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1">接單狀態碼(被ban後台)</span>
								</div>
								<input type="text" class="form-control" placeholder="1"
									aria-label="Username" aria-describedby="basic-addon1" readonly>
							</div>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1">到期日(被ban後台)</span>
								</div>
								<input type="text" class="form-control" placeholder="2018/01/02"
									aria-label="Username" aria-describedby="basic-addon1" readonly>
							</div>


							<div class="text-center">
								<button type="submit" class="btn btn-block ">確認修改</button>
								<%-- 								<input type="hidden" name="msgID"  value="${brodVO.msgID}"> --%>
							</div>
							<!-- /*放隱藏的標籤，讓Controller抓到參數進行操作*/ -->
							<input type="hidden" name="action" value="UPDATE">

						</form>

					</div>
				</div>

			</div>

		</div>
	</section>
	<!-- #contact -->

	</main>

	<!--==========================
    Footer
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

	<a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>

	<!-- JavaScript Libraries -->
	<script src="lib/jquery/jquery.min.js"></script>
	<script src="lib/jquery/jquery-migrate.min.js"></script>
	<script src="lib/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="lib/easing/easing.min.js"></script>
	<script src="lib/wow/wow.min.js"></script>
	<!-- <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD8HeI8o-c1NppZA-92oYlXakhDPYR7XMY"></script> -->

	<script src="lib/waypoints/waypoints.min.js"></script>
	<script src="lib/counterup/counterup.min.js"></script>
	<script src="lib/superfish/hoverIntent.js"></script>
	<script src="lib/superfish/superfish.min.js"></script>

	<!-- Contact Form JavaScript File -->
	<script src="contactform/contactform.js"></script>

	<!-- Template Main Javascript File -->
	<script src="js/main.js"></script>
</body>
</html>
