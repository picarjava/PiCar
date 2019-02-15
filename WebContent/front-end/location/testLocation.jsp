<%@page contentType="text/html;" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Location</title>
</head>
<body>
    <form action="/PiCar/locationServlet" method="POST">
        <input type="text" name="memId"/>
        <input type="text" name="location"/>
        <input type="hidden" name="action" value="select"/>
        <input type="submit"/>
    </form>
</body>
</html>