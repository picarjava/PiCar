<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page  import="com.rate.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<style>
table{
	margin-top:30px;
}
</style>
<body>
<%
	RateVO rateVO =  (RateVO)request.getAttribute("rateVO");

%>
 <h3>addRate.jsp</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form method="post" action="rate.do" name="form1" enctype="multipart/form-data">
<a href="select_page.jsp">回主頁面</a>
	<table >
		
	
		<tr>
			<td>資費名稱：</td>
			<td><input type="text" name="rateName" size="45" value="<%= (rateVO==null)? "匯率":rateVO.getRateName() %>" /></td>		
	
		</tr>
		<tr>
			<td>資費費率(元/公里)：</td>
			<td><input type="text" name="ratePrice" size="45" value="<%= (rateVO==null)? "0":rateVO.getRatePrice() %>" /></td>		
		</tr>
		
		<tr>
			<td>基本費(元)：</td>
			<td><input type="text" name="rateBasic" size="45" value="<%= (rateVO==null)? "0":rateVO.getRateBasic() %>" /></td>		
		</tr>		
		
		<td>上傳圖片：</td>
			<td><input type="file" name="pic" size="45"  /></td>		
		</tr>
		
	</table>                          
	
	<input type="hidden" name="action" value="insert">
	<input type="submit" value="送出新增">
</form>


</body>


</html>