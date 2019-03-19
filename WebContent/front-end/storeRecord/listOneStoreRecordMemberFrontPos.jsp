<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.storeRecord.model.*"
	import="java.util.List"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<%
	//給小編的
	//以下為了用迴圈將儲值紀錄列出
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
	StoreRecordService storeRecordSvc = new StoreRecordService();	
	
	List<StoreRecordVO> list = storeRecordSvc.getMemStoreRecordPos(memberVO.getMemID());
	pageContext.setAttribute("list", list);

// 	List<StoreRecordVO> list = (List) request.getAttribute("list");
// 	StoreRecordVO storeRecordVO = (StoreRecordVO) request.getAttribute("storeRecordVO");
	//以下為了取得姓名
// 	MemberService memberSvc = new MemberService();
// 	MemberVO memberVO = memberSvc.getOneMember(storeRecordVO.getMemID());
	//以下為了得到加總金額
// 	Integer sumCount = (Integer) request.getAttribute("sumCount");
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listOneStoreRecordMemberFront1.jsp</title>
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
	<a href="select_page.jsp">回主頁面</a>
	<h1 align="center">
		${memberVO.memID}
		
		<%=memberVO.getName()%>
		你好，以下為您的儲值紀錄
	</h1>
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
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"  	value="${storeRecordVO.saveDate}" /></td>




			</tr>
		</c:forEach>
		<tr>
			<td colspan="1">總儲值金額</td>
			<td>${memberVO.token}</td>
			<%-- 			<c:forEach var="storeRecordVO" items="${list}" begin="0" end="0" --%>
			<%-- 				step="1"> --%>
			<%-- 				<td>${storeRecordDAO.getSumAmount(storeRecordVO.memID)}</td> --%>
			<%-- 			</c:forEach> --%>
			<td></td>
		</tr>


	</table>



</body>
</html>