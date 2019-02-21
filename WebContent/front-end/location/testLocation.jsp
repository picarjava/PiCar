<%@page import="com.location.model.LocationVO"%>
<%@page import="com.location.model.LocationService"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>testLocation</title>
</head>
<body>
    <a href="listAllLocation.jsp">ALL</a>
    <% LocationService service = new LocationService();
       Set<String> memIDSet = new HashSet<>();
       Set<String> locationSet = new HashSet<>();
       for (LocationVO location: service.getAll()) {
           memIDSet.add(location.getMemID());
           locationSet.add(location.getLocation());
       } // for
       
       pageContext.setAttribute("memIDSet", memIDSet);
       pageContext.setAttribute("locationSet", locationSet);
    %>
    <c:forEach var="location" items="${service.all}">
    </c:forEach>
    <form action="<%=application.getContextPath()%>/location">
        <label>會員編號</label>
        <select name="memID">
            <c:forEach var="memID" items="${memIDSet}">
            <option value="${memID}">${memID}</option>
            </c:forEach>
        </select>
        <label>地點</label>
        <select name="location">
            <c:forEach var="location" items="${locationSet}">
            <option value="${location}">${location}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="action" value="select"/>
        <input type="submit"/>
    </form>
</body>
</html>