<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="UTF-8"%>
<%@ page  import="com.member.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<style>
table{
	margin-top:30px;
}
</style>
<body>
<%
	MemberVO memberVO =  (MemberVO)request.getAttribute("memberVO");

%>


<form method="post" action="member.do" name="form1">
<a href="select_page.jsp">回主頁面</a>
	<table >
		<tr>
			<td>會員姓名：</td>
			<td><input type="text" name="memID" size="45" value="<%= (memberVO==null)? "綠毛蟲":memberVO.getName() %>" /></td>		
	
		</tr>
		<tr>
			<td>會員信箱：</td>
			<td><input type="text" name="email" size="45" value="<%= (memberVO==null)? "綠毛蟲@gmail.com":memberVO.getEmail() %>" /></td>		
		</tr>
		
		<tr>
			<td>會員密碼：</td>
			<td><input type="text" name="password" size="45" value="<%= (memberVO==null)? "greenmomo":memberVO.getPassword() %>" /></td>		
		</tr>
		
		<tr>
			<td>會員電話：</td>
			<td><input type="text" name="phone" size="45" value="<%= (memberVO==null)? "綠毛蟲@gmail.com":memberVO.getPhone() %>" /></td>		
		</tr>
		
		<tr>
			<td>會員信用卡：</td>
			<td><input type="text" name="creditcard" size="45" value="<%= (memberVO==null)? "9898-5959-5959-4545":memberVO.getCreditcard() %>" /></td>		
		</tr>
		
		<tr>
			<td>會員寵物喜好設定：</td>
			<td><select name="pet">
			<option value="1">喜歡
			<option value="0">不喜歡
			</select></td>		
		</tr>
		<tr>
			<td>會員抽菸喜好設定：</td>
			<td><select name="pet">
			<option value="1">喜歡
			<option value="0">不喜歡
			</select></td>		
		</tr>
		<tr>
			<td>會員性別設定：</td>
			<td><select name="pet">
			<option value="1">男人
			<option value="0">女人
			</select></td>		
		</tr>
		<tr>
			<td>會員代幣：</td>
			<td><input type="text" name="token" size="45" value="<%= (memberVO==null)? "100":memberVO.getToken() %>" /></td>		
		</tr>
		
		<tr>
			<td>會員活動代幣：</td>
			<td><input type="text" name="activityToken" size="45" value="<%= (memberVO==null)? "1000":memberVO.getActivityToken() %>" /></td>		
		</tr>
		<tr> 
			<td>會員生日:</td>
			<td><input type="text" name="birthday" id="f_date1" ></td>
		</tr>		
		<tr>
			<td>會員驗證狀態：</td>
			<td><select name="verified">
			<option value="1">已驗證
			<option value="0">為驗證
			</select></td>		
		</tr>
		<tr>
			<td>會員性別設定：</td>
			<td><select name="babySeat">
			<option value="1">需要
			<option value="0">不需要
			</select></td>		
		</tr>
	</table>
	

</form>


</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->


</html>