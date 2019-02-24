package com.broadcast.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.broadcast.model.BroadcastService;
import com.broadcast.model.BroadcastVO;

//@WebServlet("/BroadcastServlet")
public class BroadcastServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BroadcastServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求 okk
			List<String> errorMsgs = new LinkedList<String>();	
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String msgID = req.getParameter("msgID");
				
				if (msgID == null || (msgID.trim()).length() == 0) {
					errorMsgs.add("請輸入推播編號");
				}
				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/broadcast/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
//				String msgid = null;
				////////////
//				try {
//					msgid = new String(msgID);
//				} catch (Exception e) {
//					errorMsgs.add("推播編號格式不正確");
//				}
				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/broadcast/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
				/***************************2.開始查詢資料*****************************************/
				BroadcastService brodSvc = new BroadcastService();
				BroadcastVO brodVO = brodSvc.getOneBroadcast(msgID);
				if (brodVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/broadcast/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("brodVO", brodVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/broadcast/listOnebrod.jsp";
				//指向該單筆頁面
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/************************其他可能的錯誤處理*********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料::" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/broadcast/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}
//		
////////////////////////////////////////////////////////////////////////////////
		if ("getOne_For_Update".equals(action)) { // 來自listAllBrod.jsp的請求  ok

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String msgID = req.getParameter("msgID");

				/***************************
				**2.開始查詢資料****
				 ****************************************/
				BroadcastService brodSvc = new BroadcastService();
				BroadcastVO brodVO = brodSvc.getOneBroadcast(msgID);

				/***************************
				 **3.查詢完成,準備轉交(Send the Success view)**
				 ************/
				req.setAttribute("brodVO", brodVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/broadcast/update_brod_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_brod_input.jsp
				successView.forward(req, res);

				/*************************其他可能的錯誤處理*********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料::" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/broadcast/listAllBrod.jsp");
//				failureView.forward(req, res);
//			}
		}
//		
////////////////////////////////////////////////
		if ("update".equals(action)) { //  來自update_brod_input.jsp的請求  ok

			
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

//				try {
				/************************ 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String msgID = req.getParameter("msgID");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (msgID == null || msgID.trim().length() == 0) {
					errorMsgs.add("推播編號: 請勿空白");
				} else if (!msgID.trim().matches(enameReg)) { //// 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("推播姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間�");
				}

				String memID = "M001";

//							req.getParameter("memID").trim();
//					if (memID == null || memID.trim().length() == 0) {
//						errorMsgs.add("¾��ФŪť�");
//					}

				String message = null;
				try {
					message = new String(req.getParameter("message")
//							.trim()
							);
				} catch (NumberFormatException e) {
					message = "";
					errorMsgs.add("�����ж�Ʀr.");
				}

//				Integer confirmed = 1;
					Integer 
					confirmed = new Integer(req.getParameter("confirmed"));
//					confirmed = 1;
				// 若是空值trim則跳500

				BroadcastVO brodVO = new BroadcastVO();


				brodVO.setMsgID(msgID);
				brodVO.setMemID(memID);
				brodVO.setMessage(message);
				brodVO.setConfirmed(confirmed);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("brodVO", brodVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/broadcast/update_brod_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/***************************
				 *2.開始修改資料***
				 *****************************************/
				BroadcastService brodSvc = new BroadcastService();
				brodVO = brodSvc.updateBroadcast(msgID, memID, message, confirmed);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)****
				 *************/
				req.setAttribute("brodVO", brodVO); //// 資料庫update成功後,正確的的brodVO物件,存入req
				String url = "/back-end/broadcast/listAllBrod.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);  // 修改成功後,轉交listOneBrod.jsp
				successView.forward(req, res);

				/***************************
				 *其他可能的錯誤處理*
				 *************************************/
//			} 
//				catch (Exception e) {
//				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/broadcast/update_brod_input.jsp");
//				failureView.forward(req, res);
//			}
		}
////////////////////////////////////////////////////////////////
		if ("insert".equals(action)) { // 來自addBrod.jsp的請求 ok

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/************************ 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String msgID = req.getParameter("msgID");//對應233行
//			String msgID = "MSG";//對應232行 注意解除。add.jsp。disabled="disabled"
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (msgID == null || msgID.trim().length() == 0) {
				errorMsgs.add("推播編號: 請勿空白");
			} else if (!msgID.trim().matches(enameReg)) { //// 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("推播編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間�");
			}
//			String memID =req.getParameter("memID").trim();//注意:正是從session 抓下來
			String memID ="M001";
			String	message = new String(req.getParameter("message").trim()); //訊息做字串處理
				if (message == null || message.trim().length() == 0) {
					errorMsgs.add("訊息內容: 請勿空白");
				}
			Integer confirmed = 0;
//				Integer 
//				confirmed = new Integer(req.getParameter("confirmed"));
//				confirmed = 1;
			// 若是空值trim則跳500

			BroadcastVO brodVO = new BroadcastVO();

			brodVO.setMsgID(msgID);
			brodVO.setMemID(memID);
			brodVO.setMessage(message);
			brodVO.setConfirmed(confirmed);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("brodVO", brodVO); // // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/broadcast/addBrod.jsp");
				failureView.forward(req, res);
				return;
			}
			/***************************
			 *2.開始新增資料*********
			 ***************************************/
			BroadcastService brodSvc = new BroadcastService();
			brodVO = brodSvc.addBroadcast(msgID, memID, message, confirmed);

			/***************************
			 **3.新增完成,準備轉交(Send the Success view)* Success view)
			 ***********/
			String url = "/back-end/broadcast/listAllBrod.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllBrod.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理*****************************/
//			} 
//        catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/broadcast/addBrod.jsp");
//				failureView.forward(req, res);
//			}
		}
//		
//		//////////////////////////////////////////////////////
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp    ok

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數**********************************************/
				String msgID = req.getParameter("msgID");
				
				/************************2.開始刪除資料*******************************************/
				BroadcastService brodSvc = new BroadcastService();
				brodSvc.deleteBroadcast(msgID);
				
				/************************3.刪除完成,準備轉交(Send the Success view)**********/								
				String url = "/back-end/broadcast/listAllBrod.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ����
				successView.forward(req, res);
				
				/************************其他可能的錯誤處理********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/broadcast/listAllBrod.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
