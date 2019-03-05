package com.login.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LogoutHandlerBackEnd extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		if("logout".equals(action)) {  
     
			HttpSession session  = req.getSession();
			    session.invalidate();                               
			    res.sendRedirect(req.getContextPath() + "/back-end/login/login.html");          

			}
    }

}
