<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>

 <!--引用SweetAlert2.js-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.all.min.js"></script>
<!-- 登入功能串接 ，將VOmemID指定給 memID-->
<%@ page import="com.admin.model.*"%>
<%
if((AdminVO)session.getAttribute("adminVO")!=null){
	AdminVO adminVO=(AdminVO)session.getAttribute("adminVO");
	String adminID=adminVO.getAdminID();
	session.setAttribute("adminID",adminID);
}
%> 
 
 
<!-- Required meta tags -->
<meta charset="utf-8">
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<!--     Fonts and icons     -->
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
<!-- Material Kit CSS -->
<link href="<%=request.getServletContext().getContextPath()%>/back-end/assets/css/material-dashboard.css" rel="stylesheet" />
<style>
table, tr, td, th {
	background-color: white;
	border: 1px solid #aaa;
	text-align: center;
	padding: 5px;
	text-align: center;
	font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro',
		'Noto Sans CJK SC', monospace;
}

table {
	width: 100%;
}

.col-9 {
	margin-top: -15px;
	margin-left: -55px;
	margin-bottom: 1rem;
}

#error {
	margin-left: 20px;
}

#s3 {
	width: 186px;
}

.footer {
  position: absolute;
  left: 1280px;
  top:600px;
}

#p1 {
	margin-top:12px;
	margin-right:12px;

}

#btn1 {
	background-color:#d3d3d3;
	margin-left:-4%;
	font-size:16px;
}

.modal-body input[type="submit"] 
{
	width: 100%;
    padding:10px 20px; 
    background:#EEAD0E; 
    border:0 none;
    cursor:pointer;
    -webkit-border-radius: 5px;
    border-radius: 5px; 
    font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro', 'Noto Sans CJK SC', monospace;
    font-size: 16px;
    color:#000000;
    position: relative;
	transition: 0.4s;
}

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
	margin:3px;
}



</style>
 <body onload="connect();" onunload="disconnect();">

</body>
<%@ page import="com.admin.model.*"%>
<%
	AdminVO adminVO = (AdminVO) session.getAttribute("adminVO");
%>

			<div class="main-panel">
			<!-- Navbar -->
			<nav
				class="navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top ">
				<div class="container-fluid">
					<div class="navbar-wrapper">
						<a class="navbar-brand" href="<%=request.getServletContext().getContextPath()%>/back-end/backHome.jsp"></a>
					</div>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						aria-controls="navigation-index" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="sr-only">Toggle navigation</span> <span
							class="navbar-toggler-icon icon-bar"></span> <span
							class="navbar-toggler-icon icon-bar"></span> <span
							class="navbar-toggler-icon icon-bar"></span>
					</button>
					<div class="collapse navbar-collapse justify-content-end">
						<ul class="navbar-nav">
<!-- 							<li class="nav-item"><a class="nav-link" href="#pablo"> -->
<!-- 									<i class="material-icons">notifications</i> Notifications -->
<!-- 							</a></li> -->
				              <p id="p1"><font color=black> ${adminVO.adminName} </font>您好   </p>
				                <FORM METHOD="post" ACTION="<%=request.getServletContext().getContextPath()%>/back-end/logoutHandlerBackEnd.do">
									<input type="hidden" name="action" value="logout">
									<input type="submit" name="logout" value="登出">				
								</FORM>
								<input type="submit" data-toggle="modal" data-target="#exampleModal" value="修改個人密碼">
							<!-- your navbar here -->
						</ul>
					</div>
				</div>
			</nav>
			<!-- End Navbar -->
		<!-- Modal -->
	      <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	        <div class="modal-dialog" role="document">
	          <div class="modal-content">
	            <div class="modal-header">
	              <h5 class="modal-title" id="exampleModalLabel">修改密碼</h5>
	              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                <span aria-hidden="true">&times;</span>
	              </button>
	            </div>
	            <div class="modal-body">
	                    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/adminSelf.do" name="form1">
								輸入新密碼<input class="form-control" type="password" id="p2" name="password"><br>
								再次輸入新密碼<input class="form-control" type="password" id="p2" name="password2"><br>
		                          	<input type="hidden" name="adminID" value="<%=adminVO.getAdminID()%>"> 
		                            <input type="hidden" name="adminName" value="<%=adminVO.getAdminName()%>"> 
		                            <input type="hidden" name="email" value="<%=adminVO.getEmail()%>"> 
		                            <input type="hidden" name="isEmp" value="<%=adminVO.getIsEmp()%>"> 
								<input type="hidden" name="action" value="update"> 
								<input type="hidden" name="adminID" value="<%=adminVO.getAdminID()%>"> 
								<!-- 呼叫JS方法判斷新密碼輸入 -->
								<input type="submit" onclick="return test()" value="確定"> <br>
	                    </FORM>
	                  <br>
	                  <br>
	                  <br>
	            </div>
	            </div>
	          </div>
	        </div>
	        
<script type="text/javascript">
	        function test(){
	        var f = document.form1;
	        var newPsw1 = f.password.value;
	        var newPsw2 = f.password2.value;
	        if(newPsw1 == "" || newPsw2 == ""){
	        alert("密碼請勿空白");
	        return false;
	        }
	        if(newPsw1.length<6){
		   	alert("密碼不得低於6個英數字");
		    return false;
		    }
	        if(newPsw1.length>12){
			   	alert("密碼不得超過12個英數字");
			    return false;
			}
	        if(newPsw1 != newPsw2){
	        alert("新密碼輸入不一致");
	        return false;
	        }
	        return true;
	        }
	
</script>

<!--==========websocket推播 開始=============-->
<script>
var MyPoint = "/BroadcastOrderServer/${adminID}";
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

var webSocketTitle = document.getElementById("webSocketTitle"); //狀態標題
var statusOutput = document.getElementById("statusOutput"); //狀態內容

var webSocket;

function connect() {

    //建立websocket物件
    webSocket = new WebSocket(endPointURL);

    webSocket.onopen = function(event) {
//         updateStatus("WebSocket 成功連線");
    };

//     webSocket.onmessage = function(event) {
//         var jsonObj = JSON.parse(event.data);
//         var message = jsonObj.message + "\r\n";
//         window.alert(message);
//         debugger;
//         //         window.alert("${AllDelay}");
//         debugger;
//         // console.log(Alldelay);
//         updateStatus(message);
//     };

		var message=""; //本次連線的推播容器
 		webSocket.onmessage=function(event){
 			var jsonObj=JSON.parse(event.data);
 			message=jsonObj.message+"\r\n"+"<br>"+message;
//  			window.alert(message);
//  			updateStatus(message);
 			
 			swal(message, "請至【訂單管理】查詢處理", "success");
 			
//  			else{
//  				swal(message, "歡迎使用Picar智慧叫車系統", "success");
//  				count++;
//  			}
 		};

    if ("${AllDelay}" != null) {
        window.alert("逾時訂單" + "${AllDelay}");
        debugger;
    }

    webSocket.onclose = function(event) {
//         updateStatus("WebSocket已離線");
    };
}

function disconnect() {
    webSocket.close();
}

// function updateStatus(newStatus) {
//     statusOutput.innerHTML = newStatus;
// }
</script>
<!--==========websocket推播 結束============-->

