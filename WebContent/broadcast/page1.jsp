<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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