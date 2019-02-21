<%@page contentType="text/html;" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Location</title>
</head>
<body>
    <div class="container">
        <div>
            <c:if test="${not empty errorMsgs }">
                <h3>資料未填或格式錯誤</h3>
                <c:forEach var="errorMsg" items="${errorMsgs}">
                <p>${errorMsg}</p>
                </c:forEach>
            </c:if>
            <a href="listAllSingleOrder.jsp">列出全部</a>
            <form action="/PiCar/singleOrder" method="POST">
                <label for="orderID">訂單編號</label>
                <input type="text" name="orderID" id="orderID"/>
                <input type="hidden" name="action" value="select"/>
                <input type="submit"/>
            </form>
        </div>
        <div>
        <jsp:useBean id="service" scope="page" class="com.singleOrder.model.SingleOrderService"/>
        <form action="/PiCar/singleOrder" method="POST">
            <label for="orderID">訂單編號</label>
            <select name="orderID">
                <c:forEach var="singleOrder" items="${service.all}">
                    <option value="${singleOrder.orderID}">${singleOrder.orderID}</option>
                </c:forEach>
            </select>
            <input type="hidden" name="action" value="select"/>
            <input type="submit"/>
        </form>
        </div>
        <a href="addSingleOrder.jsp">新增訂單</a>
    </div>
</body>
</html>