<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.singleOrder.model.SingleOrderService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.Date"%>
<%@ page import="com.groupOrder.model.GroupOrderService"%>
<%@ page import="com.groupOrder.model.GroupOrderVO"%>

<!-- 本頁面待與登入功能串接 此處先指定memID-->
<%-- <%String memID=(String)session.getAttribute("memID"); %>  --%>
<%! String memID="M001";  %>
<% session.setAttribute("memID", memID);%>

<!-- 揪團訂單 -->
<% GroupOrderService groupOrderSvc=new GroupOrderService();
   List<GroupOrderVO> groupOrderlist=groupOrderSvc.getAll();
   request.setAttribute("groupOrderlist", groupOrderlist);
%>
<jsp:useBean id="groupBandSvc" scope="page" class="com.groupBand.model.GroupBandService"/>
<jsp:useBean id="groupBandVO" scope="page" class="com.groupBand.model.GroupBandVO"/>

<!DOCTYPE html>
<html>
<head>
<title>查看揪團歷史訂單</title>
	<jsp:include page="/regna-master/head.jsp" />  
</head>
<body>
<!-- 錯誤列表開始 -->
    <%List<String> errorMsgs=(List<String>)request.getAttribute("errorMsgs");%>
    <c:if test="${not empty errorMsgs}"><ul class="list-group">
		  <li class="list-group-item active">Opps!錯誤訊息回報</li>
		  <c:forEach var="massage" items="${errorMsgs}">
		  <li class="list-group-item">${massage}</li>
		  </c:forEach>
		</ul>
	</c:if>
<!-- 錯誤列表結束 -->
		<section id="contact">
 			<div class="container wow fadeInUp">
                <div class="section-header">
                    <h3 class="section-title">揪團歷史訂單</h3>
                    <form action="<%=request.getContextPath()%>/front-end/singleOrder/listAllfutureTrip.jsp">
					 <div class="text-center"><button type="submit" class="btn btn-outline-success">返回</button></div>
					</form>
                </div>
            </div>
             <div class="container wow fadeInUp">
            	<div class="row" > 
	            	<!--==========================
							  揪團歷史訂單 
					============================-->
	            	<div class="col-lg-12 col-md-6 wow fadeInUp" data-wow-delay="0.2s"> 
	                        <div class="section-header">
<!-- 			                    <h3 class="section-title">揪團歷史訂單</h3> -->
			                </div>
	                        <table class="table">
							  <thead class="thead-dark">							    <tr>
							      <tr></tr>
							      <tr>
							      <th scope="col">訂單編號	</th>
							      <th scope="col">乘車時間	</th>
							      <th scope="col">乘車地點	</th>
							      <th scope="col">乘車目的地	</th>
							      <th scope="col">總金額	    </th>
							      <th scope="col">評價分數	</th>
							      <th scope="col">評價司機	</th>
							    </tr>
							  </thead>
							  <tbody>
		<c:forEach var="groupOrder" items="${groupOrderlist}" >			  
			 <c:if test="${groupOrder.memID eq memID && groupOrder.state !=0 && singleOrder.state !=1}">
						 		<tr>	 
							      <th scope="row">${groupOrder.gorderID}</th>
							      <td>
							      <fmt:formatDate  type="both" value="${groupOrder.startTime}" pattern="yyyy-MM-dd mm:ss" /> 
							      </td>
							      <td>${groupBandSvc.getOneGroupBand(groupOrder.groupID).startLoc}</td>
							      <td>${groupBandSvc.getOneGroupBand(groupOrder.groupID).endLoc}</td>
							      <td>${groupOrder.totalAmout}</td>
							      <td>
							       ${groupOrder.rate==0? "尚未評價": groupOrder.rate}
							      </td>
							      <td>
							      <!-- 此處與評價司機串接 -->
							      <Form METHOD="post" ACTION="<%=request.getContextPath()%>" >
								    <div class="text-center"><button type="submit" class="btn btn-light">評價司機</button>
								      	<!-- /*放隱藏的標籤，讓Controller抓到參數進行操作*/ -->
		                				<input type="hidden" name="groupOrder" value="${groupOrder.gorderID}">
								     </div>
								  </Form>
							      </td>
							    </tr>
		</c:if>
		</c:forEach>
							</tbody>
							</table>
				   </div>  <!-- col-->
            	</div>  <!-- row-->
            </div> <!-- container -->
        </section>
<!--==========================
    底部:include body樣板
  ============================-->
    
    <jsp:include page="/regna-master/body.jsp" />

</body>
</html>