package com.activity.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PrintWriter;
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
  
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
    	doPost(req,res);
    } 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action =((ServletRequest) req).getParameter("action");
		PrintWriter out=res.getWriter();
	
		
		// 來自addActivity.jsp的請求  
		if("INSERT".equals(action)){
			LinkedList<String> errorMsgs =new LinkedList<String>();
			/*將errorMsgs設定為request scope，以便送至errorPage view*/
			req.setAttribute("errorMsgs", errorMsgs);
			java.sql.Date activityStart=null;
			byte[] activityPost=null;
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
				 System.out.println("activityName測試亂碼:"+activityName);
				 String activityInfo=(String) req.getParameter("activityInfo");
				 try {
					 activityStart=java.sql.Date.valueOf(req.getParameter("activityStart"));
				 }catch(IllegalArgumentException e) {
					 errorMsgs.add("日期格式轉換錯誤");
				 }
				 
				 java.sql.Date activityEnd=java.sql.Date.valueOf(req.getParameter("activityEnd"));
				 String activityCode=(String) req.getParameter("activityCode");
				 Integer tokenAmount=new Integer(req.getParameter("tokenAmount")) ;
				//此處由於參數取到的是檔案位置，因此需自訂方法，將請求參數得到的圖片路徑讀入並 寫出byte[]
				 /*表單屬性設定為enctype="multipart/form-data" 即可使用getPart傳遞圖片*/ /*但是會跳白*/
				 //寫一個writeInFileAndGetByet(Part part) 寫進本地、秀圖並回傳檔案位置，以指定給activityPost作為參數值
//				 Collection<Part> activityPost= (Collection<Part>) req.getPart("activityPost");
				 
				 try {
					 activityPost=this.getBytePost(req.getParameter("activityPost"));
				 }catch(Exception e) {
					 errorMsgs.add("無法轉型"+e.getMessage());
				 }
				 
				 
				 
				 if(activityName==null||activityName.trim().length()==0){
					 errorMsgs.add("活動名稱未填寫");
				 }else if(activityInfo==null||activityInfo.trim().length()==0){
					 errorMsgs.add("活動資訊未填寫");
				 }else if(activityEnd.getTime()<activityStart.getTime()){
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
			 ActivityVO addedActivityVO =activityService.addActivity(activityID,activityName,activityInfo,activityStart,activityEnd,activityCode,tokenAmount,activityPost);
			 if(addedActivityVO==null) {
				 errorMsgs.add("無法新增至DB");
			 }
			
			/**************step3.開始新增完成，轉交ListAllActivity頁面*****************/
			String url="/activity/listAllActivity.jsp";
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
			LinkedList<String> errorMsgs=new LinkedList();
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
			/*************3.圖片資料處理**************/
			String path="";//給空字串 
			if(activityVO.getActivityPost()!=null) {
				path=this.saveToGetPath(activityID,activityVO.getActivityPost(),req);
			}
			/*************4.得到資料存在scope=reqest，並送出VO給處理頁面**************/
			req.setAttribute("path", path);
			req.setAttribute("activityVO", activityVO);
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
			/*************3.資料處理:某一筆的vo的圖片資料轉換**************/
			/*在此處先將圖片做處理，寫入本地端並傳送檔案位置給頁面，即可秀圖*/
			/*將byte[]存入專案資料夾，以活動ID命名，回傳轉此圖的檔案路徑*/
			/*檢查是否有已上傳海報，有則儲存轉換為路徑，否則為null*/
			/*嘗試回傳相對路徑*/
			String path="";//給空字串 
			if(activityVO.getActivityPost()!=null) {
				path=this.saveToGetPath(activityID,activityVO.getActivityPost(),req);
			}
			/*************3.得到資料和圖片轉換資料存在scope=reqest，並送出VO給處理頁面:getOneUpdate頁面**************/
			
			req.setAttribute("path", path);
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
		
		
		//來自getOnUpdateActivity.jsp 的請求
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
				/*這邊還沒寫好*/
				 //activityPost若有更改，重新上傳為絕對路徑可直接傳入；
				 //若未更改則為回傳的是本地端儲存檔案的相對路徑，須轉換為絕對路徑才能使用FileInpustream讀取
				 ActivityService activitySvc1=new ActivityService();
				 ActivityVO originActivityVO=activitySvc1.getOneActivity(activityID);
				 
				 String abstactPath=getServletContext().getRealPath(req.getParameter("activityPost"));
				 String activityPostValue=req.getParameter("activityPost");
				 byte[] activityPost=null;
				 /*狀況3原本沒值有更改*/
				 if(originActivityVO.getActivityPost()==null&&activityPostValue.trim().length()!=0) {
					  activityPost=this.getBytePost(activityPostValue);
				 /*狀況4:原本沒值/沒更改*/
				 }else if(originActivityVO.getActivityPost()==null&&activityPostValue.trim().length()==0) {
					 activityPost=null;
				/*狀況2: 原本有值/沒改*/
				 }else if(originActivityVO.getActivityPost()!=null&&this.getBytePost(abstactPath)==originActivityVO.getActivityPost()){ 
					activityPost=this.getBytePost(req.getParameter(abstactPath));
				 /*狀況1 原本有值/有改*/
				 }else {
					 activityPost=this.getBytePost(activityPostValue);
				 }
				 
				 
				 System.out.println(req.getParameter("activityPost"));//測試路徑是否正常傳遞
				 if(activityName==null||activityName.trim().length()==0){
					 errorMsgs.add("活動名稱未填寫");
				 }else if(activityInfo==null||activityInfo.trim().length()==0){
					 errorMsgs.add("活動資訊未填寫");
				 }else if(activityEnd.getTime()<=activityStart.getTime()){
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
				 
				 
				 /*修改的資料如有錯誤，資料包進VO送回原頁面*/
				 if(!errorMsgs.isEmpty()) {
					 req.setAttribute("activityVO",activityVO);
					 RequestDispatcher failurePage =req.getRequestDispatcher("/activity/getOneUpdateActivity.jsp");
					 failurePage.forward(req, res);
					 return;//程式中止
				 }
	
				/*************2.開始修改**************/
				 ActivityService activitySvc=new ActivityService();
				 activitySvc.updateActivity(activityVO);
				 
				/*************3.修改完轉交頁面所有列表**************/
				 req.setAttribute("activityVO", activityVO);
				 RequestDispatcher seccessPage =req.getRequestDispatcher("/activity/listAllActivity.jsp");
				 seccessPage.forward(req, res);
				/*************4.處理例外轉交回原頁面**************/
			}catch(Exception e) {
				errorMsgs.add("修改資料失敗"+e.getMessage());
				RequestDispatcher failurePage=req.getRequestDispatcher("/activity/getOneUpdateActivity.jsp");
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
	/*用於初次客戶端上傳時秀圖*/
	/*處理圖片存進本地資夾，以便用路徑名稱顯示在jsp網頁<img>標籤中，利於預設值並秀圖*/
	/*parts裡可放多張Part型別的圖片*/
	/*Part介面方便用於檔案上傳*/
	public String writeInMyFileAndGetPath(Collection <Part>parts) {
		String myPath="/img_saveFromDB"; //指定資料夾名稱
		File file=new File(myPath);
		String realPath=getServletContext().getRealPath(myPath);//先取得指定資料在進專案的真實路徑
		System.out.println("realPath="+realPath);
		File realPathFile=new File(realPath);
		if(!realPathFile.exists()) {
			realPathFile.mkdirs();//在專案裡建立目錄
		}
		for(Part part:parts) {
			//取得上傳檔名
		}
		return realPath;
	}
	
	
	//byte[] 轉換真實相對路徑(才能秀圖)， 用acitivityID當檔名存入指定資料夾/img_saveFromDB
	public String saveToGetPath(String acitivityID,byte[] acitivityPost,HttpServletRequest req ) {
		/*取得相對路徑寫法*/
//		String relativePath="/activity/img_saveFromDB"; //指定資料夾名稱
//		String realPath=req.getContextPath()+relativePath;//先取得指定資料再進 ContextPath的路徑
//		System.out.println("realPath="+realPath);
//		File realPathFile=new File(realPath);
//		if(!realPathFile.exists()) {
//			realPathFile.mkdirs();//在 ContextPath裡建立目錄
//		}
		
//		/*絕對路徑寫法 伺服器無法取讀本地端資料*/
		String saveFile="/activity/img_saveFromDB"; /*指定儲存位置的資料夾名稱*/
		String realPath=getServletContext().getRealPath(saveFile);//取得資料夾在ContextPath下的真實路徑
		String relativePath=req.getContextPath()+saveFile; /*資料夾相對路徑*/
		File relativeFile=new File(realPath);//ContextPath下自動建目錄
		if(!relativeFile.exists()) {
			relativeFile.mkdirs();
		}
		FileOutputStream fos=null;
		String getRelativePath=relativePath+"\\"+acitivityID +".jpg";  /*檔案的相對路徑，回傳用*/
		String saveAbstractPath=realPath+"\\"+acitivityID +".jpg"; /*檔案的絕對路徑，儲存用*/
		//位元資料>bais讀入低階管>bis讀入高階管>fos寫出低階短管>檔案位置
		
		try {
			fos=new FileOutputStream(saveAbstractPath); //存絕對路徑
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//讀進
		ByteArrayInputStream bais=new ByteArrayInputStream(acitivityPost);
		BufferedInputStream bis=new BufferedInputStream(bais);
		
		int i;
		try {
			while((i=bis.read(acitivityPost))!=-1){	
				fos.write(acitivityPost,0,i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bis.close();
				bais.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return getRelativePath; //回傳相對位置，伺服器才能取讀
	}
	
	//將請求參數得到的圖片路徑讀入並 寫出byte[]
	public byte[] getBytePost(String ClientPath) throws IOException {
		File file=new File(ClientPath);
		FileInputStream fis;
		ByteArrayOutputStream baos;
		byte[] bytePost;
		
			fis = new FileInputStream(file);
			baos=new ByteArrayOutputStream();
			bytePost=new byte[fis.available()];
			while((fis.read(bytePost))!=-1) {
				baos.write(bytePost);
			}
			baos.close();
			fis.close();
		return bytePost;
	}
	
}


