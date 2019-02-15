<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert single order</title>
</head>
<body>
    <h3>新增訂單</h3>
    <form action="/PiCar/singleOrder" method="POST">
        <label for="memId">會員ID</label><input type="text" name="memId" id="memID"/><br>
        <label for="startTime">上車時間</label><input type="date" name="startTime" id="startTime"/><br>
        <label for="endTime">下車時間</label><input type="date" name="endTime" id="endTime"/><br>
        <label for="startLoc">上車地點</label><input type="text" name="startLoc" id="startLoc"/><br>
        <label for="endLoc">上車地點</label><input type="text" name="endLoc" id="endLoc"/><br>
        <label for="orderType">訂單種類</label>
        <select name="endLoc" id="endLoc">
            <% HashMap<String, String> map = (HashMap<String, String>) application.getAttribute("orderTypeMap");
               for (String oT:map.keySet()) {%>
                   <option value=<%=oT%>><%=map.get(oT)%></option>
             <%}//for ()%>
        </select><br>
    </form>
</body>
</html>