<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.driver.model.*" %>
<%@ page import="com.member.model.*"%>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64" %>
<%@ page import="java.util.*" %>
	
<!DOCTYPE html>
<html lang="zh">

<head>
    <title>後台所有司機列表</title>
    <jsp:include page="/regna-master/head.jsp" />
</head>
<body>
    <!--=====     司機listALL========-->
   <!-- getAll再setAttribute存進pageContext 給forEach抓 -->
    <%
    MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
    DriverService driverSvc=new DriverService();%>
    <%List<DriverVO> list=driverSvc.getAll();%>
    <%request.setAttribute("list", list); %>
    <%if(list!=null&&(list.size()>0)){ %>
    
    <% 
    MemberDAO memberdao = new MemberDAO();
%>
    
    <%LinkedList<String> errorMsgs=(LinkedList<String>)request.getAttribute("errorMsgs");%>
    <!-- 錯誤列表 -->
    <c:if test="${not empty errorMsgs}"><ul class="list-group">
		  <li class="list-group-item active">Opps!錯誤訊息回報</li>
		  <c:forEach var="massage" items="${errorMsgs}">
		  <li class="list-group-item">${massage}</li>
		  </c:forEach>
		</ul>
	</c:if>
        <section id="contact">
            <div class="container wow fadeInUp">
                <div class="section-header">
                    <h3 class="section-title">後台所有司機列表</h3>
                    <form action="driverMemberManagement.jsp">
			          <div class="text-center"><button type="submit" class="btn btn-outline-success">返回</button></div>
			         </form>
                </div>
            </div>
            <div class="container wow fadeInUp">
                        <table class="table">
						  <thead class="thead-dark">
						    <tr>
						      <th scope="col">審核驗證	</th>
						      <th scope="col">會員編號	</th>
						      <th scope="col">司機編號	</th>
						      <th scope="col">姓名    		</th>
						      <th scope="col">車牌號碼	</th>
						      <th scope="col">BAN	</th>
						      <th scope="col">到期時間	</th>
						      <th scope="col">是否線上	</th>
						      <th scope="col">評價分數	</th>
						      <th scope="col">品牌	</th>
						      <th scope="col">共享	</th>
						      <th scope="col">寵物	</th>
						      <th scope="col">抽菸	</th>
						      <th scope="col">嬰兒座椅	</th>
						      <th scope="col" colspan="2"><%@ include file="page1.file" %></th>
						    </tr>
						  </thead>
						  <tbody>
	<c:forEach var="driverVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >			  
					 		<tr>	 
						      <th scope="row">
						      <c:if test="${driverVO.verified == 0}">
						        <Form METHOD="post" ACTION="driver.do" >
							    <div class="text-center"><button type="submit" class="btn btn-light">未審核</button>
							      	<!-- /*放隱藏的標籤，重複使用activityVO，讓Controller抓到參數進行操作*/ -->
							      	<input type="hidden" name="actionS" value="GET_ONE_FOR_PERMIT">
	                				<input type="hidden" name="action" value="GET_ONE_FOR_CHECK">
	                				<input type="hidden" name="driverID" value="${driverVO.driverID}">
							     </div>
							  </Form>
						      </c:if>
						      <c:if test="${driverVO.verified == 1}">審核通過</c:if>
						      <c:if test="${driverVO.verified == 2}">未通過</c:if>
						      </th>
						      <td>${driverVO.memID}</td>
						      
						      <td>${driverVO.driverID}</td>
						      
						      <td>

								<c:set var="memID" value="${driverVO.memID}"/>
<!-- 							     預設當前頁面  -->
							      <% 
							      String memID = (String)pageContext.getAttribute("memID");
// 							      SingleOrderVO singleOrder = (SingleOrderVO)pageContext.getAttribute("memID");//不需要
							      MemberVO member = memberdao.findByPrimaryKey(memID);
							      System.out.println(member.getName());
							      %>
							      <%= member.getName() %>
							     <%  System.out.println("-------------------------");%>
							</td>
						      <td>${driverVO.plateNum}</td>
<%-- 						      <td>${driverVO.licence}</td> --%>
<!-- 						      <td> -->
<%-- 						       	  <c:if test="${empty driverVO.photo}" var="condition"> --%>
<%-- 					              <img src="<%=request.getContextPath()%>/regna-master/img/noFileUpdate.JPG" width="200" height="100"/> --%>
<%-- 					              </c:if> --%>
<%-- <%-- 					              <img  src='<%=request.getContextPath()%>/driver/Activ_servlet.html?activityID=${activityVO.activityID}' width='200' height='100' alt='"這是"+${activityVO.activityID}+"的活動海報"  '/> --%> 
<%-- 					              <c:if test="${not empty driverVO.photo}" var="condition"> --%>
<%-- 					              <img  src='<%=request.getContextPath()%>/driver.do?driverID=${driverVO.driverID}' width='200' height='100' alt='"這是"+${driverVO.driverID}+"的"  '/> --%>
<%-- 					              </c:if>  --%>
<!-- 						      </td> -->
						      <td>
						      <c:if test="${driverVO.banned == 0}">
						       <Form METHOD="post" ACTION="driver.do" >
							    <div class="text-center"><button type="submit" class="btn btn-light">可以接單</button>
<!-- 							      	/*放隱藏的標籤，重複使用activityVO，讓Controller抓到參數進行操作*/ -->
							      	<input type="hidden" name="actionS" value="GET_ONE_FOR_BANNED">
	                				<input type="hidden" name="action" value="GET_ONE_FOR_CHECK">
	                				<input type="hidden" name="driverID" value="${driverVO.driverID}">
							     </div>
							  </Form>
							  可以接單。給司機檢舉。
						      </c:if>
						      <c:if test="${driverVO.banned == 1}">禁止接單</c:if>
						      </td>
						      <td>
<%-- 						      <c:if test="${driverVO.deadline != null}">${driverVO.deadline}</c:if> --%>
<%-- 						      <c:if test="${driverVO.deadline == null}">沒有到期日</c:if> --%>
								<c:out value="${driverVO.deadline}" default="沒有到期日"/>
						      </td>
						      <td>
						      <c:if test="${driverVO.onlineCar == 0}">不在線</c:if>
						      <c:if test="${driverVO.onlineCar == 1}">在線</c:if>
						      </td>
						      <td>${driverVO.score}</td>
						      <td>${driverVO.carType}</td>
						      <td>
						      <c:if test="${driverVO.sharedCar == 0}">不接受共享</c:if>
						      <c:if test="${driverVO.sharedCar == 1}">接受共享</c:if>
						      </td>
						      <td>
						      <c:if test="${driverVO.pet == 0}">不要寵物</c:if>
						      <c:if test="${driverVO.pet == 1}">寵物我可以</c:if>
						      </td>
						      <td>
						      <c:if test="${driverVO.smoke == 0}">不接受</c:if>
						      <c:if test="${driverVO.smoke == 1}">接受</c:if>
						      </td>
						      <td>
						      <c:if test="${driverVO.babySeat == 0}">不提供</c:if>
						      <c:if test="${driverVO.babySeat == 1}">提供</c:if>
						      </td>
<!-- 						       <td> -->
<%-- 						      <Form METHOD="post" ACTION="<%=request.getContextPath()%>/activityToken/ActivTokenServlet" > --%>
<!-- 							    <div class="text-center"><button type="submit" class="btn btn-light">查看領取名單</button> -->
<!-- 							      	/*放隱藏的標籤，讓Controller抓到參數進行操作*/ -->
<!-- 	                				<input type="hidden" name="action" value="GET_ALL_STMT"> -->
<%-- 	                				<input type="hidden" name="activityID" value="${activityVO.activityID}"> --%>
<!-- 							     </div> -->
<!-- 							  </Form> -->
<!-- 						      </td> -->
						      <td>
						      <Form METHOD="post" ACTION="driver.do" >
							    <div class="text-center"><button type="submit" class="btn btn-light">修改</button>
							      	<!-- /*放隱藏的標籤，重複使用activityVO，讓Controller抓到參數進行操作*/ -->
							      		<input type="hidden" name="actionS" value="GET_ONE_CHECK_PERMIT">
	                				<input type="hidden" name="action" value="GET_ONE_FOR_CHECK">
	                				<input type="hidden" name="driverID" value="${driverVO.driverID}">
							     </div>
							  </Form>
						      </td>
						    </tr>
	</c:forEach>	
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