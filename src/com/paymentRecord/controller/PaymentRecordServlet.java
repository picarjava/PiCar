package com.paymentRecord.controller;

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

/**
 * Servlet implementation class MemberServlet
 */

public class PaymentRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		PrintWriter out = res.getWriter();
		
		PaymentRecordDAO prDAO = new PaymentRecordDAO();
//		PaymentRecordVO prVO = prDAO.findByPrimaryKey("action");
//		System.out.println(prVO.getPayAmount());
//		out.println(prVO.getPayTime());
		
		List<PaymentRecordVO> list = prDAO.getAll();
		for(PaymentRecordVO prVO2 : list) {
			out.println(prVO2.getPaymentID()); 
			out.println(prVO2.getDriverID());
			out.println(prVO2.getPayAmount());
			out.println(prVO2.getPayTime());
		}
		
		
		
//		prDAO.delete("ation");
//		out.print("delete ok");
		doGet(req, res);
	}

}
