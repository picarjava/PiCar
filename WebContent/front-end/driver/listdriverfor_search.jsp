<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.driver.model.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
 <jsp:include page="/regna-master/head.jsp" />
</head>

<body>
    <!--==========================
      Contact Section
    ============================-->
    <!-- 先取出VO -->
  <%DriverVO driverVO=(DriverVO)request.getAttribute("driverVO");%>
  
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
                  <h3 class="section-title">請查看單筆司機資訊</h3>
                  <div class="text-center">
                  <!-- <form action="homeActivity.jsp">設返回頁面 -->
                  <form action="https://www.google.com">

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
                       <p>車牌號碼</p>
                      <input type="text" name="plateNum" class="form-control" value="${driverVO.plateNum}"   placeholder="請輸入車牌號碼" />
                       <div class="card" style="width: 18rem;">
                          <img src="${driverVO.plateNum}" width="300" height="150" class="card-img-top" alt="..." >
                        </div>
                    </div>
                    <div class="form-group">
                       <p>車型</p>
                      <input type="text" name="carType" class="form-control" value="${driverVO.carType}"   placeholder="請輸入車型" />
                       <div class="card" style="width: 18rem;">
                          <img src="${driverVO.carType}" width="300" height="150" class="card-img-top" alt="..." >
                        </div>
                    </div>
                    <div class="form-group">
                      <p>駕照</p>
                      <input type="file" class="form-control" name="licence" value="${driverVO.licence}" placeholder="請輸入駕照"  /> 
                       <div class="card" style="width: 18rem;">
                          <img src="${driverVO.licence}" width="300" height="150" class="card-img-top" alt="..." >
                        </div>      
                   </div>
                    <div class="form-group">
                      <p>良民證</p>
                      <input type="file" class="form-control" name="criminal" value="${driverVO.criminal}" placeholder="請輸入肇事紀錄"  />
                       <div class="card" style="width: 18rem;">
                          <img src="${driverVO.criminal}" width="300" height="150" class="card-img-top" alt="..." >
                        </div>
                    </div>

                    <div class="form-group">
                      <p>肇事紀錄</p>
                      <input type="file" class="form-control" name="trafficRecord" value="${driverVO.trafficRecord}" placeholder="請輸入肇事紀錄"  />
                       <div class="card" style="width: 18rem;">
                          <img src="${driverVO.trafficRecord}" width="300" height="150" class="card-img-top" alt="..." >
                        </div>
                    </div>
                    
                    <div class="form-group">
                      <p>身分證</p>
                      <input type="file" class="form-control" name="idNum" value="${driverVO.idNum}" placeholder="請輸入身分證"  />
                       <div class="card" style="width: 18rem;">
                          <img src="${driverVO.idNum}" width="300" height="150" class="card-img-top" alt="..." >
                        </div>
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
  ============================-->
 
  <jsp:include page="/regna-master/body.jsp" />

</body>
</html>
