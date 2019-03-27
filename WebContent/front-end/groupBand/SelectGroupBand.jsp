<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.groupBand.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%
	String groupKind[] = {"揪團", "長期揪團"};
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>SelectGroupBand.jsp</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script
	src="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.0/css/all.css"
	integrity="sha384-Mmxa0mLqhmOeaE8vgOSbKacftZcsNYDjQzuCOm6D02luYSzBG8vpaOykv9lFQ51Y"
	crossorigin="anonymous">
<meta charset="utf-8">
</head>

<style>
.Gridline {
	margin: auto;
	width: 80%;
	background: #e6e6e6;
	height: 5px;
	margin-bottom: 30px;
	margin-top: 20px;
}

table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
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

#Floatingwindow {
	position: fixed;
	background-color: #555;
	width: 500px;
	height: 320px;
	z-index: 995;
	left: 40%;
	top: 30%;
}

#messageboards {
	margin-top: 40px;
}

.iconsw {
	
}

.iconsw:hover {
	filter: hue-rotate(70deg);
}
</style>

<style>
table {
	width: 800px;
	background-color: white;
	margin: auto;
	margin-top: 15px;
	margin-bottom: 15px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	height: 50px;
	width: 50px;
	padding: 5px;
	text-align: center;
}

#topright {
	position: absolute;
	right: 0px;
}

.text30 {
	width: 10%;
}

.radio {
	display: inline;
	margin: 0.5rem;
}

.radio input[type="radio"] {
	position: absolute;
	opacity: 0;
}

.radio input[type="radio"]+.radio-label:before {
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

.radio input[type="radio"]:checked+.radio-label:before {
	background-color: #2dc997;
	box-shadow: inset 0 0 0 4px #f4f4f4;
}

.radio input[type="radio"]:focus+.radio-label:before {
	outline: none;
	border-color: #2dc997;
}

.radio input[type="radio"]:disabled+.radio-label:before {
	box-shadow: inset 0 0 0 4px #f4f4f4;
	border-color: #b4b4b4;
	background: #b4b4b4;
}

.radio input[type="radio"]+.radio-label:empty:before {
	margin-right: 0;
}

.pagination {
	
}

.buttonS {
	font-family: "Poppins", sans-serif;
	border-radius: 50px;
	padding: 5px 22px;
	background: #f5f5f5;
	color: #333333;
	font-style: italic;
	text-decoration: none;
	moz-box-shadow: 0 1px 25px 0 rgba(0, 0, 0, .05) inset, 0 -1px 25px 0
		rgba(0, 0, 0, .05) inset;
	webkit-box-shadow: 0 1px 25px 0 rgba(0, 0, 0, .05) inset, 0 -1px 25px 0
		rgba(0, 0, 0, .05) inset;
	box-shadow: 0 1px 25px 0 rgba(0, 0, 0, .05) inset, 0 -1px 25px 0
		rgba(0, 0, 0, .05) inset;
	border: 2px solid #999999; 0 1 px 25px 0 rgba(0,0,0,.05) inset, :; 0 -1
	px 25px 0 rgba(0,0,0,.05) inset :;
	webkit-box-shadow: 0 1px 25px 0 rgba(0, 0, 0, .05) inset, 0 -1px 25px 0
		rgba(0, 0, 0, .05) inset;
	box-shadow: 0 1px 25px 0 rgba(0, 0, 0, .05) inset, 0 -1px 25px 0
		rgba(0, 0, 0, .05) inset;
	display: inline;
	margin-right: 20px;
}

.buttonS:hover {
	font-family: "Poppins", sans-serif;
	border-radius: 50px;
	padding: 5px 22px;
	background: #ddd;
	color: #1f7157;
	font-style: italic;
	text-decoration: none;
	moz-box-shadow: 0 1px 25px 0 rgba(0, 0, 0, .05) inset, 0 -1px 25px 0
		rgba(0, 0, 0, .05) inset;
	webkit-box-shadow: 0 1px 25px 0 rgba(0, 0, 0, .05) inset, 0 -1px 25px 0
		rgba(0, 0, 0, .05) inset;
	box-shadow: 0 1px 25px 0 rgba(0, 0, 0, .05) inset, 0 -1px 25px 0
		rgba(0, 0, 0, .05) inset;
	border: 2px solid #29b387; 0 1 px 25px 0 rgba(0,0,0,.05) inset, 0 -1px
	25px 0 rgba(0,0,0,.05) inset;
	webkit-box-shadow: 0 1px 25px 0 rgba(0, 0, 0, .05) inset, 0 -1px 25px 0
		rgba(0, 0, 0, .05) inset;
	box-shadow: 0 1px 25px 0 rgba(0, 0, 0, .05) inset, 0 -1px 25px 0
		rgba(0, 0, 0, .05) inset;
	display: inline;
	margin-right: 20px;
}

.snack {
	margin-top: 20px;
}

.btsts {
	background-color: #5ec146;
	background-image: linear-gradient(to bottom, #3cd422, #709c1a);
	display: inline-block;
	padding: 4px 12px;
	margin-bottom: 0;
	font-size: 14px;
	line-height: 20px;
	text-align: center;
	vertical-align: middle;
	cursor: pointer;
	color: #ffffff;
	text-shadow: 0 1px 1px rgba(255, 255, 255, 0.75);
	border: 1px solid #22a03e;
	border-bottom-color: #a2a2a2;
	border-radius: 4px;
	box-shadow: inset 0 1px 0 rgba(255, 255, 255, .2), 0 1px 2px
		rgba(0, 0, 0, .05);
	width: 60px;
	height: 35px;
}

.btsts:hover {
	background-image: linear-gradient(to bottom, #40b92c, #709c1a);
	width: 60px;
	height: 35px;
}

.padding {
	display: inline-block;
	color: #333;
	width: 60%;
	background-color: #ccc;
	padding: 10px;
	text-align: -webkit-left;
	border-radius: 10px;
	margin-left: 10px;
	margin-right: 20px;
	box-shadow:4px 4px 3px rgba(20%,20%,40%,0.5);
}

.paddingTime {
	display: inline-block;
	color: #333;
	background-color: #ccc;
	padding: 10px;
	text-align: -webkit-left;
	border-radius: 10px;
	margin-left: 10px;
	box-shadow:4px 4px 3px rgba(20%,20%,40%,0.5);
}

.paddinglocation {
	display: inline-block;
	color: #333;
	background-color: #ccc;
	padding: 10px;
	text-align: -webkit-left;
	border-radius: 10px;
	margin-left: 10px;
	margin-top: 30px;
	width: 88%;
	box-shadow:4px 4px 3px rgba(20%,20%,40%,0.5);
}

.paddingtent {
	height: 80px;
	display: inline-block;
	color: #333;
	background-color: #ccc;
	padding: 10px;
	text-align: -webkit-left;
	border-radius: 10px;
	margin-left: 10px;
	margin-top: 30px;
	width: 91%;
	box-shadow:4px 4px 3px rgba(20%,20%,40%,0.5);
}

.boderRi {
	border-radius: 30px;
	margin-bottom: 10px;
	box-shadow:4px 4px 3px rgba(20%,20%,40%,0.5);
	    filter: brightness(0.8);
}	
.boderRi:hover {
	filter: brightness(1);
}
.floatSquare{


}
.floatSqu{
    margin-top: -50px;
        position: absolute;
            right: 40%;
}
</style>
<body bgcolor='white'>
	<jsp:include page="/front-end/HomeMember/HeadMember.jsp" />
	<jsp:include page="/front-end/HomeMember/HeadMemberGroup.jsp" />

	<div id="Floatingwindow" style="display: none">
		<button type="button" onclick="Fork()" id="topright" class="">
			<i class="fas fa-times fa-2x"></i>
		</button>
		<div>
			<h1>檢舉內容</h1>
		</div>
		<div id="messageboards">檢舉原因</div>
		<div id="noteExection"></div>
		<div>
			<textarea name="note" required id="note" class="form-control"
				rows="3" cols="50" style="width: 400px; height: 150px;"></textarea>
		</div>
		<div>
			<button type="button" onclick="floatingwindow()" class="">送出</button>
		</div>
	</div>


	<h3>揪團資料查詢:</h3>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>

		<li>

			<FORM METHOD="post"
				ACTION="<%=request.getServletContext().getContextPath()%>/GroupBand"
				enctype="multipart/form-data">
				<div class="radio">
					<input type="radio" id="radio-1" name="GROUP_KIND" value="5"
						checked> <label
						for="radio-1" class="radio-label">揪團</label>
				</div>
				<div class="radio">
					<input id="radio-2" type="radio" name="GROUP_KIND" value="6"
						> <label for="radio-2"
						class="radio-label">長期揪團</label>

				</div>

				<div style="margin-top: 20px;"></div>
				<b>團名</b> <input class="text30" type="TEXT" name="GROUP_NAME"><br>
				<br> <b>上車地點</b> <select name="START_LOC" class="text30">
					<option value="">請選擇</option>
					<option value="台北">台北市</option>
					<option value="新北">新北市</option>
					<option value="桃園">桃園市</option>
					<option value="基隆">基隆市</option>
					<option value="宜蘭市">宜蘭市</option>
					<option value="新竹">新竹市</option>
					<option value="台中">台中市</option>
					<option value="彰化">彰化市</option>
					<option value="台南">台南市</option>
					<option value="嘉義市">嘉義市</option>
					<option value="高雄">高雄市</option>
					<option value="新竹">新竹縣</option>
					<option value="苗栗">苗栗縣</option>
					<option value="彰化">彰化縣</option>
					<option value="南投">南投縣</option>
					<option value="雲林">雲林縣</option>
					<option value="嘉義縣">嘉義縣</option>
					<option value="屏東">屏東縣</option>
					<option value="宜蘭縣">宜蘭縣</option>
					<option value="台東">台東縣</option>
					<option value="澎湖">澎湖縣</option>
				</select><br> <br> <b>上車日期</b> <input class="text30"
					name="START_TIME" id="start_date" type="text" size="15"> <br>
				<br> <b>揪團類別</b> <select name="GROUP_TYPE" class="text30">
					<option value="">請選擇</option>
					<option value="演唱會">演唱會</option>
					<option value="旅遊">旅遊</option>
					<option value="美食">美食</option>
					<option value="運動團">運動團</option>
					<option value="展覽">展覽</option>
					<option value="遊樂園">遊樂園</option>
				</select><br> <br> <input type="hidden" name="action"
					value="listgroupBand_ByCompositeQuery"> <input
					class="buttonS" type="submit" value="送出"> <input
					class="buttonS" type="reset" value="清除" />
			</FORM>
		</li>
	</ul>

	<div class="Gridline"></div>

	<%
		if (request.getAttribute("listgroupBand_ByCompositeQuery") != null) {
	%>
	<jsp:include page="listgroupBand_ByCompositeQuery.jsp" />
	<%
		} else {
	%>
	<%
		GroupBandService groupBandService = new GroupBandService();
			List<GroupBandVO> list = new ArrayList<GroupBandVO>();
			list = groupBandService.getAllStartTime();
			out.println("<h6>目前日期最近的五筆揪團</h6>");
			int i = 0;
	%>
	<script>
 var colors = [];
 </script>
	<%
		for (GroupBandVO lists : list) {
	%>
	<div class="services-half-width container">
		<div class="row">

			<div class="services-half-width-text span9">
				房名:
				<div class="padding"><%=lists.getGroupName()%></div>
				上車時間:
				<div class="paddingTime"><%=lists.getStartTime()%></div>
				<br> 上車地點:
				<div class="paddinglocation"><%=lists.getStartLoc()%></div>
				<br> 下車地點:
				<div class="paddinglocation"><%=lists.getEndLoc()%></div>
				<br> 簡介:
				<div class="paddingtent"><%=lists.getIntroduction()%></div>
				<br>
				<div class="floatSqu">
					<%for(int x=1;x<=lists.getUpperLimit();x++){%>			
			<%if(x<=lists.getCurrenTnum()) {%>	
			
			<img src="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/img/smogRed.png"  class="floatSquare">
			
			<%}else{%>
		
			<img src="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/img/smog.png" class="floatSquare">
			
		<%}}%>
		</div>
			</div>
			<div class="services-half-width-text span3">
				<div>
					<img src="/PiCar/GroupBand?groupID=<%=lists.getGroupID()%>"
						class="boderRi" width="500px" height="500px">
				</div>
				<div style="margin-top: 10px">
					<FORM id="<%=lists.getGroupID()%>" name="<%=lists.getGroupID()%>"
						METHOD="post"
						ACTION="<%=request.getServletContext().getContextPath()%>/GroupBand"
						enctype="multipart/form-data" style="margin-bottom: 0px;">
						<button type="button" name="submit_Btn" id="submit_Btn"
							onClick="document.<%=lists.getGroupID()%>.submit();"
							class="btsts" style="" title="加入揪團">
							<i class="fas fa-user-plus"></i>
						</button>
						<input type="hidden" name="groupLeader"
							value="<%=lists.getGroupLeader()%>"> <input type="hidden"
							name="startTime" value="<%=lists.getStartTime()%>"> <input
							type="hidden" name="groupID" value="<%=lists.getGroupID()%>">
						<input type="hidden" name="memIDs" value="${memberVO.memID}" /> <input
							type="hidden" name="action" value="GroupJoin">
						<button type="button" value="<%=lists.getGroupID()%>"
							id="<%=lists.getGroupID()%>"
							onclick="floatingwindowshow('<%=lists.getGroupID()%>','${memberVO.memID}')"
							class="btn btn-lg btn-danger" style="width: 60px; height: 35px;"
							title="檢舉揪團">
							<i class="fas fa-exclamation-triangle"></i>
						</button>
					</FORM>
				</div>
			</div>
		</div>
		</div>
		<script>

colors[<%=i%>] = document.getElementById("<%=lists.getGroupID()%>");
colors[<%=i%>].value="<%=lists.getGroupID()%>";
<%i++;%>
</script>
		<div class="Gridline"
			style="margin-top: 70px; margin-bottom: 70px; width: 70%; height: 10px"></div>
		<%
			}
			}
		%>
		<script>
var GroupID;
var memID;
var note;
function floatingwindowshow(GroupID,memID){
	document.getElementById("note").value='';
	
	$('#Floatingwindow').show();
	this.GroupID=GroupID;
	this.memID=memID;
	
}

function Fork(){
	$('#Floatingwindow').hide();
}

function floatingwindow(){
	note = document.getElementById("note").value;
	var noteExection = document.getElementById("noteExection");
	var Floatingwindow = document.getElementById("Floatingwindow");
	if(note!=''){
		for(i=0;i<colors.length;i++){
		
			
			if(colors[i].value==GroupID){
				
				colors[i].disabled="disabled";
			}
		}
		noteExection.innerHTML="";
		Floatingwindow.style="height: 320px;";
		groudReport(memID,GroupID,note);
		$('#Floatingwindow').hide();	
				
	}else{
		noteExection.innerHTML="<h3 style='color:#FF3333'>※請輸入錯誤訊息※<h3>";
		Floatingwindow.style="height: 380px;";
	}
	
	
	
}

</script>



		<script>
function groudReport(memID,GroupID,note)
{
	   $.ajax({
		   
// 		   	alert(memID);
// 			alert(GroupID);
 			
	        //告訴程式表單要傳送到哪裡                                         

	        url:"<%=request.getServletContext().getContextPath()%>/front-end/groupBand/AjexGroupReport.jsp",                                                              

	        //需要傳送的資料

	        data:"groupID="+GroupID+"&memID="+memID+"&note="+note,

	         //使用POST方法     

	        type : "POST",                                                                    

	        //接收回傳資料的格式，在這個例子中，只要是接收true就可以了
	        dataType:'json', 

	         //傳送失敗則跳出失敗訊息      

	        error:function(selects){                                                                 
	        	 console.log(selects);
	        //資料傳送失敗後就會執行這個function內的程式，可以在這裡寫入要執行的程式  
					
	      
			
	        
	        
					
	        },

	        //傳送成功則跳出成功訊息

	        success:function(selects){                                                           
	        	 console.log(selects);
	 	        //資料傳送失敗後就會執行這個function內的程式，可以在這裡寫入要執行的程式  
	 				
	        //資料傳送成功後就會執行這個function內的程式，可以在這裡寫入要執行的程式  
	  		

	        }

	    }); 
	
	}
</script>



		<script>
$('#start_date').datetimepicker(
		{
			format : 'Y-m-d',
			onShow : function() {			
			},
			step: 5,
			timepicker : false,
					value :'',
			minDate:           '+1970-01-05', // 去除今日(不含)之前
			maxDate:           '+1970-01-20'  // 去除今日(不含)之後
		});
</script>
</body>
</html>