<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <meta charset="utf-8">
  <title>Regna Bootstrap Template</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="" name="keywords">
  <meta content="" name="description">

  <!-- Favicons -->
  <link href="img/favicon.png" rel="icon">
  <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700" rel="stylesheet">

  <!-- Bootstrap CSS File -->
  <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Libraries CSS Files -->
  <link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="lib/animate/animate.min.css" rel="stylesheet">

  <!-- Main Stylesheet File -->
  <link href="css/style.css" rel="stylesheet">



  <!-- =======================================================
    Theme Name: Regna
    Theme URL: https://bootstrapmade.com/regna-bootstrap-onepage-template/
    Author: BootstrapMade.com
    License: https://bootstrapmade.com/license/
  ======================================================= -->
</head>

<body>
    <!--==========================
      Contact Section
    ============================-->
    <section id="contact">
      <div class="container wow fadeInUp">
        <div class="section-header">
          <h3 class="section-title">活動表單</h3>
          <p class="section-description"> 請開始新增活動</p>
        </div>
        <div class="text-center"><button type="button">返回</button></div>
      </div>

      <div class="container wow fadeInUp">
        <div class="row justify-content-center">

          <div class="col-lg-3 col-md-4">
            <div class="info">
            

            </div>
          </div>
 
          

          <div class="col-lg-9 col-md-8">
            <div class="form">
              <div id="sendmessage">Your message has been sent. Thank you!</div>
              <div id="errormessage"></div>
              <!-- 活動表單開始 -->
              <form action="<%=request.getContextPath()%>/Activ_servlet.html" method="post" role="form" class="contactForm">
        
                <div class="form-group">
                  <p>活動編號</p> 
                  <input type="text" name="activity_Id" class="form-control" id="name" placeholder="請輸入活動編號" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
                  <div class="validation"></div>
                </div>

                <div class="form-group">
                   <p>活動名稱</p>
                  <input type="text" class="form-control" name="activity_Name" id="email" placeholder="請輸入活動名稱" data-rule="email" data-msg="Please enter a valid email" />
                  <div class="validation"></div>
                </div>

                <div class="form-group">
                  <p>活動資訊</p>
                  <textarea class="form-control" name="activity_Info" rows="5" data-rule="required" data-msg="請輸入活動資訊" placeholder="Message"></textarea>
                  <div class="validation"></div>
                </div>

               <div class="form-group">
                  <p>活動開始時間</p>
                  <input type="date" class="form-control" name="activity_Start" id="email" placeholder="請輸入活動開始時間" data-rule="email" data-msg="Please enter a valid email" />
                  <div class="validation"></div>
                </div>

                <div class="form-group">
                  <p>活動結束時間</p>
                  <input type="date" class="form-control" name="activity_End" id="email" placeholder="請輸入活動結束時間" data-rule="email" data-msg="Please enter a valid email" />
                  <div class="validation"></div>
                </div>

                <div class="form-group">
                  <p>活動序號</p>
                  <input type="text" class="form-control" name="activity_Code" id="email" placeholder="請輸入活動序號" data-rule="email" data-msg="Please enter a valid email" />
                  <div class="validation"></div>
                </div>

                <div class="form-group">
                  <p>活動代幣</p>
                  <input type="text" class="form-control" name="token_Amount" id="email" placeholder="請輸入本活動代幣數量" data-rule="email" data-msg="Please enter a valid email" />
                  <div class="validation"></div>
                </div>
                <p>活動海報</p>
                 <div class="form-group">
                  <input type="file" class="form-control" name="activity_Post">
                  <div class="validation"></div>
                </div>
                <div class="text-center"><button type="submit">新增活動</button></div>
              <!--隱藏的參數action讓controller抓-->
              	<input type="hidden" name="action" value="CREATE">
              
              </form>

            </div>
          </div>

        </div>

      </div>
    </section><!-- #contact -->

  </main>

  <!--==========================
    Footer
  ============================-->
  <footer id="footer">
    <div class="footer-top">
      <div class="container">

      </div>
    </div>

    <div class="container">
      <div class="copyright">
        &copy; Copyright <strong>Regna</strong>. All Rights Reserved
      </div>
      <div class="credits">
        <!--
          All the links in the footer should remain intact.
          You can delete the links only if you purchased the pro version.
          Licensing information: https://bootstrapmade.com/license/
          Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/buy/?theme=Regna
        -->
        Bootstrap Templates by <a href="https://bootstrapmade.com/">BootstrapMade</a>
      </div>
    </div>
  </footer><!-- #footer -->

  <a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>

  <!-- JavaScript Libraries -->
  <script src="lib/jquery/jquery.min.js"></script>
  <script src="lib/jquery/jquery-migrate.min.js"></script>
  <script src="lib/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="lib/easing/easing.min.js"></script>
  <script src="lib/wow/wow.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD8HeI8o-c1NppZA-92oYlXakhDPYR7XMY"></script>

  <script src="lib/waypoints/waypoints.min.js"></script>
  <script src="lib/counterup/counterup.min.js"></script>
  <script src="lib/superfish/hoverIntent.js"></script>
  <script src="lib/superfish/superfish.min.js"></script>

  <!-- Contact Form JavaScript File -->
  <script src="contactform/contactform.js"></script>

  <!-- Template Main Javascript File -->
  <script src="js/main.js"></script>

</body>
</html>
