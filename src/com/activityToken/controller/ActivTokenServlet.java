package com.activityToken.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

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
	
		if("INSERT_F0R_GET_ONES_ALL".equals(action)) {
			List<String> errorMsgs =new LinkedList<String>();
			List<ActivityTokenVO>  list =new LinkedList<ActivityTokenVO>();
			/*將errorMsgs設定為request scope，以便送至errorPage view*/
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			/**************step1.接收請求參數+錯誤處理*****************/
			
			try {
				
				 String memID=(String) req.getParameter("memID");
				 String activityCode=(String) req.getParameter("activityCode");
				 Integer tokenAmount=null;
				 java.sql.Date deadline=null;
				 String activityID=null;
				 ActivityVO activityVO=null;
				 
				 if(memID==null||memID.trim().length()==0){
					 errorMsgs.add("會員ID未填寫");
				 }else if(activityCode==null||activityCode.trim().length()==0){
					 errorMsgs.add("活動序號未填寫");
				 }
				 
				 //先查出activityCode對應的活動ID
				 //搭配活動DAO和activityService新增 透過活動序號找當筆活動資訊的方法findActicvityByCode() ；以及SQL指令  GET_ONE_BY_CODE
				 
				 ActivityService activityService=new ActivityService();
				 activityVO=activityService.findActicvityByCode(activityCode);//取到此筆活動代碼的活動VO
				 activityID=activityVO.getActivityID();
				 tokenAmount=activityVO.getTokenAmount();
				 deadline=activityVO.getActivityEnd();
				 
				 //存進活動代幣VO
				 ActivityTokenVO activityTokenVO=new ActivityTokenVO();
				 activityTokenVO.setMemID(memID);
				 activityTokenVO.setActivityID(activityID);
				 activityTokenVO.setTokenAmount(tokenAmount);
				 activityTokenVO.setDeadline(deadline);
				 
				 /*先送至下個頁面，錯誤頁面才能保留資料*/
				 req.setAttribute("activityTokenVO",activityTokenVO);
				 req.setAttribute("activityVO",activityVO);
		 
				 /*如有錯誤，資料包進VO送至錯誤頁面*/
				 if(!errorMsgs.isEmpty()) {
					 RequestDispatcher failurePage =req.getRequestDispatcher("/front-end/activityToken/addActivityToken.jsp");
					 failurePage.forward(req, res);
					 return;//程式中止
				 }
				 
		
			/**************step2.開始新增資料並listOnseAll*****************/
				 
			 ActivityTokenService activityTokenService=null;
				 //新增	 
			 try {
				activityTokenService=new ActivityTokenService();
				activityTokenVO =activityTokenService.addActivityToken(memID,activityID,tokenAmount,deadline);
			  }catch(Exception e) {
					 errorMsgs.add("您已領過本次活動代幣"+e.getMessage());
			 }
			 //listOnseAllActivity
			 try {
				 list=activityTokenService.getOnesALL(memID);
			 }catch(Exception e){
				 errorMsgs.add("無法得到此會員的所有活動代幣資訊");
			 }
			 
			 int sum=0;
			 //計算總額，送至listOnesAll.jsp
			 for(int i=0;i<list.size();i++) {
				 ActivityTokenVO activityTokenVO_forSum=list.get(i);
				 sum+=activityTokenVO_forSum.getTokenAmount();
			 }
			 req.setAttribute("sum", sum);
			 req.setAttribute("list", list);
			/**************step3.開始新增並listOnesAll完成，轉交ListAll頁面*****************/
			String url="/front-end/activityToken/listOnesAllActivityToken.jsp";
			RequestDispatcher successPage =req.getRequestDispatcher(url);
			successPage.forward(req, res);
			}
			/**************step4.處理其他可能的錯誤，轉交add頁面*****************/
			catch(Exception e){
				
				errorMsgs.add("無法新增此筆資料"+e.getMessage());
				RequestDispatcher failurePage=req.getRequestDispatcher("/front-end/activityToken/addActivityToken.jsp");
				failurePage.forward(req, res);
			}
		}
		
		// 此處待小蔣請提供(1)memID參數(2)「查詢代幣明細」的jsp頁面 後即可串接
		if("GET_ONES_ALL".equals(action)){
			List<String> errorMsgs =new LinkedList<String>();
			List<ActivityTokenVO> list=new LinkedList<ActivityTokenVO>();
			/*將errorMsgs設定為request scope，以便送至errorPage view*/
			req.setAttribute("errorMsgs", errorMsgs);
			
			/**************step1.接收請求參數+錯誤處理*****************/
			//接收請求參數memID
			try {
				 String memID=(String) req.getParameter("memID");
				 
				 if(memID==null||memID.trim().length()==0){
					 errorMsgs.add("未取得會員編號");
				 }
				 /*如有錯誤，資料包進VO送至錯誤頁面*/
				 if(!errorMsgs.isEmpty()) {
					 RequestDispatcher failurePage =req.getRequestDispatcher("/front-end/activityToken/addActivityToken.jsp");
					 failurePage.forward(req, res);
					 return;//程式中止
				 }
				 /**************step2.開始取得資料*****************/
				 ActivityTokenService activityTokenService=new ActivityTokenService();
				 try {
					 list=activityTokenService.getOnesALL(memID);
				 }catch(Exception e){
					 errorMsgs.add("無法得到此會員的所有活動代幣資訊");
				 }
				 
				 int sum=0;
				 //計算總額，送至listOnesAll.jsp
				 for(int i=0;i<list.size();i++) {
					 ActivityTokenVO activityTokenVO_forSum=list.get(i);
					 sum+=activityTokenVO_forSum.getTokenAmount();
				 }
				 
			/**************step3.開始查詢完成，轉交ListAll頁面*****************/
			req.setAttribute("sum", sum);
			req.setAttribute("list", list);
			String url="/front-end/activityToken/listOnesAllActivityToken.jsp";
			RequestDispatcher successPage =req.getRequestDispatcher(url);
			successPage.forward(req, res);
			}
			/**************step4.處理其他可能的錯誤，此處先轉交add頁面，之後轉交「查詢代幣明細」頁面*****************/
			catch(Exception e){
				errorMsgs.add("無法得到此會員活動代幣資料"+e.getMessage());
				RequestDispatcher failurePage=req.getRequestDispatcher("/front-end/activityToken/addActivityToken.jsp");
				failurePage.forward(req, res);
			}
		 }
		if("GET_ALL_STMT".equals(action)) {
			List<String> errorMsgs =new LinkedList<String>();
			List<ActivityTokenVO> list=new LinkedList<ActivityTokenVO>();
			/*將errorMsgs設定為request scope，以便送至errorPage view*/
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************1.接收請求參數**************/
			String activityID =(String)req.getParameter("activityID");
			if(activityID==null) {
				errorMsgs.add("無法取得本活動編碼");
			}
			/*************2.先查詢全部list**************/
			
			ActivityTokenService activityTokenSvc =new ActivityTokenService(); 
		    List<ActivityTokenVO> activityTokenlist= activityTokenSvc.getAll();
		    
			/*************3把 ID和活動list整包丟給頁面，再直接用el篩選條件查詢資料**************/
		    req.setAttribute("activityTokenlist", activityTokenlist); 
			req.setAttribute("activityID", activityID);
			String url="/back-end/activityToken/listAllActivityTokenByActivitvity.jsp";
			RequestDispatcher successPage=req.getRequestDispatcher(url);
			successPage.forward(req, res);
			/*************4.處理例外 回原頁面**************/
			}catch(Exception e) {
				errorMsgs.add("無法此筆資料:"+e.getMessage());
			}
			RequestDispatcher failurePage=req.getRequestDispatcher("/back-end/activity/listAllActivity.jsp");
			failurePage.forward(req, res);
		}//GET_ALL_STMT的if
	}
}
