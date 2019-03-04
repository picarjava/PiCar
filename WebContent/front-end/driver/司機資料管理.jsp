<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.driver.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>司機資料管理</title>
<jsp:include page="/regna-master/head.jsp" />

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
	<section id="contact">
		<div class="container wow fadeInUp">
			<div class="section-header">
				<h3 class="section-title">司機資料管理(前端_已成為司機的)</h3>
				<p class="section-description">請查看與修改資料</p>
			</div>
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
						<form method="post"
							action="<%=request.getContextPath()%>/driver/driver.do"
							role="form" class="contactForm" style="margin-bottom: 0px;">
							
							<div align="center">個人資料</div><br>
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
							<!-- 
		cartype 
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

							<div align="center">喜好設定</div><br>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<p class="input-group-text" id="basic-addon1">願意共乘載客</p>
				                <select name="sharedCar" class="form-group">
									<option value="0">無</option>
									<option value="1">有</option>
								</select>
								</div>
								<input type="text" class="form-control" placeholder="3"
									name="sharedCar" value="${driverVO.sharedCar}"
									aria-label="Username" aria-describedby="basic-addon1" readonly>
							</div>
							
			<!-- 					<div class="form-group"> -->
<!-- 								<p>願意共乘載客</p> -->

<!-- 							</div> -->
							
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

<!-- 							<div class="form-group"> -->
<!-- 								<p>可載寵物</p> -->
<!-- 								<select name="pet" class="form-control"> -->
<!-- 									<option value="0">無法</option> -->
<!-- 									<option value="1">接受</option> -->
<!-- 								</select> -->
<!-- 							</div> -->

<!-- 							<div class="form-group"> -->
<!-- 								<p>抽菸</p> -->
<!-- 								<select name="smoke" class="form-control"> -->
<!-- 									<option value="0">無</option> -->
<!-- 									<option value="1">有</option> -->
<!-- 								</select> -->
<!-- 							</div> -->

<!-- 							<div class="form-group"> -->
<!-- 								<p>提供嬰兒座椅</p> -->
<!-- 								<select name="babySeat" class="form-control"> -->
<!-- 									<option value="0">無</option> -->
<!-- 									<option value="1">有</option> -->
<!-- 								</select> -->
<!-- 							</div> -->
							<div align="center">違規狀態</div><br>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1">接單狀態碼(被ban後台)</span>
								</div>
<!-- 								<input type="text" class="form-control" placeholder="1" -->
<!-- 									aria-label="Username" aria-describedby="basic-addon1" readonly> -->
								<font color="pink"><c:out value="${driverVO.Banned}" default="表現不錯，沒被BAN" /></font>
							</div>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1">到期日(被ban後台)</span>
								</div>
								<font color="pink"><c:out value="${driverVO.Deadline}" default="表現不錯，繼續開車" /></font>
							</div>
							<div class="text-center">
								<button type="submit" class="btn btn-block ">確認修改</button>
								<%-- 	<input type="hidden" name="msgID"  value="${brodVO.msgID}"> --%>
							<input type="hidden" name="action" value="UPDATE_DRI"/>
							</div>
							<!-- /*放隱藏的標籤，讓Controller抓到參數進行操作*/ -->
                            <button type="button" class="btn btn-block ">返回PICAR首頁</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- #contact -->

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

	<jsp:include page="/regna-master/body.jsp" />
</body>
</html>
