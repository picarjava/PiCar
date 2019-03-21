<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.singleOrder.model.SingleOrderService"%>
<%@ page import="com.singleOrder.model.SingleOrderVO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.Date"%>
<%@ page import="com.groupOrder.model.GroupOrderVO"%>
<%@ page import="com.groupOrder.model.GroupOrderService"%>

<!-- 登入功能串接 ，將VOmemID指定給 memID-->
<%@ page import="com.member.model.MemberVO"%>
<%MemberVO memberVO=(MemberVO)session.getAttribute("memberVO");
String memID=memberVO.getMemID();
session.setAttribute("memID",memID);
%> 


<!-- 個人訂單 -->
<%
    SingleOrderService service = new SingleOrderService();
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
    <title>查看排定訂單</title>
    <jsp:include page="/regna-master/head.jsp" />  
</head>
<body onload="connect();" onunload="disconnect();">
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
                    <h3 class="section-title">查看排定訂單</h3>
                    <p>會員${memID}最新即時推播內容:</p><p id="statusOutput"></p>
                    <form action="">
					 <div class="text-center"><button type="submit" class="btn btn-outline-success">返回首頁</button></div>
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
			                    <h3 class="section-title">個人行程</h3>
			                </div>
	                        <table class="table">
							  <thead class="thead-dark">							    <tr>
							      <tr></tr>
							      <tr>
							      <th scope="col">乘車時間	</th>
							      <th scope="col">訂單編號	</th>
							      <th scope="col">訂單種類	</th>
							      <th scope="col">乘車地點	</th>
							      <th scope="col">乘車目的地	</th>
							      <th scope="col">總金額	    </th>
							      <th scope="col">取消行程	</th>
							      <th scope="col" colspan="2">訂單狀態	</th>
							    </tr>
							  </thead>
							  <tbody>
		
		<c:forEach var="singleOrder" items="${singleOrderlist}" >			  
			 <c:if test="${singleOrder.memID eq memID && singleOrder.state eq 0|| singleOrder.state eq 1}">
						 		<tr>	 
							      <th scope="row">
							      <fmt:formatDate  type="both" value="${singleOrder.startTime}" pattern="yyyy-MM-dd mm:ss" />
							      </th>
							      <td>${singleOrder.orderID}</td>
							      <td>
							      <c:forEach var="orderType" items="${orderTypeMap}">
						 		   ${orderType.key eq singleOrder.orderType ? orderType.value: ""}
						 		  </c:forEach>
							      </td>
							      <td>${singleOrder.startLoc}</td>
							      <td>${singleOrder.endLoc}</td>
							      <td>${singleOrder.totalAmount}</td>
							      <c:if test="${singleOrder.state eq 1 }">
							          <td>
								      </td>
							       </c:if>
							       <c:if test="${singleOrder.state eq 0 and singleOrder.orderType eq 0}">
							          <td>
								            需於Picar App中取消
								      </td>
							       </c:if>
							      <c:if test="${singleOrder.state eq 0 and singleOrder.orderType eq 3}">
							          <td>
								      <Form METHOD="post" ACTION="<%=request.getContextPath()%>/singleOrder" >
									    <div class="text-center"><button type="submit" class="btn btn-light">取消</button>
									      	<!-- /*放隱藏的標籤，讓Controller抓到參數進行操作*/ -->
			                				<input type="hidden" name="action" value="DELETE">
			                				<input type="hidden" name="orderID" value="${singleOrder.orderID}">
									     </div>
									   </Form>
								       </td>
							       </c:if>
							       <td >
							        <c:if test="${singleOrder.state eq 0 and singleOrder.orderType eq 4}">
							           <div class="text-center"><button id="DELETELONGTERM" type="submit" class="btn btn-light">取消長期預約</button>
							           <Form METHOD="post" ACTION="<%=request.getContextPath()%>/singleOrder" >
									    <!-- /*放隱藏的標籤，讓Controller抓到參數進行操作*/ -->
			                				<input type="hidden" name="action" value="DELETELONGTERM">
			                				<input type="hidden" name="orderID" value="${singleOrder.orderID}">
									   </Form>
									   </div>
								       </td>
							       </c:if>
							       <td>
						 		  <c:forEach var="state" items="${stateMap}">
						 		   ${state.key eq singleOrder.state ? state.value: ""}
						 		  </c:forEach>
						 		  </td>
							    </tr>
		</c:if>					    
		</c:forEach>
							</tbody>
						  </table>
		<div class="text-center">
<!-- 		<button type="button" class="btn btn-secondary btn-lg btn-block" data-toggle="modal" data-target="#listPastSingleOrder"> -->
<!-- 		  查看歷史紀錄 -->
<!-- 		</button> -->
		<Form  ACTION="<%=request.getContextPath()%>/front-end/singleOrder/listPastSingleOrder.jsp" >
		<button type="submit" class="btn btn-secondary btn-lg btn-block">查看個人歷史紀錄</button>
		</Form>
		</div>	
						</div>  <!-- col-->
						 <!--==========================
						    揪團訂單 
						  ============================-->
				 <div class="container wow fadeInUp">
            		<div class="col-lg-12 col-md-6 wow fadeInUp" data-wow-delay="0.2s"> 
                        <div class="section-header">
			                    <h3 class="section-title">揪團行程</h3>
			            </div>
                        <table class="table">
						  <thead class="thead-dark">
						    <tr>
						     <th scope="col">乘車時間	</th>
						     <th scope="col">訂單種類	</th>
							 <th scope="col">乘車地點	</th>
							 <th scope="col">前往目的地</th>
							 <th scope="col">總金額	</th>
							  <th scope="col">訂單狀態</th>
						    </tr>
						  </thead>
						  <tbody>
						  
						  
	<c:forEach var="groupOrder" items="${groupOrderlist}" >			  
		 	 <c:if test="${groupOrder.memID eq memID&& groupOrder.state eq 0|| groupOrder.state eq 1 }">
					 		<tr>
					 			  <th scope="row">
							      <fmt:formatDate type="BOTH" value="${groupOrder.startTime}" pattern="yyyy/MM/dd/ mm:ss"/>
							      </th>
							      <td>
							      <c:forEach var="orderType" items="${orderTypeMap}">
						 		   ${orderType.key eq groupOrder.orderType ? orderType.value: ""}
						 		  </c:forEach>
							      </td>	
							      <td>${groupBandSvc.getOneGroupBand(groupOrder.groupID).startLoc}</td>
							      <td>${groupBandSvc.getOneGroupBand(groupOrder.groupID).endLoc}</td>
							      <td>${groupOrder.totalAmout}</td>
<!-- 						       <td> -->
<%-- 						      <Form METHOD="post" ACTION="<%=request.getContextPath()%>/singleOrder" > --%>
<!-- 							    <div class="text-center"><button type="submit" class="btn btn-light">取消</button> -->
<!-- 							      	/*放隱藏的標籤，讓Controller抓到參數進行操作*/ -->
<!-- 	                				<input type="hidden" name="action" value="DELETE"> -->
<%-- 	                				<input type="hidden" name="groupOrder" value="${groupOrder.gorderID}"> --%>
<!-- 							     </div> -->
<!-- 							  </Form> -->
<!-- 						      </td> -->
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
						<Form ACTION="<%=request.getContextPath()%>/front-end/groupOrder/listPastGroupOrder.jsp">
						<button type="submit" class="btn btn-secondary btn-lg btn-block">查看揪團歷史紀錄</button>
						</Form>
						</div>
						
					</div>  <!-- col-->
            	</div>  <!-- row-->
            </div> <!-- container -->
        </section>
    <!--==========================
    底部
  ============================-->
    
    <jsp:include page="/regna-master/body.jsp" />
</body>

<script>
$("#DELETELONGTERM").click(function() {
	window.alert("同一筆長期預約訂單將一併刪除");
});
</script>

<!--==========websocket推播 開始=============-->
 <script>
 	var MyPoint="/BroadcastServer/${memID}";
 	var host=window.location.host;
 	var path=window.location.pathname;
 	var webCtx=path.substring(0,path.indexOf('/',1));
 	var endPointURL="ws://"+window.location.host+webCtx+MyPoint;
 	
 	var webSocketTitle =document.getElementById("webSocketTitle"); //狀態標題
 	var statusOutput=document.getElementById("statusOutput");//狀態內容
 	
 	var webSocket;
 	
 	function connect(){
 		
 		//建立websocket物件
 		webSocket=new WebSocket(endPointURL);
 		
 		webSocket.onopen=function(event){
 			updateStatus("WebSocket 成功連線");
 		};
 		
 		webSocket.onmessage=function(event){
 			var jsonObj=JSON.parse(event.data);
 			var message=jsonObj.message+"\r\n";
  			window.alert(message);
 			updateStatus(message);
 		};
 		
 		webSocket.onclose=function(event){
 			
 			updateStatus("WebSocket已離線");
 		};
 	}
 	
 	function disconnect(){
 		webSocket.close();
 	}
 	
 	function updateStatus(newStatus){
 		statusOutput.innerHTML= newStatus;
 	}
 	
 	
 </script>

 <!--==========websocket推播 結束============-->
</html>