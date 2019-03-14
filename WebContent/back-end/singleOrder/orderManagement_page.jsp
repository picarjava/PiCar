<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.sql.Timestamp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.Date"%>
<%@ page import="com.singleOrder.model.*"%>
<%@ page import="com.admin.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.groupOrder.model.*"%>
<%@ page import="com.singleOrder.controller.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>

//

<!-- 本頁面待與登入功能session 此處先指定driverID-->
<%-- <%String driverID=(String)session.getAttribute("driverID"); %>  --%>
<%! String driverID="D001";%>
<% session.setAttribute("driverID", driverID);%>

<!-- 個人訂單 -->
<% SingleOrderService service = new SingleOrderService();
   List<SingleOrderVO> singleOrderlist = service.getAll();
   request.setAttribute("singleOrderlist", singleOrderlist);
%>


<!-- 揪團訂單 -->
<% GroupOrderService groupOrderSvc=new GroupOrderService();
   List<GroupOrderVO> groupOrderlist=groupOrderSvc.getAll();
   request.setAttribute("groupOrderlist", groupOrderlist);
%>

<% MemberDAO memberdao = new MemberDAO();
	MemberService memberSvc=new MemberService();
   List<MemberVO> memberlist=memberSvc.getAll();
   request.setAttribute("memberlist", memberlist);
%>

<jsp:useBean id="groupBandSvc" scope="page" class="com.groupBand.model.GroupBandService"/>
<jsp:useBean id="groupBandVO" scope="page" class="com.groupBand.model.GroupBandVO"/>
<jsp:useBean id="singleOrderVO" scope="page" class="com.singleOrder.model.SingleOrderVO"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單管理頁面</title>


  <base href="<%=basePath%>">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!-- <link rel="stylesheet" type="text/css" href="styles.css"> -->
<!--     <script src="jquery-3.0.0.js" type="text/javascript"></script> -->
<!--     <script type="text/javascript">  -->
<!-- //     function myFunction(){  -->
<!-- //     	$.ajax({ type:"post", -->
<!-- //     		//傳輸方式為post，所以我們在servlet里面代碼是寫在doPost()函數中 url:"DataServlet",  -->
<!-- //     		//這就是使用的servlet的url success: -->
<!-- //     function(data){ document.getElementById("json").innerHTML=data; } } -->
<!-- //     	); }  -->
<!-- <!--     </script> --> 


</head>
<body>
    <!-- 錯誤列表開始 -->
    <%List errorMsgs=(List<String>)request.getAttribute("errorMsgs");%>
    <c:if test="${not empty errorMsgs}"><ul class="list-group">
		  <li class="list-group-item active">Opps!錯誤訊息回報</li>
		  <c:forEach var="massage" items="${errorMsgs}">
		  <li class="list-group-item">${massage}</li>
		  </c:forEach>
		</ul>
	</c:if>
    <!-- 錯誤列表結束 -->

    <p id="json">資料</p><br>
    <button onclick="myFunction()">獲取資料</button>

<div>
	<div><button>修改<img src=""></button></div>
	
</div>


<div id ="333"><button>查詢訂單</button></div>


<body>
	年級：
	<select id="grade">
		<option value="-1">請選擇</option>
		<option value="grade3">三年級</option>
		<option value="grade2">二年級</option>
		<option value="grade1">一年級</option>
	</select>
	班別：
	<select id="class">
		<option value="-1">請選擇</option>
	</select>
	姓名:
	<select id="name">
		<option value="-1">請選擇</option>
	</select>
	<br><br>
		                        <table class="table">
							  <thead class="thead-dark"><tr>
							      <tr></tr>
							      <tr>
							      <th scope="col">訂單編號	</th>
							      <th scope="col">訂單種類	</th>
							      <th scope="col">乘客名稱	</th>
							      <th scope="col">乘客電話	</th>
							      <th scope="col">乘車時間	</th>
							      <th scope="col">乘車地點	</th>
							      <th scope="col">乘車目的地	</th>
							      <th scope="col">訂單總金額	</th>
							      <th scope="col">訂單狀態	</th>
								  <th scope="col">重新叫車</th>
								  <th scope="col">退款</th>
							    </tr>
							  </thead>
							  <tbody>
		
		<c:forEach var="singleOrder" items="${singleOrderlist}" >
		<!-- 未來訂單:篩選狀態碼為 1:訂單成立、4:執行中、6:逾期未處理-->				  
			 <c:if test="${singleOrder.driverID eq driverID &&(singleOrder.state eq 6)}">
						 		<tr>	
						 		  <th scope="row">${singleOrder.orderID}</th>
						 		  <td>
						 		  <c:forEach var="orderType" items="${orderTypeMap}">
						 		   ${orderType.key eq singleOrder.orderType ? orderType.value: ""}
						 		  </c:forEach>
						 		  </td>
							      <td >
								 <c:set var="xxx" value="${singleOrder.memID}"/>
<!-- 							     預設當前頁面  -->
							      <% 
							      String xxx = (String)pageContext.getAttribute("xxx");
// 							      SingleOrderVO singleOrder = (SingleOrderVO)pageContext.getAttribute("xxx");//不需要
							      System.out.println(xxx);
							      System.out.println("-------------------------");
// 							      List<SingleOrderVO> singleOrderlist = service.getAll();

							      MemberVO ccc = memberdao.findByPrimaryKey(xxx);
							      System.out.print(ccc.getName());
// 							      System.out.print(ccc.getPhone());
							      %>
							      <%= ccc.getName() %>

							      </td>
							      <td >
							      <%= ccc.getPhone() %>
							      </td>
							      <td >
							      <fmt:formatDate  type="both" value="${singleOrder.startTime}" pattern="yyyy-MM-dd mm:ss" />
							      </td>
							      <td>${singleOrder.startLoc}</td>
							      <td>${singleOrder.endLoc}</td>
							      <td>${singleOrder.totalAmount}</td>
							       <td>
						 		  <c:forEach var="state" items="${stateMap}">
						 		   ${state.key eq singleOrder.state ? state.value: ""}
						 		  </c:forEach>
						 		  </td>
							      <td ><button>重新叫車</button>
								  </td>
								  <td>
								  <button>退款</button>
								  </td> 
<!-- 							      <td> -->
<%-- 							      <Form METHOD="post" ACTION="<%=request.getContextPath()%>/singleOrder" > --%>
<!-- 								    <div class="text-center"><button type="submit" class="btn btn-light">取消</button> -->
<!-- 								      	/*放隱藏的標籤，讓Controller抓到參數進行操作*/ -->
<!-- 		                				<input type="hidden" name="action" value="DELETE"> -->
<%-- 		                				<input type="hidden" name="orderID" value="${singleOrder.orderID}"> --%>
<!-- 								     </div> -->
<!-- 								  </Form> -->
<!-- 							      </td> -->
							    </tr>
		</c:if>					    
		</c:forEach>
							</tbody>
							</table>


</body>
</body>
<script>

</script>
</html>