<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.driver.model.*"%>

<%
DriverVO driverVO = (DriverVO) request.getAttribute("driverVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>司機資料修改 - update_driver_input.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
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

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>司機資料修改 - update_driver_input.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="driver.do" name="form1">
<table>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="memID" size="45" 
			 value="<%= (driverVO==null)? "吳永志" : driverVO.getMemID()%>" /></td>
	</tr>
	<tr>
		<td>司機編號:</td>
		<td><input type="TEXT" name="driverID" size="45"
			 value="<%= (driverVO==null)? "MANAGER" : driverVO.getDriverID()%>" /></td>
	</tr>
	<tr>
		<td>車牌號碼:</td>
		<td><input type="TEXT" name="plateNum" size="45"
			 value="<%= (driverVO==null)? "10000" : driverVO.getPlateNum()%>" /></td>
	</tr>
	<tr>
		<td>駕照:</td>
		<td><input type="TEXT" name="licence" size="45"
			 value="<%= (driverVO==null)? "100" : driverVO.getLicence()%>" /></td>
	</tr>
	<tr>
		<td>良民證:</td>
		<td><input type="TEXT" name="criminal" size="45"
			 value="<%= (driverVO==null)? "100" : driverVO.getCriminal()%>" /></td>
	</tr>
	<tr>
		<td>肇事紀錄:</td>
		<td><input type="TEXT" name="trafficRecord" size="45"
			 value="<%= (driverVO==null)? "100" : driverVO.getTrafficRecord()%>" /></td>
	</tr>
	<tr>
		<td>身分證:</td>
		<td><input type="TEXT" name="idNum" size="45"
			 value="<%= (driverVO==null)? "100" : driverVO.getIdNum()%>" /></td>
	</tr>
	<tr>
		<td>是被BAN:</td>
		<td><input type="TEXT" name="banned" size="45"
			 value="<%= (driverVO==null)? "100" : driverVO.getBanned()%>" /></td>
	</tr>
	<tr>
		<td>截止期限:</td>
		<td><input type="TEXT" name="deadline" size="45"
			 value="<%= (driverVO==null)? "100" : driverVO.getDeadline()%>" /></td>
	</tr>
	<tr>
		<td>顯示上線:</td>
		<td><input type="TEXT" name="onlineCar" size="45"
			 value="<%= (driverVO==null)? "100" : driverVO.getOnlineCar()%>" /></td>
	</tr>
	<tr>
		<td>評價分數:</td>
		<td><input type="TEXT" name="score" size="45"
			 value="<%= (driverVO==null)? "100" : driverVO.getScore()%>" /></td>
	</tr>
	<tr>
		<td>車型:</td>
		<td><input type="TEXT" name="carType" size="45"
			 value="<%= (driverVO==null)? "100" : driverVO.getCarType()%>" /></td>
	</tr>
	<tr>
		<td>是否共享:</td>
		<td><input type="TEXT" name="sharedCar" size="45"
			 value="<%= (driverVO==null)? "100" : driverVO.getSharedCar()%>" /></td>
	</tr>
	<tr>
		<td>接受寵物:</td>
		<td><input type="TEXT" name="pet" size="45"
			 value="<%= (driverVO==null)? "100" : driverVO.getPet()%>" /></td>
	</tr>
	<tr>
		<td>接受抽菸:</td>
		<td><input type="TEXT" name="smoke" size="45"
			 value="<%= (driverVO==null)? "100" : driverVO.getSmoke()%>" /></td>
	</tr>
	<tr>
		<td>提供嬰兒椅:</td>
		<td><input type="TEXT" name="babySeat" size="45"
			 value="<%= (driverVO==null)? "100" : driverVO.getBabySeat()%>" /></td>
	</tr>



</table>
<br>
<input type="hidden" name="action" value="update"/>
<input type="hidden" name="driverID" value="<%=driverVO.getDriverID()%>">
<input type="submit" value="送出修改"></FORM>
</body>



</html>