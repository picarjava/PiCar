package com.groupBand.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.groupBand.model.*;




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
					
					Collection<Part> parts = req.getParts();
					for (Part part : parts) {
						if (getFileNameFromPart(part) != null && part.getContentType()!=null) {
						
							
							long size = part.getSize();

							// 額外測試 InputStream 與 byte[] (幫將來model的VO預作準備)
							InputStream in = part.getInputStream();
							photo = new byte[in.available()];						
							in.close();						
						}
					}
					
					
					
					
					String groupType = req.getParameter("groupType");
					
					
					Integer totalAmout	=0;
					
				
					java.sql.Date startTime = null;
//					try {
					String startTimes =null;
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				
				
					if("1".equals(req.getParameter("orderT"))) {
						startTimes=req.getParameter("startTime");
					}
					else {
					    startTimes=req.getParameter("startTimes");
					}			
					
   				startTime = new Date(simpleDateFormat.parse(startTimes).getTime());
//					} catch (IllegalArgumentException e) {
//						startTime=new java.sql.Date(System.currentTimeMillis());
//						errorMsgs.add("請輸入日期!");
//					}
					
					
					Integer rate =5;
					
					
					String note =req.getParameter("note");
					String notes = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$";
					if (note == null || note.trim().length() == 0) {
						errorMsgs.add("員工姓名: 請勿空白");
					} else if(!note.trim().matches(notes)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
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
				
					

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("GroupBandVO", groupBandVO); // �t����J�榡���~��GroupBandVO����,�]�s�Jreq
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/groupBand/insertGroupBand.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.�}�l�s�W���***************************************/
					GroupBandService groupBandService = new GroupBandService();
					groupBandVO = groupBandService.addGroupBand(content,introduction,groupStatus,currenTnum,
							upperLimit,lowerLimit,groupName,groupLeader,startLoc,
							endLoc,privates,photo,groupType,totalAmout,startTime,
							rate,note);
					

					
					
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
