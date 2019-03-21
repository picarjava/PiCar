<%@ page language="java" contentType="text/html; chartset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.storeRecord.model.*" import="java.util.List"
	import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
	<h3>listAllStoretRecord.jsp</h3>
	<%
		StoreRecordService srSvc = new StoreRecordService();
		List<StoreRecordVO> list = srSvc.getAll();
		pageContext.setAttribute("list", list);
	%>
	<a href="select_page.jsp">回主畫面</a>
	<table border="1" id="table1">	
			<tr>
				<td>儲值ID</td>
				<td>會員ID</td>
				<td>儲值金額</td>
				<td>儲值時間</td>
				<td>修改</td>
				<td>刪除</td>
			</tr>
			
	<c:forEach var="storeRecordVO" items="${list}">
				

			<tr>
			
				<td>${storeRecordVO.storeID}</td>
				<td>${storeRecordVO.memID}</td>
				<td>${storeRecordVO.amount}</td>
				<td><fmt:formatDate value="${storeRecordVO.saveDate}" pattern="yyyy-MM-dd HH:mm"/></td>
<%-- 				<td>${storeRecordVO.saveDate}</td> --%>


				<td>
					<form method="post"	action="<%=request.getContextPath()%>/storeRecord/storeRecord.do">
						<input type="submit" value="修改"> 
						<input type="hidden" name="storeID" value="${storeRecordVO.storeID}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
				
				    </form>
				 </td>  
				<td>
					<form method="post" action="<%=request.getContextPath()%>/storeRecord/storeRecord.do">
						<input type="submit" value="刪除">
						 <input type="hidden" name="storeID" value="${storeRecordVO.storeID}"> 
						 <input type="hidden" name="action" value="delete">
					</form>


				</td>

			</tr>

		

	</c:forEach>

</table>



</body>
</html>