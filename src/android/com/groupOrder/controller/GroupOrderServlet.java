package android.com.groupOrder.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.groupOrder.model.GroupOrderService;
import com.groupOrder.model.GroupOrderVO;

import android.com.groupOrder.model.GroupOrder;

public class GroupOrderServlet extends HttpServlet {
    private final static int ESTABLISHED = 1;
    private final static int ONE_TIME_GROUP_RESERVE = 5;
    private final static int LONG_TERM_GROUP_RESERVE = 6;
    private final static String DRIVER_ID = "driverID";
    private final static String GROUP_ID = "groupID";
    private final static String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm";
    
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
        GroupOrderService service = new GroupOrderService();
        Gson gson = new Gson();
        JsonObject jsonIn = gson.fromJson(sBuilder.toString(), JsonObject.class);
        String action = jsonIn.get("action").getAsString();
        if ("getGroupOrder".equals(action)) {
            List<GroupOrderVO> list = service.getByStateAndOrderType(ESTABLISHED, ONE_TIME_GROUP_RESERVE)
                                             .stream().filter(g -> g.getDriverID() == null)
                                             .collect(Collectors.toList());
            gson = new GsonBuilder().setDateFormat(TIMESTAMP_PATTERN).create();
            writer.print(gson.toJson(convertToGroupOrderList(list)));
            System.out.println(gson.toJson(convertToGroupOrderList(list)));
            writer.close();
        } else if ("getLongTermGroupOrder".equals(action)) {
            List<GroupOrderVO> list = service.getByStateAndOrderType(ESTABLISHED, LONG_TERM_GROUP_RESERVE)
                                             .stream().filter(g -> g.getDriverID() == null)
                                             .collect(Collectors.toList());
            gson = new GsonBuilder().setDateFormat(TIMESTAMP_PATTERN).create();
            writer.print(gson.toJson(convertToGroupOrderList(list)));
            System.out.println(gson.toJson(convertToGroupOrderList(list)));
            writer.close();
        } else if ("takeGroupOrder".equals(action)) {
            String driverID = jsonIn.get(DRIVER_ID).getAsString();
            String groupID = jsonIn.get(GROUP_ID).getAsString();
            System.out.println(driverID + " " + groupID);
            if (driverID != null)
                service.updateDriverIDByGroupID(driverID, groupID);
        } else if ("getScheduledOrder".equals(action)) {
            String driverID = jsonIn.get(DRIVER_ID).getAsString();
            Map<Integer, List<GroupOrderVO>> map = service.getByStateAndDriverID(ESTABLISHED, driverID)
                                                          .stream()
                                                          .collect(Collectors.groupingBy(GroupOrderVO::getOrderType));
            List<GroupOrder> groupOrders = null;
            List<GroupOrder> longTermGroupOrders = null;
            if (map.containsKey(ONE_TIME_GROUP_RESERVE))
                groupOrders = convertToGroupOrderList(map.get(ONE_TIME_GROUP_RESERVE)
                                                         .stream()
                                                         .collect(Collectors.toList()));
            if (map.containsKey(LONG_TERM_GROUP_RESERVE))
                longTermGroupOrders = convertToGroupOrderList(map.get(LONG_TERM_GROUP_RESERVE)
                                                                 .stream()
                                                                 .collect(Collectors.toList()));
            gson = new GsonBuilder().setDateFormat(TIMESTAMP_PATTERN).create();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("groupOrder", gson.toJson(groupOrders));
            jsonObject.addProperty("longTermGroupOrder", gson.toJson(longTermGroupOrders));
            writer.print(jsonObject.toString());
        } else if ("getOffPiCar".equals(action)) {
            String driverID = jsonIn.get(DRIVER_ID).getAsString();
            String groupID = jsonIn.get(GROUP_ID).getAsString();
            if (jsonIn.has("startTime")) {
                
            } else {
                
            }
        }
    }
    
    private List<GroupOrder> convertToGroupOrderList(List<GroupOrderVO> groupOrderVOs) {
        Collection<List<GroupOrderVO>> list =  groupOrderVOs.stream()
                                                            .collect(Collectors.groupingBy(GroupOrderVO::getGroupID))
                                                            .values();
        return list.stream()
                   .map(l -> convertToGroupOrder(l))
                   .collect(Collectors.toList());
    }
    
    private GroupOrder convertToGroupOrder(List<GroupOrderVO> groupOrderVOs) {
        List<GroupOrderVO> list = groupOrderVOs.stream()
                                               .sorted(Comparator.comparing(GroupOrderVO::getStartTime))
                                               .collect(Collectors.toList());
        int amount = groupOrderVOs.stream()
                                  .mapToInt(GroupOrderVO::getTotalAmout)
                                  .sum();
        int people = (int) groupOrderVOs.stream()
                                        .map(GroupOrderVO::getMemID)
                                        .distinct()
                                        .count();
        GroupOrderVO groupOrderVO = list.get(0);
        GroupOrder groupOrder = new GroupOrder();
        groupOrder.setGroupID(groupOrderVO.getGroupID());
        groupOrder.setStartLoc(groupOrderVO.getStartLoc());
        groupOrder.setStartLat(groupOrderVO.getStartLat());
        groupOrder.setStartLng(groupOrderVO.getStartLng());
        groupOrder.setEndLoc(groupOrderVO.getEndLoc());
        groupOrder.setEndLat(groupOrderVO.getEndLat());
        groupOrder.setEndLng(groupOrderVO.getEndLng());
        groupOrder.setStartTime(groupOrderVO.getStartTime());
        // for common use in long-term group order
        groupOrder.setEndTime(groupOrderVOs.get(groupOrderVOs.size() - 1).getStartTime());
        groupOrder.setTotalAmount(amount);
        groupOrder.setPeople(people);
        groupOrder.setNote(groupOrderVO.getNote());
        return groupOrder;
    }
}
