<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page  import="com.storeRecord.model.*" %>
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
	StoreRecordVO storeRecordVO =  (StoreRecordVO)request.getAttribute("storeRecordVO");

%>
 <h3>addPaymentRecord.jsp</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form method="post" action="storeRecord.do" name="form1" >
<a href="select_page.jsp">回主頁面</a>
	<table >
		
	
		<tr>
			<td>會員ID：</td>
			<td><input type="text" name="memID" size="45" value="<%= (storeRecordVO==null)? "會員ID":storeRecordVO.getMemID() %>" /></td>		
	
		</tr>
		<tr>
			<td>儲值金額：</td>
			<td><input type="text" name="amount" size="45" value="<%= (storeRecordVO==null)? "0":storeRecordVO.getAmount() %>" /></td>		
		</tr>
		
		<%-- <tr>
			<td>撥款日期：</td>
			<td><input type="text" name="payTime"  id="f_date1" size="45" ></td>		
		</tr>				
		--%>
		
	</table>                          
	
	<input type="hidden" name="action" value="insert">
	<input type="submit" value="送出新增">
</form>


</body>

</html>