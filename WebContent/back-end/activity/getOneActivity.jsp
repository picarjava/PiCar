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
		          <form action="<%=request.getContextPath()%>/back-end/activity/homeActivity.jsp">
		          <button type="submit" class="btn btn-outline-success" >返回</button>
		          </form>
				  </div>		        
		        </div>	
			</div>      
	     </div>
	     <div class="container wow fadeInUp">
	        <div class="row justify-content-center">
	          <div class="col-lg-12 col-md-12">
	           <!-- 卡片開始 -->
		          <div class="card mb-3">
		             <div class="form-group">
			              <c:if test="${empty activityVO.activityPost}" var="condition">
			              <img class="card-img-top" src="<%=request.getContextPath()%>/regna-master/img/noFileUpdate.JPG" >
			              </c:if>
			              <c:if test="${not empty activityVO.activityPost}" var="condition">
			              <img class="card-img-top" src='<%=request.getContextPath()%>/activity/Activ_servlet.html?activityID=${activityVO.activityID}'  alt='"這是"+${activityVO.activityID}+"的活動海報"  '/>
			              </c:if>
			           </div>
					   <div class="card-body">
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
					  </div>
					</div>
					<!-- 卡片結束 -->
	              
	          </div><!-- col結尾 -->
	        </div><!-- row結尾 -->
	      </div><!-- container結尾 -->
    </section><!-- #contact -->
  <!--==========================
    底部
  ============================-->
 
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

  <!-- Contact Form JavaScript File 
  <script src="contactform/contactform.js"></script>-->

  <!-- Template Main Javascript File -->
  <script src="<%=request.getContextPath()%>/back-end/activity/js/main.js"></script>

</body>
</html>
