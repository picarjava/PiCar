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
