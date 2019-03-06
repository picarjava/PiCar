package com.rate.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.member.model.MemberService;
import com.rate.model.RateDAO;
import com.rate.model.RateService;
import com.rate.model.RateVO;

/**
 * Servlet implementation class MemberServlet
 */
public class RateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String str = req.getParameter("rateID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入資費編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rate/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				Integer rateID = null;
				try {
					rateID = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("格式錯誤");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rate/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				RateService rateService = new RateService();
				RateVO rateVO = rateService.getOneRate(rateID);
				if (rateVO == null) {
					errorMsgs.add("無此資費編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rate/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				req.setAttribute("rateVO", rateVO);
				RequestDispatcher succesView = req.getRequestDispatcher("/back-end/rate/listOneRate.jsp");
				succesView.forward(req, res);

			} catch (RuntimeException e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rate/selec_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer rateID = new Integer(req.getParameter("rateID"));
				RateService rateSvc = new RateService();
				RateVO rateVO = rateSvc.getOneRate(rateID);
				req.setAttribute("rateVO", rateVO);

				RequestDispatcher succesView = req.getRequestDispatcher("/back-end/rate/update_rate_iuput.jsp");
				succesView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/rate/listAllrate.jsp");
				failureView.forward(req, res);
			}

		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer rateID = new Integer(req.getParameter("rateID").trim());

				String rateName = req.getParameter("rateName").trim();
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (rateName == null || rateName.trim().length() == 0) {

					errorMsgs.add("請輸入費率名稱");
				} else if (!rateName.trim().matches(nameReg)) {

					errorMsgs.add("會員姓名請輸入 中文、英文字母、數字和   \" , \" 且長度必需在2到20之間");
				}

				Double ratePrice = null;
				try {
					ratePrice = new Double(req.getParameter("ratePrice").trim());
					if (ratePrice == 0) {
						errorMsgs.add("請輸入數字");
					}
				} catch (Exception e) {
					ratePrice = 0.0;
					errorMsgs.add("請輸入數字，可至小數點第一位");
				}

				Integer rateBasic = null;
				try {
					rateBasic = new Integer(req.getParameter("rateBasic").trim());
				} catch (Exception e) {
					rateBasic = 0;
					errorMsgs.add("請輸入數字");
				}

				RateVO rateVO = new RateVO();
				rateVO.setRateID(rateID);
				rateVO.setRateName(rateName);
				rateVO.setRatePrice(ratePrice);
				rateVO.setRateBasic(rateBasic);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rateVO", rateVO);
					RequestDispatcher failView = req.getRequestDispatcher("/back-end/rate/update_rate_iuput.jsp");
					failView.forward(req, res);
					return;
				}

				// 開始新增資料
				RateService rateSvc = new RateService();
				rateVO = rateSvc.updateRate(rateID, rateName, ratePrice, rateBasic);

				RequestDispatcher succesView = req.getRequestDispatcher("/back-end/rate/listAllRate.jsp");
				succesView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要新增的資料：" + e.getMessage());
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/rate/update_rate_iuput.jsp");
				successView.forward(req, res);
			}

		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String rateName = req.getParameter("rateName").trim();
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (rateName == null || rateName.trim().length() == 0) {

					errorMsgs.add("請輸入費率名稱");
				} else if (!rateName.trim().matches(nameReg)) {

					errorMsgs.add("會員姓名請輸入 中文、英文字母、數字和   \" , \" 且長度必需在2到20之間");
				}

				Double ratePrice = null;
				try {
					ratePrice = new Double(req.getParameter("ratePrice").trim());
				} catch (Exception e) {
					ratePrice = 0.0;
					errorMsgs.add("請輸入數字，可至小數點第一位");
				}

				Integer rateBasic = null;
				try {
					rateBasic = new Integer(req.getParameter("rateBasic").trim());
				} catch (Exception e) {
					rateBasic = 0;
					errorMsgs.add("請輸入數字");
				}

				RateVO rateVO = new RateVO();
				rateVO.setRateName(rateName);
				rateVO.setRatePrice(ratePrice);
				rateVO.setRateBasic(rateBasic);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rateVO", rateVO);
					RequestDispatcher failView = req.getRequestDispatcher("/back-end/rate/addRate.jsp");
					failView.forward(req, res);
					return;
				}

				// 開始新增資料
				RateService rateSvc = new RateService();
				rateVO = rateSvc.addRate(rateName, ratePrice, rateBasic);

				RequestDispatcher succesView = req.getRequestDispatcher("/back-end/rate/listAllRate.jsp");
				succesView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要新增的資料：" + e.getMessage());
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/rate/addRate.jsp");
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
				Integer rateID = new Integer(req.getParameter("rateID").trim());

				/*************************** 2.開始刪除資料 ***************************************/
				RateService rateSvc = new RateService();
				rateSvc.deleteRate(rateID);
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				RequestDispatcher successView = req.getRequestDispatcher("listAllRate.jsp");// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("listAllRate.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
