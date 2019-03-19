package com.login.filter;

import java.io.IOException;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.member.model.MemberService;
import com.member.model.MemberVO;

public class LoginHandler extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	protected boolean allowUser(String account, String password) {
//		if ("M001".equals(account) && "123".equals(password)
//			||"M002".equals(account) && "123".equals(password)
//			||"M003".equals(account) && "123".equals(password)
//			||"M004".equals(account) && "123".equals(password)
//			||"M005".equals(account) && "123".equals(password)
//			||"M006".equals(account) && "123".equals(password)
//			||"M007".equals(account) && "123".equals(password)) {
//
//			return true;
//		} else {
//			return false;
//		}
//	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();

		String account = req.getParameter("account").trim();
		String password = req.getParameter("password").trim();

		HttpSession session = req.getSession();
		MemberVO memebrVO1 = (MemberVO) session.getAttribute("memberVO");
		if (memebrVO1 == null) {
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getOneMemberByPass(account, password);
			if (memberVO == null) {
				out.println("<HTML><HEAD><TITLE>登入畫面</TITLE></HEAD>");
				out.println("<BODY>你的帳號，密碼無效<BR>");
				out.println("<BODY>五秒後將自動跳轉回登入畫面<BR>");

				// 加入計時器自動跳轉
				res.setHeader("Refresh", "5; URL=" + req.getContextPath() + "/front-end/login/login.html");
				out.println("請按此重新登入<a href=" + req.getContextPath() + "/front-end/login/login.jsp> 重新登入 </a><br>"); // 密碼錯誤的時候要去的地方
				out.println("請按此回首頁<a href=" + req.getContextPath() + "/regna-master/homeindex.jsp> 回首頁 </a>");
				out.println("</BODY></HTML>");

			} else {
//			HttpSession session = req.getSession();
//			session.setAttribute("account", account);
				session.setAttribute("memberVO", memberVO);

				try {
					String location = (String) session.getAttribute("location");
					if (location != null) {
//					session.removeAttribute("location"); // 從其他頁面登入成功時候，要回到其他頁面
						res.sendRedirect(location);
						return;
					}
				} catch (Exception ignored) {
				}
				res.sendRedirect(req.getContextPath() + "/front-end/member/listOneMemberByUpdate.jsp"); // *工作2: 看看有無來源網頁
																								// (-->如有來源網頁:則重導至來源網頁)
			}
		}
	}

}
