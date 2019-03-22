<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.driverReport.model.*"%>
<%@ page import="com.admin.model.*"%>

<%
DriverReportVO driverReportVO = (DriverReportVO) request.getAttribute("driverReportVO"); //DriverReportServlet.java (Concroller) 存入req的DriverReportVO物件 (包括幫忙取出的DriverReportVO, 也包括輸入資料錯誤時的DriverReportVO物件)

%>

<%
	AdminVO adminVO = (AdminVO) session.getAttribute("adminVO");
%>

<html>
<head>
<title>PICAR BACK-END</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"
	name="viewport" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<!--     Fonts and icons     -->
<link rel="stylesheet" type="text/css"
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
<!-- Material Kit CSS -->
<link href="assets/css/material-dashboard.css" rel="stylesheet" />
<style>
table, tr, td, th {
	background-color: white;
	border: 1px solid #aaa;
	text-align: center;
	padding: 5px;
	margin-left: 10%;
	font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro',
		'Noto Sans CJK SC', monospace;
}

table {
	width: 80%;
}

.col-9 {
	margin-top: -15px;
	margin-left: -55px;
	margin-bottom: 1rem;
}

h4 {
	padding-left: 50%;
	font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro',
		'Noto Sans CJK SC', monospace;
}

h3 {
	font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro',
		'Noto Sans CJK SC', monospace;
}

#btn1 {
	padding: 20px;
	font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro',
		'Noto Sans CJK SC', monospace;
	margin-left: 48%;
}

.form-control {
	background: no-repeat center bottom, center calc(100% - 1px);
	border: 0;
	transition: background 0s ease-out;
	padding-left: 480px;
	padding-right: 0;
	border-radius: 0;
	font-size: 14px;
}

#s2 {
	font-size: 20px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>檢舉司機資料修改 - update_DriverReport_input.jsp</h3>
				<h4>
					<a href="select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h4>資料修改</h4>
	<%-- 錯誤表列 --%>
	<table>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
	</table>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/driverReport/driverReport.do" name="form1">
		<table>
			<tr>
				<td>檢舉司機單號<font color=red></font></td>
				<td><input class="form-control" type="text" size="45"
					value="<%=driverReportVO.getDreportID()%>"
					placeholder="Readonly input here..." readonly></td>
			</tr>
			<tr>
				<td>會員編號</td>
				<td><input class="form-control" type="TEXT" name="memID"
					size="45" value="<%=driverReportVO.getMemID()%>"
					placeholder="Readonly input here..." readonly /></td>
			</tr>
			<tr>
				<td>管理員編號</td>
				<td><input class="form-control" type="TEXT" name="adminID" size="45"
					value="<%=adminVO.getAdminID()%>" readonly/></td>
			</tr>
			<tr>
				<td>檢舉日期</td>
				<td><input class="form-control" name="time" id="f_date1" size="45" type="text" readonly></td>
			</tr>
			<tr>
				<td>訂單編號</td>
				<td><input class="form-control" type="TEXT" name="orderID" size="45"
					value="<%=driverReportVO.getOrderID()%>" readonly/></td>
			</tr>
			<tr>
				<td>內容</td>
				<td><input class="form-control" type="TEXT" name="content" size="45"
					value="<%=driverReportVO.getContent()%>" readonly/></td>
			</tr>
			<tr>
				<td>處理狀態</td>
				<td><select name="state" class="form-control form-control-lg"
					id="s2">
						<option value="1" ${(driverReportVO.state=='1') ? 'selected' : ''}>已處理
						
						<option value="0" ${(driverReportVO.state=='0') ? 'selected' : ''}>未處理
						
				</select></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="dreportID"
			value="<%=driverReportVO.getDreportID()%>"> <input
			type="submit" value="送出修改" id="btn1">
	</FORM>
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
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=driverReportVO.getTime()%>', // value:   new Date(),
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