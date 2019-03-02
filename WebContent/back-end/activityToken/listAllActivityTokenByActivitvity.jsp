<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.activityToken.model.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <title>後台查詢活動代幣明細</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">
    <!-- Favicons -->
    <link href="<%=request.getContextPath()%>/front-end/activityToken/img/favicon.png" rel="icon">
    <link href="<%=request.getContextPath()%>/front-end/activityToken/img/apple-touch-icon.png" rel="apple-touch-icon">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700" rel="stylesheet">
    <!-- Bootstrap CSS File -->
    <link href="<%=request.getContextPath()%>/front-end/activityToken/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Libraries CSS Files -->
    <link href="<%=request.getContextPath()%>/front-end/activityToken/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/front-end/activityToken/lib/animate/animate.min.css" rel="stylesheet">
    <!-- Main Stylesheet File -->
    <link href="<%=request.getContextPath()%>/front-end/activityToken/css/style.css" rel="stylesheet">
   

    
    <!-- =======================================================
    Theme Name: Regna
    Theme URL: https://bootstrapmade.com/regna-bootstrap-onepage-template/
    Author: BootstrapMade.com
    License: https://bootstrapmade.com/license/
  ======================================================= -->
</head>

<body>
 	<!-- 錯誤列表 -->
    <%List<String> errorMsgs=(List<String>)request.getAttribute("errorMsgs");%>
    <c:if test="${not empty errorMsgs}">
       <ul class="list-group">
		  <li class="list-group-item active">Opps!錯誤訊息回報</li>
		  <c:forEach var="massage" items="${errorMsgs}">
		  <li class="list-group-item">${massage}</li>
		  </c:forEach>
		</ul>
	</c:if>

    <!--==========================
     listOnesALL
    ============================-->
    <%List<ActivityTokenVO> activityTokenlist=(List<ActivityTokenVO>)request.getAttribute("activityTokenlist");%>
    <% String activityID=(String)request.getAttribute("activityID");%>
    <jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
    
    
        <section id="contact">
            <div class="container wow fadeInUp">
                <div class="section-header">
                    <h3 class="section-title">後台查詢活動代幣領取明細</h3>
                    <form action="<%=request.getContextPath()%>/front-end/activityToken/listAllActivity.jsp">
			          <div class="text-center"><button type="submit" class="btn btn-outline-success">返回</button></div>
			         </form>
                </div>
                
            </div>
            <div class="container wow fadeInUp">
                        <table class="table">
						  <thead class="thead-dark">
						    <tr>
						      <th scope="col">會員編號	</th>
						      <th scope="col">會員姓名	</th>
						      <th scope="col">活動編號	</th>
						      <th scope="col">代幣數量	</th>
						      <th scope="col">使用期限	</th>
						      <th scope="col" colspan="3"></th>
						    </tr>
						  </thead>
						  <tbody>
	<% int count=0;%>
	 
	<c:forEach var="activityTokenVO" items="${activityTokenlist}"  >	
	<c:if test="${activityTokenVO.activityID==activityID}">
					 		<tr>	 
						      <th scope="row">${activityTokenVO.memID}</th>
						      <td>${memberSvc.getOneMember(activityTokenVO.memID).name}</td>
						      <td>${activityTokenVO.activityID}</td>
						      <td>${activityTokenVO.tokenAmount}</td>
						      <td>${activityTokenVO.deadline}</td>
						    </tr>
						    <% count++;%>
	  </c:if>
	 </c:forEach>
	
	 						<tr>
	 						<td colspan="4" align="left">本活動領取代幣會員共:　<%=count %>　位</td>
	 						</tr>
 						</tbody>
						</table>
						
            </div>
        </section>
   	
  
    
   
    <!--==========================
    底部
  ============================-->
    
    <a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>
    <!-- JavaScript Libraries -->
    <script src="<%=request.getContextPath()%>/front-end/activityToken/lib/jquery/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/activityToken/lib/jquery/jquery-migrate.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/activityToken/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/activityToken/lib/easing/easing.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/activityToken/lib/wow/wow.min.js"></script>
    <!-- <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD8HeI8o-c1NppZA-92oYlXakhDPYR7XMY"></script> -->
    <script src="<%=request.getContextPath()%>/front-end/activityToken/lib/waypoints/waypoints.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/activityToken/lib/counterup/counterup.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/activityToken/lib/superfish/hoverIntent.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/activityToken/lib/superfish/superfish.min.js"></script>
    <!-- Contact Form JavaScript File -->
    <script src="<%=request.getContextPath()%>/front-end/activityToken/contactform/contactform.js"></script>
    <!-- Template Main Javascript File -->
    <script src="<%=request.getContextPath()%>/front-end/activityToken/js/main.js"></script>
</body>

</html>