<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.driver.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<html>
<head>
<jsp:include page="/back-end/head_back.jsp" />
</head>
<body>
<jsp:include page="/back-end/kidBodyLeft.jsp" />
	<!--====      Contact Section===============-->
	<!-- 先取出VO -->

	<!-- 錯誤列表 -->
	<%
		LinkedList errorMsgs = (LinkedList<String>) request.getAttribute("errorMsgs");
	%>
<%-- 	<% DriverService driverSvc=new DriverService();%> --%>
<%--     <%List<DriverVO> list=driverSvc.getOneDriver();%> --%>
<%--     <%request.setAttribute("list", list); %> --%>
<%--     <%if(list!=null&&(list.size()>0)){ %> --%>
    
	<c:if test="${not empty errorMsgs}">
		<ul class="list-group">
			<li class="list-group-item active">Opps!錯誤訊息回報</li>
			<c:forEach var="massage" items="${errorMsgs}">
				<li class="list-group-item">${massage}</li>
			</c:forEach>
		</ul>
	</c:if>
	<section id="contact">
		<div class="container wow fadeInUp">
			<div class="col-lg-12 col-md-12">
				<div class="section-header">
					<h3 class="section-title">司機會員查詢</h3>
					<div class="text-center">
						<form action="listAllDriver.jsp">
							<button type="submit">返回</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="container wow fadeInUp">
			<div class="row justify-content-center">
				<div class="col-lg-12 col-md-12">
					<div class="form">
						<!-- 司機表單開始 -->
						<form action="driver.do" method="post" role="form"
							class="contactForm">
							<!-- <form action="<.%=request.getContextPath()%.>/activity/Activ_servlet.html" method="post" role="form" class="contactForm"> -->
<!--  176行 -->
							<div class="form-group">
								<p>會員編號</p>
								<input type="text" name="memID" readonly 
									value="${driverVO.memID}" class="form-control" />
							</div>
							<div class="form-group">
								<p>會員名字</p>
								<input type="text" name="memID" readonly 
									value="${MemberVOs.name}" class="form-control" />
							</div>
							<div class="form-group">
								<p>司機編號</p>
								<input type="text" name="driverID" readonly 
									value="${driverVO.driverID}" class="form-control" />
							</div>
							<div class="form-group">
								<p>車牌號碼</p>
								<input type="text" name="plateNum" class="form-control"
									value="${driverVO.plateNum}" readonly placeholder="請輸入車牌號碼" />
								<div class="card" style="width: 18rem;"></div>
							</div>
							<div class="form-group">
								<p>車型</p>
								<input type="text" name="carType" class="form-control"
									value="${driverVO.carType}" disabled="disabled" />
								<div class="card" style="width: 18rem;"></div>
							</div>
							<div class="form-group">
								<p>駕照</p>
								<div class="card" style="width: 18rem;">
<%-- 									<img src="driver.do?driverID=<%=driverVO.getDriverID()%>&pic=1" width="300" --%>
<%-- 									<img src="<%=request.getContextPath()%>/driver/driver.do?driverID=${driverVO.driverID}&pic=1" width="300" --%>
<!-- 										height="150" class="card-img-top" alt="..."> -->
							<td >
						      <c:set var="licence" value="${driverVO.licence}" />
						      <%
						      byte[] licence = (byte[])pageContext.getAttribute("licence");
						      String encodeImg1 = null;
						      if(licence!=null){
						    	  encodeImg1 = Base64.encode(licence);%>
						    	  <img src="data:image/jpg;base64,<%=encodeImg1 %>" id="img1" width='400' height='350'>
						      <% }%>
						      </td>			
								</div>
							</div>
							<div class="form-group">
								<p>良民證</p>
								<div class="card" style="width: 18rem;">
<%-- 									<img src="driver.do?driverID=<%=driverVO.getDriverID()%>&pic=2" width="300" --%>
<%-- 									<img src="<%=request.getContextPath()%>/driver/driver.do?driverID=${driverVO.driverID}&pic=2" width="300" --%>
<!-- 										height="150" class="card-img-top" alt="..."> -->
										<!-- 						      <td> -->
						      <c:set var="criminal" value="${driverVO.criminal}" />
						      <%
 						      byte[] criminal = (byte[])pageContext.getAttribute("criminal");
 						      String encodeImg2 = null;
 						      if(criminal!=null){
						    	  encodeImg2 = Base64.encode(criminal);%> 
						    	  <img src="data:image/jpg;base64,<%=encodeImg2 %>" id="img2" width='400' height='350'>
						      <% }%>
						      </td>
								</div>
							</div>

							<div class="form-group">
								<p>肇事紀錄</p>
								<div class="card" style="width: 18rem;">
<%-- 									<img src="driver.do?driverID=<%=driverVO.getDriverID()%>&pic=3" width="300" --%>
<%-- 									<img src="<%=request.getContextPath()%>/driver/driver.do?driverID=${driverVO.driverID}&pic=3" width="300" --%>
<!-- 										height="150" class="card-img-top" alt="..."> -->
								
						      <td>
						      <c:set var="trafficRecord" value="${driverVO.trafficRecord}" />
						      <%
 						      byte[] trafficRecord = (byte[])pageContext.getAttribute("trafficRecord");
 						      String encodeImg3 = null;
 						      if(trafficRecord!=null){
						    	  encodeImg3 = Base64.encode(trafficRecord);%>
						    	  <img src="data:image/jpg;base64,<%=encodeImg3 %>" id="img3" width='400' height='350'>
						      <% }%>
						      </td>								
								</div>
							</div>
							<div class="form-group">
<%-- <%-- 						      <td>${driverVO.idNum}</td> --%> 
								<p>身分證</p>
								<div class="card" style="width: 18rem;">
<%-- 									<img src="driver.do?driverID=<%=driverVO.getDriverID()%>&pic=4" width="300" --%>
<%-- 									<img src="<%=request.getContextPath()%>/driver/driver.do?driverID=${driverVO.driverID}&pic=4" width="300" --%>
<%-- 									<img src="${driverVO.photo}" width="300" --%>
<!-- 										height="150" class="card-img-top" alt="..."> -->
						      <td>
						      <c:set var="idNum" value="${driverVO.idNum}" /> 
						      <%
						      byte[] idNum = (byte[])pageContext.getAttribute("idNum");
						      String encodeImg4 = null;
						      if(idNum!=null){
						    	  encodeImg4 = Base64.encode(idNum);%> 
						    	  <img src="data:image/jpg;base64,<%=encodeImg4 %>" id="img4" width='400' height='350'>
						      <% }%>
						      </td>
								</div>
							</div>


							<div class="form-group">
								<p>願意共乘載客:</p>
							  <c:if test="${driverVO.sharedCar == 0}">不接受共乘</c:if>
						      <c:if test="${driverVO.sharedCar == 1}">接受共乘</c:if>
							</div>

							<div class="form-group">
								<p>可載寵物:</p>
 							<c:if test="${driverVO.pet == 0}">不要寵物</c:if>
						      <c:if test="${driverVO.pet == 1}">寵物我可以</c:if>
							</div>

							<div class="form-group">
							<span class="input-group-text" id="basic-addon1">抽菸:</span>
							 <c:if test="${driverVO.smoke == 0}">不接受抽菸</c:if>
						      <c:if test="${driverVO.smoke == 1}">接受抽菸</c:if>
							</div>

							<div class="form-group">
							<span class="input-group-text" id="basic-addon1">提供嬰兒座椅:</span>
								<c:if test="${driverVO.babySeat == 0}">不提供</c:if>
						        <c:if test="${driverVO.babySeat == 1}">提供</c:if>
							</div>

							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1">審核狀態(驗證用)</span>
						      <c:if test="${driverVO.verified == 0}">未審核</c:if>
						      <c:if test="${driverVO.verified == 1}">審核成功</c:if>
								</div>
							</div>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1">BAN</span>
						      <c:if test="${driverVO.banned == 0}">可以接單</c:if>
						      <c:if test="${driverVO.banned == 1}">禁止接單</c:if>
								</div>
							</div>

							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1">在線狀態(後台看的...)</span>
									<c:if test="${driverVO.onlineCar == 0}">不在線上</c:if>
						     	    <c:if test="${driverVO.onlineCar == 1}">在線上 </c:if>
								</div>
							</div>
							
							 <div class="text-center">
							 			<p>審核驗證</p>
			<div><select name="verified">
			<option value="1" ${(driverVO.babySeat == '1')?'selected':'' }>審核成功
			<option value="2" ${(driverVO.babySeat == '2')?'selected':'' }>審核失敗
			</select></div>
				<input type="hidden" name="driverID" value="${driverVO.driverID}">
	<input type="hidden" name="actionP" value="GET_ONE_FORPERMIT">
	<input type="submit" value="審核">
			<input type="hidden" name="action" value="GET_ONE_FOR_CHECK">
							 </div> 
<!-- 							<input type="hidden" name="actionP" value="GET_ONE_FORPERMIT"> -->
<!-- 							 <div class="text-center"><button type="submit">確認修改</button></div>  -->
							<!--隱藏的參數action讓controller抓-->
							<input type="hidden" name="action" value="GET_ONE_FOR_CHECK">
						</form>
					</div>
<%-- 					<%} %> --%>
				</div>
			</div>
			<!-- row結尾 -->
		</div>
	</section>
	<!-- #contact -->
<jsp:include page="/back-end/kidFooter.jsp" />
</body>
</html>
