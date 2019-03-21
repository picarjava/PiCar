<%@ page language="java" contentType="text/html; chartset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.rate.model.*" import="java.util.List"
	import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/back-end/kidHead.jsp" />
<title>listALLRate</title>
<jsp:include page="/back-end/kidHead.jsp" />
</head>

<body>
<div class="wrapper ">
<jsp:include page="/back-end/kidBodyLeft.jsp" />
<jsp:include page="/back-end/kidNavbar.jsp" />
	<h3>listALLRate.jsp</h3>
	<%
		RateService rateSvc = new RateService();
		List<RateVO> list = rateSvc.getAll();
		pageContext.setAttribute("list", list);
	%>
	<jsp:include page="/back-end/kidBodyLeft.jsp" />
	<div class="main-panel">
	<jsp:include page="/back-end/kidNavbar.jsp" />
	<div class="content">
<!-- 	<a href="select_page.jsp">回主畫面</a> -->
	
	<c:forEach var="rateVO" items="${list}">
		<table border="1" id="table1">

			<tr>
				<td>費率ID</td>
				<td>費率名稱</td>
				<td>費率(NTDS/KM)</td>
				<td>基本費率(NTDS)</td>
				<td>修改</td>
<!-- 				<td>刪除</td> -->
			</tr>

			<tr>
				<td>${rateVO.rateID}</td>
				<td>${rateVO.rateName}</td>
				<td>${rateVO.ratePrice}</td>
				<td>${rateVO.rateBasic}</td>


				<td>
					<form method="post"	action="<%=request.getContextPath()%>/back-end/rate/rate.do">
						<input type="submit" value="修改"> 
						<input type="hidden" name="rateID" value="${rateVO.rateID}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
				
				    </form>
				 </td>
				 </tr>
<!-- 				<td> -->
<%-- 					<form method="post" action="<%=request.getContextPath()%>/back-end/rate/rate.do"> --%>
<!-- 						<input type="submit" value="刪除"> -->
<%-- 						 <input type="hidden" name="rateID" value="${rateVO.rateID}">  --%>
<!-- 						 <input type="hidden" name="action" value="delete"> -->
<!-- 					</form> -->


<!-- 				</td> -->

			

		</table>

	</c:forEach>
<jsp:include page="/back-end/kidFooter.jsp" />
</div>
</div>
</div>
<jsp:include page="/back-end/kidFooter.jsp" />
</body>
</html>