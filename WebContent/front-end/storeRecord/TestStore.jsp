<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="storeRecord.do">
		<input type="text" name="memID" value="M001">�|��ID <br>
		<input type="text" name="amount" value="1000">����<br>
		<input type="text" name="orderID" value="sss">�q��ID
		<input type="hidden" name="action" value="insertOrder">
		<input type="submit" value="submit">
	</form>

</body>
</html>