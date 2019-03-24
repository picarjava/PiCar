<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<title>Andia - Responsive Agency Template</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,400">
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Droid+Sans">
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Lobster">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/HomeMember/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/HomeMember/assets/prettyPhoto/css/prettyPhoto.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/HomeMember/assets/css/flexslider.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/HomeMember/assets/css/font-awesome.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/HomeMember/assets/css/style.css">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/ico/apple-touch-icon-57-precomposed.png">
<style>
 span { 
 	text-align: left; 
 	　 
 } 


</style>



</head>

<body>
	<%
		MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
	%>
	<jsp:include page="/front-end/HomeMember/HeadMember.jsp" />
<jsp:include page="/front-end/HomeMember/HeadMemberSetting.jsp" />
	<div class="container">
                <div class="row">
                    <div class="contact-form span5">
                		
				
						<h3>addMember.jsp</h3>
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>

						<form method="post" action="member.do" name="form1" enctype="multipart/form-data">
							<a href="select_page.jsp">回主頁面</a><br> 
							<span class="span">

								會員姓名  ： <input type="text" name="name" size="45"
								value="" /><br><br>


								會員信箱  ： <input type="text" name="email" size="45"
								value="" /><br><br>


								<!-- 		<tr> --> <!-- 			<td>會員密碼：</td> --> 
								<input type="hidden"	name="password" size="45"	value="" />
								
								會員電話 ： <input type="text" name="phone" size="45"	value="" /><br><br>



								信用卡號 ： <input type="text" name="creditcard" size="45"	value="" /><br><br>



								寵物喜好： <input type="radio" name="pet" value="1">	 喜歡 
											<input	type="radio" name="pet" value="0"> 不喜歡<br><br>		
													
								 抽菸喜好： <input type="radio" name="smoke" value="1">喜歡
								 			<input type="radio" name="smoke" value="0"> 不喜歡<br><br>
								 			 		
								 會員性別： <input type="radio" name="gender" value="1">男生 
										<input type="radio" name="gender" value="0"> 女生<br><br>
										
							<!-- 			<td>會員代幣：</td> --> <%-- 			<td><input type="text" name="token" size="45" value="<%= (memberVO==null)? "100":memberVO.getToken() %>" ></td>		 --%>
							
								<input type="hidden" name="token" size="45" value="0"> 
								<!-- 			<td>會員活動代幣：</td> -->
								<%-- 			<td><input type="text" name="activityToken" size="45" value="<%= (memberVO==null)? "1000":memberVO.getActivityToken() %>" /></td>		 --%>
								<input type="hidden" name="activityToken" size="45" value="0" />

								 嬰兒座椅：
								<input type="radio" name="babySeat" value="1"> 需要 
								<input	type="radio" name="babySeat" value="0"> 不需要<br><br> 
				
								會員生日 :  <input type="text" name="birthday" id="f_date1"><br>


								<input type="hidden" name="verified" value="0">
								
								
								</div>
								
								<div class="contact-address span5">
							<br><br><br> 
							會員照片： <!-- 			<td> --> <!-- 			<input type="file" name="pic" > <br></td>	 -->
								 <input
								type="file" name="pic" onchange="readURL(this)"
								targetID="preview_progressbarTW_img"><br>
								<img	alt="請選取照片"  width="200" height ="200" id="preview_progressbarTW_img"><br><br>

							 <input type="hidden" name="action" value="insertver2"> <input
								type="submit" value="送出新增"> <img src="cat.jpg"
								height="20" width="20" onClick="idwrite(this)">
								</span>
						</form>
						</div>
					</div>
				</div>
			</div>


</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
	$.datetimepicker.setLocale('zh');
	$('#f_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value : new Date(), // value:   new Date(),
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

	//
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

	input.getAttribute("targetID")
	getElementById(preview_progressbarTW_img)
	function readURL(input) {
		if (input.files && input.files[0]) {
			var imageTagID = input.getAttribute("targetID");
			var reader = new FileReader();
			reader.onload = function(e) {
				var img = document.getElementById(imageTagID);
				img.setAttribute("src", e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}

	function idwrite(name) {
		form1.name.value = "卡比獸"
		form1.email.value="samchiang74@gmail.com"
		form1.phone.value="0933-995-225"
		form1.creditcard.value="1584-5947-2846-5947"
		form1.pet.value = "1"
		form1.smoke.value="1"
		form1.gender.value="1"
		form1.babySeat.value="1"
		form1.birthday.value="2000-10-10"
		//   	  form1.cust_niname.value="資策會小小書童" 

	}
</script>



</html>