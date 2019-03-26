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
	width:95%;
	margin-left:-8%;
}

#btn1 {
    padding:10px 20px; 
    background:#DDDDDD; 
    border:0 none;
    cursor:pointer;
    -webkit-border-radius: 10px;
    border-radius: 5px; 
/*     font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro', 'Noto Sans CJK SC', monospace; */
    font-family: "FangSong";
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
    font-family: "FangSong";
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



<title>PICAR BACK-END</title>
<jsp:include page="/back-end/head_back.jsp" />
<%-- <jsp:include page="/back-end/kidHead.jsp" /> --%>
<meta charset="utf-8">
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
									<a href="addAdmin.jsp" id="btn1" class="btn btn-secondary active" role="button" aria-pressed="true">新增管理員</a>
								</ul>
							</ul>
						</div>
						
						<table id="t1">
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