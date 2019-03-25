<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  	  <%@ page import="java.util.*"%>
  	 
      <%@ page import="com.groupMem.model.*"%>

    <%@ page import="com.groupBand.model.*"%>
     <%@ page import="com.member.model.*"%>
    
    <jsp:useBean id="listgroupBand_ByCompositeQuery" scope="request" type="java.util.List<GroupBandVO>" /> 
<%--     <jsp:useBean id="groupOrderService" scope="page" class="com.groupOrder.model.GroupOrderService" /> --%>
<% String groupKind[] = {"揪團","長期揪團"}; %>
<%request.setAttribute("groupKind", groupKind);%>

							
<!DOCTYPE html>
<html>
<head>
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
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
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
}	
.floatSquare{


}
.floatSqu{
    margin-top: -50px;
        position: absolute;
            right: 40%;
}
</style>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<div id="Floatingwindow" style="display:none">
<button type="button" onclick="Fork()" id="topright" class="" ><i class="fas fa-times fa-2x"></i></button>
<div><h1>檢舉內容</h1></div>
<div id="messageboards">檢舉原因</div>
<div id="noteExection"></div>
<div><textarea name="note" required id="note" class="form-control" rows="3" cols="50" style=" width: 400px;height: 150px;"></textarea></div>
<div><button type="button" onclick="floatingwindow()" class="" >送出</button></div>
</div>
<% int sss = 0;
 %>
 <script>
 var colors = [];
 </script>
	<%@ include file="page1_ByCompositeQuery.file" %>
<c:forEach var="GroupBandVO" items="${listgroupBand_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<div class="services-half-width container">
		<div class="row">

			<div class="services-half-width-text span9">
				房名:
				<div class="padding">${GroupBandVO.groupName}</div>
				上車時間:
				<div class="paddingTime">${GroupBandVO.startTime}</div>
				<br> 上車地點:
				<div class="paddinglocation">${GroupBandVO.startLoc}</div>
				<br> 下車地點:
				<div class="paddinglocation">${GroupBandVO.endLoc}</div>
				<br> 簡介:
				<div class="paddingtent">${GroupBandVO.introduction}</div>
				<br>
				<div class="floatSqu">
			<c:forEach var="x" begin="1" end="${GroupBandVO.upperLimit}" step="1" >	
			
				<c:if test="${x<=GroupBandVO.currenTnum}">	
					<img src="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/img/smogRed.png"  class="floatSquare">
				</c:if>
				
				<c:if test="${x>GroupBandVO.currenTnum}">	
					<img src="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/img/smog.png" class="floatSquare">
				</c:if>								
		</c:forEach>
		</div>
			</div>
			<div class="services-half-width-text span3">
				<div>
					<img src="/PiCar/GroupBand?groupID=${GroupBandVO.groupID}"
						class="boderRi" width="500px" height="500px">
				</div>
				<div style="margin-top: 10px">
					<FORM id="${GroupBandVO.groupID}" name="${GroupBandVO.groupID}"
						METHOD="post"
						ACTION="<%=request.getServletContext().getContextPath()%>/GroupBand"
						enctype="multipart/form-data" style="margin-bottom: 0px;">
						<button type="button" name="submit_Btn" id="submit_Btn"
							onClick="document.${GroupBandVO.groupID}.submit();"
							class="btsts" style="" title="加入揪團">
							<i class="fas fa-user-plus"></i>
						</button>
						<input type="hidden" name="groupLeader"
							value="${GroupBandVO.groupLeader}"> <input type="hidden"
							name="startTime" value="${GroupBandVO.startTime}"> <input
							type="hidden" name="groupID" value="${GroupBandVO.groupID}">
						<input type="hidden" name="memIDs" value="${memberVO.memID}" /> <input
							type="hidden" name="action" value="GroupJoin">
						<button type="button" value="${GroupBandVO.groupID}"
							id="${GroupBandVO.groupID}"
							onclick="floatingwindowshow('${GroupBandVO.groupID}','${memberVO.memID}')"
							class="btn btn-lg btn-danger" style="width: 60px; height: 35px;"
							title="檢舉揪團">
							<i class="fas fa-exclamation-triangle"></i>
						</button>
					</FORM>
				</div>
			</div>
		</div>
		</div>
<div class="Gridline"
			style="margin-top: 70px; margin-bottom: 70px; width: 70%; height: 10px"></div>


 <script>

colors[<%=sss%>] = document.getElementById("${GroupBandVO.groupID}");
colors[<%=sss%>].value="${GroupBandVO.groupID}";
<%sss++;%>
</script>

</c:forEach>
	<%@ include file="page2_ByCompositeQuery.file" %>
</body>
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
</html>