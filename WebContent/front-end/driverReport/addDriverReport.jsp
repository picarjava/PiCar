<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.List"%>
<%@ page import="com.driverReport.model.DriverReportVO" %>

<!DOCTYPE html>
<html>
<head>
	<title>單程的檢舉司機單</title>
	<jsp:include page="/regna-master/head.jsp" />  
</head>
<body>
<!-- 取得orderID 與 memID -->
<%String orderID=(String)request.getAttribute("orderID");
String memID=(String)request.getAttribute("memID");
%>

<%DriverReportVO driverReportVO=(DriverReportVO)request.getAttribute("driverReportVO");%>


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

       <!-- 內容開始 -->
            <div class="section-header">
            <h3 class="section-title">檢舉司機單</h3>
            </div>
            <form action='<%=request.getContextPath()%>/front-end${orderID.substring(0,1) eq "S"?"/singleOrder/listPastSingleOrder.jsp":"/groupOrder/listPastGroupOrder.jsp"}'  >
					 <div class="text-center"><button type="submit" class="btn btn-outline-success">返回</button></div>
			</form>
			<Form METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/driverReport/driverReport.do">
				<div class="text-center">
				訂單編號<input  name="orderID" readonly  value="${orderID}">
				會員編號<input  name="memID" readonly  value="${memID}">
				<!-- /*放隱藏的標籤，讓Controller抓到參數進行操作*/ -->
				<input type="hidden" name="state" value="0">
				<input type="hidden" name="action" value="insertDriverReport">
				</div>
				<jsp:useBean id="driberReportSvc" class="com.driverReport.model.DriverReportService"/>
				<c:if test="${empty driberReportSvc.getOneByOrderID(orderID).content}">
				<div class="form-group">
					<label >請輸入檢舉內容</label>
					<textarea class="form-control" 
						rows="3" name="content"></textarea>
				</div>
				<button type="submit" class="btn btn-secondary" data-dismiss="modal">送出評價</button>
			    </c:if>
				<c:if test="${not empty driberReportSvc.getOneByOrderID(orderID).content}">
				<div class="form-group">
					<label >您於 ${driberReportSvc.getOneByOrderID(orderID).time} 的檢舉內容</label>
					<textarea class="form-control" 
						rows="3" name="content" readonly>${driberReportSvc.getOneByOrderID(orderID).content}</textarea>
				</div>
			    </c:if>
			</Form>
		<!-- 內容結束 -->
<!-- 	<!--========================== -->
<!--     底部 -->
<!--   ============================--> 
    
    <jsp:include page="/regna-master/body.jsp" />
</body>
</html>