package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class OrderTypeMap extends HttpServlet {
    public final static Map<String, String> ORDER_TYPE = new HashMap<>();
    @Override
    public void init() throws ServletException {
        ORDER_TYPE.put("0", "一般叫車");
        ORDER_TYPE.put("1", "代駕");
        ORDER_TYPE.put("2", "共乘叫車");
        ORDER_TYPE.put("3", "預約叫車");
        ORDER_TYPE.put("4", "長期叫車");
        ORDER_TYPE.put("5", "揪團叫車");
        ORDER_TYPE.put("6", "長期揪團");
        ORDER_TYPE.put("7", "客服叫車");
        getServletContext().setAttribute("orderTypeMap", ORDER_TYPE);
    }
    
}
