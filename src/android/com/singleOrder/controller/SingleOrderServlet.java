package android.com.singleOrder.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

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
import com.util.Checkout;
import com.util.CountToken;

import android.com.location.model.StoredInfo;
import android.com.singleOrder.model.LongTermOrder;
import android.com.webSocket.LocationWebSocket;
import android.com.webSocket.OrderBroadcastWebSocket;

import com.driver.model.DriverVO;
import com.member.model.MemberService;
import com.calculate.controller.*;

public class SingleOrderServlet extends HttpServlet {
    // state
    private final static int ESTABLISHED = 1;
    private final static int EXRCUTING = 4;
    private final static int FINISHED = 5;
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
            JsonObject jsonOut = new JsonObject();
            Map<String, StoredInfo> matchDriver = LocationWebSocket.getMap();
            if (matchDriver != null && !matchDriver.entrySet().isEmpty()) {
                int distance = jsonIn.get("distance").getAsInt();
                SingleOrderVO singleOrderVO = gson.fromJson(jsonIn.get("singleOrder"), SingleOrderVO.class);
        		Double lat1 = singleOrderVO.getStartLat(); //乘客緯度 /*******
        		Double lng1 = singleOrderVO.getStartLng(); //乘客經度 /*******
        		List<Map.Entry<String, StoredInfo>> list = matchDriver.entrySet()
        		                                                      .stream()
        		                                                      .filter(d -> !d.getValue().isExecuting())
        		                                                      .collect(Collectors.toList());
        		list.sort((d1, d2) -> {
        		    Double lat3 = d1.getValue().getLatlng().getLatitude();
                    Double lon3 = d1.getValue().getLatlng().getLongitude();
                    Double lat4 = d2.getValue().getLatlng().getLatitude();
                    Double lon4 = d2.getValue().getLatlng().getLongitude();
                    return (int) (DistanceUtil.algorithm(lng1, lat1, lon3, lat3) - DistanceUtil.algorithm(lng1, lat1, lon4, lat4));
                });
        		for (Entry<String, StoredInfo> o : list) {
        			System.out.println(o.getKey());  //這是司機ID
        			System.out.println(o.getValue().getSession()); //這是司機的連線
        			Double result = DistanceUtil.algorithm(lng1, lat1, o.getValue().getLatlng().getLongitude(), o.getValue().getLatlng().getLatitude()); 
        			System.out.println(result); //這是計算出的距離
        		}
        		
           		System.out.println(list.get(0).getKey()+"被抓到了");  //司機須在線上，乘客再發起訂單
        		String ChoosenDriver  = list.get(0).getKey(); //被上帝選擇的司機
                DriverService driverService = new DriverService();
                MemberService memberService = new MemberService();    
                DriverVO driver = driverService.getOneDriver(list.get(0).getKey());
                MemberVO memberVO = memberService.getOneMember(driver.getMemID());
        		/***********把司機SET進資料庫******使用方法updateDriverIDAndStateByOrderID***********/
                singleOrderVO.setState(ESTABLISHED);
                singleOrderVO.setDriverID(driver.getDriverID());
                singleOrderVO.setTotalAmount(Checkout.checkout(distance, singleOrderVO.getOrderType()));
                singleOrderVO.setLaunchTime(new Timestamp(System.currentTimeMillis()));
                String orderID = singleOrderService.addSingleOrder(singleOrderVO);
                singleOrderVO.setOrderID(orderID);
    			System.out.println(ChoosenDriver + "被選擇的司機");
    			gson = new GsonBuilder().setDateFormat(TIMESTAMP_PATTERN).create();
    			JsonObject jsonObject = new JsonObject();
                jsonObject.add("singleOrder", gson.toJsonTree(singleOrderVO));
    			list.get(0)
                    .getValue()
                    .getSession()
                    .getAsyncRemote()
                    .sendText(jsonObject.toString());
                jsonOut.addProperty("state", "Success");
                jsonOut.addProperty("driverName", memberVO.getName());
                jsonOut.addProperty("plateNum", driver.getPlateNum());
                jsonOut.addProperty("carType", driver.getCarType());
            } else
                jsonOut.addProperty("state", "Failed");
            
            System.out.println(jsonOut.toString());
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
            writer.print(gson.toJson(convertToLongTermOrderList(singleOrderVOs)));
            System.out.println(gson.toJson(convertToLongTermOrderList(singleOrderVOs)));
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
            String memID = jsonIn.get("memID").getAsString();
            SingleOrderVO singleOrderVO = singleOrderService.getOneSingleOrder(orderID);
            JsonObject jsonObject = new JsonObject();
            if (singleOrderVO != null && driverID != null && driverID.equals(singleOrderVO.getDriverID()) &&
                memID != null && memID.equals(singleOrderVO.getMemID())) {
                singleOrderService.updateDriverIDAndStateByOrderID(driverID, EXRCUTING, orderID);
                Map<String, StoredInfo> driverLocation = LocationWebSocket.getMap();
                Session session = driverLocation.get(driverID).getSession();
                if (session != null && session.isOpen()) {
                    System.out.println("send");
                    jsonObject.addProperty("state", "OK");
                    session.getAsyncRemote().sendText(jsonObject.toString());
                } else
                    jsonObject.addProperty("state", "Failed");
            } else
                jsonObject.addProperty("state", "Failed");
            
            writer.print(jsonObject.toString());
        } else if ("getScheduledOrder".equals(action)) {
            String driverID = jsonIn.get(DRIVER_ID).getAsString();
            Map<Integer, List<SingleOrderVO>> map = singleOrderService.getByStateAndDriverID(ESTABLISHED, driverID)
                                                                      .stream()
                                                                      .collect(Collectors.groupingBy(SingleOrderVO::getOrderType));
            List<SingleOrderVO> singleOrderVOs = map.get(ONE_TIME_RESERVE);
            List<LongTermOrder> longTermOrders = null;
            if (map.containsKey(LONG_TERM_RESERVE))
                longTermOrders = convertToLongTermOrderList(map.get(LONG_TERM_RESERVE)
                                                               .stream()
                                                               .collect(Collectors.toList()));
            gson = new GsonBuilder().setDateFormat(TIMESTAMP_PATTERN).create();
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("singleOrder", gson.toJsonTree(singleOrderVOs));
            jsonObject.add("longTermOrder", gson.toJsonTree(longTermOrders));
            writer.write(jsonObject.toString());
            System.out.println(jsonObject);
        } else if ("getOffPiCar".equals(action)) {
            String memID = jsonIn.get("memID").getAsString();
            String orderID = jsonIn.get(ORDER_ID).getAsString();
            if (jsonIn.has("singleOrder")) {
                SingleOrderVO singleOrderVO = singleOrderService.getOneSingleOrder(orderID);
                int amount = singleOrderVO.getTotalAmount();
                try {
                    new CountToken().countToken(memID, amount, orderID);
                    Map<String, Session> map = OrderBroadcastWebSocket.getMap();
                    if(map != null) {
                        Session session = map.get(memID);
                        if (session != null && session.isOpen()) {
                            JsonObject jsonObject = new JsonObject();
                            jsonObject.addProperty("state", "success");
                            System.out.println(jsonObject.toString());
                            session.getAsyncRemote().sendText(jsonObject.toString());
                        }
                    }
                } catch (Exception e) {
                    System.err.println("扣款失敗");
                }
            }
            
            singleOrderService.updateStateByOrderID(FINISHED, orderID);
        }
        
        writer.close();
    }
    
    private List<LongTermOrder> convertToLongTermOrderList(List<SingleOrderVO> vos) {
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
        
        return vosLists.stream().map(o -> convertToLongTermOrder(o)).collect(Collectors.toList());
    }
    
    private LongTermOrder convertToLongTermOrder(List<SingleOrderVO> singleOrderVOs) { 
        singleOrderVOs = singleOrderVOs.stream()
                                       .sorted(Comparator.comparing(SingleOrderVO::getStartTime))
                                       .collect(Collectors.toList());
        SingleOrderVO singleOrderVO = singleOrderVOs.get(0);
        LongTermOrder singleOrder = new LongTermOrder();
        int amount = singleOrderVOs.stream()
                                   .mapToInt(SingleOrderVO::getTotalAmount)
                                   .sum();
        singleOrder.setOrderIDs(singleOrderVOs.stream()
                                              .map(SingleOrderVO::getOrderID)
                                              .collect(Collectors.toList()));
        singleOrder.setDriverID(singleOrderVO.getDriverID());
        singleOrder.setMemID(singleOrderVO.getMemID());
        singleOrder.setStartLoc(singleOrderVO.getStartLoc());
        singleOrder.setStartLat(singleOrderVO.getStartLat());
        singleOrder.setStartLng(singleOrderVO.getStartLng());
        singleOrder.setEndLoc(singleOrderVO.getEndLoc());
        singleOrder.setEndLat(singleOrderVO.getEndLat());
        singleOrder.setEndLng(singleOrderVO.getEndLng());
        singleOrder.setStartTime(singleOrderVO.getStartTime());
        singleOrder.setEndTime(singleOrderVOs.get(singleOrderVOs.size() - 1).getStartTime());
        singleOrder.setTotalAmount(amount);
        singleOrder.setNote(singleOrderVO.getNote());
        return singleOrder;
    }
    
    
}