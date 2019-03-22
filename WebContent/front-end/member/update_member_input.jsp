<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page  import="com.member.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>update_member_input.jsp</title>
</head>
<style>
table{
	margin-top:30px;
}
</style>
<body>
<%
	MemberVO memberVO =  (MemberVO)request.getAttribute("memberVO");
response.setHeader("Cache-Control","no-store"); //HTTP 1.1
response.setHeader("Pragma","no-cache");        //HTTP 1.0
response.setDateHeader ("Expires", 0);
%>
 <h3>update_member_input.jsp</h3>
 
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
<a id="GIbutton" href="<%=request.getContextPath()%>/front-end/HomeMember/index.jsp">請按此回會員首頁</a><br>
<a  href=listOneMemberByUpdate.jsp> 請按此回上一頁 </a>
	<table >
		<tr>
			<td>會員編號：</td>
			<td>  <%= memberVO.getMemID() %>   </td>		
	
		</tr>
	
		<tr>
			<td>會員姓名：</td>
			<td><input type="text" name="name" size="45" value="<%=memberVO.getName() %>" /></td>		
	
		</tr>
		<tr>
			<td>會員信箱：</td>
			<td><input type="text" name="email" size="45" value="<%=memberVO.getEmail() %>" /></td>		
		</tr>
		
<!-- 		<tr> -->
<!-- 			<td>會員密碼：</td> -->
			<td><input type="hidden" name="password" size="45" value="<%=memberVO.getPassword() %>" /></td>		
<!-- 		</tr> -->
		
		<tr>
			<td>會員電話：</td>
			<td><input type="text" name="phone" size="45" value="<%=memberVO.getPhone() %>" /></td>		
		</tr>
		
		<tr>
			<td>會員信用卡：</td>
			<td><input type="text" name="creditcard" size="45" value="<%=memberVO.getCreditcard() %>" /></td>		
		</tr>
		
		
		<tr>
			<td>會員寵物喜好：</td>
			<td><c:choose>
			  <c:when test="${memberVO.verified == '1'}">喜歡寵物  </c:when>
			  <c:when test="${memberVO.verified == '0'}">不喜歡寵物  </c:when>			 
			</c:choose>			
			<input type = "hidden" name="pet" value= "<%=memberVO.getPet() %>"></td>		
		</tr>
		<tr>
			<td>會員抽菸喜好：</td>
			<td><c:choose>
			  <c:when test="${memberVO.verified == '1'}">抽菸  </c:when>
			  <c:when test="${memberVO.verified == '0'}">不抽菸  </c:when>			 
			</c:choose>			
			<input type = "hidden" name="smoke" value= "<%=memberVO.getSmoke() %>"></td>		
		</tr>
		<tr>
			<td>會員性別：</td>
			<td><c:choose>
			  <c:when test="${memberVO.gender == '1'}">男生  </c:when>
			  <c:when test="${memberVO.gender == '0'}">女生  </c:when>			 
			</c:choose>
			<input type = "hidden" name="gender" value= "<%=memberVO.getGender() %>">
			</td>		
		</tr>
		<tr>
			<td>會員代幣：</td>			
			<td><%=memberVO.getToken() %>
			<input type="hidden" name="token" size="45" value="<%=memberVO.getToken() %>" ></td>		
		</tr>
		
		<tr>
			<td>會員活動代幣：</td>
			<td><%=memberVO.getActivityToken() %>
			<input type="hidden" name="activityToken" size="45" value="<%=memberVO.getActivityToken() %>" />
			</td>		
		</tr>
		<tr> 
			<td>會員生日:</td>
			<td><%=memberVO.getBirthday() %>
			<input type="hidden" name="birthday" id="f_date1" ></td>
		</tr>		
		<tr>
			<td>會員驗證狀態：</td>
			<td>
			<c:choose>
			  <c:when test="${memberVO.verified == '1'}">已經驗證  </c:when>
			  <c:when test="${memberVO.verified == '0'}">尚未驗證  </c:when>			 
			</c:choose>
			<input type = "hidden" name="verified" value= "<%=memberVO.getVerified() %>"></td>		
		</tr>
		<tr>
			<td>嬰兒座椅設定：</td>
			<td>
			<c:choose>
			  <c:when test="${memberVO.babySeat == '1'}">需要  </c:when>
			  <c:when test="${memberVO.babySeat == '0'}">不需要  </c:when>			 
			</c:choose>
			<input type = "hidden" name="babySeat" value= "<%=memberVO.getBabySeat() %>"></td>		
		</tr>
		<tr>
			<td>會員照片：</td>
			<td><img src="http://localhost:8081/PiCar/front-end/member/member.do?memID=${memberVO.memID}" alt="這是圖片替代文字" onerror="this.src='cat.jpg'" width='200' height="200" id="preview_progressbarTW_img">
		<input type="file" name="pic" onchange="readURL(this)" targetID="preview_progressbarTW_img"></td>
			<td></td>
			<td>
			 <br></td>		
		
			 
		</tr>
		
		
		
	</table>                          
	<input type="hidden" name="memID" value="<%=memberVO.getMemID() %> ">
	<input type="hidden" name="action" value="update">
	<input type="submit" value="submit">
</form>
<script>
input.getAttribute("targetID")
getElementById(preview_progressbarTW_img)
function readURL(input){
  if(input.files && input.files[0]){
    var imageTagID = input.getAttribute("targetID");
    var reader = new FileReader();
    reader.onload = function (e) {
       var img = document.getElementById(imageTagID);
       img.setAttribute("src", e.target.result);
    }
    reader.readAsDataURL(input.files[0]);
  }
}
</script>


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
 	      value: '<%=memberVO.getBirthday()%>', // value:   new Date(),
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