<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page  import="com.member.model.*" %>
<%@ page  import="com.driver.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hobby_setting</title>
</head>
<style>
table{
	margin-top:30px;
}
</style>
<body>

<!-- 登入功能串接 ，將VOmemID指定給 memID-->
<%
MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
String memID=memberVO.getMemID();
session.setAttribute("memID",memID);
DriverService driSrc = new DriverService();
DriverVO driverVO  = driSrc.getOneDriverBymemID(memberVO.getMemID());
%>
 <h3>setting.jsp</h3>
 
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form method="post" action="driver.do" name="form1" enctype="multipart/form-data">
<a href="<%=request.getServletContext().getContextPath()%>/front-end/driver/homeDriverDataManagment.jsp">回主頁面</a>
	<table >
		<tr>
			<td>會員編號：</td>
			<td><%= memberVO.getMemID() %></td>		
		</tr>
		<tr>
			<td>司機編號：</td>
			<td>${driverVO.driverID}</td>		
		</tr>
		
		<tr>
			<td>司機寵物喜好設定：</td>
			<td><select name="pet">
			<option value="1" ${(driverVO.pet == '1')?'selected':'' }>喜歡寵物
			<option value="0" ${(driverVO.pet == '0')?'selected':'' }>不喜歡寵物
			</select></td>		
		</tr>
		<tr>
			<td>司機抽菸喜好設定：</td>
			<td><select name="smoke">
			<option value="1" ${(driverVO.smoke == '1')?'selected':'' }>抽菸
			<option value="0" ${(driverVO.smoke == '0')?'selected':'' }>不抽菸
			</select></td>		
		</tr>

		<tr>
			<td>嬰兒座椅設定：</td>
			<td><select name="babySeat">
			<option value="1" ${(driverVO.babySeat == '1')?'selected':'' }>需要
			<option value="0" ${(driverVO.babySeat == '0')?'selected':'' }>不需要
			</select></td>		
		</tr>
		<tr>
			<td>共享車子</td>
			<td><select name="sharedCar">
			<option value="1" ${(driverVO.sharedCar == '1')?'selected':'' }>需要
			<option value="0" ${(driverVO.sharedCar == '0')?'selected':'' }>不需要
			</select></td>		
		</tr>

		
	</table>                          
	<input type="hidden" name="driverID" value="${driverVO.driverID}">
	<input type="hidden" name="action" value="Update_Hobby">
	<input type="submit" value="submit">
</form>


</body>



</html>