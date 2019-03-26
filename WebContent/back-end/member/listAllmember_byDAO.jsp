<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%
MemberService memberSvc = new MemberService();
List<MemberVO> list = memberSvc.getAll();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		 <jsp:include page="/back-end/head_back.jsp" /> 
		<title>會員管理</title>
		<jsp:include page="/back-end/btnCss.jsp" />
		<style>
		div.content {
/* 			width:95%; */
 		margin-left:-25%; 
		}
		/* .footer{ */
		/* margin-bottem:-1%; */
		/* } */
		table{
			width:1200px;
			border:1px solid #ffffff;
					
		}
		td{
			border:1px solid #333333;
			padding:10px;
			font-family: arial;
			font-size:12px;	
			width:120px;
			align:center;
			
		}
		tr:nth-child(1){
			align:center;
			background-color:#7788aa;
			color:#888888;
			
		}
		#tr1{
			background-color:#blue;
			color:#888888;
			font-family:fantasy;
		}
		tr:nth-child(even){
			background-color:#e8e8e8;
		}
		td:nth-child(last){			
			width:30px;			
		}
		th{
		font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro', 'Noto Sans CJK SC', monospace;
		border:1px solid #333333;
		}
		</style>
	</head>
	<body bgcolor="#11e1e9">
		<div class="wrapper ">
			<jsp:include page="/back-end/kidBodyLeft.jsp" />
			<div class="main-panel">
				<div class="content">
					<div class="container-fluid">
<!-- 						 <h3>listAllmember_byDAO.jsp</h3> -->
						<div id="errmsg">
							<c:if test="${not empty errorMsgs}">
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
								<li>
									${message}
								</li>
								</c:forEach>
							</ul>
							</c:if>
						</div>
						<h3 align="center">管理員你好，這是Picar會員資料</h3>
						<div class="page1">
<!-- 							<a href="select_page.jsp">查詢主頁面</a> -->
						</div>
						<br>
						<br>
						<div class="page1"><%@ include file="page1.file"%></div>
						<table align="center"  >
							<tr id="tr1">
								<th >會員帳號</td>
								<th>會員姓名</td>
								<th>會員信箱</td>
								<!-- <td>password</td> -->
								<th>會員電話</td>
<!-- 								<td>信用卡號</td> -->
<!-- 								<td>寵物設定</td> -->
<!-- 								<td>抽菸設定</td> -->
								<th>會員性別</td>
								<th>一般代幣</td>
<!-- 								<td>活動代幣</td> -->
								<th>會員生日</td>
								<th>會員權限</td>
<!-- 								<td>嬰兒座椅</td> -->
								<th>個人照片</td>
								<th>修改</td>
								<!-- 			<td>刪除</td> -->
							</tr>
							<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td>${memberVO.memID}</td>
								<td>${memberVO.name}</td>
								<td>${memberVO.email}</td>
								<!-- <td></td> -->
								<%-- 			<td>${memberVO.password}</td> --%>
								<td>${memberVO.phone}</td>
<%-- 								<td>${memberVO.creditcard}</td> --%>
<%-- 								<td><c:choose> --%>
<%-- 									<c:when test="${memberVO.pet == '1'}"> 喜好寵物  </c:when> --%>
<%-- 									<c:when test="${memberVO.pet == '0'}">  不喜好寵物  </c:when> --%>
<%-- 								</c:choose></td> --%>
								
<%-- 								<td><c:choose> --%>
<%-- 									<c:when test="${memberVO.smoke == '1'}">抽菸  </c:when> --%>
<%-- 									<c:when test="${memberVO.smoke == '0'}">  不抽菸  </c:when> --%>
<%-- 								</c:choose></td> --%>
								
								<td><c:choose>
									<c:when test="${memberVO.gender == '1'}">男生  </c:when>
									<c:when test="${memberVO.gender == '0'}">女生  </c:when>
								</c:choose></td>
								
								<td>${memberVO.token}</td>
<%-- 								<td>${memberVO.activityToken}</td> --%>
								<td>${memberVO.birthday}</td>
								
								<td><c:choose>
									<c:when test="${memberVO.verified == '1'}">已經驗證  </c:when>
									<c:when test="${memberVO.verified == '0'}">停權  </c:when>
								</c:choose></td>
								
<%-- 								<td><c:choose> --%>
<%-- 									<c:when test="${memberVO.babySeat == '1'}">需要  </c:when> --%>
<%-- 									<c:when test="${memberVO.babySeat == '0'}">不需要  </c:when> --%>
<%-- 								</c:choose></td> --%>
								<td><img src="http://localhost:8081/PiCar/back-end/member/member.do?memID=${memberVO.memID}"  width="130" height="130" onerror="this.src='cat.jpg'"></td>
								
								<td >
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/member/member.do" style="margin-bottom: 0px;">
										<input type="submit" value="修改">
										<input type="hidden" name="memID"  value="${memberVO.memID}">
										<input type="hidden" name="action"	value="getOne_For_Update"></FORM>
									</td>
									<!-- 			<td> -->
									<!-- 				<form method="post" action="member.do"> -->
									<!-- 				<input type="submit" value="刪除" onClick="window.alert('想做什麼，不要亂按!!');"> -->
									<%-- 				<input type="hidden" name="memID"  value="${memberVO.memID}"> --%>
									<!-- 				<input type="hidden" name="action" value="delete"> -->
									<!-- 			</td> -->
									<!-- 		</form>		 -->
									
<!-- 								</form> -->
							</tr>
							</c:forEach>
						</table>
					</div>
					<div align="center"><%@ include file="page2.file"%></div>
				</div>
			</div>
		</div>
	</body>
	<jsp:include page="/back-end/kidFooter.jsp" />
</html>