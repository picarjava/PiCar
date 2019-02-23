<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.singleOrder.model.SingleOrderVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert single order</title>
<style>
    #errorMsg > p {
        color: red;
    }
    
    #errorMsg > p:before {
        content:'＊';
    }
</style>
</head>
<body>
    <h3>新增訂單</h3>
    <c:if test="${not empty errorMsgs }">
        <h3>資料未填或格式錯誤</h3>
        <div id="errorMsg">
            <c:forEach var="errorMsg" items="${errorMsgs}">
                <p>${errorMsg}</p>
            </c:forEach>
        </div>
    </c:if>
    <form action="<%=application.getContextPath()%>/singleOrder" method="POST">
        <label for="memId">會員ID </label><input type="text" name="memID" id="memID" value="${singleOrder.memID}"/><br>
        <label for="startTime">上車時間 </label><input type="text" id="f_date1" name="startTime" id="startTime" value="${singleOrder.startTime}"/><br>
        <label for="startLoc">上車地點 </label><input type="text" name="startLoc" id="startLoc" value="${singleOrder.startLoc}"/><br>
        <label for="endLoc">下車地點 </label><input type="text" name="endLoc" id="endLoc" value="${singleOrder.endLoc}"/><br>
        <label for="orderType">訂單種類 </label>
        <select name="orderType" id="orderType">
            <c:forEach var="orderType" items="${orderTypeMap}">
                <option value="${orderType.key}" ${orderType.key eq singleOrder.orderType ? "selected": ""}>${orderType.value}</option>
            </c:forEach>
        </select><br>
        <label for="note">備註 </label><br>
        <textarea name="note" id="note" rows="3" cols="50">${singleOrder.note}</textarea><br>
        <input type="hidden" name="action" value="insert"/>
        <input type="submit"/>
    </form>
</body>
<% 
  SingleOrderVO singleOrderVO = (SingleOrderVO) request.getAttribute("singleOrder");
  String startTime = null;
  if (singleOrderVO != null)
      if (singleOrderVO.getStartTime() != null)
          startTime = new SimpleDateFormat("yyyy-MM-dd mm:ss").format(singleOrderVO.getStartTime());
%>
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
           timepicker:true,       //timepicker:true,
           step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
           format:'Y-m-d H:i',         //format:'Y-m-d H:i:s',
           value: '<%=startTime%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:             '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        var somedate1 = new Date();
        $('#f_date1').datetimepicker({
            beforeShowDay: function(date) {
               if (date.getYear() <  somedate1.getYear())
            	   return [false, ""];
               else if (date.getYear() == somedate1.getYear())
            	   if (date.getMonth() <  somedate1.getMonth())
            		   return [false, ""];
                   else if (date.getMonth() == somedate1.getMonth())
            		   if (date.getHours() < somedate1.getHours())
            			   return [false, ""];
            		   else if (date.getHours() == somedate1.getHours())
            			   if (date.getMinutes() < somedate1.getMinutes())
            				   return [false, ""];
               
            if (date.getYear() <  somedate1.getYear() || 
               (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
               (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())) {
                return [false, ""];
            }
            
            return [true, ""];
        }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //            if (  date.getYear() >  somedate2.getYear() || 
        //                 (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //                 (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
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
        //            if (  date.getYear() <  somedate1.getYear() || 
        //                 (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //                 (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //                   ||
        //                  date.getYear() >  somedate2.getYear() || 
        //                 (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //                 (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>