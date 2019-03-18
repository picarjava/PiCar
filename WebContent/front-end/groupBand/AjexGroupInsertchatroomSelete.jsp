<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.JsonArray"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.groupMem.model.*"%>
    <%@ page import="com.member.model.*"%>
    <%@ page import="com.groupBand.model.*"%>
     <%@ page import="java.util.*"%>
  
<% request.setCharacterEncoding("UTF-8"); 
response.setContentType("text/html; charset=UTF-8");
String groupID = request.getParameter("groupID");
String roomMems;
GroupBandService groupBandService = new GroupBandService();
roomMems= groupBandService.getOneContentGroupID(groupID);
JsonObject jObj = new JsonObject();
jObj.addProperty("roomMem",roomMems);
out.println(jObj);
%> 
