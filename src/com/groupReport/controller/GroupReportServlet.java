package com.groupReport.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.driverReport.model.DriverReportService;
import com.driverReport.model.DriverReportVO;
import com.groupReport.model.*;

public class GroupReportServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) 
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
				String str = req.getParameter("greportID");
				if (str==null || (str.trim()).length()==0) {
					errorMsgs.add("請輸入揪團檢舉編號");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groupReport/greport_select_page.jsp");
					failureView.forward(req,res);
					return; 
				}
				
				String greportID = null;
				try {
					greportID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("揪團檢舉單號格式不對");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groupReport/greport_select_page.jsp");
					failureView.forward(req, res);
					return;
				}
			
				/***************************2.開始查詢資料*****************************************/
				
				GroupReportService groupReportSvc = new GroupReportService();
				GroupReportVO groupReportVO = groupReportSvc.getOneGroupReport(greportID);
				if (groupReportVO==null) {
					errorMsgs.add("查無資料");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groupReport/greport_select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("groupReportVO", groupReportVO);
				String url = "/back-end/groupReport/listOneGroupReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groupReport/greport_select_page.jsp");
				failureView.forward(req, res);
			}
			
		}	
		
			if("getOne_For_Update".equals(action)) { 
				
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數****************************************/
					String greportID = req.getParameter("greportID");
				
					/***************************2.開始查詢資料****************************************/
					
					GroupReportService groupReportSvc = new GroupReportService();
					GroupReportVO groupReportVO = groupReportSvc.getOneGroupReport(greportID);
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("groupReportVO", groupReportVO);
					String url = "/back-end/groupReport/update_GroupReport_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料"+e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groupReport/update_GroupReport_input.jsp");
					failureView.forward(req, res);
				}
			}	
			
			if("update".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String greportID = req.getParameter("greportID");
					
					String memID = req.getParameter("memID").trim();
					String memIDReg = "^[(M)(0-9]{4}$";
					if(memID == null || (memID.trim()).length()==0) {
						errorMsgs.add("會員編號請勿空白");
					} else if (!memID.trim().matches(memIDReg)) {
						errorMsgs.add("會員編號輸入格式有誤");
					}
					
					String groupID = req.getParameter("groupID").trim();
					String groupIDReg = "^[(G)(0-9)]{4}$";
					if(groupID == null || (groupID.trim()).length()==0) {
						errorMsgs.add("揪團編號請勿空白");
					} else if(!groupID.trim().matches(groupIDReg)) {
						errorMsgs.add("揪團編號輸入格式有誤");
					}
					
					String adminID = req.getParameter("adminID").trim();
					String adminIDreg = "^[(A)(0-9)]{4}$";
					if(adminID == null || (adminID.trim()).length() == 0) {
						errorMsgs.add("管理員編號請勿空白");
					} else if (!adminID.trim().matches(adminIDreg)) {
						errorMsgs.add("管理員編號輸入格式有誤");
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
						
					Integer state = null; 
					try {
						state = new Integer(req.getParameter("state").trim());
					if (state>1 || state==null) 
							errorMsgs.add("處理狀態只能0或1");
					} catch (Exception e) {
						errorMsgs.add("處理狀態錯誤" + e.getMessage());
					}
					
					
					GroupReportVO groupReportVO = new GroupReportVO();
					
					groupReportVO.setGreportID(greportID);
					groupReportVO.setMemID(memID);
					groupReportVO.setGroupID(groupID);
					groupReportVO.setAdminID(adminID);
					groupReportVO.setContent(content);
					groupReportVO.setTime(time);
					groupReportVO.setState(state);
					
					if(!errorMsgs.isEmpty()) {
						req.setAttribute("groupReportVO", groupReportVO);
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groupReport/update_GroupReport_input.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					/***************************2.開始修改資料*****************************************/
					
					GroupReportService GroupReportSvc = new GroupReportService();
					groupReportVO = GroupReportSvc.updateGroupReport(greportID, memID, groupID, adminID, content, time, state);
					
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					req.setAttribute("groupReportVO", groupReportVO);
					String url = "/back-end/groupReport/greport_select_page.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groupReport/update_GroupReport_input.jsp");
					failureView.forward(req, res);
				}
			}
				
	        if ("insert".equals(action)) { 
				
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					
					String memID = req.getParameter("memID").trim();
					String memIDReg = "^[(M)(0-9_)]{4}$";
					if(memID == null || memID.trim().length()==0) {
						errorMsgs.add("會員編號請勿空白");
					} else if (!memID.trim().matches(memIDReg)) {
						errorMsgs.add("輸入格式有誤");
					}

					String groupID = req.getParameter("groupID").trim();
					String groupIDReg = "^[(G)(0-9_)]{4}$";
					if(groupID == null || (groupID.trim()).length()==0) {
						errorMsgs.add("揪團編號請勿空白");
					} else if(!groupID.trim().matches(groupIDReg)) {
						errorMsgs.add("揪團編號輸入格式有誤");
					}
					
					String adminID = req.getParameter("adminID").trim();
					String adminIDreg = "^[(A)(0-9)]{4}$";
					if(adminID == null || (adminID.trim()).length() == 0) {
						errorMsgs.add("管理員編號請勿空白");
					} else if (!adminID.trim().matches(adminIDreg)) {
						errorMsgs.add("管理員編號輸入格式有誤");
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
					
						
					GroupReportVO groupReportVO = new GroupReportVO();
						
					groupReportVO.setMemID(memID);
					groupReportVO.setGroupID(groupID);
					groupReportVO.setAdminID(adminID);
					groupReportVO.setContent(content);
					groupReportVO.setTime(time);
					groupReportVO.setState(state);
						
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("groupReportVO", groupReportVO); 
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groupReport/addGroupReport.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					/***************************2.開始新增資料***************************************/
					GroupReportService groupReportSvc = new GroupReportService();
					groupReportVO = groupReportSvc.addGroupReport(memID, groupID, adminID, content, time, state);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/back-end/groupReport/greport_select_page.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("資料有誤請再次確認");
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groupReport/addGroupReport.jsp");
					failureView.forward(req, res);
				}
			}
			
			
			if ("delete".equals(action)) { 

				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					/***************************1.接收請求參數***************************************/
					String greportID = req.getParameter("greportID");
					
					/***************************2.開始刪除資料***************************************/
					GroupReportService groupReportSvc = new GroupReportService();
					groupReportSvc.deleteGroupReport(greportID);
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/					
					String url = "/back-end/groupReport/greport_select_page.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/groupReport/greport_select_page.jsp");
					failureView.forward(req, res);
				}
			}
		}
	}
