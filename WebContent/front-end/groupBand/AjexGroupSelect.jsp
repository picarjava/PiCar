<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.groupMem.model.*"%>
    <%@ page import="com.member.model.*"%>
    <%@ page import="java.util.*"%>
  <script>
    var fiveItems = new Array(5);
<% System.out.println("有獨到嗎");
String memID = request.getParameter("memid");
String groupID = request.getParameter("groupID");
GroupMemService groupMemService =new GroupMemService();
List<GroupMemVO> groupMemlist =new ArrayList<GroupMemVO>();
groupMemlist= groupMemService.getAllgroupID(groupID,1);
MemberService memberService = new MemberService();
MemberVO memberVO = new MemberVO();
int sun=0;
for (GroupMemVO Memlist:groupMemlist){
	
	memberVO = memberService.getOneMember(Memlist.getMemID());	
%>
fiveItems[<%=sun%>]="<%=memberVO.getName()%>";
	<%
	sun++;
}
%> 
return fiveItems;
</script>