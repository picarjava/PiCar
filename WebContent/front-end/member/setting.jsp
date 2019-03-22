<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page  import="com.member.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>setting.jsp</title>
</head>
<style>
table{
	margin-top:30px;
}
</style>
<body>
<%
	MemberVO memberVO =  (MemberVO)request.getAttribute("memberVO");

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

<form method="post" action="member.do" name="form1" enctype="multipart/form-data">
<a href="listOneMemberByUpdate.jsp">回上一頁</a>
	<table >
		<tr>
			<td>會員編號：</td>
			<td>  <%= memberVO.getMemID() %>   </td>		
	
		</tr>
		
		<tr>
			<td>會員信用卡：</td>
			<td><input type="text" name="creditcard" size="45" value="<%=memberVO.getCreditcard() %>" /></td>		
		</tr>
		
		<tr>
			<td>會員寵物喜好設定：</td>
			<td><select name="pet">
			<option value="1" ${(memberVO.pet == '1')?'selected':'' }>喜歡寵物
			<option value="0" ${(memberVO.pet == '0')?'selected':'' }>不喜歡寵物
			</select></td>		
		</tr>
		<tr>
			<td>會員抽菸喜好設定：</td>
			<td><select name="smoke">
			<option value="1" ${(memberVO.smoke == '1')?'selected':'' }>抽菸
			<option value="0" ${(memberVO.smoke == '0')?'selected':'' }>不抽菸
			</select></td>		
		</tr>

		<tr>
			<td>嬰兒座椅設定：</td>
			<td><select name="babySeat">
			<option value="1" ${(memberVO.babySeat == '1')?'selected':'' }>需要
			<option value="0" ${(memberVO.babySeat == '0')?'selected':'' }>不需要
			</select></td>		
		</tr>

		
	</table>                          
	<input type="hidden" name="memID" value="<%=memberVO.getMemID() %> ">
	<input type="hidden" name="action" value="Update_Hobby">
	<input type="submit" value="submit">
</form>


</body>



</html>