<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.driver.model.*"%>
<%
	DriverService driverSvc = new DriverService();
	List<DriverVO> list = driverSvc.getAll();
	pageContext.setAttribute("list", list);
	DriverVO driverVO = (DriverVO) request.getAttribute("driverVO");
%>
<!doctype html>
<html lang="zh">
<head>
    <title>司機會員管理</title>
    <jsp:include page="/back-end/kidHead.jsp" />
</head>
<body>
    <div class="wrapper ">
        <jsp:include page="/back-end/kidBodyLeft.jsp" />
        <div class="main-panel">
            <jsp:include page="/back-end/kidNavbar.jsp" />
            <div class="content">
                <div class="container-fluid">
                    <!-- your content here -->
                    <div class="container-fluid">
                        <div class="col-9">
                            <%-- 錯誤表列 --%>
                            <c:if test="${not empty errorMsgs}">
                                <font style="color: red" id="error">請修正以下錯誤：</font>
                                <ul>
                                    <c:forEach var="message" items="${errorMsgs}">
                                        <li style="color: red">${message}</li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                        </div>
                        <table id="t1">
                            <tr>
                                <th colspan="2">查詢一筆司機</th>
                                <th>查詢全部司機</th>
                            </tr>
                            <%@ include file="page1.file"%>
                            <tr>
                                <td>
                                    <form class="form-inline" <%-- action="<%=request.getContextPath()%>/back-end/driver/listOneDriver.jsp" --%>
                                        action="
                                        <%=request.getServletContext().getContextPath()%>/back-end/driver/driver.do"
                                        method="post">
                                        <input class="form-control mr-sm-2" name="driverID" type="text" placeholder="請輸入司機編號(eg.D001)" aria-label="Search">
                                        <!--隱藏的參數action讓controller抓-->
                                        <input type="hidden" name="action" value="GET_ONE_BACK">
                                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">查詢一筆司機</button>
                                    </form>
                                </td>
                                <jsp:useBean id="driversrV" scope="page" class="com.driver.model.DriverService" />
                                <td>
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
                                </td>
                                <td>
                                    <form class="form-inline" action="<%=request.getContextPath()%>/back-end/driver/listAllDriver.jsp" method="post">
                                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">查詢全部司機</button>
                                    </form>
                                </td>
                            </tr>
                        </table>
                        <%@ include file="page2.file"%>
                    </div>
                    
                </div>
                <jsp:include page="/back-end/kidFooter.jsp" />
            </div>
        </div>
        <jsp:include page="/back-end/kidBodyscript.jsp" />
</body>
</html>