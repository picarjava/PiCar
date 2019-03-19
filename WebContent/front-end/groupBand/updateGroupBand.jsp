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
				<h3>揪團資料修改 -updateGroupBand.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="listAllGroupBand.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>
	<h3>修改揪團資料</h3>

	<form
		action="<%=request.getServletContext().getContextPath()%>/GroupBand"
		method="POST" enctype="multipart/form-data" name="form1">

		<table>



			<tr>
				<td>揪團類別</td>
				<td><select name="groupType">
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
				</select></td>
			</tr>


			<tr>
				<td>團名</td>
				<td><input type="TEXT" name="groupName" size="25"
					value="<%=(groupBandVO == null) ? "" : groupBandVO.getGroupName()%>" /></td>
				<td></td>
			</tr>
			<tr>
				<td>簡介:</td>

				<td><textarea name="introduction" id="note" rows="3" cols="50"><%=(groupBandVO == null) ? "好吃嗎".trim() : groupBandVO.getIntroduction().trim()%></textarea></td>
				<td></td>
			</tr>
			<tr>

				<td>備註:</td>

				<td><textarea name="note" id="note" rows="3" cols="50"><%=(groupBandVO == null) ? "".trim() : groupBandVO.getNote().trim()%></textarea></td>
				<td></td>
			</tr>
			<tr>
				<td>揪團圖片</td>

				<td><img id="preview_img"
					src="/PiCar/GroupBand?groupID=${GroupBandVO.groupID}" width="100px"
					height="100px"> <img id="preview_progressbarTW_img" src="#"
					width="100px" height="100px" style="display: none" /> <label
					for="progressbarTWInput" class="custom-file-upload"> <i
						class="fas fa-file-import"></i> 上傳圖片
				</label> <input id="progressbarTWInput" type="file" name="photo" size="25"
					value="<%=groupBandVO.getPhoto()%>" /></td>

			</tr>









			<tr>
				<td><input type="hidden" name="action" value="update" /> <input
					type="hidden" name="groupID" value="<%=groupBandVO.getGroupID()%>">
					<input type="hidden" name="LaunchTime"
					value="<%=groupBandVO.getLaunchTime()%>"> <input
					type="submit" value="更改揪團" /></td>
				<td><input type="reset" value="清除揪團" /></td>

			</tr>
		</table>
	</form>
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
</html>