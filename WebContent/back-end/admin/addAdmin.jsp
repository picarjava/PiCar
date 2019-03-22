<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.admin.model.*"%>

<%
	AdminVO adminVO = (AdminVO) session.getAttribute("adminVO");
%>

<html>
<head>
<title>PICAR BACK-END 新增管理員</title>
<jsp:include page="/back-end/head_back.jsp" />
<meta charset="utf-8">
<title>管理員資料新增 - addAdmin.jsp</title>

</head>
<body bgcolor='white'>
    <div class="wrapper ">
        <jsp:include page="/back-end/kidBodyLeft.jsp" />
        <div class="main-panel">
            <div class="content">
                <div class="container-fluid">
                    <!-- your content here -->
                    <div class="container-fluid">
                        <div class="col-9">

					<h4>新增管理員</h4>

					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/admin/admin.do" name="form1">
								<table>
									<tr>
										<td>管理員姓名</td>
										<td><input type="TEXT" name="adminName" size="45" value="<%= (adminVO==null)? "Kev" : adminVO.getAdminName()%>" /></td>
									</tr>
									<tr>
										<td>電子信箱</td>
										<td><input type="TEXT" name="email" size="45" value="<%= (adminVO==null)? "jyunliou120@gmail.com" : adminVO.getEmail()%>" /></td>
									</tr>
									
									<input type=hidden name="password" size="45" value="<%= (adminVO==null)? "" : adminVO.getPassword()%>" />
									<input type="hidden" name="isEmp" size="45" value="<%= (adminVO==null)? "1" : adminVO.getIsEmp()%>" />
									
								</table>
								<br> 
								<input type="hidden" name="action" value="insert"> 
								<input type="submit" value="送出新增">
							</FORM>
	                  </div>
                </div>
                <jsp:include page="/back-end/kidFooter.jsp" />
            </div>
        </div>
    </div> 
    </div>   
</body>
</html>