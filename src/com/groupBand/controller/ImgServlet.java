package com.groupBand.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.groupBand.model.*;
/**
 * Servlet implementation class ImgServlet
 */
//@WebServlet("/ImgServlet")
public class ImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
	
		String groupID =req.getParameter("groupID");
		
		GroupBandService groupBandService = new GroupBandService();
		GroupBandVO groupBandVO=(GroupBandVO) groupBandService.getOneGroupBand(groupID);
		System.out.println(groupID);
		
		byte[] pic =groupBandVO.getPhoto();
		if(pic !=null) {
			out.write(pic);
		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}	

}
