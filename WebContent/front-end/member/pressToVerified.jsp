<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<input type="hidden" name="memID" value="${memberVO.memID }" >


</form>

	<script>
	$(document).ready(function() {
		 $('#sub').submit();
	}); 
	</script>



</body>
</html>