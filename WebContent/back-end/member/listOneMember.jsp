<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="UTF-8" import="com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<style>
#table1 {
	width: 1300px;
	background-color: #7afec6;
	margin-top: 100px;
	margin-bottom: 20px;
	text-align: center;
	font-family: Microsoft JhengHei;
}

</style>
<body bgcolor="#11e1e9">
<h3>listOneMember.jsp</h3>
<a href="select_page.jsp">回主頁面</a>
	<h1 align="center">Hello!!管理員，這是編號"<%=memberVO.getMemID()%>"<%=memberVO.getName()%>個人資料</h1>
	<table align="center" border="1" id="table1">
		<tr>
			<td>memID</td>
			<td>${memberVO.memID}</td>
		</tr>
		<tr>
			<td>name</td>
			<td>${memberVO.name}</td>
		</tr>
		<tr>	
			<td>birthday</td>
			<td>${memberVO.birthday}</td>
		</tr>	
		<tr>
			<td>email</td>
			<td>${memberVO.email}</td>
		</tr>
		<tr>	
			<td>password</td>
<%-- 			<td>${memberVO.password}</td> --%>
		</tr>
		<tr>	
			<td>phone</td>
			<td>${memberVO.phone}</td>
		</tr>
		<tr>	
			<td>creditcard</td>
			<td>${memberVO.creditcard}</td>
		</tr>
		<tr>	
			<td>token</td>
			<td>${memberVO.token}</td>
		</tr>
		<tr>	
			<td>activityToken</td>
			<td>${memberVO.activityToken}</td>
		</tr>
		<tr>	
			<td>pet</td>
			<td><c:choose>
			  <c:when test="${memberVO.pet == '1'}"> 喜好寵物  </c:when>
			  <c:when test="${memberVO.pet == '0'}">  不喜好寵物  </c:when>			 
			</c:choose></td>
		</tr>
		<tr>
			<td>smoke</td>
			<td><c:choose>
			  <c:when test="${memberVO.smoke == '1'}">抽菸  </c:when>
			  <c:when test="${memberVO.smoke == '0'}">  不抽菸  </c:when>			 
			</c:choose></td>
		</tr>
		<tr>	
			<td>gender</td>
			<td><c:choose>
			  <c:when test="${memberVO.gender == '1'}">男生  </c:when>
			  <c:when test="${memberVO.gender == '0'}">女生  </c:when>			 
			</c:choose></td>
		</tr>	
		
		<tr>	
			<td>babySeat</td>
			<td><c:choose>
			  <c:when test="${memberVO.babySeat == '1'}">需要  </c:when>
			  <c:when test="${memberVO.babySeat == '0'}">不需要  </c:when>			 
			</c:choose></td>			
		</tr>
		<tr>	
			<td>verified</td>
			<td><c:choose>
			  <c:when test="${memberVO.verified == '1'}">已經驗證  </c:when>
			  <c:when test="${memberVO.verified == '0'}">尚未驗證  </c:when>			 
			</c:choose></td>
		</tr>
		
		<tr>
		<td>個人照片</td>
		<td><img src="http://localhost:8081/PiCar/front-end/member/member.do?memID=${memberVO.memID}"  width='200' height="200"
		onerror="this.src='cat.jpg'"></td>
		</tr>	
	
			
	</table>
	<table>
		<tr>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/member/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="驗證此會員">
			     <input type="hidden" name="memID"  value="${memberVO.memID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="贈送代幣">
			     <input type="hidden" name="memID"  value="${memberVO.memID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
		</tr>	
	</table>	

</body>
</html>