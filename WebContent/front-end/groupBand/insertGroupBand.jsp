<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.groupBand.model.*"%>

<%
	GroupBandVO groupBandVO = (GroupBandVO) request.getAttribute("groupBandVO");
%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<meta charset="BIG5">
<title>Insert title here</title>
<style>
table#table-1 {
	width: 450px;
	background-color: #008888;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>
</head>
<body bgcolor=#aaaaaa>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<table id="table-1">


		<tr>
			<td>
				<h3>發起揪團 - insertGroupBand.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="slistAllGroupBand.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>
	<h3>發起揪團</h3>

	<form action="<%=request.getServletContext().getContextPath()%>/GroupBand" method="POST" enctype="multipart/form-data">
		<table border="1">

			<tr>
				<td><input type="radio" name="orderT" value="0" checked
					onclick="groupif(this.value)">揪團</td>
				<td><input type="radio" name="orderT" value="1"
					onclick="groupif(this.value)">長期揪團<br></td>
			</tr>
			
			<tr>
			<td>揪團類別</td>
			<td><select name="groupType">
			<option value="演唱會">演唱會</option>
			<option value="旅遊">旅遊</option>
			<option value="美食">美食</option>
			<option value="運動團">運動團</option>
			<option value="展覽">展覽</option>
			<option value="遊樂園">遊樂園</option>
			</select></td>
			</tr>
			

			<tr>
				<td>團名</td>
				<td><input type="TEXT" name="groupName" size="25"
					value="<%=(groupBandVO == null) ? "今天來建servlet" : groupBandVO.getGroupName()%>" /></td>
			</tr>
			<tr>
				<td>簡介:</td>

				<td><textarea name="introduction" id="note" rows="3" cols="50"><%=(groupBandVO == null) ? "除錯中".trim() : groupBandVO.getIntroduction().trim()%></textarea></td>
			</tr>

			<tr>
				<td>揪團圖片</td>


  
				<td><input type="file" id="progressbarTWInput" name="photo" size="25" accept="image/gif, image/jpeg, image/png"
					value="<%=(groupBandVO == null) ? "" : groupBandVO.getPhoto()%>" />
					 <img id="preview_progressbarTW_img" src="#"  width="100px"   height="100px"  style = "display:none"/>
				</td>
				
			</tr>

			<tr>
				<td>上車地點:</td>

				<td><select name="startLoc">
						<option value="桃園火車站">桃園市</option>
						<option value="台北市火車站">台北市</option>
				</select></td>
			</tr>

			<tr>
				<td>下車地點:</td>

				<td><select name="endLoc">
						<option value="桃園火車站">桃園市</option>
						<option value="台北市火車站">台北市</option>
				</select></td>
			</tr>

			<tr>
				<td>下限人數</td>
				<td><select id="lowerLimit" onchange="pvalue(this.value);" name="lowerlimit">
						<%
							for (int a = 2; a < 5; a++) {
						%>
						<option  value="<%=a%>"><%=a%></option>
						<%
							}
						%>
				</select></td>
			</tr>


			<tr>
				<td>上限人數</td>
				<td><select id="Lower" name="upperlimit">
						<%
							for (int a = 2; a < 5; a++) {
						%>
						<option value="<%=a%>"><%=a%></option>
						<%
							}
						%>
					
					</select></td>
			</tr>


			<tr>
				<td>上車日期</td>

				<td><input name="startTime" id="start_date" type="text"
					style="display: none" onchange="datestamps(this.value);" > 
					<input name="startTimes" id="f_date1"
					type="text"></td>
							<td id="enddate" style="display:none" >	
				<select id="days" name="days" style="display: none" onchange="timestamps(this.value);">
		
						<%
							for (int a = 1; a < 20; a++) {
						%>
						<option value="<%=a%>"><%=a%></option>
						<%
							}
						%>
				</select>
				</td>
			</tr>

			<tr>
		
<td >結束日期:</td>

				<td id="enddate"  >
<input name="endtime" id="end_date" type="text"
					style="display: none" disabled="disabled"></td>
				
			</tr>

<tr>
			<td>備註:</td>

			<td><textarea name="note" id="note" rows="3" cols="50"><%=(groupBandVO == null) ? "謝謝".trim() : groupBandVO.getNote().trim()%></textarea></td>
			</tr>
			<tr>
				<td>隱私設定:</td>

				<td><input type="checkbox" value="1" name="privates">隱私權<br>
				</td>
			</tr>


			<tr>
				<td><input type="hidden" name="action" value="insert" /> <input
					type="submit" value="發起揪團" /></td>
				<td><input type="reset" value="清除揪團" /></td>

			</tr>
		</table>
	</form>
</body>
<%
java.sql.Timestamp startTime = null;
try {
	startTime = groupBandVO.getStartTime();
 } catch (Exception e) {
	 startTime = new java.sql.Timestamp(System.currentTimeMillis());
 }
%> 
<script>

function datestamps(dater){
	end_date = document.getElementById("end_date");
	days = document.getElementById("days");
	
	var todays = new Date(dater);
	var todayss =todays.getTime();
	
	var timers=parseInt(days.value)+1;
	var ss = new Date(1970,0,timers);
	
	var day3=ss.getTime();
	day3=day3+todayss;

	var d = new Date();

	d.setTime(day3);



	qr =d.getDate();
	Month = d.getMonth()+1;
	date = d.getDate();

	//小於10的日期轉換 例01 02 03
	if(parseInt(Month)<10)
		{
		Month="0"+Month;
		
		}
		
	//小於10的日期轉換 例01 02 03
	if(parseInt(date)<10)
	{
		date="0"+date;

	}	

	end_date.value=d.getFullYear()+"-"+Month+"-"+date;
	}

function timestamps(timer) {
	end_date = document.getElementById("end_date");
	start_date =document.getElementById("start_date");
	
	//放入日期 日期時間轉換		
	var todays = new Date(start_date.value);
	var todayss =todays.getTime();
	

	var timers=parseInt(timer)+1;
	
 	var ss = new Date(1970,0,timers);
	

var day3=ss.getTime();
day3=day3+todayss;

var d = new Date();

d.setTime(day3);



qr =d.getDate();
Month = d.getMonth()+1;
date = d.getDate();

//小於10的日期轉換 例01 02 03
if(parseInt(Month)<10)
	{
	Month="0"+Month;
	
	}
	
//小於10的日期轉換 例01 02 03
if(parseInt(date)<10)
{
	date="0"+date;

}	

end_date.value=d.getFullYear()+"-"+Month+"-"+date;
}

</script>
	<script>
							function pvalue(s) {

								Lower = document.getElementById("Lower");

								Lower.innerHTML = "";

								for (x = s; x < 5; x++) {
									Lower.innerHTML = Lower.innerHTML
											+ '<option name="'+x+'" value="'+x+'">'
											+ x + '</option>';

								}

							}
						</script>
<script>
	$.datetimepicker.setLocale('zh'); // kr ko ja en
	$('#f_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : true, //timepicker: false,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d H:i',
		//value : new Date(),
		value : '<%=startTime%>',
	//disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	        '2017/07/10',  // 起始日
	minDate:           '-1970-01-01', // 去除今日(不含)之前
	maxDate:           '+1970-01-20'  // 去除今日(不含)之後
	});
</script>

<script>
	$.datetimepicker.setLocale('zh'); // kr ko ja en
	//$(function() {
		$('#start_date').datetimepicker(
				{
					format : 'Y-m-d H:i',
					onShow : function() {
						this.setOptions({
							maxDate : $('#start_date').val() ? $('+1970-01-20')
									.val() :  true
						})
					},
					timepicker : true,
					value : '<%=startTime%>',
					minDate:           '-1970-01-01', // 去除今日(不含)之前
					maxDate:           '+1970-01-20'  // 去除今日(不含)之後
				});

// 		$('#end_date').datetimepicker(
// 				{
// 					format : 'Y-m-d',
// 					onShow : function() {
// 						this.setOptions({
// 							minDate : $('#start_date').val() ? $('#start_date')
// 									.val() :  true
// 						})
// 					},
// 					value : $('#start_date').val(),
// 					timepicker : false,
// 					minDate:           '#start_date', // 去除今日(不含)之前
// 					maxDate:           '+1970-01-20'  // 去除今日(不含)之後
// 				});
//	});

</script>

<script>

	function groupif(number) {
		days = document.getElementById("days");
		startdate = document.getElementById("start_date");
		enddate = document.getElementById("end_date");
		fdate1 = document.getElementById("f_date1");
		enddates = document.getElementById("enddate");
		if (number == 1) {
			days.style="";
			startdate.style = "";
			enddate.style = "";
			fdate1.style = "display:none";
			enddates.style = "";
		} else {
			days.style="display:none";
			startdate.style = "display:none";
			enddate.style = "display:none";
			fdate1.style = "";
			enddates.style = "display:none";

		}
	}
</script>
<!-- JavaScript & jQuery 版本-->

<!-- HTML part -->





<!-- JavaScript part -->

<script>

$("#progressbarTWInput").change(function(){

  readURL(this);

});



function readURL(input){
	
  if(input.files && input.files[0]){

    var reader = new FileReader();

    reader.onload = function (e) {
    	
       $("#preview_progressbarTW_img").attr('src', e.target.result);
       $("#preview_progressbarTW_img").removeAttr("style");
    }

    reader.readAsDataURL(input.files[0]);

  }

}

</script>
</html>