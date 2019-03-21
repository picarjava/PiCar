package com.admin.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.DigestService.DigestService;
import com.admin.model.*;
import com.mailService.MailService;

public class AdminSelfServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
			
			if("update".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String adminID = req.getParameter("adminID");
					
					String adminName = req.getParameter("adminName").trim();
					String adminNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
					if(adminName == null || (adminName.trim()).length()==0) {
						errorMsgs.add("管理員姓名請勿空白");
					} else if (!adminName.trim().matches(adminNameReg)) {
						errorMsgs.add("管理員姓名只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
					}
					
					/*********************************************/
					String email = req.getParameter("email");
					String emailReg = "^\\w+\\.*\\w+@(\\w+\\.){1,5}[a-zA-Z]{2,3}$";
					if (email==null || (email.trim()).length()==0) {
						errorMsgs.add("電子信箱請勿空白");
					} else if (!email.trim().matches(emailReg)) {
						errorMsgs.add("信箱格式有問題，請確認。");
					}
					/*********************************************/
					String password = req.getParameter("password");

					
					DigestService digestSvc = new DigestService();
					String digestpassword = digestSvc.digest(password);
					
					
							
					Integer isEmp = null;
					try {
						isEmp = new Integer(req.getParameter("isEmp").trim());
						if (isEmp >1 || isEmp==null)
							errorMsgs.add("在職狀態只能為0或1");
					} catch (Exception e) {
						errorMsgs.add("在職狀態錯誤"+e.getMessage());
					}
					
					
					AdminVO adminVO = new AdminVO();
					
					adminVO.setAdminID(adminID);
					adminVO.setAdminName(adminName);
					adminVO.setEmail(email);
					adminVO.setPassword(digestpassword);	
					adminVO.setIsEmp(isEmp);
					
					if(!errorMsgs.isEmpty()) {
						req.setAttribute("adminVO", adminVO);
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/update_Admin_input.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					/***************************2.開始修改資料*****************************************/

					AdminService adminSvc = new AdminService();
					adminVO = adminSvc.updateAdmin(adminID, adminName, email ,digestpassword, isEmp);
					
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					req.setAttribute("adminVO", adminVO);
					
					String url = "/back-end/backHome.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);	

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/update_Admin_input.jsp");
					failureView.forward(req, res);
				}
			}
				
		}
	}
