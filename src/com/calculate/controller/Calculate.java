package com.calculate.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import android.com.location.model.InputInfo;
import android.com.location.model.StoredInfo;

/**
 * Servlet implementation class Caculate
 */
@WebServlet("/Caculate")
public class Calculate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Calculate() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");

		Double lon1 = 0.0;
		Double lat1 = 0.0;

		Double lon2 = 0.0;
		Double lat2 = 0.0;

		if ("singleorder".equals(action)) {

			lon1 = Double.valueOf(req.getParameter("lon"));
			lat1 = Double.valueOf(req.getParameter("lat"));

			System.out.println(lon1);
			System.out.println(lat1);
		}

		ServletContext sc = getServletContext();
		Map<String, StoredInfo> driver = (ConcurrentHashMap<String, StoredInfo>) sc.getAttribute("driverLocation");

		Set set = driver.keySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			String driverID = (String) it.next();
			System.out.println(driverID);
		}
		for (StoredInfo storedInfo : driver.values()) {
			System.out.println(storedInfo.getLatlng().getLatitude());
			System.out.println(storedInfo.getLatlng().getLongitude());
			System.out.println(storedInfo.getSession());
			lat2 = storedInfo.getLatlng().getLatitude();
			lon2 = storedInfo.getLatlng().getLongitude();

		}
		// 1.乘客的經度 2.乘客的緯度 3.司機的經度 4.司機的緯度
		Double result = DistanceUtil.algorithm(lon1, lat1, lon2, lat2);

		System.out.println(result);

	}

}
