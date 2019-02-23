<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Search result</title>
</head>
<body>
    <table border="1" >
        <tr>
            <th>訂單編號</th>
            <th>司機ID</th>
            <th>會員ID</th>
            <th>狀態碼</th>
            <th>上車時間</th>
            <th>下車時間</th>
            <th>上車地點</th>
            <th>下車地點</th>
            <th>訂單種類</th>
            <th>總金額</th>
            <th>評價分數</th>
            <th>備註</th>
            <th>建立時間</th>
        </tr>
        <tr>
            <td>${singleOrder.orderID}</td>
            <td>${singleOrder.driverID}</td>
            <td>${singleOrder.memID}</td>
            <td>${singleOrder.state}</td>
            <td><fmt:formatDate value="${singleOrder.startTime}" pattern="yyyy-MM-dd mm:ss"/></td>
            <td><fmt:formatDate value="${singleOrder.endTime}" pattern="yyyy-MM-dd mm:ss"/></td>
            <td>${singleOrder.startLoc}</td>
            <td>${singleOrder.endLoc}</td>
            <td>${singleOrder.orderType}</td>
            <td>${singleOrder.totalAmount}</td>
            <td>${singleOrder.rate}</td>
            <td>${singleOrder.note}</td>
            <td>${singleOrder.launchTime}</td>
       </tr>
    </table>
</body>
</html>