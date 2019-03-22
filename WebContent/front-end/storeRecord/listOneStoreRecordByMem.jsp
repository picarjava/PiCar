<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.storeRecord.model.*"
	import="java.util.List"%>
<%@ page import="com.member.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<%	
	
	//給會員儲值後顯示用的
	//以下為了用迴圈將儲值紀錄列出
	List<StoreRecordVO> list = (List) request.getAttribute("list");
	StoreRecordVO storeRecordVO = (StoreRecordVO)request.getAttribute("storeRecordVO");
	//以下為了取得姓名
	MemberService memberSvc = new MemberService();
	MemberVO memberVO = memberSvc.getOneMember(storeRecordVO.getMemID());
	//以下為了得到加總金額
	Integer sumCount = (Integer)request.getAttribute("sumCount");
// 	MemberVO memberVO = (MemberVO)session.getAttribute("memeberVO");
int i = 0;
i++;
if (i > 0 ){
	response.sendRedirect("/PiCar/front-enf/HomeMember/index.jsp");
}
	
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listOneRate.jsp</title>
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
	<h3>listOneStoreRecordMember.jsp</h3>
	<a class="box" href=/PiCar/front-end/HomeMember/index.jsp> 請按此回首頁 </a>
	<h1 align="center"> ${storeRecordVO.memID} <%=memberVO.getName() %>	你好，以下為您的儲值紀錄</h1>
	<table align="center" border="1" id="table1">
		<tr>
			<td>儲值ID</td>
			
			<td>儲值金額</td>
			<td>儲值時間</td>
		</tr>
		<c:forEach var="storeRecordVO" items="${list}">
			<tr>

				<td>${storeRecordVO.storeID}</td>				
				<td>${storeRecordVO.amount}</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${storeRecordVO.saveDate}" /></td>				
			</tr>
		</c:forEach>
		<tr>
			<td colspan="1">總儲值金額</td>	

			<td><%=memberVO.getToken() %></td>
		</tr>
			

	</table>



</body>
</html>