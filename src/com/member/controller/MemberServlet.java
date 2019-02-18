package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.*;

/**
 * Servlet implementation class MemberServlet
 */

public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		PrintWriter out = res.getWriter();
		
		MemberDAO memberDAO = new MemberDAO();
//		MemberVO memberVO = memberDAO.findByPrimaryKey(action);
//		System.out.println(memberVO.getCreditcard());
//		out.println(memberVO.getName());
		
		List<MemberVO> list = memberDAO.getAll();
		for(MemberVO memberVO2 : list) {
			out.println(memberVO2.getName());
			out.println(memberVO2.getActivityToken()); 
		}
		
		
		
//		memberDAO.delete("ation");
//		out.print("delete ok");
		doGet(req, res);
	}

}
