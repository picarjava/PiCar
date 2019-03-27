<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.groupOrder.model.GroupOrderVO"%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/front-end/HomeMember/HeadMember.jsp" />
    <meta charset="UTF-8"/>
    <title>司機評價</title>
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/bootstrap-3.3.4.css">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link href="<%=application.getContextPath()%>/regna-master/css/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
    <!--suppress JSUnresolvedLibraryURL -->
    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <script src="<%=application.getContextPath()%>/regna-master/js/star-rating.js" type="text/javascript"></script>

<%GroupOrderVO groupOrderVO=(GroupOrderVO)request.getAttribute("groupOrderVO");%>

<body>
<div class="container">
    <div class="page-header">
        <h2>立即評價司機
            <small>&copy; Picar.com</small>
        </h2>
    </div>

    <form METHOD="post" ACTION="<%=request.getContextPath()%>/GroupOrder">
    <p>訂單編號 :${groupOrderVO.gorderID}</p>
    <p>司機編號 :${groupOrderVO.driverID}</p>
        <input name="rate" id="input-21b" value="" type="text" class="rating" data-min=0 data-max=5 data-step=1 data-size="lg"
               required title="good">
        <div class="clearfix"></div>
        <hr>
        <div class="form-group" style="margin-top:10px">
        <button type="submit" class="btn btn-primary">送出評價</button>
        </div>
        	<!-- 放隱藏的標籤，讓Controller抓到參數進行操作 -->
		<input type="hidden" name="action" value="addRate">
		<input type="hidden" name="orderID" value="${groupOrderVO.gorderID}">
    </form>
</div>
</body>
</html>
