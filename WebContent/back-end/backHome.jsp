<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.admin.model.*"%>
	
<%
	AdminVO adminVO = (AdminVO) session.getAttribute("adminVO");
%>
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>


<!doctype html>
<html lang="zh">
<head>
<title>PICAR BACK-END</title>
<jsp:include page="/back-end/head_back.jsp" />


</head>

<body>

	<div class="wrapper ">
		<div class="sidebar" data-color="" data-background-color="black"
			data-image="../assets/img/sidebar-1.jpg">
			<div class="logo">
				<a href="<%=request.getServletContext().getContextPath()%>/back-end/backHome.jsp" class="simple-text logo-normal"> PICAR </a>
			</div>
      <div class="sidebar-wrapper">
        <ul class="nav">
          <li class="nav-item active  ">
            <a class="nav-link" href="<%=request.getServletContext().getContextPath()%>/back-end/admin/admin_select_page.jsp">
              <i class="material-icons">person</i>
              <p>管理員管理</p>
            </a>
          </li>
          <li class="nav-item ">
            <a class="nav-link" href="<%=request.getServletContext().getContextPath()%>/back-end/groupReport/greport_select_page.jsp">
              <i class="material-icons">dashboard</i>
              <p>檢舉揪團管理</p>
            </a>
          </li>
          <li class="nav-item ">
            <a class="nav-link" href="<%=request.getServletContext().getContextPath()%>/back-end/driverReport/select_page.jsp">
              <i class="material-icons">content_paste</i>
              <p>檢舉司機管理</p>
            </a>
          </li>
          <li class="nav-item ">
            <a class="nav-link" href="<%=request.getServletContext().getContextPath()%>/back-end/member/select_page.jsp">
              <i class="material-icons">content_paste</i>
              <p>乘客會員管理</p>
            </a>
          </li>
          <li class="nav-item ">
            <a class="nav-link" href="<%=request.getServletContext().getContextPath()%>/back-end/driver/driverMemberManagement.jsp">
              <i class="material-icons">content_paste</i>
              <p>司機會員管理</p>
            </a>
          </li>
          <li class="nav-item ">
            <a class="nav-link" href="<%=request.getServletContext().getContextPath()%>/back-end/rate/select_page.jsp">
              <i class="material-icons">content_paste</i>
              <p>資費管理</p>
            </a>
          </li>
          <li class="nav-item active-pro ">
            <a class="nav-link" href="<%=request.getServletContext().getContextPath()%>/back-end/singleOrder/orderManagement_page.jsp">
              <i class="material-icons">content_paste</i>
              <p>訂單管理</p>
            </a>
          </li>
          <li class="nav-item ">
            <a class="nav-link" href="<%=request.getServletContext().getContextPath()%>/back-end/rate/select_page.jsp">
              <i class="material-icons">content_paste</i>
              <p>乘客推播訊息設定</p>
            </a>
          </li>
          <li class="nav-item ">
            <a class="nav-link" href="./typography.html">
              <i class="material-icons">library_books</i>
              <p>Typography</p>
            </a>
          </li>
          <li class="nav-item ">
            <a class="nav-link" href="<%=request.getServletContext().getContextPath()%>/back-end/activity/homeActivity.jsp">
              <i class="material-icons">bubble_chart</i>
              <p>活動資訊管理</p>
            </a>
          </li>
          <li class="nav-item ">
            <a class="nav-link" href="./map.html">
              <i class="material-icons">location_ons</i>
              <p>Maps</p>
            </a>
          </li>
          <li class="nav-item ">
            <a class="nav-link" href="./notifications.html">
              <i class="material-icons">notifications</i>
              <p>即時客服交談</p>
            </a>
          </li>
          <li class="nav-item ">
            <a class="nav-link" href="./rtl.html">
              <i class="material-icons">language</i>
              <p>RTL Support</p>
            </a>
          </li>
        </ul>
      </div>
    </div>
		<div class="main-panel">
			<!-- Navbar -->
			<nav
				class="navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top ">
				<div class="container-fluid">
					<div class="navbar-wrapper">
						<a class="navbar-brand" href="#"></a>
					</div>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						aria-controls="navigation-index" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="sr-only">Toggle navigation</span> <span
							class="navbar-toggler-icon icon-bar"></span> <span
							class="navbar-toggler-icon icon-bar"></span> <span
							class="navbar-toggler-icon icon-bar"></span>
					</button>
				
					<div class="collapse navbar-collapse justify-content-end">
						<ul class="navbar-nav">
							<li class="nav-item"><a class="nav-link" href="#pablo">
									<i class="material-icons">notifications</i> Notifications
							</a>
							</li>
				              <p id="p1"><font color=black> ${adminVO.adminName} </font>您好   </p>
				                <FORM METHOD="post" ACTION="logoutHandlerBackEnd.do">
									<input type="hidden" name="action" value="logout">
									<input type="submit" name="logout" value="登出">				
								</FORM>
							<!-- your navbar here -->
						</ul>
					</div>
				
				</div>
			</nav>
			<!-- End Navbar -->

				<footer class="footer">
					<div class="container-fluid">
						<nav class="float-left"></nav>
						<div class="copyright float-right">
							&copy;
							<script>
				              document.write(new Date().getFullYear())
				            </script>
							  PICAR.
						</div>
						<!-- your footer here -->
					</div>
				</footer>
			</div>
		</div>

		<!-- Optional JavaScript -->
		<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>