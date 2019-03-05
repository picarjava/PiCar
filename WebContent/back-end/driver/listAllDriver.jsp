<%@page import="com.driver.model.DriverVO"%>
<%@page import="com.driver.model.DriverService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.driver.model.*" %>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64" %>
<%@ page import="java.util.*" %>


<!DOCTYPE html>
<html lang="zh">

<head>
    <title>後台所有司機列表</title>
    <jsp:include page="/regna-master/head.jsp" />
</head>

<body>
    <!--==========================
     司機listALL
    ============================-->
   <!-- getAll再setAttribute存進pageContext 給forEach抓 -->
    <% DriverService driverSvc=new DriverService();%>
    <%List<DriverVO> list=driverSvc.getAll();%>
    <%request.setAttribute("list", list); %>
    <%if(list!=null&&(list.size()>0)){ %>
    
    <%LinkedList errorMsgs=(LinkedList<String>)request.getAttribute("errorMsgs");%>
    <!-- 錯誤列表 -->
    
    <c:if test="${not empty errorMsgs}"><ul class="list-group">
		  <li class="list-group-item active">Opps!錯誤訊息回報</li>
		  <c:forEach var="massage" items="${errorMsgs}">
		  <li class="list-group-item">${massage}</li>
		  </c:forEach>
		</ul>
	</c:if>
   
 
        <section id="contact">
            <div class="container wow fadeInUp">
                <div class="section-header">
                    <h3 class="section-title">後台所有司機列表</h3>
                    <form action="司機會員管理.jsp">
			          <div class="text-center"><button type="submit" class="btn btn-outline-success">返回</button></div>
			         </form>
                </div>
            </div>
            <div class="container wow fadeInUp">
                        <table class="table">
						  <thead class="thead-dark">
						    <tr>
						      <th scope="col">會員編號	</th>
						      <th scope="col">司機編號	</th>
						      <th scope="col">車牌號碼	</th>
						      <th scope="col">駕照	</th>
						      <th scope="col">良民證	</th>
						      <th scope="col">肇事紀錄	</th>
						      <th scope="col">身分證	</th>
						      <th scope="col">大頭照	</th>
						      <th scope="col">審核驗證	</th>
						      <th scope="col">BAN	</th>
						      <th scope="col">到期時間	</th>
						      <th scope="col">是否線上	</th>
						      <th scope="col">評價分數	</th>
						      <th scope="col">品牌	</th>
						      <th scope="col">共享	</th>
						      <th scope="col">寵物	</th>
						      <th scope="col">抽菸	</th>
						      <th scope="col">嬰兒座椅	</th>
						      <th scope="col" colspan="2"><%@ include file="page1.file" %></th>
						    </tr>
						  </thead>
						  <tbody>
	
	<c:forEach var="driverVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >			  
		 	
					 		<tr>	 
						      <th scope="row">${driverVO.memID}</th>
						      <td>${driverVO.driverID}</td>
						      <td>${driverVO.plateNum}</td>
<%-- 						      <td>${driverVO.licence}</td> --%>
						      <td>
						      <c:set var="licence" value="${driverVO.licence}" />
						      <%
						      byte[] licence = (byte[])pageContext.getAttribute("licence");
						      String encodeImg1 = null;
						      if(licence!=null){
						    	  encodeImg1 = Base64.encode(licence);%>
						    	  <img src="data:image/jpg;base64,<%=encodeImg1 %>" id="img1">
						      <% }%>
						      </td>						      
<%-- 						      <td>${driverVO.criminal}</td> --%>
						      <td>
						      <c:set var="criminal" value="${driverVO.criminal}" />
						      <%
						      byte[] criminal = (byte[])pageContext.getAttribute("criminal");
						      String encodeImg2 = null;
						      if(criminal!=null){
						    	  encodeImg2 = Base64.encode(criminal);%>
						    	  <img src="data:image/jpg;base64,<%=encodeImg2 %>" id="img2">
						      <% }%>
						      </td>
<%-- 						      <td>${driverVO.trafficRecord}</td> --%>
						      <td>
						      <c:set var="trafficRecord" value="${driverVO.trafficRecord}" />
						      <%
						      byte[] trafficRecord = (byte[])pageContext.getAttribute("trafficRecord");
						      String encodeImg3 = null;
						      if(trafficRecord!=null){
						    	  encodeImg3 = Base64.encode(trafficRecord);%>
						    	  <img src="data:image/jpg;base64,<%=encodeImg3 %>" id="img3">
						      <% }%>
						      </td>
<%-- 						      <td>${driverVO.idNum}</td> --%>
						      <td>
						      <c:set var="idNum" value="${driverVO.idNum}" />
						      <%
						      byte[] idNum = (byte[])pageContext.getAttribute("idNum");
						      String encodeImg4 = null;
						      if(idNum!=null){
						    	  encodeImg4 = Base64.encode(idNum);%>
						    	  <img src="data:image/jpg;base64,<%=encodeImg4 %>" id="img4">
						      <% }%>
						      </td>
<%-- 						      <td>${driverVO.photo}</td> --%>
                              <td>
						      <c:set var="photo" value="${driverVO.photo}" />
						      <%
						      byte[] photo = (byte[])pageContext.getAttribute("photo");
						      String encodeImg5 = null;
						      if(photo!=null){
						    	  encodeImg5 = Base64.encode(photo);%>
						    	  <img src="data:image/jpg;base64,<%=encodeImg5 %>" id="img5">
						      <% }%>
						      </td>
<!-- 						      <td> -->
<%-- 						       	  <c:if test="${empty driverVO.photo}" var="condition"> --%>
<%-- 					              <img src="<%=request.getContextPath()%>/regna-master/img/noFileUpdate.JPG" width="200" height="100"/> --%>
<%-- 					              </c:if> --%>
<%-- <%-- 					              <img  src='<%=request.getContextPath()%>/driver/Activ_servlet.html?activityID=${activityVO.activityID}' width='200' height='100' alt='"這是"+${activityVO.activityID}+"的活動海報"  '/> --%> --%>
<%-- 					              <c:if test="${not empty driverVO.photo}" var="condition"> --%>
<%-- 					              <img  src='<%=request.getContextPath()%>/driver.do?driverID=${driverVO.driverID}' width='200' height='100' alt='"這是"+${driverVO.driverID}+"的活動海報"  '/> --%>
<%-- 					              </c:if>  --%>
<!-- 						      </td> -->
						      <td>${driverVO.verified}</td>
						      <td>${driverVO.banned}</td>
						      <td>${driverVO.deadline}</td>
						      <td>${driverVO.onlineCar}</td>
						      <td>${driverVO.score}</td>
						      <td>${driverVO.carType}</td>
						      <td>${driverVO.sharedCar}</td>
						      <td>${driverVO.pet}</td>
						      <td>${driverVO.smoke}</td>
						      <td>${driverVO.babySeat}</td>

<!-- 						       <td> -->
<%-- 						      <Form METHOD="post" ACTION="<%=request.getContextPath()%>/activityToken/ActivTokenServlet" > --%>
<!-- 							    <div class="text-center"><button type="submit" class="btn btn-light">查看領取名單</button> -->
<!-- 							      	/*放隱藏的標籤，讓Controller抓到參數進行操作*/ -->
<!-- 	                				<input type="hidden" name="action" value="GET_ALL_STMT"> -->
<%-- 	                				<input type="hidden" name="activityID" value="${activityVO.activityID}"> --%>
<!-- 							     </div> -->
<!-- 							  </Form> -->
<!-- 						      </td> -->
						      <td>
						      <Form METHOD="post" ACTION="<%=request.getContextPath()%>/activity/Activ_servlet.html" >
							    <div class="text-center"><button type="submit" class="btn btn-light">修改</button>
							      	<!-- /*放隱藏的標籤，重複使用activityVO，讓Controller抓到參數進行操作*/ -->
	                				<input type="hidden" name="action" value="GET_ONE_FOR_CHECK">
	                				<input type="hidden" name="driverID" value="${driverVO.driverID}">
							     </div>
							  </Form>
						      </td>
						    </tr>
	</c:forEach>	
 						</tbody>
						</table>
						<%@ include file="page2.file" %>
            </div>
        </section>
  
    <%}%>
    <!--==========================
    底部
  ============================-->
    
    <jsp:include page="/regna-master/body.jsp" />
</body>

</html>