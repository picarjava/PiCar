<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.groupMem.model.*"%>
    <%@ page import="com.groupReport.model.*"%>
<% System.out.println("有獨到嗎");
	String groupID = request.getParameter("groupID");
	String memID = request.getParameter("memID");
	String note = request.getParameter("note");
	System.out.println(" groupID"+groupID+" memID"+memID+" note"+note);
	GroupReportService groupReportService = new GroupReportService();
	groupReportService.insertGroupStmt(memID, groupID, note);
%> 
