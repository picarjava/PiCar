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
		<input type="text" name="memID" value="M001">會員ID <br>
		<input type="text" name="amount" value="1000">價格<br>
		<input type="text" name="orderID" value="sss">訂單ID<br>
		<input type="text" name="count" value="sss">訂單比數
		<input type="hidden" name="action" value="insertOrder1">
		<input type="submit" value="submit">
	</form>

</body>
</html>