package com.admin.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.admin.model.AdminService;
import com.admin.model.AdminVO;



public class AdminServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("adminID");
				if(str == null || (str.trim()).length()==0) {
					errorMsgs.add("請輸入管理員編號");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/admin_select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String adminID = null;
				try {
					adminID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("管理員編號格式不對");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/admin_select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				
				AdminService adminSvc = new AdminService();
				AdminVO adminVO = adminSvc.getOneAdmin(adminID);
				if (adminVO == null) {
					errorMsgs.add("查無此筆資料");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/admin_select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				
				req.setAttribute("adminVO", adminVO);
				String url = "/back-end/admin/listOneAdmin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" +e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/admin_select_page.jsp");
				failureView.forward(req, res);
			}		
		}
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String adminID = req.getParameter("adminID");
				
				/***************************2.開始查詢資料****************************************/
				AdminService adminSvc = new AdminService();
				AdminVO adminVO = adminSvc.getOneAdmin(adminID);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("adminVO", adminVO);
				String url = "/back-end/admin/update_Admin_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/***************************其他可能的錯誤處理**********************************/
			} catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/update_Admin_input.jsp");
				failureView.forward(req, res);
			}
		} 
 			
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String adminID = req.getParameter("adminID");
				
				
			} catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/update_Admin_input.jsp");
				failureView.forward(req, res);
			}
			
			
			
			
			
		}
		
		
		
	}

}
