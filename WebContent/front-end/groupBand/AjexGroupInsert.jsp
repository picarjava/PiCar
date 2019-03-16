<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.groupMem.model.*"%>
<% String memID ="M004";
String groupID ="G001" ;
GroupMemService groupMemService =new GroupMemService();
GroupMemVO groupMemVO= groupMemService.findone_memid__GROUP_ID_MEM_ID(groupID, memID);
System.out.println("有獨到嗎");
if(groupMemVO.getMemID()==null){
	groupMemService.addGroupMem(groupID, memID,1);	
}else{
	groupMemService.update_State(groupID, memID,1);
}
%> 