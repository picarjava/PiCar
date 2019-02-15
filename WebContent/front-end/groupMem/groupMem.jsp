<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.groupMem.model.*" %>
    <%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% GroupMemDAO DAO =new GroupMemDAO();
//增
//GroupMemVO groupMemVO1 =new GroupMemVO();
//groupMemVO1.setGroupID("G002");
//groupMemVO1.setMemID("M002");
//groupMemVO1.setState(0);
//DAO.insert(groupMemVO1);
//System.out.println(groupMemVO1);


//改
//GroupMemVO groupMemVO1 =new GroupMemVO();
//groupMemVO1.setGroupID("G001");
//groupMemVO1.setMemID("M002");
//groupMemVO1.setState(2);
//DAO.update(groupMemVO1);
//System.out.println(groupMemVO1);


//刪除
//DAO.delete("G001");	
//System.out.println("成功拉");	


//查一
//GroupMemVO groupMemVO = DAO.findByPrimaryKey("G002");
//System.out.print(groupMemVO.getGroupID() + ",");
//System.out.print(groupMemVO.getMemID() + ",");
//System.out.print(groupMemVO.getState() + ",");
//System.out.println("成功拉");


//查全
List<GroupMemVO> list =DAO.getAll();
for (GroupMemVO groupMemVO : list) {
	System.out.print(groupMemVO.getGroupID() + ",");
	System.out.print(groupMemVO.getMemID() + ",");
	System.out.print(groupMemVO.getState() + ",");
	System.out.println("成功拉");
}
%>

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	%>
</body>
</html>