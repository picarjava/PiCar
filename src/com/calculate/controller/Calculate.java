package com.calculate.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.singleOrder.model.SingleOrderVO;

import android.com.location.model.InputInfo;
import android.com.location.model.StoredInfo;

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
		Double lat1 = Double.valueOf(req.getParameter("lat"));
		Double lon1 = Double.valueOf(req.getParameter("lon"));
//		Double lat2 = 0.0;
//		Double lon2 = 0.0;

		String driverID = null;
		Session session = null;
//		Map<Session, Double> map = null;
//		Map<String, StoredInfo> map1 = null;
		List<Map.Entry<String, StoredInfo>> list = null;
//		List<Double> list = new ArrayList();

		ServletContext sc = getServletContext();
		Map<String, StoredInfo> driver = (ConcurrentHashMap<String, StoredInfo>) sc.getAttribute("driverLocation");

//		for (Entry<String, StoredInfo> storedInfo : driver.entrySet()) {

//			driverID = storedInfo.getKey();
//			session = storedInfo.getValue().getSession();
//			lat2 = storedInfo.getValue().getLatlng().getLatitude();
//			lon2 = storedInfo.getValue().getLatlng().getLongitude();
//
//			Double result = DistanceUtil.algorithm(lon1, lat1, lon2, lat2);

//			map = new HashMap<Session, Double>();
//			driver = new HashMap<String, StoredInfo>();
//			new Comparator<Map.Entry<String, StoredInfo>>() {
//				public int compare(Entry<String, StoredInfo> o1, Entry<String, StoredInfo> o2) {
//					// TODO Auto-generated method stub
//					Double lat3 = o1.getValue().getLatlng().getLatitude();
//					Double lon3 = o1.getValue().getLatlng().getLongitude();
//					Double lat4 = o2.getValue().getLatlng().getLatitude();
//					Double lon4 = o2.getValue().getLatlng().getLongitude();
//
//					Double result2 = DistanceUtil.algorithm(lon1, lat1, lon3, lat3);
//					Double result3 = DistanceUtil.algorithm(lon1, lat1, lon4, lat4);
//
//					return (int) (result2 - result3);
//				}
//			};
//			map1.put(session, result);

//			System.out.println(storedInfo.getKey());
//			System.out.println(storedInfo.getValue().getLatlng().getLatitude());
//			System.out.println(storedInfo.getValue().getLatlng().getLongitude());
//			System.out.println(list);
//		}

		list = new ArrayList<Map.Entry<String, StoredInfo>>(driver.entrySet());
//		list = new ArrayList<Map.Entry<Session, Double>>(map.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, StoredInfo>>() {
			public int compare(Entry<String, StoredInfo> o1, Entry<String, StoredInfo> o2) {
				// TODO Auto-generated method stub
				Double lat3 = o1.getValue().getLatlng().getLatitude();
				Double lon3 = o1.getValue().getLatlng().getLongitude();
				Double lat4 = o2.getValue().getLatlng().getLatitude();
				Double lon4 = o2.getValue().getLatlng().getLongitude();

				Double result2 = DistanceUtil.algorithm(lon1, lat1, lon3, lat3);
				Double result3 = DistanceUtil.algorithm(lon1, lat1, lon4, lat4);

				return (int) (result2 - result3);
			}

		});

		for (Entry<String, StoredInfo> o : list) {
			System.out.println(o.getKey());  //這是司機ID
			System.out.println(o.getValue().getSession()); //這是司機的連線
			Double result = DistanceUtil.algorithm(lon1, lat1, o.getValue().getLatlng().getLongitude(),
					o.getValue().getLatlng().getLatitude()); 
			System.out.println(result); //這是計算出的距離
		}
		
		
		Gson gson = new Gson();
//		String json = gson.toJson(vo);
		list.get(0).getValue().getSession().getAsyncRemote().sendText("json");

		

	}

}
