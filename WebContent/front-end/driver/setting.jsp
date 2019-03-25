<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page  import="com.member.model.*" %>
<%@ page  import="com.driver.model.*" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
    <%-- <jsp:include page="/front-end/HomeMember/HeadMember.jsp" />     --%>
    <meta charset="UTF-8">
    <title>司機喜好設定</title>
    <jsp:include page="/front-end/HomeDriver/Header.jsp" />
    <%-- <jsp:include page="/regna-master/head.jsp" /> --%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- CSS -->
    <style>

input{border:0;
  background-color:#3fffbf	;
  color:#fff;
  border-radius:10px;
  cursor:pointer;}

input:hover{
  color:#3fffbf;
  background-color:#fff;
  border:2px #3fffbf solid;
}

table{
width:50%;
height:80%;
/* margin- */
padding:20px;
color:black;
font-weight:bold;
line-height:60px;
text-aligh:justify;
position:relative;
top:70px;
left:30%;
background-color:white;
border-style:dashed;
border-color:gray;
}

</style>
</head>
<style>
</style>

<body>
    <!-- 登入功能串接 ，將VOmemID指定給 memID-->
    <%
MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
String memID=memberVO.getMemID();
session.setAttribute("memID",memID);
DriverService driSrc = new DriverService();
DriverVO driverVO  = driSrc.getOneDriverBymemID(memberVO.getMemID());
%>
    <h3>司機喜好設定</h3>
    <%-- 錯誤表列 --%>
    <c:if test="${not empty errorMsgs}">
        <font style="color:red">請修正以下錯誤:</font>
        <ul>
            <c:forEach var="message" items="${errorMsgs}">
                <li style="color:red">${message}</li>
            </c:forEach>
        </ul>
    </c:if>
    <form method="post" action="driver.do" name="form1" enctype="multipart/form-data">
        <a href="<%=request.getServletContext().getContextPath()%>/front-end/driver/homeDriverDataManagment.jsp">回主頁面</a>
        <table>
            <tr>
                <td>會員編號：</td>
                <td>
                    <%= memberVO.getMemID() %>
                </td>
            </tr>
            <tr>
                <td>司機編號：</td>
                <td>${driverVO.driverID}</td>
            </tr>
            <tr>
                <td>會員名字：</td>
                <td>
                    <%= memberVO.getName() %>
                </td>
            </tr>
            <tr>
                <td>寵物：</td>
                <td><select name="pet">
                        <option value="1" ${(driverVO.pet=='1' )?'selected':'' }>喜歡寵物
                        <option value="0" ${(driverVO.pet=='0' )?'selected':'' }>不喜歡寵物
                    </select></td>
            </tr>
            <tr>
                <td>抽菸：</td>
                <td><select name="smoke">
                        <option value="1" ${(driverVO.smoke=='1' )?'selected':'' }>抽菸
                        <option value="0" ${(driverVO.smoke=='0' )?'selected':'' }>不抽菸
                    </select></td>
            </tr>
            <tr>
                <td>嬰兒座椅：</td>
                <td><select name="babySeat">
                        <option value="1" ${(driverVO.babySeat=='1' )?'selected':'' }>需要
                        <option value="0" ${(driverVO.babySeat=='0' )?'selected':'' }>不需要
                    </select></td>
            </tr>
            <tr>
                <td>共享車子</td>
                <td><select name="sharedCar">
                        <option value="1" ${(driverVO.sharedCar=='1' )?'selected':'' }>需要
                        <option value="0" ${(driverVO.sharedCar=='0' )?'selected':'' }>不需要
                    </select></td>
            </tr>
                    <tr><td COLSPAN=2 >
        <input type="hidden" name="driverID" value="${driverVO.driverID}">
        <input type="hidden" name="action" value="Update_Hobby">
        <input type="submit" value="更改送出">
        </td></tr>
        </table>
    </form>
    <jsp:include page="/regna-master/body.jsp" />
<jsp:include page="/front-end/HomeDriver/Footer.jsp" />
</body>
</html>