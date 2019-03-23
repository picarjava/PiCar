package Timer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

public class BannedTimer extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	int count = 0;   //起始為0 如果count=1 就關閉timer     
	Timer timer;
	String strDate;
	
	public void init() {
		

	}

    public BannedTimer() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	public void destroy() {
		if(count==1) {
		timer.cancel();    //count=1就關掉
		}
	}

}