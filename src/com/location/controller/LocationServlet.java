package com.location.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.location.model.LocationService;
import com.location.model.LocationVO;

public class LocationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String action = req.getParameter("action");
        String forwardUrl = null;
        LocationService service = new LocationService();
        String memID = req.getParameter("memID");
        String location = req.getParameter("location");
        List<String> errorMsgs = new ArrayList<>();
        if ("select".equals(action)) {
            LocationVO locationVO = service.getOneLocation(memID, location);
            req.setAttribute("location", locationVO);
            forwardUrl = "/front-end/location/listOneLocation.jsp";
        } else if ("getUpdateLocation".equals(action)) {
            LocationVO locationVO = new LocationVO();
            locationVO.setMemId(memID);
            locationVO.setLocation(location);
            req.setAttribute("location", locationVO);
            forwardUrl = "/front-end/location/updateLocation.jsp";
        } else if ("update".equals(action)) {
            String newLocation = req.getParameter("newLocation");
            if (!newLocation.matches(".+"))
                errorMsgs.add("地點不為空");
            
            if (!errorMsgs.isEmpty()) {
                LocationVO locationVO = new LocationVO();
                locationVO.setMemId(memID);
                locationVO.setLocation(newLocation);
                req.setAttribute("location", locationVO);
                req.setAttribute("errorMsgs", errorMsgs);
                forwardUrl = "/front-end/location/updateLocation.jsp";
            } else {
                System.out.println(memID + " " + location + " " + newLocation);
                service.updateLocation(memID, location, newLocation);
                forwardUrl = "/front-end/location/listAllLocation.jsp";
            }
        } else if ("delete".equals(action)) {
            service.deleteLocation(memID, location);
            forwardUrl = "/front-end/location/listAllLocation.jsp";
        }
        
        if (forwardUrl == null)
            forwardUrl = "/front-end/location/testLocation.jsp";
        
        RequestDispatcher dispatcher = req.getRequestDispatcher(forwardUrl);
        dispatcher.forward(req, resp);
    }
    
}
