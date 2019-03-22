<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.activityToken.model.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <title>後台查詢活動代幣明細</title>
     <jsp:include page="/regna-master/head.jsp" />
     <jsp:include page="/back-end/head_back.jsp" />
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
    
    <jsp:include page="/back-end/kidBodyLeft.jsp" />
        <section id="contact">
            <div class="container wow fadeInUp">
                <div class="section-header">
                    <h3 class="section-title">後台查詢活動代幣領取明細</h3>
                    
                    <form action="<%=request.getContextPath()%>/back-end/activity/listAllActivity.jsp">
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
    
  <jsp:include page="/regna-master/body.jsp" />
   <jsp:include page="/back-end/kidFooter.jsp" />
</body>

</html>