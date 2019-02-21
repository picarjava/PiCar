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
        String account = json.get("account").getAsString();
        String password = json.get("password").getAsString();
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        if (isValidLogin(account, password))
            writer.append(gson.toJson("Ok"));
        else
            writer.append(gson.toJson("Auth failed"));
    }
    
    private boolean isValidLogin(String account, String password) {
        if (account.equals(password))
            return true;
        
        return false;
    }
}
