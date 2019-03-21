package com.groupReport.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.groupReport.model.*;
import com.groupBand.model.*;



public class FindGroupServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
			doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
			
			if("FindOne".equals(action)) {  
				
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/	
					String groupID = req.getParameter("groupID");

					if(!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groupReport/greport_select_page.jsp");
						failureView.forward(req,res);
						return; 
					}
									
					/***************************2.開始查詢資料*****************************************/
					
					GroupBandService groupBandSvc = new GroupBandService();
					GroupBandVO groupBandVO = groupBandSvc.findGroupID(groupID);
					
					System.out.println(groupID);
					System.out.println(groupBandVO);
					
					if (groupBandVO==null) {
						errorMsgs.add("查無資料");
					}
					
					if(!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groupReport/greport_select_page.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("groupBandVO", groupBandVO);
					String url = "/back-end/groupReport/listOneGroupReport.jsp";   //********************
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);

				} catch (Exception e) {
					errorMsgs.add("無法取得資料"+e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groupReport/greport_select_page.jsp");
					failureView.forward(req, res);
				}
				
			}	
		}
	}
