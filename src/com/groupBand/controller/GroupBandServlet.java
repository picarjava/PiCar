package com.groupBand.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupBand.model.*;




/**
 * Servlet implementation class GroupBandServlet
 */
//@WebServlet("/GroupBandServlet")
public class GroupBandServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
	      if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
					
					String content =" ";	
					
					
					String introduction = req.getParameter("introduction");
					String introduc = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]";
					if (introduction == null || introduction.trim().length() == 0) {
						errorMsgs.add("簡介: 請勿空白");
					} else if(!introduction.trim().matches(introduc)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("簡介: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
		            }
					
					
					Integer groupStatus =1;
					
							
					Integer currenTnum  =1;
					
					
					Integer upperLimit = new Integer(req.getParameter("upperlimit").trim());
					
					
					Integer lowerLimit = new Integer(req.getParameter("lowerlimit").trim());
					
					
					String groupName =req.getParameter("groupName");
					String groupN = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$";
					if (groupName == null || groupName.trim().length() == 0) {
						errorMsgs.add("團名: 請勿空白");
					} else if(!groupName.trim().matches(groupN)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("團名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
		            }
					
					groupLeader
					
					
					
					String ename = req.getParameter("ename");
					String introduc = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]";
					if (ename == null || ename.trim().length() == 0) {
						errorMsgs.add("員工姓名: 請勿空白");
					} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
		            }
					
					String job = req.getParameter("job").trim();
					if (job == null || job.trim().length() == 0) {
						errorMsgs.add("職位請勿空白");
					}
					
					java.sql.Date hiredate = null;
					try {
						hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
					} catch (IllegalArgumentException e) {
						hiredate=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}
					
					Double sal = null;
					try {
						sal = new Double(req.getParameter("sal").trim());
					} catch (NumberFormatException e) {
						sal = 0.0;
						errorMsgs.add("薪水請填數字.");
					}
					
					Double comm = null;
					try {
						comm = new Double(req.getParameter("comm").trim());
					} catch (NumberFormatException e) {
						comm = 0.0;
						errorMsgs.add("獎金請填數字.");
					}
					
					Integer deptno = new Integer(req.getParameter("deptno").trim());
					
					
					GroupBandVO groupBandVO = new GroupBandVO();
					groupBandVO.setContent(content);
					groupBandVO.setIntroduction(introduction);
					groupBandVO.setGroupStatus(groupStatus);
					groupBandVO.setCurrenTnum(currenTnum);
					groupBandVO.setUpperLimit(upperLimit);
					groupBandVO.setLowerLimit(lowerLimit);
					groupBandVO.setGroupName(groupName);
					groupBandVO.setGroupLeader(groupLeader);
					groupBandVO.setStartLoc(startLoc);
					groupBandVO.setEndLoc(endLoc);
					groupBandVO.setPrivates(privates);
					groupBandVO.setPhoto(photo);
					groupBandVO.setGroupType(groupType);
					groupBandVO.setTotalAmout(totalAmout);
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					groupBandVO.setStartTime(startTime);
//					groupBandVO.setStartTime(new Date(simpleDateFormat.parse("2019-02-14").getTime()));
					groupBandVO.setRate(rate);
					groupBandVO.setNote(note);
				
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("GroupBandVO", groupBandVO); // �t����J�榡���~��GroupBandVO����,�]�s�Jreq
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/groupBand/insertGroupBand.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.�}�l�s�W���***************************************/
					GroupBandService empSvc = new GroupBandService();
					groupBandVO = empSvc.addGroupBand(content,introduction,groupStatus,currenTnum,
							upperLimit,lowerLimit,groupName,groupLeader,startLoc,
							endLoc,privates,photo,groupType,totalAmout,startTime,
							rate,note);
					
					/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
					String url = "/front-end/groupBand/insertGroupBand.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************��L�i�઺���~�B�z**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/groupBand/insertGroupBand.jsp");
					failureView.forward(req, res);
				}
			}
		
	}

}
