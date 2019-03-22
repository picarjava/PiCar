<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.activity.model.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="zh">

<head>
    
    <title>查看最新消息</title>
    
<%--     <jsp:include page="/regna-master/head.jsp" /> --%>
    <jsp:include page="/front-end/HomeMember/HeadMember.jsp" />
    
</head>

<body>
    <!--==========================
      最新消息頁面
    ============================-->
   <!-- getAll再setAttribute存進pageContext 給forEach抓 -->
    <% ActivityService activitySvc=new ActivityService();%>
    <%List<ActivityVO> list=activitySvc.getALL();%>
    <%request.setAttribute("list", list); %>
    <%if(list!=null&&(list.size()>0)){ %>
    
    <!-- 錯誤列表 -->
    <%List<String> errorMsgs=(List<String>)request.getAttribute("errorMsgs");%>
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
                    <h3 class="section-title">查看最新消息</h3>
                    <form action="<%=request.getContextPath()%>/regna-master/homeindex.jsp">
			          <div class="text-center"><button type="submit" class="btn btn-outline-success">返回</button></div>
			         </form>
                </div>
             </div>
          	<div class="container wow fadeInUp">
            <%@ include file="page1.file" %>
        	<div class="row" >
		<c:forEach var="activityVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >			  
		 			<!-- 卡片開始 -->
					 		<div class="col-lg-12 col-md-6 wow fadeInUp" data-wow-delay="0.2s"> 
					 		<div class="card" style="width: 65rem;height:35rem ">
					 			  <c:if test="${empty activityVO.activityPost}" var="condition">
					              <img src="<%=request.getContextPath()%>/regna-master/img/noFileUpdate.JPG" class='card-img-top' width='300' height='350'>
					              </c:if>
					              <c:if test="${not empty activityVO.activityPost}" var="condition">
					              <img  src='<%=request.getContextPath()%>/activity/Activ_servlet.html?activityID=${activityVO.activityID}' class='card-img-top' width='400' height='350' alt='"這是"+${activityVO.activityID}+"的活動海報"  '/>
					              </c:if>
							  <div class="card-body">
							    <h5 class="card-title">${activityVO.activityName}</h5>
							    <p class="card-text">${activityVO.activityStart}~${activityVO.activityEnd} ${activityVO.activityInfo}</p>
							    <form action="<%=request.getContextPath()%>/activity/Activ_servlet.html" method="post">
							    <!--隱藏的參數action讓controller抓-->
	              				<input type="hidden" name="action" value="GET_ONE">
	              				<input type="hidden" name="activityID" value="${activityVO.activityID}">
	              				<button type="submit" class="btn btn-primary">查看詳情</button>
							    </form>
							  </div>
							  </div>
							  </div><!-- col -->
							 
							<!-- 卡片結束 -->
		</c:forEach>	
		</div>	<!-- row --> 				
 					 </div> <!-- container --> 	
						<%@ include file="page2.file" %>
           
        	</section>
  
    <%}%>
    <!--==========================
    底部
  ============================-->
    
<%--    <jsp:include page="/regna-master/body.jsp" /> --%>
</body>

</html>