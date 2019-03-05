<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form method="post" action="/PiCar/back-end/member/member.do" >


<input type="hidden" name="action" value="verified">
<input type="hidden" name="memID" value="${memberVO.memID }" >
<input type="submit" value="驗證">

</form>





</body>
</html>