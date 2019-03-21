<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.admin.model.*"%>

<!-- 				這支不用session! -->
<%
AdminVO adminVO = (AdminVO) request.getAttribute("adminVO"); 
%>

<style>
#btn9 {
    background-color: #d3d3d3;
    font-size: 16px;
}
</style>


<html>
<head>
<jsp:include page="/back-end/head_back.jsp" />
<title>PICAR BACK-END</title>
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

					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/admin/admin.do" name="form1">
						<table>
							<tr>
								<td>管理員編號<font color=red></font></td>
								<td><input class="form-control" type="text" size="45" value="<%=adminVO.getAdminID()%>" placeholder="Readonly input here..." readonly></td>
							</tr>
							<tr>
								<td>管理員姓名</td>
								<td><input class="form-control" type="TEXT" name="adminName" size="45" value="<%=adminVO.getAdminName()%>" placeholder="Readonly input here..." readonly /></td>
							</tr>
							<tr>
								<td>電子信箱</td>
								<td><input class="form-control" type="TEXT" name="email" size="45" value="<%=adminVO.getEmail()%>" placeholder="Readonly input here..." readonly /></td>
							</tr>
								<input type="hidden" name="password" size="45" value="<%=adminVO.getPassword()%>" />					
							<tr>
								<td>在職狀態</td>
								<td><select name="isEmp" class="form-control form-control-lg" id="s2">
										<option value="1" ${(adminVO.isEmp=='1') ? 'selected' : ''}>在職
										<option value="0" ${(adminVO.isEmp=='0') ? 'selected' : ''}>離職
								</select></td>
							</tr>
						</table>	
																
                        <br> <input type="hidden" name="action" value="update"> 
							 <input type="hidden" name="adminID" value="<%=adminVO.getAdminID()%>"> 
							 <input type="submit" value="送出修改" id="btn9">
					</FORM>
                    </div>
                </div>
                <jsp:include page="/back-end/kidFooter.jsp" />
            </div>
        </div>
    </div> 								
					
</body>

</html>