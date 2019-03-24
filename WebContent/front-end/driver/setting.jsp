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
    <%-- <jsp:include page="/regna-master/head.jsp" /> --%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,400">
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Droid+Sans">
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Lobster">
    <link rel="stylesheet" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/assets/prettyPhoto/css/prettyPhoto.css">
    <link rel="stylesheet" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/assets/css/flexslider.css">
    <link rel="stylesheet" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/assets/css/font-awesome.css">
    <link rel="stylesheet" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/assets/css/style.css">
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">
    <style>
        .offset1 {
        margin-top: 110px;
    }
    
.navbar .nav.pull-right{
    margin-right: 100px;

}
.widthes{
width: 100px;

}
.header .navbar-inner {
  left: 50px;
    right: 50px;
          box-shadow: 4px 4px 20px 0px rgba(20%,20%,20%,0.5);
}

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
    <!-- Header -->
    <div class="container">
        <div class="header row">
            <div class="span12">
                <div class="navbar">
                    <div class="navbar-inner">
                        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </a>
                        <h1>
                            <a class="brand" href="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/index.jsp">Andia - a super cool design agency...</a>
                        </h1>
                        <div class="nav-collapse collapse">
                            <ul class="nav pull-right">
                                <li class="current-page widthes">
                                    <a href="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/index.jsp"><i class="icon-home"></i><br />司機首頁</a>
                                </li>
                                <li class="widthes">
                                    <a href="<%=request.getServletContext().getContextPath()%>/front-end/singleOrder/listAllfutureDriverTask.jsp"><i class="icon-camera"></i><br />未來訂單</a>
                                </li>
                                <li class="widthes">
                                    <a href="<%=request.getServletContext().getContextPath()%>/front-end/driver/homeDriverDataManagment.jsp"><i class="icon-camera"></i><br />司機資料</a>
                                </li>
                                <li class="widthes">
                                    <a href="<%=request.getServletContext().getContextPath()%>/front-end/driver/setting.jsp"><i class="icon-camera"></i><br />喜好設定</a>
                                </li>
                                <li class="widthes">
                                    <a href="<%=request.getServletContext().getContextPath()%>/front-end/singleOrder/listPastSingleDriverTask.jsp"><i class="icon-camera"></i><br />單人歷史</a>
                                </li>
                                <li class="widthes">
                                    <a href="<%=request.getServletContext().getContextPath()%>/front-end/groupOrder/listPastGroupDriverTask.jsp"><i class="icon-camera"></i><br />揪團歷史</a>
                                </li>
                                <li class="widthes">
                                    <a href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/index.jsp"><i class="icon-camera"></i><br />會員首頁</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
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
    <!-- Javascript -->
    <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/assets/js/jquery-1.8.2.min.js"></script>
    <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/assets/js/jquery.flexslider.js"></script>
    <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/assets/js/jquery.tweet.js"></script>
    <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/assets/js/jflickrfeed.js"></script>
    <script src="http://maps.google.com/maps/api/js?sensor=true"></script>
    <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/assets/js/jquery.ui.map.min.js"></script>
    <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/assets/js/jquery.quicksand.js"></script>
    <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/assets/prettyPhoto/js/jquery.prettyPhoto.js"></script>
    <script src="<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/<%=request.getServletContext().getContextPath()%>/front-end/HomeDriver/assets/js/scripts.js"></script>
</body>

</html>