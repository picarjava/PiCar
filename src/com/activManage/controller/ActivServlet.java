package com.activManage.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.activity.model.ActivityJDBCDAO;
import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;

/**
 * Servlet implementation class activ_servlet
 */

public class ActivServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ActivityVO activityVO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setCharacterEncoding("UTF-8");
		/*取出enter_activ.jsp的資料，透過ActivityService產生VO，再透過ActivityJDBCDAO存進資料庫*/
		HttpSession session=req.getSession();
		String action =req.getParameter("action");
		 if(action.equals("CREATE")){
			 String activity_Id =(String) session.getAttribute("activity_Id");
			 String activity_Name=(String) session.getAttribute("activity_Name");
			 String activity_Info=(String) session.getAttribute("activity_Info");
			 Date activity_Start=(Date) session.getAttribute("activity_Start");
			 Date activity_End=(Date) session.getAttribute("activity_End");
			 String activity_Code=(String) session.getAttribute("activity_Code");
			 Integer token_Amount=(Integer) session.getAttribute("token_Amount");
			 byte[]activity_Post=(byte[]) session.getAttribute("activity_Post");
			 ActivityService activityService=new ActivityService();
			 ActivityJDBCDAO activityJDBCDAO=new ActivityJDBCDAO();
			 activityVO =activityService.addActivity(activity_Id,activity_Name,activity_Info,activity_Start,activity_End,activity_Code,token_Amount,activity_Post);
			 activityJDBCDAO.insert(activityVO);
		 }
			
		}
		
	
	}


