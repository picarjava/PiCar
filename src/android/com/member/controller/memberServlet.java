package android.com.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import android.com.member.model.MemberVO;

import android.com.member.model.MemberService;

public class memberServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        BufferedReader bufferedReader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        
        bufferedReader.close();
        System.out.println(stringBuilder.toString());
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        JsonObject jsonIn = gson.fromJson(stringBuilder.toString(), JsonObject.class);
        if (jsonIn != null && jsonIn.has("action")) {
            MemberService serivce = new MemberService();
            String action = jsonIn.get("action").getAsString();
            if ("login".equals(action)) {
                String account = null;
                String password = null;
                if (jsonIn.has("account"))
                    account = jsonIn.get("account").getAsString();
                if (jsonIn.has("password"))
                    password = jsonIn.get("password").getAsString();
                
                gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                MemberVO memberVO = MemberVO.castToAndroidMemberVO(serivce.getOneByEmailAndPassword(account, password));
                if (memberVO != null) {
                    jsonObject.addProperty("auth", "OK");
                    jsonObject.addProperty("member", gson.toJson(memberVO));
                } else
                    jsonObject.addProperty("auth", "Failed");
                
                writer.print(jsonObject);
                writer.close();
            } else if ("getPicture".equals(action)) {
                String memID = jsonIn.get("memID").getAsString();
                byte[] picture = serivce.getPictureByMemID(memID);
                if (picture != null) {
                    String encodePicture = Base64.getEncoder().encodeToString(picture);
                    System.out.println(encodePicture);
                    writer.print(encodePicture);
                    writer.close();
                }
            } else if ("updatePreference".equals(action)) {
                int pet = jsonIn.get("pet").getAsInt();
                int smoke = jsonIn.get("smoke").getAsInt();
                int babySeat = jsonIn.get("babySeat").getAsInt();
                String memID = jsonIn.get("memID").getAsString();
                serivce.updatePrefenceByMemID(pet, smoke, babySeat, memID);
            } else if ("updateCreditCard".equals(action)) {
                String creditCard = jsonIn.get("creditCard").getAsString();
                String memID = jsonIn.get("memID").getAsString();
                if (creditCard.matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}")) {
                    serivce.updateCreditCardByMemID(creditCard, memID);
                }
            }
        } else {
            jsonObject.addProperty("state", "failed");
            writer.print(jsonObject.toString());
            writer.close();
        }
    }
}
