<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.groupReport.model.*"%>

<%
	GroupReportVO groupReportVO = (GroupReportVO) request.getAttribute("groupReportVO");
%>

<html>
<head>
<title>PICAR BACK-END</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"
	name="viewport" />
<!--     Fonts and icons     -->
<link rel="stylesheet" type="text/css"
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
<!-- Material Kit CSS -->
<link href="assets/css/material-dashboard.css" rel="stylesheet" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>檢舉揪團資料新增 - addGroupReport.jsp</title>

<style>
<
style>table, tr, td, th {
	border: 1px solid #aaa;
	text-align: center;
	padding: 5px;
	text-align: center;
	font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro',
		'Noto Sans CJK SC', monospace;
}

table {
	width: 100%;
	width: 800px;
	margin-top: 1px;
	margin-bottom: 1px;
}

#error {
	margin-left: 20px;
}

body {
	font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro',
		'Noto Sans CJK SC', monospace;
	margin-left: 150px;
}

h4 {
	font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro',
		'Noto Sans CJK SC', monospace;
}

h5 {
	margin-left: 20%;
	font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro',
		'Noto Sans CJK SC', monospace;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>檢舉揪團資料新增 - addGroupReport.jsp</h3>
			</td>
			<td>
				<h5>
					<a href="greport_select_page.jsp"><img src="images/arrow.png"
						width="40" height="40" border="0">回首頁</a>
				</h5>
			</td>
		</tr>
	</table>

	<h4>資料新增</h4>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/groupReport/groupReport.do" name="form1">
		<table>
			<tr>
				<td>會員編號</td>
				<td><input type="TEXT" name="memID" size="45"
					value="<%= (groupReportVO==null)? "M001" : groupReportVO.getMemID()%>" /></td>
			</tr>
			<tr>
				<td>揪團編號</td>
				<td><input type="TEXT" name="groupID" size="45"
					value="<%= (groupReportVO==null)? "G001" : groupReportVO.getGroupID()%>" /></td>
			</tr>
			<tr>
				<td>管理員編號</td>
				<td><input type="TEXT" name="adminID" size="45"
					value="<%= (groupReportVO==null)? "A001" : groupReportVO.getAdminID()%>" /></td>
			</tr>
			<tr>
				<td>檢舉內容</td>
				<td><input type="TEXT" name="content" size="45"
					value="<%= (groupReportVO==null)? "揪團內容很奇怪真可怕壓" : groupReportVO.getContent()%>" /></td>
			</tr>
			
			<tr>
				<td>檢舉日期</td>
				<td><input name="time" id="f_date1" type="text"></td>
			</tr>

			<tr>
				<td>處理狀態</td>
				<td><input type="TEXT" name="state" size="45"
					value="<%= (groupReportVO==null)? "0" : groupReportVO.getState()%>" /></td>
			</tr>



		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date time = null;
  try {
	    time = groupReportVO.getTime();
   } catch (Exception e) {
	    time = new java.sql.Date(System.currentTimeMillis());
   }
%>
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
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=time%>', // value:   new Date(),
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

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
</html>