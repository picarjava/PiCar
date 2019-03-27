<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page  import="com.storeRecord.model.*" %>
<%@ page import="com.member.model.*" %>
<!DOCTYPE html>
<html>
<head>
<script src="jquery-1.12.4.min.js" >$('#post-form').one('submit', otherDoPost); </script>
<meta charset="UTF-8">
<title>Andia - Responsive Agency Template</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">


</head>
<style>
table{
	margin-top:30px;
	width:900px;
	border:1px solid #888888;
	margin-left:300px;
}

#tr1{
height:50px;
}
#tr2{
height:150px;
}
#tr3{
height:50px;
}

</style>
<body>
<%
	StoreRecordVO storeRecordVO =  (StoreRecordVO)request.getAttribute("storeRecordVO");
// 	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
%>
<jsp:include page="/front-end/HomeMember/HeadMember.jsp" />
<!--  <h3>addStoreRecord.jsp</h3> -->
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form method="post" action="storeRecord.do" name="form1" ID="post-form">
<!-- <a href="select_page.jsp">回主頁面</a> -->
	<table border="1" >
		
		<h3>請點選金額儲值帳戶</h3>
		<tr id="tr1">
			<td colspan="2">會員ID：${memberVO.memID}</td><br>
				
			<input type="hidden" name="memID"  value="${memberVO.memID}">	
	
		</tr>
		<tr id="tr2">
			<td>儲值金額：</td>
			<td>
			
			<img src="10000.png" onclick="document.getElementById('10000').checked=true" height="120" width="120">
			<input type="radio" id="10000" name="amount" value="10000">10000
			
			<img src="5000.png" onclick="document.getElementById('5000').checked=true" height="120" width="120">
			<input type="radio" id="5000" name="amount" value="5000">5000 
			
			<img src="2000.png" onclick="document.getElementById('2000').checked=true" height="120" width="120">			
			<input type="radio" id="2000" name="amount" value="2000">2000 
			
			<img src="1000.png" onclick="document.getElementById('1000').checked=true" height="120" width="120">
			<input type="radio" id="1000" name="amount" value="1000">1000
			
			</td>					<br>
		</tr>
		<tr id="tr3">
			<td >輸入信用卡號：</td>
<!-- 			<td><input type="text"  size="45"  /> -->

			<td>			
				<input style="width:70px" type="text" name="pan_no1" maxlength="4" size="4" onKeyUp="next(this)">-  
				<input style="width:70px" type="text" name="pan_no2" maxlength="4" size="4" onKeyUp="next(this)">- 
				<input style="width:70px" type="text" name="pan_no3" maxlength="4" size="4" onKeyUp="next(this)">-
				<input style="width:70px" type="text" name="pan_no4" maxlength="4" size="4" onKeyUp="next(this)">
			</td>	
			
						
							
		</tr>
		<%-- <tr>
			<td>撥款日期：</td>
			<td><input type="text" name="payTime"  id="f_date1" size="45" >
			</td>		
		</tr>				
		--%>
		
	</table><br>                          
	
	<input type="hidden" name="action" value="insert">
	<input type="button" onclick="alert()" value="結帳"  ID="post-form">
<%-- 	<input  type="hidden"  name="url" value=<%=request.getRequestURL()%>>  --%>
	
	

	
	
	
	
</form>
<script>


function next(obj) {  
    if (obj.value.length == obj.maxLength) {  
        do {  
            obj = obj.nextSibling;  
        } while (obj.nodeName != "INPUT");  
        obj.focus();  
    }         
}  
document.forms[0].N1.focus();  

 function alert(){
	 swal("刷卡成功 !", "已為您儲值!", "success");
	 $('#post-form').submit(); 
 }


		
</script>

</body>

</html>