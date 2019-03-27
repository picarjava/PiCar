<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.sql.Timestamp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Date"%>
<%@ page import="com.singleOrder.model.*"%>
<%@ page import="com.admin.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.groupOrder.model.*"%>
<%@ page import="com.singleOrder.controller.*"%>
<!-- 登入功能串接 ，將VOadminID指定給 adminID-->
<%@ page import="com.admin.model.*"%>
<%AdminVO adminVO=(AdminVO)session.getAttribute("adminVO");
String adminID=adminVO.getAdminID();
session.setAttribute("adminID",adminID);
getServletContext().setAttribute("conAdminID",adminID);
// System.out.println(adminID);
%>
<!-- 個人訂單 -->
<% SingleOrderService service = new SingleOrderService();
   List<SingleOrderVO> singleOrderlist = service.getAll();
   request.setAttribute("singleOrderlist", singleOrderlist);
   
   List<String> AllDelay= new SingleOrderService().getAllDelay();
   session.setAttribute("AllDelay",AllDelay);
   System.out.println((new SingleOrderService()).getAllDelay()); 
%>
<!-- 揪團訂單 -->
<% GroupOrderService groupOrderSvc=new GroupOrderService();
   List<GroupOrderVO> groupOrderlist=groupOrderSvc.getAll();
   request.setAttribute("groupOrderlist", groupOrderlist);
%>
<% MemberDAO memberdao = new MemberDAO();
%>
<%-- <c:if test="${singleOrder.driverID eq driverID && singleOrder.state !=0&& singleOrder.state !=1 && singleOrder.state != 4 && singleOrder.state !=6}"> --%>
<jsp:useBean id="groupBandSvc" scope="page" class="com.groupBand.model.GroupBandService" />
<jsp:useBean id="groupBandVO" scope="page" class="com.groupBand.model.GroupBandVO" />
<jsp:useBean id="singleOrderVO" scope="page" class="com.singleOrder.model.SingleOrderVO" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>訂單管理頁面</title>
    <jsp:include page="/back-end/head_back.jsp" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <style>
/*         table, tr, td, th { */
/*     background-color: white; */
/*     border: 1px solid #aaa; */
/*     text-align: center; */
/*     padding: 5px; */
/*     text-align: center; */
/*     font-family: 'Microsoft JhengHei', 'Fira Code', 'Source Code Pro', */
/*         'Noto Sans CJK SC', monospace; */
/* } */

/* table { */
/*     width: 100%; */
/* } */
   table{
   	border-collapse: collapse;
   	width: 500px; 	
/*    	/*自動斷行*/英文*/
/*    	word-wrap: break-word; */
/*    	table-layout: fixed; */
   }	

.col-9 {
    margin-top: -15px;
    margin-left: -200px;
    margin-bottom: 1rem;
}

#error {
    margin-left: 20px;
}

#s3 {
    width: 186px;
}
.avg{
width:800px;
}
#timer {
margin-top: 3%;
margin-left: 3%;
font-size: 200%;

}
</style>
        <script language="javascript">  
            setInterval("timer.innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);   
        </script>
    <jsp:include page="/back-end/btnCss.jsp" />
</head>

<body onload="connect();" onunload="disconnect();">
<div id="timer"></div> 
    <c:set var="contextPath" value="${singleOrder.memID}" />
    <div class="wrapper ">
        <jsp:include page="/back-end/kidBodyLeft.jsp" />
<%--         <jsp:include page="/back-end/sidebar.jsp" /> --%>
        <div class="main-panel">
            <div class="content">
                <div class="container-fluid">
                    <div class="container-fluid">
                        <div class="col-9">
                            <!-- 錯誤列表開始 -->
                            <%List errorMsgs=(List<String>)request.getAttribute("errorMsgs");%>
                            <c:if test="${not empty errorMsgs}">
                                <ul class="list-group">
                                    <li class="list-group-item active">Opps!錯誤訊息回報</li>
                                    <c:forEach var="massage" items="${errorMsgs}">
                                        <li class="list-group-item">${massage}</li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                            <!-- 錯誤列表結束 -->
                            <div class="container wow fadeInUp">
                                <div class="section-header">
                                    <h3 class="section-title">訂單管理</h3>
                                    <p>管理員${adminID}最新即時推播內容:</p>
                                    <p id="statusOutput"></p>
                                </div>
                            </div>
                            <table class="table">
                                <thead class="thead-dark" class="avg">
                                    <tr>
                                    <tr></tr>
                                    <tr>
                                        <th scope="col" class="avg">訂單編號 </th>
                                        <th scope="col" class="avg">乘車時間 </th>
                                        <th scope="col" class="avg">訂單種類 </th>
                                        <th scope="col" class="avg">乘客編號 </th>
                                        <th scope="col" class="avg">乘客名稱 </th>
                                        <th scope="col" class="avg">乘客電話 </th>
                                        <th scope="col" class="avg">乘車地點 </th>
                                        <th scope="col" class="avg">乘車目的地 </th>
                                        <th scope="col" class="avg">訂單總金額 </th>
                                        <th scope="col" class="avg">訂單狀態 </th>
                                        <th scope="col" class="avg">重新叫車</th>
                                        <th scope="col" class="avg">退款</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="singleOrder" items="${singleOrderlist}">
                                        <!-- 逾期訂單:篩選狀態碼為 6:逾期未處理-->
                                        <c:if test="${singleOrder.state eq 6}">
                                            <tr>
                                                <th scope="row">${singleOrder.orderID}</th>
                                                <td>
                                                    <fmt:formatDate type="both" value="${singleOrder.startTime}" pattern="yyyy-MM-dd mm:ss" />
                                                </td>
                                                <td>
                                                    <c:forEach var="orderType" items="${orderTypeMap}">
                                                        ${orderType.key eq singleOrder.orderType ? orderType.value: ""}
                                                    </c:forEach>
                                                </td>
                                                <td>
                                                    <c:set var="orderMem" value="${singleOrder.memID}" />
                                                    <!--                                 預設當前頁面  -->
                                                    <% 
                                  String orderMem = (String)pageContext.getAttribute("orderMem");
//                                SingleOrderVO singleOrder = (SingleOrderVO)pageContext.getAttribute("orderMem");//不需要
                                  System.out.println("-------------------------");
//                                List<SingleOrderVO> singleOrderlist = service.getAll();
                                  MemberVO members = memberdao.findByPrimaryKey(orderMem);
//                                System.out.print(members.getName());
                                  %>
                                                    <%= members.getMemID() %>
                                                </td>
                                                <td>
                                                    <%= members.getName() %>
                                                </td>
                                                <td>
                                                    <%= members.getPhone() %>
                                                </td>
                                                <td>${singleOrder.startLoc}</td>
                                                <td>${singleOrder.endLoc}</td>
                                                <td>${singleOrder.totalAmount}</td>
                                                <td>
                                                    <c:forEach var="state" items="${stateMap}">
                                                        ${state.key eq singleOrder.state ? state.value: ""}
                                                    </c:forEach>
                                                </td>
                                                <td><button class="call" type="submit">叫車</button>
                                                </td>
                                                <div>
                                                <td>
                                                    <button class="repay" type="submit">退款</button>
                                                </td>
                                                </div>
                                                <!--                                  <td> -->
                                                <%--                                  <Form METHOD="post" ACTION="<%=request.getContextPath()%>/singleOrder" > --%>
                                                <!--                                    <div class="text-center"><button type="submit" class="btn btn-light">取消</button> -->
                                                <!--                                        /*放隱藏的標籤，讓Controller抓到參數進行操作*/ -->
                                                <!--                                        <input type="hidden" name="action" value="DELETE"> -->
                                                <%--                                        <input type="hidden" name="orderID" value="${singleOrder.orderID}"> --%>
                                                <!--                                     </div> -->
                                                <!--                                  </Form> -->
                                                <!--                                  </td> -->
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <jsp:include page="/back-end/kidFooter.jsp" />
            </div>
        </div>
    </div>
</body>
<script>

$(".call").click(function(e){
// 	$('#call').attr('disabled', true);
	e.target.disabled = true;
	debugger;
	  window.alert("已幫您重新叫車");
	});
	
$(".repay").click(function(){
	e.target.disabled = true;
	debugger;
	window.alert("已幫您退款");

});
//if ("${AllDelay}" != null) {
//window.alert("逾時訂單" + "${AllDelay}");
//debugger;
//}
</script>

</html>