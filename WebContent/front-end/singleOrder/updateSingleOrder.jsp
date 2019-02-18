<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Update single order</title>

</head>
<body>
    <c:if test="${not empty errorMsgs}">
    <h3>以下錯誤</h3>
    <div id="errorMsg">
        <c:forEach var="errorMsg" items="${errorMsgs}">
        <p>${errorMsg}</p>
        </c:forEach>
    </div>
    </c:if>
    <c:if test="${not empty singleOrder}">
    <form action="/PiCar/singleOrder" method="POST">
        <input type="hidden" name="orderID" value="${singleOrder.orderID}"/>
                    訂單ID ${singleOrder.orderID}<br>
        <label for="driverID">司機ID</label><input type="text" name="driverID" value="${singleOrder.driverID}" id="driverID"/><br>
        <label for="state">狀態</label>
        <select name="state" id="state">
            <c:forEach var="state" items="${stateMap}">
                <option value="${state.key}" ${state.key eq singleOrder.state? "selected" : ""}>${state.value}</option>
            </c:forEach>
        </select><br>
        <label for="startTime">開始時間</label><input type="date" name="startTime" value="${singleOrder.startTime}" id="startTime"/><br>
        <label for="endTime">結束時間</label><input type="date" name="endTime" value="${singleOrder.endTime}" id="endTime"/><br>
        <label for="startLoc">上車地點</label><input type="text" name="startLoc" value="${singleOrder.startLoc}" id="startLoc"/><br>
        <label for="endLoc">下車地點</label><input type="text" name="endLoc" value="${singleOrder.endLoc}" id="endLoc"/><br>
        ${orderTypeMap[singleOrde.orderType]}<br>
        <c:forEach var="index" begin="1" end="5">
        <input type="radio" name="rate" value="${index}" id="rate${index}"/><label for="rate${index}">${index}分</label>
        </c:forEach><br>
        <input type="hidden" name="action" value="update"/>
        <input type="submit">
    </form>
    </c:if>
</body>
</html>