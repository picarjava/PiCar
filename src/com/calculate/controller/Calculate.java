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
import com.singleOrder.model.SingleOrderService;
import com.singleOrder.model.SingleOrderVO;

import android.com.location.model.InputInfo;
import android.com.location.model.StoredInfo;


public class Calculate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Calculate() {
		super();
		
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		
//		Integer state = 0;
//		Integer orderType = 0;
//		String memID = req.getParameter("memID");
//		
//		SingleOrderService so = new SingleOrderService();
//		SingleOrderVO newSingleOrderVO = so.getBySingleOrder(state, orderType, memID);
		
//		Double lat1 = newSingleOrderVO.getStartLat();
//		Double lon1 = newSingleOrderVO.getStartLng();
		
		Double lat1 = Double.valueOf(req.getParameter("lat"));
		Double lon1 = Double.valueOf(req.getParameter("lon"));
		

//		String driverID = null;
//		Session session = null;

		List<Map.Entry<String, StoredInfo>> list = null;


		ServletContext sc = getServletContext();
		Map<String, StoredInfo> driver = (ConcurrentHashMap<String, StoredInfo>) sc.getAttribute("driverLocation");

		list = new ArrayList<Map.Entry<String, StoredInfo>>(driver.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, StoredInfo>>() {
			public int compare(Entry<String, StoredInfo> o1, Entry<String, StoredInfo> o2) {
				
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
		JsonObject json = new JsonObject();
		
//		String json = gson.toJson(vo);
		list.get(0).getValue().getSession().getAsyncRemote().sendText("json");

		

	}

}
