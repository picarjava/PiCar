package com.storeRecord.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.*;
import com.paymentRecord.model.PaymentRecordDAO;
import com.paymentRecord.model.PaymentRecordVO;
import com.rate.model.RateDAO;
import com.rate.model.RateVO;
import com.storeRecord.model.StoreRecordDAO;
import com.storeRecord.model.StoreRecordVO;

/**
 * Servlet implementation class MemberServlet
 */

public class StoreRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		PrintWriter out = res.getWriter();
		
		StoreRecordDAO srDAO = new StoreRecordDAO();
//		PaymentRecordVO prVO = prDAO.findByPrimaryKey("action");
//		System.out.println(prVO.getPayAmount());
//		out.println(prVO.getPayTime());
		
		List<StoreRecordVO> list = srDAO.getAll();
		for(StoreRecordVO srVO2 : list) {
			out.println(srVO2.getRecordID()); 
			out.println(srVO2.getMemID());
			out.println(srVO2.getSaveDate());
			out.println(srVO2.getAmount());
		}
		
		
		
		srDAO.delete("ation");
		out.print("delete ok");
		doGet(req, res);
	}

}
