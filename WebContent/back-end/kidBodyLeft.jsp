<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

		<div class="sidebar" data-color="azure" data-color="" data-background-color="black"
			data-image="<%=request.getServletContext().getContextPath()%>/back-end/assets/img/sidebar-1.jpg">
<!-- 			data-image="../assets/img/sidebar-1.jpg"> -->
			<div class="logo">
				<a href="<%=request.getServletContext().getContextPath()%>/back-end/backHome.jsp" class="simple-text logo-normal"> PICAR </a>
			</div>
			<div class="sidebar-wrapper">
				<ul class="nav">
<!-- 			<li class="nav-item active  ">聚焦 -->
			<li class="nav-item ">
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
			<i class="material-icons">dashboard</i>
					<p>檢舉司機管理</p>
					</a></li>
			<li class="nav-item ">
            <a class="nav-link" href="<%=request.getServletContext().getContextPath()%>/back-end/member/listAllmember_byDAO.jsp">
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
            <a class="nav-link" href="<%=request.getServletContext().getContextPath()%>/back-end/rate/listAllRate.jsp">
              <i class="material-icons">content_paste</i>
              <p>資費管理</p>
            </a>
          </li>
          <li class="nav-item ">
            <a class="nav-link" href="<%=request.getServletContext().getContextPath()%>/back-end/singleOrder/orderManagement_page.jsp">
              <i class="material-icons">content_paste</i>
              <p>訂單管理</p>
            </a>
          </li>
<!--           <li class="nav-item "> -->
<%--             <a class="nav-link" href="<%=request.getServletContext().getContextPath()%>/back-end/rate/select_page.jsp"> --%>
<!--               <i class="material-icons">content_paste</i> -->
<!--               <p>乘客推播訊息設定</p> -->
<!--             </a> -->
<!--           </li> -->
          <li class="nav-item ">
            <a class="nav-link" href="<%=request.getServletContext().getContextPath()%>/back-end/activity/homeActivity.jsp">
              <i class="material-icons">bubble_chart</i>
              <p>活動資訊管理</p>
            </a>
          </li>
<!--           <li class="nav-item "> -->
<!--             <a class="nav-link" href="./notifications.html"> -->
<!--               <i class="material-icons">notifications</i> -->
<!--               <p>即時客服交談</p> -->
<!--             </a> -->
<!--           </li> -->
		</ul>
			</div>
		</div>