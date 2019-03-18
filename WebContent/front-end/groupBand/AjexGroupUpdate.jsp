<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.groupMem.model.*"%>
    <%@ page import="com.member.model.*"%>
<% System.out.println("有獨到嗎++++++++++++++++++++++++++++");
String memID = request.getParameter("memid");
String groupID = request.getParameter("groupID");
GroupMemService groupMemService =new GroupMemService();
GroupMemVO groupMemVO= groupMemService.findone_memid__GROUP_ID_MEM_ID(groupID, memID);
MemberService memberService = new MemberService ();

	groupMemService.update_State(groupID, memID,0);

%> 
