<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.activity.model.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
  <title>活動資訊管理</title>
  <jsp:include page="/back-end/head_back.jsp" />
</head>

<body>
    <div class="wrapper ">
        <jsp:include page="/back-end/kidBodyLeft.jsp" />
        <div class="main-panel">
                        <div class="content">
                <div class="container-fluid">
                    <!-- your content here -->
                    <div class="container-fluid">
                        <div class="col-9">
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
					   <button type="submit">查詢一筆活動</button>
					</form>
				  <li class="nav-item">
				   <form class="form-inline" action="<%=request.getContextPath()%>/back-end/activity/listAllActivity.jsp" method="post">
					   <button type="submit">查詢全部活動</button>
					</form>
				  </li>
				  <li class="nav-item">
				   <form class="form-inline" action="<%=request.getContextPath()%>/back-end/activity/addActivity.jsp" method="post">
					   <button type="submit">新增活動</button>
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
</div>
                    </div>
                </div>
                <jsp:include page="/back-end/kidFooter.jsp" />
            </div>
        </div>
    </div>  
</body>
</html>
