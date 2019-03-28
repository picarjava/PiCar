package com.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.RequestDispatcher;

import com.DigestService.DigestService;
import com.admin.model.AdminService;
import com.mailService.MailService;
import com.member.model.*;
import java.util.LinkedList;
import com.storeRecord.model.*;

/**
 * Servlet implementation class MemberServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		String memID = req.getParameter("memID");

		MemberService memberSvc = new MemberService();
		MemberVO memberVO = memberSvc.getOneMember(memID);

		byte[] pic = memberVO.getPic();
		if (pic != null) {
			out.write(pic);
		}

		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
//		PrintWriter out = res.getWriter();

		// getALL getALL getALL getALL getALL getALL getALL

		if ("getAll".equals(action)) {

			// 開始查詢資料
			MemberDAO memberDAO = new MemberDAO();
			List<MemberVO> list = memberDAO.getAll();

			// 將資料存於set於session
			HttpSession session = req.getSession();
			session.setAttribute("list", list);

			// 將控制權轉送給listAllMember_getFormSession.jsp
			String url = "/member/listAllMember_getFormSession.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}

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
					RequestDispatcher failureView = req.getRequestDispatcher("/member/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/member/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				// 準備轉交
				req.setAttribute("memberVO", memberVO);

				RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/listOneMemberByUpdate.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/select_page.jsp");
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

				RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/update_member_input.jsp");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料：" + e.getMessage());
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/listOneMemberByUpdate.jsp");
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

				MemberService memberSvc1 = new MemberService();
				MemberVO memberVO1 = memberSvc1.getOneMember(memID);
				byte[] picnow = memberVO1.getPic();

				byte[] pic = null;

				Part part = req.getPart("pic");
				long size = part.getSize();
				InputStream in = part.getInputStream();
				pic = new byte[in.available()];

				if (size != 0) {
					in.read(pic);
//					in.close();
				} else if (size == 0 && picnow == null) {
					errorMsgs.add("請輸入照片");
				} else if (picnow != null && size == 0) {
					pic = picnow;
				}
				in.close();

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
				memberVO.setPic(pic);

				if (!errorMsgs.isEmpty()) {

					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/update_member_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				// 開始修改資料
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.updateMember(memID, name, email, password, phone, creditcard, pet, smoke, gender,
						token, activityToken, birthday, verified, babySeat, pic);
				req.setAttribute("memberVO", memberVO);

				RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/listOneMemberByUpdate.jsp"); // 新增成功後轉交listAllmember_byDAO
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料：" + e.getMessage());
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/update_member_input.jsp");
				successView.forward(req, res);

			}
		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
//				String memID = new String(req.getParameter("memID"));

				String name = req.getParameter("name").trim();
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
					token = new Integer(req.getParameter("token").trim());
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
				Part part = req.getPart("pic");
				InputStream in = part.getInputStream();
				byte[] pic = new byte[in.available()];
				in.read(pic);
				in.close();

				MemberVO memberVO = new MemberVO();
//				memberVO.setMemID(memID);				
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
				memberVO.setPic(pic);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/addMember.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				// 開始新增資料
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.addMember(name, email, password, phone, creditcard, pet, smoke, gender, token,
						activityToken, birthday, verified, babySeat, pic);
				req.setAttribute("memberVO", memberVO);

				RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/listOneMemberByInsert.jsp"); // 新增成功後轉交listAllmember_byDAO
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要新增的資料：" + e.getMessage());
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/addMember.jsp");
				successView.forward(req, res);

			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String empno = new String(req.getParameter("memID"));

				/*************************** 2.開始刪除資料 ***************************************/
				MemberService memberSvc = new MemberService();
				memberSvc.deleteEmp(empno);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "listAllmember_byDAO.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("listAllmember_byDAO.jsp");
				failureView.forward(req, res);
			}
		}

		if ("verified".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String memID = new String(req.getParameter("memID").trim());
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getOneMember(memID);
			if (memberVO.getVerified() == 0) {

				memberSvc.updateVerified(memID);

			} else {
				errorMsgs.add("你已驗證，等首頁出來倒回首頁");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/pressToVerified.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

		}
		
		if ("getOne_For_Update_HOBBY".equals(action)) { //阿君新增FOR前端喜好設定
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

				RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/setting.jsp");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料：" + e.getMessage());
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/listOneMemberByUpdate.jsp");
				successView.forward(req, res);
			}

		}
		
		
		if ("Update_Hobby".equals(action)) {   //阿君新增FOR前端喜好設定
			// 建立錯誤的collection
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memID = new String(req.getParameter("memID").trim());

				String creditcard = req.getParameter("creditcard");
				if (creditcard == null || creditcard.trim().length() == 0) {
					errorMsgs.add("creditcard請勿空白");
				}

				Integer pet = new Integer(req.getParameter("pet"));
				Integer smoke = new Integer(req.getParameter("smoke"));
				Integer babySeat = new Integer(req.getParameter("babySeat"));


				MemberVO memberVO = new MemberVO();
				memberVO.setMemID(memID);
				memberVO.setCreditcard(creditcard);
				memberVO.setPet(pet);
				memberVO.setSmoke(smoke);
				memberVO.setBabySeat(babySeat);
				
				MemberService memberService = new MemberService();
				memberVO = memberService.setHobby(memID, creditcard, pet, smoke, babySeat);
				
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/listOneMemberByUpdate.jsp");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料：" + e.getMessage());
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/setting.jsp");
				successView.forward(req, res);
			}

		}
		
		if ("insertver2".equals(action)) {
			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
//				String memID = new String(req.getParameter("memID"));

				String name = req.getParameter("name").trim();
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
				/*******************************************/
//				String password = req.getParameter("password");
//				if (password == null || password.trim().length() == 0) {
//					errorMsgs.add("password請勿空白");
//				}
				/****************************************亂數密碼測試***/
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
				
				/*******************************************/
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
					token = new Integer(req.getParameter("token").trim());
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
				Part part = req.getPart("pic");
				if (part.getSize() == 0) {
					errorMsgs.add("請輸入照片!");
				}
				InputStream in = part.getInputStream();
				byte[] pic = new byte[in.available()];
				in.read(pic);
				in.close();

				MemberVO memberVO = new MemberVO();
//				memberVO.setMemID(memID);				
				memberVO.setName(name);
				memberVO.setEmail(email);
				memberVO.setPassword(digestpassword);
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
				memberVO.setPic(pic);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/addMember.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				// 開始新增資料
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.addMember(name, email, digestpassword, phone, creditcard, pet, smoke, gender, token,
						activityToken, birthday, verified, babySeat, pic);
				req.setAttribute("memberVO", memberVO);
				
				/****************************/
				List<MemberVO> last = memberSvc.getAll();
				String memIDlast = last.get(last.size()-1).getMemID();
				
				 String to = email;
			      String ch_name = name;
			      String passRandom = password;
			      String subject = "PICAR管理員密碼通知";
			      String messageText = "Hello　" + ch_name + "\n"+
			    		               "歡迎你加入PICAR大家庭！"+ "\n"  +
			                           "你的會員編號為" + memIDlast +"\n"+
			                           "此為你之後的登入帳號，"+
			                           "請先以此密碼登入【 " + passRandom + " 】"+
			                           "\n" +"登入後台過後再修改你的密碼！謝謝！"; 
				
				  MailService mailService = new MailService();
			      mailService.sendMail(to, subject, messageText);
			      /****************************/
			      
			      
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/listOneMemberByInsert.jsp"); // 新增成功後轉交listAllmember_byDAO
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要新增的資料：" + e.getMessage());
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/addMember.jsp");
				successView.forward(req, res);

			}
		}

	}

}
