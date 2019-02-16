package com.singleOrder.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.singleOrder.model.SingleOrderService;

public class SingleOrderServlet extends HttpServlet{
    private final static String DATE_PATTERN = "^\\d{4}-\\d{2}-\\d{2}";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    } // doGet()

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        RequestDispatcher requestDispatcher;
        String forwordURL = null;
        List<String> errorMsgs = new LinkedList<>();
        String action = req.getParameter("action");
        SingleOrderService serivce = new SingleOrderService();
        if ("select".equals(action)) {
            String orderID = req.getParameter("orderID");
            if (!isValidParameter(orderID, "^\\d+$"))
                errorMsgs.add("請輸入數字");
            
            if (errorMsgs.isEmpty()) {
                req.setAttribute("singleOrder", serivce.getOneSingleOrder(orderID));
                forwordURL = "/front-end/singleOrder/selectSingleOrder.jsp";
            } // if
        } else if ("insert".equals(action)) {
            String memId = req.getParameter("memId");
            String startTime = req.getParameter("startTime");
            String startLoc = req.getParameter("startLoc");
            String endLoc = req.getParameter("endLoc");
            String orderType = req.getParameter("orderType");
            String note = req.getParameter("note");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startTimeDate = null;
            try {
                startTimeDate = new Date(simpleDateFormat.parse(startTime).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (!isValidParameter(memId, "^M\\d+$"))
                errorMsgs.add("會員ID錯誤");
            if (!isValidParameter(startTime, DATE_PATTERN))
                errorMsgs.add("上車時間錯誤");
            if (!isValidParameter(startLoc, ".+"))
                errorMsgs.add("上車地點錯誤");
            if (!isValidParameter(endLoc, ".+"))
                errorMsgs.add("下車地點錯誤");
            if (!isValidParameter(orderType, "^[0-7]$"))
                errorMsgs.add("訂單種類錯誤");
            if (!isValidParameter(note, ".+"))
                errorMsgs.add("備註錯誤");
            
            if (!errorMsgs.isEmpty())
                forwordURL = "/front-end/singleOrder/insertSingleOrder.jsp";
            req.setAttribute("errorMsgs", errorMsgs);
        }
        
        if (forwordURL == null) {
            forwordURL = "/front-end/singleOrder/testSingleOrder.jsp";
            req.setAttribute("errorMsgs", errorMsgs);
        } // if
        
        requestDispatcher = req.getRequestDispatcher(forwordURL);
        requestDispatcher.forward(req, resp);
    } // doPost()
    
    private boolean isValidParameter(String parameter, String regular) {
        if (parameter == null || !parameter.matches(regular))
            return false;
        
        return true;
    }
} // class SingleOrderServlet
