package android.com.singleOrder.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.driver.model.DriverService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.member.model.MemberVO;
import com.singleOrder.model.SingleOrderService;
import com.singleOrder.model.SingleOrderVO;

import android.com.location.model.StoredInfo;

import com.driver.model.DriverVO;
import com.member.model.MemberService;
import com.calculate.controller.*;

public class SingleOrderServlet extends HttpServlet {
    // state
    private final static int NOT_ESTABLISHED = 0;
    private final static int ESTABLISHED = 1;
    private final static int EXRCUTING = 4;
    // order type
    private final static int ONE_TIME_RESERVE = 3;
    private final static int LONG_TERM_RESERVE = 4;
    private final static String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm";
    private final static String DRIVER_ID = "driverID";
    private final static String ORDER_ID = "orderID";
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        BufferedReader bufferedReader = req.getReader();
        StringBuilder sBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sBuilder.append(line);
        } // while
        
        bufferedReader.close();
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        System.out.println(sBuilder.toString());
        SingleOrderService singleOrderService = new SingleOrderService();
        Gson gson = new Gson();
        JsonObject jsonIn = gson.fromJson(sBuilder.toString(), JsonObject.class);
        String action = jsonIn.get("action").getAsString();
        if ("insert".equals(action)) {
            SingleOrderVO singleOrderVO = gson.fromJson(jsonIn.get("singleOrder").getAsString(), SingleOrderVO.class);
            singleOrderService.addSingleOrder(singleOrderVO.getMemID(), NOT_ESTABLISHED, null, singleOrderVO.getStartLoc(),
                                              singleOrderVO.getEndLoc(), singleOrderVO.getEndLng(), singleOrderVO.getStartLat(), singleOrderVO.getEndLng(),
                                              singleOrderVO.getEndLat(), 0, singleOrderVO.getOrderType(), singleOrderVO.getNote(), new Timestamp(System.currentTimeMillis()));
            
    		Double lat1 = Double.valueOf(singleOrderVO.getStartLat()); //乘客緯度 /*******
    		Double lng1 = Double.valueOf(singleOrderVO.getStartLng()); //乘客經度 /*******
            
    		List<Map.Entry<String, StoredInfo>> list = null;


    		ServletContext sc = getServletContext();
    		Map<String, StoredInfo> MatchDriver = (ConcurrentHashMap<String, StoredInfo>) sc.getAttribute("driverLocation");

    		list = new ArrayList<Map.Entry<String, StoredInfo>>(MatchDriver.entrySet());

    		Collections.sort(list, new Comparator<Map.Entry<String, StoredInfo>>() {
    		    @Override
    			public int compare(Entry<String, StoredInfo> o1, Entry<String, StoredInfo> o2) {
    				
    				Double lat3 = o1.getValue().getLatlng().getLatitude();
    				Double lon3 = o1.getValue().getLatlng().getLongitude();
    				Double lat4 = o2.getValue().getLatlng().getLatitude();
    				Double lon4 = o2.getValue().getLatlng().getLongitude();

    				Double result2 = DistanceUtil.algorithm(lng1, lat1, lon3, lat3);
    				Double result3 = DistanceUtil.algorithm(lng1, lat1, lon4, lat4);

    				return (int) (result2 - result3);
    			}

    		});

    		
    		for (Entry<String, StoredInfo> o : list) {
    			System.out.println(o.getKey());  //這是司機ID
    			System.out.println(o.getValue().getSession()); //這是司機的連線
    			
    			Double result = DistanceUtil.algorithm(lng1, lat1, o.getValue().getLatlng().getLongitude(),
    					o.getValue().getLatlng().getLatitude()); 
    			System.out.println(result); //這是計算出的距離
    		}
       		System.out.println(list.get(0).getKey()+"被抓到了");  //司機須在線上，乘客再發起訂單
    		String ChoosenDriver = null;
    		ChoosenDriver = list.get(0).getKey(); //被上帝選擇的司機
    		
            // simulate driver accept order
            DriverService driverService = new DriverService();
            MemberService memberService = new MemberService();    
            DriverVO driver = driverService.getOneDriver(list.get(0).getKey());  //*************
            
    		/***********把司機SET進資料庫******使用方法updateDriverIDAndStateByOrderID***********/
    		SingleOrderService singleOrderSvc = new SingleOrderService();   //取得資料庫最後一筆資料的OrderID		
    		Integer state = 1;
    		
    		List<SingleOrderVO> lastone = singleOrderSvc.getAll();
			String orderID = lastone.get(lastone.size()-1).getOrderID();    //取得自增主鍵的OrderID
			
			singleOrderSvc.updateDriverIDAndStateByOrderID(ChoosenDriver, state , orderID);
			System.out.println(ChoosenDriver+"被選擇的司機"); 
			System.out.println(state+"狀態碼");
			System.out.println(orderID+"訂單ID");
			
			
			SingleOrderVO SingleOrderVO1 = singleOrderSvc.getOneSingleOrder(orderID);

    		/*********轉JSON to 司機  目前乘客app會跳錯 *************/
			Gson gson1 = new Gson();
			gson1 = new GsonBuilder().setDateFormat(TIMESTAMP_PATTERN).create();
    		JsonObject json = new JsonObject();
    		json.addProperty("ORDER_ID", SingleOrderVO1.getOrderID());
    		json.addProperty("DRIVER_ID", SingleOrderVO1.getDriverID());
    		json.addProperty("MEM_ID", SingleOrderVO1.getMemID());
    		json.addProperty("STATE", SingleOrderVO1.getState());
    		json.addProperty("TOTALAMOUNT", SingleOrderVO1.getTotalAmount());
    		json.addProperty("startLoc", SingleOrderVO1.getStartLoc());
    		json.addProperty("endLoc", SingleOrderVO1.getEndLoc());
    		json.addProperty("startTime", String.valueOf(SingleOrderVO1.getStartTime()));
    		json.addProperty("endTime", String.valueOf(SingleOrderVO1.getStartTime()));
    		json.addProperty("startLng", SingleOrderVO1.getStartLng());
    		json.addProperty("startLat", SingleOrderVO1.getEndLat());
    		json.addProperty("endLng", SingleOrderVO1.getEndLng());
    		json.addProperty("endLat", SingleOrderVO1.getEndLat());
    		json.addProperty("orderType", SingleOrderVO1.getOrderType());
    		json.addProperty("rate", SingleOrderVO1.getRate());
    		json.addProperty("note", SingleOrderVO1.getNote());
    		json.addProperty("lauchTime", String.valueOf(SingleOrderVO1.getLaunchTime()));
    		
    		
//            writer.write(json.toString());
           
            System.out.println(json);
            list.get(0).getValue().getSession().getAsyncRemote().sendText(json.toString());

            /**********************************************************************************/     
 
            MemberVO memberVO = memberService.getOneMember(driver.getMemID());
            JsonObject jsonOut = new JsonObject();
            jsonOut.addProperty("driverName", memberVO.getName());
            jsonOut.addProperty("plateNum", driver.getPlateNum());
            jsonOut.addProperty("carType", driver.getCarType());
            writer.write(jsonOut.toString());
        } else if ("getNewSingleOrder".equals(action)) {
            List<SingleOrderVO> singleOrderVOs = singleOrderService.getByStateAndOrderType(ESTABLISHED, ONE_TIME_RESERVE)
                                                                   .stream()
                                                                   .filter(singleOrder -> singleOrder.getDriverID() == null)
                                                                   .collect(Collectors.toList());
            gson = new GsonBuilder().setDateFormat(TIMESTAMP_PATTERN).create();
            writer.print(gson.toJson(singleOrderVOs));
            System.out.println(gson.toJson(singleOrderVOs));
        } else if ("getLongTermSingleOrder".equals(action)) {
            List<SingleOrderVO> singleOrderVOs = singleOrderService.getByStateAndOrderType(ESTABLISHED, LONG_TERM_RESERVE)
                                                                   .stream()
                                                                   .filter(singleOrder -> singleOrder.getDriverID() == null)
                                                                   .collect(Collectors.toList());
            gson = new GsonBuilder().setDateFormat(TIMESTAMP_PATTERN).create();
            writer.print(gson.toJson(convertToLongTermOrder(singleOrderVOs)));
            System.out.println(gson.toJson(convertToLongTermOrder(singleOrderVOs)));
        } else if ("takeSingleOrder".equals(action)) {
            String driverID = jsonIn.get(DRIVER_ID).getAsString();
            String orderID = jsonIn.get(ORDER_ID).getAsString();
            if (driverID != null)
                singleOrderService.updateDriverIDAndStateByOrderID(driverID, ESTABLISHED, orderID);
        } else if ("takeLongTermOrder".equals(action)) {
            String driverID = jsonIn.get(DRIVER_ID).getAsString();
            List<String> orderIDs = gson.fromJson(jsonIn.get(ORDER_ID).getAsString(), new TypeToken<List<String>>() {}.getType());
            singleOrderService.updateDriverIDAndStateByOrderID(driverID, ESTABLISHED, orderIDs);
        } else if ("getInPiCar".equals(action)) {
            String driverID = jsonIn.get(DRIVER_ID).getAsString();
            String orderID = jsonIn.get(ORDER_ID).getAsString();
            SingleOrderVO singleOrderVO = singleOrderService.getOneSingleOrder(orderID);
            JsonObject jsonObject = new JsonObject();
            if (singleOrderVO != null && driverID != null && driverID.equals(singleOrderVO.getDriverID())) {
                singleOrderService.updateDriverIDAndStateByOrderID(driverID, EXRCUTING, orderID);
                Map<String, StoredInfo> driverLocation = (Map<String, StoredInfo>) getServletContext().getAttribute("driverLocation");
                Session session = driverLocation.get(driverID).getSession();
                if (session != null && session.isOpen()) {
                    System.out.println("send");
                    jsonObject.addProperty("state", "OK");
                    session.getAsyncRemote().sendText(jsonObject.toString());
                }
            } else {
                jsonObject = new JsonObject();
                jsonObject.addProperty("state", "Failed");
                writer.print(jsonObject.toString());
            }
        } else if ("getScheduledOrder".equals(action)) {
            String driverID = jsonIn.get(DRIVER_ID).getAsString();
            List<SingleOrderVO> list = singleOrderService.getByStateAndOrderType(ESTABLISHED, ONE_TIME_RESERVE)
                                                         .stream()
                                                         .filter(singleOrder -> driverID != null && driverID.equals(singleOrder.getDriverID()))
                                                         .collect(Collectors.toList());
            gson = new GsonBuilder().setDateFormat(TIMESTAMP_PATTERN).create();
            writer.print(gson.toJson(list));
        }
        
        writer.close();
    }
    
    private List<List<SingleOrderVO>> convertToLongTermOrder(List<SingleOrderVO> vos) {
        List<List<SingleOrderVO>> vosLists = new ArrayList<>();
        for (SingleOrderVO singleOrderVO: vos) {
            boolean added = false;
            for (List<SingleOrderVO> voslist: vosLists) {
                SingleOrderVO first = voslist.get(0);
                if (first.getLaunchTime().equals(singleOrderVO.getLaunchTime()) &&
                    first.getMemID().equals(singleOrderVO.getMemID())) {
                    voslist.add(singleOrderVO);
                    added = true;
                    break;
                }
            }
            
            if (!added) {
                List<SingleOrderVO> singleOrderVOs = new ArrayList<>();
                singleOrderVOs.add(singleOrderVO);
                vosLists.add(singleOrderVOs);
            }
        }
        
        return vosLists;
    }
}