<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.activity.model.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
  <title>請查看單筆活動資訊</title>
    <jsp:include page="/regna-master/head.jsp" />
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
		          <h3 class="section-title">訪客查看單筆活動資訊</h3>
		          <div class="text-center">
		          <form action="<%=request.getContextPath()%>/regna-master/homeindex.jsp">
		          <button type="submit" class="btn btn-outline-success" >返回訪客首頁</button>
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
 <jsp:include page="/regna-master/body.jsp" />

</body>
</html>
