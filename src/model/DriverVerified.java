package model;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class DriverVerified extends HttpServlet {
    public static Map<Integer, String> VERIFIED = new HashMap<>();
    @Override
    public void init() throws ServletException {
    	VERIFIED.put(0, "未驗證");
    	VERIFIED.put(1, "通過");
    	VERIFIED.put(2, "未通過");

    	VERIFIED = Collections.unmodifiableMap(VERIFIED); // let orderType map unmodified 類似final
        getServletContext().setAttribute("DriverVerifiedMap", VERIFIED);
    }
    



}
