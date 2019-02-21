<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.activity.model.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
  <meta charset="utf-8">
  <title>Regna Bootstrap Template</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="" name="keywords">
  <meta content="" name="description">

  <!-- Favicons -->
  <link href="img/favicon.png" rel="icon">
  <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700" rel="stylesheet">

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
    <!--==========================
      Contact Section
    ============================-->
    <!-- 先取出VO -->
  <%ActivityVO activityVO=(ActivityVO)request.getAttribute("activityVO");%>
   
    <!-- 錯誤列表 -->
    <%LinkedList errorMsgs=(LinkedList<String>)request.getAttribute("errorMsgs");%>
    <c:if test="${not empty errorMsgs}"><ul class="list-group">
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
		          <h3 class="section-title">請查看單筆活動資訊</h3>
		          <div class="text-center">
		          <form action="homeActivity.jsp">
		          <button type="submit" >返回</button>
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
	              <form action="<%=request.getContextPath()%>/activity/Activ_servlet.html" method="post" role="form" class="contactForm">
	                <div class="form-group">
	                  <p>活動編號</p> 
	                 <input type="text" name="activityID"  class="form-control" readonly value="${activityVO.activityID}" />
	  				</div>
	                <div class="form-group">
	                   <p>活動名稱</p>
	                  <input type="text" class="form-control" name="activityName" readonly value="${activityVO.activityName}" />
	       			</div>
	       			<div class="form-group">
	                  <p>活動資訊</p>
	                  <textarea class="form-control"  name="activityInfo" rows="5" readonly>${activityVO.activityInfo}</textarea>
	                </div>
	                <div class="form-group">
	                  <p>活動開始時間</p>
	                  <input type="date" class="form-control" name="activityStart" readonly value="${activityVO.activityStart}"  />
	                </div>
	                <div class="form-group">
	                  <p>活動結束時間</p>
	                  <input type="date" class="form-control" name="activityEnd" readonly value="${activityVO.activityEnd}" />
	                </div>
	                <div class="form-group">
	                  <p>活動序號</p>
	                  <input type="text" class="form-control" name="activityCode" readonly value="${activityVO.activityCode}"  />
	                </div>
	                <div class="form-group">
	                  <p>活動代幣</p>
	                  <input type="text" class="form-control" name="tokenAmount" readonly value="${activityVO.tokenAmount}"  />
	                </div>
	                <div class="form-group">
	                  <p>活動海報</p> <!-- EL回傳空字串 -->
	                  <input type="file" class="form-control" name="activityPost" readonly value="{activityVO.activityPost}" />
		               <img src='<%=request.getContextPath()%>/activity/Activ_servlet.html?activityID=${activityVO.activityID}' width='200' height='100' alt='"這是"+${activityVO.activityID}+"的活動海報" '  />
		            </div>
	              <!--  <div class="text-center"><button type="submit">確認修改</button></div>  -->
	              	<!--隱藏的參數action讓controller抓-->
	              	<!-- <input type="hidden" name="action" value="UPTDATE"> -->
	              </form>
	            </div>
	          </div>
	        </div><!-- row結尾 -->
	      </div>
    </section><!-- #contact -->
  <!--==========================
    底部
  ============================-->
 
  <a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>

  <!-- JavaScript Libraries -->
  <script src="lib/jquery/jquery.min.js"></script>
  <script src="lib/jquery/jquery-migrate.min.js"></script>
  <script src="lib/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="lib/easing/easing.min.js"></script>
  <script src="lib/wow/wow.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD8HeI8o-c1NppZA-92oYlXakhDPYR7XMY"></script>

  <script src="lib/waypoints/waypoints.min.js"></script>
  <script src="lib/counterup/counterup.min.js"></script>
  <script src="lib/superfish/hoverIntent.js"></script>
  <script src="lib/superfish/superfish.min.js"></script>

  <!-- Contact Form JavaScript File 
  <script src="contactform/contactform.js"></script>-->

  <!-- Template Main Javascript File -->
  <script src="js/main.js"></script>

</body>
</html>
