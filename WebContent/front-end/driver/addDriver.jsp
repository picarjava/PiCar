<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.driver.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64" %>
<html>
<head>
<title>成為司機</title>
<jsp:include page="/regna-master/head.jsp" />
<jsp:include page="/front-end/HomeMember/HeadMember.jsp" />
</head>

<body>
	<!-- 先get 再set -->
<!-- 	http://localhost:8081/PiCar/front-end/driver/addDriver.jsp -->
<!-- 登入功能串接 ，將VOmemID指定給 memID-->
	<%
// 	印出是否拿到ID////
// 		DriverVO driverVO = (DriverVO) request.getAttribute("driverVO");
MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
DriverService driSrc = new DriverService();
DriverVO driverVO  = driSrc.getOneDriverBymemID(memberVO.getMemID());
String memID=memberVO.getMemID();
session.setAttribute("memID",memID);
	%>
	
	<!--  Session得到的MemberVO原本沒有照片，若新增照片 ，要先拿出MEMID 透過SERVEICE 查詢這位會員的新資料，拿到新的MemberVO 裡面才有照片 -->
	<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>
	${memberSvc.getOneMember(memberVO.memID).memID}

	<%
		LinkedList errorMsgs = (LinkedList) request.getAttribute("errorMsgs");
	%>
	<!-- 錯誤列表 -->
    <c:if test="${not empty errorMsgs}"><ul class="list-group">
			<li class="list-group-item active">Opps!錯誤訊息回報</li>
			<c:forEach var="massage" items="${errorMsgs}">
				<li class="list-group-item">${massage}</li>
			</c:forEach>
		</ul>
	</c:if>
	<!--==========================
      Contact Section 
    ============================-->

	<section id="contact">
		<div class="container wow fadeInUp">
			<div class="col-lg-12 col-md-12">
				<div class="section-header">
					<h3 class="section-title">成為司機(上傳檢附文件)</h3>
					<div class="text-center">
						<form action="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/index.jsp">
							<!-- 以上請寫成為司機的人 action="寫上上一頁的畫面" -->
							<button type="submit" class="btn btn-outline-success"">返回</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="container wow fadeInUp">
			<div class="row justify-content-center">
				<div class="col-lg-12 col-md-12">
					<div class="form">
						<!-- 成為司機開始 -->

						<form action="driver.do" method="post" enctype="multipart/form-data">

							<div class="form-group">
								<p>會員編號</p>
								<input type="text" name="memID" value="${memberVO.memID}"
									class="form-control"  readonly />
							</div>
							<div class="form-group">
								<p>車牌號碼</p>
								<input type="text" required name="plateNum" class="form-control"
									 placeholder="請輸入車牌號碼例如:ABC1234" />
							</div>
							<div class="form-group">
								<p>車型</p>
								<select name="carType" class="form-control">
									<option value="Picar">Picar</option>
									<option value="Nissan">Nissan</option>
									<option value="Volkswagen">Volkswagen</option>
									<option value="Skoda">Skoda</option>
									<option value="Audi">Audi</option>
									<option value="Ford">Ford</option>
									<option value="Toyota">Toyota</option>
									<option value="Honda">Honda</option>
									<option value="Mazda">Mazda</option>
									<option value="Mercedes-Benz">Mercedes-Benz</option>
								</select>

							</div>
							<div class="form-group">
								<p>駕照</p>
								<input type="file" class="form-control" name="licence"
									value="" placeholder="請輸入駕照" />
							</div>
							<div class="form-group">
								<p>良民證</p>
								<input type="file" class="form-control" name="criminal"
									value="" />
							</div>

							<div class="form-group">
								<p>肇事紀錄</p>
								<input type="file" class="form-control" name="trafficRecord"
									value="2019-03-29" placeholder="請上傳肇事紀錄" />
							</div>

							<div class="form-group">
								<p>身分證</p>
								<input type="file" class="form-control" name="idNum" value="200"
									placeholder="請上傳身分證" />
							</div>

							
							<div class="form-group">
<!-- 								<p>會員照片</p> -->
<%-- 								<c:if test="${empty memberVO.pic}" > --%>
<%-- 					            <img src="<%=request.getContextPath()%>/regna-master/img/noFileUpdate.JPG" class='card-img-top' width='300' height='350'> --%>
<%-- 					            </c:if> --%>
<%-- 					             <c:if test="${not empty memberVO.pic}" > --%>
<%-- 									<img src="<%=request.getContextPath()%>/front-end/member/member.do?memID=${memberVO.memID}"  width='400' height='350' > --%>
<%-- 					             </c:if> --%>

									<p>會員照片</p> 
										<c:if test="${empty memberSvc.getOneMember(memberVO.memID).pic}" >
							            <img src="<%=request.getContextPath()%>/regna-master/img/noFileUpdate.JPG" class='card-img-top' width='300' height='350'>
							            </c:if>
							             <c:if test="${not empty memberSvc.getOneMember(memberVO.memID).pic}" >
										<img src="<%=request.getContextPath()%>/front-end/member/member.do?memID=${memberSvc.getOneMember(memberVO.memID).memID}"  width='400' height='350' >
						                </c:if>
							</div>
							
							<div class="form-group">
								<p>願意共乘載客</p>
								<select name="sharedCar" class="form-control">
									<option value="0">無</option>
									<option value="1">有</option>
								</select>
							</div>

							<div class="form-group">
								<p>可載寵物</p>
								<select name="pet" class="form-control">
									<option value="0">無法</option>
									<option value="1">接受</option>
								</select>
							</div>

							<div class="form-group">
								<p>抽菸</p>
								<select name="smoke" class="form-control">
									<option value="0">無</option>
									<option value="1">有</option>
								</select>
							</div>

							<div class="form-group">
								<p>提供嬰兒座椅</p>
								<select name="babySeat" class="form-control">
									<option value="0">無</option>
									<option value="1">有</option>
								</select>
							</div>

							<div class="text-center">
								<input type="hidden" name="action" value="INSERT">
								<button type="submit" class="btn btn-dark" id="btn">成為司機</button>
							</div>
							<!--隱藏的參數action讓controller抓-->
						</form>
					</div>
				</div>
			</div>
			<!-- row結尾 -->
		</div>
	</section>
	<!-- #contact -->
	<!--==========================
    底部
  ============================-->

	<jsp:include page="/regna-master/body.jsp" />
<script>


var btn= document.getElementById("btn");
$(btn).css("color","blue");


</script>
</body>
</html>