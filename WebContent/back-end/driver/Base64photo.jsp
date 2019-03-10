<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.driver.model.*" %>
<%@ page import="com.member.model.*"%>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<td >
						      <c:set var="licence" value="${driverVO.licence}" />
						      <%
						      byte[] licence = (byte[])pageContext.getAttribute("licence");
						      String encodeImg1 = null;
						      if(licence!=null){
						    	  encodeImg1 = Base64.encode(licence);%>
						    	  <img src="data:image/jpg;base64,<%=encodeImg1 %>" id="img1">
						      <% }%>
						      </td>						      
<%-- 						      <td>${driverVO.criminal}</td> --%>
						      <td>
						      <c:set var="criminal" value="${driverVO.criminal}" />
						      <%
						      byte[] criminal = (byte[])pageContext.getAttribute("criminal");
						      String encodeImg2 = null;
						      if(criminal!=null){
						    	  encodeImg2 = Base64.encode(criminal);%>
						    	  <img src="data:image/jpg;base64,<%=encodeImg2 %>" id="img2">
						      <% }%>
						      </td>
<%-- 						      <td>${driverVO.trafficRecord}</td> --%>
						      <td>
						      <c:set var="trafficRecord" value="${driverVO.trafficRecord}" />
						      <%
						      byte[] trafficRecord = (byte[])pageContext.getAttribute("trafficRecord");
						      String encodeImg3 = null;
						      if(trafficRecord!=null){
						    	  encodeImg3 = Base64.encode(trafficRecord);%>
						    	  <img src="data:image/jpg;base64,<%=encodeImg3 %>" id="img3">
						      <% }%>
						      </td>
<%-- 						      <td>${driverVO.idNum}</td> --%>
						      <td>
						      <c:set var="idNum" value="${driverVO.idNum}" />
						      <%
						      byte[] idNum = (byte[])pageContext.getAttribute("idNum");
						      String encodeImg4 = null;
						      if(idNum!=null){
						    	  encodeImg4 = Base64.encode(idNum);%>
						    	  <img src="data:image/jpg;base64,<%=encodeImg4 %>" id="img4">
						      <% }%>
						      </td>
<%-- 						      <td>${driverVO.photo}</td> --%>
                              <td>
						      <c:set var="photo" value="${driverVO.photo}" />
						      <%
						      byte[] photo = (byte[])pageContext.getAttribute("photo");
						      String encodeImg5 = null;
						      if(photo!=null){
						    	  encodeImg5 = Base64.encode(photo);%>
						    	  <img src="data:image/jpg;base64,<%=encodeImg5 %>" id="img5">
						      <% }%>
						      </td>
<!-- 網址驗證						       -->
						      <!-- 	<script> -->
// 	$(document).ready(function() {
// 		 $('#sub').submit();
// 	}); 
<!-- 	</script> -->
						      
</body>
</html>