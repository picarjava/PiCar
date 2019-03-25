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
<jsp:include page="/front-end/HomeMember/HeadMember.jsp" />
<jsp:include page="/front-end/HomeMember/HeadMemberReservatoin.jsp" />
    
    <style>
	#map { 
         height: 500px;  
         width: 1200px;
      } 
        #origin-input, 
       #destination-input { 
         background-color: #fff; 
         font-family: Roboto;  
         font-size: 18px; 
         font-weight: 400; 
         margin-left: 15px; 
         padding: 1 11px 1 13px; 
         text-overflow: ellipsis; 
         width: 400px; 
       } 
        .form-row {
		    display: inline-block;    /* 如需支持IE8以下版本，用浮动来做 */
		}
		
		
</style>
</head>
<!-- 登入功能串接 ，將VOmemID指定給 memID-->
<%@ page import="com.member.model.MemberVO"%>
<%MemberVO memberVO=(MemberVO)session.getAttribute("memberVO");
String memID=memberVO.getMemID();
session.setAttribute("memID",memID);
%> 

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
      預約叫車
    ============================-->
    <section id="contact">
      <div class="container wow fadeInUp">
        <div class="section-header">
          <h3 class="section-title">長期預約</h3>
          <h4 class="section-description">長期叫車需於三日前預約，預約天數需達7日以上即享85折優惠</h4>
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
	                   <h3>會員${memID} ${memberVO.name} 您好!  歡迎預約叫車</h3>
	                  <input type="hidden" type="text" name="memID" class="form-control" value="${memID}"  readonly placeholder="請輸入會員編號" />
	       			</div>
	       			<div class="form-row" >
		       			<div class="span">
						<p id="distance"></p>
						</div>
						<div class="span">
		       			<p id="duration"></p>
		       			</div>
		       			<div class="span">
		       			<p id="checkout"></p>
		       			</div>
	       			</div>
	       			<div class="form-row">
	       			    <div class="span">
		       			<p id="calculate"></p>
		       			</div>
		       		</div>	
 					<div class="form-row">
                      <div class="span">
                        <p>上車地點/下車地點</p> 
                        <input id="origin-input" type="text" name="startLoc" value="${singleOrder.startLoc}" class="form-control" placeholder="請輸入上車地點">
                      </div>
                      <div class="col">
                        <input id="destination-input" type="text" name="endLoc" value="${singleOrder.endLoc}" class="form-control" placeholder="請輸入下車地點">
                      </div>
                      <div  id="map" class="form-row">
                     </div>
                    </div>
                      
                      <!--==========================
                    google地圖開始
                    ============================-->
<!-- 					 <iframe  -->
<!-- 					      width="600"  -->
<!-- 					      height="450"  -->
<!-- 					      frameborder="0"  -->
<!-- 					      style="border:0"  -->
<!-- 					      src="https://www.google.com/maps/embed/v1/place?key=AIzaSyCWL8JxUOY0dQZ01M4rCgDU-oHLkP5BORI&q=高雄市政府"  -->
<!-- 					      allowfullscreen> -->
<!-- 					  </iframe> -->

                 <!--==========================
                     google地圖結束
                    ============================-->
<!--                 <div class="alert alert-light"> -->
<!--                   <p>上車時間</p> -->
<%--                   <input type="text" id="f_date1" name="startTime"  value="${singleOrder.startTime}"/> --%>
<!--                 </div> -->
                <!-- 長期叫車測試成功 -->
                <div class="alert alert-light">
	               <p>開始日期時間 / 結束日期</p>
	               <div class="input-group mb-3">
					  <input type="text" id="f_date1" name="startTime"  value="${singleOrder.startTime}" class="form-control" placeholder="請輸入開始日期與時間" aria-label="Recipient's username" aria-describedby="button-addon2">
					  至<input  onchange="totalDays()" type="text" id="f_date2" name="endTime"  value="${endTime}" class="form-control" placeholder="請輸入結束日期" aria-label="Recipient's username" aria-describedby="button-addon2">
					</div>
				</div>
				<div class="form-row">
	       			<div class="span">
		       			<p id="days"></p>
		       		</div>
		       		<div class="span">
		       			<p id="total"></p>
		       		</div>
		       	</div>
		       	<div class="form-row">
	       			<div class="span">
		       			<p id="totalCalculate"></p>
		       		</div>
		       	</div>
				
				<!-- 測試jquery新增日期的HTML -->
<!-- 		               <div class="alert alert-light"> -->
<!-- 						  <p> 開始日期時間 / 結束日期</p>  -->
<!-- 						  <div class="input-group mb-3"> -->
<%-- 					      <input type="text" id="f_date1" name="startTime"  value="${singleOrder.startTime}" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="button-addon2"> --%>
<!-- 						  <button onclick="appendText()" class="btn btn-outline-secondary" type="button" id="button-addon2">新增其他日期</button> -->
<!-- 						  </div> -->
						  
<!-- 						  <div id="addOne"> -->
<%-- 						  <input type="text" id="f_date2" name="endTime"  value="${endTime}" class="form-control" placeholder="請輸入結束日期" aria-label="Recipient's username" aria-describedby="button-addon2"> --%>
<!-- 						  </div> -->
<!-- 						</div> -->
			    <!-- 測試新增日期結束 -->
				
				
                <div class="form-group">
	            <p>備註</p>
	            <textarea class="form-control" name="note"  placeholder="請輸入備註">${singleOrder.note}</textarea>
	            </div>
                <div class="text-center"><button id="submitAutocomplete" type="submit">送出</button></div>

                <!-- /*放隱藏的標籤，讓Controller抓到參數進行操作*/ -->
                <input type="hidden" name="action" value="insertLongterm">
                <input type="hidden" name="orderType" value="4">
                <input type="hidden" id="startLng" name="startLng" value="">
                <input type="hidden" id="startLat" name="startLat" value="">
                <input type="hidden" id="endLng" name="endLng" value="">
                <input type="hidden" id="endLat" name="endLat" value="">
                <input type="hidden" id="totalAmount" name="totalAmount" value="">
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

 
 
</body>
<!-- auto place complete 開始 -->
  <script>
// This example requires the Places library. Include the libraries=places
// parameter when you first load the API. For example:
// <script
// src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">

function initMap() {
  var map = new google.maps.Map(document.getElementById('map'), {
    mapTypeControl: false,
    center: {lat: 23.914626, lng: 121.060895},
    zoom: 7.5
  });

  new AutocompleteDirectionsHandler(map);
}

/**
 * @constructor
 */
function AutocompleteDirectionsHandler(map) {
  this.map = map;
  this.originPlaceId = null;
  this.destinationPlaceId = null;
  this.travelMode = 'DRIVING';
  this.directionsService = new google.maps.DirectionsService;
  this.directionsDisplay = new google.maps.DirectionsRenderer;
  this.directionsDisplay.setMap(map);

  var originInput = document.getElementById('origin-input');
  var destinationInput = document.getElementById('destination-input');

  var originAutocomplete = new google.maps.places.Autocomplete(originInput);
  // Specify just the place data fields that you need.
  originAutocomplete.setFields(['place_id']);

  var destinationAutocomplete =
      new google.maps.places.Autocomplete(destinationInput);
  // Specify just the place data fields that you need.
  destinationAutocomplete.setFields(['place_id']);


  this.setupPlaceChangedListener(originAutocomplete, 'ORIG');
  this.setupPlaceChangedListener(destinationAutocomplete, 'DEST');

  this.map.controls[google.maps.ControlPosition.TOP_LEFT].push(originInput);
  this.map.controls[google.maps.ControlPosition.TOP_LEFT].push(
      destinationInput);

}

AutocompleteDirectionsHandler.prototype.setupPlaceChangedListener = function(
    autocomplete,mode) {
  var me = this;
  autocomplete.bindTo('bounds', this.map);

  autocomplete.addListener('place_changed', function() {
    var place = autocomplete.getPlace();

    if (!place.place_id) {
      window.alert('Please select an option from the dropdown list.');
      return;
    }
    if (mode === 'ORIG') {
      me.originPlaceId = place.place_id;
    } else {
      me.destinationPlaceId = place.place_id;
    }
    me.route();
  });
};



var totalLongterm;
AutocompleteDirectionsHandler.prototype.route = function() {
  if (!this.originPlaceId || !this.destinationPlaceId) {
    return;
  }
  var me = this;

  this.directionsService.route(
      {
        origin: {'placeId': this.originPlaceId},
        destination: {'placeId': this.destinationPlaceId},
        travelMode: this.travelMode
      },
      function(response, status) {
        if (status === 'OK') {
          me.directionsDisplay.setDirections(response);
        //呈現預估時間與距離
          var distance =response.routes[0].legs[0].distance.value;
    	  var duration=response.routes[0].legs[0].duration.value;
    	  
    	  var totalAmount =document.getElementById('totalAmount');
    	  totalAmount.value=parseInt((distance/1000*24.5*0.85)+40);
    	  totalLongterm=totalAmount.value; <!--存給實體變數-->
    	  
    	  document.getElementById('distance').innerHTML = 
             "<h3>預估距離</h3><h4>"+ parseInt(distance/1000) + "公里"+distance%1000+"公尺</h4>" ;
    	  document.getElementById('duration').innerHTML = 
              "<h3>預估時間</h3><h4>"+parseInt(duration/60/60)+"時"+parseInt(duration/60%60) + "分</h4>";
    	  document.getElementById('checkout').innerHTML = 
        	  "<h3>單趟金額</h3><h4>$"+ totalLongterm+"元</h4>";
          document.getElementById('calculate').innerHTML = 
        	  "<h6>"+parseInt(distance/1000) + "公里"+distance%1000+"公尺 X 24.5 元/每公里資費 X 0.85 長期預約折扣+ 40元 基本費/每趟 ="+ totalAmount.value +"元</h6>";
        } else {
          window.alert('Directions request failed due to ' + status);
        }
      });
  
     //     將資料轉緯經度存入表格value
	  var startLngInput = document.getElementById('startLng');
	  var startLatInput = document.getElementById('startLat');
	  var endLngInput = document.getElementById('endLng');
	  var endLatInput = document.getElementById('endLat');
	  
	  var geocoder = new google.maps.Geocoder();
	  geocoder.geocode({'placeId':this.originPlaceId},function(results,status){
		 
		  if (status === 'OK') {
	  			  startLatInput.value =results[0].geometry.location.lat();//得到起點緯經度資料Object
	  			  startLngInput.value=results[0].geometry.location.lng();
	  	  }else{
	  		  window.alert('No results found');
	  	  }
	  });
	  
	  geocoder.geocode({'placeId':this.destinationPlaceId},function(results,status){
		 
		  if (status === 'OK') {
	  			  endLatInput.value=results[0].geometry.location.lat();//得到迄點緯經度資料Object
	  			  endLngInput.value=results[0].geometry.location.lng();
	  	  }else{
	  		  window.alert('No results found');
	  	  }
	  });  
		  
};

    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCWL8JxUOY0dQZ01M4rCgDU-oHLkP5BORI&libraries=places&callback=initMap"
        async defer></script>
<!-- auto complete結束 -->


 <!--測試用class註冊 -->
<script> 
//  var count=1;
//  function appendText() {
//   	var txt1 ='<input type="text" id="test'+count+'" name="endTime"  value="${endTime}" class="endTime form-control" placeholder="請輸入結束日期"  >'	;	
//  		$("#addOne").append(txt1); // Append new elements
//  	}
 
 
//    $.datetimepicker.setLocale('zh');
//    $("#test"+count).datetimepicker({
//       theme: '',              //theme: 'dark',
//       timepicker:true,       //timepicker:true,
//      step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
//       format:'Y-m-d',         //format:'Y-m-d H:i:s',
//  	   // value: ,        // value:   new Date(),
//       //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//       //startDate: '',            '2017/07/10',  // 起始日
//       minDate:   minDateLong,            //'-1970-01-01', // 去除今日(不含)之前
//       maxDate:   maxDate         //  '+1970-01-01'  // 去除今日(不含)之後
//    });
  

   // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

   //      1.以下為某一天之前的日期無法選擇
//    var somedate3 = minDateLong;    //new Date();
//    $("#test"+count).datetimepicker({
//        beforeShowDay: function(date) {
//           if (date.getYear() <  somedate3.getYear())
//       	   return [false, ""];
//           else if (date.getYear() == somedate3.getYear())
//       	   if (date.getMonth() <  somedate3.getMonth())
//       		   return [false, ""];
//               else if (date.getMonth() == somedate3.getMonth())
//      		   if (date.getHours() < somedate3.getHours())
//       			   return [false, ""];
//        		   else if (date.getHours() == somedate3.getHours())
//        			   if (date.getMinutes() < somedate3.getMinutes())
//      				   return [false, ""];
         
//     if (date.getYear() <  somedate3.getYear() || 
//           (date.getYear() == somedate3.getYear() && date.getMonth() <  somedate3.getMonth()) || 
//           (date.getYear() == somedate3.getYear() && date.getMonth() == somedate3.getMonth() && date.getDate() < somedate3.getDate())) {
//            return [false, ""];
//        }
      
//        return [true, ""];
//    }});

  
   //      2.以下為某一天之後的日期無法選擇
//         var somedate4 = maxDate;    //new Date('2017-06-15');
//         $("#test"+count).datetimepicker({
//             beforeShowDay: function(date) {
//               if (  date.getYear() >  somedate4.getYear() || 
//                    (date.getYear() == somedate4.getYear() && date.getMonth() >  somedate4.getMonth()) || 
//                    (date.getYear() == somedate4.getYear() && date.getMonth() == somedate4.getMonth() && date.getDate() > somedate4.getDate())
//                 ) {
//                      return [false, ""]
//                 }
//               return [true, ""];
//         }});

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
             
             
             //計算總天數
             var days; 
             function totalDays(){
            	 var firstday=$("#f_date1").val();
            	 var y, m,d;
            	 y=parseInt(firstday.substr(0,4));
            	 m=parseInt(firstday.substr(5,2));
            	 d=parseInt(firstday.substr(8,2));

            	 var endday=$("#f_date2").val();
            	 var yy, mm,dd;
            	 yy=parseInt(endday.substr(0,4));
            	 mm=parseInt(endday.substr(5,2));
            	 dd=parseInt(endday.substr(8));

            	 /*開始相減*/
				 if(dd<d){
					 mm=mm-1;
					 if(yy%4==0){ /*若為閏年 2月是29天 ；大月1、3、5、7、8、10、12； 小月4、6、9、11*/
						 if(mm==1||3||5||7||8||10||12){
							 dd=dd+31;
						 }else if(mm==4||6||9||11){
							 dd=dd+30;
					     }else if(mm==2){
					    	 dd=dd+29;
					     }
					 }else{ /*若為平年 2月是28天*/
						 if(mm==1||3||5||7||8||10||12){
							 dd=dd+31;
						 }else if(mm==4||6||9||11){
							 dd=dd+30;
					     }else if(mm==2){
					    	 dd=dd+28;
					     }
					 }
					 /*開始相減*/
					 days=dd-d+1;
				 }else{
					 days=dd-d+1;
				 }
				 document.getElementById('days').innerHTML=
					 "<h3>預約天數</h3><h4>"+days+"天</h4>";
                 document.getElementById('total').innerHTML=
                	 "<h3>總金額</h3><h4>$"+totalLongterm*days+"元</h4>";
                 document.getElementById('totalCalculate').innerHTML=
                 totalLongterm +"單趟金額/元X"+days+"天=$"+totalLongterm*days+"元";
             };
</script>

</html>
