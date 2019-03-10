<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page  import="com.driver.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update_driver_input.jsp</title>
<!-- <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script> -->
</head>

<body>

<%
	DriverVO driverVO =  (DriverVO)request.getAttribute("driverVO");
%>
<form method="post" id="sub" action="<%=request.getServletContext().getContextPath()%>/back-end/driver/driver.do" >

<input type="hidden" name="action" value="GET_ONE_FOR_BANNED">
<%-- <input type="hidden" name="driverID" value="${driverVO.driverID}" > --%>
<input class="form-control mr-sm-2" name="driverID" type="text" placeholder="請輸入司機編號(eg.D001)" aria-label="Search">
<button  type="submit" value="GET_ONE_FOR_BANNED">Ban it~</button>

<!-- 			<div> -->
<!-- 			<p>司機截止日:</p> -->
<%-- 			<%=driverVO.getDeadline()%> --%>
<!-- 			<input type="hidden" name="Deadline" id="f_date1" ></td> -->
<!-- 			</div> -->
</form>

</body>



</html>