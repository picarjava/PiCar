<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.location.model.*"%>
<%@ page import="java.util.List"%>
<%
		MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
		LocationVO locationVO = (LocationVO)session.getAttribute("locationVO");
		LocationService lSvc = new LocationService();
		List<LocationVO> list = lSvc.getAll(memberVO.getMemID());
		pageContext.setAttribute("list", list);
		
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>update location</title>
</head>
<body>
    <c:if test="${not empty errorMsgs}">
        <h3>錯誤</h3>
        <c:forEach var="errorMsg" items="${errorMsgs}">
            <p>${errorMsg}</p>
        </c:forEach>
    </c:if>
    <form action="<%=application.getContextPath()%>/location" method="POST">
                會員ID ${memberVO.name}
                ${memberVO.memID} 您好<br>
       <p>請輸入您的常用地點</p>
       <input type="hidden" name="memID" value="${memberVO.memID}"/>
       <input type="text" name="location" value="${locationVO.location}"/>
       <input type="hidden" name="action" value="insert"/>
<%--        <label>地點</label><input type="text" name="newLocation" value="${locationVO.location}"/><br> --%>
       <input type="submit" value="新增"/>
    </form>
    <br>
    <br>
    <br>
    <br>
    <jsp:useBean id="service" class="com.location.model.LocationService"/>
        <c:forEach var="location" items="${list}">
        <tr>
            <td>${location.memID}</td>
            <td>${location.location}</td>
            <td>
                <form action="<%=application.getContextPath()%>/location" method="POST">
                    <input type="hidden" name="memID" value="${location.memID}"/>
                    <input type="hidden" name="location" value="${location.location}"/>
                    <input type="hidden" name="action" value="delete"/>
                    <input type="submit" value="刪除"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    
    
    
</body>
</html>