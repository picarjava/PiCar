<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.singleOrder.model.SingleOrderService"%>
<%@ page import="java.util.List"%>
<%@ page import="com.singleOrder.model.SingleOrderVO"%>
<%@ page import="com.driverReport.model.DriverReportVO"%>
<!-- 本頁面待與登入功能串接 此處先指定memID-->
<%-- <%String memID=(String)session.getAttribute("memID"); %>  --%>
<%!String memID="M001";%>
<%
    session.setAttribute("memID", memID);
%>

<!-- 個人訂單 -->
<%
    SingleOrderService service = new SingleOrderService();
   List<SingleOrderVO> singleOrderlist = service.getAll();
   request.setAttribute("singleOrderlist", singleOrderlist);
%>

<!-- driver Report  -->
<%DriverReportVO driverReportVO=(DriverReportVO)request.getAttribute("driverReportVO");%>



<!DOCTYPE html>
<html>
<head>
	<title>查看個人歷史訂單</title>
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
                    <h3 class="section-title">個人歷史訂單</h3>
                    <form action="<%=request.getContextPath()%>/front-end/singleOrder/listAllfutureTrip.jsp">
					 <div class="text-center"><button type="submit" class="btn btn-outline-success">返回</button></div>
					</form>
                </div>
            </div>
 		
            <div class="container wow fadeInUp">
            	<div class="row" > 
	            	<!--==========================
							    個人訂單 
					============================-->
	            	<div class="col-lg-12 col-md-6 wow fadeInUp" data-wow-delay="0.2s"> 
	                        <div class="section-header">
<!-- 			                    <h3 class="section-title">個人歷史行程</h3> -->
			                </div>
	                        <table class="table">
							  <thead class="thead-dark">							    <tr>
							      <tr></tr>
							      <tr>
							      <th scope="col">訂單編號	</th>
							      <th scope="col">訂單種類	</th>
							      <th scope="col">乘車時間	</th>
							      <th scope="col">乘車地點	</th>
							      <th scope="col">乘車目的地	</th>
							      <th scope="col">總金額	    </th>
							      <th scope="col">訂單狀態	</th>
							      <th scope="col">評價司機	</th>
							      <th scope="col">檢舉司機	</th>
							    </tr>
							  </thead>
							  <tbody>
		<c:forEach var="singleOrder" items="${singleOrderlist}" >			  
			 <c:if test="${singleOrder.memID eq memID && singleOrder.state !=0 && singleOrder.state !=1}">
						 		<tr>	 
							      <td scope="row">${singleOrder.orderID}</td>
							      <td>
							      <c:forEach var="orderType" items="${orderTypeMap}">
						 		   ${orderType.key eq singleOrder.orderType ? orderType.value: ""}
						 		  </c:forEach>
							      </td>
							      <td>
							      <fmt:formatDate  type="both" value="${singleOrder.startTime}" pattern="yyyy-MM-dd mm:ss" /> 
							      </td>
							      <td>${singleOrder.startLoc}</td>
							      <td>${singleOrder.endLoc}</td>
							      <td>${singleOrder.totalAmount}</td>
							       <td>
						 		  <c:forEach var="state" items="${stateMap}">
						 		   ${state.key eq singleOrder.state ? state.value: ""}
						 		  </c:forEach>
						 		  </td>
							       <c:if test="${singleOrder.rate!=0}">
							       <td>
							          您的評價:${singleOrder.rate} 分
							       </td>
							       </c:if>
							      <c:if test="${singleOrder.rate==0}">
							      <td>
							      <Form METHOD="post" ACTION="<%=request.getContextPath()%>/singleOrder" >
								    <div class="text-center"><button type="submit" class="btn btn-light">尚未評價</button>
<!-- 								      	放隱藏的標籤，讓Controller抓到參數進行操作 -->
		                				<input type="hidden" name="orderID" value="${singleOrder.orderID}">
		                				<input type="hidden" name="action" value="passID">
								     </div>
								  </Form>
				                  </td>
				                  </c:if>
								 <td>
							      <Form METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/driverReport/driverReport.do" >
								    <jsp:useBean id="driberReportSvc" class="com.driverReport.model.DriverReportService"/>
									 <div class="text-center"><button type="submit" class="btn btn-light" id="driberReport">${empty driberReportSvc.getOneByOrderID(singleOrder.orderID).content?"檢舉司機": "已檢舉"}</button>
								      	<!--  orderID 與 memID 需透過controller傳遞至addGroupOrderDriverReport.jsp -->
		                				<input type="hidden" name="orderID" value="${singleOrder.orderID}">
		                				<input type="hidden" name="memID" value="${singleOrder.memID}">
		                				<input type="hidden" name="action" value="passID">
								     </div>
								  </Form>
							      </td>
								  <td>			   
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
    底部
  ============================-->
   
								   
									
								    
  
  
    <!--==========================
    測試modal失敗，modal無法用於TABEL中的資料傳遞
  ============================-->
   <!-- Modal -->
<!-- <div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true"> -->
<!--   <div class="modal-dialog" role="document"> -->
<!--     <div class="modal-content"> -->
<!--       <div class="modal-header"> -->
<!--         <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5> -->
<!--         <button type="button" class="close" data-dismiss="modal" aria-label="Close"> -->
<!--           <span aria-hidden="true">&times;</span> -->
<!--         </button> -->
<!--       </div> -->
<!--       <div class="modal-body"> -->
<%--         <jsp:include page="/front-end/singleOrder/rating.jsp"/> --%>
<!--       </div> -->
<!--       <div class="modal-footer"> -->
<!--         <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
<!--         <button type="button" class="btn btn-primary">Save changes</button> -->
<!--       </div> -->
<!--     </div> -->
<!--   </div> -->
<!-- </div> -->
	<!-- Modal結束 -->


	<jsp:include page="/regna-master/body.jsp" />
</body>

</html>