<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="sidebar-wrapper">
        <ul class="nav">
          <li class="nav-item active  ">
            <a class="nav-link" href="<%=request.getServletContext().getContextPath()%>/back-end/admin/admin_select_page.jsp">
              <i class="material-icons">person</i>
              <p>管理員管理</p>
            </a>
          </li>
          <li class="nav-item ">
            <a class="nav-link" href="<%=request.getServletContext().getContextPath()%>/back-end/member/listAllmember_byDAO.jsp">
              <i class="material-icons">person</i>
              <p>乘客會員管理</p>
            </a>
          </li>
          <li class="nav-item ">
            <a class="nav-link" href="<%=request.getServletContext().getContextPath()%>/back-end/driver/driverMemberManagement.jsp">
              <i class="material-icons">person</i>
              <p>司機會員管理</p>
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
              <i class="material-icons">location_ons</i>
              <p>訂單管理</p>
            </a>
          </li>
          <li class="nav-item ">
            <a class="nav-link" href="<%=request.getServletContext().getContextPath()%>/back-end/activity/homeActivity.jsp">
              <i class="material-icons">bubble_chart</i>
              <p>活動資訊管理</p>
            </a>
          </li>
        </ul>
</div>