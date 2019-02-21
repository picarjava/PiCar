<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>one location</title>
</head>
<body>
    <table border="1">
    <tr><th>會員ID</th><th>地點</th></tr>
        <tr>
            <td>${location.memID}</td><td>${location.location}</td>
        </tr>
    </table>
</body>
</html>