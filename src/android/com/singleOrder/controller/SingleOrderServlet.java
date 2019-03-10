package android.com.singleOrder.controller;

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

import com.driver.model.DriverService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.member.model.MemberVO;
import com.singleOrder.model.SingleOrderService;
import com.singleOrder.model.SingleOrderVO;

import android.com.driver.controller.DriverServlet;
import com.driver.model.DriverVO;
import com.member.model.MemberService;

public class SingleOrderServlet extends HttpServlet {
    private final static int NOT_ESTABLISHED = 0;
    private final static int ESTABLISHED = 1;
    private final static int ONE_TIME_RESERVE = 3;
    private final static int LONG_TERM_RESERVE = 4;
    
    
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
            singleOrderService.addSingleOrder(singleOrderVO.getMemID(), singleOrderVO.getState(), null, singleOrderVO.getStartLoc(),
                                   singleOrderVO.getEndLoc(), singleOrderVO.getEndLng(), singleOrderVO.getStartLat(), singleOrderVO.getEndLng(),
                                   singleOrderVO.getEndLat(), 0, singleOrderVO.getOrderType(), singleOrderVO.getNote(), new Timestamp(System.currentTimeMillis()));
            // simulate driver accept order
            DriverService driverService = new DriverService();
            MemberService memberService = new MemberService();
            DriverVO driver = driverService.getOneDriver("D001");
            MemberVO memberVO = memberService.getOneMember(driver.getMemID());
            JsonObject jsonOut = new JsonObject();
            jsonOut.addProperty("driverName", memberVO.getName());
            jsonOut.addProperty("plateNum", driver.getPlateNum());
            jsonOut.addProperty("carType", driver.getCarType());
            writer.write(jsonOut.toString());
        } else if ("getNewSingleOrder".equals(action)) {
            List<SingleOrderVO> singleOrderVOs = singleOrderService.getByStateAndOrderType(NOT_ESTABLISHED, ONE_TIME_RESERVE);
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
            writer.print(gson.toJson(singleOrderVOs));
            System.out.println(gson.toJson(singleOrderVOs));
        } else if ("getLongTermSingleOrder".equals(action)) {
            List<SingleOrderVO> singleOrderVOs = singleOrderService.getByStateAndOrderType(NOT_ESTABLISHED, LONG_TERM_RESERVE);
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
            writer.print(gson.toJson(convertToLongTermOrder(singleOrderVOs)));
            System.out.println(gson.toJson(convertToLongTermOrder(singleOrderVOs)));
        } else if ("takeSingleOrder".equals(action)) {
            String driverID = jsonIn.get("driverID").getAsString();
            String orderID = jsonIn.get("orderID").getAsString();
            if (driverID != null)
                singleOrderService.updateDriverIDAndStateByOrderID(driverID, ESTABLISHED, orderID);
        } else if ("takeLongTermOrder".equals(action)) {
            String driverID = jsonIn.get("driverID").getAsString();
            List<String> orderIDs = gson.fromJson(jsonIn.get("orderID").getAsString(), new TypeToken<List<String>>() {}.getType());
            singleOrderService.updateDriverIDAndStateByOrderID(driverID, ESTABLISHED, orderIDs);
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