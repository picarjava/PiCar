<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.singleOrder.model.SingleOrderService"%>
<%@ page import="java.util.List"%>
<%@ page import="com.singleOrder.model.SingleOrderVO"%>
<% SingleOrderService service = new SingleOrderService();
   List<SingleOrderVO> list = service.getAll();
   pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>List single order</title>
    <style type="text/css">
        table {
            border-spacing:0;
        }
    </style>
</head>
<body>
    <c:if test="${not empty singleOrder}">
        <div id="errorMsg">
            <c:forEach var="errorMsg" items="errorMsgs">
                <p>${errorMsg}</p>
        </c:forEach>
        </div>
    </c:if>
    <%@include file="pageTop.file"%>
    <table border="1" >
        <caption>搜尋結果</caption>
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
            <th></th>
        </tr>
        <c:forEach var="singleOrder" items="${list}" begin="<%=fromIndex%>" end="<%=toIndex%>">
        <tr>
            <td>${singleOrder.orderID}</td>
            <td>${singleOrder.driverID}</td>
            <td>${singleOrder.memID}</td>
            <td>${stateMap[singleOrder.state]}</td>
            <td>${singleOrder.startTime}</td>
            <td>${singleOrder.endTime}</td>
            <td>${singleOrder.startLoc}</td>
            <td>${singleOrder.endLoc}</td>
            <td>${orderTypeMap[singleOrder.orderType]}</td>
            <td>${singleOrder.totalAmount}</td>
            <td>${singleOrder.rate}</td>
            <td>${singleOrder.note}</td>
            <td>${singleOrder.launchTime}</td>
            <td>
                <form action="<%=application.getContextPath()%>/singleOrder" method="POST">
                    <input type="hidden" name="orderID" value="${singleOrder.orderID}"/>
                    <input type="hidden" name="action" value="getUpdateID"/>
                    <button type="submit">修改</button>
                </form>
            </td>
        </tr>
        </c:forEach>
    </table>
    <%@include file="pageBot.file" %>
</body>
</html>