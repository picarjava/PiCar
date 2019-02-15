<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.location.model.LocationService" %>
<% LocationService service = new LocationService();
   pageContext.setAttribute("allLocation", service.getAll());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
    <style type="text/css">
        table {
	        border-spacing:0;
	    }
    </style>
</head>
<body>
    <table border="1" >
        <tr><th>會員編號</th><th>地點</th></tr>
	    <c:forEach var="location" items="${allLocation}">
	        <tr><td>${location.memId}</td><td>${location.location}</td></tr>
	    </c:forEach>
    </table>
</body>
</html>