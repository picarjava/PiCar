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
				<a href="index.jsp" class="simple-text logo-normal"> PICAR </a>
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
          <li class="nav-item ">
            <a class="nav-link" href="<%=request.getServletContext().getContextPath()%>/back-end/rate/select_page.jsp">
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
          <li class="nav-item active-pro ">
            <a class="nav-link" href="./upgrade.html">
              <i class="material-icons">unarchive</i>
              <p>Upgrade to PRO</p>
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
								
								<input type="submit" data-toggle="modal" data-target="#exampleModal" value="修改個人密碼">
							<!-- your navbar here -->
						</ul>
					</div>
				
				</div>
			</nav>
			<!-- End Navbar -->
		<!-- Modal -->
	      <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	        <div class="modal-dialog" role="document">
	          <div class="modal-content">
	            <div class="modal-header">
	              <h5 class="modal-title" id="exampleModalLabel">修改密碼</h5>
	              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                <span aria-hidden="true">&times;</span>
	              </button>
	            </div>
	            <div class="modal-body">
	                    <FORM METHOD="post" ACTION="adminSelf.do" name="form1">
								輸入新密碼<input class="form-control" type="text" id="p2" name="password"><br>
								再次輸入新密碼<input class="form-control" type="text" id="p2" name="password2"><br>
		                          	<input type="hidden" name="adminID" value="<%=adminVO.getAdminID()%>"> 
		                            <input type="hidden" name="adminName" value="<%=adminVO.getAdminName()%>"> 
		                            <input type="hidden" name="email" value="<%=adminVO.getEmail()%>"> 
		                            <input type="hidden" name="isEmp" value="<%=adminVO.getIsEmp()%>"> 
								<input type="hidden" name="action" value="update"> 
								<input type="hidden" name="adminID" value="<%=adminVO.getAdminID()%>"> 
								<!-- 呼叫JS方法判斷新密碼輸入 -->
								<br>
								<br>
								<br>
								<input type="submit" onclick="return test()"> <br>
								<br>
	                    </FORM>
	                  <br>
	                  <br>
	                  <br>
	            </div>
	            </div>
	          </div>
	        </div>
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
		
		
			<script type="text/javascript">
	        function test(){
	        var f = document.form1;
	        var newPsw1 = f.password.value;
	        var newPsw2 = f.password2.value;
	        if(newPsw1 == "" || newPsw2 == ""){
	        alert("密碼請勿空白");
	        return false;
	        }
	        if(newPsw1.length<6){
		   	alert("密碼不得低於6個英數字");
		    return false;
		    }
	        if(newPsw1.length>12){
			   	alert("密碼不得超過12個英數字");
			    return false;
			}
	        if(newPsw1 != newPsw2){
	        alert("新密碼輸入不一致");
	        return false;
	        }
	        return true;
	        }
	
	    </script>
		<!-- Optional JavaScript -->
		<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>