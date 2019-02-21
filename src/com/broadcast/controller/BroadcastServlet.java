package com.broadcast.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	doPost(req,res);	
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();	
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String msgID = req.getParameter("msgID");
				
				if (msgID == null || (msgID.trim()).length() == 0) {
					errorMsgs.add("請輸入推播編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/broadcast/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String msgid = null;
				try {
					msgid = new String(msgID);
				} catch (Exception e) {
					errorMsgs.add("推播編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/broadcast/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				BroadcastService brodSvc = new BroadcastService();
				BroadcastVO brodVO = brodSvc.getOneBroadcast(msgid);
				if (brodVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/broadcast/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("brodVO", brodVO); // 資料庫取出的empVO物件,存入req
				String url = "/broadcast/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料::" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/broadcast/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
////////////////////////////////////////////////////////////////////////////////
//		if ("getOne_For_Update".equals(action)) {  // 來自listAllEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
		/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//		         String msgID = req.getParameter("msgID");
//				
//				/***************************2.�}�l�d�߸��****************************************/
//				BroadcastService brodSvc = new BroadcastService();
//				BroadcastVO brodVO = brodSvc.getOneBroadcast(msgid);
//								
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
//				req.setAttribute("brodVO", brodVO);         // ��Ʈw���X��empVO����,�s�Jreq
//				String url = "/broadcast/update_emp_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//
//				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料::" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/broadcast/listAllBrod.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		///////////////////////////////////////////////
//		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
//				Integer empno = new Integer(req.getParameter("empno").trim());
//				
//				String ename = req.getParameter("ename");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.add("���u�m�W: �ФŪť�");
//				} else if(!ename.trim().matches(enameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
//	            }
//				
//				String job = req.getParameter("job").trim();
//				if (job == null || job.trim().length() == 0) {
//					errorMsgs.add("¾��ФŪť�");
//				}	
//				

//				Double sal = null;
//				try {
//					sal = new Double(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					sal = 0.0;
//					errorMsgs.add("�~��ж�Ʀr.");
//				}
//

//				Integer deptno = new Integer(req.getParameter("deptno").trim());
//
//				BroadcastVO brodVO= new BroadcastVO();
//				brodVO.setEmpno(empno);
//				brodVO.setEname(ename);
//				brodVO.setJob(job);
//				brodVO.setHiredate(hiredate);

//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("brodVO", brodVO); // �t����J�榡���~��empVO����,�]�s�Jreq
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/broadcast/update_emp_input.jsp");
//					failureView.forward(req, res);
//					return; //�{�����_
//				}
//				
//				/***************************2.�}�l�ק���*****************************************/
//				BroadcastService brodSvc = new BroadcastService();
//				brodVO = brodSvc.updateEmp(empno, ename, job, hiredate, sal,comm, deptno);
//				
//				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
//				req.setAttribute("brodVO", brodVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
//				String url = "/broadcast/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************��L�i�઺���~�B�z*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/broadcast/update_emp_input.jsp");
//				failureView.forward(req, res);
//			}
//		}
////////////////////////////////////////////////////////////////
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String msgID = req.getParameter("msgID");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (msgID == null || msgID.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!msgID.trim().matches(enameReg)) { ////以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間�");
	            }
				
				String memID = "M001";
						
//						req.getParameter("memID").trim();
//				if (memID == null || memID.trim().length() == 0) {
//					errorMsgs.add("¾��ФŪť�");
//				}
				

				String message = null;
				try {
					message = new String (req.getParameter("message").trim());
				} catch (NumberFormatException e) {
					message = "";
					errorMsgs.add("�����ж�Ʀr.");
				}
				
				Integer confirmed = new Integer(req.getParameter("confirmed").trim());

				BroadcastVO brodVO = new BroadcastVO();
				
				brodVO.setMsgID(msgID);
				brodVO.setMemID(memID);
				brodVO.setMessage(message);
				brodVO.setConfirmed(confirmed);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("brodVO", brodVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/broadcast/addBrod.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				BroadcastService brodSvc = new BroadcastService();
				brodVO = brodSvc.addBroadcast(msgID, memID, message, confirmed);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/broadcast/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/broadcast/addBrod.jsp");
				failureView.forward(req, res);
			}
		}
//		
//		//////////////////////////////////////////////////////
//		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.�����ШD�Ѽ�***************************************/
//				Integer empno = new Integer(req.getParameter("empno"));
//				
//				/***************************2.�}�l�R�����***************************************/
//				BroadcastService brodSvc = new BroadcastService();
//				brodSvc.deleteEmp(empno);
//				
//				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
//				String url = "/broadcast/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ����
//				successView.forward(req, res);
//				
//				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�R����ƥ���:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/broadcast/listAllEmp.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}
}
