package com.location.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.location.model.LocationService;

public class LocationServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        req.setCharacterEncoding("utf-8");
        String memId = req.getParameter("memId");
        String location = req.getParameter("location");
        resp.setContentType("text/plain; charset=utf-8;");
        Writer writer = resp.getWriter();
//        writer.append(memId + " " + location);
        if ("select".equals(req.getParameter("action")))
            writer.append(new LocationService().getAll().toString());
        writer.flush();
        writer.close();
    }   
}
