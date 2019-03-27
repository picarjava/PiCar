<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.admin.model.*"%>
<%
	AdminService adminSvc = new AdminService();
	AdminVO adminVO = (AdminVO) session.getAttribute("adminVO");
	List<AdminVO> list = adminSvc.getAll();
	session.setAttribute("list", list);
%>


<!doctype html>
<html lang="zh">
<head>
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

#btn1 {
    background-color: #d3d3d3;
    margin-left: 0;
    font-size: 16px;
}

#bb1 {
    background-color: #d3d3d3;
    margin-left: 0;
    font-size: 16px;
}

</style>



<title>PICAR BACK-END</title>
<jsp:include page="/back-end/head_back.jsp" />
<%-- <jsp:include page="/back-end/kidHead.jsp" /> --%>
<meta charset="utf-8">
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
								<form METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/admin/admin.do">
									<a>搜尋管理員</a> 
									<input type="text" placeholder="(例如:A001)" name="adminID" id="i1"> 
									<input type="hidden" name="action" value="getOne_For_Display"> 
									<input type="submit" value="送出">
								</form>
								
								<jsp:useBean id="adminSvc1" scope="page" class="com.admin.model.AdminService" />
								
								<FORM METHOD="post" ACTION="admin.do">
									<a>選擇管理員編號</a> 
									<select size="1" name="adminID" id="s3">
										<c:forEach var="adminVO" items="${adminSvc1.all}">
											<option value="${adminVO.adminID}">${adminVO.adminID}
										</c:forEach>
									</select> 
									<input type="hidden" name="action" value="getOne_For_Display">
									<input type="submit" value="送出">
								</FORM>
								
								<ul>
									<a href="addAdmin.jsp" id="bb1" class="btn btn-secondary active" role="button" aria-pressed="true">新增管理員</a>
								</ul>
							</ul>
						</div>
						<div class="container wow fadeInUp" style="visibility: visible;animation-name: fadeInUp;">
							<table class="table">
								<thead class="thead-dark">
								<tr>
									<th>管理員編號</th>
									<th>管理員姓名</th>
									<th>電子信箱</th>
								<!-- 	<th>密碼</th>-->
									<th>在職狀態</th>
									<th colspan="2">編輯</th>


								</tr>
							<%@ include file="page1.file"%>
							<c:forEach var="adminVO" items="${list}"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

								<tr>
									<td>${adminVO.adminID}</td>
									<td>${adminVO.adminName}</td>
									<td>${adminVO.email}</td>
								<!-- 	<td>${adminVO.password}</td>-->
									<td>
										<c:choose>
											<c:when test="${adminVO.isEmp=='1'}">在職</c:when>
											<c:when test="${adminVO.isEmp=='0'}">離職</c:when>
										</c:choose></td>
									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/back-end/admin/admin.do" style="margin-bottom: 0px;">
											<input type="submit" value="修改"> <input type="hidden"
												name="adminID" value="${adminVO.adminID}">
											<input type="hidden" name="action" value="getOne_For_Update">
										</FORM>
									</td>
<!-- 									<td> -->
<!-- 										<FORM METHOD="post" -->
<%-- 											ACTION="<%=request.getContextPath()%>/back-end/admin/admin.do" style="margin-bottom: 0px;"> --%>
<!-- 											<input type="submit" value="刪除">  -->
<%-- 											<input type="hidden" name="adminID" value="${adminVO.adminID}"> --%>
<!-- 											<input type="hidden" name="action" value="delete"> -->
<!-- 									    </FORM> -->
<!-- 									</td> -->
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