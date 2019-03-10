package com.groupOrder.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupOrder.model.GroupOrderService;
import com.groupOrder.model.GroupOrderVO;

public class PleaseInsertIntoGroupOrderControllerThankYou {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
	
	/*小編開始*/
	//來自groupOrderRating.jsp
    if("addRate".equals(action)) {
    List<String> errorMsgs = new LinkedList<String>();
    String forwordURL ="/front-end/groupOrder/groupOrderRating.jsp";
    Integer rate=new Integer(req.getParameter("rate")) ;
    String orderID=req.getParameter("orderID");
    GroupOrderService groupOrderSvc=new GroupOrderService();
    GroupOrderVO groupOrderVO=groupOrderSvc.getOneGroupOrder(orderID);
    try {
    	groupOrderSvc.updategroupOrderVO(orderID, groupOrderVO.getDriverID(), groupOrderVO.getMemID(), groupOrderVO.getState(),groupOrderVO.getTotalAmout(), groupOrderVO.getStartTime(),groupOrderVO.getEndTime(),groupOrderVO.getStartLng(), groupOrderVO.getStartLat(), groupOrderVO.getEndLng(), groupOrderVO.getEndLat(), groupOrderVO.getOrderType(), rate, groupOrderVO.getNote());
     }catch(RuntimeException e) {
    	errorMsgs.add("無法更新至資料庫");
    }
    forwordURL ="/front-end/groupOrder/listPastGroupOrder.jsp";//成功則回歷史頁面
    RequestDispatcher requestDispatcher=req.getRequestDispatcher(forwordURL);
	requestDispatcher.forward(req, res);
    }
    
    //來自 listPastGroupOrder.jsp  passID to rating
     if("passID".equals(action)){
    	List<String> errorMsgs = new LinkedList<String>();
    	String forwordURL ="/front-end/groupOrder/listPastGroupOrder.jsp";
    	String orderID=req.getParameter("orderID");
    	if(orderID==null||orderID.trim().length()==0) {
    		errorMsgs.add("未取得此筆訂單編號");
    	}
    	if (!errorMsgs.isEmpty()) {
            req.setAttribute("errorMsgs", errorMsgs);
        }
    	GroupOrderService groupOrderSvc=new GroupOrderService();
    	try {
    		GroupOrderVO groupOrderVO=groupOrderSvc.getOneGroupOrder(orderID);
        	req.setAttribute("groupOrderVO", groupOrderVO);
    	}catch(Exception e) {
    		errorMsgs.add("無法取得orderID"+e.getMessage());
    		req.setAttribute("errorMsgs", errorMsgs);
    	}
    	forwordURL="/front-end/groupOrder/groupOrderRating.jsp"; //成功則送至rating頁面
    	RequestDispatcher requestDispatcher=req.getRequestDispatcher(forwordURL);
    	requestDispatcher.forward(req, res);
    }
	/*小編結束*/

	}
	}
