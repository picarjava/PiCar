<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.groupBand.model.*" %>
    <%@page import="java.util.List" %>
    
    <%@page import="java.io.ByteArrayOutputStream" %>
<%@page import="java.io.File" %>
<%@page import="java.io.FileInputStream" %>
<%@page import="java.io.IOException" %>
<%@page import="java.sql.Date" %>
<%@page import="java.sql.Timestamp" %>
<%@page import="java.text.ParseException" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<%
GroupBandJDBCDAO DAO =new GroupBandJDBCDAO();

//增加
GroupBandVO groupBandVO =new GroupBandVO();

groupBandVO.setContent("XX");
groupBandVO.setIntroduction("XXX");
groupBandVO.setGroupStatus(1);
groupBandVO.setCurrenTnum(1);
groupBandVO.setUpperLimit(2);
groupBandVO.setLowerLimit(4);
groupBandVO.setGroupName("五月天演唱會");
groupBandVO.setGroupLeader("M001");
groupBandVO.setStartLoc("桃園火車站");
groupBandVO.setEndLoc("中壢火車站");
groupBandVO.setPrivates(1);
groupBandVO.setPhoto(null);
groupBandVO.setGroupType("演唱會");
groupBandVO.setTotalAmout(5000);
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
groupBandVO.setStartTime(new Date(simpleDateFormat.parse("2019-02-14").getTime()));
groupBandVO.setRate(5);
groupBandVO.setNote("無");

DAO.insert(groupBandVO);
System.out.println("成功拉");



%>
</body>
</html>