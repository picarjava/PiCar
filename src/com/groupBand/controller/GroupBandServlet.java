package com.groupBand.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.groupBand.model.*;
import com.groupOrder.model.*;
import com.member.model.*;

/**
 * Servlet implementation class GroupBandServlet
 */
//@WebServlet("/GroupBandServlet")
@MultipartConfig
public class GroupBandServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (req.getParameter("groupID") != null) {
			req.setCharacterEncoding("utf-8");
			res.setContentType("image/gif");
			ServletOutputStream out = res.getOutputStream();

			String groupID = req.getParameter("groupID");
			
			GroupBandService groupBandService = new GroupBandService();
			GroupBandVO groupBandVO = (GroupBandVO) groupBandService.getOneGroupBand(groupID);
			System.out.println(groupID);

			byte[] pic = groupBandVO.getPhoto();
			if (pic != null) {
				out.write(pic);
			}
		}
		if (req.getParameter("MEM_ID") != null) {
			req.setCharacterEncoding("utf-8");
			res.setContentType("image/gif");
			ServletOutputStream out = res.getOutputStream();

			String memID = req.getParameter("MEM_ID");
			
			MemberService memberService =new MemberService();
			MemberVO memberVO =new MemberVO();
			memberVO =memberService.getOneMember(memID);
			
			byte[] pic = memberVO.getPic();
			if (pic != null) {
				out.write(pic);
			}
		}

		doPost(req, res);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String groupID = req.getParameter("groupID");
				if (groupID == null || (groupID.trim()).length() == 0) {
					errorMsgs.add("錯誤拉");
				}
				GroupBandService groupBandService = new GroupBandService();
				GroupBandVO groupBandVO = groupBandService.getOneGroupBand(groupID);
				if (groupBandVO == null) {
					errorMsgs.add("格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/groupBand/SelectGroupBand.jsp");
					failureView.forward(req, res);
					return;
				}

				req.setAttribute("GroupBandVO", groupBandVO);
				String url = "/front-end/groupBand/listOneGroupBand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupBand/SelectGroupBand.jsp");
				failureView.forward(req, res);
			}

		}
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String groupID = req.getParameter("groupID");

				GroupBandService groupBandService = new GroupBandService();

				groupBandService.deleteGroupBand(groupID);

				String url = "/front-end/groupBand/listAllGroupBand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupBand/listAllGroupBand.jsp");
				failureView.forward(req, res);
			}

		}
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String groupID = req.getParameter("groupID");

				GroupBandService groupBandService = new GroupBandService();
				GroupBandVO groupBandVO = groupBandService.getOneGroupBand(groupID);

				req.setAttribute("GroupBandVO", groupBandVO);
				String url = "/front-end/groupBand/updateGroupBand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupBand/listAllGroupBand.jsp");
				failureView.forward(req, res);
			}

		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			GroupBandService groupBandService = new GroupBandService();
			try {
				/***********************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 *************************/
				GroupBandVO groupBandVO = new GroupBandVO();
				String content = " ";

				java.sql.Timestamp launchTime = null;
//				try {
				String launchTimes = null;
				launchTimes = req.getParameter("LaunchTime");
				SimpleDateFormat simpleDateFormats = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

				launchTime = new Timestamp(simpleDateFormats.parse(launchTimes).getTime());

				String introduction = req.getParameter("introduction");
				String introduc = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{1,20}$";
				if (introduction == null || introduction.trim().length() == 0) {
					errorMsgs.add("簡介: 請勿空白");
				} else if (!introduction.trim().matches(introduc)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("簡介: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				Integer groupStatus = 1;

				Integer currenTnum = 1;

				Integer upperLimit = new Integer(req.getParameter("upperlimit").trim());

				Integer lowerLimit = new Integer(req.getParameter("lowerlimit").trim());

				String groupName = req.getParameter("groupName");
				String groupN = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$";
				if (groupName == null || groupName.trim().length() == 0) {
					errorMsgs.add("團名: 請勿空白");
				} else if (!groupName.trim().matches(groupN)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("團名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String groupLeader = "M001";

				String startLoc = req.getParameter("startLoc");

				String endLoc = req.getParameter("endLoc");

				Integer privates = 1;
				if ("1".equals(req.getParameter("privates"))) {
					privates = new Integer(req.getParameter("privates").trim());
				} else {
					privates = 0;
				}

				GroupBandVO groupBandVO1 = (GroupBandVO) groupBandService.getOneGroupBand(req.getParameter("groupID"));

				byte[] pice = groupBandVO1.getPhoto();// old
				byte[] photo = null;
				if (pice != null) {
					photo = pice;
				} else {
					Part part = req.getPart("photo");
					long size = part.getSize();

					InputStream in = part.getInputStream();

					photo = new byte[in.available()];
					if (in.available() != 0) {
						in.read(photo);
						in.close();
					} else {
						errorMsgs.add("請上傳照片");

					}

				}

				String groupType = req.getParameter("groupType");

				Integer totalAmout = 0;

				java.sql.Timestamp startTime = null;
				try {
					String startTimes = null;
//				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

					if ("1".equals(req.getParameter("groupKind"))) {
						startTimes = req.getParameter("startTime");
						if (startTimes == null || "".equals(startTimes)) {
							errorMsgs.add("日期: 請勿空白");
							startTime = new java.sql.Timestamp(System.currentTimeMillis());

						}
					} else {
						startTimes = req.getParameter("startTimes");
						if (startTimes == null || "".equals(startTimes)) {
							errorMsgs.add("日期: 請勿空白");
							startTime = new java.sql.Timestamp(System.currentTimeMillis());

						}
					}
				} catch (IllegalArgumentException e) {
					startTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer groupKind = new Integer(req.getParameter("groupKind").trim());

				Integer rate = 5;

				String note = req.getParameter("note");
				String notes = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$";
				if (note == null || note.trim().length() == 0) {
					errorMsgs.add("備註: 請勿空白");
				} else if (!note.trim().matches(notes)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("備註: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				groupBandVO.setGroupID(req.getParameter("groupID"));
				groupBandVO.setContent(content);
				groupBandVO.setLaunchTime(launchTime);
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

				groupBandVO.setStartTime(startTime);
//				groupBandVO.setStartTime(new Date(simpleDateFormat.parse("2019-02-14").getTime()));
				groupBandVO.setRate(rate);
				groupBandVO.setNote(note);
				groupBandVO.setGroupKind(groupKind);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("GroupBandVO", groupBandVO); // �t����J�榡���~��GroupBandVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/groupBand/updateGroupBand.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************
				 * 2.�}�l�s�W���
				 ***************************************/

				groupBandVO = groupBandService.updateGroupBand(req.getParameter("groupID"), launchTime, content,
						introduction, groupStatus, currenTnum, upperLimit, lowerLimit, groupName, groupLeader, startLoc,
						endLoc, privates, photo, groupType, totalAmout, startTime, rate, note, groupKind);

				/***************************
				 * 3.�s�W����,�ǳ����(Send the Success view)
				 ***********/
//				req.setAttribute("GroupBandVO", groupBandVO);
				String url = "/front-end/groupBand/listAllGroupBand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupBand/updateGroupBand.jsp");
				failureView.forward(req, res);
			}

		}
		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 *************************/

				String content = " ";

				String introduction = req.getParameter("introduction");
				String introduc = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{1,20}$";
				if (introduction == null || introduction.trim().length() == 0) {
					errorMsgs.add("簡介: 請勿空白");
				} else if (!introduction.trim().matches(introduc)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("簡介: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				Integer groupStatus = 1;

				Integer currenTnum = 1;

				Integer upperLimit = new Integer(req.getParameter("upperlimit").trim());

				Integer lowerLimit = new Integer(req.getParameter("lowerlimit").trim());

				String groupName = req.getParameter("groupName");
				String groupN = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$";
				if (groupName == null || groupName.trim().length() == 0) {
					errorMsgs.add("團名: 請勿空白");
				} else if (!groupName.trim().matches(groupN)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("團名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String groupLeader = req.getParameter("memIDs");

				String startLoc = req.getParameter("startLoc");

				String endLoc = req.getParameter("endLoc");

				Integer privates = 1;
				if ("1".equals(req.getParameter("privates"))) {
					privates = new Integer(req.getParameter("privates").trim());
				} else {
					privates = 0;
				}

				byte[] photo = null;
//					Collection<>=ima01,ima02;
				Collection<Part> parts = req.getParts();
				for (Part part : parts) {
					if (getFileNameFromPart(part) != null && part.getContentType() != null) {

						long size = part.getSize();

						// 額外測試 InputStream 與 byte[] (幫將來model的VO預作準備)
						InputStream in = part.getInputStream();
						photo = new byte[in.available()];
						in.read(photo);
						in.close();
					}
				}

				Part part = null;
				part = req.getPart("photo");
				System.out.println(part);

				long size = part.getSize();

				InputStream in = part.getInputStream();

				photo = new byte[in.available()];
				if (in.available() != 0) {
					in.read(photo);
					in.close();
				} else {
					errorMsgs.add("請上傳照片");

				}

				String groupType = req.getParameter("groupType");

				Integer totalAmout = 0;

				java.sql.Timestamp startTime = null;
				java.sql.Timestamp endtime = null;
//					try {

//					String startTimes =null;
//					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

				if ("0".equals(req.getParameter("groupKind"))) {
					try {

//						startTimes=req.getParameter("startTime");
						startTime = java.sql.Timestamp.valueOf(req.getParameter("startTime").trim());

					} catch (IllegalArgumentException e) {
//								
						errorMsgs.add("日期: 請勿空白");
						startTime = new java.sql.Timestamp(System.currentTimeMillis());
					}
				} else {
					try {
//					    startTimes=req.getParameter("startTimes");

						startTime = java.sql.Timestamp.valueOf(req.getParameter("startTime").trim());
//						endtime = java.sql.Timestamp.valueOf(req.getParameter("endTime").trim());

					}

					catch (IllegalArgumentException e) {
//							
						errorMsgs.add("日期: 請勿空白");
						startTime = new java.sql.Timestamp(System.currentTimeMillis());
					}
				}

//   	
				Integer groupKind = new Integer(req.getParameter("groupKind").trim());

				Integer rate = 5;

				String note = req.getParameter("note");
				String notes = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$";
				if (note == null || note.trim().length() == 0) {
					errorMsgs.add("備註: 請勿空白");
				} else if (!note.trim().matches(notes)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("備註: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				GroupBandVO groupBandVO = new GroupBandVO();
				GroupBandDAO groupBandDAO = new GroupBandDAO();

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

				groupBandVO.setStartTime(startTime);
//					groupBandVO.setStartTime(new Date(simpleDateFormat.parse("2019-02-14").getTime()));
				groupBandVO.setRate(rate);
				groupBandVO.setNote(note);
				groupBandVO.setGroupKind(groupKind);
				/***************************
				 * 2.�}�l�s�W���
				 ***************************************/
//				GroupBandService groupBandService = new GroupBandService();
//				groupBandVO = groupBandService.addGroupBand(content, introduction, groupStatus, currenTnum, upperLimit,
//						lowerLimit, groupName, groupLeader, startLoc, endLoc, privates, photo, groupType, totalAmout,
//						startTime, rate, note,groupKind);

				// 產生訂單

//		-------------------產生 (揪團訂單 )多筆------------------------------------------

				String diverID = null;

				String memID[];

				memID = new String[upperLimit];

				for (int x = 0; x < upperLimit; x++) {
					memID[x] = null;

				}
				memID[0] = groupLeader;
				Integer state = 1;

				Integer totalAmoutOr = 0;

				Timestamp startTimeOr = startTime;

				Timestamp endTime = null;

				Double startLng = 0.1;

				Double startLat = 0.1;

				Double endLng = 0.1;

				Double endLat = 0.1;

				Integer orderType = 0; // 0是揪團 1是長期揪團

				Integer rateOr = 0;

				String noteOr = null;

				String numdays[] = req.getParameterValues("numdays");

				List<GroupOrderVO> testList = new ArrayList<GroupOrderVO>();

				if ("0".equals(req.getParameter("groupKind"))) {

					for (int x = 0; x < upperLimit; x++) {
						GroupOrderVO groupOrderVO;
						groupOrderVO = new GroupOrderVO();
						groupOrderVO.setDriverID(diverID);
						groupOrderVO.setMemID(memID[x]);
						groupOrderVO.setState(state);
						groupOrderVO.setTotalAmout(totalAmoutOr);
						groupOrderVO.setStartTime(startTimeOr);
						groupOrderVO.setEndTime(endTime);
						groupOrderVO.setStartLat(startLat);
						groupOrderVO.setStartLng(startLng);
						groupOrderVO.setEndLng(endLng);
						groupOrderVO.setEndLat(endLat);
						groupOrderVO.setOrderType(orderType);
						groupOrderVO.setRate(rateOr);
						groupOrderVO.setNote(noteOr);
						groupOrderVO.setEndLoc(endLoc);
						groupOrderVO.setStartLoc(startLoc);
						testList.add(groupOrderVO);

//						groupOrderVO[x] = (GroupOrderVO) groupOrderService.addGroupOrder(diverID, memID[x], state,
//								totalAmoutOr, startTimeOr, endTime, startLng, startLat, endLng, endLat, orderType,
//								rateOr, noteOr);

					}
				} else {

					for (int y = 0; y < numdays.length; y++) {
						for (int x = 0; x < upperLimit; x++) {
							java.sql.Timestamp startTimeOrs = null;
							GroupOrderVO groupOrderVO;
							startTimeOrs = java.sql.Timestamp.valueOf(numdays[y]);
							groupOrderVO = new GroupOrderVO();
							groupOrderVO.setDriverID(diverID);
							groupOrderVO.setMemID(memID[x]);
							groupOrderVO.setState(state);
							groupOrderVO.setTotalAmout(totalAmoutOr);
							groupOrderVO.setStartTime(startTimeOrs);
							groupOrderVO.setEndTime(endTime);
							groupOrderVO.setStartLat(startLat);
							groupOrderVO.setStartLng(startLng);
							groupOrderVO.setEndLng(endLng);
							groupOrderVO.setEndLat(endLat);
							groupOrderVO.setOrderType(orderType);
							groupOrderVO.setRate(rateOr);
							groupOrderVO.setNote(noteOr);
							groupOrderVO.setEndLoc(endLoc);
							groupOrderVO.setStartLoc(startLoc);

							testList.add(groupOrderVO);

//						groupOrderVO[x] = (GroupOrderVO) groupOrderService.addGroupOrder(diverID, memID[x], state,
//								totalAmoutOr, startTimeOrs, endTime, startLng, startLat, endLng, endLat, orderType,
//								rateOr, noteOr);

						}
					}

				}

				groupBandDAO.insertWithEmps(groupBandVO, testList);
//		------------------------------------------------------------------------------------

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("GroupBandVO", groupBandVO); // �t����J�榡���~��GroupBandVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/groupBand/insertGroupBand.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************
				 * 3.�s�W����,�ǳ����(Send the Success view)
				 ***********/
				String url = "/front-end/groupBand/SelectGroupBand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupBand/insertGroupBand.jsp");
				failureView.forward(req, res);
			}
		}

		if ("listgroupBand_ByCompositeQuery".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");

				if (req.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map", map1);
					map = map1;

				}

				GroupBandService groupBandService = new GroupBandService();
				List<GroupBandVO> list = groupBandService.getAll(map);
				req.setAttribute("listgroupBand_ByCompositeQuery", list); // 資料庫取出的list物件,存入request

				RequestDispatcher successView = req
						.getRequestDispatcher("/front-end/groupBand/listgroupBand_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupBand/SelectGroupBand.jsp");
				failureView.forward(req, res);
			}

		}
		if ("GroupJoin".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String groupID = req.getParameter("groupID");
				if (groupID == null || (groupID.trim()).length() == 0) {
					errorMsgs.add("錯誤拉");
				}
				GroupBandService groupBandService = new GroupBandService();
				GroupBandVO groupBandVO = groupBandService.getOneGroupBand(groupID);
				if (groupBandVO == null) {
					errorMsgs.add("格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/groupBand/listgroupBand_ByCompositeQuery.jsp");
					failureView.forward(req, res);
					return;
				}
				String memIDs = req.getParameter("memIDs");

				if (memIDs.equals(groupBandVO.getGroupLeader())) // 如果為團長會 有修改 和刪除 ""修改成目前的連線
				{
					req.setAttribute("GroupLeader", "true");
					req.setAttribute("GroupBandVO", groupBandVO);

				} else {
					req.setAttribute("GroupLeader", "false");
					req.setAttribute("GroupBandVO", groupBandVO);
				}
				java.sql.Timestamp startTime = null;
				startTime = java.sql.Timestamp.valueOf(req.getParameter("startTime").trim());
				GroupOrderDAO groupOrderDAO = new GroupOrderDAO();
				List<MemberVO> testList = new ArrayList<MemberVO>();
				System.out.println(startTime+"+++++++");
				System.out.println(groupID+"+++++++");
				List<GroupOrderVO> list = groupOrderDAO.getALL_GroupID_StArtTime(groupID,startTime);
				for (GroupOrderVO elements : list) {
					if(elements.getMemID()!=null) {
						MemberService memberService =new MemberService();
						MemberVO memberVO =new MemberVO();
						memberVO =memberService.getOneMember(elements.getMemID());
						
						testList.add(memberVO);
					}
				}
				req.setAttribute("testList",testList);
				System.out.println(testList.size());
				
				String url = "/front-end/groupBand/listOneGroupBand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/groupBand/listgroupBand_ByCompositeQuery.jsp");
				failureView.forward(req, res);
			}
		}
		if ("GroupAdd".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String memID = req.getParameter("memIDs");
			GroupOrderDAO groupOrderDAO = new GroupOrderDAO();
			try {
				String groupID = req.getParameter("groupID");
				java.sql.Timestamp startTime = null;

				startTime = java.sql.Timestamp.valueOf(req.getParameter("startTime").trim());

				;
				Integer groupKind = new Integer(req.getParameter("groupKind").trim());
				
				String memIDs = req.getParameter("memIDs");

				GroupBandService groupBandService = new GroupBandService();
				GroupBandVO groupBandVO = groupBandService.getOneGroupBand(groupID);
				if (groupBandVO == null) {
					errorMsgs.add("格式不正確");
				}

				if (memIDs.equals(groupBandVO.getGroupLeader())) // 如果為團長會 有修改 和刪除 ""修改成目前的連線
				{
					req.setAttribute("GroupLeader", "true");
					req.setAttribute("GroupBandVO", groupBandVO);

				} else {
					req.setAttribute("GroupLeader", "false");
					req.setAttribute("GroupBandVO", groupBandVO);
				}
				
				//判斷揪團還長期揪團
				if (groupKind == 0) {
					
					//揪團訂單： 單查 抓出所有資料 ，以 揪團和上車時間下判斷
					List<GroupOrderVO> list = groupOrderDAO.findByALLGroupMemTime(groupID, startTime);

					GroupBandDAO groupBandDAO = new GroupBandDAO();
					
					//揪團：為了等一下判斷人數
					GroupBandVO groupBandV = groupBandDAO.findByPrimaryKey(groupID);

					for (GroupOrderVO element : list) {
						
						//揪團訂單：比對 資料庫乘客ID 還 連線乘客ID 比對	
						if (memID.equals(element.getMemID())) {
							errorMsgs.add("你已經在揪團中");
							break;
						} else if (element.getMemID() == null) {// 加入糾團完成
							groupOrderDAO.updateMem(memID, element.getGorderID());
							//揪團：判斷揪團人數
							groupBandDAO.UpdateCURRENT(groupBandV.getCurrenTnum() + 1, groupID);
							break;
						
						//揪團訂單：比對 資料庫乘客ID 不為空值  && 資料庫現在人數  比對 資料庫上限 人數   
						} else if (element.getMemID() != null && groupBandDAO.findByPrimaryKey(groupID)
								.getCurrenTnum() == groupBandDAO.findByPrimaryKey(groupID).getUpperLimit()) {
							errorMsgs.add("揪團人數已滿");
							break;
						}
					}
				} else {
					
					GroupBandDAO groupBandDAO = new GroupBandDAO();

					//1.取出揪團資料 等會用到
					GroupBandVO groupBandV = groupBandDAO.findByPrimaryKey(groupID);
	
					//2.揪團訂單：取出上車時間 。以揪團的團長ID、揪團ID
					List<GroupOrderVO> lists = groupOrderDAO.getOneStntstartTimeMem(groupID,
							groupBandV.getGroupLeader());
					
					//判斷人數有沒有滿的判斷
						boolean numberpeople =false;
						
						//外層以揪團訂單 日期 包住內層產生訂單  每一次都會填入不同日期 
					for (GroupOrderVO elements : lists) {
						List<GroupOrderVO> list = groupOrderDAO.findByALLGroupMemTime(groupID, elements.getStartTime());//不同日期
						for (GroupOrderVO element : list) {
							
							//揪團訂單：比對 資料庫乘客ID 還 連線乘客ID 比對如果發現已在揪團中直接導走
							if (memID.equals(element.getMemID())) {
								errorMsgs.add("你已經在揪團中");
								String url = "/front-end/groupBand/listOneGroupBand.jsp";
								RequestDispatcher successView = req.getRequestDispatcher(url); 
								successView.forward(req, res);
								return;
								
								//揪團訂單：比對 資料庫乘客ID 不為空值  && 資料庫現在人數  比對 資料庫上限 人數   && 判斷人數有沒有滿的判斷，滿的話不進入
							} else if (element.getMemID() != null && groupBandDAO.findByPrimaryKey(groupID)
									.getCurrenTnum() == groupBandDAO.findByPrimaryKey(groupID).getUpperLimit()&&numberpeople==false) {
								errorMsgs.add("揪團人數已滿");
								String url = "/front-end/groupBand/listOneGroupBand.jsp";
								RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
								successView.forward(req, res);
								return;
							} else if (element.getMemID() == null) {// 加入糾團完成
								groupOrderDAO.updateMem(memID, element.getGorderID());
								// 長期揪團判斷要加
								groupBandDAO.UpdateCURRENT(groupBandV.getCurrenTnum() + 1, groupID);
								
								//控制第一次如果沒滿人，就不會進入 揪團人數已滿的判斷 
								numberpeople=true;
								break;

							}
						}
						
					}
				}
				
				List<MemberVO> testList = new ArrayList<MemberVO>();
				List<GroupOrderVO> list = groupOrderDAO.getALL_GroupID_StArtTime(groupID,startTime);
				for (GroupOrderVO elements : list) {
					if(elements.getMemID()!=null) {
						MemberService memberService =new MemberService();
						MemberVO memberVO =new MemberVO();
						memberVO =memberService.getOneMember(elements.getMemID());
						testList.add(memberVO);
					}
				}
				req.setAttribute("testList",testList);
				

				String url = "/front-end/groupBand/listOneGroupBand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/groupBand/listOneGroupBand.jsp");
				failureView.forward(req, res);
			}
		}
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}

	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
