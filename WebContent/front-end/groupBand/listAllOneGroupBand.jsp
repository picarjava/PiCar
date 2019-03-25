<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.groupBand.model.*"%>
    <%@ page import="com.groupOrder.model.*"%>
    <%@ page import="java.util.*"%>
    <%@ page import="com.member.model.*"%>
 
     


    <%
    MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
    GroupOrderService groupOrderService =new GroupOrderService();
    List<GroupOrderVO> lists = groupOrderService.GET_one_groupid__state_men_id(memberVO.getMemID());
    GroupBandService groupBandSvc = new GroupBandService();
    List<GroupBandVO> list=new ArrayList<GroupBandVO>();

    for(GroupOrderVO listrer :lists){
    	
    GroupBandVO groupBandVO = groupBandSvc.getOneGroupBand(listrer.getGroupID());

    list.add(groupBandVO);
    }

    pageContext.setAttribute("list",list);
%>
<% String groupKind[] = {"揪團","長期揪團"}; %>
<!--     <link href="img/favicon.png" rel="icon"> -->
<!-- <link href="img/apple-touch-icon.png" rel="apple-touch-icon"> -->

<!-- <!-- Google Fonts --> -->
<!-- <link -->
<!-- 	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700" -->
<!-- 	rel="stylesheet"> -->

<!-- <!-- Bootstrap CSS File --> -->
<!-- <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet"> -->

<!-- <!-- Libraries CSS Files --> -->
<!-- <link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet"> -->
<!-- <link href="lib/animate/animate.min.css" rel="stylesheet"> -->

<!-- <!-- Main Stylesheet File --> -->
<!-- <link href="css/style.css" rel="stylesheet"> -->
<!-- <link rel="stylesheet" type="text/css"> -->
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>揪團管理</title>
  <style>
  .Gridline {
	margin: auto;
	width: 50%;
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
}	
.floatSquare{


}
.floatSqu{
    margin-top: -50px;
        position: absolute;
            right: 40%;
}
</style>
</head>

<body align="center">
<jsp:include page="/front-end/HomeMember/HeadMember.jsp" />
<jsp:include page="/front-end/HomeMember/HeadMemberGroup.jsp" />

<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<div style="margin-top: 50px;"></div>

<%@ include file="page1.file" %> 
<div style="margin-top: 50px;"></div>
<c:forEach var="GroupBandVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

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

</c:forEach>

<%@ include file="page2.file" %>


</body>
</html>