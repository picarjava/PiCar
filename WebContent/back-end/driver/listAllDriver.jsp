<%@page import="com.driver.model.DriverVO"%>
<%@page import="com.driver.model.DriverService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.driver.model.*" %>
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
                     
<%--                <form action="<%=request.getContextPath()%>/back-end/driver/司機會員管理.jsp"> --%>
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
						      <th scope="col">活動代碼	</th>
						      <th scope="col">活動名稱	</th>
						      <th scope="col">開始時間	</th>
						      <th scope="col">結束時間	</th>
<!-- 						      <th scope="col">活動海報	</th> -->
<!-- 						      <th scope="col">活動代幣領取明細 </th> -->
						      <th scope="col" colspan="2"><%@ include file="page1.file" %></th>
						    </tr>
						  </thead>
						  <tbody>
	
	<c:forEach var="driverVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >			  
		 	
					 		<tr>	 
						      <th scope="row">${driverVO.memID}</th>
						      <td>${driverVO.driverID}</td>
						      <td>${activityVO.activityName}</td>
						      <td>${activityVO.activityName}</td>
						      <td>${activityVO.activityStart}</td>
						      <td>${activityVO.activityEnd}</td>
<!-- 						      <td> -->
<%-- 						       	  <c:if test="${empty activityVO.activityPost}" var="condition"> --%>
<%-- 					              <img src="<%=request.getContextPath()%>/regna-master/img/noFileUpdate.JPG" width="200" height="100"/> --%>
<%-- 					              </c:if> --%>
<%-- 					              <c:if test="${not empty activityVO.activityPost}" var="condition"> --%>
<%-- 					              <img  src='<%=request.getContextPath()%>/activity/Activ_servlet.html?activityID=${activityVO.activityID}' width='200' height='100' alt='"這是"+${activityVO.activityID}+"的活動海報"  '/> --%>
<%-- 					              </c:if> --%>
<!-- 						      </td> -->
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