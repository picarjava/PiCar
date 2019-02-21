package com.driver.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.driver.model.DriverJDBCDAO;
import com.driver.model.DriverService;
import com.driver.model.DriverVO;
@MultipartConfig
public class DriverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public DriverServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		res.setContentType("text/html;charset=UTF-8");
//		String action = req.getParameter("action");
//		RequestDispatcher requestDispatcher;
//		RequestDispatcher failurePage;
//		DriverService service = new DriverService(); // 以VO物件傳送參數
//
//		String memID ;
//		String driverID = "DR003";
//		String plateNum = "123";
////		byte[] licence;
////		byte[] criminal;
////		byte[] trafficRecord;
////		byte[] idNum;
////		byte[] photo;
//		Integer verified= 1;
//		Integer banned= 1;
////		Date deadline= null;
//		Integer onlineCar= 1;
//		Integer score= 60;
//		String carType= null;
//		Integer sharedCar = 1;
//		Integer pet= 1;
//		Integer smoke= 1;
//		Integer babySeat= 1;
////參考241 insertgroup
//		if ("INSERT".equals(action)) { // 來自value=INSERT
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to send the ErrorPage
//			// view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
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
//				carType = req.getParameter("carType");
//				sharedCar = new Integer(req.getParameter("sharedCar"));
//				pet = new Integer(req.getParameter("pet"));
//				smoke = new Integer(req.getParameter("smoke"));
//				babySeat =new Integer(req.getParameter("babySeat"));
//
//				DriverVO driverVO = new DriverVO();
//				driverVO.setMemID(memID);//
//				driverVO.setDriverID(driverID);//
//				driverVO.setPlateNum(plateNum);
////				driverVO.setLicence(licence);
////				driverVO.setCriminal(criminal);
////				driverVO.setTrafficRecord(trafficRecord);
////				driverVO.setIdNum(idNum);
////				driverVO.setPhoto(photo);
//				driverVO.setVerified(verified);//
//				driverVO.setBanned(banned);//
////				driverVO.setDeadline(deadline);//
//				driverVO.setOnlineCar(onlineCar );//
//				driverVO.setScore(score );//
//				driverVO.setCarType(carType);
//				driverVO.setSharedCar(sharedCar);//
//				driverVO.setPet(pet);//
//				driverVO.setSmoke(smoke);//
//				driverVO.setBabySeat(babySeat);//
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("driverVO", driverVO);
//					failurePage = req.getRequestDispatcher("/driver/addDriver.jsp");
//					return;
//				}
//
//				/************** step2.開始新增資料 *****************/
//				/* 從addDriver.jsp取得的資料，透過DriverService操作DAO存進資料庫 */
////				DriverService driverService = new DriverService();
////				DriverVO addedDriverVO = driverService.addDriver(memID, driverID, plateNum, verified, banned, onlineCar, score, carType, sharedCar, pet,
////						smoke, babySeat);
////				licence, criminal,	trafficRecord, idNum, photo, 
////				deadline, 
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
//			/////////////////////////////
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
//			String path1,path2,path3,path4,path5="";//給空字串 
//			if(driverVO.getLicence()!=null) {
//				path1=this.saveToGetPath(driverID,driverVO.getLicence(),req);
//			}
//			
//			path2="";//給空字串 
//			if(driverVO.getCriminal()!=null) {
//				path2=this.saveToGetPath(driverID,driverVO.getCriminal(),req);
//			}
//			path3="";//給空字串 
//			if(driverVO.getTrafficRecord()!=null) {
//				path3=this.saveToGetPath(driverID,driverVO.getTrafficRecord(),req);
//			}
//			path4="";//給空字串 
//			if(driverVO.getIdNum()!=null) {
//				path4=this.saveToGetPath(driverID,driverVO.getIdNum(),req);
//			}
//			path5="";//給空字串 
//			if(driverVO.getPhoto()!=null) {
//				path5=this.saveToGetPath(driverID,driverVO.getPhoto(),req);
//			}
//			 
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
//		
////		.equals(action)
////		doGet(req, res);
//	}

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
}

