<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.singleOrder.model.SingleOrderVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Update single order</title>

</head>
<body>
    <c:if test="${not empty errorMsgs}">
    <h3>以下錯誤</h3>
    <div id="errorMsg">
        <c:forEach var="errorMsg" items="${errorMsgs}">
        <p>${errorMsg}</p>
        </c:forEach>
    </div>
    </c:if>
    <c:if test="${not empty singleOrder}">
    <form action="<%=application.getContextPath()%>/singleOrder" method="POST">
        <input type="hidden" name="orderID" value="${singleOrder.orderID}"/>
                    訂單ID ${singleOrder.orderID}<br>
        <label for="driverID">司機ID</label><input type="text" name="driverID" value="${singleOrder.driverID}" id="driverID"/><br>
        <label for="state">狀態</label>
        <select name="state" id="state">
            <c:forEach var="state" items="${stateMap}">
                <option value="${state.key}" ${state.key eq singleOrder.state? "selected" : ""}>${state.value}</option>
            </c:forEach>
        </select><br>
        <label for="startTime">開始時間</label><input type="text" id="f_date1" name="startTime" value="${singleOrder.startTime}" id="startTime"/><br>
        <label for="endTime">結束時間</label><input type="text" id="f_date2" name="endTime" value="${singleOrder.endTime}" id="endTime"/><br>
        <label for="startLoc">上車地點</label><input type="text" name="startLoc" value="${singleOrder.startLoc}" id="startLoc"/><br>
        <label for="endLoc">下車地點</label><input type="text" name="endLoc" value="${singleOrder.endLoc}" id="endLoc"/><br>
        ${orderTypeMap[singleOrde.orderType]}<br>
        <c:forEach var="index" begin="1" end="5">
        <input type="radio" name="rate" value="${index}" id="rate${index}" ${singleOrder.rate == index? "checked": ""}/><label for="rate${index}">${index}分</label>
        </c:forEach><br>
        <input type="hidden" name="action" value="update"/>
        <input type="submit">
    </form>
    </c:if>
</body>
<% 
  SingleOrderVO singleOrderVO = (SingleOrderVO) request.getAttribute("singleOrder");
  java.sql.Date startTime = null;
  try {
      startTime = singleOrderVO.getStartTime();
   } catch (Exception e) {
      startTime = new java.sql.Date(System.currentTimeMillis());
   }
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
        
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
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
        var somedate1 = new Date('2019-02-20');
        $('#f_date1').datetimepicker({
            beforeShowDay: function(date) {
            if (date.getYear() <  somedate1.getYear() || 
               (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
               (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())) {
                           return [false, ""]
                      }
                      return [true, ""];
              }});
        
        var somedate2 = new Date();
        $('#f_date1').datetimepicker({
            beforeShowDay: function(date) {
            if (date.getYear() <  somedate2.getYear() || 
               (date.getYear() == somedate2.getYear() && date.getMonth() <  somedate2.getMonth()) || 
               (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() < somedate2.getDate())) {
                           return [false, ""]
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