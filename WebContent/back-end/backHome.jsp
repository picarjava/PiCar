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


.sidebar-wrapper {

font-family: "FangSong";
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



</html>