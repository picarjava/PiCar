<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.groupReport.model.*"%>
<%@ page import="com.admin.model.*"%>
<%@ page import="com.groupBand.model.*"%>
<%
	GroupReportService groupReportSvc = new GroupReportService();
	List<GroupReportVO> list = groupReportSvc.getAll();
	pageContext.setAttribute("list", list);
	AdminVO adminVO = (AdminVO)session.getAttribute("adminVO");
%>

<%
	GroupBandVO groupBandVO = (GroupBandVO)request.getAttribute("groupBandVO"); 
%>



<!doctype html>
<html lang="zh">
<head>
<title>PICAR BACK-END</title>
<jsp:include page="/back-end/head_back.jsp" />

<style>

div.content {
	width:95%;
	margin-left:-7%;
}

#btn1 {
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

input[type="submit"]:hover
{
    background:rgb(248, 197, 68);
}

#btn1:hover {
	background:rgb(248, 197, 68);
}

select, #i1 {
	border-radius: 5px; 
}

</style>


</head>

<body>
    <div class="wrapper ">
        <jsp:include page="/back-end/kidBodyLeft.jsp" />
        <div class="main-panel">
            <div class="content">
                <div class="container-fluid">
                    <div class="container-fluid">
                        <div class="col-9">
	                        <div>
	                            <%-- 錯誤表列 --%>
	                            <c:if test="${not empty errorMsgs}">
	                                <font style="color: red" id="error">請修正以下錯誤：</font>
	                                <ul>
	                                    <c:forEach var="message" items="${errorMsgs}">
	                                        <li style="color: red">${message}</li>
	                                    </c:forEach>
	                                </ul>
	                            </c:if>
	                        </div>

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
								<form METHOD="post" ACTION="groupReport.do">
									<a>搜尋檢舉揪團單號</a> 
									<input type="text" placeholder="(例如:GR001)" name="greportID" id="i1"> 
									<input type="hidden" name="action" value="getOne_For_Display"> 
									<input type="submit" value="送出">
								</form>
								
								<jsp:useBean id="groupReportSvc1" scope="page" class="com.groupReport.model.GroupReportService" />
								
								<FORM METHOD="post" ACTION="groupReport.do">
									<a>選擇檢舉揪團單號</a> 
									<select size="1" name="greportID" id="s3">
										<c:forEach var="groupReportVO" items="${groupReportSvc1.all}">
											<option value="${groupReportVO.greportID}">${groupReportVO.greportID}
										</c:forEach>
									</select> 
									<input type="hidden" name="action" value="getOne_For_Display">
									<input type="submit" value="送出">
								</FORM>
								
								<ul>
									<li><a href='addGroupReport.jsp'>(測試)新增檢舉揪團單</a></li>
								</ul>
							</ul>


						</div>

						<table id="t1">
							<tr>
								<th>檢舉揪團單號</th>
<!-- 								<th>會員編號</th> -->
								<th>揪團編號</th>
<!-- 								<th>管理員編號</th> -->
								<th>檢舉內容</th>
								<th>檢舉日期</th>
								<th>處理狀態</th>
								<th colspan="3">編輯</th>


							</tr>
							<%@ include file="page1.file"%>
							<c:forEach var="groupReportVO" items="${list}"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

								<tr>
									<td>${groupReportVO.greportID}</td>
<%-- 									<td>${groupReportVO.memID}</td> --%>
									<td>${groupReportVO.groupID}</td>
<%-- 									<td>${groupReportVO.adminID}</td> --%>
									<td>${groupReportVO.content}</td>
									<td>${groupReportVO.time}</td>
									<td>
										<c:choose>
											<c:when test="${groupReportVO.state=='1'}">已處理</c:when>
											<c:when test="${groupReportVO.state=='0'}">未處理</c:when>
										</c:choose></td>
										
									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/back-end/groupReport/FindGroup.do" style="margin-bottom: 0px;">		
											<input type="hidden" name="groupID" value="${groupReportVO.groupID}">
											<input type="hidden" name="action" value="FindOne">
											<input type="submit" value="查看內容"> 
									    </FORM>
									</td>	
									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/back-end/groupReport/groupReport.do" style="margin-bottom: 0px;">
											<input type="submit" value="確認檢舉"> <input type="hidden"
												name="greportID" value="${groupReportVO.greportID}">
											<input type="hidden" name="action" value="getOne_For_Update">
										</FORM>
									</td>
									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/back-end/groupReport/groupReport.do" style="margin-bottom: 0px;">
											<input type="submit" value="退回檢舉"> 
											<input type="hidden" name="greportID" value="${groupReportVO.greportID}">
											<input type="hidden" name="action" value="delete">
									    </FORM>
									</td>							
								</tr>
							</c:forEach>
						</table>
                       <%@ include file="page2.file"%>
                    </div>
                </div>
            </div>
             <jsp:include page="/back-end/kidFooter.jsp" />
        </div>
    </div>    

</body>
</html>