<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page  import="com.storeRecord.model.*" %>
<%@ page import="com.member.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addStoreRecord.jsp</title>
<script src="jquery-1.12.4.min.js" >$('#post-form').one('submit', otherDoPost); </script>

</head>
<style>
table{
	margin-top:30px;
}
</style>
<body>
<%
	StoreRecordVO storeRecordVO =  (StoreRecordVO)request.getAttribute("storeRecordVO");
// 	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
%>
 <h3>addStoreRecord.jsp</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form method="post" action="storeRecord.do" name="form1" ID="post-form">
<a href="select_page.jsp">回主頁面</a>
	<table >
		
	
		<tr>
			<td>會員ID：</td>
			<td><input type="text" name="memID" size="45" value="${memberVO.memID}" readonly/></td>		
	
		</tr>
		<tr>
			<td>儲值金額：</td>
			<td>
			<input type="radio" name="amount" value="5000">5000 
			<input type="radio" name="amount" value="3000">3000 
				<input type="radio" name="amount" value="1000">1000 
				<input type="radio" name="amount" value="500">500
			</td>					
		</tr>
		<tr>
			<td>輸入信用卡號：</td>
			<td><input type="text"  size="45"  />
			</td>					
		</tr>
		<%-- <tr>
			<td>撥款日期：</td>
			<td><input type="text" name="payTime"  id="f_date1" size="45" ></td>		
		</tr>				
		--%>
		
	</table>                          
	
	<input type="hidden" name="action" value="insert">
	<input type="submit" value="結帳" id="submit" ID="post-form">
<%-- 	<input  type="hidden"  name="url" value=<%=request.getRequestURL()%>>  --%>
	
	

	
	
	
	
</form>
<script>
	
		//otherDoPost 為一個包括 post 的 function
		
// 	 function checkSubmit(){
// 	        document.getElementById("submit").disabled = "disabled";
// 	        return true;
		
	</script>

</body>

</html>