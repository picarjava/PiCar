<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
 <h3>addMember.jsp</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form method="post" action="member.do" name="form1" enctype="multipart/form-data">
<a href="select_page.jsp">回主頁面</a>
	<table >
		
	
		<tr>
			<td>會員姓名：</td>
			<td><input type="text" name="name" size="45" value="<%= (memberVO==null)? "綠毛蟲":memberVO.getName() %>" /></td>		
	
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
			<td>
			<input type="radio" name="pet" value="1"> 喜歡
			<input type="radio" name="pet" value="0"> 不喜歡<br>
			</td>		
		</tr>
		<tr>
			<td>會員抽菸喜好設定：</td>
			<td>
			<input type="radio" name="smoke" value="1"> 喜歡
			<input type="radio" name="smoke" value="0"> 不喜歡<br>
<!-- 			<select name="smoke"> -->
<!-- 			<option value="1">喜歡 -->
<!-- 			<option value="0">不喜歡 -->
			</select></td>		
		</tr>
		<tr>
			<td>會員性別設定：</td>
			<td>
			<input type="radio" name="gender" value="1"> 男生
			<input type="radio" name="gender" value="0"> 女生<br></td>		
		</tr>
		<tr>
<!-- 			<td>會員代幣：</td> -->
<%-- 			<td><input type="text" name="token" size="45" value="<%= (memberVO==null)? "100":memberVO.getToken() %>" ></td>		 --%>
				<td><input type="hidden" name="token" size="45" value="0" ></td>
		</tr>
		
		<tr>
<!-- 			<td>會員活動代幣：</td> -->
<%-- 			<td><input type="text" name="activityToken" size="45" value="<%= (memberVO==null)? "1000":memberVO.getActivityToken() %>" /></td>		 --%>
				<td><input type="hidden" name="activityToken" size="45" value="0" /></td>
		</tr>
		<tr> 
			<td>會員生日:</td>
			<td><input type="text" name="birthday" id="f_date1" ></td>
		</tr>		
		<tr>
			
			<input type="hidden" name="verified" value="0">	
		</tr>
		<tr>
			<td>嬰兒座椅設定：</td>
			<td>
			<input type="radio" name="babySeat" value="1"> 需要
			<input type="radio" name="babySeat" value="0"> 不需要<br></td>		
		</tr>
		<tr>
			<td>會員照片：</td>
			<td>
			<input type="file" name="pic" > <br></td>		
		</tr>
		
	</table>                          
	
	<input type="hidden" name="action" value="insert">
	<input type="submit" value="送出新增">
</form>



</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 	      value:   new Date(), // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>


</html>