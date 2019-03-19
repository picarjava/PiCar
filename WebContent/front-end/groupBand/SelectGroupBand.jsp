<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>SelectGroupBand.jsp</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<meta charset="BIG5">
</head>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }

</style>
<body bgcolor='white'>


<h3>揪團資料查詢:</h3>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getServletContext().getContextPath()%>/GroupBand"  enctype="multipart/form-data">
        <input type="radio" name="GROUP_KIND" value="5" checked
					onclick="groupif(this.value)">揪團
				<input type="radio" name="GROUP_KIND" value="6"
					onclick="groupif(this.value)">長期揪團<br>
        
        <b>團名</b>
         <input  type="TEXT" name="GROUP_NAME"><br><br>
         <b>上車地點</b>
         	  <select name="START_LOC">
         	  <option value="">請選擇</option>
			<option value="台北">台北市</option>
			<option value="桃園">桃園市</option>
			</select><br><br>
			<b>上車日期</b>
			<input name="START_TIME" id="start_date" type="text"
					 onchange="datestamps();" size="15"><br><br>
        <b>揪團類別</b>
        <select name="GROUP_TYPE">
         <option value="">請選擇</option>
			<option value="演唱會">演唱會</option>
			<option value="旅遊">旅遊</option>
			<option value="美食">美食</option>
			<option value="運動團">運動團</option>
			<option value="展覽">展覽</option>
			<option value="遊樂園">遊樂園</option>
			</select><br><br>
       
 
        <input type="hidden" name="action" value="listgroupBand_ByCompositeQuery">
        <input type="submit" value="送出">
    </FORM>
  </li>
</ul>
<script>
$('#start_date').datetimepicker(
		{
			format : 'Y-m-d',
			onShow : function() {			
			},
			step: 5,
			timepicker : false,
					value : ' ',
			minDate:           '-1970-01-01', // 去除今日(不含)之前
			maxDate:           '+1970-01-20'  // 去除今日(不含)之後
		});
</script>
</body>
</html>