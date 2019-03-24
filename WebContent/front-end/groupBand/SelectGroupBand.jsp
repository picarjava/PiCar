<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.groupBand.model.*"%>
    <%@ page import="java.util.*"%>
     <%@ page import="com.member.model.*"%>
     <% String groupKind[] = {"揪團","長期揪團"}; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>SelectGroupBand.jsp</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.0/css/all.css" integrity="sha384-Mmxa0mLqhmOeaE8vgOSbKacftZcsNYDjQzuCOm6D02luYSzBG8vpaOykv9lFQ51Y" crossorigin="anonymous">
<meta charset="utf-8">
</head>

<style>
.Gridline{
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
 #messageboards{
 	margin-top: 40px;
 }   
    
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin:auto;
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
  #topright{
    position: absolute;
    right: 0px;
  
  }
  .text30{
      width: 10%;
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
.pagination{


}
.buttonS{
    font-family: "Poppins", sans-serif;
    border-radius: 50px;
    padding: 5px 22px;
    background: #f5f5f5;
    color: #2dc997;
    font-style: italic;
    text-decoration: none;
    moz-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    webkit-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    border: 2px solid #999999;
    0 1px 25px 0 rgba(0,0,0,.05) inset,: ;
    0 -1px 25px 0 rgba(0,0,0,.05) inset: ;
    webkit-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
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
    moz-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    webkit-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    border: 2px solid #29b387;
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    webkit-box-shadow:
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow:
        0 1px 25px 0 rgba(0,0,0,.05) inset,
        0 -1px 25px 0 rgba(0,0,0,.05) inset;
	display: inline;
    margin-right: 20px;
}
.snack{
	margin-top: 20px;
}
</style>
<body bgcolor='white'>
<jsp:include page="/front-end/HomeMember/HeadMember.jsp" />
<jsp:include page="/front-end/HomeMember/HeadMemberGroup.jsp" />

<div id="Floatingwindow" style="display:none">
<button type="button" onclick="Fork()" id="topright" class="" ><i class="fas fa-times fa-2x"></i></button>
<div><h1>檢舉內容</h1></div>
<div id="messageboards">檢舉原因</div>
<div id="noteExection"></div>
<div><textarea name="note" required id="note" class="form-control" rows="3" cols="50" style=" width: 400px;height: 150px;"></textarea></div>
<div><button type="button" onclick="floatingwindow()" class="" >送出</button></div>
</div>


<h3>揪團資料查詢:</h3>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  
  <li>
  
    <FORM METHOD="post" ACTION="<%=request.getServletContext().getContextPath()%>/GroupBand"  enctype="multipart/form-data">
     	<div class="radio">
        	<input type="radio"  id="radio-1" name="GROUP_KIND" value="5" checked
					onclick="groupif(this.value)">
			 <label for="radio-1" class="radio-label">揪團</label>
		 </div>
		 <div class="radio">
				<input id="radio-2" type="radio" name="GROUP_KIND" value="6"
					onclick="groupif(this.value)">
				<label  for="radio-2" class="radio-label">長期揪團</label>
        
         </div>
         <br>
         <div></div>
        <b>團名</b>
         <input class="text30" type="TEXT" name="GROUP_NAME"><br><br>
         <b>上車地點</b>
         	  <select name="START_LOC" class="text30">
         	  <option value="">請選擇</option>
			<option value="臺北">臺北市</option>
			<option value="新北">新北市</option>
			<option value="桃園">桃園市</option>
			<option value="基隆">基隆市</option>
			<option value="宜蘭">宜蘭市</option>
			<option value="新竹">新竹市</option>
			<option value="臺中">臺中市</option>
			<option value="彰化">彰化市</option>
			<option value="臺南">臺南市</option>
			<option value="嘉義市">嘉義市</option>
			<option value="高雄">高雄市</option>
			<option value="新竹">新竹縣</option>
			<option value="苗栗">苗栗縣</option>
			<option value="彰化">彰化縣</option>
			<option value="南投">南投縣</option>
			<option value="雲林">雲林縣</option>
			<option value="嘉義縣">嘉義縣</option>
			<option value="屏東">屏東縣</option>
			<option value="屏東">宜蘭縣</option>
			<option value="臺東">臺東縣</option>
			<option value="澎湖">澎湖縣</option>
			</select><br><br>
			<b>上車日期</b>
			<input class="text30" name="START_TIME" id="start_date" type="text"
					 size="15">
					 <br><br>
        <b>揪團類別</b>
        <select name="GROUP_TYPE" class="text30">
         <option value="">請選擇</option>
			<option value="演唱會">演唱會</option>
			<option value="旅遊">旅遊</option>
			<option value="美食">美食</option>
			<option value="運動團">運動團</option>
			<option value="展覽">展覽</option>
			<option value="遊樂園">遊樂園</option>
			</select><br><br>
       
 
        <input type="hidden" name="action" value="listgroupBand_ByCompositeQuery">
        <input type="submit" value="送出">
        <input class="buttonS" type="reset" value="清除" />
    </FORM>
  </li>
</ul>

<div class="Gridline"></div>

<%if(request.getAttribute("listgroupBand_ByCompositeQuery")!=null){%>
<jsp:include page="listgroupBand_ByCompositeQuery.jsp" />
<%}
else{
%>
<%GroupBandService groupBandService =new GroupBandService();
List<GroupBandVO> list = new ArrayList<GroupBandVO>();
list = groupBandService.getAllStartTime();
out.println("<h6>目前日期最近的五筆揪團</h6>");
 int i = 0;
 %>
 <script>
 var colors = [];
 </script>
 <%
for(GroupBandVO lists :list){
	%>
	
	<table>
	<tr>
<th>發起時間</th><th><%=lists.getLaunchTime()%></th>
<th>簡介</th><th><%=lists.getIntroduction()%></th>
<th>現在人數</th><th><%=lists.getCurrenTnum()%></th>
<th>上限人數</th><th><%=lists.getUpperLimit()%></th>
	</tr>
	<tr>
<th>下限人數</th><th><%=lists.getLowerLimit()%></th>
<th>團名</th><th><%=lists.getGroupName()%></th>

<c:set var="id" scope="page" value="<%=lists.getGroupLeader()%>" />
			<%
	String id = (String) pageContext.getAttribute("id");
	MemberService memberService = new MemberService();
	MemberVO memberVOS =  memberService.getOneMember(id);
	pageContext.setAttribute("memberVOS", memberVOS);
%>

<th>團長</th><th>${memberVOS.name}</th>
<th>上車地點</th><th><%=lists.getStartLoc()%></th>
</tr>
	<tr>
<th>下車地點</th><th><%=lists.getEndLoc()%></th>
<th>照片</th><th><img src="/PiCar/GroupBand?groupID=<%=lists.getGroupID()%>" width="100px"   height="100px"></th>
<th>揪團類別</th><th><%=lists.getGroupType()%></th>
<th>上車時間</th><th><%=lists.getStartTime()%></th>
</tr>
	<tr>
<th>揪團種類</th>
		 <th><%=groupKind[lists.getGroupKind()-5]%></th>
		
<th>
<FORM METHOD="post" ACTION="<%=request.getServletContext().getContextPath()%>/GroupBand" enctype="multipart/form-data" style="margin-bottom: 0px;">
	<input type="submit" value="進入揪團">
	<input type="hidden" name="groupLeader"  value="<%=lists.getGroupLeader()%>">
	<input type="hidden" name="startTime"  value="<%=lists.getStartTime()%>">
	<input type="hidden" name="groupID"  value="<%=lists.getGroupID()%>">
	<input type="hidden" name="memIDs" value="${memberVO.memID}" /> 
	<input type="hidden" name="action"	value="GroupJoin"></FORM>
</th>
	<th>
	<button type="button" value="<%=lists.getGroupID()%>" id="<%=lists.getGroupID()%>" onclick="floatingwindowshow('<%=lists.getGroupID()%>','${memberVO.memID}')" class="btn btn-lg btn-danger" ><i class="fas fa-exclamation-triangle"></i></button>
</th>
</tr>	
</table>
 <script>

colors[<%=i%>] = document.getElementById("<%=lists.getGroupID()%>");
colors[<%=i%>].value="<%=lists.getGroupID()%>";
<%i++;%>
</script>

	<%
}
}%>
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
<ul class="pagination">
    <li><a href="#">&laquo;</a></li>
    <li><a href="#">1</a></li>
    <li><a href="#">2</a></li>
    <li><a href="#">3</a></li>
    <li><a href="#">4</a></li>
    <li><a href="#">5</a></li>
    <li><a href="#">&raquo;</a></li>
</ul>


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