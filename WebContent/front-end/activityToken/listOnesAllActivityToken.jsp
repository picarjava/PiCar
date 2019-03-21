<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.activityToken.model.*" %>

<%@ page import="com.member.model.*" %>
<%@ page import="java.util.*" %>

<!-- 會員登入功能串接 ，將VOmemID指定給 memID-->
<%@ page import="com.member.model.MemberVO"%>
<%MemberVO memberVO=(MemberVO)session.getAttribute("memberVO");
String memID=memberVO.getMemID();
session.setAttribute("memID",memID);
%> 
<!-- 得到此會員的list和sum  -->

<% ActivityTokenService activityTokenSvc=new ActivityTokenService();
  List<ActivityTokenVO> list=activityTokenSvc.getOnesALL(memID); 
  MemberService memberSvc=new MemberService();
  int sum=memberSvc.getOneMember(memID).getActivityToken();
  request.setAttribute("sum", sum);
  request.setAttribute("list", list);
  %> 


<!DOCTYPE html>
<html lang="zh">

<head>
   <jsp:include page="/front-end/storeRecord/listOneStoreRecordMemberFrontPos.jsp"/> 

   
    <title>查詢活動代幣明細</title>
   <jsp:include page="/regna-master/head.jsp" />
   
</head>

<body>
 	<!-- 錯誤列表 -->
    <%List<String> errorMsgs=(List<String>)request.getAttribute("errorMsgs");%>
    <c:if test="${not empty errorMsgs}"><ul class="list-group">
		  <li class="list-group-item active">Opps!錯誤訊息回報</li>
		  <c:forEach var="massage" items="${errorMsgs}">
		  <li class="list-group-item">${massage}</li>
		  </c:forEach>
		</ul>
	</c:if>

    <!--==========================
     listOnesALL
    ============================-->
<%--     <%int sum=(int)request.getAttribute("sum");%> --%>
<%--     <%List<ActivityTokenVO> list=(List<ActivityTokenVO>)request.getAttribute("list");%> --%>
   
    
    <%if(list!=null&&(list.size()>0)){ %>
        <section id="contact">
            <div class="container wow fadeInUp">
                <div class="section-header">
                    <h3 class="section-title">查詢活動代幣明細</h3>
                    <form action="<%=request.getContextPath()%>/front-end/HomeMember/index.jsp">
			          <div class="text-center"><button type="submit" class="btn btn-outline-success">返回會員首頁</button></div>
			         </form>
                </div>
            </div>
            <div class="container wow fadeInUp">
                        <table class="table">
						  <thead class="thead-dark">
						    <tr>
						      <th scope="col">會員編號	</th>
						      <th scope="col">活動編號	</th>
						      <th scope="col">代幣數量	</th>
						      <th scope="col">使用期限	</th>
						      <th scope="col">領用狀態	</th>
						      <th scope="col" colspan="3"><%@ include file="page1.file" %></th>
						    </tr>
						  </thead>
						  <tbody>
	<% int total=0;%>
	<c:forEach var="activityTokenVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >	
					 		<tr>	 
						      <th scope="row">${activityTokenVO.memID}</th>
						      <td>${activityTokenVO.activityID}</td>
						      <td>${activityTokenVO.tokenAmount}</td>
						      <td>${activityTokenVO.deadline}</td>
						      <td>${activityTokenVO.tokenAmount eq 0?"已使用":"已領取"}</td>
						    </tr>
	 </c:forEach>	
	 						<tr>
	 						<td colspan="4" align="left">總活動代幣共:　${sum}　點</td>
	 						</tr>
 						</tbody>
						</table>
						<%@ include file="page2.file" %>
            </div>
        </section>
  
    <%}%>
    <!--==========================
    底部
  ============================-->
    
   <jsp:include page="/regna-master/body.jsp" />
</body>

</html>