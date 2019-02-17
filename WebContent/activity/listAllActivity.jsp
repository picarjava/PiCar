<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.activity.model.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="utf-8">
    <title>12345679</title>
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
      活動listALL
    ============================-->
   <!-- getAll再setAttribute存進pageContext 給forEach抓 -->
    <% ActivityService activitySvc=new ActivityService();%>
    <%List<ActivityVO> list=activitySvc.getALL();%>
    <%request.setAttribute("list", list); %>
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
                </div>
            </div>
            <div class="container wow fadeInUp">
                        <table class="table">
						  <thead class="thead-dark">
						    <tr>
						      <th scope="col">活動ID	    </th>
						      <th scope="col">活動名稱	</th>
						      <th scope="col">開始時間	</th>
						      <th scope="col">結束時間	</th>
						      <th scope="col"  colspan="2">  
						      <Form METHOD="post" ACTION="addActivity.jsp" >
							    <div class="text-center"><button type="submit" class="btn btn-light">新增活動</button>
							    </div>
							  </Form>
						      </th>
						    </tr>
						  </thead>
						  <tbody>
	
	<c:forEach var="activityVO" items="${list}" >			  
					 		
					 		<tr>	 
						      <th scope="row">${activityVO.activityID}</th>
						      <td>${activityVO.activityName}</td>
						      <td>${activityVO.activityStart}</td>
						      <td>${activityVO.activityEnd}</td>
						      <td>
						      <Form METHOD="post" ACTION="<%=request.getContextPath()%>/Activ_servlet.html" >
							    <div class="text-center"><button type="submit" class="btn btn-light">修改</button>
							      	<!-- /*放隱藏的標籤，重複使用activityVO，讓Controller抓到參數進行操作*/ -->
	                				<input type="hidden" name="action" value="GET_ONE_FOR_UPTDATE">
	                				<input type="hidden" name="activityID" value="${activityVO.activityID}">
							     </div>
							  </Form>
						      </td>
						      <td>
						      <Form METHOD="post" ACTION="<%=request.getContextPath()%>/Activ_servlet.html" >
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
                    
            </div>
        </section>
  
    <%}%>
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