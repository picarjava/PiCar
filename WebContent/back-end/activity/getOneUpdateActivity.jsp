<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.activity.model.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
  <title>請修改此筆活動資訊</title>
    <jsp:include page="/regna-master/head.jsp" />
    
<!-- datetimepicker -->
<link  rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
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
    <!--==========================
      Contact Section
    ============================-->
    <!-- 先取出VO -->
  <%ActivityVO activityVO=(ActivityVO)request.getAttribute("activityVO");%>
  
  <!-- 錯誤列表 -->
    <%LinkedList<String> errorMsgs=(LinkedList<String>)request.getAttribute("errorMsgs");%>
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
		          <h3 class="section-title">請修改此筆活動資訊</h3>
		          <div class="text-center">
		          <form action="<%=request.getContextPath()%>/back-end/activity/listAllActivity.jsp">
		          <button type="submit" class="btn btn-outline-success">返回</button>
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
	              <form action="<%=request.getContextPath()%>/activity/Activ_servlet.html" method="post" enctype="multipart/form-data">
	                <div class="form-group">
	                  <p>活動編號</p> 
	                 <input type="text" name="activityID" readonly class="form-control"  value="${activityVO.activityID}"  />
	  				</div>
	                <div class="form-group">
	                   <p>活動名稱</p>
	                  <input type="text" class="form-control" name="activityName"  value="${activityVO.activityName}"   />
	       			</div>
	       			<div class="form-group">
	                  <p>活動資訊</p>
	                  <textarea class="form-control" name="activityInfo" rows="5">${activityVO.activityInfo}</textarea>
	                </div>
	                <div class="form-group">
	                  <p>活動開始時間</p>
	                  <input id="start_date" type="text" class="form-control" name="activityStart" value="${activityVO.activityStart}"  />
	                </div>
	                <div class="form-group">
	                  <p>活動結束時間</p>
	                  <input id="end_date" type="text" class="form-control" name="activityEnd"  value="${activityVO.activityEnd}" />
	                </div>
	                <div class="form-group">
	                  <p>活動序號</p>
	                  <input type="text" class="form-control" name="activityCode" value="${activityVO.activityCode}"  />
	                </div>
	                <div class="form-group">
	                  <p>活動代幣</p>
	                  <input type="text" class="form-control" name="tokenAmount" value="${activityVO.tokenAmount}"  />
	                </div>
	                <div class="form-group">
	                  <p>活動海報</p> <!-- EL回傳空字串 -->
	                  <input type="file" class="form-control" name="activityPost" />
		               <c:if test="${empty activityVO.activityPost}" var="condition">
		              <img src="<%=request.getContextPath()%>/regna-master/img/${activityVO.activityID eq ('AC001'or'AC002'or'AC003'or'AC004'or'AC005')?activityVO.activityID:noFileUpdate}.JPG" width='200' height='100'>
		              </c:if>
		              <c:if test="${not empty activityVO.activityPost}" var="condition">
		              <img  src='<%=request.getContextPath()%>/activity/Activ_servlet.html?activityID=${activityVO.activityID}' width='200' height='100' alt='"這是"+${activityVO.activityID}+"的活動海報"  '/>
		              </c:if>
		      
		            </div>
	                <div class="text-center"><button type="submit" >確認修改</button></div>
	              	<!--隱藏的參數action讓controller抓-->
	              	<input type="hidden" name="action" value="UPDATE">
	              </form>
	            </div>
	          </div>
	        </div><!-- row結尾 -->
	      </div>
    </section><!-- #contact -->
  <!--==========================
    底部
  ============================-->
 	
  
 <jsp:include page="/regna-master/body.jsp" />
  
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
