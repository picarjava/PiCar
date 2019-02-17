package com.rate.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.*;
import com.rate.model.RateDAO;
import com.rate.model.RateVO;

/**
 * Servlet implementation class MemberServlet
 */

public class RateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		PrintWriter out = res.getWriter();
		
		RateDAO rateDAO = new RateDAO();
//		RateVO rateVO = rateDAO.findByPrimaryKey(action);
//		System.out.println(memberVO.getCreditcard());
//		out.println(memberVO.getName());
		
		List<RateVO> list = rateDAO.getAll();
		for(RateVO rateVO2 : list) {
			out.println(rateVO2.getRateID()); 
			out.println(rateVO2.getRateBasic());			
		}
		
		
		
//		memberDAO.delete("ation");
//		out.print("delete ok");
		doGet(req, res);
	}

}
