<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/back-end/head_back.jsp" />
<meta charset="UTF-8">
<title>select_page.jsp</title>
<style>
#errmsg{
	width:200px;
	height:100px;
	color:red;
}
    <style>
    
    div.content {
	width:95%;
	margin-left:-7%;
}

#btn1 {
    padding:10px 20px; 
    background:#DDDDDD; 
    border:0 none;
    cursor:pointer;
    -webkit-border-radius: 10px;
    border-radius: 5px; 
    font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro', 'Noto Sans CJK SC', monospace;
    font-size: 16px;
    color:#444444;
    position: relative;
	transition: 0.4s;

}

input[type="submit"] {
    padding:10px 20px; 
    background:#DDDDDD; 
    border:0 none;
    cursor:pointer;
    -webkit-border-radius: 10px;
    border-radius: 5px; 
    font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro', 'Noto Sans CJK SC', monospace;
    font-size: 16px;
    color:#444444;
    position: relative;
	transition: 0.4s;

}
button[type="submit"] {
    padding:10px 20px; 
    background:#DDDDDD; 
    border:0 none;
    cursor:pointer;
    -webkit-border-radius: 10px;
    border-radius: 5px; 
    font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro', 'Noto Sans CJK SC', monospace;
    font-size: 16px;
    color:#444444;
    position: relative;
	transition: 0.4s;

}

input[type="submit"]:hover
{
    background:rgb(248, 197, 68);
}
button[type="submit"]:hover
{
    background:rgb(248, 197, 68);
}

#btn1:hover {
	background:rgb(248, 197, 68);
}

select, #i1 {
	border-radius: 5px; 
}
    </style>
</style>

</head>
<body>
<jsp:include page="/back-end/kidBodyLeft.jsp" />
<h3>select_page.jsp</h3>
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

	<ul>
		<li><a href="listAllmember_byDAO.jsp" >點選顯示全部會員</a>  <font color="blue" face="DFKai-sb">All members by DAO</font> <br><br>
	
<!-- 		<li><a href="member.do?action=getAll" >點選顯示全部會員</a>  <font color="blue" face="DFKai-sb">All members getFormSession</font> <br><br> -->
		
		<li>
			<form method="post" action="member.do">
				<b>請輸入會員編號(EX:M001) (資料格式驗證  by Controller ):</b><br> 
				<input type="text" name="memID">
				<input type="hidden" name="action" value="getOne_For_Display"><br>
				<input type="submit" value="submit"> 
			</form> 
		</li>	
		<br>
		<li>
			<form method="post" action="member.do">
				<b>請輸入會員編號(EX:M001) (資料格式驗證  by Java Script):</b><br> 
				<input type="text" name="memID">
				<input type="hidden" name="action" value="getOne_For_Display"><br>
				<input type="button" value="submit" onclick="fun1()">  
			</form> 
		</li>	
		<br>
		
			<jsp:useBean id="memberdao" scope="page" class="com.member.model.MemberDAO" />
		
		
		<li>
			<form method="post" action="member.do">
				<b>請選擇會員編號</b> <br>
				<select size="1" name="memID">
					<c:forEach var="memberVO" items="${memberdao.all}">
						<option value="${memberVO.memID}">${memberVO.memID}
					</c:forEach>
				</select> 
				<input type="hidden" name="action" value="getOne_For_Display"><br>
				<input type="submit" value="submit">
			</form>
		</li>
		<br>
		<li>
			<form method="post" action="member.do">
				<b>請選擇會員姓名</b><br>
				<select size="1" name="memID">
					<c:forEach var="memberVO" items="${memberdao.all}">
						<option value="${memberVO.memID}">${memberVO.name}					
					</c:forEach>
				</select>
				<br>	
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="submit">			
			</form>
			
		</li>
	</ul>
<!-- 	<ul> -->
<!-- 		<li>	 -->
<!-- 		<a href="addMember.jsp">點選新增會員資料</a> -->
<!-- 		</li> -->
<!-- 	</ul> -->
	
	
	
	

<script>    
   function fun1(){
      with(document.form1){
         if (memID.value=="") 
             alert("請輸入員工編號!");
         else if (isNaN(memID.value)) 
             alert("員工編號格式不正確!");
      
         else
             submit();
      }
   }
</script>

</body>
</html>