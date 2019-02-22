package com.driverReport.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.admin.model.*;
import com.driverReport.model.*;


public class DriverReportServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		doPost(req, res);
	}
		
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException , IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
			
		if("getOne_For_Display".equals(action)) { //來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("dreportID");
				if (str ==null || (str.trim()).length()==0) {
					errorMsgs.add("請輸入司機檢舉單編號");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()) { 
					RequestDispatcher failureView = req.getRequestDispatcher("/driverReport/select_page.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				String dreportID = null;
				try {
					dreportID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("司機檢舉單號格式不對");
				}
				// Send the use back to the form, if there were errors
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/driverReport/select_page.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				
				DriverReportService driverReportSvc = new DriverReportService();
				DriverReportVO driverReportVO = driverReportSvc.getOneDriverReport(dreportID);
				if(driverReportVO==null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/driverReport/select_page.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("driverReportVO", driverReportVO); //資料庫取出的VO物件,存入req
				String url = "/driverReport/listOneDriverReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);  //成功轉交到listOneDriverReport.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/
							
			} catch (Exception e) {
				errorMsgs.add("無法取得資料"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/driverReport/select_page.jsp");
				failureView.forward(req, res);
			} 
		}
			
			if("getOne_For_Update".equals(action)) { // 來自listAllDriverReport.jsp的請求
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數****************************************/
					String dreportID = req.getParameter("dreportID");
				
					/***************************2.開始查詢資料****************************************/
					
					DriverReportService driverReportSvc = new DriverReportService();
					DriverReportVO driverReportVO = driverReportSvc.getOneDriverReport(dreportID);
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("driverReportVO", driverReportVO);
					String url = "/driverReport/update_DriverReport_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_DriverReport_input.jsp
					successView.forward(req, res);
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料"+e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/driverReport/listAllDriverReport.jsp");
					failureView.forward(req, res);
				}
			}	
			if("update".equals(action)) {  // 來自update_DriverReport_input.jsp的請求
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
					
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String dreportID = req.getParameter("dreportID").trim();
						
					String memID = req.getParameter("memID").trim();
					String memIDReg = "^[(M)(0-9)]{4}$";
					if(memID == null || memID.trim().length()==0) {
						errorMsgs.add("會員編號請勿空白");
					} else if (!memID.trim().matches(memIDReg)) {
						errorMsgs.add("輸入格式有誤");
					}
					
					
				
					String adminID = req.getParameter("adminID").trim();
					String adminIDreg = "^[(A)(0-9)]{4}$";
					if(adminID == null || adminID.trim().length() == 0) {
						errorMsgs.add("管理員編號請勿空白");
					} else if (!adminID.trim().matches(adminIDreg)) {
						errorMsgs.add("輸入格式有誤");
					}
					
					//捕捉不到錯誤故用此方法抓?!
					AdminService svc = new AdminService();
					AdminVO vo = svc.getOneAdmin(adminID);
					if (vo == null) {
						errorMsgs.add("查無此管理員");
					}
					//


					String orderID = req.getParameter("orderID").trim();
					if(orderID == null || orderID.trim().length() == 0) {
						errorMsgs.add("訂單編號請勿空白");
					}
						
					String content = req.getParameter("content");
					String contentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
					if (content == null ||content.trim().length()==0) {
						errorMsgs.add("檢舉內容請勿空白");
					} else if (!content.trim().matches(contentReg)) {
						errorMsgs.add("檢舉內容: 只能用中、英文字母、數字和_ , 且長度必需在10到50之間");
					}
						
					java.sql.Date time = null;
					try {
						time = java.sql.Date.valueOf(req.getParameter("time").trim());		
					} catch (IllegalArgumentException e) {
						time = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期");
					}
						
					Integer state = null; 
					try {
						state = new Integer(req.getParameter("state").trim());
					if (state>1 || state==null) 
							errorMsgs.add("處理狀態只能0或1");
					} catch (Exception e) {
						errorMsgs.add("處理狀態錯誤" + e.getMessage());
					}
					
					
					DriverReportVO driverReportVO = new DriverReportVO();
						
					driverReportVO.setDreportID(dreportID);
					driverReportVO.setMemID(memID);
					driverReportVO.setAdminID(adminID);
					driverReportVO.setOrderID(orderID);
					driverReportVO.setContent(content);
					driverReportVO.setTime(time);
					driverReportVO.setState(state);
						
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("driverReportVO", driverReportVO); // 含有輸入格式錯誤的driverReportVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/driverReport/update_DriverReport_input.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
						
					/***************************2.開始修改資料*****************************************/
					DriverReportService driverReportSvc = new DriverReportService();
					driverReportVO = driverReportSvc.updateDriverReport(dreportID, memID, adminID, orderID, content,time, state);
					
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					req.setAttribute("driverReportVO", driverReportVO); // 資料庫update成功後,正確的的driverReportVO物件,存入req
					String url = "/driverReport/select_page.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneDriverReport.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/driverReport/update_DriverReport_input.jsp");
					failureView.forward(req, res);
				}
			}
						
	        if ("insert".equals(action)) { 
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					//String dreportID = req.getParameter("dreportID").trim();
					
					String memID = req.getParameter("memID").trim();
					String memIDReg = "^[(M)(0-9_)]{4}$";
					if(memID == null || memID.trim().length()==0) {
						errorMsgs.add("會員編號請勿空白");
					} else if (!memID.trim().matches(memIDReg)) {
						errorMsgs.add("輸入格式有誤");
					}

					String adminID = req.getParameter("adminID").trim();
					if(adminID == null || adminID.trim().length() == 0) {
						errorMsgs.add("管理員編號請勿空白");
					}
					String orderID = req.getParameter("orderID").trim();
					if(orderID == null || orderID.trim().length() == 0) {
						errorMsgs.add("訂單編號請勿空白");
					}
						
					String content = req.getParameter("content");
					String contentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{10,50}$";
					if (content == null ||content.trim().length()==0) {
						errorMsgs.add("檢舉內容請勿空白");
					} else if (!content.trim().matches(contentReg)) {
						errorMsgs.add("檢舉內容: 只能用中、英文字母、數字和_ , 且長度必需在10到50之間");
					}
						
					java.sql.Date time = null;
					try {
						time = java.sql.Date.valueOf(req.getParameter("time").trim());		
					} catch (IllegalArgumentException e) {
						time = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期");
					}
						
					Integer state = new Integer(req.getParameter("state").trim());
					
					try {
						if (state>1 ||state.equals(null)) 
							errorMsgs.add("處理狀態只能0或1");
					} catch(NullPointerException e) {
							errorMsgs.add("請輸入處理狀態");
					}
					
						
					DriverReportVO driverReportVO = new DriverReportVO();
						
					driverReportVO.setMemID(memID);
					driverReportVO.setAdminID(adminID);
					driverReportVO.setOrderID(orderID);
					driverReportVO.setContent(content);
					driverReportVO.setTime(time);
					driverReportVO.setState(state);
						
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("driverReportVO", driverReportVO); // 含有輸入格式錯誤的driverReportVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/driverReport/addDriverReport.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					/***************************2.開始新增資料***************************************/
					DriverReportService driverReportSvc = new DriverReportService();
					driverReportVO = driverReportSvc.addDriverReport(memID, adminID, orderID, content, time, state);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/driverReport/select_page.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllDriverReport.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("資料有誤請再次確認");
					RequestDispatcher failureView = req.getRequestDispatcher("/driverReport/addDriverReport.jsp");
					failureView.forward(req, res);
				}
			}
			
			
			if ("delete".equals(action)) { // 來自listAllDriverReport.jsp

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					/***************************1.接收請求參數***************************************/
					String dreportID = req.getParameter("dreportID");
					
					/***************************2.開始刪除資料***************************************/
					DriverReportService driverReportSvc = new DriverReportService();
					driverReportSvc.deleteDriverReport(dreportID);
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/					
					String url = "/driverReport/select_page.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/driverReport/select_page.jsp");
					failureView.forward(req, res);
				}
			}
		}
	}
