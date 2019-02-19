package com.activity.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.activity.model.ActivityJDBCDAO;
import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;

/**
 * Servlet implementation class activ_servlet
 */

public class ActivServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ActivityVO activityVO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setCharacterEncoding("UTF-8");
		String action =((ServletRequest) req).getParameter("action");
		String btn=req.getParameter("btn");
//		
		
		// 來自addActivity.jsp的請求  
		if("INSERT".equals(action)){
			LinkedList<String> errorMsgs =new LinkedList<String>();
			/*將errorMsgs設定為request scope，以便送至errorPage view*/
			req.setAttribute("errorMsgs", errorMsgs);
			/**************step1.接收請求參數+錯誤處理*****************/
			
			try {
				String engNum="^[(a-zA-Z0-9)]{1,10}$";
				String activityID =(String) req.getParameter("activityID");
				 if(activityID==null||activityID.trim().length()==0) {
					 errorMsgs.add("活動編號未填寫");
				 }else if(engNum.equals(activityID)){
					 errorMsgs.add("活動編號需為1-10個英文或數字");
				 }
				 
				 String activityName=(String) req.getParameter("activityName");
				 String activityInfo=(String) req.getParameter("activityInfo");
				 java.sql.Date activityStart=java.sql.Date.valueOf(req.getParameter("activityStart"));
				 java.sql.Date activityEnd=java.sql.Date.valueOf(req.getParameter("activityEnd"));
				 String activityCode=(String) req.getParameter("activityCode");
				 Integer tokenAmount=new Integer(req.getParameter("tokenAmount")) ;
				 /*表單屬性設定為enctype="multipart/form-data" 即可使用getPart傳遞圖片*/
				 Collection<Part> activityPost= (Collection<Part>) req.getPart("activityPost");
				 if(activityName==null||activityName.trim().length()==0){
					 errorMsgs.add("活動名稱未填寫");
				 }else if(activityInfo==null||activityInfo.trim().length()==0){
					 errorMsgs.add("活動資訊未填寫");
				 }else if(activityEnd.getTime()>=activityStart.getTime()){
					 errorMsgs.add("活動開始時間需早於活動結束間之前");
				 }
				 
				 ActivityVO activityVO=new ActivityVO();
				 activityVO.setActivityID(activityID);
				 activityVO.setActivityName(activityName);
				 activityVO.setActivityInfo(activityInfo);
				 activityVO.setActivityStart(activityStart);
				 activityVO.setActivityEnd(activityEnd);
				 activityVO.setActivityCode(activityCode);
				 activityVO.setTokenAmount(tokenAmount);
				 activityVO.setActivityPost(activityPost);
				 
				 
				 /*如有錯誤，資料包進VO送至錯誤頁面*/
				 if(!errorMsgs.isEmpty()) {
					 req.setAttribute("activityVO",activityVO);
					 RequestDispatcher failurePage =req.getRequestDispatcher("/activity/addActivity.jsp");
					 failurePage.forward(req, res);
					 return;//程式中止
				 }
				 
		
			/**************step2.開始新增資料*****************/
			 /*從addActiv.jsp取得的資料，透過ActivityService操作DAO存進資料庫*/
			 ActivityService activityService=new ActivityService();
			 activityService.addActivity(activityID,activityName,activityInfo,activityStart,activityEnd,activityCode,tokenAmount,activityPost);
			
			
			/**************step3.開始新增完成，轉交ListAllActivity頁面*****************/
			String url="/activity.listAllActivity.jsp";
			RequestDispatcher successPage =req.getRequestDispatcher(url);
			successPage.forward(req, res);
			}
			/**************step4.處理其他可能的錯誤，轉交addActivity頁面*****************/
			catch(Exception e){
				errorMsgs.add("無法新增此筆資料"+e.getMessage());
				RequestDispatcher failurePage=req.getRequestDispatcher("/activity/addActivity.jsp");
				failurePage.forward(req, res);
			}
			 
		 }
		//來自homeActivity.jsp的請求
		if("GET_ONE".equals(action)){
			LinkedList errorMsgs=new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
			/*************1.接收請求參數**************/
			String activityID=req.getParameter("activityID");
			
			
			/*************2查詢資料**************/
			ActivityService activitySvc=new ActivityService();
			ActivityVO activityVO=activitySvc.getOneActivity(activityID);
			if(activityVO==null) {
				errorMsgs.add("查無此筆");
				RequestDispatcher failurePage=req.getRequestDispatcher("/activity/listAllActivity.jsp");
				failurePage.forward(req, res);
				return;
			}
			/*************3.得到資料存在scope=reqest，並送出VO給處理頁面**************/
			req.setAttribute("activityVO", activityVO);
			readPicture(activityVO.getActivityPost(),"ActivityPost.jpg"); //把海報存在某名稱
			String url="/activity/getOneActivity.jsp";
			RequestDispatcher successPage=req.getRequestDispatcher(url);
			successPage.forward(req, res);
			/*************4.處理例外**************/
		}catch(Exception e){
			errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
			}
			RequestDispatcher failurePage=req.getRequestDispatcher("/activity/listAllActivity.jsp");
			failurePage.forward(req, res);
		}
		
		//來自listAllActivity.jsp 修改某一筆的請求
		if("GET_ONE_FOR_UPTDATE".equals(action)){
			LinkedList errorMsgs=new LinkedList();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
			/*************1.接收請求參數:某一筆活動ID**************/
			String activityID=req.getParameter("activityID");
			
			
			/*************2查詢資料:調出某一筆的vo**************/
			ActivityService activitySvc=new ActivityService();
			ActivityVO activityVO=activitySvc.getOneActivity(activityID);
			
			/*************3.得到資料存在scope=reqest，並送出VO給處理頁面:getOneUpdate頁面**************/
			req.setAttribute("activityVO", activityVO);
			String url="/activity/getOneUpdateActivity.jsp";
			RequestDispatcher successPage=req.getRequestDispatcher(url);
			successPage.forward(req, res);
			/*************4.處理例外:回listALL原頁面**************/
		}catch(Exception e){
			errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
			}
			RequestDispatcher failurePage=req.getRequestDispatcher("/activity/listAllActivity.jsp");
			failurePage.forward(req, res);
		}
		
		
		//來自getOneActivity.jsp 以及getOnUpdateActivity.jsp單筆查詢的請求
		if("UPDATE".equals(action)) {
			LinkedList<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try{
				/*************1.取出修改過的參數**************/
				String engNum="^[(a-zA-Z0-9)]{1,10}$";
				String activityID =(String) req.getParameter("activityID");
				 if(activityID==null||activityID.trim().length()==0) {
					 errorMsgs.add("活動編號未填寫");
				 }else if(engNum.equals(activityID)){
					 errorMsgs.add("活動編號需為1-10個英文或數字");
				 }
				 
				 String activityName=(String) req.getParameter("activityName");
				 String activityInfo=(String) req.getParameter("activityInfo");
				 java.sql.Date activityStart=java.sql.Date.valueOf(req.getParameter("activityStart"));
				 java.sql.Date activityEnd=java.sql.Date.valueOf(req.getParameter("activityEnd"));
				 String activityCode=(String) req.getParameter("activityCode");
				 Integer tokenAmount=new Integer(req.getParameter("tokenAmount")) ;
				 Collection<Part> activityPost= (Collection<Part>) req.getPart("activityPost");
				 if(activityName==null||activityName.trim().length()==0){
					 errorMsgs.add("活動名稱未填寫");
				 }else if(activityInfo==null||activityInfo.trim().length()==0){
					 errorMsgs.add("活動資訊未填寫");
				 }else if(activityEnd.getTime()>=activityStart.getTime()){
					 errorMsgs.add("活動開始時間需早於活動結束間之前");
				 }
				 
				 ActivityVO activityVO=new ActivityVO();
				 activityVO.setActivityID(activityID);
				 activityVO.setActivityName(activityName);
				 activityVO.setActivityInfo(activityInfo);
				 activityVO.setActivityStart(activityStart);
				 activityVO.setActivityEnd(activityEnd);
				 activityVO.setActivityCode(activityCode);
				 activityVO.setTokenAmount(tokenAmount);
				 activityVO.setActivityPost(activityPost);
				 
				 
				 /*修改的資料如有錯誤，資料包進VO送回getOneActivity.jsp頁面*/
				 if(!errorMsgs.isEmpty()) {
					 req.setAttribute("activityVO",activityVO);
					 RequestDispatcher failurePage =req.getRequestDispatcher("/activity/getOneActivity.jsp");
					 failurePage.forward(req, res);
					 return;//程式中止
				 }
	
				/*************2.開始修改**************/
				 ActivityService ActivitySvc=new ActivityService();
				 ActivitySvc.updateActivity(activityVO);
				 
				/*************3.修改完轉交頁面所有列表**************/
				 req.setAttribute("activityVO", activityVO);
				 RequestDispatcher seccessPage =req.getRequestDispatcher("/activity/listAllActivity.jsp");
				 seccessPage.forward(req, res);
				/*************4.處理例外轉交回原頁面**************/
			}catch(Exception e) {
				errorMsgs.add("修改資料失敗"+e.getMessage());
				RequestDispatcher failurePage=req.getRequestDispatcher("/activity/getOneActivity.jsp");
				failurePage.forward(req, res);
			}
			
			
		}
		
		
		//來自listAllActivity.jsp的請求
		
		if("DELETE".equals(action)){
			LinkedList<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try{
				/*************1.接收請求**************/
				String activityID=req.getParameter("activityID");
				
				/*************2.開始刪除**************/
				ActivityService activitySvc=new ActivityService();
				activitySvc.deleteActivity(activityID);
				/*************3.轉交listALL頁面查看是否刪除**************/
				RequestDispatcher successPage=req.getRequestDispatcher("/activity/listAllActivity.jsp");
				successPage.forward(req, res);
				/*************4.處理例外**************/
			}catch(Exception e) {
				errorMsgs.add("無法刪除"+e.getMessage());
				RequestDispatcher failurePage=req.getRequestDispatcher("/activity/listAllActivity.jsp");
				failurePage.forward(req, res);
			}
			
		}
	}
	/*處理圖片存進資夾，以便以名稱顯示在網頁上*/
	public static void readPicture(byte[] bytes,String picName) throws IOException{
//		Creates a file output stream to write to the file with the specified name. 
		FileOutputStream fos=new FileOutputStream(picName);
			fos.write(bytes);
			fos.flush();
			fos.close();
	}
	
	public static byte[] ObjectToByteArray(Object parameter) {
		byte[] post=null;
		
		return post;
	}
}


