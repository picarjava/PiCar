<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.groupBand.model.*"%>  
    
<%
	GroupBandVO groupBandVO = (GroupBandVO)request.getAttribute("groupBandVO"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>揪團檢舉查詢</title>

<style>
table {
	border-collapse: collapse;
	width: 80%;
		margin:auto;
}

th, td {
	text-align: center;
	padding: 8px;
}

th, td ,th {
	border:2px #cccccc dashed;
}


tr:nth-child(odd) {
	background-color: #dcdcdc;
}

body {
   font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro','Noto Sans CJK SC', monospace;
   font-size:18px;
   margin-top:5%;
}

button {
    padding:10px 20px; 
    background:#DDDDDD; 
    border:0 none;
    cursor:pointer;
    -webkit-border-radius: 10px;
    border-radius: 5px; 
    font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro', 'Noto Sans CJK SC', monospace;
    font-size: 16px;
    color:#444444;
    position:relative;
	transition: 0.4s;
	margin-left:48%;
	margin-top:30px;
}

button:hover
{
    background:rgb(248, 197, 68);
}

h2 {
	text-align:center;
}


</style>


</head>
<body>
<h2>檢舉揪團詳細內容</h2>
<br>
	<table>
		<tr>
			<th colspan="3">揪團ID</th>		
			<th colspan="6">發起時間</th>
			<th colspan="6">揪團種類</th>
		</tr>
		
		<tr>
			<td colspan="3"><%=groupBandVO.getGroupID()%></td>		
			<td colspan="6"><%=groupBandVO.getLaunchTime()%></td>
			<td colspan="6"><%=groupBandVO.getGroupKind()%></td>
		</tr>
		
		<tr>
			<th colspan="12">簡介</th>
			<th>現在人數</th>
			<th>上限人數</th>
			<th>下限人數</th>
		</tr>	
		
		

		<tr>
			<td colspan="12" rowspan="7"><%=groupBandVO.getIntroduction()%></td>	
			<td><%=groupBandVO.getCurrenTnum()%></td>
			<td><%=groupBandVO.getUpperLimit()%></td>
			<td><%=groupBandVO.getLowerLimit()%></td>				
		</tr>	
			
	
			
		<tr>	
			<th>團名</th>
			<th>團長</th>
			<th>揪團類別</th>	
		</tr>

		<tr>
			<td><%=groupBandVO.getGroupName()%></td>
			<td><%=groupBandVO.getGroupLeader()%></td>	
			<td><%=groupBandVO.getGroupType()%></td>
		</tr>
					
		<tr>	
			<th>上車地點</th>
			<th>下車地點</th>
<!-- 			<th>隱私設定</th> -->
			<th colspan="3">照片</th>	
		</tr>
		
		<tr>
			<td><%=groupBandVO.getStartLoc()%></td>
			<td><%=groupBandVO.getEndLoc()%></td>
<%-- 			<td><%=groupBandVO.getPrivates()%></td> --%>
			<td colspan="3"><img src="/PiCar/GroupBand?groupID=<%=groupBandVO.getGroupID()%>" width="100px" height="100px"></td>

		</tr>
			
		<tr>	
			<th>總金額</th>
			<th>上車時間</th>
			<th>評價分數</th>
		</tr>
		<tr>
			<td><%=groupBandVO.getTotalAmout()%></td>
			<td><%=groupBandVO.getStartTime()%></td>
			<td><%=groupBandVO.getRate()%></td>
		</tr>
		<tr>
			<th colspan="18">備註</th>
		</tr>
		<tr>	
			<td colspan="18"><%=groupBandVO.getNote()%></td>
		</tr>
	</table>
	<br>
    <form action="<%=request.getContextPath()%>/back-end/groupReport/greport_select_page.jsp">
		<div class="text-center">
			<button type="submit" class="btn btn-outline-success">返回上一頁</button>
		</div>
	</form>	

</body>
</html>