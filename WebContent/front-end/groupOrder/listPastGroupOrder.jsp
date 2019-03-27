<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.singleOrder.model.SingleOrderService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.Date"%>
<%@ page import="com.groupOrder.model.GroupOrderService"%>
<%@ page import="com.groupOrder.model.GroupOrderVO"%>

<!-- 會員登入功能串接 ，將VOmemID指定給 memID-->
<%@ page import="com.member.model.MemberVO"%>
<%MemberVO memberVO=(MemberVO)session.getAttribute("memberVO");
String memID=memberVO.getMemID();
session.setAttribute("memID",memID);
%> 

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
	
</head>
<jsp:include page="/front-end/HomeMember/HeadMember.jsp" />
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
		<c:forEach var="groupOrder" items="${groupOrderlist}" >			  
			 <c:if test="${groupOrder.memID eq memberVO.memID}">
						 <c:if test="${groupOrder.state !=0 && groupOrder.state !=1 && groupOrder.state !=4}">		
						 
						 		<tr>	 
							      <td scope="row">${groupOrder.gorderID}</td>
							      <td>
							      <c:forEach var="orderType" items="${orderTypeMap}">
						 		   ${orderType.key eq groupOrder.orderType ? orderType.value: ""}
						 		  </c:forEach>
							      </td>	
							      <td>
							      <fmt:formatDate  type="both" value="${groupOrder.startTime}" pattern="yyyy-MM-dd mm:ss" /> 
							      </td>
							      <td>${groupBandSvc.getOneGroupBand(groupOrder.groupID).startLoc}</td>
							      <td>${groupBandSvc.getOneGroupBand(groupOrder.groupID).endLoc}</td>
							      <td>${groupOrder.totalAmout}</td>
							      <td>
							      <c:forEach var="state" items="${stateMap}">
						 		   ${state.key eq groupOrder.state? state.value: ""}
						 		  </c:forEach>
							     </td>
							      <c:if test="${groupOrder.rate!=0}">
							      <td>
							          您的評價分數:${groupOrder.rate}
							      </td>
							      </c:if>
							      <c:if test="${groupOrder.rate==0}">
							      <td>
							      <Form METHOD="post" ACTION="<%=request.getContextPath()%>/GroupOrder" >
								    <div class="text-center"><button type="submit" class="btn btn-light">尚未評價</button>
<!-- 								      	放隱藏的標籤，讓Controller抓到參數進行操作 -->
		                				<input type="hidden" name="orderID" value="${groupOrder.gorderID}">
		                				<input type="hidden" name="action" value="passID">
								     </div>
								  </Form>
				                  </td>
							      </c:if>
							      <td>
								   <Form METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/driverReport/driverReport.do" >
								    <jsp:useBean id="driberReportSvc" class="com.driverReport.model.DriverReportService"/>
									 <div class="text-center"><button type="submit" class="btn btn-light" id="driberReport">${empty driberReportSvc.getOneByOrderID(groupOrder.gorderID).content?"檢舉司機": "已檢舉"}</button>
								      	<!--  orderID 與 memID 需透過controller傳遞至addGroupOrderDriverReport.jsp -->
		                				<input type="hidden" name="orderID" value="${groupOrder.gorderID}">
		                				<input type="hidden" name="memID" value="${groupOrder.memID}">
		                				<input type="hidden" name="action" value="passID_GODR">
								     </div>
								  </Form>
							      </td>
							    </tr>
			</c:if>
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
    

</body>
</html>