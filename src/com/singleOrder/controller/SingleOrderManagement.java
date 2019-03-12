package com.singleOrder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.singleOrder.model.SingleOrderDAO;
import com.singleOrder.model.SingleOrderVO;

/**
 * Servlet implementation class SingleOrderManagement
 */
@WebServlet("/SingleOrderManagement")
public class SingleOrderManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingleOrderManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
        }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        request.setCharacterEncoding("utf-8"); 
//        response.setContentType("text/html;charset=utf-8"); 
//        response.setCharacterEncoding("utf-8"); 
//
//        ArrayList<SingleOrderVO> list=  new SingleOrderDAO().getAllByState();
//        String json=SingleOrderDAO.dataJson(list);
//        System.out.println(json);//有資料
//        PrintWriter out = response.getWriter();
//        out.println(json);
//        out.close();
//   }

}
