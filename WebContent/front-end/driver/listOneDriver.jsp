<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.driver.model.*" %>
<%@ page import="com.member.model.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
  <title>司機資料管理</title>

<jsp:include page="/regna-master/head.jsp" />
  
</head>

<body>
    <!--==========================
      Contact Section
    ============================-->
    <!-- 先取出VO -->
  <%DriverVO driverVO=(DriverVO)request.getAttribute("driverVO");%>
  <%MemberVO memberVO=(MemberVO)request.getAttribute("memberVO");%>
    <!-- 錯誤列表 -->
    <%LinkedList errorMsgs=(LinkedList<String>)request.getAttribute("errorMsgs");%>
    <c:if test="${not empty errorMsgs}"><ul class="list-group">
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
                  <h3 class="section-title">司機個人資料</h3>
                  <div class="text-center">
                  <!-- <form action="homeActivity.jsp">設返回頁面 -->
                  <form action="司機資料管理.jsp">
                  <button type="submit" >返回</button>
                  </form>
                  </div>                
                </div>  
            </div>      
         </div>
         <div class="container wow fadeInUp">
            <div class="row justify-content-center">
              <div class="col-lg-12 col-md-12">
                <div class="form">
                  <!-- 活動表單開始 -->
                  <form action="https://www.google.com" method="post" role="form" class="contactForm">
                    <!-- <form action="<%=request.getContextPath()%>/activity/Activ_servlet.html" method="post" role="form" class="contactForm"> -->
                     <div class="form-group">
                      <p>會員編號</p> 
                     <input type="text" name="memID"  readonly  value="${driverVO.memID}" class="form-control"   />
                    </div>
                     <div class="form-group">
                      <p>司機編號</p> 
                     <input type="text" name="driverID"  readonly  value="${driverVO.driverID}" class="form-control"   />
                    </div>
                    <div class="form-group">
                       <p>車牌號碼</p>
                      <input type="text" name="plateNum" class="form-control" value="${driverVO.plateNum}" readonly  placeholder="請輸入車牌號碼" />
                       <div class="card" style="width: 18rem;">
                        </div>
                    </div>
                    <div class="form-group">
                      <p>駕照</p>
                      <input type="file" class="form-control" name="licence" value="${driverVO.licence}" placeholder="請輸入駕照"  /> 
                       <div class="card" style="width: 18rem;">
<%--                           <img src="driver.do?driverID=<%=driverVO.getDriverID()%>&pic=1" width="300" height="150" class="card-img-top" alt="..." > --%>
                          <img src="driver.do?driverID=D003&pic=1" width="300" height="150" class="card-img-top" alt="..." >
                        </div>      
                   </div>
                    <div class="form-group">
                      <p>良民證</p>
                      <input type="file" class="form-control" name="criminal" value="${driverVO.criminal}" placeholder="請輸入肇事紀錄"  />
                       <div class="card" style="width: 18rem;">
<%--                           <img src="driver.do?driverID=<%=driverVO.getDriverID()%>&pic=2" width="300" height="150" class="card-img-top" alt="..." > --%>
                          <img src="driver.do?driverID=D003&pic=2" width="300" height="150" class="card-img-top" alt="..." >
                        </div>
                    </div>
<%-- <img src="/PiCar/GroupBand?groupID=<%=groupBandVO.getGroupID()%>"  --%>
                    <div class="form-group">
                      <p>肇事紀錄</p>
                      <input type="file" class="form-control" name="trafficRecord" value="${driverVO.trafficRecord}" placeholder="請輸入肇事紀錄"  />
                       <div class="card" style="width: 18rem;">
<%--                           <img src="driver.do?driverID=<%=driverVO.getDriverID()%>&pic=3" width="300" height="150" class="card-img-top" alt="..." > --%>
                          <img src="driver.do?driverID=D003&pic=3" width="300" height="150" class="card-img-top" alt="..." >
                        </div>
                    </div>
                    <div class="form-group">
                      <p>身分證</p>
                      <input type="file" class="form-control" name="idNum" value="${driverVO.idNum}" placeholder="請輸入身分證"  />
                       <div class="card" style="width: 18rem;">
<%--                           <img src="driver.do?driverID=<%=driverVO.getDriverID()%>&pic=4"  width="300" height="150" class="card-img-top" alt="..." > --%>
                          <img src="driver.do?driverID=D003&pic=4"  width="300" height="150" class="card-img-top" alt="..." >
                        </div>
                    </div>
                    
                    <div class="form-group">
                      <p>大頭照</p>
                      <input type="file" class="form-control" name="photo" value="${driverVO.photo}" />
                       <div class="card" style="width: 18rem;">
<%--                           <img src="driver.do?driverID=<%=driverVO.getDriverID()%>&pic=5"  width="300" height="150" class="card-img-top" alt="..." > --%>
                          <img src="driver.do?driverID=D003&pic=5"  width="300" height="150" class="card-img-top" alt="..." >
<%--                                <img src="http://localhost:8081/PiCar/front-end/member/member.do?memID=${memberVO.memID}"  width='200' height="200">                           --%>
                        </div>
                    </div>
                    <div class="form-group">
                       <p>評價分數</p>
                      <input type="text" name="Score" class="form-control" value="${driverVO.carType}"   placeholder="請輸入車型" />
                       <div class="card" style="width: 18rem;">
                        </div>
                    </div>
                    <div class="form-group">
                       <p>車型</p>
                      <input type="text" name="carType" class="form-control" value="${driverVO.carType}"   placeholder="請輸入車型" />
                       <div class="card" style="width: 18rem;">
                        </div>
                    </div>
                            <div class="form-group">
                                <p>願意共乘載客</p>
                                <input type="text" name="sharedCar"  readonly  value="${driverVO.sharedCar}" class="form-control"   />
                           </div>

                            <div class="form-group">
                                <p>可載寵物</p>
                                <input type="text" name="pet"  readonly  value="${driverVO.pet}" class="form-control"   />
                            </div>

                            <div class="form-group">
                                <p>抽菸</p>
                                <input type="text" name="smoke"  readonly  value="${driverVO.smoke}" class="form-control"   />
                            </div>

                            <div class="form-group">
                                <p>提供嬰兒座椅</p>
                                <input type="text" name="babySeat"  readonly  value="${driverVO.babySeat}" class="form-control"   />
                            </div>
                  <!--  <div class="text-center"><button type="submit">確認修改</button></div>  -->
                    <!--隱藏的參數action讓controller抓-->
                    <!-- <input type="hidden" name="action" value="UPTDATE"> -->
                  </form>
                </div>
              </div>
            </div><!-- row結尾 -->
          </div>
    </section><!-- #contact -->
  <!--==========================
    底部
    <!--==========================
    Footer
  ============================-->
	<footer id="footer">
		<div class="footer-top">
			<div class="container"></div>
		</div>

		<div class="container">
			<div class="copyright">
				&copy; Copyright <strong>Regna</strong>. All Rights Reserved
			</div>
			<div class="credits">
				<!--
          All the links in the footer should remain intact.
          You can delete the links only if you purchased the pro version.
          Licensing information: https://bootstrapmade.com/license/
          Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/buy/?theme=Regna
        -->
				Bootstrap Templates by <a href="https://bootstrapmade.com/">BootstrapMade</a>
			</div>
		</div>
	</footer>
	<!-- #footer -->
<!--   ============================--> 
 
  <jsp:include page="/regna-master/body.jsp" />

</body>
</html>
