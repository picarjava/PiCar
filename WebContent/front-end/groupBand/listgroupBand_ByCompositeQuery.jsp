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

<table>
	<tr>
<th>發起時間</th><th>${GroupBandVO.launchTime}</th>
<th>簡介</th><th>${GroupBandVO.introduction}</th>
<th>現在人數</th><th>${GroupBandVO.currenTnum}</th>
<th>上限人數</th><th>${GroupBandVO.upperLimit}</th>
	</tr>
	<tr>
<th>下限人數</th><th>${GroupBandVO.lowerLimit}</th>
<th>團名</th><th>${GroupBandVO.groupName}</th>

<c:set var="id" scope="page" value="${GroupBandVO.groupLeader}" />
			<%
	String id = (String) pageContext.getAttribute("id");
	MemberService memberService = new MemberService();
	MemberVO memberVOS =  memberService.getOneMember(id);
	pageContext.setAttribute("memberVOS", memberVOS);
%>

<th>團長</th><th>${memberVOS.name}</th>
<th>上車地點</th><th>${GroupBandVO.startLoc}</th>
</tr>
	<tr>
<th>下車地點</th><th>${GroupBandVO.endLoc}</th>
<th>照片</th><th><img src="/PiCar/GroupBand?groupID=${GroupBandVO.groupID}" width="100px"   height="100px"></th>
<th>揪團類別</th><th>${GroupBandVO.groupType}</th>
<th>上車時間</th><th>${GroupBandVO.startTime}</th>
</tr>
	<tr>




<th>揪團種類</th><c:forEach var="mypurstatus" items="${groupKind}" varStatus="s">
		 <c:choose>
		 <c:when test="${GroupBandVO.groupKind-5 == s.index}">
		 <th>${mypurstatus}</th>
		 </c:when>
		 </c:choose>
		 </c:forEach>
<th><FORM METHOD="post" ACTION="<%=request.getServletContext().getContextPath()%>/GroupBand" enctype="multipart/form-data" style="margin-bottom: 0px;">
	<input type="submit" value="進入揪團">
	<input type="hidden" name="groupLeader"  value="${GroupBandVO.groupLeader}">
	<input type="hidden" name="startTime"  value="${GroupBandVO.startTime}">
	<input type="hidden" name="groupID"  value="${GroupBandVO.groupID}">
	<input type="hidden" name="memIDs" value="${memberVO.memID}" /> 
	<input type="hidden" name="action"	value="GroupJoin"></FORM>
</th>
<th>
	<button type="button" value="${GroupBandVO.groupID}" id="${GroupBandVO.groupID}" onclick="floatingwindowshow('${GroupBandVO.groupID}','${memberVO.memID}')" class="btn btn-lg btn-danger" ><i class="fas fa-exclamation-triangle"></i></button>
</th>
</tr>	
</table>
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