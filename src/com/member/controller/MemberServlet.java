package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import com.member.model.*;
import java.util.LinkedList;
/**
 * Servlet implementation class MemberServlet
 */

public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
//		PrintWriter out = res.getWriter();
		if ("getAll".equals(action)) {
			
			// 開始查詢資料
			MemberDAO memberDAO = new MemberDAO();
			List<MemberVO> list = memberDAO.getAll();
			
//			將資料存於set於session
			HttpSession session = req.getSession();
			session.setAttribute("list", list);
			
//			將控制權轉送給listAllMember_getFormSession.jsp
			String url = "/member/listAllMember_getFormSession.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		
		
		if ("getOne_For_Display".equals(action)) { 
			
			//建立錯誤的collection			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			//開始驗證資料格式以及設定格式錯誤訊息			
			String str = req.getParameter("memID");
			if (str ==null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入會員編號");
			}			
			
			if (!errorMsgs.isEmpty()){
				RequestDispatcher failureView = req.getRequestDispatcher("/member/select_page.jsp");
				failureView.forward(req,res);
				return;
			}
			
			String memID = null;
			try {
				memID = new String(str);
			}catch(Exception e) {
				errorMsgs.add("員工編號格式不正確");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/member/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
		
			//開始查詢資料
			MemberDAO memberDAO = new MemberDAO();
			MemberVO memberVO = memberDAO.findByPrimaryKey(memID);
			if (memberVO == null) {
				errorMsgs.add("查無資料");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/member/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			//準備轉交
			req.setAttribute("memberVO", memberVO);
			String url = "/member/listOneMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
		
		
		
//		MemberDAO memberDAO = new MemberDAO();
//		MemberVO memberVO = memberDAO.findByPrimaryKey(action);
//		System.out.println(memberVO.getCreditcard());
//		out.println(memberVO.getName());
		
//		List<MemberVO> list = memberDAO.getAll();
//		for(MemberVO memberVO2 : list) {
//			out.println(memberVO2.getName());
//			out.println(memberVO2.getActivityToken()); 
//		}
		
		
		
//		memberDAO.delete("ation");
//		out.print("delete ok");
		
	}

}
