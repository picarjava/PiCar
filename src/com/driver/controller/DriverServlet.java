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
import javax.servlet.http.Part;

import com.driver.model.DriverJDBCDAO;
import com.driver.model.DriverService;
import com.driver.model.DriverVO;
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
		String driverID =req.getParameter("driverID");
		int pic = new Integer(req.getParameter("pic"));
		
		DriverService driverSvc = new DriverService();
	    DriverVO driverVO = driverSvc.getOneDriver(driverID);
	    
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
////		byte[] licence=null;
//		byte[] licence = driverVO.getLicence();
//		if(licence !=null) {
//			out.write(licence);
//		}
//		byte[] criminal = driverVO.getCriminal();
//		if(criminal !=null) {
//			out.write(criminal);
//		}
//		byte[] trafficRecord = driverVO.getTrafficRecord();
//		if(trafficRecord !=null) {
//			out.write(trafficRecord);
//		}
//		byte[] idNum = driverVO.getIdNum();
//		if(idNum !=null) {
//			out.write(idNum);
//		}
//		byte[] photo = driverVO.getPhoto();
//		if(photo !=null) {
//			out.write(photo);
//		}
//////////////
////		String realPath = getServletContext().getRealPath("/images_uploaded");
////		System.out.println(req.getContentType());
////
////		String[] xxx = req.getContentType().split(";"); // 分割前後兩段 xxx陣列
////		
////		if (xxx[0].equals("multipart/form-data")) {// 比字串內容.equals()
////			Collection<Part> parts = req.getParts();
////			//新增修改刪除先 顯示放最後
////			for (Part part : parts) {
////				if (part.getSubmittedFileName() != "") { // 未加此行 會filenotfoundexception
////					part.write(realPath + "/" + part.getSubmittedFileName());// 資料夾名 檔名
////				}
////			}
////		} 
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
////		InputStream in = part.getInputStream();
////		
////		photo = new byte[in.available()];
////		if(in.available()!=0) {
////		in.read(photo); 
////		in.close();		
////		}else {
////			errorMsgs.add("請上傳照片");
////			
////		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
//		RequestDispatcher requestDispatcher;
//		RequestDispatcher failurePage;
	///////////////////////////////////////////////////////////////////////
////參考241 insertgroup 
	if ("INSERT".equals(action)) { // 來自addBrod.jsp的請求 ok

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

//		String memID =req.getParameter("memID").trim();//注意:正是從session 抓下來
		
//		String	message = new String(req.getParameter("message").trim()); //訊息做字串處理
//			if (message == null || message.trim().length() == 0) {
//				errorMsgs.add("訊息內容: 請勿空白");
//			}
		String memID = "M003" ;//--
		String driverID = "D003";//--
		//String driverID=req.getParameter("memID").trim();//注意:正是從session 抓下來
		
		String plateNum = "ABC-1234";	
		
		/////////////////////////////////
		///////////////////////////區域變數給初始值
		byte[] licence = null;
		byte[] criminal = null;
		byte[] trafficRecord = null;
		byte[] idNum = null;
		byte[] photo = null;
//		Part parts = req.getPart("licence");
		Collection<Part> parts = req.getParts();
//		System.out.println(part);
//		long size = part.getSize();
//		System.out.println(size);
		for (Part part : parts) {
		if (getFileNameFromPart(part) != null && part.getContentType()!=null) {
			part.getName();
//			long size = part.getSize();//update用到
			// 額外測試 InputStream 與 byte[] (幫將來model的VO預作準備)
			InputStream 	in = part.getInputStream();
			switch(part.getName()) {
			case "licence":
				licence = new byte[in.available()];			
				in.read(licence);
				break;
			case "criminal":
				criminal = new byte[in.available()];
				in.read(criminal);//讀進去陣列中
				break;	
			case "trafficRecord":				
				trafficRecord = new byte[in.available()];					
				in.read(trafficRecord);
				break;	
			case "idNum":				
				idNum = new byte[in.available()];	
				in.read(idNum);
				break;					
			case "photo":				
				photo = new byte[in.available()];					
				in.read(photo);//inputstream獨到byte[] ，service存到資料庫 
				break;					
					}
//			if(in.available()==0) { //每行會跑 共5次驗證 /////錯誤驗證圖片
//				errorMsgs.add("請上傳圖片");				
//			}
			in.close();	
			;
		}
	}
//		轉成byte[]; 先read進來 write出去
		Integer verified= 0;//--
		Integer banned= 0;//--
		Date deadline = null;//--
		Integer onlineCar= 0;//--沒在線上
		Integer score= 60;//--
//		photo = new byte[in.available()];
//		if(in.available()!=0) {
//			in.read(photo); 
//			in.close();		
//		}else {
//			errorMsgs.add("請上傳照片");
//		}
////////////////////////////////////////////照片
		
		String carType= " ";
		carType = new String(req.getParameter("carType").trim()); //訊息做字串處理
		
		if (carType == null || carType.trim().length() == 0) {
			errorMsgs.add("訊息內容: 請勿空白");
		}
		//////////////
		Integer sharedCar = 0;
		Integer pet= 0;
		Integer smoke= 0;
		Integer babySeat= 0;
		sharedCar = new Integer(req.getParameter("sharedCar"));
		pet = new Integer(req.getParameter("pet"));
		smoke = new Integer(req.getParameter("smoke"));
		babySeat =new Integer(req.getParameter("babySeat"));
		/////////////////
		DriverVO driverVO = new DriverVO();
		driverVO.setMemID(memID);
		driverVO.setDriverID(driverID);//
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
//		dao.insert(driverVO;ㄥ

		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("driverVO", driverVO); // // 含有輸入格式錯誤的empVO物件,也存入req
			RequestDispatcher failureView = req.getRequestDispatcher("/driver/addDriver.jsp");
			failureView.forward(req, res);
			return;
		}
		
		/********************* *2.開始新增資料*****************************************/
		/* 從addDriver.jsp取得的資料，透過DriverService操作DAO存進資料庫 */
		DriverService driverSvc ; // 以VO物件傳送參數
		driverSvc = new DriverService();
		driverVO = driverSvc.addDriver(memID, driverID, plateNum,
				licence, criminal, trafficRecord, idNum, photo, 
				verified, banned, deadline, onlineCar, score, carType, sharedCar, pet, smoke, babySeat);

		/***************************
		 **3.新增完成,準備轉交(Send the Success view)* Success view)
		 ***********/
		req.setAttribute("driverVO", driverVO);
		String url = "/driver/listOneDriver.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllBrod.jsp
		successView.forward(req, res);

		/***************************其他可能的錯誤處理*****************************/
		} 
//    catch (Exception e) {
//			errorMsgs.add(e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/broadcast/addBrod.jsp");
//			failureView.forward(req, res);
//		}
	}
///////////////////////////////////////////////////////////	
	//old insert
//		if ("INSERT".equals(action)) { // 來自value=INSERT
//			try {
//				memID = req.getParameter("memID");
//				if (memID == null || memID.trim().length() == 0) {
//					errorMsgs.add("會員編號未填寫");
//				}
//				driverID = req.getParameter("driverID");
//				plateNum = req.getParameter("plateNum");
////				licence = req.getParameter("licence");
////				criminal = req.getParameter("criminal");
////				trafficRecord = req.getParameter("trafficRecord");
////				idNum = req.getParameter("idNum");
////				photo = req.getParameter("photo");
//				verified = new Integer(req.getParameter("verified"));
//				banned = new Integer(req.getParameter("banned"));
////				deadline = req.getParameter("deadline");
//				onlineCar = new Integer(req.getParameter("onlineCar"));
//				score = new Integer(req.getParameter("score"));
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("driverVO", driverVO);
//					failurePage = req.getRequestDispatcher("/driver/addDriver.jsp");
//					return;
//				}
//
//				/************** step2.開始新增資料 *****************/

//				if (addedDriverVO == null) {
//					errorMsgs.add("無法新增至DB");
//				}
//				/**************step3.開始新增完成，轉交ListAllDriver頁面*****************/
//				String url="/driver/listAllDriver.jsp";
//				RequestDispatcher successPage =req.getRequestDispatcher(url);
//				successPage.forward(req, res);
//			} catch (Exception e) {
//				/**************step4.處理其他可能的錯誤，轉交addDriver頁面*****************/
//				e.printStackTrace();
//				errorMsgs.add("無法取得修改資料"+e.getMessage());
//			}
//			failurePage=req.getRequestDispatcher("/driver/addDriver.jsp");
//			failurePage.forward(req, res);
//		}
//			
//	//////////////////////////////////////////////////
//		
//		//來自homeDriver.jsp的請求
//
//		if("GET_ONE".equals(action)){
//			LinkedList<String> errorMsgs=new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			try {
//			/*************1.接收請求參數**************/
//			driverID=req.getParameter("driverID");
//						
//			/*************2查詢資料**************/
//			DriverService driverSvc=new DriverService();
//			DriverVO driverVO=driverSvc.getOneDriver(driverID);
//			if(driverVO==null) {
//				errorMsgs.add("查無此筆");
//				failurePage=req.getRequestDispatcher("/driver/listAllDriver.jsp");
//				failurePage.forward(req, res);
//				return;
//			}
//			/*************3.圖片資料處理**************/
//			
//			/*************4.得到資料存在scope=request，並送出VO給處理頁面**************/
//			//req.setAttribute("path", path);
//			req.setAttribute("driverVO", driverVO);
////			readPicture(activityVO.getActivityPost(),"ActivityPost.jpg"); //把海報存在某名稱
//			String url="/driver/listOneDriver.jsp";
//			RequestDispatcher successPage=req.getRequestDispatcher(url);
//			successPage.forward(req, res);
//			/*************5.處理例外**************/
//		}catch(Exception e){
//			errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
//			}
//			failurePage=req.getRequestDispatcher("/driver/listAllDriver.jsp");
//			failurePage.forward(req, res);
//		}
////		.equals(action)
////		doGet(req, res);
//	}
////////////////////////
//	update
//	Date deadline = null;//被ban的時間
//	driverVO.setDeadline(deadline);//修改時使用
	
/////////////////	
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
			String saveFile="/driver/img_saveFromDB"; /*指定儲存位置的資料夾名稱*/
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

