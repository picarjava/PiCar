package com.admin.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.DigestService.DigestService;
import com.admin.model.*;
import com.mailService.MailService;




public class AdminServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) {  
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/	
				String str = req.getParameter("adminID");
				if (str==null || (str.trim()).length()==0) {
					errorMsgs.add("請輸入揪團檢舉編號");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/admin_select_page.jsp");
					failureView.forward(req,res);
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
				if (adminVO==null) {
					errorMsgs.add("查無資料");
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

			} catch (Exception e) {
				errorMsgs.add("無法取得資料"+e.getMessage());
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
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料"+e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/update_Admin_input.jsp");
					failureView.forward(req, res);
				}
			}	
			
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
					String passwordReg = "^[a-zA-Z0-9]{6,100}$";
					if (password==null || (password.trim()).length()==0) {
						errorMsgs.add("密碼請勿空白");
					} else if (!password.trim().matches(passwordReg)) {
						errorMsgs.add("密碼長度須為6-10之間，且只能使用英文字或數字");
					}
							
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
					adminVO.setPassword(password);	
					adminVO.setIsEmp(isEmp);
					
					if(!errorMsgs.isEmpty()) {
						req.setAttribute("adminVO", adminVO);
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/update_Admin_input.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					/***************************2.開始修改資料*****************************************/
					
					AdminService adminSvc = new AdminService();
					adminVO = adminSvc.updateAdmin(adminID, adminName, email ,password, isEmp);
					
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					req.setAttribute("adminVO", adminVO);
					String url = "/back-end/admin/admin_select_page.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/update_Admin_input.jsp");
					failureView.forward(req, res);
				}
			}
				
	        if ("insert".equals(action)) { 
				
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					
					String adminName = req.getParameter("adminName").trim();
					String adminNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
					if(adminName == null || (adminName.trim()).length()==0) {
						errorMsgs.add("管理員姓名請勿空白");
					} else if (!adminName.trim().matches(adminNameReg)) {
						errorMsgs.add("管理員姓名只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
					}
					
					/*********************************************/
					String email = req.getParameter("email").trim();
					String emailReg = "^\\w+\\.*\\w+@(\\w+\\.){1,5}[a-zA-Z]{2,3}$";
					if (email==null || (email.trim()).length()==0) {
						errorMsgs.add("信箱請勿空白");
					} else if (!email.trim().matches(emailReg)) {
						errorMsgs.add("信箱格式有問題，請確認。");
					}
					/*********************************************/
					String password = req.getParameter("password").trim();
					String[] array = new String[] {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
					String str = "";
					for (int i =1; i<=10;i++) {
						 int index = (int)(Math.random() * array.length+1);
						 str = str +array[index];
					}
					password = str;
					
					DigestService digestSvc = new DigestService();
					String digestpassword = digestSvc.digest(password);
							
					/*********************************************/	
					Integer isEmp = new Integer(req.getParameter("isEmp").trim());
					
//					try {
//						if (isEmp>1 ||isEmp.equals(null)) 
//							errorMsgs.add("處理狀態只能0或1");
//					} catch(NullPointerException e) {
//							errorMsgs.add("請輸入處理狀態");
//					}
					
					AdminVO adminVO = new AdminVO();
					
					adminVO.setAdminName(adminName);
					adminVO.setEmail(email);
					adminVO.setPassword(digestpassword);
					adminVO.setIsEmp(isEmp);
					
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("adminVO", adminVO);
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/addAdmin.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					/***************************2.開始新增資料*********************************************/
					AdminService adminSvc = new AdminService();
					adminVO = adminSvc.addAdmin(adminName, email ,digestpassword, isEmp);
					
					AdminService adminSvc1 = new AdminService();
					List<AdminVO> lastone = adminSvc1.getAll();
					String id = lastone.get(lastone.size()-1).getAdminID();
					
					/***************************3.將密碼Email出去***************************************/
				      String to = email;
				      String ch_name = adminName;
				      String passRandom = password;
				      String subject = "PICAR管理員密碼通知";
				      String messageText = "Hello　" + ch_name + "\n"+
				    		               "歡迎你加入PICAR大家庭！"+ "\n"  +
				                           "你的管理員編號為" + id +"\n"+
				                           "此為你之後的登入帳號，"+
				                           "請先以此密碼登入【 " + passRandom + " 】"+
				                           "\n" +"登入後台過後再修改你的密碼！謝謝！"; 
					
					  MailService mailService = new MailService();
				      mailService.sendMail(to, subject, messageText);
					
					/***************************4.新增完成,準備轉交(Send the Success view)***********/
					String url = "/back-end/admin/admin_select_page.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("資料有誤請再次確認");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/addAdmin.jsp");
					failureView.forward(req, res);
				}	
			}
				
			if ("delete".equals(action)) { 

				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					/***************************1.接收請求參數***************************************/
					String adminID = req.getParameter("adminID");
					
					/***************************2.開始刪除資料***************************************/
					AdminService adminSvc = new AdminService();
					adminSvc.deleteAdmin(adminID);
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/					
					String url = "/back-end/admin/admin_select_page.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/admin/admin_select_page.jsp");
					failureView.forward(req, res);
				}
			}
			
	        if ("updatePSW".equals(action)) { 
				
				try {
					
					String password = req.getParameter("newpassword1").trim();
					
					DigestService digestSvc = new DigestService();
					String digestpassword = digestSvc.digest(password);
							
					AdminVO adminVO = new AdminVO();
					
					adminVO.setPassword(digestpassword);
					
					/***************************2.開始新增資料*********************************************/
					AdminService adminSvc = new AdminService();
					adminVO = adminSvc.updatePSW(digestpassword);
					
					String url = "/back-end/index.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);	
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/addAdmin.jsp");
					failureView.forward(req, res);
				}	
			}
			
			
		}
	}
