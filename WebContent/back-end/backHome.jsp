<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.admin.model.*"%>
	
<%
	AdminVO adminVO = (AdminVO) session.getAttribute("adminVO");
%>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
<!doctype html>
<html lang="zh">
<head>
<title>PICAR BACK-END</title>
<jsp:include page="/back-end/head_back.jsp" />
<jsp:include page="/back-end/btnCss.jsp" />

        <script language="javascript">  
            setInterval("timer.innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);   
        </script>
<style>


#timer {
margin-top: 3%;
margin-left: 3%;
font-size: 200%;

}
</style>

</head>

<body onload="connect();" onunload="disconnect();">
<div id="timer"></div> 
	<div class="wrapper ">
		<div class="sidebar" data-color="" data-background-color="black"	data-image="../assets/img/sidebar-1.jpg">
			<div class="logo">
				<a href="<%=request.getServletContext().getContextPath()%>/back-end/backHome.jsp" class="simple-text logo-normal"> PICAR </a>
			</div>
      <jsp:include page="/back-end/sidebar.jsp" />
<%--       <jsp:include page="/back-end/kidBodyLeft.jsp" /> --%>
    	</div>
		<jsp:include page="/back-end/kidFooter.jsp" />
			</div>
		</div>
</body>
<!-- <!--==========websocket推播 開始=============--> -->
<!--  <script> -->
<%-- //  	var MyPoint="/BroadcastOrderServer/${adminID}"; --%>
<!-- //  	var host=window.location.host; -->
<!-- //  	var path=window.location.pathname; -->
<!-- //  	var webCtx=path.substring(0,path.indexOf('/',1)); -->
<!-- //  	var endPointURL="ws://"+window.location.host+webCtx+MyPoint; -->
 	
<!-- //  	var webSocketTitle =document.getElementById("webSocketTitle"); //狀態標題 -->
<!-- //  	var statusOutput=document.getElementById("statusOutput");//狀態內容 -->
 	
<!-- //  	var webSocket; -->
 	
<!-- //  	function connect(){ -->
 		
<!-- //  		//建立websocket物件 -->
<!-- //  		webSocket=new WebSocket(endPointURL); -->
 		
<!-- //  		webSocket.onopen=function(event){ -->
<!-- //  			updateStatus("WebSocket 成功連線"); -->
<!-- //  		}; -->
 		
<!-- //  		webSocket.onmessage=function(event){ -->
<!-- //  			var jsonObj=JSON.parse(event.data); -->
<!-- //  			var message=jsonObj.message+"\r\n"; -->
<!-- //   			window.alert(message); -->
<!-- //  			updateStatus(message); -->
<!-- //  		}; -->
 		
<!-- //  		webSocket.onclose=function(event){ -->
 			
<!-- //  			updateStatus("WebSocket已離線"); -->
<!-- //  		}; -->
<!-- //  	} -->
 	
<!-- //  	function disconnect(){ -->
<!-- //  		webSocket.close(); -->
<!-- //  	} -->
 	
<!-- //  	function updateStatus(newStatus){ -->
<!-- //  		statusOutput.innerHTML= newStatus; -->
<!-- //  	} -->
 	
 	
<!--  </script> -->

<!--  <!--==========websocket推播 結束============--> 



</html>