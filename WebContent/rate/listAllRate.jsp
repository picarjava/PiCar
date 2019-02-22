<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="UTF-8"%>
<%@ page import="com.rate.model.*"  import="java.util.List" import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>listALLRate</title>
</head>
<style>
#table1 {
	width: 100%;
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
<h3>listALLRate.jsp</h3>
<%
	RateDAO rateDAO = new RateDAO();
    List<RateVO> list = rateDAO.getAll();
	pageContext.setAttribute("list", list);
	
%>

<c:forEach var="rateVO" items="${list}"  >
<table border="1" id="table1">

<tr>
<td> 費率ID</td>
<td> 費率名稱</td>
<td> 費率(NTDS/KM)</td>
<td> 基本費率(NTDS)</td>
<td> 圖片</td>
</tr>

<tr>
<td> ${rateVO.rateID}</td>
<td> ${rateVO.rateName}</td>
<td> ${rateVO.ratePrice}</td>
<td> <a href="${rateVO.rateBasic}"></a></td>
<td> 

${rateVO.pic}


</td>

</tr>

</table>		

</c:forEach>





</body>
</html>