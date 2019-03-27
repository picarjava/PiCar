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
								<form METHOD="post" ACTION="groupReport.do">
									<a>搜尋檢舉揪團單號</a> 
									<input type="text" placeholder="(例如:GR001)" name="greportID" id="i1"> 
									<input type="hidden" name="action" value="getOne_For_Display"> 
									<input type="submit" value="查詢">
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
									<input type="submit" value="查詢">
								</FORM>
								
<!-- 								<ul> -->
<!-- 									<li><a href='addGroupReport.jsp'>(測試)新增檢舉揪團單</a></li> -->
<!-- 								</ul> -->
							</ul>


						</div>
						<div class="container wow fadeInUp" style="visibility: visible;animation-name: fadeInUp;">
							<table class="table">
								<thead class="thead-dark">
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
									
								  <c:if test="${groupReportVO.state == 0}">
							      <Form METHOD="post" ACTION="<%=request.getServletContext().getContextPath()%>/back-end/groupReport/groupReport.do" >
								    <div class="text-center">
								    <button type="submit">確認檢舉</button>
								    <input type="hidden" name="greportID" value="${groupReportVO.greportID}">
		                			<input type="hidden" name="action" value="getOne_For_Update">
								    </div>
								  </Form>
							      </c:if>
							      		      
							      <c:if test="${groupReportVO.state == 1}">已處理</c:if>		
																	
									
<%-- 										<c:choose> --%>
<%-- 											<c:when test="${groupReportVO.state=='1'}">已處理</c:when> --%>
<%-- 											<c:when test="${groupReportVO.state=='0'}">未處理</c:when> --%>
<%-- 										</c:choose></td> --%>
										
									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/back-end/groupReport/FindGroup.do" style="margin-bottom: 0px;">		
											<input type="hidden" name="groupID" value="${groupReportVO.groupID}">
											<input type="hidden" name="action" value="FindOne">
											<input type="submit" value="查看內容"> 
									    </FORM>
									</td>	
<!-- 									<td> -->
<!-- 										<FORM METHOD="post" -->
<%-- 											ACTION="<%=request.getContextPath()%>/back-end/groupReport/groupReport.do" style="margin-bottom: 0px;"> --%>
<!-- 											<input type="submit" value="確認檢舉"> 
												<input type="hidden" name="greportID" value="${groupReportVO.greportID}"> --%>
<!-- 											<input type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 										</FORM> -->
<!-- 									</td> -->
									<td>
									<c:if test="${groupReportVO.state == 0}">
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/groupReport/groupReport.do" style="margin-bottom: 0px;">
											<input type="submit" value="退回檢舉"> 
											<input type="hidden" name="greportID" value="${groupReportVO.greportID}">
											<input type="hidden" name="action" value="delete">
									    </FORM>
									</c:if>     
									<c:if test="${groupReportVO.state == 1}">退回檢舉</c:if>   
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