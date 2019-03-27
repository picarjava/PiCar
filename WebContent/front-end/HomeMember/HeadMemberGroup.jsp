<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.driver.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html lang="en">

    <head>
    <style>
     #GIbutton {
    font-family: "Poppins", sans-serif;
    border-radius: 50px;
    padding: 5px 22px;
    background: #f5f5f5;
    color: #2dc997;
    font-style: italic;
    text-decoration: none;
    -moz-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    -webkit-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    border-radius: 50px;
    border: 2px solid #2dc997;
    0 1px 25px 0 rgba(0,0,0,.05) inset,: ;
    0 -1px 25px 0 rgba(0,0,0,.05) inset: ;
    -webkit-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
     margin-bottom: 100px;
}
    #GIbutton:hover {
    font-family: "Poppins", sans-serif;
    border-radius: 50px;
       
    padding: 5px 22px;
    background: #2dc997;
    color: #f5f5f5;
    font-style: italic;
    text-decoration: none;
    -moz-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    -webkit-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    border-radius: 50px;
    border: 2px solid #2dc997;
    0 1px 25px 0: ;
    rgba(0,0,0,.05) inset,: ;
    0 -1px 25px 0 rgba(0,0,0,.05) inset: ;
    -webkit-box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
    box-shadow: 0 1px 25px 0 rgba(0,0,0,.05) inset, 0 -1px 25px 0 rgba(0,0,0,.05) inset;
}

#GroupTitles{
	width: 1000px;
}
#GroupAnimation{
    position: absolute;
    top: 40%;
    left: 60%;
    width: 180px;
}
.marrgin{
margin-top:30px;
}
</style>
    </head>
<body>
	<div>
		<img id="GroupTitles" src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/img/Group.png">
		<img id="GroupAnimation" src="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/assets/img/GroupAnimation.png">
	</div>
	<div class="marrgin">
		<a id="GIbutton" href="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/insertGroupBand.jsp">發起揪團</a>
		<a id="GIbutton" href="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/SelectGroupBand.jsp">加入揪團</a>
		<a id="GIbutton" href="<%=request.getServletContext().getContextPath()%>/front-end/groupBand/listAllOneGroupBand.jsp">揪團管理</a>
	</div>

</body>

</html>

