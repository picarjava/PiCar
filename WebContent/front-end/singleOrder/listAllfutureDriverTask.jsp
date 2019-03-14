<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.singleOrder.model.SingleOrderService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.Date"%>
<%@ page import="com.singleOrder.model.SingleOrderVO"%>
<%@ page import="com.groupOrder.model.GroupOrderService"%>
<%@ page import="com.groupOrder.model.GroupOrderVO"%>


<!-- 本頁面待與登入功能串接 此處先指定driverID-->
<%-- <%String driverID=(String)session.getAttribute("driverID"); %>  --%>
<%! String driverID="D001";%>
<% session.setAttribute("driverID", driverID);%>

<!-- 個人訂單 -->
<% SingleOrderService service = new SingleOrderService();
   List<SingleOrderVO> singleOrderlist = service.getAll();
   request.setAttribute("singleOrderlist", singleOrderlist);
%>


<!-- 揪團訂單 -->
<% GroupOrderService groupOrderSvc=new GroupOrderService();
   List<GroupOrderVO> groupOrderlist=groupOrderSvc.getAll();
   request.setAttribute("groupOrderlist", groupOrderlist);
%>

<jsp:useBean id="groupBandSvc" scope="page" class="com.groupBand.model.GroupBandService"/>
<jsp:useBean id="groupBandVO" scope="page" class="com.groupBand.model.GroupBandVO"/>

<!DOCTYPE html>
<html lang="zh">
<head>
    <title>查看司機未來訂單</title>
    <jsp:include page="/regna-master/head.jsp" />  
</head>
<body>
    <!-- 錯誤列表開始 -->
    <%List errorMsgs=(List<String>)request.getAttribute("errorMsgs");%>
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
                    <h3 class="section-title">查看司機未來訂單</h3>
                    <form action="">
					 <div class="text-center"><button type="submit" class="btn btn-outline-success">返回首頁</button></div>
					</form>
                </div>
            </div>
            <div class="container wow fadeInUp">
            	<div class="row" > 
	            	<!--==========================
							    查看司機未來訂單-個人訂單 
					============================-->
            	
	            	<div class="col-lg-12 col-md-6 wow fadeInUp" data-wow-delay="0.2s"> 
	                        <div class="section-header">
			                    <h3 class="section-title">個人訂單</h3>
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
							      <th scope="col">訂單總金額	</th>
							      <th scope="col">訂單狀態	</th>
							    </tr>
							  </thead>
							  <tbody>
		
		<c:forEach var="singleOrder" items="${singleOrderlist}" >
		<!-- 未來訂單:篩選狀態碼為 1:訂單成立、4:執行中、6:逾期未處理-->				  
			 <c:if test="${singleOrder.driverID eq driverID &&( singleOrder.state eq 1|| singleOrder.state eq 4||singleOrder.state eq 6)}">
						 		<tr>	
						 		  <th scope="row">${singleOrder.orderID}</th>
						 		  <td>
						 		  <c:forEach var="orderType" items="${orderTypeMap}">
						 		   ${orderType.key eq singleOrder.orderType ? orderType.value: ""}
						 		  </c:forEach>
						 		  </td>
							      <td >
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
							      <td >
<!-- 							      <td> -->
<%-- 							      <Form METHOD="post" ACTION="<%=request.getContextPath()%>/singleOrder" > --%>
<!-- 								    <div class="text-center"><button type="submit" class="btn btn-light">取消</button> -->
<!-- 								      	/*放隱藏的標籤，讓Controller抓到參數進行操作*/ -->
<!-- 		                				<input type="hidden" name="action" value="DELETE"> -->
<%-- 		                				<input type="hidden" name="orderID" value="${singleOrder.orderID}"> --%>
<!-- 								     </div> -->
<!-- 								  </Form> -->
<!-- 							      </td> -->
							    </tr>
		</c:if>					    
		</c:forEach>
							</tbody>
							</table>
		<div class="text-center">
<!-- 		<button type="button" class="btn btn-secondary btn-lg btn-block" data-toggle="modal" data-target="#listPastSingleOrder"> -->
<!-- 		  查看歷史紀錄 -->
<!-- 		</button> -->
		<Form  ACTION="<%=request.getContextPath()%>/front-end/singleOrder/listPastSingleDriverTask.jsp" >
		<button type="submit" class="btn btn-secondary btn-lg btn-block">查看司機個人訂單歷史紀錄</button>
		</Form>
		</div>	
						</div>  <!-- col-->
						 <!--==========================
						    查看司機未來訂單-揪團訂單 
						  ============================-->
            		<div class="col-lg-12 col-md-6 wow fadeInUp" data-wow-delay="0.2s"> 
                        <div class="section-header">
			                    <h3 class="section-title">揪團行程</h3>
			            </div>
                        <table class="table">
						  <thead class="thead-dark">
						    <tr>
						     <th scope="col">訂單編號	</th>
							 <th scope="col">訂單種類	</th>
							 <th scope="col">乘車時間	</th>
							 <th scope="col">乘車地點	</th>
							 <th scope="col">乘車目的地	</th>
							 <th scope="col">訂單總金額	</th>
							 <th scope="col">訂單狀態	</th>
						    </tr>
						  </thead>
						  <tbody>
	<c:forEach var="groupOrder" items="${groupOrderlist}" >	
	<!-- 未來訂單:篩選狀態碼為 1:訂單成立、4:執行中、6:逾期未處理-->		  
		 	 <c:if test="${groupOrder.driverID eq driverID&& groupOrder.state eq 1|| groupOrder.state eq 4|| groupOrder.state eq 6 }">
					 		<tr>
					 			  <td>${groupOrder.gorderID}</td>
					 			  <td>
							      <c:forEach var="orderType" items="${orderTypeMap}">
						 		   ${orderType.key eq groupOrder.orderType ? orderType.value: ""}
						 		  </c:forEach>
							      </td>		
					 			  <td scope="row">
							      <fmt:formatDate type="BOTH" value="${groupOrder.startTime}" pattern="yyyy/MM/dd/ mm:ss"/>
							      </td>
							      <td>${groupOrder.startLoc}</td>
							      <td>${groupOrder.endLoc}</td>
							      <td>${groupOrder.totalAmout}</td>
							      <td>
							      <c:forEach var="state" items="${stateMap}">
						 		   ${state.key eq groupOrder.state? state.value: ""}
						 		  </c:forEach>
							      </td>
<!-- 										<td> -->
<!-- 											<Form METHOD="post" -->
<%-- 												ACTION="<%=request.getContextPath()%>/singleOrder"> --%>
<!-- 												<div class="text-center"> -->
<!-- 													<button type="submit" class="btn btn-light">取消</button> -->
<!-- 													/*放隱藏的標籤，讓Controller抓到參數進行操作*/ -->
<!-- 													<input type="hidden" name="action" value="DELETE"> -->
<!-- 													<input type="hidden" name="groupOrder" -->
<%-- 														value="${groupOrder.gorderID}"> --%>
<!-- 												</div> -->
<!-- 											</Form> -->
<!-- 										</td> -->
									</tr>  
						   
	</c:if>		
	</c:forEach>
 						</tbody>
						</table>
						<Form ACTION="<%=request.getContextPath()%>/front-end/groupOrder/listPastGroupDriverTask.jsp">
						<button type="submit" class="btn btn-secondary btn-lg btn-block">查看揪團訂單歷史紀錄</button>
						</Form>
						</div><!-- col-->
					</div>   <!-- row-->
            	</div> <!-- container -->
        </section>
         <!--==========================
		    includ個人歷史訂單
		  ============================-->
		<!-- Modal -->
<!-- 		<div class="modal fade" id="listPastSingleOrder" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true"> -->
<!-- 		  <div class="modal-dialog" role="document"> -->
<!-- 		    <div class="modal-content"> -->
<!-- 		      <div class="modal-header"> -->
<!-- 		        <h5 class="modal-title" id="exampleModalLabel">個人歷史訂單</h5> -->
<!-- 		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"> -->
<!-- 		          <span aria-hidden="true">&times;</span> -->
<!-- 		        </button> -->
<!-- 		      </div> -->
<!-- 		      <div class="modal-body"> -->
<%-- 		      <jsp:include page="/front-end/singleOrder/listPastSingleOrder.jsp"/>; --%>
<!-- 		      </div> -->
<!-- 		      <div class="modal-footer"> -->
<!-- 		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
<!-- 		      </div> -->
<!-- 		    </div> -->
<!-- 		  </div> -->
<!-- 		</div> -->
       <!-- Button trigger modal 查看個人單筆詳情 結束-->
   
    <!--==========================
    底部
  ============================-->
    
    <jsp:include page="/regna-master/body.jsp" />
</body>

</html>