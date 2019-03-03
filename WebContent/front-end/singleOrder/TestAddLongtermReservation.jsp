<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.singleOrder.model.SingleOrderVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="zh">
<head>

    <jsp:include page="/regna-master/head.jsp" />
</head>

<body>
<% Timestamp endTime=(Timestamp)request.getAttribute("endTime");%>
 <!-- 錯誤列表 -->
    <%List<String> errorMsgs=(List<String>)request.getAttribute("errorMsgs");%>
    <c:if test="${not empty errorMsgs}"><ul class="list-group">
		  <li class="list-group-item active">Opps!錯誤訊息回報</li>
		  <c:forEach var="massage" items="${errorMsgs}">
		  <li class="list-group-item">${massage}</li>
		  </c:forEach>
		</ul>
	</c:if>

  <!--==========================
    Hero Section
  ============================-->
  <section id="hero">
    <div class="hero-container">
      <h1>Welcome to Picar</h1>
      <h2>We are team of smart ridesharing </h2>
      <a href="<%=application.getContextPath()%>/front-end/singleOrder/addReservation.jsp#contact" class="btn-get-started">單程預約</a>
      <a href="#contact" class="btn-get-started">長期預約</a>
    </div>
  </section><!-- #hero -->
  <!--==========================
      預約叫車
    ============================-->
    <section id="contact">
      <div class="container wow fadeInUp">
        <div class="section-header">
          <h3 class="section-title">長期預約</h3>
          <p class="section-description">長期叫車需於三日前預約，且14天內預約天數需達7日以上</p>
        </div>
      </div>

      <div class="container wow fadeInUp">
        <div class="row justify-content-center">
          <div class="col-lg-1 col-md-4">
            <div class="info">

            </div>
          </div>

          <div class="col-lg-9 col-md-8">
            <div class="form">
              <!-- 新增活動表單開始 -->
              <form action="<%=application.getContextPath()%>/singleOrder" method="post" role="form" class="contactForm">
 					<div class="form-group">
	                   <p>會員編號</p>
	                  <input type="text" name="memID" class="form-control" value="${singleOrder.memID}"   placeholder="請輸入會員編號" />
	       			</div>
 					<div class="form-row">
                      <div class="col">
                        <p>上車地點</p> 
                        <input type="text" name="startLoc" value="${singleOrder.startLoc}" class="form-control" placeholder="請輸入上車地點">
                      </div>
                      <div class="col">
                        <p>下車地點</p>
                        <input type="text" name="endLoc" value="${singleOrder.endLoc}" class="form-control" placeholder="請輸入下車地點">
                      </div>
                    </div>
                      <!--==========================
                    google地圖開始
                    ============================-->
					 <iframe 
					      width="600" 
					      height="450" 
					      frameborder="0" 
					      style="border:0" 
					      src="https://www.google.com/maps/embed/v1/place?key=AIzaSyCWL8JxUOY0dQZ01M4rCgDU-oHLkP5BORI&q=高雄市政府" 
					      allowfullscreen>
					  </iframe>

                 <!--==========================
                     google地圖結束
                    ============================-->
<!--                 <div class="alert alert-light"> -->
<!--                   <p>上車時間</p> -->
<%--                   <input type="text" id="f_date1" name="startTime"  value="${singleOrder.startTime}"/> --%>
<!--                 </div> -->
                <!-- 長期叫車測試成功 -->
<!--                 <div class="alert alert-light"> -->
<!-- 	               <p>開始日期時間 / 結束日期</p> -->
<!-- 	               <div class="input-group mb-3"> -->
<%-- 					  <input type="text" id="f_date1" name="startTime"  value="${singleOrder.startTime}" class="form-control" placeholder="請輸入開始日期與時間" aria-label="Recipient's username" aria-describedby="button-addon2"> --%>
<%-- 					  至<input type="text" id="f_date2" name="endTime"  value="${endTime}" class="form-control" placeholder="請輸入結束日期" aria-label="Recipient's username" aria-describedby="button-addon2"> --%>
<!-- 					</div> -->
<!-- 				</div> -->
				
				<!-- 測試jquery新增日期的HTML -->
		               <div class="alert alert-light">
						  <p> 開始日期時間 / 結束日期</p> 
						  <div class="input-group mb-3">
					      <input type="text" id="f_date1" name="startTime"  value="${singleOrder.startTime}" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="button-addon2">
						  <button onclick="appendText()" class="btn btn-outline-secondary" type="button" id="button-addon2">新增其他日期</button>
						  </div>
						  
						  <div id="addOne">
						  <input type="text" id="f_date2" name="endTime"  value="${endTime}" class="form-control" placeholder="請輸入結束日期" aria-label="Recipient's username" aria-describedby="button-addon2">
						  </div>
						</div>
			    <!-- 測試新增日期結束 -->
				
				
                <div class="form-group">
	            <p>備註</p>
	            <textarea class="form-control" name="note"  placeholder="請輸入備註">${singleOrder.note}</textarea>
	            </div>
                <div class="text-center"><button type="submit">送出</button></div>

                <!-- /*放隱藏的標籤，讓Controller抓到參數進行操作*/ -->
                <input type="hidden" name="action" value="insertLongterm">
                <input type="hidden" name="orderType" value="4">
                <!-- 訂單種類:預約叫車3/長期預約叫車4-->
              </form>
            </div>
          </div>
        </div>

      </div>
    </section><!-- #contact -->

  <!--==========================
    Footer
  ============================-->
  <footer id="footer">
    <div class="footer-top">
      <div class="container">

      </div>
    </div>

    <div class="container">
      <div class="copyright">
        &copy; Copyright <strong>Regna</strong>. All Rights Reserved
      </div>
      <div class="credits">
        <!--
          All the links in the footer should remain intact.
          You can delete the links only if you purchased the pro version.
          Licensing information: https://bootstrapmade.com/license/
          Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/buy/?theme=Regna
        -->
        Bootstrap Templates by <a href="https://bootstrapmade.com/">BootstrapMade</a>
      </div>
    </div>
  </footer><!-- #footer -->

 <jsp:include page="/regna-master/body.jsp" />
 
</body>
 <!--測試用class註冊 -->
<script> 

// $(DOCUMENT).READY(FUNCTION() {
// 	$("#BUTTON-ADDON2").CLICK(FUNCTION() {
// 		$("#BUTTON-ADDON2").ATTR("ONCLICK","'APPENDTEXT('+ INPUTNAME +')'");
// 	});


 function appendText() {
  	var txt1 ='<input type="text" id="'+inputName+'" name="endTime"  value="${endTime}" class="endTime form-control" placeholder="請輸入結束日期"  >'	;	
 		$("#addOne").append(txt1); // Append new elements
 		alert("有沒有執行這行");
 		register();
 		
 }
 
//  function init(){
// 	  $id('button-addon2').onclick = register(inputName);
// 	}
// 	window.onload = init;
 
	
//  var disabledDates;
var count=3;
var inputName= "f_date"+ count;

 //動態註冊日曆
 function register(){
	
   $.datetimepicker.setLocale('zh');
   $('"#'+inputName+'"').datetimepicker({
      theme: '',              //theme: 'dark',
      timepicker:true,       //timepicker:true,
     step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
      format:'Y-m-d',         //format:'Y-m-d H:i:s',
 	   // value: ,        // value:   new Date(),
      //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
      //startDate: '',            '2017/07/10',  // 起始日
      minDate:   minDateLong,            //'-1970-01-01', // 去除今日(不含)之前
      maxDate:   maxDate         //  '+1970-01-01'  // 去除今日(不含)之後
   });
  

   // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

   //      1.以下為某一天之前的日期無法選擇
   var somedate3 = minDateLong;    //new Date();
   $('"#'+inputName+'"').datetimepicker({
       beforeShowDay: function(date) {
          if (date.getYear() <  somedate3.getYear())
      	   return [false, ""];
          else if (date.getYear() == somedate3.getYear())
      	   if (date.getMonth() <  somedate3.getMonth())
      		   return [false, ""];
              else if (date.getMonth() == somedate3.getMonth())
     		   if (date.getHours() < somedate3.getHours())
      			   return [false, ""];
       		   else if (date.getHours() == somedate3.getHours())
       			   if (date.getMinutes() < somedate3.getMinutes())
     				   return [false, ""];
         
    if (date.getYear() <  somedate3.getYear() || 
          (date.getYear() == somedate3.getYear() && date.getMonth() <  somedate3.getMonth()) || 
          (date.getYear() == somedate3.getYear() && date.getMonth() == somedate3.getMonth() && date.getDate() < somedate3.getDate())) {
           return [false, ""];
       }
      
       return [true, ""];
   }});

  
   //      2.以下為某一天之後的日期無法選擇
        var somedate4 = maxDate;    //new Date('2017-06-15');
        $('"#'+inputName+'"').datetimepicker({
            beforeShowDay: function(date) {
              if (  date.getYear() >  somedate4.getYear() || 
                   (date.getYear() == somedate4.getYear() && date.getMonth() >  somedate4.getMonth()) || 
                   (date.getYear() == somedate4.getYear() && date.getMonth() == somedate4.getMonth() && date.getDate() > somedate4.getDate())
                ) {
                     return [false, ""]
                }
              return [true, ""];
        }});
        count++; 
 }// register()     

</script>
<!-- 新增日期結束 -->
				



 <!-- datetimepicker -->

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
//得到未來n天的Date
function getFutureDate(addDays) {
	var futureDate=new Date()
	futureDate.setDate(futureDate.getDate()+addDays);
	return futureDate;
	}
	
var minDate=getFutureDate(3);//須於三天前預約
var maxDate=getFutureDate(16); //僅能下定未來三天後的14天
var minDateLong=getFutureDate(9); //長期叫車需於三日前預約，且14天內預約天數需達7日以上

        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
           timepicker:true,       //timepicker:true,
           step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
           format:'Y-m-d H:i',         //format:'Y-m-d H:i:s',
      	   value:  minDate,        // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate: '',            '2017/07/10',  // 起始日
           minDate:   minDate,            //'-1970-01-01', // 去除今日(不含)之前
           maxDate:   maxDate         //  '+1970-01-01'  // 去除今日(不含)之後
        });
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        var somedate1 = minDate;    //new Date();
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
             var somedate2 = maxDate;    //new Date('2017-06-15');
             $('#f_date1').datetimepicker({
                 beforeShowDay: function(date) {
                   if (  date.getYear() >  somedate2.getYear() || 
                        (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
                        (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
                     ) {
                          return [false, ""]
                     }
                     return [true, ""];
             }});


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
        
        //第二個日曆註冊
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
           theme: '',              //theme: 'dark',
           timepicker:true,       //timepicker:true,
           step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
           format:'Y-m-d',         //format:'Y-m-d H:i:s',
      	   // value: ,        // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate: '',            '2017/07/10',  // 起始日
           minDate:   minDateLong,            //'-1970-01-01', // 去除今日(不含)之前
           maxDate:   maxDate         //  '+1970-01-01'  // 去除今日(不含)之後
        });
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        var somedate3 = minDateLong;    //new Date();
        $('#f_date2').datetimepicker({
            beforeShowDay: function(date) {
               if (date.getYear() <  somedate3.getYear())
            	   return [false, ""];
               else if (date.getYear() == somedate3.getYear())
            	   if (date.getMonth() <  somedate3.getMonth())
            		   return [false, ""];
                   else if (date.getMonth() == somedate3.getMonth())
            		   if (date.getHours() < somedate3.getHours())
            			   return [false, ""];
            		   else if (date.getHours() == somedate3.getHours())
            			   if (date.getMinutes() < somedate3.getMinutes())
            				   return [false, ""];
               
            if (date.getYear() <  somedate3.getYear() || 
               (date.getYear() == somedate3.getYear() && date.getMonth() <  somedate3.getMonth()) || 
               (date.getYear() == somedate3.getYear() && date.getMonth() == somedate3.getMonth() && date.getDate() < somedate3.getDate())) {
                return [false, ""];
            }
            
            return [true, ""];
        }});

        
        //      2.以下為某一天之後的日期無法選擇
             var somedate4 = maxDate;    //new Date('2017-06-15');
             $('#f_date2').datetimepicker({
                 beforeShowDay: function(date) {
                   if (  date.getYear() >  somedate4.getYear() || 
                        (date.getYear() == somedate4.getYear() && date.getMonth() >  somedate4.getMonth()) || 
                        (date.getYear() == somedate4.getYear() && date.getMonth() == somedate4.getMonth() && date.getDate() > somedate4.getDate())
                     ) {
                          return [false, ""]
                     }
                     return [true, ""];
             }});
             
            


        
</script>

</html>
