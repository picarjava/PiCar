package com.activityToken.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;
import com.activityToken.model.ActivityTokenService;
import com.activityToken.model.ActivityTokenVO;

/**
 * Servlet implementation class ActivTokenServlet
 */
@WebServlet("/ActivTokenServlet")
public class ActivTokenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivTokenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.getWriter().append("Served at: ").append(req.getContextPath());
	doPost(req,res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action =((ServletRequest) req).getParameter("action");
		PrintWriter out=res.getWriter();
	
		
		// 來自addActivity.jsp的請求  
		if("INSERT".equals(action)){
			LinkedList<String> errorMsgs =new LinkedList<String>();
			/*將errorMsgs設定為request scope，以便送至errorPage view*/
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			/**************step1.接收請求參數+錯誤處理*****************/
			
			try {
				
				 String memID=(String) req.getParameter("memID");
				 String activityID=(String) req.getParameter("activityID");
				 Integer tokenAmount=null;
				 java.sql.Date deadline=null;
				 
				 ActivityService activityService=new ActivityService();
				 ActivityVO activityVO=activityService.getOneActivity(activityID);
				 tokenAmount=activityVO.getTokenAmount();
				 deadline=activityVO.getActivityEnd();
				 
				 
				 if(memID==null||memID.trim().length()==0){
					 errorMsgs.add("未填寫");
				 }else if(activityID==null||activityID.trim().length()==0){
					 errorMsgs.add("活動代碼未填寫");
				 }
				 
				 ActivityTokenVO activityTokenVO=new ActivityTokenVO();
				 activityTokenVO.setMemID(memID);
				 activityTokenVO.setActivityID(activityID);
				 activityTokenVO.setTokenAmount(tokenAmount);
				 activityTokenVO.setDeadline(deadline);
				
				 
				 /*如有錯誤，資料包進VO送至錯誤頁面*/
				 if(!errorMsgs.isEmpty()) {
					 req.setAttribute("activityTokenVO",activityTokenVO);
					 RequestDispatcher failurePage =req.getRequestDispatcher("/activityToken/addActivityToken.jsp");
					 failurePage.forward(req, res);
					 return;//程式中止
				 }
				 
		
			/**************step2.開始新增資料*****************/
			 /*從addActiv.jsp取得的資料，透過ActivityService操作DAO存進資料庫*/
			 ActivityTokenService activityTokenService=new ActivityTokenService();
			 ActivityTokenVO addedActivityTokenVO =activityTokenService.addActivityToken(memID,activityID,tokenAmount,deadline);
			 if(addedActivityTokenVO==null) {
				 errorMsgs.add("無法新增至DB");
			 }
			
			/**************step3.開始新增完成，轉交ListAllActivity頁面*****************/
			String url="/activityToken/listAllActivityToken.jsp";
			RequestDispatcher successPage =req.getRequestDispatcher(url);
			successPage.forward(req, res);
		}
			/**************step4.處理其他可能的錯誤，轉交addActivity頁面*****************/
			catch(Exception e){
				errorMsgs.add("無法新增此筆資料"+e.getMessage());
				RequestDispatcher failurePage=req.getRequestDispatcher("/activityToken/addActivityToken.jsp");
				failurePage.forward(req, res);
			}
			 
		 }
		
		
		
	
	}

}
