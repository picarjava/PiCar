<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.groupBand.model.*"%>

<%
	GroupBandVO groupBandVO = (GroupBandVO) request.getAttribute("groupBandVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<style>
table#table-1 {
	width: 450px;
	background-color: #008888;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>
</head>
<body bgcolor=#aaaaaa>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<table id="table-1">


		<tr>
			<td>
				<h3>發起揪團 - insertGroupBand.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>
	<h3>發起揪團</h3>

	<form action="/PiCar/GroupBand" method="POST">
		<table>

			<tr>
				<td><input type="radio" name="orderType" value="0">揪團</td>
				<td><input type="radio" name="orderType" value="1">長期揪團
				</td>
			</tr>

			<tr>
				<td>團名</td>
				<td><input type="TEXT" name="groupName" size="25"
					value="<%=(groupBandVO == null) ? "" : groupBandVO.getGroupName()%>" /></td>
			</tr>
			<tr>
				<td>簡介:</td>

				<td><textarea name="introduction" id="note" rows="3" cols="50">
					<%=(groupBandVO == null) ? "" : groupBandVO.getIntroduction()%> </textarea></td>
			</tr>

			<tr>
				<td>揪團圖片</td>

				<td><input type="file" name="photo" size="25"
					value="<%=(groupBandVO == null) ? "" : groupBandVO.getPhoto()%>" />
				</td>
			</tr>

			<tr>
				<td>上車地點:</td>

				<td><select name="startLoc">
						<option value="a01">桃園市</option>
						<option value="a02">台北市</option>
				</select></td>
			</tr>

			<tr>
				<td>下車地點:</td>

				<td><select name="endLoc">
						<option value="a01">桃園市</option>
						<option value="a02">台北市</option>
				</select></td>
			</tr>

			<tr>
				<td>上限人數</td>
				<td><select id="LowerLimit" onchange="pvalue(this.value);">
						<%
							for (int a = 2; a < 5; a++) {
						%>
						<option name="<%=a%>" value="<%=a%>"><%=a%></option>
						<%
							}
						%>
				</select></td>
			</tr>

			<tr>
				<td>下限人數</td>
				<td><select id="Lower">
						<%
							for (int a = 2; a < 5; a++) {
						%>
						<option name="<%=a%>" value="<%=a%>"><%=a%></option>
						<%
							}
						%>
						<script language="Javascript">
							function pvalue(s) {

								Lower = document.getElementById("Lower");

								Lower.innerHTML = "";

								for (x = s; x < 5; x++) {
									Lower.innerHTML = Lower.innerHTML
											+ '<option name="'+x+'" value="'+x+'">'
											+ x + '</option>';

								}

							}
						</script></td>
			</tr>


			<tr>
				<td>上車日期</td>

				<td><input type="file" name="photo" size="25"
					value="<%=(groupBandVO == null) ? "" : groupBandVO.getPhoto()%>" />
				</td>
			</tr>
			<td>備註:</td>

			<td><textarea name="note" id="note" rows="3" cols="50">
					<%=(groupBandVO == null) ? "" : groupBandVO.getNote()%> </textarea></td>
			</tr>
			<tr>
				<td>隱私設定:</td>

				<td><input type="checkbox" value="1" name="privates">隱私權<br>
				</td>
			</tr>


			<tr>
				<td><input type="hidden" name="action" value="insert" /> <input
					type="submit" value="發起揪團" /></td>
				<td><input type="reset" value="清除揪團" /></td>

			</tr>
		</table>
	</form>
</body>
</html>