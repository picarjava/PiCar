<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body bgcolor="#c1ece9">

	<ul>
		<li><a href="listAllmember_byDAO.jsp" >點選顯示全部員工</a>  <font color="blue" face="DFKai-sb">All members by DAO</font> <br><br>
	
		<li><a href="member.do?action=getAll" >點選顯示全部員工</a>  <font color="blue" face="DFKai-sb">All members getFormSession</font> <br><br>
		<li>
			<form method="post" action="member.do">
				<b>請輸入會員編號(EX:M001):</b><br> 
				<input type="text" name="memberID">
				<input type="hidden" name="action" value="getOne_For_Display"><br>
				<input type="submit" value="submit">
			</form> 
			
			<jsp:useBean id="memberdao" scope="page" class="com.member.model.MemberDAO" />
		</li>
		<br><br>
		<li>
			<form method="post" action="member.do">
				<b>請選擇員工編號</b> <br>
				<select size="1" name="memID">
					<c:forEach var="memberVO" items="${memberdao.all}">
						<option value="${memberVO.memID}">${memberVO.memID}
					</c:forEach>
				</select> 
				<input type="hidden" name="action" value="get_For_Display"><br>
				<input type="submit" value="submit">
			</form>
		</li>
		<br>
		<li>
			<form method="post" action="member.do">
				<b>請選擇員工姓名</b><br>
				<select size="1" name="name">
					<c:forEach var="memberVO" items="${memberdao.all}">
						<option value="${memberVO.memID}">"${memberVO.name}" 					
					</c:forEach>
				</select>
				<br>	
				<input type="hidden" name="action" value="get_For_Display">
				<input type="submit" value="submit">			
			</form>
			
		</li>
	</ul>



</body>
</html>