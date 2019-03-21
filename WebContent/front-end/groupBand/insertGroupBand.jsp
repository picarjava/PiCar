<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.groupBand.model.*"%>

<%
	GroupBandVO groupBandVO = (GroupBandVO) request.getAttribute("groupBandVO");
%>

<!DOCTYPE html>
<html>
<head>
<link href="img/favicon.png" rel="icon">
<link href="img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- <!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700"
	rel="stylesheet">

<!-- <!-- Bootstrap CSS File -->
<link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!--  Libraries CSS Files  -->
<link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="lib/animate/animate.min.css" rel="stylesheet">
<link href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/css/style.css" rel="stylesheet">
<link href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/css/flexslider.css" rel="stylesheet">
<link href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/css/font-awesome.css" rel="stylesheet">
<!-- <!-- Main Stylesheet File --> 
<link rel="stylesheet" type="text/css"
	href="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<meta charset="utf-8">
<title>Insert title here</title>
<!-- google map用 -->
<style>
	#map { 
         height: 500px;  
         width: 1100px;
      } 
        #origin-input, 
       #destination-input { 
         background-color: #fff; 
         font-family: Roboto;  
         font-size: 15px; 
         font-weight: 300; 
         margin-left: 12px; 
         padding: 1 11px 1 13px; 
         text-overflow: ellipsis; 
         width: 350px; 
       } 
       body {
           text-align: left;
       }
       .about-us {
    margin-top: 120px;
}
.h3center{
    text-align: center;
}
.radio {
display:inline;
  margin: 0.5rem;
}
.radio input[type="radio"] {
  position: absolute;
  opacity: 0;
}
.radio input[type="radio"] + .radio-label:before {
  content: '';
  background: #f4f4f4;
  border-radius: 100%;
  border: 1px solid #b4b4b4;
  display: inline-block;
  width: 1.4em;
  height: 1.4em;
  position: relative;
  top: -0.2em;
  margin-right: 1em;
  vertical-align: top;
  cursor: pointer;
  text-align: center;
  -webkit-transition: all 250ms ease;
  transition: all 250ms ease;
}
.radio input[type="radio"]:checked + .radio-label:before {
  background-color: #2dc997;
  box-shadow: inset 0 0 0 4px #f4f4f4;
}
.radio input[type="radio"]:focus + .radio-label:before {
  outline: none;
  border-color: #2dc997;
}
.radio input[type="radio"]:disabled + .radio-label:before {
  box-shadow: inset 0 0 0 4px #f4f4f4;
  border-color: #b4b4b4;
  background: #b4b4b4;
}
.radio input[type="radio"] + .radio-label:empty:before {
  margin-right: 0;
}
a:not([href]):not([tabindex]) {
color: #2dc997;
}
a:not([href]):not([tabindex]):hover{
color: #ffffff;
}
.form-control:hover{

}
.form-group{
    margin-top: 20px;
}
.form-control-lg, .input-group-lg>.form-control, .input-group-lg>.input-group-addon, .input-group-lg>.input-group-btn>.btn {
    font-size: 1.1rem;
}
.buttonS{
    font-family: "Poppins", sans-serif;
    border-radius: 50px;
    padding: 5px 22px;
    background: #f5f5f5;
    color: #2dc997;
    font-style: italic;
    text-decoration: none;
    -moz-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    -webkit-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    border-radius: 50px;
/*     border: 2px solid #2dc997; */
    0 1px 25px 0 rgba(0,0,0,.05) inset,: ;
    0 -1px 25px 0 rgba(0,0,0,.05) inset: ;
    -webkit-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
}
}
.buttonS:hover {
    font-family: "Poppins", sans-serif;
    border-radius: 50px;
    padding: 5px 22px;
    background: #2dc997;
    color: #f5f5f5;
    font-style: italic;
    text-decoration: none;
    -moz-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    -webkit-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    border-radius: 50px;
    border: 2px solid #2dc997;
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    -webkit-box-shadow:
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow:
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
}
</style>
</head>
<body bgcolor="#aaaaaa">
<jsp:include page="/front-end/HomeMember/HeadMember.jsp" />
<jsp:include page="/front-end/HomeMember/HeadMemberGroup.jsp" />
<div class="about-us container">
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<h4 class="h3center">發起揪團 <span class="badge badge-secondary"></span></h4>
<div class="about-us-text span12">
	<form  action="<%=request.getServletContext().getContextPath()%>/GroupBand" method="POST" enctype="multipart/form-data">
		
		<div class="container">
  <div class="radio">
    <input id="radio-1" type="radio" name="groupKind" value="5" checked
					onclick="groupif(this.value)">
    <label for="radio-1" class="radio-label">揪團</label>
  </div>

  <div class="radio">
    <input id="radio-2" type="radio" name="groupKind" value="6"
					onclick="groupif(this.value)">
    <label  for="radio-2" class="radio-label">長期揪團</label>
  </div>
  <div class="radio">
  <input  type="checkbox" style="zoom: 1.8" value="1" name="privates">
  <label  for="radio-2" class="radio-label">隱私權</label>
    </div>
	</div>
	
	 <div class="form-group">
	 <div>揪團類別</div>
	 <select name="groupType" class="form-control" id="exampleFormControlSelect1">
			<option value="演唱會">演唱會</option>
			<option value="旅遊">旅遊</option>
			<option value="美食">美食</option>
			<option value="運動團">運動團</option>
			<option value="展覽">展覽</option>
			<option value="遊樂園">遊樂園</option>
			</select>
	 </div>
	  <div class="form-group">
	   <div>團名:</div>
	  <input type="TEXT" name="groupName" required size="25" class="form-control form-control-lg"
					value="<%=(groupBandVO == null) ? "今天來建servlet" : groupBandVO.getGroupName()%>" />
					
	   </div>
	 
	   <div class="form-group">
  <div>簡介:</div>
    <textarea name="introduction" required id="note exampleFormControlTextarea1" class="form-control" rows="3" cols="50"><%=(groupBandVO == null) ? "除錯中".trim() : groupBandVO.getIntroduction().trim()%></textarea></textarea>
  </div>
	 
	 
	
	  <div class="form-group">
	   <div>下限人數:</div>
	 <select  class="form-control"  id="lowerLimit" onchange="pvalue(this.value);" name="lowerlimit">
						<%
							for (int a = 2; a < 5; a++) {
						%>
						<option  value="<%=a%>"><%=a%></option>
						<%
							}
						%>
				</select>
	  </div>
	 
	 
	 	  <div class="form-group">
	 	   <div>上限人數:</div>
	<select  class="form-control"  id="Lower" name="upperlimit">
						<%
							for (int a = 2; a < 5; a++) {
						%>
						<option value="<%=a%>"><%=a%></option>
						<%
							}
						%>
					
					</select>
	  </div>
	 
	  <div class="form-group">
	   <div>備註:</div>
	 <textarea name="note" required id="note exampleFormControlTextarea1" class="form-control" rows="3" cols="50"><%=(groupBandVO == null) ? "謝謝".trim() : groupBandVO.getNote().trim()%></textarea>
	 </div>
	 <div id="innerdate"></div>
	  <div class="form-group">
	 <button type="button" id="buttons" onchange="buttones();" class="btn btn-outline-success">請點選輸入日期</button>
				<select id="days" name="days" style="display: none" onchange="timestamps();"></select>
				<nobr>時間:</nobr> 
				<nobr id="datetime" >00:00</nobr> 
				<nobr>   日期:</nobr> 
				<input name="startTime" id="start_date" type="text"
					 onchange="datestamps();" readonly="readonly" size="8" value=" ">
				<nobr id="brs"></nobr>  
						
<input name="endTime" id="end_date" type="text"
					style="display: none" readonly="readonly" size="8">
					<nobr id="numd"></nobr>
	 </div>
	 
	 
	
	 
	 
	  <div class="form-group">
	  <div>揪團圖片:</div>
	  <div id="Verifyimage" style="color:#FF3333"></div>
	 <input type="file" id="progressbarTWInput" name="photo" size="25" accept="image/gif, image/jpeg, image/png"
					value="<%=(groupBandVO == null) ? "" : groupBandVO.getPhoto()%>" />
					 <img id="preview_progressbarTW_img" src="#"  width="100px"   height="100px"  style = "display:none"/>
	 
	 
	  </div>
	   <div>上車地點:</div>
	   <div class="form-group">
	  					<div class="form-row">
		       			<div class="col" id="col">
						<p id="distance"></p>
						</div>
						<div class="col">
		       			<p id="duration"></p>
		       
		       			</div>
	       			</div>
				    <div class="form-row">              
                        <input id="origin-input" type="text" name="startLoc" value="${groupBand.startLoc}" class="form-control" placeholder="請輸入上車地點">
                      
                        <input id="destination-input" type="text" name="endLoc" value="${groupBand.endLoc}" class="form-control" placeholder="請輸入下車地點">
                      </div>
                      <div  id="map" class="col12">
                   
                    </div>
	  </div>
	  <div class="form-group">
	  <div>
	 				<input  type="hidden" name="action" value="insert" /> <input class="buttonS"
					type="submit" value="發起揪團" id="send"/>
			<input class="buttonS" type="reset" value="清除揪團" />
			</div>
			</div>
	
				<input type="hidden" name="memIDs" value="${memberVO.memID }" /> 
				<input type="hidden" name="action" value="insert" /> 
                <input type="hidden" id="startLng" name="startLng" value="">
                <input type="hidden" id="startLat" name="startLat" value="">
                <input type="hidden" id="endLng" name="endLng" value="">
                <input type="hidden" id="endLat" name="endLat" value="">
				<input type="hidden" id="distancees" name="distancees" value="">	
	</form>
	</div>
	</div>
</body>
<!-- google map auto place complete 開始 -->
<script>
var Verifyimage=document.getElementById("Verifyimage");
var send=document.getElementById("send");
var col=document.getElementById("col");
var innerdate=document.getElementById("innerdate");
send.onclick=function(){
	//必填 ，不填不能跑
	var x=0;
var start_date=document.getElementById("start_date").value;
var progressbarTWInput=document.getElementById("progressbarTWInput").value;
var distance=document.getElementById("distance");


if(distance.innerHTML==''){
	col.innerHTML="<h3 style='color:#FF3333'>※請輸入台灣地址※<h3>";
x++;
	}

if(start_date==' '){
	innerdate.innerHTML="<h3 style='color:#FF3333'>※請填寫日期※<h3>";
	x++;	
	}
if(progressbarTWInput.length<1){
Verifyimage.innerHTML="<h3 style='color:#FF3333'>※請上傳圖片※<h3>";
x++;
}
if(x>0){
	return false;
	
}
}



</script>
 <script>
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
    	  
    	  var distancees = document.getElementById('distancees');
    	  distancees.value=distance;    	  
    	  document.getElementById('distance').innerHTML = 
             "<h3>預估距離</h3>"+ parseInt(distance/1000) + "公里"+distance%1000+"公尺" ;
    	  document.getElementById('duration').innerHTML = 
              "<h3>預估時間</h3>"+parseInt(duration/60/60)+"時"+parseInt(duration/60%60) + "分";
        } else {
          window.alert('Directions request failed due to ' + status);
        }
      });
  
		  //將地址資料轉為緯經度
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



<%
java.sql.Timestamp startTime = null;
try {
	startTime = groupBandVO.getStartTime();
 } catch (Exception e) {
	 startTime = new java.sql.Timestamp(System.currentTimeMillis());
 }
%> 
<script>

$('#buttons').datetimepicker(
		{
			format : 'Y-m-d H:i:s',
			onShow : function() {
				this.setOptions({
					maxDate : $('#buttons').val() ? $('+1970-01-20')
							.val() :  true
				})
			},
			step: 5,
			timepicker : true,
					value : '+1970-01-05',
			minDate:           '+1970-01-05', // 去除今日(不含)之前
			maxDate:           '+1970-01-20'  // 去除今日(不含)之後
		});
		
function buttones(){
	$.datetimepicker.setLocale('zh'); // kr ko ja en
	buttons = document.getElementById("buttons");
	start_date =document.getElementById("start_date");
	//$(function() {
		
		

	datestamps();
	
}

function numday(){
	days = document.getElementById("days");

}	
	
function datestamps(){
	buttons = document.getElementById("buttons");
	end_date = document.getElementById("end_date");
	days = document.getElementById("days");
	datetime =document.getElementById("datetime");
	start_date =document.getElementById("start_date");
	
// 	alert(buttons.value);
	start_date.value=buttons.value;
	//取得使用者輸入的日期 
	var totalAmount = new Date(start_date.value);
	
	//轉成毫秒
	var totalAmounts =totalAmount.getTime();
	
	//取得現在日期
	var Today=new Date();
	
	//將現在日期 - 使用者輸入日期
	var sunday =parseInt(totalAmount.getDate())-parseInt(Today.getDate());
	
	//取得當月最後一天
	var LastOneDay=new Date(Today.getFullYear(),Today.getMonth()+1,0);
	
	//判段為正負數 執行不同方式
	if(sunday<0)
	{
		//將當月最後一天減掉日期總和
		sunday=parseInt(LastOneDay.getDate())+sunday;
 		sunday=19-sunday;
 		
	}else
	{
		sunday=19-sunday;		
	}
	
	
	//將一開始顯示數字設為空值
	days.innerHTML="";
	
	//位數字產生日期
	for(a=7 ;a<=sunday ;a++){
		days.innerHTML=days.innerHTML+"<option value="+a+">"+a+"</option>";
	}

	//將日期轉成數字做 +法
	var timers=parseInt(days.value)+1;
	
	//宣告一個1970年的格式 ，為了計算目前日期+上天數，這段程式碼天數轉換
	var dateAmount = new Date(1970,0,timers);
	
	//將1970年格式轉成毫秒
	var day3=dateAmount.getTime();
	
	//將使用者入日期+天數 (用毫秒相加)
	day3=day3+totalAmounts;

	//將日前從毫秒轉換回來
	var d = new Date();

	d.setTime(day3);

	//月份 日期 轉換
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
	

	
	
	//將日期月份轉換完顯示在畫面上
	end_date.value=d.getFullYear()+"-"+Month+"-"+date;
	
	//時間轉換
	var hour=totalAmount.getHours();
	var min=totalAmount.getMinutes();
	
	if(totalAmount.getHours()<10)
	{
		hour="0"+hour;		
	}
	
	if(totalAmount.getMinutes()<10)
	{
		min="0"+min;		
	}
	
	
	
	//將時間月份轉換完顯示在畫面上
	datetime.innerHTML="";
	datetime.innerHTML=hour+":"+min;
	
	
	if(days.innerHTML=="")
	{
		if(days.innerHTML=="")
		{
			end_date.value="以超過揪團20天的準則，請洽公開說明書";		
		}
	}	
	numd =document.getElementById("numd");
	numd.innerHTML='';
	for(a=2;a<=parseInt(days.value)+1;a++){
		
	//宣告一個1970年的格式 ，為了計算目前日期+上天數，這段程式碼天數轉換	
	var dateAmo = new Date(1970,0,a);
	
	//將1970年格式轉成毫秒
	var dayAmo=dateAmo.getTime();
	
	dayAmos=dayAmo+totalAmounts;
	
	//將日前從毫秒轉換回來
	var da = new Date();
	da.setTime(dayAmos);
	
	Months = da.getMonth()+1;
	dates = da.getDate();
	
	if(parseInt(Months)<10)
	{
		Months="0"+Months;
	}
	
	//小於10的日期轉換 例01 02 03
	if(parseInt(dates)<10)
	{
		
		dates="0"+dates;
	}	
	//用迴圈跑寫入資料庫
	numd.innerHTML=numd.innerHTML+'<input type="hidden" name="numdays" value="'+da.getFullYear()+"-"+Months+"-"+dates+' '+totalAmount.getHours()+':'+totalAmount.getMinutes()+':'+totalAmount.getSeconds()+'0'+'" />';
	}// 	start_date.value=totalAmount.get;
//測試


}

function timestamps() {
	end_date = document.getElementById("end_date");
	start_date =document.getElementById("start_date");
	datetime =document.getElementById("datetime");
	days =document.getElementById("days");
	//放入日期 日期時間轉換		
	var totalAmount = new Date(start_date.value);
	
	//轉成毫秒
	var totalAmounts =totalAmount.getTime();
	
	//將日期轉成數字做 +法
	var timers=parseInt(days.value)+1;
	
	//宣告一個1970年的格式 ，為了計算目前日期+上天數，這段程式碼天數轉換
 	var dateAmount = new Date(1970,0,timers);
	
 	//將1970年格式轉成毫秒
	var day3=dateAmount.getTime();
 	
	//將使用者入日期+天數 (用毫秒相加)
	day3=day3+totalAmounts;

	//將日前從毫秒轉換回來
	var d = new Date();
	d.setTime(day3);

	//月份 日期 轉換
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

	//將日期月份轉換完顯示在畫面上
	end_date.value=d.getFullYear()+"-"+Month+"-"+date;
	
	//測試
	numd =document.getElementById("numd");
	numd.innerHTML='';
	for(a=2;a<=parseInt(days.value)+1;a++){
		
	//宣告一個1970年的格式 ，為了計算目前日期+上天數，這段程式碼天數轉換	
	var dateAmo = new Date(1970,0,a);
	
	//將1970年格式轉成毫秒
	var dayAmo=dateAmo.getTime();
	
	dayAmos=dayAmo+totalAmounts;
	
	//將日前從毫秒轉換回來
	var da = new Date();
	da.setTime(dayAmos);
	
	Months = da.getMonth()+1;
	dates = da.getDate();
	
	if(parseInt(Months)<10)
	{
		Months="0"+Months;
	}
	
	//小於10的日期轉換 例01 02 03
	if(parseInt(dates)<10)
	{
		
		dates="0"+dates;
	}	
	//用迴圈跑寫入資料庫
	numd.innerHTML=numd.innerHTML+'<input type="hidden" name="numdays" value="'+da.getFullYear()+"-"+Months+"-"+dates+' '+totalAmount.getHours()+':'+totalAmount.getMinutes()+':'+totalAmount.getSeconds()+'0'+'" />';
	}
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
		if (number == 6) {
			$('#days').show();
			$('#end_date').show();
			$('#f_date1').hide();
			$('#enddate').show();
			$('#datetime').show();
			brs.innerHTML="~";
		} else {
			$('#days').hide();
			$('#end_date').hide();
			$('#f_date1').show();
			$('#enddate').hide();
			$('#datetime').hide();
			brs.innerHTML="";
	
		}
	}
// 	function groupif(number) {
// 		days = document.getElementById("days");
// 		brs = document.getElementById("brs");
// 		enddate = document.getElementById("end_date");
// 		fdate1 = document.getElementById("f_date1");
// 		enddates = document.getElementById("enddate");
// 		datetime =	document.getElementById("datetime");
// 		if (number == 6) {
// 			$('#days').hide();
// 			$('#days').show();
// 			days.style="";
// 			brs.innerHTML="~";
// 			enddate.style = "";
// 			fdate1.style = "display:none";
// 			enddates.style = "";
// 			datetime.style="";
// 		} else {
// 			days.style="display:none";
// 			brs.innerHTML="";
// 			enddate.style = "display:none";
// 			fdate1.style = "";
// 			enddates.style = "display:none";
// 			datetime.style="display:none";
// 		}
// 	}
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