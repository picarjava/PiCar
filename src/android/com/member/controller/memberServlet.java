package android.com.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.member.model.MemberVO;

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
        String jsonIn;
        while ((jsonIn = bufferedReader.readLine()) != null) {
            stringBuilder.append(jsonIn);
        }
        
        bufferedReader.close();
        System.out.println(stringBuilder.toString());
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(stringBuilder.toString(), JsonObject.class);
        String account = null;
        String password = null;
        if (json != null) {
            if (json.has("account"))
                account = json.get("account").getAsString();
            if (json.has("password"))
                password = json.get("password").getAsString();
        }
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        MemberService serivce = new MemberService();
        MemberVO memberVO = serivce.getOneByEmailAndPassword(account, password);
        JsonObject jsonObject = new JsonObject();
        if (memberVO != null) {
            jsonObject.addProperty("auth", "OK");
            jsonObject.addProperty("member", gson.toJson(memberVO));
        } else
            jsonObject.addProperty("auth", "Failed");
        
        writer.print(gson.toJson(jsonObject));
        writer.close();
    }
}
