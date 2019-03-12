<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

  
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