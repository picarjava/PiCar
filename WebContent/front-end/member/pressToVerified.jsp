<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="jquery-1.12.4.min.js" ></script>
</head>
<body>

<form method="post" id="sub" action="/PiCar/front-end/member/member.do" >


<input type="hidden" name="action" value="verified">
<input type="hidden" name="memID" value="${memberVO.memID}" >


</form>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


	<script>
	$(document).ready(function() {
		 $('#sub').submit();
		 
	}); 
	</script>
<%-- <%response.setHeader("Refresh", "2; URL=" + request.getContextPath() + "/front-end/login/login.html"); %> --%>


</body>
</html>