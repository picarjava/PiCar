package Timer;


import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
/**
 * Servlet implementation class GroupTimer
 */
@WebServlet("/GroupTimer")
public class GroupTimer extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	int count = 0;      
	Timer timer;
	String strDate;
	
	public void init() {
	     timer = new Timer();
	        Calendar cal = new GregorianCalendar(2011, Calendar.MARCH, 5, 0, 0, 0); 
//	        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
//        	Date date = new Date();
//        	String strDate = sdFormat.format(date);
	        TimerTask task = new TimerTask(){
	                   
	            public void run(){
	            	
	            	
	        		GregorianCalendar gc= new GregorianCalendar();
	    			java.util.Date date =gc.getTime();
	    			SimpleDateFormat tFormat=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
	    			strDate=tFormat.format(date);
	    			
	    			//取得當天的日年月日
	    					String stryear=strDate.substring(0, 4);
	    					String strmonth=strDate.substring(5, 7);
	    					String strday=strDate.substring(8, 10);
	    					
	    					int year=Integer.parseInt(stryear);
	    					int month=Integer.parseInt(strmonth);
	    					int day=Integer.parseInt(strday);
	    			 
	    			//取得當天00:00:00的GregorianCalendar物件，getTime()取得date物件
	    				GregorianCalendar gcToday=new GregorianCalendar(year, month-1, day);
	                   System.out.println(gcToday);
	                count++;
	            }
	        };
			GregorianCalendar gc= new GregorianCalendar();
			java.util.Date date =gc.getTime();
			SimpleDateFormat tFormat=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			strDate=tFormat.format(date);
			
			//取得當天的日年月日
					String stryear=strDate.substring(0, 4);
					String strmonth=strDate.substring(5, 7);
					String strday=strDate.substring(8, 10);
					
					int year=Integer.parseInt(stryear);
					int month=Integer.parseInt(strmonth);
					int day=Integer.parseInt(strday);
			 
			//取得當天00:00:00的GregorianCalendar物件，getTime()取得date物件
				GregorianCalendar gcToday=new GregorianCalendar(year, month-1, day, 0, 0, 0);
				java.util.Date today=gcToday.getTime();
	        timer.scheduleAtFixedRate(task, cal.getTime(),5000); 
//	        24*60*60*1000
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupTimer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	public void destroy() {
		
		
	}

}