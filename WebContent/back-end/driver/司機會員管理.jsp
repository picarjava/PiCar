<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.driver.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="/regna-master/head.jsp" />
    <title>司機會員管理</title>

</head>

<body>
    <!-- 先取出Driver_VO -->
    <%
		DriverVO driverVO = (DriverVO) request.getAttribute("driverVO");
	%>
    <!-- 	// -->
    <section id="contact">
        <div class="container wow fadeInUp">
            <div class="col-lg-12 col-md-12">
                <div class="section-header">
                    <h3 class="section-title">司機會員管理(後端_更改司機)</h3>
                    <p class="section-description">請查看與修改資料</p>
                </div>
                <button type="button" class="btn btn-dark "><a href="後台首頁.jsp">回首頁</a></button>
            </div>
        </div>
        <div class="container wow fadeInUp">
            <div class="row justify-content-center">
                <div class="col-lg-12 col-md-12">
                    <!-- 查詢nav bar開始 -->
                    <nav class="navbar navbar-light bg-light">
                        <ul class="nav nav-tabs">
                            <li class="nav-item">
                                <form class="form-inline" <%-- action="<%=request.getContextPath()%>/back-end/driver/listOneDriver.jsp" --%>
                                    action="
                                    <%=request.getServletContext().getContextPath()%>/back-end/driver/driver.do"
                                    method="post">
                                    <input class="form-control mr-sm-2" name="driverID" type="text" placeholder="請輸入司機編號(eg.D001)" aria-label="Search">
                                    <!-- 									type="search" placeholder="請輸入司機編號(eg.D001)" aria-label="Search"> -->
                                    <!--隱藏的參數action讓controller抓-->
                                    <input type="hidden" name="action" value="GET_ONE_BACK">
                                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">查詢一筆司機</button>
                                </form>
                            </li>
                            <!-- 								以下給前端司機的controller -->
                            <!-- 								  <li> -->
                            <!--     <FORM METHOD="post" ACTION="broadcast.do" > -->
                            <!--         <b>輸入推播訊息編號 (如MSG001):</b> -->
                            <!--         <input type="text" name="msgID"> -->
                            <!--         <input type="hidden" name="action" value="getOne_For_Display"> -->
                            <!--         <input type="submit" value="送出"> -->
                            <li class="nav-item">
                                <form class="form-inline" action="<%=request.getContextPath()%>/back-end/driver/listAllDriver.jsp" method="post">
                                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">查詢全部司機</button>
                                </form>
                            </li>
                            <jsp:useBean id="driversrV" scope="page" class="com.driver.model.DriverService" />
                            <li>
                                <form method="post" action="<%=request.getServletContext().getContextPath()%>/back-end/driver/driver.do">
                                    <b>請選擇編號</b> <br>
                                    <select size="1" name="driverID">
                                        <c:forEach var="driverVO" items="${driversrV.all}">
                                            <option value="${driverVO.driverID}">${driverVO.driverID}
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" name="action" value="GET_ONE_BACK"><br>
                                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">查詢一筆司機</button>
                                </form>
                            </li>
                            <!-- 							<li class="nav-item"> -->
                            <!-- 								<form class="form-inline" -->
                            <%-- 									action="<%=request.getContextPath()%>/back-end/driver/addDriver.jsp" --%>
                            <!-- 									method="post"> -->
                            <!-- 									<button class="btn btn-outline-success my-2 my-sm-0" -->
                            <!-- 										type="submit">驗證司機</button> -->
                            <!-- 								</form> -->
                            <!-- 							</li> -->
                        </ul>
                        <br>
                        <li class="nav-item">
                            <form class="form-inline" <%-- action="<%=request.getContextPath()%>/back-end/driver/listOneDriver.jsp" --%>
                                action="
                                <%=request.getServletContext().getContextPath()%>/front-end/driver/driver.do"
                                method="post">
                                <input class="form-control mr-sm-2" name="driverID" type="text" placeholder="請輸入司機編號(eg.D001)" aria-label="Search">
                                <!-- 									type="search" placeholder="請輸入司機編號(eg.D001)" aria-label="Search"> -->
                                <!--隱藏的參數action讓controller抓-->
                                <input type="hidden" name="action" value="GET_ONE_FRONT">
                                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">司機資料管理</button>
                            </form>
                        </li>
                    </nav>
                    <!-- 查詢nav bar結束 -->
                </div>
                <div class="col-lg-12 col-md-12"></div>
                <div class="col-lg-12 col-md-12"></div>
            </div>
            <!-- row結尾 -->
        </div>
    </section>
    <!-- #contact -->
    <!-- 	// -->
    <!-- 			UPDATE DRIVER SET VERIFIED = '1', BANNED= '1' WHERE DRIVER_ID='D003'; -->
    <!-- 			BANNED人家使用 -->
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
    <jsp:include page="/regna-master/body.jsp" />
</body>

</html>