<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rate.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		RateVO rateVO = (RateVO) request.getAttribute("rateVO");
	%>
 <h3>update_member_input.jsp</h3>
 
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<form method="post" action="rate.do" name="form1">
	<a href="select_page.jsp">回主頁面</a>
		
	<table>
	<tr>
		<td>費率編號</td>
		<td><%=rateVO.getRateID()%></td>
	</tr>
	<tr>
		<td>費率名稱</td>	
		<td><input type="text" name="rateName" value="<%=rateVO.getRateName() %>" ></td>
	</tr>
	<tr>
		<td>費率(元/KM)</td>	
		<td><input type="text" name="ratePrice" value="<%=rateVO.getRatePrice() %>" ></td>
	</tr>	
	<tr>
		<td>基本費(元)</td>	
		<td><input type="text" name="rateBasic" value="<%=rateVO.getRateBasic() %>" ></td>
	</tr>
	</table>
	<input type="hidden" name="rateID" value="<%=rateVO.getRateID() %> ">
	<input type="hidden" name="action" value="update">
	<input type="submit" value="submit">
</form>
</body>
</html>