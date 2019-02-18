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
	href="../../datetimepicker/jquery.datetimepicker.css" />
<script src="../../datetimepicker/jquery.js"></script>
<script src="../../datetimepicker/jquery.datetimepicker.full.js"></script>
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
					<a href="select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>
	<h3>發起揪團</h3>

	<form action="/PiCar/GroupBand" method="POST">
		<table>

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
					value="<%=(groupBandVO == null) ? "" : groupBandVO.getGroupName()%>" /></td>
			</tr>
			<tr>
				<td>簡介:</td>

				<td><textarea name="introduction" id="note" rows="3" cols="50">
					<%=(groupBandVO == null) ? "" : groupBandVO.getIntroduction()%> </textarea></td>
			</tr>

			<tr>
				<td>揪團圖片</td>

				<td><input type="file" name="photo" size="25"
					value="<%=(groupBandVO == null) ? "" : groupBandVO.getPhoto()%>" />
				</td>
			</tr>

			<tr>
				<td>上車地點:</td>

				<td><select name="startLoc">
						<option value="a01">桃園市</option>
						<option value="a02">台北市</option>
				</select></td>
			</tr>

			<tr>
				<td>下車地點:</td>

				<td><select name="endLoc">
						<option value="a01">桃園市</option>
						<option value="a02">台北市</option>
				</select></td>
			</tr>

			<tr>
				<td>下限人數</td>
				<td><select id="lowerLimit" onchange="pvalue(this.value);" name="lowerlimit">
						<%
							for (int a = 2; a < 5; a++) {
						%>
						<option name="<%=a%>" value="<%=a%>"><%=a%></option>
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
						<option name="<%=a%>" value="<%=a%>"><%=a%></option>
						<%
							}
						%>
						<script language="Javascript">
							function pvalue(s) {

								Lower = document.getElementById("Lower");

								Lower.innerHTML = "";

								for (x = s; x < 5; x++) {
									Lower.innerHTML = Lower.innerHTML
											+ '<option name="'+x+'" value="'+x+'">'
											+ x + '</option>';

								}

							}
						</script></td>
			</tr>


			<tr>
				<td>上車日期</td>

				<td><input name="startTime" id="start_date" type="text"
					style="display: none"> 
					<input name="startTime" id="f_date1"
					type="text"></td>
			</tr>

			<tr>
				<td><nobr id="enddate" style="display:none">結束日期:</nobr></td>

				<td><input name="endtime" id="end_date" type="text"
					style="display: none"></td>
			</tr>


			<td>備註:</td>

			<td><textarea name="note" id="note" rows="3" cols="50">
					<%=(groupBandVO == null) ? "" : groupBandVO.getNote()%> </textarea></td>
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

<script>
	$.datetimepicker.setLocale('zh'); // kr ko ja en
	$('#f_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : true, //timepicker: false,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d H:i:s',
		value : new Date(),
	//disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	        '2017/07/10',  // 起始日
	//minDate:           '-1970-01-01', // 去除今日(不含)之前
	//maxDate:           '+1970-01-01'  // 去除今日(不含)之後
	});
</script>

<script>
	$.datetimepicker.setLocale('zh'); // kr ko ja en
	$(function() {
		$('#start_date').datetimepicker(
				{
					format : 'Y-m-d',
					onShow : function() {
						this.setOptions({
							maxDate : $('#end_date').val() ? $('#end_date')
									.val() : false
						})
					},
					timepicker : false
				});

		$('#end_date').datetimepicker(
				{
					format : 'Y-m-d',
					onShow : function() {
						this.setOptions({
							minDate : $('#start_date').val() ? $('#start_date')
									.val() : false
						})
					},
					timepicker : false
				});
	});
</script>

<script>
	function groupif(number) {
		startdate = document.getElementById("start_date");
		enddate = document.getElementById("end_date");
		fdate1 = document.getElementById("f_date1");
		enddates = document.getElementById("enddate");
		if (number == 1) {
			startdate.style = "";
			enddate.style = "";
			fdate1.style = "display:none";
			enddates.style = "";
		} else {
			startdate.style = "display:none";
			enddate.style = "display:none";
			fdate1.style = "";
			enddates.style = "display:none";

		}
	}
</script>
</html>