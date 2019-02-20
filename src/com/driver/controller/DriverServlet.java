package com.driver.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.driver.model.DriverJDBCDAO;
import com.driver.model.DriverService;
import com.driver.model.DriverVO;

public class DriverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public DriverServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		RequestDispatcher requestDispatcher;
		RequestDispatcher failurePage;
		DriverService service = new DriverService(); // 以VO物件傳送參數

		String memID = null;
		String driverID;
		String plateNum = null;
		byte[] licence;
		byte[] criminal;
		byte[] trafficRecord;
		byte[] idNum;
		byte[] photo;
		Integer verified= null;
		Integer banned= null;
		Date deadline= null;
		Integer onlineCar= null;
		Integer score= null;
		String carType= null;
		Integer sharedCar = null;
		Integer pet= null;
		Integer smoke= null;
		Integer babySeat= null;

		if ("INSERT".equals(action)) { // 來自value=INSERT
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage
			// view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				driverID = req.getParameter("driverID");
				if (driverID == null || driverID.trim().length() == 0) {
					errorMsgs.add("司機編號未填寫");
				}

				
				licence = this.getBytePost(req.getParameter("licence"));
				criminal = this.getBytePost(req.getParameter("criminal"));
				trafficRecord = this.getBytePost(req.getParameter("trafficRecord"));
				idNum = this.getBytePost(req.getParameter("plateidNum"));
				photo = this.getBytePost(req.getParameter("photo"));
				

				DriverVO driverVO = new DriverVO();
				driverVO.setMemID(memID);//
				driverVO.setDriverID(driverID);//
				driverVO.setPlateNum(plateNum);
				driverVO.setLicence(licence);
				driverVO.setCriminal(criminal);
				driverVO.setTrafficRecord(trafficRecord);
				driverVO.setIdNum(idNum);
				driverVO.setPhoto(photo);
				driverVO.setVerified(verified);//
				driverVO.setBanned(banned);//
				driverVO.setDeadline(deadline);//
				driverVO.setOnlineCar(onlineCar );//
				driverVO.setScore(score );//
				driverVO.setCarType(carType);
				driverVO.setSharedCar(sharedCar);//
				driverVO.setPet(pet);//
				driverVO.setSmoke(smoke);//
				driverVO.setBabySeat(babySeat);//
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("driverVO", driverVO);
					failurePage = req.getRequestDispatcher("/driver/addDriver.jsp");
					return;
				}

				/************** step2.開始新增資料 *****************/
				/* 從addActiv.jsp取得的資料，透過ActivityService操作DAO存進資料庫 */
				DriverService driverService = new DriverService();
				DriverVO addedDriverVO = driverService.addDriver(memID, driverID, plateNum, licence, criminal,
						trafficRecord, idNum, photo, verified, banned, deadline, onlineCar, score, carType, sharedCar, pet,
						smoke, babySeat);
				if (addedDriverVO == null) {
					errorMsgs.add("無法新增至DB");
				}
				/**************step3.開始新增完成，轉交ListAllActivity頁面*****************/
				String url="/driver/listAllDriver.jsp";
				RequestDispatcher successPage =req.getRequestDispatcher(url);
				successPage.forward(req, res);
			} catch (Exception e) {
				/**************step4.處理其他可能的錯誤，轉交addActivity頁面*****************/
				e.printStackTrace();
				errorMsgs.add("無法取得修改資料"+e.getMessage());
			}
			failurePage=req.getRequestDispatcher("/driver/addActivity.jsp");
			failurePage.forward(req, res);
		}
			
			
			
			

		

//		.equals(action)
//		doGet(req, res);
	}

	/* 處理圖片存進資夾，以便以名稱顯示在網頁上 */
	public static void readPicture(byte[] bytes, String picName) throws IOException {
//		Creates a file output stream to write to the file with the specified name. 
		FileOutputStream fos = new FileOutputStream(picName);
		fos.write(bytes);
		fos.flush();
		fos.close();
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
