<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert single order</title>
<style>
    #errorMsg > p {
        color: red;
    }
    
    #errorMsg > p:before {
        content:'＊';
    }
</style>
</head>
<body>
    <h3>新增訂單</h3>
    <c:if test="${not empty errorMsgs }">
        <h3>資料未填或格式錯誤</h3>
        <div id="errorMsg">
            <c:forEach var="errorMsg" items="${errorMsgs}">
                <p>${errorMsg}</p>
            </c:forEach>
        </div>
    </c:if>
    <form action="/PiCar/singleOrder" method="POST">
        <label for="memId">會員ID </label><input type="text" name="memID" id="memID" value="${singleOrder.memID}"/><br>
        <label for="startTime">上車時間 </label><input type="date" name="startTime" id="startTime" value="${singleOrder.startTime}"/><br>
        <label for="startLoc">上車地點 </label><input type="text" name="startLoc" id="startLoc" value="${singleOrder.startLoc}"/><br>
        <label for="endLoc">下車地點 </label><input type="text" name="endLoc" id="endLoc" value="${singleOrder.endLoc}"/><br>
        <label for="orderType">訂單種類 </label>
        <select name="orderType" id="orderType">
            <c:forEach var="orderType" items="${orderTypeMap}">
                <option value="${orderType.key}" ${orderType.key eq singleOrder.orderType ? "selected": ""}>${orderType.value}</option>
            </c:forEach>
        </select><br>
        <label for="note">備註 </label><br>
        <textarea name="note" id="note" rows="3" cols="50">${singleOrder.note}</textarea><br>
        <input type="hidden" name="action" value="insert"/>
        <input type="submit"/>
    </form>
</body>
</html>