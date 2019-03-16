package model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class StateMap extends HttpServlet {
    private static Map<Integer, String> STATE = new HashMap<>();

    @Override
    public void init() throws ServletException {
        STATE.put(0, "訂單待成立");
        STATE.put(1, "訂單成立");
        STATE.put(2, "乘客取消");
        STATE.put(3, "已完成");
        STATE.put(4, "執行中");
        STATE.put(5, "已完成");
        STATE.put(6, "逾期未處理");
        STATE.put(7, "客服處理完成");
        STATE.put(8, "流單");
        STATE.put(9, "退全額");
        STATE = Collections.unmodifiableMap(STATE); // let state map unmodified
        getServletContext().setAttribute("stateMap", STATE);
    }
    
}
