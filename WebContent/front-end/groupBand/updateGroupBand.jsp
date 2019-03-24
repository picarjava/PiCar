<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.groupBand.model.*"%>

<%
	GroupBandVO groupBandVO = (GroupBandVO) request.getAttribute("GroupBandVO");
%>

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" type="text/css"
	href="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script
	src="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getServletContext().getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<meta charset="BIG5">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<title>updateGroupBand.jsp</title>

<style>
input[type="file"] {
	position: absolute;
	width: 1px;
	height: 1px;
	padding: 0;
	margin: -1px;
	overflow: hidden;
	clip: rect(0, 0, 0, 0);
	border: 0;
}
.custom-file-upload {
	border: 1px solid #ccc;
	display: inline-block;
	padding: 6px 12px;
	cursor: pointer;
}
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
.btn{

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
.groupType{
width: 100%;
}
label {
display:;
}
#width100{
width: 100%;
}
.form-control{
width: 100%;
}
.form-group{
width: 100%;
}
</style>
</head>
<body bgcolor=#aaaaaa>
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
	<div class="about-us container">
<div class="about-us-text span12">
	<h3>修改揪團資料</h3>
	

	<form
		action="<%=request.getServletContext().getContextPath()%>/GroupBand"
		method="POST" enctype="multipart/form-data" name="form1">

 <div class="form-group" >
				<div>揪團類別</div>
	<select name="groupType" class="form-control" id="exampleFormControlSelect1" style="height: 50px;">
						<option value="演唱會"
							<%if ("演唱會".equals(groupBandVO.getGroupType())) {
				out.print("selected='selected'");
			}%>>演唱會</option>
						<option value="旅遊"
							<%if ("旅遊".equals(groupBandVO.getGroupType())) {
				out.print("selected='selected'");
			}%>>旅遊</option>
						<option value="美食"
							<%if ("美食".equals(groupBandVO.getGroupType())) {
				out.print("selected='selected'");
			}%>>美食</option>
						<option value="運動團"
							<%if ("運動團".equals(groupBandVO.getGroupType())) {
				out.print("selected='selected'");
			}%>>運動團</option>
						<option value="展覽"
							<%if ("展覽".equals(groupBandVO.getGroupType())) {
				out.print("selected='selected'");
			}%>>展覽</option>
						<option value="遊樂園"
							<%if ("遊樂園".equals(groupBandVO.getGroupType())) {
				out.print("selected='selected'");
			}%>>遊樂園</option>
				</select>
	 </div>

			 <div class="form-group">
	   <div>團名:</div>
<input style="height: 50px;" required id="width100" type="TEXT" name="groupName" size="25"
					value="<%=(groupBandVO == null) ? "" : groupBandVO.getGroupName()%>" />
			</div>
		 <div class="form-group">	
 <div>簡介:</div>
<textarea name="introduction" required id="note exampleFormControlTextarea1" style="width: 100%;" class="form-control" rows="3" cols="50"><%=(groupBandVO == null) ? "".trim() : groupBandVO.getIntroduction().trim()%></textarea>
		 </div>
		 <div class="form-group">
		  <div>備註:</div>
		 <textarea required name="introduction" id="note exampleFormControlTextarea1"  style="width: 100%;" rows="3" cols="50"><%=(groupBandVO == null) ? "好吃嗎".trim() : groupBandVO.getIntroduction().trim()%></textarea>
			 </div>
			
		<div>揪團圖片:</div>
		<div><img id="preview_img"
					src="/PiCar/GroupBand?groupID=${GroupBandVO.groupID}" width="100px"
					height="100px"> <img id="preview_progressbarTW_img" src="#"
					width="100px" height="100px" style="display: none" /> <label
					for="progressbarTWInput" class="custom-file-upload"> <i
						class="fas fa-file-import"></i> 上傳圖片
				</label> <input id="progressbarTWInput" type="file" name="photo" size="25"
					value="<%=groupBandVO.getPhoto()%>" /></div>
					
										
<input type="hidden" name="action" value="update" /> <input
					type="hidden" name="groupID" value="<%=groupBandVO.getGroupID()%>" >
					<input  type="hidden" name="LaunchTime"
					value="<%=groupBandVO.getLaunchTime()%>"> <input id="send"
					type="submit" value="更改揪團" id="send"/>
				<input type="reset" value="清除揪團" />

</div>
</div>
</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.js"></script>
<script>
	$("#progressbarTWInput").change(function() {

		readURL(this);

	});

	function readURL(input) {

		if (input.files && input.files[0]) {

			var reader = new FileReader();

			reader.onload = function(e) {

				$("#preview_progressbarTW_img").attr('src', e.target.result);
				$("#preview_progressbarTW_img").removeAttr("style");
				$("#preview_img").hide();

			}

			reader.readAsDataURL(input.files[0]);

		}

	}
</script>
<script>
var send=document.getElementById("send");
var distance=document.getElementById("distance");
var col=document.getElementById("col");

send.onclick=function(){
	var x=0;
	if(distance.innerHTML==''){
		col.innerHTML="<h3 style='color:#FF3333'>※請確實填寫勿空值※<h3>";
	x++;
		}
	
	if(x>0){
		return false;
		
	}
}

</script>
</html>