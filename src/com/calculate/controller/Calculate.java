package com.calculate.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

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
		String driverID = null;
		Session session = null;
		List<Map.Entry<Session, Double>> list = null;
//		List<Double> list = new ArrayList();

		if ("singleorder".equals(action)) {

			lon1 = Double.valueOf(req.getParameter("lon"));
			lat1 = Double.valueOf(req.getParameter("lat"));

		}

		ServletContext sc = getServletContext();
		Map<String, StoredInfo> driver = (ConcurrentHashMap<String, StoredInfo>) sc.getAttribute("driverLocation");

		for (Entry<String, StoredInfo> storedInfo : driver.entrySet()) {

			driverID = storedInfo.getKey();
			session = storedInfo.getValue().getSession();
			lat2 = storedInfo.getValue().getLatlng().getLatitude();
			lon2 = storedInfo.getValue().getLatlng().getLongitude();

			Double result = DistanceUtil.algorithm(lon1, lat1, lon2, lat2);

			Map<Session, Double> map = new HashMap<Session, Double>();
			map.put(session, result);

			list = new ArrayList<Map.Entry<Session, Double>>(map.entrySet());

			Collections.sort(list, new Comparator<Map.Entry<Session, Double>>() {

				@Override
				public int compare(Entry<Session, Double> o1, Entry<Session, Double> o2) {
					// TODO Auto-generated method stub
					return (int) (o2.getValue() - o1.getValue());
				}

			});

			System.out.println(storedInfo.getKey());
			System.out.println(storedInfo.getValue().getLatlng().getLatitude());
			System.out.println(storedInfo.getValue().getLatlng().getLongitude());
			System.out.println(list);
		}

		for (Entry<Session, Double> o : list) {
			System.out.println(o.getKey());
			System.out.println(o.getValue());
		}

//		Set set = driver.keySet();
//		Iterator it = set.iterator();
//		while (it.hasNext()) {
//			String driverID = (String) it.next();
//			System.out.println(driverID);
//		}

//		for (String driver1 : driver.keySet()) {
//			System.out.println(driver1);
//
//		}		

//		for (StoredInfo storedInfo : driver.values()) {
//			System.out.println(storedInfo.getLatlng().getLatitude());
//			System.out.println(storedInfo.getLatlng().getLongitude());
//			
//			
//			
//			System.out.println(storedInfo.getSession());//key
//			
//			lat2 = storedInfo.getLatlng().getLatitude();
//			lon2 = storedInfo.getLatlng().getLongitude();
//			Double result = DistanceUtil.algorithm(lon1, lat1, lon2, lat2);//value
//			
//			
//			
//
//		}
		// 1.乘客的經度 2.乘客的緯度 3.司機的經度 4.司機的緯度
//		Double result = DistanceUtil.algorithm(lon1, lat1, lon2, lat2);

//		System.out.println(result);

	}

}
