<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.activity.model.*" %>
<%@ page import="java.util.*" %>

<html>
<head>
  <meta charset="UTF-8">  
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
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

<%ActivityVO activityVO=(ActivityVO)request.getAttribute("activityVO");%>

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
		          <h3 class="section-title">請新增一筆活動資訊</h3>
		          <div class="text-center">
		          <form action="homeActivity.jsp">
		          <button type="submit"  class="btn btn-outline-success"">返回</button>
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
	              <form action="<%=request.getContextPath()%>/activity/Activ_servlet.html" method="post"  enctype="multipart/form-data">
	                
	                <div class="form-group">
	                   <p>活動名稱</p>
	                  <input type="text" name="activityName" class="form-control" value="${activityVO.activityName}"   placeholder="請輸入活動名稱" />
	       			</div>
	       			<div class="form-group">
	                  <p>活動資訊</p>
	                  <textarea class="form-control" name="activityInfo"  placeholder="請輸入活動資訊">${activityVO.activityInfo}</textarea>
	                </div>
	                <div class="form-group">
	                  <p>活動開始時間</p>
	                  <input id="start_date" type="text" class="form-control" name="activityStart" value="${activityVO.activityStart}" placeholder="請輸入活動開始時間"  />
	                </div>
	                <div class="form-group">
	                  <p>活動結束時間</p>
	                  <input id="end_date" type="text" class="form-control" name="activityEnd" value="${activityVO.activityEnd}" placeholder="請輸入活動結束時間"  />
	                </div>
	                <div class="form-group">
	                  <p>活動序號</p>
	                  <input type="text" class="form-control" name="activityCode" value="${activityVO.activityCode}" placeholder="請輸入活動序號"  />
	                </div>
	                <div class="form-group">
	                  <p>活動代幣</p>
	                  <input type="text" class="form-control" name="tokenAmount" value="${activityVO.tokenAmount}" placeholder="請輸入本活動代幣數量"  />
	                </div>
	                <div class="form-group">
	                  <p>活動海報</p>
	                  <input type="file" class="form-control" name="activityPost" value="${activityVO.activityPost}">
	                </div>
	                <div class="text-center">
	                <button type="submit" class="btn btn-dark">新增活動</button>
	                </div>
	                <!--隱藏的參數action讓controller抓-->
	              	<input type="hidden" name="action" value="INSERT">
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
  <script src="contactform/contactform.js"></script> -->

  <!-- Template Main Javascript File  -->
  <script src="js/main.js"></script>
  
  <!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<!-- 參考網站: https://xdsoft.net/jqplugins/datetimepicker/ -->
<link   rel="stylesheet" type="text/css" href="datetimepicker/jquery.datetimepicker.css" />
<script src="datetimepicker/jquery.js"></script>
<script src="datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<!-- 參考網站: https://xdsoft.net/jqplugins/datetimepicker/ -->
<link   rel="stylesheet" type="text/css" href="datetimepicker/jquery.datetimepicker.css" />
<script src="datetimepicker/jquery.js"></script>
<script src="datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	 $('#start_date').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#end_date').val()?$('#end_date').val():false
	   })
	  },
	  timepicker:false
	 });
	 
	 $('#end_date').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#start_date').val()?$('#start_date').val():false
	   })
	  },
	  timepicker:false
	 });
});

</script>
  
  

</body>
</html>
