
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.JsonArray"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.groupMem.model.*"%>
    <%@ page import="com.member.model.*"%>
    <%@ page import="java.util.*"%>


<% 
String memID = request.getParameter("memid");
String groupID = request.getParameter("groupID");
GroupMemService groupMemService =new GroupMemService();
List<GroupMemVO> groupMemlist =new ArrayList<GroupMemVO>();
groupMemlist= groupMemService.getAllgroupID(groupID,1);
MemberService memberService = new MemberService();
MemberVO memberVO = new MemberVO();
JsonArray ajj = new JsonArray();
JsonObject jObj = null;
for (GroupMemVO Memlist:groupMemlist){
	
	memberVO = memberService.getOneMember(Memlist.getMemID());
	jObj = new JsonObject();
	jObj.addProperty("name",memberVO.getName());
	ajj.add(jObj);
}
out.println(ajj.toString());
%> 