package com.groupOrder.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.groupOrder.model.*;

/**
 * Servlet implementation class GroupOrderServlet
 */
//@WebServlet("/GroupOrderServlet")
public class GroupOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		
		
		if("update".equals(action)) {
		
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			GroupOrderService groupOrderService = new GroupOrderService();
			try {
				
				GroupOrderVO groupOrderVO =new GroupOrderVO();
				
				String gorderID = req.getParameter("gorderID");
				
				String driverID = req.getParameter("driverID");
				
				String memID = req.getParameter("memID");
				
				Integer state =new Integer(req.getParameter("state").trim());
				
				Integer totalAmout =new Integer(req.getParameter("totalAmout").trim());
				
				java.sql.Timestamp launchTime = null;
				try {
					launchTime = java.sql.Timestamp.valueOf(req.getParameter("launchTime").trim());
					
				} catch (IllegalArgumentException e) {
					launchTime=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Timestamp startTime = null;
				try {
					startTime = java.sql.Timestamp.valueOf(req.getParameter("startTime").trim());
					
				} catch (IllegalArgumentException e) {
					startTime=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Timestamp endTime = null;
				try {
					endTime = java.sql.Timestamp.valueOf(req.getParameter("endTime").trim());
					
				} catch (IllegalArgumentException e) {
					endTime=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Double startLng = null;
				try {
					startLng = new Double(req.getParameter("startLng").trim());
				} catch (NumberFormatException e) {
					startLng = 0.0;
					errorMsgs.add("資料有誤.");
				}
				
				Double startLat = null;
				try {
					startLat = new Double(req.getParameter("startLat").trim());
				} catch (NumberFormatException e) {
					startLat = 0.0;
					errorMsgs.add("資料有誤.");
				}
				
				Double endLng = null;
				try {
					endLng = new Double(req.getParameter("endLng").trim());
				} catch (NumberFormatException e) {
					endLng = 0.0;
					errorMsgs.add("資料有誤.");
				}
				
				Double endLat = null;
				try {
					endLat = new Double(req.getParameter("endLat").trim());
				} catch (NumberFormatException e) {
					endLat = 0.0;
					errorMsgs.add("資料有誤.");
				}
	
				Integer orderType =new Integer (req.getParameter("orderType").trim());
				
				Integer rate =new Integer (req.getParameter("rate").trim());
				
				String note =req.getParameter("note");
//				String notes = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$";
//				if (note == null || note.trim().length() == 0) {
//					errorMsgs.add("備註: 請勿空白");
//				} else if(!note.trim().matches(notes)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("備註: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
			
				System.out.println(gorderID);
				System.out.println(driverID);
				System.out.println(memID);
				System.out.println(state);
				System.out.println(totalAmout);
				System.out.println(launchTime);
				System.out.println(startTime);
				System.out.println(endTime);
				System.out.println(startLng);
				System.out.println(startLat);
				System.out.println(endLng);
				System.out.println(endLat);
				System.out.println(orderType);
				System.out.println(rate);
				System.out.println(note);
				
				groupOrderVO.setGorderID(gorderID);
				groupOrderVO.setDriverID(driverID);
				groupOrderVO.setMemID(memID);
				groupOrderVO.setState(state);
				groupOrderVO.setTotalAmout(totalAmout);
				groupOrderVO.setLaunchTime(launchTime);
				groupOrderVO.setStartTime(startTime);
				groupOrderVO.setEndTime(endTime);
				groupOrderVO.setStartLng(startLng);
				groupOrderVO.setStartLat(startLat);
				groupOrderVO.setEndLng(endLng);
				groupOrderVO.setEndLat(endLat);
				groupOrderVO.setOrderType(orderType);
				groupOrderVO.setRate(rate);
				groupOrderVO.setNote(note);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("GroupOrderVO", groupOrderVO); // �t����J�榡���~��GroupBandVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/groupOrder/updateGroupOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				System.out.println("++++++++++++++++++");
				groupOrderVO =groupOrderService.updategroupOrderVO(driverID, memID, state, totalAmout, startTime, endTime, startLng, startLat, endLng, endLat, orderType, rate, note);

				String url = "/front-end/groupOrder/ListAllGroupOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);	
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/groupOrder/updateGroupOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String groupOrderno = req.getParameter("groupOrderno");
				if (groupOrderno == null || (groupOrderno.trim()).length() == 0) {
					errorMsgs.add("錯誤拉");
				}
				GroupOrderService groupOrderService = new GroupOrderService();
				GroupOrderVO groupOrderVO = groupOrderService.getOneGroupOrder(groupOrderno);
				if (groupOrderVO == null) {
					errorMsgs.add("格式不正確");
				}
			
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/groupOrder/SelectGroupOrder.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
			req.setAttribute("groupOrderVO", groupOrderVO);
			String url="/front-end/groupOrder/ListOneGroupOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
			successView.forward(req, res);
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/groupOrder/SelectGroupOrder.jsp");
			failureView.forward(req, res);
		}
		
	}
		
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String groupOrderno = req.getParameter("gorderID");
				if (groupOrderno == null || (groupOrderno.trim()).length() == 0) {
					errorMsgs.add("錯誤拉");
				}
				GroupOrderService groupOrderService = new GroupOrderService();
				GroupOrderVO groupOrderVO = groupOrderService.getOneGroupOrder(groupOrderno);
				if (groupOrderVO == null) {
					errorMsgs.add("格式不正確");
				}
			
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/groupOrder/ListOneGroupOrder.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
			req.setAttribute("groupOrderVO", groupOrderVO);
			String url="/front-end/groupOrder/updateGroupOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
			successView.forward(req, res);
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/groupOrder/ListOneGroupOrder.jsp");
			failureView.forward(req, res);
		}
			
		}

		
	}
}
