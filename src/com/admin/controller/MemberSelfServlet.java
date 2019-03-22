package com.admin.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.DigestService.DigestService;
import com.admin.model.*;
import com.mailService.MailService;
import com.member.model.MemberService;
import com.member.model.MemberVO;

public class MemberSelfServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String adminID = req.getParameter("adminID");

				String adminName = req.getParameter("adminName").trim();
				String adminNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (adminName == null || (adminName.trim()).length() == 0) {
					errorMsgs.add("管理員姓名請勿空白");
				} else if (!adminName.trim().matches(adminNameReg)) {
					errorMsgs.add("管理員姓名只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				/*********************************************/
				String email = req.getParameter("email");
				String emailReg = "^[a-zA-Z0-9@.]{6,50}$";
				if (email == null || (email.trim()).length() == 0) {
					errorMsgs.add("電子信箱請勿空白");
				} else if (!email.trim().matches(emailReg)) {
					errorMsgs.add("電子信箱長度須為6-50之間，且只能使用英文字或數字");
				}
				/*********************************************/
				String password = req.getParameter("password");

				DigestService digestSvc = new DigestService();
				String digestpassword = digestSvc.digest(password);

				Integer isEmp = null;
				try {
					isEmp = new Integer(req.getParameter("isEmp").trim());
					if (isEmp > 1 || isEmp == null)
						errorMsgs.add("在職狀態只能為0或1");
				} catch (Exception e) {
					errorMsgs.add("在職狀態錯誤" + e.getMessage());
				}

				AdminVO adminVO = new AdminVO();

				adminVO.setAdminID(adminID);
				adminVO.setAdminName(adminName);
				adminVO.setEmail(email);
				adminVO.setPassword(digestpassword);
				adminVO.setIsEmp(isEmp);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adminVO", adminVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/update_Admin_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/

				AdminService adminSvc = new AdminService();
				adminVO = adminSvc.updateAdmin(adminID, adminName, email, digestpassword, isEmp);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("adminVO", adminVO);

				String url = "/back-end/backHome.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/update_Admin_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("modify_password".equals(action)) {

			String memID = req.getParameter("memID");
			req.getSession().setAttribute("memID", memID);
//			req.setAttribute("memID", memID);
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/modifyPassword.jsp"); // 新增成功後轉交listAllmember_byDAO
			successView.forward(req, res);

		}

		if ("modify_password2".equals(action)) {
			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String memID = (String) req.getSession().getAttribute("memID");

				String password1 = req.getParameter("password1").trim();
				String emailReg = "^[a-zA-Z0-9@.]{6,10}$";
				
				if (password1 == null || password1.trim().length() == 0) {
					errorMsgs.add("password請勿空白");
				}else if (!password1.trim().matches(emailReg)) {
					errorMsgs.add("電子信箱長度須為6-10之間，且只能使用英文字或數字");
				}				
				
				
				String password2 = req.getParameter("password2").trim();
				if (password2 == null || password2.trim().length() == 0) {
					errorMsgs.add("password請勿空白");
				}else if (!password2.trim().matches(emailReg)) {
					errorMsgs.add("電子信箱長度須為6-10之間，且只能使用英文字或數字");
				}	
				System.out.println(memID);
				System.out.println(password1);
				System.out.println(password2);
				if (!password1.equals(password2)) {
					errorMsgs.add("輸入密碼不一致");
				}

				

//				MemberVO memberVO = new MemberVO();
//				memberVO.setMemID(memID);
//
//				memberVO.setPassword(digestpassword);

				if (!errorMsgs.isEmpty()) {

//					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/modifyPassword.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				DigestService digestSvc = new DigestService();
				String digestpassword = digestSvc.digest(password2);

				MemberService memberSvc = new MemberService();
				memberSvc.updatePassVerified(memID, digestpassword);
				// 開始修改資料
//				MemberService memberSvc = new MemberService();
//				memberVO = memberSvc.updateMember(memID, password);
//				req.setAttribute("memberVO", memberVO);

				RequestDispatcher successView = req.getRequestDispatcher("/regna-master/homeindex.jsp"); 
				successView.forward(req, res);
				System.out.println("@");
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料：" + e.getMessage());
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/update_member_input.jsp");
				successView.forward(req, res);

			}
		}

	}
}
