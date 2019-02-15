package com.singleOrder.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.location.model.LocationService;
import com.singleOrder.model.SingleOrderDAO;
import com.singleOrder.model.SingleOrderService;

public class SingleOrderServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    } // doGet()

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        RequestDispatcher requestDispatcher;
        String forwordURL;
        List<String> errorMsgs = new LinkedList<>();
        String orderID = req.getParameter("orderID");
        if (orderID == null || !orderID.matches("^\\d+$"))
            errorMsgs.add("請輸入數字");

        SingleOrderService serivce = new SingleOrderService();
//        writer.append(memId + " " + location);
        if ("select".equals(req.getParameter("action"))) {
            if (errorMsgs.isEmpty()) {
                req.setAttribute("singleOrder", serivce.getOneSingleOrder(orderID));
                requestDispatcher = req.getRequestDispatcher("/front-end/singleOrder/selectSingleOrder.jsp");
                requestDispatcher.forward(req, resp);
                return;
            } // if
        }
        
        req.setAttribute("errorMsgs", errorMsgs);
        forwordURL = "/front-end/singleOrder/testSingleOrder.jsp";
        requestDispatcher = req.getRequestDispatcher(forwordURL);
        requestDispatcher.forward(req, resp);
    } // doPost()
} // class SingleOrderServlet
