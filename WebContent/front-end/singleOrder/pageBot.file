<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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