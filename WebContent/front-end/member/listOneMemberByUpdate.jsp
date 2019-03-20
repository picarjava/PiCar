<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.location.model.*"%>
<%
// 	此處為登入處理
//    String account = (String)session.getAttribute("account");                  // 從 session內取出 (key) account的值
    
//    使用濾器登入，以下程式碼可以省略------
//    if (account == null) {                                             // 如為 null, 代表此user未登入過 , 才做以下工作
//       session.setAttribute("location", request.getRequestURI());       //*工作1 : 同時記下目前位置 , 以便於login.html登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
//       response.sendRedirect(request.getContextPath()+"/front-end/login/login.html");   //*工作2 : 請該user去登入網頁(login.html) , 進行登入
//       return;
//     }
   
%>

<%
// 	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
	
// 		MemberService memberSvc = new MemberService();
// 		MemberVO memberVO = memberSvc.getOneMember(account);
		MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");

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
.box1{
	width:100px;
	padding:40px;
	position:absolute;
	top:10%;
	left:10%;
	transform:translate(-50%,-50%);	
	text-align:center;
	font-family:Microsoft JhengHei;
	border:0;
	background:none;
	display:block;
	margin :20px auto;
	text-align:center;
	border:2px solid #2ecc71;
	padding:14px 10px;	
	outline:none;
	color:white;
	border-radius:24px;
	transition:2s;
	cursor:pointer;
}

</style>
<body bgcolor="#11e1e9">
<h3>listOneMember.jsp</h3>
<a class="box" href=/PiCar/regna-master/homeindex.jsp> 請按此回首頁 </a>
	<h1 align="center">Hello!!${memberVO.name}，這是你的個人資料</h1>
	<table align="center" border="1" id="table1">
			
<%=memberVO.getMemID()%>
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
<!-- 		<tr>	 -->
<!-- 			<td>password</td> -->
<%-- 			<td>${memberVO.password}</td> --%>
<!-- 		</tr> -->
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
		<td><img src="<%=request.getServletContext().getContextPath()%>/front-end/member/member.do?memID=${memberVO.memID}"  width='200' height="200"
		onerror="this.src='cat.jpg'"></td>
		</tr>	
	</table>
	
	<table>
	<tr>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/member/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改個人資料">
			     <input type="hidden" name="memID"  value="${memberVO.memID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/storeRecord/storeRecord.do" style="margin-bottom: 0px;">
			     <input type="submit" value="代幣儲值">
			     <input type="hidden" name="memID"  value="${memberVO.memID}">
			     <input type="hidden" name="action"	value="addToken"></FORM>
			</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/member/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="喜好設定">
			     <input type="hidden" name="memID"  value="${memberVO.memID}">
			     <input type="hidden" name="action"	value="getOne_For_Update_HOBBY">
			  </FORM>
			</td>
			
            <td>
                <form action="<%=application.getContextPath()%>/location" method="POST">
                    <input type="hidden" name="memID" value="${locationVO.memID}"/>
                    <input type="hidden" name="location" value="${locationVO.location}"/>
                    <input type="hidden" name="action" value="getUpdateLocation"/>
                    <input type="submit" value="常用地點"/>
                </form>
			 </td>
			<td>
			 	<form method="post" action="logoutHandler.do">			
				<input type="submit" value="登出">	
				 <input type="hidden" name="logout"	value="logout">	
				</form>
			</td>
	</tr>
	</table>
</body>
</html>