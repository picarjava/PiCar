package com.driver.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.driver.model.DriverJNDIDAO;
import com.driver.model.DriverService;
import com.driver.model.DriverVO;
import com.member.model.MemberVO;
@MultipartConfig
//(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 5 * 50 * 1024 * 1024)
public class DriverServlet extends HttpServlet {//路徑在專案底下 讀圖片 根據專ˋ pic跟後台講。show出哪一張
	private static final long serialVersionUID = 1L;
	public DriverServlet() {
		super();
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//	顯示多張圖片
req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
//		
		ServletOutputStream out = res.getOutputStream();
		String driverID =req.getParameter("driverID");//從session抓driverID
		int pic = new Integer(req.getParameter("pic"));
		
		DriverService driverSvc = new DriverService();
	    DriverVO driverVO = driverSvc.getOneDriver(driverID);
	    System.out.println(driverID);
	    
	    if(pic == 1) {//jsp哪一個image呼叫
	    	byte[] licence = driverVO.getLicence();
	    	out.write(licence);//顯示在image src內 讀成二位元資料流
	    }
	    if(pic == 2) {//jsp哪一個image呼叫
	    	byte[] criminal = driverVO.getCriminal();
	    	out.write(criminal);//顯示在image src內 讀成二位元資料流
	    }
	    if(pic == 3) {//jsp哪一個image呼叫
	    	byte[] trafficRecord= driverVO.getTrafficRecord();
	    	out.write(trafficRecord);//顯示在image src內 讀成二位元資料流
	    }
	    if(pic == 4) {//jsp哪一個image呼叫
	    	byte[] idNum = driverVO.getIdNum();
	    	out.write(idNum);//顯示在image src內 讀成二位元資料流
	    }
	    if(pic == 5) {//jsp哪一個image呼叫
	    	byte[] photo = driverVO.getPhoto();
	    	out.write(photo);//顯示在image src內 讀成二位元資料流
	    }
//		System.out.println(driverID);
//////////////
////		Collection<Part> parts = req.getParts();
////		for (Part part : parts) {
////			if (getFileNameFromPart(part) != null && part.getContentType()!=null) {
////				long size = part.getSize();
////				// 額外測試 InputStream 與 byte[] (幫將來model的VO預作準備)
////				InputStream in = part.getInputStream();
////				photo = new byte[in.available()];						
////				in.close();						
////			}
////		}
////		Part part =null;
////		part = req.getPart("photo");
////		System.out.println(part);
////		
////		long size = part.getSize();
////		System.out.println(size);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		
		DriverJNDIDAO driverDAO = new DriverJNDIDAO();
		List<DriverVO> list = driverDAO.getAll();
		// 將資料存於set於session
		HttpSession session = req.getSession();
		session.setAttribute("list", list);
		session.getAttribute("list");
	///////////////////////////////////////////////////////////////////////
	if ("INSERT".equals(action)) { // 來自addDriver.jsp的請求 ok
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
//		try {
//		/************************ 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//		String msgID = req.getParameter("msgID");//對應233行
//		String msgID = "MSG";//對應232行 注意解除。add.jsp。disabled="disabled"
//		String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//		if (msgID == null || msgID.trim().length() == 0) {
//			errorMsgs.add("推播編號: 請勿空白");
//		} else if (!msgID.trim().matches(enameReg)) { //// 以下練習正則(規)表示式(regular-expression)
//			errorMsgs.add("推播編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間�");
//		}
		
//		session.getAttribute("list");
//		MemberVO memberVO = (MemberVO)session1.getAttribute("memberVO");
//		String memID = "M003" ;//--假資料
		String memID =(String)(req.getParameter("memID"));//注意:正是從session 抓下來'
		DriverService drimem = new DriverService();
		DriverVO  driverd =drimem.getOneDriverBymemID(memID);
//		DriverVO driverVO  = driSrc.getOneDriverBymemID(memberVO.getMemID());
		
//	    session.setAttribute("driverVO",driverVO);
		
		if(driverd != null ) {//只能註冊一次司機
			String url = "/front-end/driver/homeDriverDataManagment.jsp";//比對是否為司機
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listOneDriver.jsp
			successView.forward(req, res);
		}
//		HttpSession session1 = req.getSession();
//		String memID = (String)(session1.getAttribute("MEM_ID"));
		
//		String driverID=req.getParameter("driverID").trim();//注意:正是從session 抓下來
		String plateNum = (String)req.getParameter("plateNum").trim();
		
		if (plateNum == null || plateNum.trim().length() == 0) {
			errorMsgs.add("車牌號碼請勿空白");
		}
		///////////////////////////區域變數給初始值--圖片
		byte[] licence = null;
		byte[] criminal = null;
		byte[] trafficRecord = null;
		byte[] idNum = null;
		byte[] photo = null;
		Collection<Part> parts = req.getParts();
		for (Part part : parts) {
			part.getName();
		if (getFileNameFromPart(part) != null && part.getContentType()!=null) {//這裡是已經非空值
			
//			long size = part.getSize();//update用到
//			System.out.println(size);
			// 額外測試 InputStream 與 byte[] (幫將來model的VO預作準備)
//			InputStream in = part.getInputStream();//免除多一個連線 不用開水館
			switch(part.getName()) {
			case "licence":
				licence = new byte[part.getInputStream().available()];			
				part.getInputStream().read(licence);
				break;
			case "criminal":
				criminal = new byte[part.getInputStream().available()];
				part.getInputStream().read(criminal);//讀進去陣列中
				break;	
			case "trafficRecord":				
				trafficRecord = new byte[part.getInputStream().available()];					
				part.getInputStream().read(trafficRecord);
				break;	
			case "idNum":				
				idNum = new byte[part.getInputStream().available()];	
				part.getInputStream().read(idNum);
				break;					
			case "photo":				
				photo = new byte[part.getInputStream().available()];					
				part.getInputStream().read(photo);//inputstream獨到byte[] ，service存到資料庫 
				break;					
					}
//			in.close();	
			}
		else {//每行會跑 共5次驗證 /////錯誤驗證圖片
			switch (part.getName()) {
			case "licence":
			errorMsgs.add("請上傳駕照");	
				break;
			case "criminal":
			errorMsgs.add("請上傳criminal");	
				break;
			case "trafficRecord":
			errorMsgs.add("請上傳trafficRecord");	
				break;
			case "idNum":
			errorMsgs.add("請上傳idNum");	
				break;
			case "photo":
			errorMsgs.add("請上傳photo");	
				break;
//			default:
//				break;
			}
		}
	}
//		轉成byte[]; 先read進來 write出去
		Integer verified= 0;//--預設為未通過
		Integer banned= 0;//--
		Date deadline = null;//--
		Integer onlineCar= 0;//--沒在線上 由session判斷
		Integer score= 60;//--
////////////////////////////////////////////照片
		String carType= " ";
		carType = new String(req.getParameter("carType").trim()); //訊息做字串處理
		if (carType == null || carType.trim().length() == 0) {
			errorMsgs.add("車子品牌內容: 請勿空白");
		}
		//////////////
		Integer sharedCar ;
		sharedCar = new Integer(req.getParameter("sharedCar"));
		Integer pet;
		pet = new Integer(req.getParameter("pet"));
		Integer smoke;
		smoke = new Integer(req.getParameter("smoke"));
		Integer babySeat;
		babySeat =new Integer(req.getParameter("babySeat"));
		/////////////////
		DriverVO driverVO = new DriverVO();//告訴下頁面
		driverVO.setMemID(memID);
//		driverVO.setDriverID(driverID);//  1.新增才能產生PK--> listall秀圖片--> 修改抓到driverID 2.自增主見autogenerate
		driverVO.setPlateNum(plateNum);
		driverVO.setLicence(licence);
		driverVO.setCriminal(criminal);
		driverVO.setTrafficRecord(trafficRecord);
		driverVO.setIdNum(idNum);
		driverVO.setPhoto(photo);
		driverVO.setVerified(verified);
		driverVO.setBanned(banned);
		driverVO.setDeadline(deadline);
		driverVO.setOnlineCar(onlineCar);//
		driverVO.setScore(score);
		driverVO.setCarType(carType);
		driverVO.setSharedCar(sharedCar);//
		driverVO.setPet(pet);
		driverVO.setSmoke(smoke);
		driverVO.setBabySeat(babySeat);//
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("driverVO", driverVO); // // 含有輸入格式錯誤的empVO物件,也存入req
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/driver/addDriver.jsp");
			failureView.forward(req, res);
			return;
		}
		/********************* *2.開始新增資料*****************************************/
		/* 從addDriver.jsp取得的資料，透過DriverService操作DAO存進資料庫 */
		DriverService driverSvc ; // 以VO物件傳送參數 //資料庫產生PK
		driverSvc = new DriverService();
		driverVO = driverSvc.addDriver(memID, 
//				driverID, 
				plateNum,
				licence, criminal, trafficRecord, idNum, photo, 
				verified, banned, deadline, onlineCar, score, carType, sharedCar, pet, smoke, babySeat);
		/***************************
		 **3.新增完成,準備轉交(Send the Success view)* Success view)
		 ***********/
		//////////////////////
		req.setAttribute("driverVO", driverVO);
		String url = "/front-end/driver/listOneDriver.jsp";//有空再來處理dirtyread
		/////////////
//		String url = "/front-end/driver/homeDriverDataManagment.jsp";
//		res.sendRedirect(url);//會404
		//////////
		RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listOneDriver.jsp
		successView.forward(req, res);
		/***************************其他可能的錯誤處理*****************************/
		} 
//	RequestDispatcher requestDispatcher;
//    catch (Exception e) {
//			errorMsgs.add(e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/broadcast/addBrod.jsp");
//			failureView.forward(req, res);
//		}
//	}
///////////////////	
//	//來自首頁(eg.司機會員管理)的請求(從session查出單筆司機資料)   ok
		if("GET_ONE_FRONT".equals(action)){// 
			LinkedList<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************1.接收請求參數**************/
//			try {
				String driverID=req.getParameter("driverID"); //這裡抓的是session
				if (driverID == null || (driverID.trim()).length() == 0) {
					errorMsgs.add("請輸入司機編號");
				}
				// Send the use back to the form, if there were errors
//			//	if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/driver/司機會員管理.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
			/*************2查詢資料**************/
			DriverService driverSvc=new DriverService();
			DriverVO driverVO=driverSvc.getOneDriver(driverID);
			if(driverVO==null) {
				errorMsgs.add("查無此筆");
				RequestDispatcher failurePage =req.getRequestDispatcher("/back-end/driver/司機會員管理.jsp");//之後改成首頁
				failurePage.forward(req, res);
				return;
			}
			/*************3.得到資料存在scope=request，並送出VO給處理頁面**************/
			req.setAttribute("driverVO", driverVO);
			String url="/front-end/driver/homeDriverDataManagment.jsp";
			RequestDispatcher successPage=req.getRequestDispatcher(url);
			successPage.forward(req, res);
			/*************4.處理例外**************/
		}
//			catch(Exception e){
//			errorMsgs.add("無法取得要修改的資料:"+e.getMessage());}
//			RequestDispatcher  failurePage=req.getRequestDispatcher("/back-end/driver/司機會員管理.jsp");
//			failurePage.forward(req, res);
//		}	
///////////////////////////////////////////////////
//	//來自首頁(eg.司機會員管理)的請求(從session取出並新增司機)   //?
		if("BE_DRIVER".equals(action)){// 
			LinkedList<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************1.接收請求參數**************/
//			try {
			String memberID=req.getParameter("memberID"); //這裡抓的是session/////在session
			if (memberID == null || (memberID.trim()).length() == 0) {
				errorMsgs.add("請輸入司機編號");
			}
			// Send the use back to the form, if there were errors
//			//	if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/driver/司機會員管理.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
			/*************2查詢資料**************/
			DriverService driverSvc=new DriverService();
			DriverVO driverVO=driverSvc.getOneDriverBymemID(memberID);
			if(driverVO==null) {
				errorMsgs.add("查無此筆");
				RequestDispatcher failurePage =req.getRequestDispatcher("/back-end/driver/司機會員管理.jsp");//之後改成首頁
				failurePage.forward(req, res);
				return;
			}
			/*************3.得到資料存在scope=request，並送出VO給處理頁面**************/
			req.setAttribute("driverVO", driverVO);
			String url="/front-end/driver/addDriver.jsp";
			RequestDispatcher successPage=req.getRequestDispatcher(url);
			successPage.forward(req, res);
			/*************4.處理例外**************/
		}
//			catch(Exception e){
//			errorMsgs.add("無法取得要修改的資料:"+e.getMessage());}
//			RequestDispatcher  failurePage=req.getRequestDispatcher("/back-end/driver/司機會員管理.jsp");
//			failurePage.forward(req, res);
//		}	
/////////////////////////////////////////////////////
////來自homeDriver.jsp的請求(後台_管理員查出單筆司機資料) ok 
		if("GET_ONE_BACK".equals(action)){// 註解
			LinkedList<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************1.接收請求參數**************/
//			try {
				String driverID=req.getParameter("driverID");
				if (driverID == null || (driverID.trim()).length() == 0) {
					errorMsgs.add("請輸入司機編號");
				}
				// Send the use back to the form, if there were errors
//			//	if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/driver/司機會員管理.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
			/*************2查詢資料**************/
			DriverService driverSvc=new DriverService();
			DriverVO driverVO=driverSvc.getOneDriver(driverID);
			if(driverVO==null) {
				errorMsgs.add("查無此筆");
				RequestDispatcher failurePage =req.getRequestDispatcher("/back-end/driver/司機會員管理.jsp");
				failurePage.forward(req, res);
				return;
			}
			/*************3.得到資料存在scope=request，並送出VO給處理頁面**************/
			req.setAttribute("driverVO", driverVO);
			String url="/back-end/driver/listOneDriver.jsp";
			RequestDispatcher successPage=req.getRequestDispatcher(url);
			successPage.forward(req, res);
			/*************4.處理例外**************/
		}
//			catch(Exception e){
//			errorMsgs.add("無法取得要修改的資料:"+e.getMessage());}
//			RequestDispatcher  failurePage=req.getRequestDispatcher("/back-end/driver/司機會員管理.jsp");
//			failurePage.forward(req, res);
//		}
////////////////////////
//來自back-end/listAllDriver.jsp 修改  ban deadline verivfied==0 (後台驗證司機  )//??
		//SELECT * FROM Driver ORDER BY VERIFIED ASC, DRIVER_ID ASC; 先挑出驗證再牌號碼
if("GET_ONE_FOR_CHECK".equals(action)){
	try {
	/*************1.接收請求參數:某一筆活動ID**************/
	String driverID=new String(req.getParameter("driverID").trim());
	
	/*************2查詢資料:調出某一筆的vo**************/
	DriverService driverSvc=new DriverService();
	DriverVO driverVO=driverSvc.getOneDriver(driverID);//從driverPK
	/*************3.得到資料和圖片轉換資料存在scope=reqest，並送出VO給處理頁面:getOneUpdate頁面**************/
	if(driverVO.getVerified()==0) {
		
	}
	req.setAttribute("driverVO", driverVO);
	String url="/back-end/driver/getOneUpdateActivity.jsp";  //驗證成功頁面
	RequestDispatcher successPage=req.getRequestDispatcher(url);
	successPage.forward(req, res);
	/*************4.處理例外:回listALL原頁面**************/
}catch(Exception e){
}
//////////////////////////////////////////////////
//來自back-end/listAllDriver.jsp 修改  ban deadline ==0 (後台banned司機  )//??
if("GET_ONE_FOR_BANNED".equals(action)){
	List<String> errorMsgs1=new LinkedList<String>();//剩下1.跳轉畫面2.設定DEADLINE3.
	req.setAttribute("errorMsgs", errorMsgs1);
//	try {
		/*************1.接收請求參數:某一筆司機ID**************/
		String driverID=req.getParameter("driverID");
		java.sql.Date deadline = null;//--
//		deadline
		/*************2查詢資料:調出某一筆的vo**************/
		DriverService driverSvc=new DriverService();
		DriverVO driverVO=driverSvc.getOneDriver(driverID);//從driverPK
		/*************3.得到資料和圖片轉換資料存在scope=reqest，並送出VO給處理頁面:getOneUpdate頁面**************/
		if(driverVO.getBanned() == 0) {
			driverSvc.updateBanned(driverID);
		}else {
		}
//		執行排程器計時&&到時轉回
		
		req.setAttribute("driverVO", driverVO);
		String url="/back-end/driver/listAllDriver.jsp";  //banned成功頁面
		RequestDispatcher successPage=req.getRequestDispatcher(url);
		successPage.forward(req, res);
		/*************4.處理例外:回listALL原頁面**************/
//	}catch(Exception e){
//		errorMsgs1.add("無法取得要修改的資料:"+e.getMessage());
//	}
	RequestDispatcher failurePage=req.getRequestDispatcher("/back-end/driver/listAllDriver.jsp");
	failurePage.forward(req, res);
}
}
////////////////////////////////////////////////////////////////////////////////
//	給後端update使用()可參考全寫法
//	DEADLINE DATE
//	java.sql.Date time = null;
//	try {
//		time = java.sql.Date.valueOf(req.getParameter("time").trim());		
//	} catch (IllegalArgumentException e) {
//		time = new java.sql.Date(System.currentTimeMillis());
//		errorMsgs.add("請輸入日期");
//	}
//	}
//////////////
//	update
//	Date deadline = null;//被ban的時間
//	driverVO.setDeadline(deadline);//修改時使用
		}
	//////////////////////////////////////
//	if ("UPDATE_DRI".equals(action)) { //前端司機用(僅含喜好設定需改寫DAO) //?
//		List<String> errorMsgs = new LinkedList<String>();
//		// Store this set in the request scope, in case we need to
//		// send the ErrorPage view.
//		req.setAttribute("errorMsgs", errorMsgs);
//		GroupBandService groupBandService = new GroupBandService();
//		try {
//			/***********************1.接收請求參數*************************/
//			DriverVO driverVO = new DriverVO();
//			String content =" ";	
//			java.sql.Timestamp launchTime = null;
////			try {
//			String launchTimes =null;
//			launchTimes = req.getParameter("LaunchTime");
//			SimpleDateFormat simpleDateFormats = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//			
//			launchTime = new Timestamp(simpleDateFormats.parse(launchTimes).getTime());
//			
//			String introduction = req.getParameter("introduction");
//			String introduc = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{1,20}$";
//			if (introduction == null || introduction.trim().length() == 0) {
//				errorMsgs.add("簡介: 請勿空白");
//			} else if(!introduction.trim().matches(introduc)) { //以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("簡介: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//            }
//			Integer groupStatus =1;
//			
//			String groupName =req.getParameter("groupName");
//			String groupN = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$";
//			if (groupName == null || groupName.trim().length() == 0) {
//				errorMsgs.add("團名: 請勿空白");
//			} else if(!groupName.trim().matches(groupN)) { //以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("團名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//            }
//			if("1".equals(req.getParameter("privates"))) {
//			privates = new Integer(req.getParameter("privates").trim());	
//			}else {
//			privates =0;	
//				  }
//			
//			GroupBandVO groupBandVO1 = (GroupBandVO)groupBandService.getOneGroupBand(req.getParameter("groupID"));
//			
//			String groupType = req.getParameter("groupType");
//			
//			String note =req.getParameter("note");
//			String notes = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$";
//			if (note == null || note.trim().length() == 0) {
//				errorMsgs.add("備註: 請勿空白");
//			} else if(!note.trim().matches(notes)) { //以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("備註: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//            }
	/////////////////喜好設定 (司機)
//	String driverID=req.getParameter("driverID").trim();//注意:正是從session 抓下來
//	Integer sharedCar = 0;
//	Integer pet= 0;
//	Integer smoke= 0;
//	Integer babySeat= 0;
//	sharedCar = new Integer(req.getParameter("sharedCar"));
//	pet = new Integer(req.getParameter("pet"));
//	smoke = new Integer(req.getParameter("smoke"));
//	babySeat =new Integer(req.getParameter("babySeat"));
	/////////////////
		//	DriverVO driverVO = new DriverVO();
		//	driverVO.setMemID(memID);
		////	driverVO.setDriverID(driverID);//
		//	driverVO.setPlateNum(plateNum);
		//	driverVO.setLicence(licence);
		//	driverVO.setCriminal(criminal);
		//	driverVO.setTrafficRecord(trafficRecord);
		//	driverVO.setIdNum(idNum);
		//	driverVO.setPhoto(photo);
		//	driverVO.setVerified(verified);
		//	driverVO.setBanned(banned);
		//	driverVO.setDeadline(deadline);
		//	driverVO.setOnlineCar(onlineCar);//
		//	driverVO.setScore(score);
		//	driverVO.setCarType(carType);
		//	driverVO.setSharedCar(sharedCar);//
		//	driverVO.setPet(pet);
		//	driverVO.setSmoke(smoke);
		//	driverVO.setBabySeat(babySeat);//
	// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("driverVO", driverVO); // // 含有輸入格式錯誤的empVO物件,也存入req
//	//			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/driver/----addDriver.jsp");//更新成功
//				failureView.forward(req, res);
//				return;
//			}
//			/********************* *2.開始新增資料*****************************************/
	/* 從哪一個session取得的driverID所查到的---addDriver.jsp取得的資料，透過DriverService操作DAO存進資料庫 */
//	DriverService driverSvc1 ; // 以VO物件傳送參數
//	driverSvc1 = new DriverService();
//	driverVO = driverSvc.addDriver(memID, 
////			driverID, 
//			plateNum,
////			licence, criminal, trafficRecord, idNum, photo, 
//			verified, banned, deadline, onlineCar, score, carType, sharedCar, pet, smoke, babySeat);
//	/***************************
//			/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
////			req.setAttribute("GroupBandVO", groupBandVO);
//			String url = "/front-end/groupBand/listAllGroupBand.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
//			successView.forward(req, res);				
//			
//			/***************************��L�i�઺���~�B�z**********************************/
//		} catch (Exception e) {
//			errorMsgs.add(e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/front-end/groupBand/updateGroupBand.jsp");
//			failureView.forward(req, res);
//		}		
//   	}
//////////////////////////////////////////////////////////////////////	
	/* 處理圖片存進資夾，以便以名稱顯示在網頁上 */
	public static void readPicture(byte[] bytes, String picName) throws IOException {
//		Creates a file output stream to write to the file with the specified name. 
		FileOutputStream fos = new FileOutputStream(picName);
		fos.write(bytes);
		fos.flush();
		fos.close();
	}
	//byte[] 轉換真實相對路徑(才能秀圖)， 用acitivityID當檔名存入指定資料夾/img_saveFromDB
		public String saveToGetPath(String driverID,byte[] driverPost,HttpServletRequest req ) {
			/*取得相對路徑寫法*/
//			String relativePath="/activity/img_saveFromDB"; //指定資料夾名稱
//			String realPath=req.getContextPath()+relativePath;//先取得指定資料再進 ContextPath的路徑
//			System.out.println("realPath="+realPath);
//			File realPathFile=new File(realPath);
//			if(!realPathFile.exists()) {
//				realPathFile.mkdirs();//在 ContextPath裡建立目錄
//			}
			
//			/*絕對路徑寫法 伺服器無法取讀本地端資料*/
			String saveFile="/front-end/driver/img_saveFromDB"; /*指定儲存位置的資料夾名稱*/
			String realPath=getServletContext().getRealPath(saveFile);//取得資料夾在ContextPath下的真實路徑
			String relativePath=req.getContextPath()+saveFile; /*資料夾相對路徑*/
			File relativeFile=new File(realPath);//ContextPath下自動建目錄
			if(!relativeFile.exists()) {
				relativeFile.mkdirs();
			}
			FileOutputStream fos=null;
			String getRelativePath=relativePath+"\\"+driverID +".jpg";  /*檔案的相對路徑，回傳用*/
			String saveAbstractPath=realPath+"\\"+driverID +".jpg"; /*檔案的絕對路徑，儲存用*/
			//位元資料>bais讀入低階管>bis讀入高階管>fos寫出低階短管>檔案位置
			
			try {
				fos=new FileOutputStream(saveAbstractPath); //存絕對路徑
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			//讀進
			ByteArrayInputStream bais=new ByteArrayInputStream(driverPost);
			BufferedInputStream bis=new BufferedInputStream(bais);
			
			int i;
			try {
				while((i=bis.read(driverPost))!=-1){	
					fos.write(driverPost,0,i);
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
	
	public byte[] getBytePost(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis;
		ByteArrayOutputStream baos;
		byte[] bytePost;

		fis = new FileInputStream(file);
		baos = new ByteArrayOutputStream();
		bytePost = new byte[fis.available()];
		while ((fis.read(bytePost)) != -1) {
			baos.write(bytePost);
		}
		baos.close();
		fis.close();
		return bytePost;
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

