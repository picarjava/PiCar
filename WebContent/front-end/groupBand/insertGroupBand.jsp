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

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700"
	rel="stylesheet">

<!-- Bootstrap CSS File -->
<link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Libraries CSS Files -->
<link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="lib/animate/animate.min.css" rel="stylesheet">

<!-- Main Stylesheet File -->
<link href="css/style.css" rel="stylesheet">
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
         width: 850px;
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
</style>

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
<body bgcolor="#aaaaaa">

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
	<h3>發起揪團 ${memberVO.memID }</h3>

	<form action="<%=request.getServletContext().getContextPath()%>/GroupBand" method="POST" enctype="multipart/form-data">
		<table border="1" class="table">

			<tr>
				<td><input type="radio" name="groupKind" value="5" checked
					onclick="groupif(this.value)">揪團</td>
				<td><input type="radio" name="groupKind" value="6"
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

				<td>
<!-- 				<select name="startLoc"> -->
<!-- 						<option value="桃園火車站">桃園市</option> -->
<!-- 						<option value="台北市火車站">台北市</option> -->
<!-- 				</select> -->
<!-- 加入google map -->

					<div class="form-row">
		       			<div class="col">
						<p id="distance"></p>
						</div>
						<div class="col">
		       			<p id="duration"></p>
		       
		       			</div>
	       			</div>
				    <div class="form-row">
                      <div class="col">
                        <p>上車地點/下車地點</p> 
                        <input id="origin-input" type="text" name="startLoc" value="${groupBand.startLoc}" class="form-control" placeholder="請輸入上車地點">
                      </div>
                      <div class="col">
                        <input id="destination-input" type="text" name="endLoc" value="${groupBand.endLoc}" class="form-control" placeholder="請輸入下車地點">
                      </div>
                      <div  id="map" class="col12">
                     </div>
                    </div>
				</td>
			</tr>

<!-- 			<tr> -->
<!-- 				<td>下車地點:</td> -->

<!-- 				<td><select name="endLoc"> -->
<!-- 						<option value="桃園火車站">桃園市</option> -->
<!-- 						<option value="台北市火車站">台北市</option> -->
<!-- 				</select></td> -->
<!-- 			</tr> -->

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

				<td>
				<button type="button" id="buttons" onchange="buttones();">請點選輸入日期</button>
				<select id="days" name="days" style="display: none" onchange="timestamps();"></select>
				<nobr>時間:</nobr> 
				<nobr id="datetime" >00:00</nobr> 
				<nobr>   日期:</nobr> 
				<input name="startTime" id="start_date" type="text"
					 onchange="datestamps();" readonly="readonly" size="8">
				<nobr id="brs"></nobr>  
						
<input name="endTime" id="end_date" type="text"
					style="display: none" readonly="readonly" size="8">
					<nobr id="numd"></nobr>  
				
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
				<td><input type="hidden" name="memIDs" value="${memberVO.memID }" /> 
				<input type="hidden" name="action" value="insert" /> <input
					type="submit" value="發起揪團" /></td>
				<td><input type="reset" value="清除揪團" /></td>

			</tr>
		</table>
		
		
		 <!-- google map 放隱藏的標籤，讓Controller抓到緯經度參數進行操作*/ -->
                <input type="hidden" id="startLng" name="startLng" value="">
                <input type="hidden" id="startLat" name="startLat" value="">
                <input type="hidden" id="endLng" name="endLng" value="">
                <input type="hidden" id="endLat" name="endLat" value="">
				<input type="hidden" id="distancees" name="distancees" value="">	
	</form>
</body>
<!-- google map auto place complete 開始 -->
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
					value : '<%=startTime%>',
			minDate:           '+1970-01-03', // 去除今日(不含)之前
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
	
// 	start_date.value=totalAmount.get;
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
	for(a=1;a<=parseInt(days.value)+1;a++){
		
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
		days = document.getElementById("days");
		brs = document.getElementById("brs");
		enddate = document.getElementById("end_date");
		fdate1 = document.getElementById("f_date1");
		enddates = document.getElementById("enddate");
		datetime =	document.getElementById("datetime");
		if (number == 6) {
			days.style="";
			brs.innerHTML="~";
			enddate.style = "";
			fdate1.style = "display:none";
			enddates.style = "";
			datetime.style="";
		} else {
			days.style="display:none";
			brs.innerHTML="";
			enddate.style = "display:none";
			fdate1.style = "";
			enddates.style = "display:none";
			datetime.style="display:none";
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