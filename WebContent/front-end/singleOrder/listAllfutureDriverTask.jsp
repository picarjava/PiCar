<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.singleOrder.model.SingleOrderService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.Date"%>
<%@ page import="com.singleOrder.model.SingleOrderVO"%>
<%@ page import="com.groupOrder.model.GroupOrderService"%>
<%@ page import="com.groupOrder.model.GroupOrderVO"%>



<!-- 登入功能串接 ，將VOmemID用Driver servevice driverID找出指定給driverID-->
<%@ page import="com.member.model.MemberVO"%>
<%@ page import="com.driver.model.DriverService"%>
<%@ page import="com.driver.model.DriverVO"%>
<%MemberVO memberVO=(MemberVO)session.getAttribute("memberVO");
String memID=memberVO.getMemID();
DriverService driverSvc=new DriverService();
DriverVO driverVO=driverSvc.getOneDriverBymemID(memID);
String driverID=driverVO.getDriverID();
session.setAttribute("driverID", driverID);
%> 



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
                    <form action="<%=request.getContextPath()%>/front-end/HomeDriver/index.jsp">
					 <div class="text-center"><button type="submit" class="btn btn-outline-success">返回司機首頁</button></div>
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
							    </tr>
		</c:if>					    
		</c:forEach>
							</tbody>
							</table>
		<div class="text-center">
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
    底部
  ============================-->
    
    <jsp:include page="/regna-master/body.jsp" />
</body>

</html>