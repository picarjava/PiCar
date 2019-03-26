<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.groupReport.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	GroupReportVO groupReportVO = (GroupReportVO)request.getAttribute("groupReportVO"); 
%>

<html>
<head>
<title>檢舉揪團資料查詢</title>
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

body, h3 {
 	font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro', 'Noto Sans CJK SC', monospace;
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
								<table id="table-1">
									<tr>
										<td>
											<h3>檢舉揪團資料查詢結果</h3>
										</td>
									</tr>
								</table>

								<table>
									<tr>
										<th>檢舉揪團單號</th>
										<th>會員編號</th>
										<th>揪團編號</th>
										<th>管理員編號</th>
										<th>檢舉內容</th>
										<th>檢舉日期</th>
										<th>處理狀態</th>
									</tr>
									<tr>
										<td><%=groupReportVO.getGreportID()%></td>
										<td><%=groupReportVO.getMemID()%></td>
										<td><%=groupReportVO.getGroupID()%></td>
										<td><%=groupReportVO.getAdminID()%></td>
										<td><%=groupReportVO.getContent()%></td>
										<td><%=groupReportVO.getTime()%></td>
										<td><c:choose>
											<c:when test="${driverReportVO.state=='1'}">已處理</c:when>
											<c:when test="${driverReportVO.state=='0'}">未處理</c:when>
											</c:choose>
										</td>
									</tr>
								</table>
							<br>
							<form action="<%=request.getContextPath()%>/back-end/groupReport/greport_select_page.jsp">
								<div class="text-center">
									<input type="submit" value="返回上一頁">
								</div>
							</form>	
                   	 </div>
                </div>
            </div>
             <jsp:include page="/back-end/kidFooter.jsp" />
        </div>
    </div>    						
</div>
</div>
				
											

</body>
</html>