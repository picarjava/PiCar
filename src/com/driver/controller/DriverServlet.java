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
import java.util.Calendar;
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
import com.member.model.MemberService;
import com.member.model.MemberVO;

import sun.util.resources.cldr.aa.CalendarData_aa_ER;
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
	    
	    if(pic == 1) {//jsp哪一個image呼叫
	    	byte[] licence = driverVO.getLicence();
	    	out.write(licence);//顯示在image src內 讀成二位元資料流
	    }
	    if(pic == 2) {//jsp哪一個image呼叫
	    	byte[] criminal = driverVO.getCriminal();
	    	out.write(criminal);//顯示在image src內 讀成二位元資料流
	    }
	    if(pic == 3) {//jsp哪一個image呼叫
	    	byte[] trafficRecord= driverVO.getTrafficRecord();
	    	out.write(trafficRecord);//顯示在image src內 讀成二位元資料流
	    }
	    if(pic == 4) {//jsp哪一個image呼叫
	    	byte[] idNum = driverVO.getIdNum();
	    	out.write(idNum);//顯示在image src內 讀成二位元資料流
	    }
	    if(pic == 5) {//jsp哪一個image呼叫
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
		try {
//		/************************ 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//		session.getAttribute("list");
//		MemberVO memberVO = (MemberVO)session1.getAttribute("memberVO");
//		String memID = "M003" ;//--假資料
		String memID =(String)(req.getParameter("memID"));//注意:正是從session 抓下來'
		DriverService drimem = new DriverService();
		DriverVO  driverd =drimem.getOneDriverBymemID(memID);//(memberVO.getMemID())
//	    session.setAttribute("driverVO",driverVO);
		if(driverd != null ) {//只能註冊一次司機
			String url = "/front-end/driver/homeDriverDataManagment.jsp";//比對是否為司機
			RequestDispatcher successView = req.getRequestDispatcher(url); //新增成功後轉交listOneDriver.jsp
			successView.forward(req, res);
		}
//		HttpSession session1 = req.getSession();
//		String memID = (String)(session1.getAttribute("MEM_ID"));
		String plateNum = (String)req.getParameter("plateNum").trim();
		String enameReg = "[a-zA-Z]{3}[0-9]{3,4}";
		if (plateNum == null || plateNum.trim().length() == 0) {
			errorMsgs.add("車牌號碼請勿空白");
		}else if (!plateNum.trim().matches(enameReg)) { //// 以下練習正則(規)表示式(regular-expression)
			errorMsgs.add("車牌號碼: 例如ABC0001");
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
		Integer banned= 0;//--未被BANNED
		Date deadline = null;//--
		Integer onlineCar= 0;//--沒在線上 由session判斷
		Integer score= 60;//--
////////////////////////////////////////////照片
		String carType= "PICAR";
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
//		driverVO.setDriverID(driverID);// 1.新增才能產生PK--> listall秀圖片--> 修改抓到driverID 2.自增主見autogenerate
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
			req.setAttribute("driverVO", driverVO); // // 含有輸入格式錯誤的driverVO物件,也存入req
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/driver/addDriver.jsp");
			failureView.forward(req, res);
			return;
		}
		/********************* *2.開始新增資料********************************************/
		/* 從addDriver.jsp取得的資料，透過DriverService操作DAO存進資料庫 */
		DriverService driverSvc ; // 以VO物件傳送參數 //資料庫產生PK
		driverSvc = new DriverService();
		driverVO = driverSvc.addDriver(memID, 
//				driverID, 
				plateNum,licence, criminal, trafficRecord, idNum, photo, 
				verified, banned, deadline, onlineCar, score, carType, sharedCar, pet, smoke, babySeat);
		/*****************************3.新增完成,準備轉交(Send the Success view)* Success view)**********/
		req.setAttribute("driverVO", driverVO);
		String url = "/front-end/driver/listOneDriver.jsp";//有空再來處理dirtyread
//		String url = "/front-end/driver/homeDriverDataManagment.jsp";
//		res.sendRedirect(url);//會404
		RequestDispatcher successView = req.getRequestDispatcher(url); //新增成功後轉交listOneDriver.jsp
		successView.forward(req, res);
		/**************************其他可能的錯誤處理***************************/
		}   catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/driver/addDriver.jsp");
			failureView.forward(req, res);
		}
	}
///////////////////	
//	//來自首頁(eg.司機會員管理)的請求(從session查出單筆司機資料)    ok 轉交司機資料管理
		if("GET_ONE_FRONT".equals(action)){// 
			LinkedList<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************1.接收請求參數**************/
//			try {
				String driverID=req.getParameter("driverID"); //這裡抓的是session
				if (driverID == null || (driverID.trim()).length() == 0) {
					errorMsgs.add("請輸入司機編號");
				}
				// Send the use back to the form, if there were errors
//			//	if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/driver/driverMemberManagement.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
			/*************2查詢資料**************/
			DriverService driverSvc=new DriverService();
			DriverVO driverVO=driverSvc.getOneDriver(driverID);
			if(driverVO==null) {
				errorMsgs.add("查無此筆");
				RequestDispatcher failurePage =req.getRequestDispatcher("/back-end/backHome.jsp");//改成後台首頁
				failurePage.forward(req, res);
				return;
			}
			/*************3.得到資料存在scope=request，並送出VO給處理頁面************/
			req.setAttribute("driverVO", driverVO);
			String url="/front-end/driver/homeDriverDataManagment.jsp";
			RequestDispatcher successPage=req.getRequestDispatcher(url);
			successPage.forward(req, res);
			/*************4.處理例外***************/
//		}
//			catch(Exception e){
//			errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
			}
//			RequestDispatcher  failurePage=req.getRequestDispatcher("/back-end/driver/driverMemberManagement.jsp");
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
//							.getRequestDispatcher("/back-end/driver/driverMemberManagement.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
			/*************2查詢資料**************/
			DriverService driverSvc=new DriverService();
			DriverVO driverVO=driverSvc.getOneDriver(driverID);
			if(driverVO==null) {
				errorMsgs.add("查無此筆");
				RequestDispatcher failurePage =req.getRequestDispatcher("/back-end/driver/driverMemberManagement.jsp");
				//
				failurePage.forward(req, res);
				return;
			}
			/*************3.得到資料存在scope=request，並送出VO給處理頁面**************/
			req.setAttribute("driverVO", driverVO);
			String url="/back-end/driver/listOneDriver.jsp";
			RequestDispatcher successPage=req.getRequestDispatcher(url);
			successPage.forward(req, res);
			/*************4.處理例外**************/
//		}
//			catch(Exception e){
//			errorMsgs.add("無法取得要修改的資料:"+e.getMessage());}
//			RequestDispatcher  failurePage=req.getRequestDispatcher("/back-end/driver/driverMemberManagement.jsp");
//			failurePage.forward(req, res);
		}
////////////////////////
//來自back-end/listAllDriver.jsp 修改  ban deadline verivfied==0 (後台驗證司機 )//OK
		//SELECT * FROM Driver ORDER BY VERIFIED ASC, DRIVER_ID ASC;先挑出驗證再牌號碼
if("GET_ONE_FOR_CHECK".equals(action)){
	try {
	/*************1.接收請求參數:某一筆司機IDD**************/
	String driverID=new String(req.getParameter("driverID").trim());
	String actionS=new String(req.getParameter("actionS").trim());
 	SimpleDateFormat sdf2 = new SimpleDateFormat("YYYY-MM-DD");
//	java.sql.Date deadline = date.add(Calendar.HOUR, 24);//--
 	
	// util.Date → util.Calendar
	java.util.Date date = new java.util.Date();
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	// util.Calendar → util.Date
	java.util.Date date2 = cal.getTime();
	// util.Date → sql.Date
	java.util.Date date5 = new java.util.Date();
	java.sql.Date sqlDate2 = new java.sql.Date(date5.getTime());
	String one_day_after = sdf2.format(date.getTime());
	long long_now =  new java.util.Date().getTime();
//	System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.sql.Timestamp(long_now)));
	/*************2查詢資料:調出某一筆的vo**************/
	DriverService driverSvc=new DriverService();
	DriverVO driverVO=driverSvc.getOneDriver(driverID);//從driverPK
	/*************3.得到資料和圖片轉換資料存在scope=reqest，並送出VO給處理頁面:getOneUpdate**************/
	MemberService memberService =new MemberService();
	MemberVO MemberVOs =memberService.getOneMember(driverVO.getMemID());
	req.setAttribute("MemberVOs",MemberVOs);
	req.setAttribute("driverVO", driverVO);
	if("GET_ONE_FOR_PERMIT".equals(actionS)) {
	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/driver/listOneDriver.jsp");
	failureView.forward(req, res);
	}
	if("GET_ONE_FOR_BANNED".equals(actionS)) {//有用
		if(driverVO.getBanned() == 0) {
			driverSvc.updateBanned(driverID);
		}else {
		}
		RequestDispatcher bannedView = req.getRequestDispatcher("/back-end/driver/listAllDriver.jsp");
		bannedView.forward(req, res);
	}
	if("GET_ONE_CHECK_PERMIT".equals(actionS)) {
		RequestDispatcher failureView = req.getRequestDispatcher("/back-end/driver/");//??
 failureView.forward(req, res);
	}
	/*************4.處理例外:回listALL原頁面**************/
}catch(Exception e){
}
}
//////////////////////////////////////////////////
//來自back-end/listAllDriver.jsp 修改  ban deadline ==0 (後台banned司機  )//??沒用
if("GET_ONE_FOR_BANNEDs".equals(action)){
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
		String url="/back-end/driver/listAllDriver.jsp";  //banned成功頁面
		RequestDispatcher successPage=req.getRequestDispatcher(url);
		successPage.forward(req, res);
		/*************4.處理例外:回listALL原頁面*************/
//	}catch(Exception e){
//		errorMsgs1.add("無法取得要修改的資料:"+e.getMessage());
//	}
	RequestDispatcher failurePage=req.getRequestDispatcher("/back-end/driver/listAllDriver.jsp");
	failurePage.forward(req, res);
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
//		}
	//////////////////////////////////////
if ("Update_Hobby".equals(action)) {   //阿君新增FOR前端喜好設定
	// 建立錯誤的collection
	List<String> errorMsgs = new LinkedList<String>();
	req.setAttribute("errorMsgs", errorMsgs);
	
//	try {
		String driverID = new String(req.getParameter("driverID").trim());

		Integer sharedCar = new Integer(req.getParameter("sharedCar"));
		Integer pet = new Integer(req.getParameter("pet"));
		Integer smoke = new Integer(req.getParameter("smoke"));
		Integer babySeat = new Integer(req.getParameter("babySeat"));


		DriverVO driverVO = new DriverVO();
		driverVO.setDriverID(driverID);
		driverVO.setSharedCar(sharedCar);
		driverVO.setPet(pet);
		driverVO.setSmoke(smoke);
		driverVO.setBabySeat(babySeat);
		
		DriverService driverService = new DriverService();
		driverVO = driverService.setHobby(sharedCar, pet, smoke, babySeat, driverID);
		
		RequestDispatcher successView = req.getRequestDispatcher("/front-end/driver/homeDriverDataManagment.jsp");
		successView.forward(req, res);
		
//	} catch (Exception e) {
//		errorMsgs.add("無法取得要修改的資料：" + e.getMessage());
//		RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/setting.jsp");
//		successView.forward(req, res);
//	}

}
//	if ("UPDATE_DRI".equals(action)) { //前端司機用(僅含喜好設定需改寫DAO) //?

//long size = part.getSize();//update用到
//System.out.println(size);
//  額外測試 InputStream 與 byte[] (幫將來model的VO預作準備)
//InputStream in = part.getInputStream();//免除多一個連線 不用開水館
//		List<String> errorMsgs = new LinkedList<String>();
//		// Store this set in the request scope, in case we need to
//		// send the ErrorPage view.
//		req.setAttribute("errorMsgs", errorMsgs);
//		DriverService driverService = new DriverService();
////		try {
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
//
//			Integer sharedCar = new Integer(req.getParameter("sharedCar"));
//			Integer pet = new Integer(req.getParameter("pet"));
//			Integer smoke = new Integer(req.getParameter("smoke"));
//			Integer babySeat =new Integer(req.getParameter("babySeat"));
//			
//			String groupName =req.getParameter("groupName");
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
//				errorMsgs.add("��酉: 隢蝛箇");
//			} else if(!note.trim().matches(notes)) { //隞乩�毀蝧迤���(閬�)銵函內撘�(regular-expression)
//				errorMsgs.add("��酉: ���銝准������摮� , 銝摨血���2�10銋��");
//            }
//		}
		
//			String memID = new String(req.getParameter("memID").trim());
//
//			String name = req.getParameter("name");
//			String nameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,20}$";
//			if (name == null || name.trim().length() == 0) {
//				errorMsgs.add("�撌亙��隢蝛箇");
//			} else if (!name.trim().matches(nameReg)) {
//				errorMsgs.add("��憪��撓� 銝剜�������摮��   \" , \"  , 銝摨血���2�20銋��");
//			}
//
//			Integer token = null;
//			try {
////				MemberDAO memberDAO = new MemberDAO();
//
////				token = memberDAO.getSumAmount(memID);
//				token = new Integer(req.getParameter("token").trim());
////				token = token + memberDAO.getSumAmount(memID);
//			} catch (NumberFormatException e) {
//				token = 0;
//				errorMsgs.add("隞�撟�隢‵�摮�.");
//			}
//
//			Integer activityToken = null;
//			try {
//				activityToken = new Integer(req.getParameter("activityToken").trim());
//			} catch (NumberFormatException e) {
//				activityToken = 100;
//				errorMsgs.add("瘣餃�誨撟�隢‵�摮�.");
//			}
//
//			java.sql.Date birthday = null;
//			try {
//				birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
//			} catch (IllegalArgumentException e) {
//				birthday = new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("隢撓�����!");
//			}
//
//			Integer pet = new Integer(req.getParameter("pet"));
//			Integer smoke = new Integer(req.getParameter("smoke"));
//			Integer gender = new Integer(req.getParameter("gender"));
//			Integer verified = new Integer(req.getParameter("verified"));
//			Integer babySeat = new Integer(req.getParameter("babySeat"));
//
//			MemberService memberSvc1 = new MemberService();
//			MemberVO memberVO1 = memberSvc1.getOneMember(memID);
//			byte[] picnow = memberVO1.getPic();
//
//			byte[] pic = null;
//
//			Part part = req.getPart("pic");
//			long size = part.getSize();
//			InputStream in = part.getInputStream();
//			pic = new byte[in.available()];
//
//			if (size != 0) {
//				in.read(pic);
////				in.close();
//			} else if (size == 0 && picnow == null) {
//				errorMsgs.add("隢撓�����");
//			} else if (picnow != null && size == 0) {
//				pic = picnow;
//			}
//			in.close();
//
//			MemberVO memberVO = new MemberVO();
//			memberVO.setMemID(memID);
//			memberVO.setName(name);
//			memberVO.setEmail(email);
//			memberVO.setPassword(password);
//			memberVO.setPhone(phone);
//			memberVO.setCreditcard(creditcard);
//			memberVO.setPet(pet);
//			memberVO.setSmoke(smoke);
//			memberVO.setGender(gender);
//			memberVO.setToken(token);
//			memberVO.setActivityToken(activityToken);
//			memberVO.setBirthday(birthday);
//			memberVO.setVerified(verified);
//			memberVO.setBabySeat(babySeat);
//			memberVO.setPic(pic);
//
//			if (!errorMsgs.isEmpty()) {
//
//				req.setAttribute("memberVO", memberVO); // ���撓��撘隤斤�mpVO�隞�,銋�req
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/member/update_member_input.jsp");
//				failureView.forward(req, res);
//				return; // 程式中斷
//			}
//
//			// ���耨�鞈��
//			MemberService memberSvc = new MemberService();
//			memberVO = memberSvc.updateMember(memID, name, email, password, phone, creditcard, pet, smoke, gender,
//					token, activityToken, birthday, verified, babySeat, pic);
//			req.setAttribute("memberVO", memberVO);
//
//			RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/listOneMember.jsp"); // �憓����漱listAllmember_byDAO
//			successView.forward(req, res);
//
//		} catch (Exception e) {
//			errorMsgs.add("�瘜���耨�������" + e.getMessage());
//			RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/update_member_input.jsp");
//			successView.forward(req, res);
//
//		}
//	
		
	}
	/////////////////��末閮剖�� (�璈�)
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
//				req.setAttribute("driverVO", driverVO); // // ���撓��撘隤斤�mpVO�隞�,銋�req
//	//			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/driver/----addDriver.jsp");//������
//				failureView.forward(req, res);
//				return;
//			}
//			/********************* *2.���憓���*****************************************/
	/* 敺銝��ession����riverID�������---addDriver.jsp���������riverService���AO摮�脰��澈 */
//	DriverService driverSvc1 ; // 隞冬O�隞嗅��
//	driverSvc1 = new DriverService();
//	driverVO = driverSvc.addDriver(memID, 
////			driverID, 
//			plateNum,
////			licence, criminal, trafficRecord, idNum, photo, 
//			verified, banned, deadline, onlineCar, score, carType, sharedCar, pet, smoke, babySeat);
//	/***************************
//			/***************************3.嚙編嚙磕嚙踝蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)***********/
////			req.setAttribute("GroupBandVO", groupBandVO);
//			String url = "/front-end/groupBand/listAllGroupBand.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙編嚙磕嚙踝蕭嚙穀嚙踝蕭嚙踝蕭嚙締istAllEmp.jsp
//			successView.forward(req, res);				
//			
//			/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭嚙羯嚙畿嚙緲**********************************/
//		} catch (Exception e) {
//			errorMsgs.add(e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/front-end/groupBand/updateGroupBand.jsp");
//			failureView.forward(req, res);
//		}		
//   	}
//}
//////////////////////////////////////////////////////////////////////	
	/* �������脰�冗嚗誑靘蹂誑��迂憿舐內�蝬脤��� */
	public static void readPicture(byte[] bytes, String picName) throws IOException {
//		Creates a file output stream to write to the file with the specified name. 
		FileOutputStream fos = new FileOutputStream(picName);
		fos.write(bytes);
		fos.flush();
		fos.close();
	}
	//byte[] 頧��祕�撠楝敺�(��蝘����)嚗� �acitivityID�瑼�������冗/img_saveFromDB
		public String saveToGetPath(String driverID,byte[] driverPost,HttpServletRequest req ) {
			/*���撠楝敺神瘜�*/
//			String relativePath="/activity/img_saveFromDB"; //�����冗��迂
//			String realPath=req.getContextPath()+relativePath;//����������� ContextPath��楝敺�
//			System.out.println("realPath="+realPath);
//			File realPathFile=new File(realPath);
//			if(!realPathFile.exists()) {
//				realPathFile.mkdirs();//� ContextPath鋆∪遣蝡���
//			}
			
//			/*蝯�楝敺神瘜� 隡箸��瘜����蝡航���*/
			String saveFile="/front-end/driver/img_saveFromDB"; /*���摮�蔭����冗��迂*/
			String realPath=getServletContext().getRealPath(saveFile);//�����冗�ContextPath銝��祕頝臬��
			String relativePath=req.getContextPath()+saveFile; /*鞈�冗�撠楝敺�*/
			File relativeFile=new File(realPath);//ContextPath銝��遣����
			if(!relativeFile.exists()) {
				relativeFile.mkdirs();
			}
			FileOutputStream fos=null;
			String getRelativePath=relativePath+"\\"+driverID +".jpg";  /*瑼��撠楝敺���*/
			String saveAbstractPath=realPath+"\\"+driverID +".jpg"; /*瑼����楝敺�摮*/
			//雿����>bais霈��雿�恣>bis霈��擃�恣>fos撖怠雿�蝞�>瑼��蔭
			
			try {
				fos=new FileOutputStream(saveAbstractPath); //摮��楝敺�
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			//霈���
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
			return getRelativePath; //���撠�蔭嚗撩�������
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
		System.out.println("header=" + header); // 皜祈岫�
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // 皜祈岫�
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}

