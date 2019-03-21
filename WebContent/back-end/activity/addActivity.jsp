<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.activity.model.*" %>

<%@ page import="java.util.*" %>

<html>
<head>
  <title>後台新增活動資訊</title>

    <jsp:include page="/back-end/kidHead.jsp" />
	
	  <!-- datetimepicker  -->
	<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
	
	
	<style>
	  .xdsoft_datetimepicker .xdsoft_datepicker {
	           width:  300px;   /* width:  300px; */
	  }
	  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	           height: 151px;   /* height:  151px; */
	  }
	</style>


  <!-- =======================================================
    Theme Name: Regna
    Theme URL: https://bootstrapmade.com/regna-bootstrap-onepage-template/
    Author: BootstrapMade.com
    License: https://bootstrapmade.com/license/
  ======================================================= -->
</head>

<body>


<%ActivityVO activityVO=(ActivityVO)request.getAttribute("activityVO");%>
<%LinkedList<String> errorMsgs=(LinkedList<String>)request.getAttribute("errorMsgs");%>
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
<div class="wrapper ">
    <jsp:include page="/back-end/kidBodyLeft.jsp" />
        <div class="main-panel">
            <jsp:include page="/back-end/kidNavbar.jsp" />
<section id="contact">
  <div class="container wow fadeInUp">
	  <div class="row">
	  <div class="col-3 col-lg-9">
	 
	  </div>
	  <div class="col-9 col-lg-9">
		        <div class="section-header">
		          <h3 class="section-title">請新增一筆活動資訊</h3>
		          <div class="text-center">
		          <form action="<%=request.getContextPath()%>/back-end/activity/homeActivity.jsp">
		          <button type="submit"  class="btn btn-outline-success">返回</button>
		          </form>
				  </div>		        
		        </div>	
	   </div>  
	   <div class="col-9 col-lg-9 ">
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
	   </div> 
	</div>    
</section>
</div>
</div>
  <!--==========================
    底部
  ============================-->
  
  <jsp:include page="/back-end/kidFooter.jsp" />
  

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<!-- 參考網站: https://xdsoft.net/jqplugins/datetimepicker/ -->

<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
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
