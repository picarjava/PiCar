<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.groupReport.model.*"%>
<%@ page import="com.admin.model.*"%>

<%
GroupReportVO groupReportVO = (GroupReportVO) request.getAttribute("groupReportVO"); 

%>
<%
	AdminVO adminVO = (AdminVO) session.getAttribute("adminVO");
%>

<html>
<head>
<title>PICAR BACK-END</title>
<jsp:include page="/back-end/head_back.jsp" />
<meta charset="utf-8">
<style>
input[type="submit"] {
    padding:10px 20px; 
    background:#DDDDDD; 
    border:0 none;
    cursor:pointer;
    -webkit-border-radius: 10px;
    border-radius: 5px; 
    font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro', 'Noto Sans CJK SC', monospace;
    font-size: 16px;
    color:#444444;
    position: relative;
	transition: 0.4s;

}

input[type="submit"]:hover
{
    background:rgb(248, 197, 68);
}

</style>

</head>
<body bgcolor='white'>
    <div class="wrapper ">
        <jsp:include page="/back-end/kidBodyLeft.jsp" />
        <div class="main-panel">
            <div class="content">
                <div class="container-fluid">
                    <!-- your content here -->
                    <div class="container-fluid">
                        <div class="col-9">

					<h4>新增管理員</h4>

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
								<td>檢舉揪團單號<font color=red></font></td>
								<td><input class="form-control" type="text" size="45"
									value="<%=groupReportVO.getGreportID()%>"
									placeholder="Readonly input here..." readonly></td>
							</tr>
							<tr>
								<td>會員編號</td>
								<td><input class="form-control" type="TEXT" name="memID"
									size="45" value="<%=groupReportVO.getMemID()%>"
									placeholder="Readonly input here..." readonly /></td>
							</tr>
							<tr>
								<td>揪團編號</td>
								<td><input class="form-control" type="TEXT" name="groupID" size="45"
									value="<%=groupReportVO.getGroupID()%>" readonly/></td>
							</tr>
							<tr>
								<td>管理員編號</td>
								<td><input class="form-control" type="TEXT" name="adminID" size="45"
									value="<%=adminVO.getAdminID()%>" readonly/></td>
							</tr>
							<tr>
								<td>檢舉內容</td>
								<td><input class="form-control" type="TEXT" name="content" size="45"
									value="<%=groupReportVO.getContent()%>" readonly/></td>
							</tr>
							<tr>
								<td>檢舉日期</td>
								<td><input class="form-control" name="time" id="f_date1" size="45" type="text" readonly></td>
							</tr>
							<tr>
								<td>處理狀態</td>
								<td><select name="state" class="form-control form-control-lg" id="s2">
										<option value="1" ${(groupReportVO.state=='1') ? 'selected' : ''}>已處理
										<option value="0" ${(groupReportVO.state=='0') ? 'selected' : ''}>未處理
								</select></td>
							</tr>
						</table>
							<br> 
							<input type="hidden" name="action" value="update"> 
							<input type="hidden" name="greportID" value="<%=groupReportVO.getGreportID()%>"> 
							<input type="submit" value="送出修改">
						</FORM>
	                  </div>
                </div>
                <jsp:include page="/back-end/kidFooter.jsp" />
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
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=groupReportVO.getTime()%>', // value:   new Date(),
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