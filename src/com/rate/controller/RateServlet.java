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
import com.rate.model.RateDAO;
import com.rate.model.RateService;
import com.rate.model.RateVO;

/**
 * Servlet implementation class MemberServlet
 */
//@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class RateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Update".equals(action)) {		
			List<String> errorMsgs = new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer rateID = new Integer(req.getParameter("rateID"));
				RateService rateSvc = new RateService();
				RateVO rateVO = rateSvc.getOneRate(rateID);
				req.setAttribute("rateVO", rateVO);

				RequestDispatcher succesView = req.getRequestDispatcher("/rate/update_rate_iuput.jsp");
				succesView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/rate/listAllrate.jsp");
				failureView.forward(req, res);
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
					errorMsgs.add("請輸入數字，可至小數點第一位");
				}

				Integer rateBasic = null;
				try {
					rateBasic = new Integer(req.getParameter("rateBasic").trim());
				} catch (Exception e) {
					errorMsgs.add("請輸入數字");
				}

//  			**************到時候記得要拿掉***鳩咪

//				Part part = req.getPart("pic");
//				InputStream in = part.getInputStream();
//				byte[] pic = new byte[in.available()];
//				in.read(pic);
//				in.close();

				RateDAO rateDAO = new RateDAO();
				RateVO rateVO = new RateVO();
				rateVO.setRateName(rateName);
				rateVO.setRatePrice(ratePrice);
				rateVO.setRateBasic(rateBasic);
//				rateVO.setPic(pic);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rateVO", rateVO);
					RequestDispatcher failView = req.getRequestDispatcher("/rate/addRate.jsp");
					failView.forward(req, res);
					return;
				}

				// 開始新增資料
				rateDAO.insert(rateVO);

				RequestDispatcher succesView = req.getRequestDispatcher("/rate/listAllRate.jsp");
				succesView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要新增的資料：" + e.getMessage());
				RequestDispatcher successView = req.getRequestDispatcher("/rate/addRate.jsp");
				successView.forward(req, res);
			}

		}

	}

}
