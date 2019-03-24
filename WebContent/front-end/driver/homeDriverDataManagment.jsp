<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.driver.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64" %>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.http.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>司機資料管理</title>
<%--         <jsp:include page="/regna-master/head.jsp" /> --%>
    <jsp:include page="/front-end/HomeDriver/Header.jsp" />
    <jsp:include page="/front-end/HomeDriver/HeadDriver.jsp"/> 
</head>
<style>

.form-control{

color:blue; 
}


.block{
position:relative;
top:70%;

}

</style>
<body>
    <!-- 登入功能串接 ，將VOmemID指定給 memID-->
    <!-- 先取出VO -->
    <%
//      DriverVO driverVO = (DriverVO) request.getAttribute("driverVO");
        MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
        String memID=memberVO.getMemID();
        session.setAttribute("memID",memID);
    MemberService xxx = new MemberService();
    List<MemberVO> yyy =xxx.getAll();
    DriverService driSrc = new DriverService();
    DriverVO driverVO  = driSrc.getOneDriverBymemID(memberVO.getMemID());
    session.setAttribute("driverVO",driverVO);
        DriverVO drixx = (DriverVO)session.getAttribute("driverVO");
        System.out.println(memberVO.getMemID());
        System.out.println(memberVO.getPic());////
    %>
    <section id="contact">
        <div class="container wow fadeInUp">
            <div class="section-header">
                <h3 class="section-title">司機資料管理</h3>
                <p class="section-description">請查看與修改資料</p>
            </div>
        </div>
        <div class="container wow fadeInUp">
            <div class="row justify-content-center">
                <div class="col-lg-1 col-md-4">
                    <div class="info"></div>
                </div>
                <div class="col-lg-9 col-md-8">
                    <div class="form">
                        <!--                         <div id="sendmessage">Welcome to PICAR. Thank you!</div> -->
                        <div id="errormessage"></div>
                        <div class="block">
                        <form method="post" action="<%=request.getContextPath()%>/driver/driver.do" role="form" class="contactForm" style="margin-bottom: 0px;">
                            <div align="center">個人資料</div><br>
                            <div class="input-group mb-3 ">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1">會員編號</span>
                                </div>
                                <input type="text" class="form-control" placeholder="MEMID" value="<%=driverVO.getMemID() %>" aria-label="Username" aria-describedby="basic-addon1" disabled="disabled">
                            </div>
                            <div class="input-group mb-3 ">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1">司機編號</span>
                                </div>
                                <input type="text" class="form-control" placeholder="DRIVER1" value="${driverVO.driverID}" aria-label="Username" aria-describedby="basic-addon1" readonly>
                            </div>
                            <div class="input-group mb-3 ">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1">名字</span>
                                </div>
                                <input type="text" class="form-control" placeholder="DRIVER1" value="${memberVO.name}" aria-label="Username" aria-describedby="basic-addon1" readonly>
                            </div>
                            <div class="input-group mb-3 ">
                                <div class="input-group-prepend ">
                                    <span class="input-group-text " id="basic-addon1">車牌號碼</span>
                                </div>
                                <input type="text" class="form-control" placeholder="RAS-9958" value="${driverVO.plateNum}" aria-label="Username" aria-describedby="basic-addon1" readonly>
                            </div>
                            <div class="form-group">
                                <p>駕照</p>
                                <c:set var="licence" value="${driverVO.licence}" />
                                <%
                              byte[] licence = (byte[])pageContext.getAttribute("licence");
                              String encodeImg1 = null;
                              if(licence!=null){
                                  encodeImg1 = Base64.encode(licence);%>
                                <img src="data:image/jpg;base64,<%=encodeImg1 %>" id="img1" width='200' height="200" onerror="this.src='cat.jpg'">
                                <% }%>
                            </div>
                            <%--                              <td>${driverVO.criminal}</td> --%>
                            <div class="form-group">
                                <p>良民證</p>
                                <c:set var="criminal" value="${driverVO.criminal}" />
                                <%
                              byte[] criminal = (byte[])pageContext.getAttribute("criminal");
                              String encodeImg2 = null;
                              if(criminal!=null){
                                  encodeImg2 = Base64.encode(criminal);%>
                                <img src="data:image/jpg;base64,<%=encodeImg2 %>" id="img2" width='200' height="200" onerror="this.src='cat.jpg'">
                                <% }%>
                            </div>
                            <%--                              <td>${driverVO.trafficRecord}</td> --%>
                            <div class="form-group">
                                <p>肇事紀錄</p>
                                <c:set var="trafficRecord" value="${driverVO.trafficRecord}" />
                                <%
                              byte[] trafficRecord = (byte[])pageContext.getAttribute("trafficRecord");
                              String encodeImg3 = null;
                              if(trafficRecord!=null){
                                  encodeImg3 = Base64.encode(trafficRecord);%>
                                <img src="data:image/jpg;base64,<%=encodeImg3 %>" id="img3" width='200' height="200" onerror="this.src='cat.jpg'">
                                <% }%>
                            </div>
                            <%--                              <td>${driverVO.idNum}</td> --%>
                            <div class="form-group">
                                <p>身分證</p>
                                <c:set var="idNum" value="${driverVO.idNum}" />
                                <%
                              byte[] idNum = (byte[])pageContext.getAttribute("idNum");
                              String encodeImg4 = null;
                              if(idNum!=null){
                                  encodeImg4 = Base64.encode(idNum);%>
                                <img src="data:image/jpg;base64,<%=encodeImg4 %>" id="img4" width='200' height="200" onerror="this.src='cat.jpg'">
                                <% }%>
                            </div>
                            <div class="form-group">
                                <p>會員照片</p>
                                <img src="<%=request.getServletContext().getContextPath()%>/front-end/member/member.do?memID=${memberVO.memID}" width='200' height="200" onerror="this.src='cat.jpg'">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1">評價分數</span>
                                </div>
                                <input type="text" class="form-control" placeholder="3" value="${driverVO.score}" aria-label="Username" aria-describedby="basic-addon1" readonly>
                            </div>
                            <div align="center">喜好設定</div><br>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1">願意共乘載客</span>
                                </div>
                                <div class="form-control" aria-label="Username" aria-describedby="basic-addon1">
                                    <font>
                                        <c:if test="${driverVO.sharedCar == 0}">不接受共乘</c:if>
                                        <c:if test="${driverVO.sharedCar == 1}">接受共乘</c:if>
                                    </font>
                                </div>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1">可載寵物</span>
                                </div>
                                <div class="form-control" aria-label="Username" aria-describedby="basic-addon1">
                                    <font>
                                        <c:if test="${driverVO.pet == 0}">不要寵物</c:if>
                                        <c:if test="${driverVO.pet == 1}">寵物我可以</c:if>
                                    </font>
                                </div>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1">是否抽菸</span>
                                </div>
                                <div class="form-control" aria-label="Username" aria-describedby="basic-addon1">
                                    <font>
                                        <c:if test="${driverVO.smoke == 0}">不接受抽菸</c:if>
                                        <c:if test="${driverVO.smoke == 1}">接受抽菸</c:if>
                                    </font>
                                </div>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1">提供嬰兒座椅</span>
                                </div>
                                <div class="form-control" aria-label="Username" aria-describedby="basic-addon1">
                                    <font>
                                        <c:if test="${driverVO.babySeat == 0}">不提供嬰兒座椅</c:if>
                                        <c:if test="${driverVO.babySeat == 1}">提供嬰兒座椅</c:if>
                                    </font>
                                </div>
                            </div>
                            <div><button>
                                <a href="<%=request.getContextPath()%>/front-end/driver/setting.jsp"><br />喜好設定</a>
                            </button></div>
                            <!--                                     <div align="center">違規狀態</div><br> -->
                            <!--                                     <div class="input-group mb-3"> -->
                            <!--                                         <div class="input-group-prepend"> -->
                            <!--                                             <span class="input-group-text" id="basic-addon1">接單狀態碼:</span> -->
                            <!--                                         </div> -->
                            <!--                                         <input type="text" class="form-control" placeholder="1" -->
                            <!--                                         aria-label="Username" aria-describedby="basic-addon1" readonly> -->
                            <!-- <!--                                         <DIV> -->
                            <!-- <!--                                         <font color="black"> -->
                            <%-- <%--                                         <c:if test="${driverVO.banned == 0}">表現不錯，沒被BAN</c:if> --%>
                            <%-- <%--                                       <c:if test="${driverVO.banned == 1}">BAN</c:if> --%>
                            <!-- <!--                                         </font> -->
                            <!-- <!--                                       </DIV> -->
                            </form>
                            </div>
                    </div>
                    <!--                                     <div class="input-group mb-3"> -->
                    <!--                                         <div class="input-group-prepend"> -->
                    <!--                                             <span class="input-group-text" id="basic-addon1">到期日:</span> -->
                    <!--                                         </div> -->
                    <!--                                         <font color="black"> -->
                    <%--                                             <c:out value="${driverVO.deadline}" default="表現不錯，繼續開車" /> --%>
                    <!--                                         </font> -->
                    <!--                                     </div> -->
                    <!--                                     <div class="text-center"> -->
                    <!--                                         <button type="submit" class="btn btn-block ">確認修改</button> -->
                    <%--                                            <input type="hidden" name="msgID"  value="${brodVO.msgID}"> --%>
                    <!--                                         <input type="hidden" name="action" value="UPDATE_DRI" /> -->
                    <!--                                     </div> -->
                    <!-- /*放隱藏的標籤，讓Controller抓到參數進行操作*/ -->
                    <div class="text-center" class="btn btn-block" class="btn btn-outline-success">
                        <button><a href="<%=request.getServletContext().getContextPath()%>/front-end/HomeMember/index.jsp">返回會員首頁</a>
                            </button>
                    </div>
                </div>
            </div><!--     row justify-content-center -->
        </div><!-- "container wow fadeInUp" -->
    </section>
    <!-- #contact -->
    <jsp:include page="/regna-master/body.jsp" />
    <jsp:include page="/front-end/HomeDriver/Footer.jsp" />
</body>
</html>