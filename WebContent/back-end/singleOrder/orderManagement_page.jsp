<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.singleOrder.model.SingleOrderVO"%>
<%@ page import="com.singleOrder.controller.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單管理頁面</title>


  <base href="<%=basePath%>">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!-- <link rel="stylesheet" type="text/css" href="styles.css"> -->
    <script src="jquery-3.0.0.js" type="text/javascript"></script>
    <script type="text/javascript"> 
    function myFunction(){ 
    	$.ajax({ type:"post",
    		//傳輸方式為post，所以我們在servlet里面代碼是寫在doPost()函數中 url:"DataServlet", 
    		//這就是使用的servlet的url success:
    function(data){ document.getElementById("json").innerHTML=data; } }
    	); } 
    </script>



</head>
<body>

    <p id="json">資料</p><br>
    <button onclick="myFunction()">獲取資料</button>

<div>
	<div><button>修改<img src=""></button></div>
	<button>重新叫車</button>
	<button>退款</button>
</div>


<div id ="333"><button>查詢訂單</button></div>


<body>
	年級：
	<select id="grade">
		<option value="-1">請選擇</option>
		<option value="grade3">三年級</option>
		<option value="grade2">二年級</option>
		<option value="grade1">一年級</option>
	</select>
	班別：
	<select id="class">
		<option value="-1">請選擇</option>
	</select>
	姓名:
	<select id="name">
		<option value="-1">請選擇</option>
	</select>
	<br><br>
<!-- 	<iframe width="860" height="615" src="https://www.youtube.com/embed/aVmZpcrQBU4?autoplay=1" frameborder="0" allowfullscreen></iframe> -->
</body>




</body>
<script>

</script>
</html>