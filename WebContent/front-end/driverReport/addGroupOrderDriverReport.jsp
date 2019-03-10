<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
	<title>揪團訂單檢舉司機</title>
	<jsp:include page="/regna-master/head.jsp" />  
</head>
<body>
<!-- 取得orderID 與 memID -->
<%String orderID=(String)request.getAttribute("orderID");
String memID=(String)request.getAttribute("memID");
%>


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
            <h3 class="section-title">新增揪團訂單檢舉司機</h3>
			<Form METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/driverReport/driverReport.do">
				<div class="text-center">
				揪團編號<input  name="orderID" readonly  value="${groupID}">
				訂單編號<input  name="orderID" readonly  value="${orderID}">
				會員編號<input  name="memID" readonly  value="${memID}">
				<!-- /*放隱藏的標籤，讓Controller抓到參數進行操作*/ -->
				<input type="hidden" name="state" value="0">
				<input type="hidden" name="action" value="insertDriverReport">
				</div>
				<div class="form-group">
					<label >請輸入檢舉內容</label>
					<textarea class="form-control" 
						rows="3" name="content"></textarea>
				</div>
				<button type="submit" class="btn btn-secondary" data-dismiss="modal">送出評價</button>
			</Form>
		<!-- 內容結束 -->
<!-- 	<!--========================== -->
<!--     底部 -->
<!--   ============================--> 
    
    <jsp:include page="/regna-master/body.jsp" />
</body>
</html>