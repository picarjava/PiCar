<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.activityToken.model.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <title>查詢活動代幣明細</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">
    <!-- Favicons -->
    <link href="<%=request.getContextPath()%>/front-end/activity/img/favicon.png" rel="icon">
    <link href="<%=request.getContextPath()%>/front-end/activity/img/apple-touch-icon.png" rel="apple-touch-icon">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700" rel="stylesheet">
    <!-- Bootstrap CSS File -->
    <link href="<%=request.getContextPath()%>/front-end/activity/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Libraries CSS Files -->
    <link href="<%=request.getContextPath()%>/front-end/activity/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/front-end/activity/lib/animate/animate.min.css" rel="stylesheet">
    <!-- Main Stylesheet File -->
    <link href="<%=request.getContextPath()%>/front-end/activity/css/style.css" rel="stylesheet">
   

    
    <!-- =======================================================
    Theme Name: Regna
    Theme URL: https://bootstrapmade.com/regna-bootstrap-onepage-template/
    Author: BootstrapMade.com
    License: https://bootstrapmade.com/license/
  ======================================================= -->
</head>

<body>
    <!--==========================
      活動listALL
    ============================-->
   <!-- getAll再setAttribute存進pageContext 給forEach抓 -->
    <% ActivityTokenService activityTokenSvc=new ActivityTokenService();%>
    <%List<ActivityTokenVO> list=activityTokenSvc.getAll();%>
    
    <%if(list!=null&&(list.size()>0)){ %>
    
    <%LinkedList errorMsgs=(LinkedList<String>)request.getAttribute("errorMsgs");%>
    <!-- 錯誤列表 -->
    
    <c:if test="${not empty errorMsgs}"><ul class="list-group">
		  <li class="list-group-item active">Opps!錯誤訊息回報</li>
		  <c:forEach var="massage" items="${errorMsgs}">
		  <li class="list-group-item">${massage}</li>
		  </c:forEach>
		</ul>
	</c:if>
   
 
        <section id="contact">
            <div class="container wow fadeInUp">
                <div class="section-header">
                    <h3 class="section-title">所有活動列表</h3>
                     
                    <form action="">
			          <div class="text-center"><button type="submit" class="btn btn-outline-success">返回</button>
			         </form>
                </div>
            </div>
            <div class="container wow fadeInUp">
                        <table class="table">
						  <thead class="thead-dark">
						  	
						    <tr>
						      <th scope="col">活動名稱	</th>
						      <th scope="col">開始時間	</th>
						      <th scope="col">結束時間	</th>
						      <th scope="col">活動海報	</th>
						      <th scope="col" colspan="3"><%@ include file="page1.file" %></th>
						    </tr>
						  </thead>
						  <tbody>
	
	<c:forEach var="activityVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >			  
		 	
					 		<tr>	 
						      <th scope="row">${activityVO.activityID}</th>
						      <td>${activityVO.activityName}</td>
						      <td>${activityVO.activityStart}</td>
						      <td>${activityVO.activityEnd}</td>
						      <td>
						       	  <c:if test="${empty activityVO.activityPost}" var="condition">
					              <img src="<%=request.getContextPath()%>/activity/img/noFileUpdate.JPG" width='200' height='100'>
					              </c:if>
					              <c:if test="${not empty activityVO.activityPost}" var="condition">
					              <img  src='<%=request.getContextPath()%>/activity/Activ_servlet.html?activityID=${activityVO.activityID}' width='200' height='100' alt='"這是"+${activityVO.activityID}+"的活動海報"  '/>
					              </c:if>
						      </td>
						      <td>
						      <Form METHOD="post" ACTION="<%=request.getContextPath()%>/activity/Activ_servlet.html" >
							    <div class="text-center"><button type="submit" class="btn btn-light">修改</button>
							      	<!-- /*放隱藏的標籤，重複使用activityVO，讓Controller抓到參數進行操作*/ -->
	                				<input type="hidden" name="action" value="GET_ONE_FOR_UPTDATE">
	                				<input type="hidden" name="activityID" value="${activityVO.activityID}">
							     </div>
							  </Form>
						      </td>
						      <td>
						      <Form METHOD="post" ACTION="<%=request.getContextPath()%>/activity/Activ_servlet.html" >
							    <div class="text-center"><button type="submit" class="btn btn-light">刪除</button>
							      	<!-- /*放隱藏的標籤，讓Controller抓到參數進行操作*/ -->
	                				<input type="hidden" name="action" value="DELETE">
	                				<input type="hidden" name="activityID" value="${activityVO.activityID}">
							     </div>
							  </Form>
						      </td>
						    </tr>
	</c:forEach>	
							
 						</tbody>
						</table>
						<%@ include file="page2.file" %>
            </div>
        </section>
  
    <%}%>
    <!--==========================
    底部
  ============================-->
    
    <a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>
    <!-- JavaScript Libraries -->
    <script src="<%=request.getContextPath()%>/front-end/activity/lib/jquery/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/activity/lib/jquery/jquery-migrate.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/activity/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/activity/lib/easing/easing.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/activity/lib/wow/wow.min.js"></script>
    <!-- <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD8HeI8o-c1NppZA-92oYlXakhDPYR7XMY"></script> -->
    <script src="<%=request.getContextPath()%>/front-end/activity/lib/waypoints/waypoints.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/activity/lib/counterup/counterup.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/activity/lib/superfish/hoverIntent.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/activity/lib/superfish/superfish.min.js"></script>
    <!-- Contact Form JavaScript File -->
    <script src="<%=request.getContextPath()%>/front-end/activity/contactform/contactform.js"></script>
    <!-- Template Main Javascript File -->
    <script src="<%=request.getContextPath()%>/front-end/activity/js/main.js"></script>
</body>

</html>