package com.groupBand.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.groupBand.model.*;
import com.groupOrder.model.*;




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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
	
		String groupID =req.getParameter("groupID");
		
		GroupBandService groupBandService = new GroupBandService();
		GroupBandVO groupBandVO=(GroupBandVO) groupBandService.getOneGroupBand(groupID);
		System.out.println(groupID);
		
		byte[] pic =groupBandVO.getPhoto();
		if(pic !=null) {
			out.write(pic);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
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
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/groupBand/SelectGroupBand.jsp");
				failureView.forward(req, res);
			}
			
			
			
		}
		if ("delete".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String groupID = req.getParameter("groupID");
				 
				GroupBandService groupBandService  = new GroupBandService();
				
				groupBandService.deleteGroupBand(groupID);
				
				String url = "/front-end/groupBand/listAllGroupBand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/groupBand/listAllGroupBand.jsp");
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
			
			req.setAttribute("GroupBandVO",groupBandVO);
			String url = "/front-end/groupBand/updateGroupBand.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
			successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/groupBand/listAllGroupBand.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if ("update".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				
				String content =" ";	
				
				
				java.sql.Timestamp launchTime = null;
//				try {
				String launchTimes =null;
				launchTimes = req.getParameter("LaunchTime");
				SimpleDateFormat simpleDateFormats = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				
				launchTime = new Timestamp(simpleDateFormats.parse(launchTimes).getTime());
				
				String introduction = req.getParameter("introduction");
				String introduc = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{1,20}$";
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
				
				String groupLeader = "M001";
				
				
				String startLoc = req.getParameter("startLoc");
				
				
				String endLoc = req.getParameter("endLoc");
				
				
				Integer privates =1;
				if("1".equals(req.getParameter("privates"))) {
				privates = new Integer(req.getParameter("privates").trim());	
				}else {
				privates =0;	
					  }
				
				byte[] photo=null;
				Part part = req.getPart("photo");
				long size = part.getSize();
				System.out.println(size);
				InputStream in = part.getInputStream();
				
				photo = new byte[in.available()];							
				if(in.available()!=0) {
					in.read(photo);                                                                                                                                                                                                                                                                                                             
					in.close();		
					}else {
						errorMsgs.add("請上傳照片");
						
					}
				
				
				String groupType = req.getParameter("groupType");
				
				
				Integer totalAmout	=0;
				
			
				java.sql.Timestamp startTime = null;
				try {
				String startTimes =null;
//				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			
			
				if("1".equals(req.getParameter("orderT"))) {
					startTimes=req.getParameter("startTime");
					if(startTimes==null || "".equals(startTimes)) {
						errorMsgs.add("日期: 請勿空白");
						startTime=new java.sql.Timestamp(System.currentTimeMillis());
						
					}
				}
				else {
				    startTimes=req.getParameter("startTimes");
				    if(startTimes==null || "".equals(startTimes)) {
						errorMsgs.add("日期: 請勿空白");
						startTime=new java.sql.Timestamp(System.currentTimeMillis());
						
					}
				}
				} catch (IllegalArgumentException e) {
					startTime=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				
				Integer rate =5;
				
				
				String note =req.getParameter("note");
				String notes = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$";
				if (note == null || note.trim().length() == 0) {
					errorMsgs.add("備註: 請勿空白");
				} else if(!note.trim().matches(notes)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("備註: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				
				
				GroupBandVO groupBandVO = new GroupBandVO();
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
			
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("GroupBandVO", groupBandVO); // �t����J�榡���~��GroupBandVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/groupBand/updateGroupBand.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				GroupBandService groupBandService = new GroupBandService();
				groupBandVO = groupBandService.updateGroupBand(req.getParameter("groupID"),launchTime,content,introduction,groupStatus,currenTnum,
						upperLimit,lowerLimit,groupName,groupLeader,startLoc,
						endLoc,privates,photo,groupType,totalAmout,startTime,
						rate,note);
				

				
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
//				req.setAttribute("GroupBandVO", groupBandVO);
				String url = "/front-end/groupBand/listAllGroupBand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/groupBand/updateGroupBand.jsp");
				failureView.forward(req, res);
			}		
	   		
	   		
	   		
	   	}
	      if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
					
					String content =" ";	
					
					
					String introduction = req.getParameter("introduction");
					String introduc = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{1,20}$";
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
					
					String groupLeader = "M001";
					
					
					String startLoc = req.getParameter("startLoc");
					
					
					String endLoc = req.getParameter("endLoc");
					
					
					Integer privates =1;
					if("1".equals(req.getParameter("privates"))) {
					privates = new Integer(req.getParameter("privates").trim());	
					}else {
					privates =0;	
						  }
					
					byte[] photo=null;
//					Collection<>=ima01,ima02;
					Collection<Part> parts = req.getParts();
					for (Part part : parts) {
						if (getFileNameFromPart(part) != null && part.getContentType()!=null) {
						
							
							long size = part.getSize();

							// 額外測試 InputStream 與 byte[] (幫將來model的VO預作準備)
							InputStream in = part.getInputStream();
							photo = new byte[in.available()];	
					in.read(photo);
							in.close();						
						}
					}
					
					
					Part part =null;
					part = req.getPart("photo");
					System.out.println(part);
					
					long size = part.getSize();
				
					InputStream in = part.getInputStream();
					
					photo = new byte[in.available()];
					if(in.available()!=0) {
					in.read(photo); 
					in.close();		
					}else {
						errorMsgs.add("請上傳照片");
						
					}
					
					String groupType = req.getParameter("groupType");
					
					
					Integer totalAmout	=0;
					
				
					java.sql.Timestamp startTime = null;
//					try {
					
//					String startTimes =null;
//					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				
				
					if("1".equals(req.getParameter("orderT"))) {try {
					
//						startTimes=req.getParameter("startTime");
						startTime = java.sql.Timestamp.valueOf(req.getParameter("startTime").trim());
							
							
						}
						 catch (IllegalArgumentException e) {
//								
								errorMsgs.add("日期: 請勿空白");
								startTime=new java.sql.Timestamp(System.currentTimeMillis());
							}
					}
					else {try {
//					    startTimes=req.getParameter("startTimes");
					    startTime = java.sql.Timestamp.valueOf(req.getParameter("startTimes").trim());
					    	
					}	
							
						
					 catch (IllegalArgumentException e) {
//							
							errorMsgs.add("日期: 請勿空白");
							startTime=new java.sql.Timestamp(System.currentTimeMillis());
						}
					}			
				
//   				startTime = new Timestamp(simpleDateFormat.parse(startTimes).getTime());
//					} catch (IllegalArgumentException e) {
//						startTime=new java.sql.Date(System.currentTimeMillis());
//						errorMsgs.add("請輸入日期!");
//					}
					
					
					
//					java.sql.Timestamp startTime = null;
//					
//					
//					if("1".equals(req.getParameter("orderT"))) {
//						try {	
//					startTime = java.sql.Timestamp.valueOf(req.getParameter("startTime").trim());
//		
//						} catch (IllegalArgumentException e) {
//							
//							errorMsgs.add("日期: 請勿空白");
//							startTime=new java.sql.Timestamp(System.currentTimeMillis());
//						}
//							
//							
//							
//						}
//					
//					else {
//						try {	
//						startTime = java.sql.Timestamp.valueOf(req.getParameter("startTims").trim());
//					
//					   
//						} catch (IllegalArgumentException e) {
//							
//							errorMsgs.add("日期: 請勿空白");
//							startTime=new java.sql.Timestamp(System.currentTimeMillis());
//						}
//							
//							
//							
//						}
					
				
					
				
   				
   				
					Integer rate =5;
					
					
					String note =req.getParameter("note");
					String notes = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$";
					if (note == null || note.trim().length() == 0) {
						errorMsgs.add("備註: 請勿空白");
					} else if(!note.trim().matches(notes)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("備註: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
		            }
					
					
					
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

					groupBandVO.setStartTime(startTime);
//					groupBandVO.setStartTime(new Date(simpleDateFormat.parse("2019-02-14").getTime()));
					groupBandVO.setRate(rate);
					groupBandVO.setNote(note);
				
					
					/***************************2.�}�l�s�W���***************************************/
					GroupBandService groupBandService = new GroupBandService();
					groupBandVO = groupBandService.addGroupBand(content,introduction,groupStatus,currenTnum,
							upperLimit,lowerLimit,groupName,groupLeader,startLoc,
							endLoc,privates,photo,groupType,totalAmout,startTime,
							rate,note);
					
					//產生訂單
					
					
					
					
//		-------------------產生 (揪團訂單 )多筆------------------------------------------
					
					
					String diverID =null;

					String memID [];
					
					memID = new String[upperLimit];
					
				memID[0]=groupLeader;
					
				for(int x=1;x<upperLimit;x++)
				{
						memID[x]=null;	
								
					}
					
					Integer state =1;
					
					Integer totalAmoutOr =0;
					
					Date startTimeOr =null;
					
					
					Date endTime =null;
					
					Double startLng = 0.1;
					
					Double startLat= 0.1;
					
					Double endLng= 0.1;
					
					
					Double endLat= 0.1;
					
					Integer orderType=0; //0是揪團 1是長期揪團
					
					Integer rateOr =0;
					
					 String noteOr =null;
					
					
					
					
			
					 
					GroupOrderService groupOrderService =new GroupOrderService();
			
					
					GroupOrderVO groupOrderVO[];
					groupOrderVO = new GroupOrderVO[upperLimit];
					for(int x=0; x<upperLimit;x++) {
						
					groupOrderVO[x] = (GroupOrderVO) groupOrderService.addGroupOrder
						 (diverID,memID[x],state,totalAmoutOr, startTimeOr, endTime,startLng, startLat, endLng, endLat, orderType, rateOr, noteOr);
					
					}
					
//		------------------------------------------------------------------------------------
					
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("GroupBandVO", groupBandVO); // �t����J�榡���~��GroupBandVO����,�]�s�Jreq
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/groupBand/insertGroupBand.jsp");
						failureView.forward(req, res);
						return;
					}
					
				

					
					
					/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
					String url = "/front-end/groupBand/listAllGroupBand.jsp";
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
