<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.driverReport.model.*"%>
<%
	DriverReportService driverReportSvc = new DriverReportService();
	List<DriverReportVO> list = driverReportSvc.getAll();
	pageContext.setAttribute("list", list);
%>



<!doctype html>
<html lang="zh">
<head>
<jsp:include page="/back-end/head_back.jsp" />
<title>PICAR BACK-END</title>
</head>

<body>
	<div class="wrapper ">
		<jsp:include page="/back-end/kidBodyLeft.jsp" />
		<div class="main-panel">
			<div class="content">
				<div class="container-fluid">
					<!-- your content here -->
					<div class="container-fluid">
						<div class="col-9">

							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red" id="error">請修正以下錯誤：</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>


							<ul id="s1">
								<form METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/driverReport/driverReport.do">
									<a>搜尋檢舉司機單號</a> <input type="text" placeholder="(例如:DR001)"
										name="dreportID"> <input type="hidden" name="action"
										value="getOne_For_Display"> <input type="submit"
										value="送出">
								</form>
								<jsp:useBean id="driverReportSvc1" scope="page"
									class="com.driverReport.model.DriverReportService" />
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/driverReport/driverReport.do">
									<a>選擇檢舉司機單號</a> <select size="1" name="dreportID" id="s3">
										<c:forEach var="driverReportVO"
											items="${driverReportSvc1.all}">
											<option value="${driverReportVO.dreportID}">${driverReportVO.dreportID}
										</c:forEach>
									</select> <input type="hidden" name="action" value="getOne_For_Display">
									<input type="submit" value="送出">
								</FORM>
								<ul>
									<li><a href='addDriverReport.jsp'>(測試)新增檢舉司機單</a></li>
								</ul>
							</ul>


						</div>

						<table id="t1">
							<tr>
								<th>檢舉司機單號</th>
								<th>會員編號</th>
<!-- 								<th>管理員編號</th> -->
								<th>訂單編號</th>
								<th>檢舉內容</th>
								<th>檢舉日期</th>
								<th>處理狀態</th>
								<th colspan="2">編輯</th>


							</tr>
							<%@ include file="page1.file"%>
							<c:forEach var="driverReportVO" items="${list}"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

								<tr>
									<td>${driverReportVO.dreportID}</td>
									<td>${driverReportVO.memID}</td>
<%-- 									<td>${driverReportVO.adminID}</td> --%>
									<td>${driverReportVO.orderID}</td>
									<td>${driverReportVO.content}</td>
									<td>${driverReportVO.time}</td>
									<td><c:choose>
											<c:when test="${driverReportVO.state=='1'}">已處理</c:when>
											<c:when test="${driverReportVO.state=='0'}">未處理</c:when>
										</c:choose></td>


									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/back-end/driverReport/driverReport.do"
											style="margin-bottom: 0px;">
											<input type="submit" value="修改"> <input type="hidden"
												name="dreportID" value="${driverReportVO.dreportID}">
											<input type="hidden" name="action" value="getOne_For_Update">
										</FORM>
									</td>
									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/back-end/driverReport/driverReport.do"
											style="margin-bottom: 0px;">
											<input type="submit" value="刪除"> <input type="hidden"
												name="dreportID" value="${driverReportVO.dreportID}">
											<input type="hidden" name="action" value="delete">
										</FORM>
									</td>
								</tr>
							</c:forEach>
						</table>

						<%@ include file="page2.file"%>

					</div>
				</div>
				<footer class="footer">
					<div class="container-fluid">
						<nav class="float-left"></nav>
						<div class="copyright float-right">
							&copy;
							<script>
              document.write(new Date().getFullYear())
            </script>
							PICAR.
						</div>
						<!-- your footer here -->
					</div>
				</footer>
			</div>
		</div>

		<!-- Optional JavaScript -->
		<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
			integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
			crossorigin="anonymous"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
			integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
			crossorigin="anonymous"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
			integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
			crossorigin="anonymous"></script>
</body>
</html>