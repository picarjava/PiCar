<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.activityToken.model.*" %>
<%@ page import="com.activity.model.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
  <jsp:include page="/front-end/HomeMember/HeadMember.jsp" />
  <jsp:include page="/front-end/HomeMember/HeadMemberToken.jsp" />
  <title>請輸入活動代碼</title> 
<style>
.navbar .nav {
   
    left: 50%;
    
   
}


	
</style>

<!-- 會員登入功能串接 ，將VOmemID指定給 memID-->
<%@ page import="com.member.model.MemberVO"%>
<%MemberVO memberVO=(MemberVO)session.getAttribute("memberVO");
String memID=memberVO.getMemID();
session.setAttribute("memID",memID);
%> 

 
</head>

<body>
<%ActivityVO activityVO=(ActivityVO)request.getAttribute("activityVO");%>
<%ActivityTokenVO activityTokenVO=(ActivityTokenVO)request.getAttribute("activityToken");%>
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
		          <h3 class="section-title">請輸入活動序號 </h3>
		          <jsp:useBean id="memberSvc" class="android.com.member.model.MemberService"/>
		          <p class="section-description">會員編號 ${memID}</p>
		        </div>	
			</div>      
	     </div>
	          <!-- 查詢nav bar開始 -->
	          <nav   class="navbar navbar-light bg-light justify-content-center">
	          <form class="form-inline" action="<%=request.getContextPath()%>/activityToken/ActivTokenServlet" method="post">
				<ul class="nav nav-tabs"  style="left:35%;">
					<li class="nav-item">
					<input style="height: 30px;" type="text" class="form-control mr-sm-2" name="activityCode" value="${activityVO.activityCode}" placeholder="請輸入活動序號" >
					</li>
				  	<li class="nav-item">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">立即領取活動代幣</button>
					</li>
				  </ul>
				  <!--隱藏的參數action讓controller抓-->
				   <input type="hidden" name="action" value="INSERT_F0R_GET_ONES_ALL">
				   <input type="hidden" name="memID" value="${memID}">
				</form>
			  </nav>
		 </section>
				<!-- 查詢nav bar結束 -->
				<jsp:include page="/front-end/activityToken/listOnesAllActivityToken.jsp"/>
				<!--==========================
			    Footer
			  ============================-->
			  <footer id="footer">
			    <div class="footer-top">
			      <div class="container">
			
			      </div>
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
			  </footer><!-- #footer -->
  <!--==========================
    底部
  ============================-->
  
</body>
</html>
