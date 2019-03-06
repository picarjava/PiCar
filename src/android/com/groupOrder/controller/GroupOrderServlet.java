package android.com.groupOrder.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.singleOrder.model.SingleOrderService;
import com.singleOrder.model.SingleOrderVO;

public class GroupOrderServlet extends HttpServlet {
    private final static int NOT_ESTABLISHED = 0;
    private final static int ESTABLISHED = 1;
    private final static int ONE_TIME_GROUP_RESERVE = 5;
    private final static int LONG_TERM_GROUP_RESERVE = 6;
    
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
        SingleOrderService service = new SingleOrderService();
        Gson gson = new Gson();
        JsonObject jsonIn = gson.fromJson(sBuilder.toString(), JsonObject.class);
        String action = jsonIn.get("action").getAsString();
        if ("getNewGroupOrder".equals(action)) {
            List<SingleOrderVO> singleOrderVOs = service.getByStateAndOrderType(NOT_ESTABLISHED, ONE_TIME_GROUP_RESERVE);
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
            writer.print(gson.toJson(convertToGroupOrder(singleOrderVOs)));
            System.out.println(gson.toJson(convertToGroupOrder(singleOrderVOs)));
            writer.close();
        } else if ("getLongTermGroupOrder".equals(action)) {
            List<SingleOrderVO> singleOrderVOs = service.getByStateAndOrderType(NOT_ESTABLISHED, LONG_TERM_GROUP_RESERVE);
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
            writer.print(gson.toJson(convertToLongTermGroupOrder(singleOrderVOs)));
            System.out.println(gson.toJson(convertToLongTermGroupOrder(singleOrderVOs)));
            writer.close();
        } else if ("takeGroupOrder".equals(action)) {
            String driverID = jsonIn.get("driverID").getAsString();
            String orderID = jsonIn.get("orderID").getAsString();
//            if (driverID != null)
//                service.updateDriverIDAndStateByOrderID(driverID, ESTABLISHED, orderID);
        } else if ("takeLongTermGroupOrder".equals(action)) {
            String driverID = jsonIn.get("driverID").getAsString();
            List<String> orderIDs = gson.fromJson(jsonIn.get("orderID").getAsString(), new TypeToken<List<String>>() {}.getType());
//            service.updateDriverIDAndStateByOrderID(driverID, ESTABLISHED, orderIDs);
        }
    }
    
    private List<List<SingleOrderVO>> convertToGroupOrder(List<SingleOrderVO> vos) {
        List<List<SingleOrderVO>> vosLists = new ArrayList<>();
        for (SingleOrderVO singleOrderVO: vos) {
            boolean added = false;
            for (List<SingleOrderVO> voslist: vosLists) {
                SingleOrderVO first = voslist.get(0);
                if (first.getLaunchTime().equals(singleOrderVO.getLaunchTime()) &&
                    first.getStartTime().equals(singleOrderVO.getStartTime())) {
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
    
    private List<List<List<SingleOrderVO>>> convertToLongTermGroupOrder(List<SingleOrderVO> groupOrder) {
        List<List<SingleOrderVO>> groupOrders = convertToGroupOrder(groupOrder);
        List<List<List<SingleOrderVO>>> longTermOrder = new ArrayList<>();
        for (List<SingleOrderVO> gVO: groupOrders) {
            boolean added = false;
            for (List<List<SingleOrderVO>> gVOs: longTermOrder) {
                if (gVO.get(0).getLaunchTime().equals(gVOs.get(0).get(0).getLaunchTime())) {
                    gVOs.add(gVO);
                    added = true;
                } // if
            }
            
            if (!added) {
                List<List<SingleOrderVO>> gVOs = new ArrayList<>();
                gVOs.add(gVO);
                longTermOrder.add(gVOs);
            }
        }
        
        return longTermOrder;
    }
    
}
