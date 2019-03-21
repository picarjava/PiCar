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
<title>Insert title here</title>
</head>
<body>

	<table>
		<tr>
			<th>揪團ID</th>		
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
			<th>總金額</th>
			<th>上車時間</th>
			<th>評價分數</th>
			<th>備註</th>


		</tr>

		<tr>
			<td><%=groupBandVO.getGroupID()%></td>		
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
			<td><img src="/PiCar/GroupBand?groupID=<%=groupBandVO.getGroupID()%>" width="100px" height="100px"></td>
			<td><%=groupBandVO.getGroupType()%></td>
			<td><%=groupBandVO.getTotalAmout()%></td>
			<td><%=groupBandVO.getStartTime()%></td>
			<td><%=groupBandVO.getRate()%></td>
			<td><%=groupBandVO.getNote()%></td>
		</tr>
	</table>


</body>
</html>