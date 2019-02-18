<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.singleOrder.model.SingleOrderService"%>
<%@ page import="java.util.List"%>
<%@ page import="com.singleOrder.model.SingleOrderVO"%>
<% SingleOrderService service = new SingleOrderService();
   List<SingleOrderVO> list = service.getAll();
   pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
    <style type="text/css">
        table {
            border-spacing:0;
        }
    </style>
</head>
<body>
    <% int rowNum = 3;
       int whichPage;
       int totalPage = list.size() / rowNum;
       if (list.size() % rowNum != 0)
           totalPage++;
       
       try {
           whichPage = Integer.parseInt(request.getParameter("whichPage"));
           if (whichPage <= 0)
               whichPage = 1;
           else if (whichPage >= totalPage)
               whichPage = totalPage;
       } catch(NumberFormatException e) {
           whichPage = 1;
       } // catch
       
       int fromIndex = (whichPage - 1) * rowNum;
       int toIndex = whichPage * rowNum - 1;
    %>
    <table border="1" >
        <caption>搜尋結果</caption>
        <tr>
            <th>訂單編號</th>
            <th>司機ID</th>
            <th>會員ID</th>
            <th>狀態碼</th>
            <th>上車時間</th>
            <th>下車時間</th>
            <th>上車地點</th>
            <th>下車地點</th>
            <th>訂單種類</th>
            <th>總金額</th>
            <th>評價分數</th>
            <th>備註</th>
            <th>建立時間</th>
        </tr>
        <c:forEach var="singleOrder" items="${list}" begin="<%=fromIndex%>" end="<%=toIndex%>">
           <tr>
               <td>${singleOrder.orderID}</td>
               <td>${singleOrder.driverID}</td>
               <td>${singleOrder.memID}</td>
               <td>${stateMap[singleOrder.state]}</td>
               <td>${singleOrder.startTime}</td>
               <td>${singleOrder.endTime}</td>
               <td>${singleOrder.startLoc}</td>
               <td>${singleOrder.endLoc}</td>
               <td>${orderTypeMap[singleOrder.orderType]}</td>
               <td>${singleOrder.totalAmount}</td>
               <td>${singleOrder.rate}</td>
               <td>${singleOrder.note}</td>
               <td>${singleOrder.launchTime}</td>
           </tr>
        </c:forEach>
    </table>
    <% if (whichPage > 1) {%>
    <a href="<%=request.getRequestURI()%>?whichPage=1">第一頁</a>
    <a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage - 1%>">上一頁</a>
    <% } else {%>
                第一頁 上一頁
    <% }
       
       if (whichPage < totalPage) {%>
    <a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage + 1%>">下一頁</a>
    <a href="<%=request.getRequestURI()%>?whichPage=<%=totalPage%>">最後一頁</a>
    <% } else {%>
                下一頁 最後一頁
    <% }%>
</body>
</html>