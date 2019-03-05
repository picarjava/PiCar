package com.login.filter;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.admin.model.*;


public class LoginHandlerBackEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;

  public void doPost(HttpServletRequest req, HttpServletResponse res)
                                throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    res.setContentType("text/html; charset=UTF-8");
    PrintWriter out = res.getWriter();

    // 【取得管理員編號(adminID) 密碼(password)】
    String adminID = req.getParameter("adminID");
    String password = req.getParameter("password");
    //透過AdminService的loginAdmin方法取得資料庫資料
	AdminService adminSvc = new AdminService();
	AdminVO adminVO = adminSvc.loginAdmin(adminID, password);
	

    // 【檢查該帳號 , 密碼是否有效】
    if (adminVO==null) {          //【帳號 , 密碼無效時】
	     out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
	     out.println("<BODY>你的帳號 , 密碼無效!<BR>");
	     out.println("請按此重新登入");
	     out.println("<A HREF=login.html>重新登入</A>");
	     out.println("</BODY></HTML>");
    }else {                                       //【帳號 , 密碼有效時, 才做以下工作】
      HttpSession session = req.getSession();

      session.setAttribute("adminVO", adminVO);   //*工作1: 才在session內做已經登入過的標識

       try {                                                        
         String location = (String) session.getAttribute("location");
         if (location != null) {
           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
           res.sendRedirect(location);            
           return;
         }
       }catch (Exception ignored) { }
       
      res.sendRedirect(req.getContextPath()+"/back-end/index.jsp");  //*工作3: (-->如無來源網頁:則重導至login_success.jsp)
    }
    
  }
}