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

public class MemberServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
//		PrintWriter out = res.getWriter();

		// getALL getALL getALL getALL getALL getALL getALL

//		if ("getAll".equals(action)) {
//
//			// 開始查詢資料
//			MemberDAO memberDAO = new MemberDAO();
//			List<MemberVO> list = memberDAO.getAll();
//
//			// 將資料存於set於session
//			HttpSession session = req.getSession();
//			session.setAttribute("list", list);
//
//			// 將控制權轉送給listAllMember_getFormSession.jsp
//			String url = "/member/listAllMember_getFormSession.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//			return;
//		}

		if ("getOne_For_Display".equals(action)) {

			// 建立錯誤的collection
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 開始驗證資料格式以及設定格式錯誤訊息
				String str = req.getParameter("memID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				String memID = null;
				try {
					memID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				// 開始查詢資料使用01，直接取DAO的值
//				MemberDAO memberDAO = new MemberDAO();
//				MemberVO memberVO = memberDAO.findByPrimaryKey(memID);
//				if (memberVO == null) {
//					errorMsgs.add("查無資料");
//				}
//
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/member/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}

				// 開始查詢資料使用01，使用facade模式，新增sevice.java為DAO跟controller的橋樑

				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(memID);
				if (memberVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				// 準備轉交
				req.setAttribute("memberVO", memberVO);

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/member/listOneMember.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {
			// 建立錯誤的collection
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				// 接收請求參數
				String memID = new String(req.getParameter("memID"));

				// 開始查詢
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(memID);
				req.setAttribute("memberVO", memberVO);
				// 查詢完成 轉交給update_member_input.jsp 可以顯示原始的資料值

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/member/update_member_input.jsp");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料：" + e.getMessage());
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/member/listOnemember");
				successView.forward(req, res);
			}

		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String memID = new String(req.getParameter("memID").trim());

				String name = req.getParameter("name");
				String nameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("員工姓名~請勿空白");
				} else if (!name.trim().matches(nameReg)) {
					errorMsgs.add("會員姓名請輸入 中文、英文字母、數字和   \" , \"  , 且長度必需在2到20之間");
				}

				String email = req.getParameter("email");
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("EMAI請勿空白");
				}
				String password = req.getParameter("password");
				if (password == null || password.trim().length() == 0) {
					errorMsgs.add("password請勿空白");
				}
				String phone = req.getParameter("phone");
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("phone請勿空白");
				}
				String creditcard = req.getParameter("creditcard");
				if (creditcard == null || creditcard.trim().length() == 0) {
					errorMsgs.add("creditcard請勿空白");
				}
				Integer token = null;
				try {
//					MemberDAO memberDAO = new MemberDAO();

//					token = memberDAO.getSumAmount(memID);
					token = new Integer(req.getParameter("token").trim());
//					token = token + memberDAO.getSumAmount(memID);
				} catch (NumberFormatException e) {
					token = 0;
					errorMsgs.add("代幣請填數字.");
				}
				Integer activityToken = null;
				try {
					activityToken = new Integer(req.getParameter("activityToken").trim());
				} catch (NumberFormatException e) {
					activityToken = 100;
					errorMsgs.add("活動代幣請填數字.");
				}
				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					birthday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer pet = new Integer(req.getParameter("pet"));
				Integer smoke = new Integer(req.getParameter("smoke"));
				Integer gender = new Integer(req.getParameter("gender"));
				Integer verified = new Integer(req.getParameter("verified"));
				Integer babySeat = new Integer(req.getParameter("babySeat"));
//				
				MemberVO memberVO = new MemberVO();
				memberVO.setMemID(memID);
				memberVO.setName(name);
				memberVO.setEmail(email);
				memberVO.setPassword(password);
				memberVO.setPhone(phone);
				memberVO.setCreditcard(creditcard);
				memberVO.setPet(pet);
				memberVO.setSmoke(smoke);
				memberVO.setGender(gender);
				memberVO.setToken(token);
				memberVO.setActivityToken(activityToken);
				memberVO.setBirthday(birthday);
				memberVO.setVerified(verified);
				memberVO.setBabySeat(babySeat);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/update_member_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				// 開始修改資料
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.updateMember(memID, name, email, password, phone, creditcard, pet, smoke, gender,
						token, activityToken, birthday, verified, babySeat);
				req.setAttribute("memberVO", memberVO);

				RequestDispatcher successView = req.getRequestDispatcher("/back-end/member/listOneMember.jsp");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料：" + e.getMessage());
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/member/update_member_input.jsp");
				successView.forward(req, res);

			}
		}

	}

}
