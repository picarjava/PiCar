package com.singleOrder.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
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
import com.singleOrder.model.SingleOrderVO;

public class SingleOrderServlet extends HttpServlet{
    /**
     * 
     */
    private static final long serialVersionUID = -4141756259988501128L;
    
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
            String memID = req.getParameter("memId");
            Date startTime = parseDate(req.getParameter("startTime"));
            String startLoc = req.getParameter("startLoc");
            String endLoc = req.getParameter("endLoc");
            String note = req.getParameter("note");
            int totalAmount = (int) (Math.random() * 1000) + 1;
            Integer orderType = parseInteger(req.getParameter("orderType"));
            Timestamp launchTime = new Timestamp(System.currentTimeMillis());
            if (!isValidParameter(memID, "^[Mm]\\d+$"))
                errorMsgs.add("會員ID錯誤");
            if (startTime == null)
                errorMsgs.add("上車時間錯誤");
            if (!isValidParameter(startLoc, ".+"))
                errorMsgs.add("上車地點錯誤");
            if (!isValidParameter(endLoc, ".+"))
                errorMsgs.add("下車地點錯誤");
            if (orderType == null || orderType < 0 || orderType > 7)
                errorMsgs.add("訂單種類錯誤");
            if (!isValidParameter(note, ".+"))
                errorMsgs.add("備註錯誤");
            
            if (!errorMsgs.isEmpty()) {
                forwordURL = "/front-end/singleOrder/insertSingleOrder.jsp";
                SingleOrderVO singleOrderVO = new SingleOrderVO();
                singleOrderVO.setMemID(memID);
                singleOrderVO.setStartTime(startTime);
                singleOrderVO.setStartLoc(startLoc);
                singleOrderVO.setEndLoc(endLoc);
                singleOrderVO.setOrderType(orderType);
                singleOrderVO.setNote(note);
                req.setAttribute("singleOrder", singleOrderVO);
                req.setAttribute("errorMsgs", errorMsgs);
            } else {
                serivce.addSingleOrder(memID, 0, startTime, startLoc,
                                       endLoc, 0.0, 0.0, 0.0,
                                       0.0, totalAmount, orderType, note,
                                       launchTime);
                forwordURL = "/front-end/singleOrder/listAllSingleOrder.jsp";
            } // else
        } else if ("getUpdateID".equals(action)) {
            String orderID = req.getParameter("orderID");
            if (!isValidParameter(orderID, "^\\d+$"))
                errorMsgs.add("ID錯誤");
            
            if (!errorMsgs.isEmpty()) {
                forwordURL = "/front-end/singleOrder/listAllSingleOrder.jsp";
                req.setAttribute("errorMsgs", errorMsgs);
            } else {
                SingleOrderVO singleOrderVO = serivce.getOneSingleOrder(orderID);
                req.setAttribute("singleOrder", singleOrderVO);
                forwordURL = "/front-end/singleOrder/updateSingleOrder.jsp";
            } // else
        } else if ("update".equals(action)) {
            String orderID = req.getParameter("orderID");
            String driverID = req.getParameter("driverID");
            Integer state = parseInteger(req.getParameter("state"));
            Date startTime = parseDate(req.getParameter("startTime"));
            Date endTime = parseDate(req.getParameter("endTime"));
            String startLoc = req.getParameter("startLoc");
            String endLoc = req.getParameter("endLoc");
            Integer rate = parseInteger(req.getParameter("rate"));
            if (!isValidParameter(orderID, "^\\d+$"))
                errorMsgs.add("訂單ID錯誤");
            if (!isValidParameter(driverID, "^DR\\d+$"))
                errorMsgs.add("司機ID錯誤");
            if (state == null || state < 0 || state > 9)
                errorMsgs.add("狀態錯誤");
            if (startTime == null)
                errorMsgs.add("上車時間錯誤");
            if (endTime == null)
                errorMsgs.add("下車時間錯誤");
            if (!isValidParameter(startLoc, ".+"))
                errorMsgs.add("上車地點錯誤");
            if (!isValidParameter(endLoc, ".+"))
                errorMsgs.add("下車地點錯誤");
            if (rate == null || rate < 0 || rate > 5)
                errorMsgs.add("評價錯誤");
            
            if (!errorMsgs.isEmpty()) {
                forwordURL = "/front-end/singleOrder/updateSingleOrder.jsp";
                req.setAttribute("errorMsgs", errorMsgs);
            } else {
                serivce.updateSingleOrder(orderID, driverID, state, startTime,
                                          endTime, startLoc, endLoc, 0.0,
                                          0.0, 0.0, 0.0, (int)(Math.random() * 1000 + 1),
                                          rate);
                forwordURL = "/front-end/singleOrder/listAllSingleOrder.jsp";
            }
        }
        
        if (forwordURL == null) {
            forwordURL = "/front-end/singleOrder/testSingleOrder.jsp";
            req.setAttribute("errorMsgs", errorMsgs);
        } // if
        
        requestDispatcher = req.getRequestDispatcher(forwordURL);
        requestDispatcher.forward(req, resp);
    } // doPost()
    
    private Integer parseInteger(String integer) {
        try {
            return new Integer(integer.trim());
        } catch (NumberFormatException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        } // catch
    } // parseInteger()
    
    private Date parseDate(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return new Date(simpleDateFormat.parse(time.trim()).getTime());
        } catch (ParseException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        } // catch
    } // parseDate()
    
    private boolean isValidParameter(String parameter, String regular) {
        if (parameter == null || !parameter.trim().matches(regular))
            return false;
        
        return true;
    } // isValidParameter()
} // class SingleOrderServlet
