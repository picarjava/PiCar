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
import com.member.model.MemberService;
import com.member.model.MemberVO;
@MultipartConfig
//(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 5 * 50 * 1024 * 1024)
public class DriverServlet extends HttpServlet {//頝臬�撠���� 霈����� ����� pic頝�雓�how��銝�撘�
	private static final long serialVersionUID = 1L;
	public DriverServlet() {
		super();
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//	憿舐內憭撐����
req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
//		
		ServletOutputStream out = res.getOutputStream();
		String driverID =req.getParameter("driverID");//敺ession��riverID
		int pic = new Integer(req.getParameter("pic"));
		
		DriverService driverSvc = new DriverService();
	    DriverVO driverVO = driverSvc.getOneDriver(driverID);
	    System.out.println(driverID);
	    
	    if(pic == 1) {//jsp�銝��mage��
	    	byte[] licence = driverVO.getLicence();
	    	out.write(licence);//憿舐內�image src� 霈����������
	    }
	    if(pic == 2) {//jsp�銝��mage��
	    	byte[] criminal = driverVO.getCriminal();
	    	out.write(criminal);//憿舐內�image src� 霈����������
	    }
	    if(pic == 3) {//jsp�銝��mage��
	    	byte[] trafficRecord= driverVO.getTrafficRecord();
	    	out.write(trafficRecord);//憿舐內�image src� 霈����������
	    }
	    if(pic == 4) {//jsp�銝��mage��
	    	byte[] idNum = driverVO.getIdNum();
	    	out.write(idNum);//憿舐內�image src� 霈����������
	    }
	    if(pic == 5) {//jsp�銝��mage��
	    	byte[] photo = driverVO.getPhoto();
	    	out.write(photo);//憿舐內�image src� 霈����������
	    }
//		System.out.println(driverID);
//////////////
////		Collection<Part> parts = req.getParts();
////		for (Part part : parts) {
////			if (getFileNameFromPart(part) != null && part.getContentType()!=null) {
////				long size = part.getSize();
////				// 憿�葫閰� InputStream ��� byte[] (撟怠��odel��O������)
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
		// 撠���set�session
		HttpSession session = req.getSession();
		session.setAttribute("list", list);
		session.getAttribute("list");
	///////////////////////////////////////////////////////////////////////
	if ("INSERT".equals(action)) { // 靘addDriver.jsp����� ok
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
//		try {
//		/************************ 1.��隢�� - 頛詨�撘�隤方��� **********************/
//		String msgID = req.getParameter("msgID");//撠��233銵�
//		String msgID = "MSG";//撠��232銵� 瘜冽�圾��dd.jsp�isabled="disabled"
//		String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//		if (msgID == null || msgID.trim().length() == 0) {
//			errorMsgs.add("��蝺刻��: 隢蝛箇");
//		} else if (!msgID.trim().matches(enameReg)) { //// 隞乩�毀蝧迤���(閬�)銵函內撘�(regular-expression)
//			errorMsgs.add("��蝺刻��: ���銝准������摮� , 銝摨血���2�10銋�蕭");
//		}
		
//		session.getAttribute("list");
//		MemberVO memberVO = (MemberVO)session1.getAttribute("memberVO");
//		String memID = "M003" ;//--�����
		String memID =(String)(req.getParameter("memID"));//瘜冽��:甇��敺ession �����'
		DriverService drimem = new DriverService();
		DriverVO  driverd =drimem.getOneDriverBymemID(memID);
//		DriverVO driverVO  = driSrc.getOneDriverBymemID(memberVO.getMemID());
		
//	    session.setAttribute("driverVO",driverVO);
		
		if(driverd != null ) {//��閮餃��甈∪璈�
			String url = "/front-end/driver/homeDriverDataManagment.jsp";//瘥����璈�
			RequestDispatcher successView = req.getRequestDispatcher(url); // �憓����漱listOneDriver.jsp
			successView.forward(req, res);
		}
//		HttpSession session1 = req.getSession();
//		String memID = (String)(session1.getAttribute("MEM_ID"));
		
//		String driverID=req.getParameter("driverID").trim();//瘜冽��:甇��敺ession �����
		String plateNum = (String)req.getParameter("plateNum").trim();
		if (plateNum == null || plateNum.trim().length() == 0) {
			errorMsgs.add("頠��Ⅳ隢蝛箇");
		}
		///////////////////////////�����蝯血����--����
		byte[] licence = null;
		byte[] criminal = null;
		byte[] trafficRecord = null;
		byte[] idNum = null;
		byte[] photo = null;
		Collection<Part> parts = req.getParts();
		for (Part part : parts) {
			part.getName();
		if (getFileNameFromPart(part) != null && part.getContentType()!=null) {//�ㄐ�撌脩��征��
//			long size = part.getSize();//update��
//			System.out.println(size);
			// 憿�葫閰� InputStream ��� byte[] (撟怠��odel��O������)
//			InputStream in = part.getInputStream();//��憭����蝺� 銝��偌擗�
			switch(part.getName()) {
			case "licence":
				licence = new byte[part.getInputStream().available()];			
				part.getInputStream().read(licence);
				break;
			case "criminal":
				criminal = new byte[part.getInputStream().available()];
				part.getInputStream().read(criminal);//霈��脣���葉
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
				part.getInputStream().read(photo);//inputstream��byte[] 嚗ervice摮鞈�澈 
				break;					
					}
//			in.close();	
			}
		else {//瘥���� �5甈⊿��� /////�隤日�����
			switch (part.getName()) {
			case "licence":
			errorMsgs.add("隢�擏");	
				break;
			case "criminal":
			errorMsgs.add("隢�criminal");	
				break;
			case "trafficRecord":
			errorMsgs.add("隢�trafficRecord");	
				break;
			case "idNum":
			errorMsgs.add("隢�idNum");	
				break;
			case "photo":
			errorMsgs.add("隢�photo");	
				break;
//			default:
//				break;
			}
		}
	}
//		頧�yte[]; ��ead�脖�� write��
		Integer verified= 0;//--��身�����
		Integer banned= 0;//--
		Date deadline = null;//--
		Integer onlineCar= 0;//--瘝蝺�� �session��
		Integer score= 60;//--
////////////////////////////////////////////����
		String carType= " ";
		carType = new String(req.getParameter("carType").trim()); //閮���葡����
		if (carType == null || carType.trim().length() == 0) {
			errorMsgs.add("頠���摰�: 隢蝛箇");
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
		DriverVO driverVO = new DriverVO();//��迄銝�
		driverVO.setMemID(memID);
//		driverVO.setDriverID(driverID);//  1.�憓����K--> listall蝘�����--> 靽格��driverID 2.�憓蜓閬utogenerate
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
			req.setAttribute("driverVO", driverVO); // // ���撓��撘隤斤�mpVO�隞�,銋�req
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/driver/addDriver.jsp");
			failureView.forward(req, res);
			return;
		}
		/********************* *2.���憓���*****************************************/
		/* 敺ddDriver.jsp���������riverService���AO摮�脰��澈 */
		DriverService driverSvc ; // 隞冬O�隞嗅�� //鞈�澈���K
		driverSvc = new DriverService();
		driverVO = driverSvc.addDriver(memID, 
//				driverID, 
				plateNum,
				licence, criminal, trafficRecord, idNum, photo, 
				verified, banned, deadline, onlineCar, score, carType, sharedCar, pet, smoke, babySeat);
		/***************************
		 **3.�憓���,皞��漱(Send the Success view)* Success view)
		 ***********/
		//////////////////////
		req.setAttribute("driverVO", driverVO);
		String url = "/front-end/driver/listOneDriver.jsp";//��征�����irtyread
		/////////////
//		String url = "/front-end/driver/homeDriverDataManagment.jsp";
//		res.sendRedirect(url);//���404
		//////////
		RequestDispatcher successView = req.getRequestDispatcher(url); // �憓����漱listOneDriver.jsp
		successView.forward(req, res);
		/***************************�隞���隤方���*****************************/
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
//	//靘擐��(eg.�璈�蝞∠��)�����(敺ession���蝑璈���)   ok
		if("GET_ONE_FRONT".equals(action)){// 
			LinkedList<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************1.��隢��**************/
//			try {
				String driverID=req.getParameter("driverID"); //�ㄐ���session
				if (driverID == null || (driverID.trim()).length() == 0) {
					errorMsgs.add("隢撓��璈楊���");
				}
				// Send the use back to the form, if there were errors
//			//	if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/driver/driverMemberManagement.jsp");
//					failureView.forward(req, res);
//					return;//蝔�葉�
//				}
			/*************2�閰Ｚ���**************/
			DriverService driverSvc=new DriverService();
			DriverVO driverVO=driverSvc.getOneDriver(driverID);
			if(driverVO==null) {
				errorMsgs.add("��甇斤��");
				RequestDispatcher failurePage =req.getRequestDispatcher("/back-end/driver/driverMemberManagement.jsp");//銋������
				failurePage.forward(req, res);
				return;
			}
			/*************3.敺鞈��scope=request嚗蒂�VO蝯西���**************/
			req.setAttribute("driverVO", driverVO);
			String url="/front-end/driver/homeDriverDataManagment.jsp";
			RequestDispatcher successPage=req.getRequestDispatcher(url);
			successPage.forward(req, res);
			/*************4.������**************/
		}
//			catch(Exception e){
//			errorMsgs.add("�瘜���耨������:"+e.getMessage());}
//			RequestDispatcher  failurePage=req.getRequestDispatcher("/back-end/driver/driverMemberManagement.jsp");
//			failurePage.forward(req, res);
//		}	
/////////////////////////////////////////////////////
////靘homeDriver.jsp�����(敺_蝞∠����蝑璈���) ok 
		if("GET_ONE_BACK".equals(action)){// 閮餉圾
			LinkedList<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************1.��隢��**************/
//			try {
				String driverID=req.getParameter("driverID");
				if (driverID == null || (driverID.trim()).length() == 0) {
					errorMsgs.add("隢撓��璈楊���");
				}
				// Send the use back to the form, if there were errors
//			//	if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/driver/driverMemberManagement.jsp");
//					failureView.forward(req, res);
//					return;//蝔�葉�
//				}
			/*************2�閰Ｚ���**************/
			DriverService driverSvc=new DriverService();
			DriverVO driverVO=driverSvc.getOneDriver(driverID);
			if(driverVO==null) {
				errorMsgs.add("��甇斤��");
				RequestDispatcher failurePage =req.getRequestDispatcher("/back-end/driver/driverMemberManagement.jsp");
				failurePage.forward(req, res);
				return;
			}
			/*************3.敺鞈��scope=request嚗蒂�VO蝯西���**************/
			req.setAttribute("driverVO", driverVO);
			String url="/back-end/driver/listOneDriver.jsp";
			RequestDispatcher successPage=req.getRequestDispatcher(url);
			successPage.forward(req, res);
			/*************4.������**************/
		}
//			catch(Exception e){
//			errorMsgs.add("�瘜���耨������:"+e.getMessage());}
//			RequestDispatcher  failurePage=req.getRequestDispatcher("/back-end/driver/driverMemberManagement.jsp");
//			failurePage.forward(req, res);
//		}
////////////////////////
//靘back-end/listAllDriver.jsp 靽格  ban deadline verivfied==0 (敺撽�璈�  )//??
		//SELECT * FROM Driver ORDER BY VERIFIED ASC, DRIVER_ID ASC; ���撽����Ⅳ
if("GET_ONE_FOR_CHECK".equals(action)){
	try {
		System.out.println("--------------------------");
	/*************1.��隢��:���蝑暑��D**************/
	String driverID=new String(req.getParameter("driverID").trim());
	String actionS=new String(req.getParameter("actionS").trim());
	/*************2�閰Ｚ���:隤踹���蝑�o**************/
	DriverService driverSvc=new DriverService();
	DriverVO driverVO=driverSvc.getOneDriver(driverID);//敺riverPK
	/*************3.敺鞈���������scope=reqest嚗蒂�VO蝯西���:getOneUpdate��**************/
	MemberService memberService =new MemberService();
	MemberVO MemberVOs =memberService.getOneMember(driverVO.getMemID());
	req.setAttribute("MemberVOs",MemberVOs);
	req.setAttribute("driverVO", driverVO);
	if("GET_ONE_FOR_PERMIT".equals(actionS)) {
	  //撽����

	RequestDispatcher failureView = req
			.getRequestDispatcher("/back-end/driver/listOneDriver.jsp");
	failureView.forward(req, res);
	}
	if("GET_ONE_CHECK_PERMIT".equals(actionS)) {
		RequestDispatcher failureView = req
			.getRequestDispatcher("/back-end/driver/getOneUpdateActivity.jsp");
 failureView.forward(req, res);
	}
	/*************4.������:��istALL���**************/
}catch(Exception e){
}
//////////////////////////////////////////////////
//靘back-end/listAllDriver.jsp 靽格  ban deadline ==0 (敺banned�璈�  )//??
if("GET_ONE_FOR_BANNED".equals(action)){
	List<String> errorMsgs1=new LinkedList<String>();//�銝�1.頝唾��2.閮剖�EADLINE3.
	req.setAttribute("errorMsgs", errorMsgs1);
//	try {
		/*************1.��隢��:���蝑璈D**************/
		String driverID=req.getParameter("driverID");
		java.sql.Date deadline = null;//--
//		deadline
		/*************2�閰Ｚ���:隤踹���蝑�o**************/
		DriverService driverSvc=new DriverService();
		DriverVO driverVO=driverSvc.getOneDriver(driverID);//敺riverPK
		/*************3.敺鞈���������scope=reqest嚗蒂�VO蝯西���:getOneUpdate��**************/
		if(driverVO.getBanned() == 0) {
			driverSvc.updateBanned(driverID);
		}else {
		}
//		�銵��閮��&&������
		
		req.setAttribute("driverVO", driverVO);
		String url="/back-end/driver/listAllDriver.jsp";  //banned����
		RequestDispatcher successPage=req.getRequestDispatcher(url);
		successPage.forward(req, res);
		/*************4.������:��istALL���**************/
//	}catch(Exception e){
//		errorMsgs1.add("�瘜���耨������:"+e.getMessage());
//	}
	RequestDispatcher failurePage=req.getRequestDispatcher("/back-end/driver/listAllDriver.jsp");
	failurePage.forward(req, res);
}

////////////////////////////////////////////////////////////////////////////////
//	蝯血�垢update雿輻()����撖急��
//	DEADLINE DATE
//	java.sql.Date time = null;
//	try {
//		time = java.sql.Date.valueOf(req.getParameter("time").trim());		
//	} catch (IllegalArgumentException e) {
//		time = new java.sql.Date(System.currentTimeMillis());
//		errorMsgs.add("隢撓�����");
//	}
//	}
//////////////
//	update
//	Date deadline = null;//鋡剎an�����
//	driverVO.setDeadline(deadline);//靽格��蝙�
		}
	//////////////////////////////////////
//	if ("UPDATE_DRI".equals(action)) { //��垢�璈(����末閮剖���撖非AO) //?
//		List<String> errorMsgs = new LinkedList<String>();
//		// Store this set in the request scope, in case we need to
//		// send the ErrorPage view.
//		req.setAttribute("errorMsgs", errorMsgs);
//		DriverService driverService = new DriverService();
////		try {
//			/***********************1.��隢��*************************/
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
//				return; // 蝔�葉�
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
//	String driverID=req.getParameter("driverID").trim();//瘜冽��:甇��敺ession �����
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

