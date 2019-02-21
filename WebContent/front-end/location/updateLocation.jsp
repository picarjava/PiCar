<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>update location</title>
</head>
<body>
    <c:if test="${not empty errorMsgs}">
        <h3>錯誤</h3>
        <c:forEach var="errorMsg" items="${errorMsgs}">
            <p>${errorMsg}</p>
        </c:forEach>
    </c:if>
    <form action="<%=application.getContextPath()%>/location" method="POST">
                會員ID ${location.memID}<br>
       <label>地點</label><input type="text" name="newLocation" value="${location.location}"/><br>
       <input type="hidden" name="memID" value="${location.memID}"/>
       <input type="hidden" name="location" value="${location.location}"/>
       <input type="hidden" name="action" value="update"/>
       <input type="submit" value="修改"/>
    </form>
</body>
</html>