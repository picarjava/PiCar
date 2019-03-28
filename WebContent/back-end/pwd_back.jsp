<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.admin.model.*"%>
<%
	AdminVO adminVO = (AdminVO) session.getAttribute("adminVO");
%>

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