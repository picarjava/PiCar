<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.groupMem.model.*"%>
    <%@ page import="com.member.model.*"%>
    <%@ page import="com.groupBand.model.*"%>
    <!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">    
    </head>
    <body>
    
<% request.setCharacterEncoding("UTF-8"); 
response.setContentType("text/html; charset=UTF-8");
System.out.println("有獨到嗎");
String groupID = request.getParameter("groupID");
String room = request.getParameter("room");
String roomMem;
GroupBandService groupBandService = new GroupBandService();
roomMem= groupBandService.getOneContentGroupID(groupID) + room;
groupBandService.UPDATE_CONTENT__GROUP_ID(groupID,roomMem);
System.out.println(roomMem);
%> 
</body>
</html>