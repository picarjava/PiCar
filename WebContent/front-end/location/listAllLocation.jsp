<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
</head>
<body>
    <jsp:useBean id="service" class="com.location.model.LocationService"/>
    <table border="1">
    <tr><th>會員ID</th><th>地點</th><th colspan="2"></th></tr>
    <c:forEach var="location" items="${service.all}">
        <tr>
            <td>${location.memID}</td>
            <td>${location.location}</td>
            <td>
                <form action="<%=application.getContextPath()%>/location" method="POST">
                    <input type="hidden" name="memID" value="${location.memID}"/>
                    <input type="hidden" name="location" value="${location.location}"/>
                    <input type="hidden" name="action" value="getUpdateLocation"/>
                    <input type="submit" value="修改"/>
                </form>
            </td>
            <td>
                <form action="<%=application.getContextPath()%>/location" method="POST">
                    <input type="hidden" name="memID" value="${location.memID}"/>
                    <input type="hidden" name="location" value="${location.location}"/>
                    <input type="hidden" name="action" value="delete"/>
                    <input type="submit" value="刪除"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </table>
</body>
</html>