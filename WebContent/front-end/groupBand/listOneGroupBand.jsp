<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.groupBand.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.groupMem.model.*"%>
<%@ page import="com.groupOrder.model.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.SimpleDateFormat"%>


<%
	MemberVO memberVOs = (MemberVO) session.getAttribute("memberVO");

	GroupBandVO groupBandVO = (GroupBandVO) request.getAttribute("GroupBandVO");
	Object GroupLeader = request.getAttribute("GroupLeader");
	List<MemberVO> testList = (List) session.getAttribute("testList");
	boolean dropOut = (boolean) request.getAttribute("dropOut");
%>
<%
	String groupKind[] = {"揪團", "長期揪團"};
%>
<%
	request.setAttribute("groupKind", groupKind);
%>
<!DOCTYPE html>

<html>
<head>
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,400">
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Droid+Sans">
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Lobster">
      <link rel="stylesheet" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/prettyPhoto/css/prettyPhoto.css">
        <link rel="stylesheet" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/css/flexslider.css">
        <link rel="stylesheet" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/css/font-awesome.css">
        <link rel="stylesheet" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/css/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
             <link rel="shortcut icon" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/ico/apple-touch-icon-57-precomposed.png">

<meta charset="UTF-8">
<title>listOneGroupBand.jsp</title>
<!-- google map -->
<style>

#messagesArea {
	height: 300px;
	width: 850px;
}

#map_canvas {
	border-radius: 10px;
    height: 270px;
    width: 200px;
}

#origin-input, #destination-input {
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
.p-2 {
	margin: auto;
	width: 200px;
	height: 300px;
}

.marrgin {
	width: 150px;
	height: 200px;
	color: #fff;
	background-color: #fff;
	margin: auto;
}

.tentcenter {
	text-align: center;
	font-family: Microsoft JhengHei;
	font-weight: bold;
	margin-top: 25px;
}

.mar {
	margin-left: 5px;
}

.imgss {
	width: 150px;
	height: 200px;
}

.color1 {
	background-color: #008ccc !important;
}

.color0 {
	background-color: #FFB7DD !important;
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
margin-top: 60px;
	margin: auto;
	background-color: white;
    margin-top: 15px;
	margin-bottom: 15px;
}

table, th, td {
	border-radius: 10px;
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
.what-we-do .service {
    background: #eeeeee;
        height: 190px;
}
.imgtopStyle{
    margin-bottom: 55px;
    border: 6px solid #ccc;
    -moz-border-radius: 0;
    -webkit-border-radius: 0;
box-shadow:4px 4px 12px 4px rgba(20%,20%,40%,0.5);
}
.imgtopStyle:hover{
    margin-bottom: 55px;
    border: 6px solid #ccc;
    -moz-border-radius: 0;
    -webkit-border-radius: 0;
    -moz-box-shadow:1px 1px 3px 2px rgba(20%,20%,40%,0.5) inset;
-webkit-box-shadow:1px 1px 3px 2px rgba(20%,20%,40%,0.5) inset;
  box-shadow:1px 1px 3px 2px rgba(20%,20%,40%,0.5) inset;    
}
.Gridline{
	margin: auto;
    width: 80%;
    background: #e6e6e6;
    height: 5px;   
    margin-top: 20px;
}
.floating{
    left: 33%;
    position: relative;
    bottom: 25%;
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
    border: 2px solid #2dc997;
    0 1px 25px 0 rgba(0,0,0,.05) inset,: ;
    0 -1px 25px 0 rgba(0,0,0,.05) inset: ;
    webkit-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
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
}
.sunbmits{
    font-family: "Poppins", sans-serif;
    border-radius: 50px;
    padding: 5px 22px;
    background: #f5f5f5;
    color: #333333;
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
}

.sunbmits:hover {
   
    font-family: "Poppins", sans-serif;
    border-radius: 50px;
    padding: 5px 22px;
    background: #999999;
    color: #e5e5e5;
    font-style: italic;
    text-decoration: none;
    moz-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    webkit-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    border: 2px solid #ddd;
    0 1px 25px 0: ;
    rgba(0,0,0,.05) inset,: ;
    0 -1px 25px 0: ;
    rgba(0,0,0,.05) inset: ;
    webkit-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
}

.sunbm{
    font-family: "Poppins", sans-serif;
    border-radius: 10px;
    padding: 5px 22px;
    background: #f5f5f5;
    color: #333333;
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
}

.sunbm:hover {
   
    font-family: "Poppins", sans-serif;
    border-radius: 10px;
    padding: 5px 22px;
    background: #999999;
    color: #e5e5e5;
    font-style: italic;
    text-decoration: none;
    moz-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    webkit-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    border: 2px solid #ddd;
    0 1px 25px 0: ;
    rgba(0,0,0,.05) inset,: ;
    0 -1px 25px 0: ;
    rgba(0,0,0,.05) inset: ;
    webkit-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
}

.Modifyto{
left: 33%;
	margin-top: 1%;
    position: relative;
}
.styleall{

margin-top: 20px;
   margin-right: 10%;
}

.styleall form{
margin:8px; 
display:inline

   
}

.Exitto{
left: 33%;
	margin-top: 1%;
    position: relative;
}
.ChatRoomSpace{
    padding-left: 30px;
    padding-right: 5;
    padding-top: 20px;
    border-radius: 10px;
    background-color: #d8d8d8;
    height: 290px;
}
.chatroom{
width: 850px;
}
.backcolor{
	border-radius: 10px;
    height: 40px;
	width: 880px;
	 background-color: #e5e5e5;
}
.back{
       position:absolute;
    font-size: 30px;
    margin-left: 30px;
        margin-top: 10px;
}
.Details{
border-radius: 10px;
  	position: fixed;
    background-color: #555;
    width: 800px;
    height: 400px;
    z-index: 995;
    left: 25%;
    top: 30%;
}
  #topright{
  border-radius: 10px;
    position: absolute;
    right: 10px;
    top:10px;
  
  }
.DetailsInside{
    position: absolute;
    border-radius: 10px;
    background-color: #200;
    width: 750px;
    height: 320px;
    z-index: 994;
    top: 60px;
    left: 3%;
}
.DetailsInside .mapInside{
	    width: 200px;
    background-color: #999;
    border-radius: 10px;
    height: 270px;
    top: 10px;
    margin-top: 20px;
    /* margin-left: 50px; */
}
.DetailsInside .textInside{
    width: 480px;
    background-color: #f2f2f2;
    border-radius: 10px;
    height: 250px;
    top: 10px;
    margin-top: 20px;
    margin-left: 50px;
    font-size: 20px;
    color: #000;
}
.marring{
    margin-bottom: 20px;

}
</style>
</head>
<body onload="connect();"onunload="disconnect();" >
	<jsp:include page="/front-end/HomeMember/HeadMember.jsp" />
	<jsp:include page="/front-end/HomeMember/HeadMemberGroup.jsp" />

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>

			</c:forEach>
		</ul>
	</c:if>
<!--             圖片 -->
<div class="Details" style="display:none">
	<button type="button" onclick="Fork()" id="topright" class="" ><i class="fas fa-times fa-2x"></i></button>
	
		<div  class="DetailsInside " >
			<div class="row">
				<div class="services-half-width-text span9 textInside">
<div class="marring">簡介:</div>
<div class="marring"><%=groupBandVO.getIntroduction()%></div>
<div class="marring">上車時間:</div>
			<div class="marring"><%
				if (groupBandVO.getGroupKind() == 6) {
			%>
			<%
				GroupOrderService groupOrderservice = new GroupOrderService();
					Timestamp timestamp = groupOrderservice.getStartTimeGgroupID(groupBandVO.getGroupID());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					//進行轉換
					String dateString = sdf.format(groupBandVO.getStartTime());;
			%>

			
				<%
					out.println(dateString.substring(0, 10));
				%>~<%
					out.println(timestamp);
				%>
			
			<%
				} else {
			%>
			<%=groupBandVO.getStartTime()%>
			<%
				}
			%></div>
人數倍率：<br>
<table style=" max-width:; 
     background-color: ; 
     border-collapse: inherit;; 
     border-spacing: 3px; ">
<tr>
<th>
1人:<%=groupBandVO.getTotalAmout()/1%>   
</th>
<th>
2人:<%=groupBandVO.getTotalAmout()/2%>   
</th>
<th>
3人:<%=groupBandVO.getTotalAmout()/3%>   
</th>
<th>
4人:<%=groupBandVO.getTotalAmout()/4%>   
</th>
</tr>
</table>
</div>
				<div class="services-half-width-textspan3 mapInside ">
					<div id="map_canvas"style=" height:100%;width:100%">						
 					 </div>
				</div>			
			</div>
			
		</div>
</div>
<!-- 團名 -->
	<div class="row"  style="margin-top: 20px">
		<div class="Gridline"></div>
      <div class="services-full-width container" style="margin-top: 45px;">
        <div class="imgtopStyle"> 
           <img  src="/PiCar/GroupBand?groupID=<%=groupBandVO.getGroupID()%>" style="width: 2000px;  height: 300px;"/>
        </div>
      </div>
	<div class="Gridline"></div>
        <!-- Services -->
         <div class="services-half-width container">
        	 <div class="row" >
         		  <div class="services-half-width-text span9">
         			  <div class="backcolor">
         			  		 <div class="back"><%=groupBandVO.getGroupName()%></div>
         			 </div>
         		  </div>
         		  <div class="services-half-width-text span3">
         		  		<button class="sunbm" type="button" onclick="Clickdetails();initialize();calcRoute();">詳情</button>
         		  </div>
         	</div>
         </div>
        
<!--            團員 -->
        <div class="what-we-do container">
            <div class="row">

<%int sun=0;%>
           		<c:forEach var="MemberVO" items="${testList}">
            		
                <div class="service span3">
                    <div class="icon-awesome">
                 		<div style="width:100px; height:100px; border-radius:50%; overflow:hidden; margin: auto;"> 
   							<img class="imgss" src="/PiCar/GroupBand?MEM_ID=${MemberVO.memID}">
						</div>
                    </div>
                    <h2>${MemberVO.name}<img class="mar"
							src="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/img/${MemberVO.gender}.png"
							width="10px" height="10px"></h2>
                    <c:if test="${MemberVO.memID==GroupBandVO.groupLeader}">
                    <p style="font-size: 20px;">團主</p>
                    </c:if>
                      <c:if test="${MemberVO.memID!=GroupBandVO.groupLeader}">
                    <p style="font-size: 20px;">團員</p>
                    </c:if>
                    				<c:if test="${MemberVO.memID!=memberVO.memID}">
                    			
					<%
						if ("true".equals(GroupLeader)) {
					%>
						<div class="floating">
					<FORM METHOD="post"
						ACTION="<%=request.getServletContext().getContextPath()%>/GroupBand"
						enctype="multipart/form-data" style="margin-bottom: 0px;">
						<input type="submit" value="踢出" class="buttonS"> <input type="hidden"
							name="groupLeader" value="<%=groupBandVO.getGroupLeader()%>">
						<input type="hidden" name="groupID" value="${GroupBandVO.groupID}">
						<input type="hidden" name="startTime"
							value="<%=groupBandVO.getStartTime()%>"> <input
							type="hidden" name="memIDs" value="${memberVO.memID }" /> <input
							type="hidden" name="groupKind"
							value="<%=groupBandVO.getGroupKind()%>"> <input
							type="hidden" name="GroupmemID" value="${MemberVO.memID}">
						<input type="hidden" name="action" value="GroupAdd"> <input
							type="hidden" name="dropOutbutton" value="Kickingpeople">
					</FORM>
					</div>
					<%
						}
					%>
					
				</c:if>
                </div>
                <%sun++;%>
		</c:forEach>
		<%for(int x=sun;x<4;x++){%>			
			<%if(x>=groupBandVO.getUpperLimit()) {%>	
			<div class="service span3" style="background: #ccc; ">
			<img src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/img/x.png" style="width: 150px;margin: 20px;margin-top: 35px;">
			</div>
			<%}else if(x<groupBandVO.getLowerLimit()){%>
			<div class="service span3" style="background: #ececec;">
			</div>			
			<%}else{%>
			<div class="service span3" style="background: #ddd; ">
			</div>
		<%}}%>
		
            </div>
        </div>
        
<!--        修改資料 -->
        <div class="styleall">
        	<%
		if ("true".equals(GroupLeader)) {
	%>
	<form class="Exitto"
		action="<%=request.getServletContext().getContextPath()%>/GroupBand"
		method="POST" enctype="multipart/form-data"
		style="margin-bottom: 0px;">
		<input type="submit" value="修改" class="sunbmits"> <input type="hidden"
			name="groupID" value="${GroupBandVO.groupID}"> <input
			type="hidden" name="action" value="getOne_For_Update">
	</FORM>

	<%
		}
	%>
	<%
		if (false == dropOut) {
	%>
	<FORM METHOD="post" class="Exitto"
		ACTION="<%=request.getServletContext().getContextPath()%>/GroupBand"
		enctype="multipart/form-data" style="margin-bottom: 0px;">
		<input type="submit" value="加入" class="sunbmits"> <input type="hidden" 
			name="groupLeader" value="<%=groupBandVO.getGroupLeader()%>">
		<input type="hidden" name="groupID" value="${GroupBandVO.groupID}">
		<input type="hidden" name="startTime"
			value="<%=groupBandVO.getStartTime()%>"> <input type="hidden"
			name="memIDs" value="${memberVO.memID }" /> <input type="hidden"
			name="groupKind" value="<%=groupBandVO.getGroupKind()%>"> <input
			type="hidden" name="action" value="GroupAdd">

	</FORM>
	<%
		} else {
	%>

	<FORM METHOD="post" class="Exitto" class="Modifyto"
		ACTION="<%=request.getServletContext().getContextPath()%>/GroupBand"
		enctype="multipart/form-data" style="margin-bottom: 0px;">
		<input type="submit" value="退出"  class="sunbmits"> <input type="hidden"
			name="groupLeader" value="<%=groupBandVO.getGroupLeader()%>">
		<input type="hidden" name="groupID" value="${GroupBandVO.groupID}">
		<input type="hidden" name="startTime"
			value="<%=groupBandVO.getStartTime()%>"> <input type="hidden"
			name="memIDs" value="${memberVO.memID }" /> <input type="hidden"
			name="groupKind" value="<%=groupBandVO.getGroupKind()%>"> <input
			type="hidden" name="action" value="GroupAdd"> <input
			type="hidden" name="dropOutbutton" value="dropOutbutton">
	</FORM>
	<%
		}
	%>
        </div>
        
      <div class="Gridline"></div>
        
        <div class="services-half-width container">
            <div class="row">
                <div class="services-half-width-text span9">
                   	<textarea id="messagesArea" class="panel message-area text-right" readonly></textarea>  </div>
                <div class="services-half-width-text span3">
                <div class="ChatRoomSpace">
                <h3>在線人員</h3>
					<div id="messa"></div>
                </div>
                </div>
            </div>
        </div>
        <div class="services-half-width container">
			<div class="row">
				<div class="services-half-width-text span9">
					<input id="message" class="text-field chatroom" type="text" placeholder="訊息"
					onkeydown="if (event.keyCode == 13) sendMessage();" /> 
				</div>
				<div class="services-half-width-text span3">
					<input
					type="submit" id="sendMessage" class="button sunbmits" value="送出"
					onclick="sendMessage();" />
				</div>
			</div>
		</div>
	
	
		
	<div style="margin-top: 20px;"></div>
	<div>
	</div>
	<div class="row" style="margin-top: 20px">
		<c:forEach var="MemberVO" items="${testList}">
				<!-- 				踢人功能 -->
		</div>
			
			<script>
			var Mymoney ='<%=groupBandVO.getGroupID()%>'.charCodeAt(0)+'<%=groupBandVO.getGroupID()%>'.substr(1);
		
var MyPoint = "/webSocket/${memberVO.memID}/"+parseInt(Mymoney);
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





	<h3 id="statusOutput" class="statusOutput"  style="display:none"></h3>


<!-- 	<div id="messa"></div> -->

	<div id="but"></div>
	<div class="panel input-area">

		<input id="userName" class="text-field" type="hidden"
			placeholder="使用者名稱" value="${memberVO.name}" disabled="disabled" />
		<input id="message" class="text-field" type="hidden" placeholder="訊息"
			onkeydown="if (event.keyCode == 13) sendMessage();" /> <input
			type="hidden" id="sendMessage" class="button" value="送出"
			onclick="sendMessage();" /> <input type="hidden" id="connect"
			class="button" value="連線" onclick="connect();" /> <input
			type="hidden" id="disconnect" class="button" value="離線"
			onclick="disconnect();" />
	</div>

	<script type="text/javascript" src="http://www.google.com/jsapi"></script>
	<script type="text/javascript" language="javascript">google.load("jquery", "1.3");</script>
	<!-- json傳資料 -->
	<script>
	function Clickdetails(){
		$('.Details').show();		
	}
	function Fork(){
		$('.Details').hide();		
	}
	</script>
	
	<script>
function groupSelect()
{
	   $.ajax({

	        //告訴程式表單要傳送到哪裡                                         

	        url:"<%=request.getServletContext().getContextPath()%>/front-end/groupBand/AjexGroupSelect.jsp",                                                              

	        //需要傳送的資料

	        data:"memid=${memberVO.memID}&groupID=<%=groupBandVO.getGroupID()%>",  

	         //使用POST方法     

	        type : "POST",                                                                    

	        //接收回傳資料的格式，在這個例子中，只要是接收true就可以了
	        dataType:'json', 

	         //傳送失敗則跳出失敗訊息      

	        error:function(selects){                                                                 
	        	 console.log(selects);
	        //資料傳送失敗後就會執行這個function內的程式，可以在這裡寫入要執行的程式  
// 					var messa = document.getElementById("messa");
	        		for(let i=0;i<selects.length;i++)
	        		{
	        			var newDiv = document.createElement("div");
	        			newDiv.id = selects[i].name;
	        			newDiv.innerHTML= selects[i].name;
	        			messa.appendChild(newDiv);
	        			
	        		}
	        		
	        
	        
					
	        },

	        //傳送成功則跳出成功訊息

	        success:function(selects){   
	        	
	        	 console.log(selects);
	 	        //資料傳送失敗後就會執行這個function內的程式，可以在這裡寫入要執行的程式  
// 	 					var messa = document.getElementById("messa");
	 					
	 	        		for(let i=0;i<selects.length;i++)
	 	        		{
	 	        			
	 	        			var newDiv = document.createElement("div");
	 	        			newDiv.id = selects[i].name;
	 	        			newDiv.innerHTML= selects[i].name+":  已連線";
	 	        			messa.appendChild(newDiv);
	 	        			
	 	        			 
	 	        		}
	 	        		
	        //資料傳送成功後就會執行這個function內的程式，可以在這裡寫入要執行的程式  
	  		

	        }

	    }); 
	
	}
</script>

	<script>
function groupSelectChatRoom()
{
	   $.ajax({

	        //告訴程式表單要傳送到哪裡                                         

	        url:"<%=request.getServletContext().getContextPath()%>/front-end/groupBand/AjexGroupInsertchatroomSelete.jsp",                                                              

	        //需要傳送的資料

	        data:"groupID=<%=groupBandVO.getGroupID()%>",

	         //使用POST方法     

	        type : "POST",                                                                    

	        //接收回傳資料的格式，在這個例子中，只要是接收true就可以了
	        dataType:'json', 

	         //傳送失敗則跳出失敗訊息      

	        error:function(selects){                                                                 
	        	 console.log(selects);
	        //資料傳送失敗後就會執行這個function內的程式，可以在這裡寫入要執行的程式  
					
	        	 var messagesqs = document.getElementById("messagesArea");
					messagesqs.value=selects.roomMem;
			
	        
	        
					
	        },

	        //傳送成功則跳出成功訊息

	        success:function(selects){                                                           
	        	 console.log(selects);
	 	        //資料傳送失敗後就會執行這個function內的程式，可以在這裡寫入要執行的程式  
	 					var messagesqs = document.getElementById("messagesArea");
	 					messagesqs.value=selects.roomMem;
	        //資料傳送成功後就會執行這個function內的程式，可以在這裡寫入要執行的程式  
	  		

	        }

	    }); 
	
	}
</script>

	<script>
function groupSelectUpdateChatRoom(room)
{

	   $.ajax({
			
	        //告訴程式表單要傳送到哪裡                                         

	        url:"<%=request.getServletContext().getContextPath()%>/front-end/groupBand/AjexGroupInsertchatroom.jsp",                                                              

	        //需要傳送的資料

	        data:"room="+room+"&groupID=<%=groupBandVO.getGroupID()%>",  

	         //使用POST方法     

	        type : "POST",                                                                    

	        //接收回傳資料的格式，在這個例子中，只要是接收true就可以了
	        dataType:'json', 

	         //傳送失敗則跳出失敗訊息      

	        error:function(selects){                                                                 
	        	 console.log(selects);
	        //資料傳送失敗後就會執行這個function內的程式，可以在這裡寫入要執行的程式  
					
// 	        		for(let i=0;i<selects.length;i++)
// 	        		{
// 	        			var newDiv = document.createElement("div");
// 	        			newDiv.id = selects[i].name;
// 	        			newDiv.innerHTML= selects[i].name;
// 	        			messa.appendChild(newDiv);
// 	        		}
			
	        
	        
					
	        },

	        //傳送成功則跳出成功訊息

	        success:function(selects){                                                           
	        	 console.log(selects);
	 	        //資料傳送失敗後就會執行這個function內的程式，可以在這裡寫入要執行的程式  
	 					
// 	 	        		for(let i=0;i<selects.length;i++)
// 	 	        		{
// 	 	        			var newDiv = document.createElement("div");
// 	 	        			newDiv.id = selects[i].name;
// 	 	        			newDiv.innerHTML= selects[i].name+":  已連線";
// 	 	        			messa.appendChild(newDiv);
// 	 	        		}
	        //資料傳送成功後就會執行這個function內的程式，可以在這裡寫入要執行的程式  
	  		

	        }

	    }); 
	
	}
</script>




	<script>
function groupJoin()
{
	   $.ajax({

	        //告訴程式表單要傳送到哪裡                                         

	        url:"<%=request.getServletContext().getContextPath()%>/front-end/groupBand/AjexGroupInsert.jsp",                                                              

	        //需要傳送的資料

	        data:"memid=${memberVO.memID}&groupID=<%=groupBandVO.getGroupID()%>",  

	         //使用POST方法     

	        type : "POST",                                                                    

	        //接收回傳資料的格式，在這個例子中，只要是接收true就可以了
	        dataType:'json', 

	         //傳送失敗則跳出失敗訊息      

	        error:function(){                                                                 

	        //資料傳送失敗後就會執行這個function內的程式，可以在這裡寫入要執行的程式  

	       

	        },

	        //傳送成功則跳出成功訊息

	        success:function(selects){                                                           

	        //資料傳送成功後就會執行這個function內的程式，可以在這裡寫入要執行的程式  
	  		

	        }

	    }); 
// 1代表加入揪團 0代表沒加入揪團
//查詢資料庫的所有揪團
var messa = document.getElementById("messa");
<%GroupMemService groupMemService = new GroupMemService();
			List<GroupMemVO> groupMemlist = new ArrayList<GroupMemVO>();
			groupMemlist = groupMemService.getAllgroupID(groupBandVO.getGroupID(), 1);
			MemberService memberService = new MemberService();
			MemberVO memberVO = new MemberVO();
			for (GroupMemVO Memlist : groupMemlist) {

				memberVO = memberService.getOneMember(Memlist.getMemID());%>	
	 var newDiv = document.createElement("div");
	 	newDiv.id="<%=memberVO.getName()%>";
	 	newDiv.innerHTML="<%=memberVO.getName()%>  :已連線";
	 	messa.appendChild(newDiv); 
	<%}%>


<%-- 	jQuery.post("AjexGroupInsert.jsp","memid="+"${memberVO.memID}&groupID="+"<%=groupBandVO.getGroupID()%>"); --%>
}

</script>


	<script>
function groupdelecte()
{
	   $.ajax({

	        //告訴程式表單要傳送到哪裡                                         

	        url:"<%=request.getServletContext().getContextPath()%>/front-end/groupBand/AjexGroupUpdate.jsp",                                                              

	        //需要傳送的資料

	        data:"memid=${memberVO.memID}&groupID=<%=groupBandVO.getGroupID()%>",  

	         //使用POST方法     

	        type : "POST",                                                                    

	        //接收回傳資料的格式，在這個例子中，只要是接收true就可以了
	        dataType:'json', 

	         //傳送失敗則跳出失敗訊息      

	        error:function(){                                                                 

	        //資料傳送失敗後就會執行這個function內的程式，可以在這裡寫入要執行的程式  

	       

	        },

	        //傳送成功則跳出成功訊息

	        success:function(selects){                                                           

	        //資料傳送成功後就會執行這個function內的程式，可以在這裡寫入要執行的程式  
	  		

	        }

	    }); 
// 1代表加入揪團 0代表沒加入揪團
//查詢資料庫的所有揪團



<%-- 	jQuery.post("AjexGroupInsert.jsp","memid="+"${memberVO.memID}&groupID="+"<%=groupBandVO.getGroupID()%>"); --%>
}

</script>

	<script>
var btn = document.createElement("BUTTON");//放甚麼就創甚麼
   
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
    
	var statusOutput = document.getElementById("statusOutput");
	var webSocket;
	var fiveItems = new Array();
    fiveItems[0]="${memberVO.memID}";
    var sun;
	function connect() {
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
        
		webSocket.onopen = function(event) {
			
			updateStatus("${memberVO.name} 成功連線");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
	        var jsonObj = {"userName" : "${memberVO.name}", "message" : "以連線","sessionUser" : "listsessionUser","userID" : "${memberVO.memID}","status":"0000"};
	        groupJoin();
	        groupSelectChatRoom();
	        webSocket.send(JSON.stringify(jsonObj));
	    	
		};

		webSocket.onmessage = function(event) {
			
			var messa = document.getElementById("messa");
			
			var messagesArea = document.getElementById("messagesArea");
	        var jsonObj = JSON.parse(event.data);
	        var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
	      
	        //判斷是誰的連線
 	        
 
	        if(jsonObj.sessionUser=="listsessionUser"||jsonObj.sessionUser=="END")
	        {
// 				if(jsonObj.status=="1111"){
				 
				messa.innerHTML="";
				 groupSelect();

	        }
	        else
	        {
	        	
	        messagesArea.value = messagesArea.value + message;
	        
	        messagesArea.scrollTop = messagesArea.scrollHeight;
	        groupSelectUpdateChatRoom(message);
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
<script src="https://maps.googleapis.com/maps/api/js?libraries=places&key=AIzaSyCWL8JxUOY0dQZ01M4rCgDU-oHLkP5BORI"></script>
<!-- auto place complete 開始 -->
<script>
var directionsService = new google.maps.DirectionsService();
var map;
var start = "<%=groupBandVO.getStartLoc()%>";
var end = "<%=groupBandVO.getEndLoc()%>";
var waypoints = "<%=groupBandVO.getStartLoc()%>,<%=groupBandVO.getEndLoc()%>";
  
//初始化
function initialize() {
        //規畫路徑呈現選項
        var rendererOptions = {
                suppressMarkers: true
        };
  
        directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
        var startPoint = new google.maps.LatLng(24.136845, 120.685009);
        var myOptions = {
                zoom: 14,
                mapTypeId: google.maps.MapTypeId.ROADMAP,
                center: startPoint
        }
        map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
        directionsDisplay.setMap(map);
}
  
//規畫路徑
function calcRoute() {
        if (!waypoints) return;
  
        var arrPoint = waypoints.split(",");
  
        //經過地點
        var waypts = [];
        for (var i = 0; i < arrPoint.length; i++) {
                waypts.push({
                        location: arrPoint[i],
                        stopover: true
                });
        }
  
        //規畫路徑請求
        var request = {
                origin: start,
                destination: end,
                waypoints: waypts,
                optimizeWaypoints: true,
                travelMode: google.maps.DirectionsTravelMode.DRIVING
        };
          
        directionsService.route(request, function(response, status) {
                //規畫路徑回傳結果
                if (status == google.maps.DirectionsStatus.OK) {
                        directionsDisplay.setDirections(response);
                }
        });
}
</script>


<!-- auto complete結束 -->
          <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/js/jquery-1.8.2.min.js"></script>
        <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/js/jquery.flexslider.js"></script>
        <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/js/jquery.tweet.js"></script>
        <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/js/jflickrfeed.js"></script>

        <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/js/jquery.ui.map.min.js"></script>
        <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/js/jquery.quicksand.js"></script>
        <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/prettyPhoto/js/jquery.prettyPhoto.js"></script>
        <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/js/scripts.js"></script>
</html>