<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.singleOrder.model.SingleOrderService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.Date"%>
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

<!-- 揪團訂單 -->
<% GroupOrderService groupOrderSvc=new GroupOrderService();
   List<GroupOrderVO> groupOrderlist=groupOrderSvc.getAll();
   request.setAttribute("groupOrderlist", groupOrderlist);
%>


<!DOCTYPE html>
<html>
<head>
<title>查看司機揪團歷史訂單</title>
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
                    <h3 class="section-title">司機揪團歷史訂單</h3>
                    <form action="<%=request.getContextPath()%>/front-end/singleOrder/listAllfutureDriverTask.jsp">
					 <div class="text-center"><button type="submit" class="btn btn-outline-success">返回</button></div>
					</form>
                </div>
            </div>
             <div class="container wow fadeInUp">
            	<div class="row" > 
	            	<!--==========================
							  司機揪團歷史訂單 
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
									 <th scope="col">訂單種類	</th>
									 <th scope="col">乘車時間	</th>
									 <th scope="col">乘車地點	</th>
									 <th scope="col">乘車目的地</th>
									 <th scope="col">訂單總金額</th>
									 <th scope="col">訂單狀態	</th>
							    </tr>
							  </thead>
							  <tbody>
		<c:forEach var="groupOrder" items="${groupOrderlist}" >	
		<!-- 歷史訂單:篩選狀態碼為非 0:待訂單成立、1:訂單成立、4:執行中、6:逾期未處理-->			  
			 <c:if test="${groupOrder.driverID eq driverID && groupOrder.state !=0 && groupOrder.state !=1 && groupOrder.state !=4 && groupOrder.state !=6}">
						 		<tr>	 
							      <th>${groupOrder.gorderID}</th>
					 			  <th>
					 			  ${groupOrder.orderType eq 0?"單程揪團":"長期揪團"}
					 			  </th>		
					 			  <th scope="row">
							      <fmt:formatDate type="BOTH" value="${groupOrder.startTime}" pattern="yyyy/MM/dd/ mm:ss"/>
							      </th>
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