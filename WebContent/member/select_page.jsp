<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>select_page.jsp</title>
<style>
#errmsg{
	width:200px;
	height:100px;
	color:red;
}
</style>

</head>
<body bgcolor="#c1ece9">
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
		<li><a href="listAllmember_byDAO.jsp" >�I����ܥ������u</a>  <font color="blue" face="DFKai-sb">All members by DAO</font> <br><br>
	
		<li><a href="member.do?action=getAll" >�I����ܥ������u</a>  <font color="blue" face="DFKai-sb">All members getFormSession</font> <br><br>
		
		<li>
			<form method="post" action="member.do">
				<b>�п�J�|���s��(EX:M001) (��Ʈ榡����  by Controller ):</b><br> 
				<input type="text" name="memID">
				<input type="hidden" name="action" value="getOne_For_Display"><br>
				<input type="submit" value="submit"> 
			</form> 
		</li>	
		<br>
		<li>
			<form method="post" action="member.do">
				<b>�п�J�|���s��(EX:M001) (��Ʈ榡����  by Java Script):</b><br> 
				<input type="text" name="memID">
				<input type="hidden" name="action" value="getOne_For_Display"><br>
				<input type="button" value="submit" onlick="fun1()">  
			</form> 
		</li>	
		
		
			<jsp:useBean id="memberdao" scope="page" class="com.member.model.MemberDAO" />
		
		<br><br>
		<li>
			<form method="post" action="member.do">
				<b>�п�ܭ��u�s��</b> <br>
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
				<b>�п�ܭ��u�m�W</b><br>
				<select size="1" name="memID">
					<c:forEach var="memberVO" items="${memberdao.all}">
						<option value="${memberVO.memID}">"${memberVO.name}" 					
					</c:forEach>
				</select>
				<br>	
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="submit">			
			</form>
			
		</li>
	</ul>
	<ul>
		<li>	
		<a href="addMember1.jsp">�I��s�W���u���</a>
		</li>
	</ul>
	
	
	
	

<script>    
   function fun1(){
      with(document.form1){
         if (memID.value=="") 
             alert("�п�J���u�s��!");
         else if (isNaN(memID.value)) 
             alert("���u�s���榡�����T!");
      
         else
             submit();
      }
   }
</script>

</body>
</html>