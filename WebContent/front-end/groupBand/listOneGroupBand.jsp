<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.groupBand.model.*"%>
    <%@ page import="java.util.*"%>
    <%@ page import="com.member.model.*"%>
    <%
    
    GroupBandVO groupBandVO =(GroupBandVO) request.getAttribute("GroupBandVO");
    Object GroupLeader =request.getAttribute("GroupLeader");  
    List<MemberVO> testList =(List)session.getAttribute("testList"); 
    boolean dropOut =(boolean)request.getAttribute("dropOut");
%>

    
<!DOCTYPE html>

<html>
<head>
<link href="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/img/favicon.png" rel="icon">
<link href="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<!-- <link -->
<!-- 	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700" -->
<!-- 	rel="stylesheet"> -->

<!-- Bootstrap CSS File -->
<link href="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Libraries CSS Files -->
<link href="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/lib/animate/animate.min.css" rel="stylesheet">

<!-- Main Stylesheet File -->
<!-- <link href="css/style.css" rel="stylesheet"> -->
<!-- <link rel="stylesheet" type="text/css" -->
<%-- 	href="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/datetimepicker/jquery.datetimepicker.css" /> --%>
<%-- <script src="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/datetimepicker/jquery.js"></script> --%>
<%-- <script src="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/datetimepicker/jquery.datetimepicker.full.js"></script> --%>
   
<meta charset="UTF-8">
<title>listOneGroupBand.jsp</title>
<style>
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

  .p-2 {
   margin: auto;
      width: 200px;
    height: 300px;
  }
  .marrgin{
     width: 150px;
    height: 200px;
    color: #fff;
    background-color: #fff;
    margin: auto;

  }
  .tentcenter{
      text-align: center;
       font-family: Microsoft JhengHei;
        font-weight: bold;
      margin-top: 25px;
  }
  .mar{
      margin-left: 5px;
  }
  .imgss{
      width: 150px;
    height: 200px;
  }
  .color1{
      background-color: #008ccc!important;
  }
    .color0{
      background-color: #FFB7DD!important;
  }
textarea {
    overflow: auto;
    resize: vertical;
    width: 500px;
    height: 300px;
}
  
</style>
<style>

  table {
   margin: auto;
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  th{
  background-color: #00BBFF;
  }
</style>
</head>
<body  onload="connect();" onunload="disconnect();">

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
				
			</c:forEach>
		</ul>
	</c:if>
	
<div class="row">
<c:forEach var="MemberVO" items="${testList}" >
<div class="d-inline p-2 bg-primary text-white color${MemberVO.gender}">
<div class="marrgin"><img class="imgss"  src="/PiCar/GroupBand?MEM_ID=${MemberVO.memID}"></div>
<div class="tentcenter" id="gender"><h4 >${MemberVO.name}<img class="mar" src="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/img/${MemberVO.gender}.png"  width="20px"   height="25px" ></h4></div>
<div class="tentce" id="${MemberVO.memID}"></div>
</div>
<script>
var MyPoint = "/webSocket/${MemberVO.memID}/309";
var ${MemberVO.memID} = document.getElementById("${MemberVO.memID}");

${MemberVO.memID}.innerHTML="${MemberVO.memID}";

if(${MemberVO.memID}.innerHTML=="${memberVO.memID}"){
	//jsp+javascript
// 	取得木遣使用者跟揪團裡的人比對
	${MemberVO.memID}.innerHTML="${MemberVO.memID}";
	
	
}

</script>
</c:forEach>
</div>
<table>
<tr>
<th>揪團ID</th>
<th>留言板內容</th>
<th>發起時間</th>
<th>簡介</th>
<th>揪團種類</th>
<th>現在人數</th>
<th>上限人數</th>
<th>下限人數</th>
<th>團名</th>
<th>團長</th>
<th>上車地點</th>
<th>下車地點</th>
<th>隱私設定</th>
<th>照片</th>
<th>揪團類別</th>
<th>總金額	</th>
<th>上車時間</th>
<th>評價分數</th>
<th>備註</th>


</tr>


<tr>
	<td><%=groupBandVO.getGroupID()%></td>
	<td><%=groupBandVO.getContent()%></td>
	<td><%=groupBandVO.getLaunchTime()%></td>
	<td><%=groupBandVO.getIntroduction()%></td>
	<td><%=groupBandVO.getGroupKind()%></td>
	<td><%=groupBandVO.getCurrenTnum()%></td>
	<td><%=groupBandVO.getUpperLimit()%></td>
	<td><%=groupBandVO.getLowerLimit()%></td>
	<td><%=groupBandVO.getGroupName()%></td>
	<td><%=groupBandVO.getGroupLeader()%></td>
	<td><%=groupBandVO.getStartLoc()%></td>
	<td><%=groupBandVO.getEndLoc()%></td>
	<td><%=groupBandVO.getPrivates()%></td>
	<td><img src="/PiCar/GroupBand?groupID=<%=groupBandVO.getGroupID()%>" width="100px"   height="100px"></td>
	<td><%=groupBandVO.getGroupType()%></td>
	<td><%=groupBandVO.getTotalAmout()%></td>
	<td><%=groupBandVO.getStartTime()%></td>
	<td><%=groupBandVO.getRate()%></td>
	<td><%=groupBandVO.getNote()%></td>

</tr>
</table>
<%--判斷是否為團長 GroupBandServlet.java配合上689行--%>
<%if("true".equals(GroupLeader)){ %>
<h5>修改資料</h5>
\
<form action="<%=request.getServletContext().getContextPath()%>/GroupBand" method="POST" enctype="multipart/form-data"style="margin-bottom: 0px;">
	<input type="submit" value="修改">
	<input type="hidden" name="groupID"  value="${GroupBandVO.groupID}">
	<input type="hidden" name="action"	value="getOne_For_Update">
</FORM>

	<%}%>
<%if(false==dropOut){%>
<h5>加入揪團</h5>
<FORM METHOD="post" ACTION="<%=request.getServletContext().getContextPath()%>/GroupBand" enctype="multipart/form-data" style="margin-bottom: 0px;">
	<input type="submit" value="加入">
	
	<input type="hidden" name="groupLeader"  value="<%=groupBandVO.getGroupLeader()%>">
	<input type="hidden" name="groupID"  value="${GroupBandVO.groupID}">
	<input type="hidden" name="startTime"  value="<%=groupBandVO.getStartTime()%>">
	<input type="hidden" name="memIDs" value="${memberVO.memID }" /> 
	<input type="hidden" name="groupKind"  value="<%=groupBandVO.getGroupKind()%>">
	<input type="hidden" name="action"	value="GroupAdd">
	
	</FORM>	
<%}else{%>
<FORM METHOD="post" ACTION="<%=request.getServletContext().getContextPath()%>/GroupBand" enctype="multipart/form-data" style="margin-bottom: 0px;">
	<input type="submit" value="退出">
	
	<input type="hidden" name="groupLeader"  value="<%=groupBandVO.getGroupLeader()%>">
	<input type="hidden" name="groupID"  value="${GroupBandVO.groupID}">
	<input type="hidden" name="startTime"  value="<%=groupBandVO.getStartTime()%>">
	<input type="hidden" name="memIDs" value="${memberVO.memID }" /> 
	<input type="hidden" name="groupKind"  value="<%=groupBandVO.getGroupKind()%>">
	<input type="hidden" name="action"	value="GroupAdd">
	<input type="hidden" name="dropOutbutton"	value="dropOutbutton">
	</FORM>	
<%}%>




    
    
        <h1> Chat Room </h1>
	    <h3 id="statusOutput" class="statusOutput"></h3>
        <textarea id="messagesArea" class="panel message-area text-right" readonly ></textarea>
        
        <div id="messa" ></div>
        
        <div id="but"></div>
        <div class="panel input-area">
        	
            <input id="userName" class="text-field" type="text" placeholder="使用者名稱" value="${memberVO.name}"disabled="disabled"/>
            <input id="message"  class="text-field" type="text" placeholder="訊息" onkeydown="if (event.keyCode == 13) sendMessage();"/>
            <input type="submit" id="sendMessage" class="button" value="送出" onclick="sendMessage();"/>
		    <input type="button" id="connect"     class="button" value="連線" onclick="connect();"/>
		    <input type="button" id="disconnect"  class="button" value="離線" onclick="disconnect();"/>
	    </div>
 
    
<script>
var btn = document.createElement("BUTTON");//放甚麼就創甚麼
   
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
    
	var statusOutput = document.getElementById("statusOutput");
	var webSocket;
	
	function connect() {
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
			updateStatus("${memberVO.name} 成功連線");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
	        var jsonObj = {"userName" : "${memberVO.name}", "message" : "以連線","scanners" : "scannerq","userID" : "${memberVO.memID}"};
	        webSocket.send(JSON.stringify(jsonObj));
		};

		webSocket.onmessage = function(event) {
			
			var messa = document.getElementById("messa");
			
			var messagesArea = document.getElementById("messagesArea");
	        var jsonObj = JSON.parse(event.data);
	        var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
	      
	        //判斷是誰的連線
	        if(jsonObj.scanners=="scannerq"){
	        	 var newDiv = document.createElement("div");
	        	 newDiv.id=jsonObj.userID;
	        	 newDiv.innerHTML=jsonObj.userName+"  :已連線";
	        	 messa.appendChild(newDiv);
	        	 DZ
	        }else{
	        
	        messagesArea.value = messagesArea.value + message;
	        
	        messagesArea.scrollTop = messagesArea.scrollHeight;
	        }
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket 已離線");
		};
	}
	
	
	var inputUserName = document.getElementById("userName");
	inputUserName.focus();
	
	function sendMessage() {
	    var userName = inputUserName.value.trim();
	    if (userName === ""){
	        alert ("使用者名稱請勿空白!");
	        inputUserName.focus();	
			return;
	    }
	    
	    var inputMessage = document.getElementById("message");
	    var message = inputMessage.value.trim();
	    
	    if (message === ""){
	        alert ("訊息請勿空白!");
	        inputMessage.focus();	
	    }else{
	        var jsonObj = {"userName" : userName, "message" : message};
	        webSocket.send(JSON.stringify(jsonObj));
	        inputMessage.value = "";
	        inputMessage.focus();
	    }
	}

	
	function disconnect () {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}

	
	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}
    
</script>




</body>
</html>