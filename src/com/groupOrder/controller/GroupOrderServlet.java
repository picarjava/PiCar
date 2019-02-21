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

import com.groupBand.model.GroupBandService;
import com.groupBand.model.GroupBandVO;

/**
 * Servlet implementation class GroupOrderServlet
 */
//@WebServlet("/GroupOrderServlet")
public class GroupOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
				String groupID = req.getParameter("groupID");
				if (groupID == null || (groupID.trim()).length() == 0) {
					errorMsgs.add("錯誤拉");
				}
				GroupBandService groupBandService = new GroupBandService();
				GroupBandVO groupBandVO = groupBandService.getOneGroupBand(groupID);
				if (groupBandVO == null) {
					errorMsgs.add("格式不正確");
				}
			
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/groupBand/SelectGroupBand.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
			
			
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/groupBand/SelectGroupBand.jsp");
			failureView.forward(req, res);
		}
		
	}
	}
}
