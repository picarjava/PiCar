<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.driverReport.model.*"%>

<%
	DriverReportService driverReportSvc = new DriverReportService();
	List<DriverReportVO> list = driverReportSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="singleOrderSvc" scope="page" class="com.singleOrder.model.SingleOrderService" />
<jsp:useBean id="groupOrderSvc" scope="page" class="com.groupOrder.model.GroupOrderService" />

<!doctype html>
<html lang="zh">
<head>
<jsp:include page="/back-end/head_back.jsp" />
<title>PICAR BACK-END</title>
<style>

div.content {
	margin-left:-5%;
}

table, tr, td, th {
    background-color: white;
    border: 1px solid #aaa;
    text-align: center;
    padding: 5px;
    text-align: center;
    margin:auto;
}

input[type="submit"] {
    padding:10px 20px; 
    background:#DDDDDD; 
    border:0 none;
    cursor:pointer;
    -webkit-border-radius: 10px;
    border-radius: 5px; 
    font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro', 'Noto Sans CJK SC', monospace;
    font-size: 16px;
    color:#444444;
    position: relative;
	transition: 0.4s;

}

button[type="submit"] {
	margin-top:5px;
}

button[type="submit"] {
	width: 150px;
	height: 40px;
    padding: 10px 20px;
    background: #DDDDDD;
    border: 0 none;
    cursor: pointer;
    -webkit-border-radius: 10px;
    border-radius: 5px;
    font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro', 'Noto Sans CJK SC', monospace;
    font-size: 16px;
    color: #444444;
    position: relative;
    transition: 0.4s;
    margin-top: 20px;
}

input[type="submit"]:hover
{
    background:rgb(248, 197, 68);
}

button[type="submit"]:hover
{
    background:rgb(248, 197, 68);
}

select, #i1 {
	border-radius: 5px; 
}

ul, menu, dir {
    display: block;
    list-style-type: disc;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    padding-inline-start: 0px;
}

</style>

</head>

<body>
	<div class="wrapper ">
		<jsp:include page="/back-end/kidBodyLeft.jsp" />
		<div class=""style="margin-top: 10%;">
			<div class="">	
					<!-- your content here -->
					<div class="container-fluid">
						<div class="container wow fadeInUp">

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
									<a>搜尋檢舉司機單號</a> <input type="text" placeholder="(例如:DR001)" name="dreportID" id="i1"> 
									<input type="hidden" name="action" value="getOne_For_Display"> 
									<input type="submit" value="送出">
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
<!-- 								<ul> -->
<!-- 									<li><a href='addDriverReport.jsp'>(測試)新增檢舉司機單</a></li> -->
<!-- 								</ul> -->
							</ul>


						</div>
						<div class="container wow fadeInUp" style="visibility: visible;animation-name: fadeInUp;">
							<table class="table">
								<thead class="thead-dark">
								<tr>
									<th>檢舉司機單號</th>
									<th>司機編號</th>
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
									<td>
										<c:forEach var="singleOrederVO" items="${singleOrderSvc.all}">
						                    <c:if test="${driverReportVO.orderID==singleOrederVO.orderID}">
							                    	${singleOrederVO.driverID}
						                    </c:if>
						                </c:forEach>   
						                
						                <c:forEach var="groupOrderVO" items="${groupOrderSvc.all}">
						                    <c:if test="${driverReportVO.orderID==groupOrderVO.gorderID}">
							                    ${groupOrderVO.driverID}
						                    </c:if>
						                </c:forEach>    
									</td>
<%-- 									<td>${driverReportVO.adminID}</td> --%>




									<td>${driverReportVO.orderID}</td>
									<td>${driverReportVO.content}</td>
									<td>${driverReportVO.time}</td>
									<td>
									
						      <c:if test="${driverReportVO.state == 0}">
						      <Form METHOD="post" ACTION="<%=request.getServletContext().getContextPath()%>/back-end/driverReport/driverReport.do" >
							    <div class="text-center">
							    <button type="submit">未處理</button>
							    <input type="hidden" name="dreportID" value="${driverReportVO.dreportID}">
	                			<input type="hidden" name="action" value="getOne_For_Update">
							     </div>
							  </Form>
						      </c:if>
						      		      
						      <c:if test="${driverReportVO.state == 1}">已處理</c:if>									
										
<%-- 										<c:choose> --%>
<%-- 											<c:when test="${driverReportVO.state=='1'}">已處理</c:when> --%>
<%-- 											<c:when test="${driverReportVO.state=='0'}">未處理</c:when> --%>
<%-- 										</c:choose></td> --%>


<!-- 									<td> -->
<!-- 										<FORM METHOD="post" -->
<%-- 											ACTION="<%=request.getContextPath()%>/back-end/driverReport/driverReport.do" --%>
<!-- 											style="margin-bottom: 0px;"> -->
<!-- 											<input type="submit" value="修改"> 
												<input type="hidden" name="dreportID" value="${driverReportVO.dreportID}"> --%>
<!-- 											<input type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 										</FORM> -->
<!-- 									</td> -->
									<td>								
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/driverReport/driverReport.do" style="margin-bottom: 0px;">
											<input type="submit" value="刪除"> 
											<input type="hidden" name="dreportID" value="${driverReportVO.dreportID}">
											<input type="hidden" name="action" value="delete">
										</FORM>
									</td>
								</tr>
							</c:forEach>
							</thead>
						</table>
					</div>
						<%@ include file="page2.file"%>
                   </div>
                
            </div>
             <jsp:include page="/back-end/kidFooter.jsp" />
        </div>
    </div>    

</body>
</html>