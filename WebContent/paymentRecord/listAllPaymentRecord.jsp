<%@ page language="java" contentType="text/html; chartset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.paymentRecord.model.*" import="java.util.List"
	import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listALLRate</title>
</head>
<style>
#table1 {
	width: 600px;
	height: 100%;
	background-color: #7afec6;
	margin-top: 20px;
	margin-bottom: 20px;
	text-align: center;
	font-family: Microsoft JhengHei;
}

table1, td {
	
}

.page1 {
	margin-left: 80px
}
</style>
<body>
	<h3>listAllPaymentRecord.jsp</h3>
	<%
		PaymentRecordService prSvc = new PaymentRecordService();
		List<PaymentRecordVO> list = prSvc.getAll();
		pageContext.setAttribute("list", list);
	%>
	<a href="select_page.jsp">回主畫面</a>
	<table border="1" id="table1">	
			<tr>
				<td>撥款ID</td>
				<td>司機ID</td>
				<td>撥款金額</td>
				<td>撥款時間</td>
				<td>修改</td>
				<td>刪除</td>
			</tr>
			
	<c:forEach var="paymentRecordVO" items="${list}">
				

			<tr>
				<td>${paymentRecordVO.paymentID}</td>
				<td>${paymentRecordVO.driverID}</td>
				<td>${paymentRecordVO.payAmount}</td>
				<td>${paymentRecordVO.payTime}</td>


				<td>
					<form method="post"	action="<%=request.getContextPath()%>/paymentRecord/paymentRecord.do">
						<input type="submit" value="修改"> 
						<input type="hidden" name="paymentID" value="${paymentRecordVO.paymentID}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
				
				    </form>
				 </td>  
				<td>
					<form method="post" action="<%=request.getContextPath()%>/paymentRecord/paymentRecord.do">
						<input type="submit" value="刪除">
						 <input type="hidden" name="paymentID" value="${paymentRecordVO.paymentID}"> 
						 <input type="hidden" name="action" value="delete">
					</form>


				</td>

			</tr>

		

	</c:forEach>

</table>



</body>
</html>