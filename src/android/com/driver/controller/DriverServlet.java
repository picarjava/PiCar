package android.com.driver.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.driver.model.DriverService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.member.model.MemberVO;

import android.com.driver.model.DriverVO;
import android.com.member.model.MemberService;

public class DriverServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        BufferedReader bufferedReader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        } // while
        
        Gson gson = new Gson();
        JsonObject jsonIn = gson.fromJson(stringBuilder.toString(), JsonObject.class);
        if (jsonIn != null && jsonIn.has("action")) {
            PrintWriter writer = resp.getWriter();
            JsonObject jsonOut = new JsonObject();
            if ("login".equals(jsonIn.get("action").getAsString())) {
                String account = null;
                String password = null;
                if (jsonIn.has("account"))
                    account = jsonIn.get("account").getAsString();
                if (jsonIn.has("password"))
                    password = jsonIn.get("password").getAsString();
                
                System.out.println(jsonIn);
                MemberVO memberVO = new MemberService().getOneByEmailAndPassword(account, password);
                com.driver.model.DriverVO driverVO = null;
                DriverVO vo = null;
                if (memberVO != null) {
                    DriverService driverService = new DriverService();
                    driverVO = driverService.getOneDriverBymemID(memberVO.getMemID());
                    if (driverVO != null) {
                       vo = downDriverVO(driverVO);
                       gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                       if (vo.getVerified() == 1)
                           jsonOut.addProperty("auth", "OK");
                       else
                           jsonOut.addProperty("auth", "Unverified");
                       
                       jsonOut.addProperty("driverName", memberVO.getName());
                       jsonOut.add("driver", gson.toJsonTree(vo));
                    }  else
                       jsonOut.addProperty("auth", "Failed");
                } else
                    jsonOut.addProperty("auth", "Failed");
                
                writer.print(jsonOut);
                writer.close();
            } // if
        } // if
    }
    
    public static DriverVO downDriverVO(com.driver.model.DriverVO driverVO) {
        DriverVO vo = new DriverVO();
        vo.setMemID(driverVO.getMemID());
        vo.setDriverID(driverVO.getDriverID());
        vo.setVerified(driverVO.getVerified());      
        vo.setBanned(driverVO.getBanned());
        vo.setCarType(driverVO.getCarType());
        vo.setDeadline(driverVO.getDeadline());
        vo.setOnlineCar(driverVO.getOnlineCar());
        vo.setPet(driverVO.getPet());
        vo.setBabySeat(driverVO.getBabySeat());
        vo.setSmoke(driverVO.getSmoke());
        vo.setPlateNum(driverVO.getPlateNum());
        vo.setScore(driverVO.getScore());
        vo.setSharedCar(driverVO.getSharedCar());
        return vo;
    }
}
