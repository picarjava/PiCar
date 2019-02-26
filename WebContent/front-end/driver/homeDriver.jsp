<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<form class="form-inline" action="driver.do">
					   <input class="form-control mr-sm-2" name="driverID" type="search" placeholder="請寫司機編號" aria-label="Search">
					   <!--隱藏的參數action讓controller抓-->
		              	<input type="hidden" name="action" value="GET_ONE">
					   <button class="btn btn-outline-success my-2 my-sm-0" type="submit">查詢司機資料</button>
</form>
</body>
</html>