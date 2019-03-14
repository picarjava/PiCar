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
//@WebServlet("/GroupTimer")
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
	            	
	            	Calendar calendar2 = Calendar.getInstance();
	            	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	            	calendar2.add(Calendar.DATE, 3);
	            	String three_days_after = sdf2.format(calendar2.getTime());
	            	System.out.println(three_days_after);
	            	
//	            	--取得那些揪團--
//	            	select distinct GROUP_ID from GROUP_ORDER where START_TIME LIKE '15-MAR-19%' and STATE=0;
//
//	            	--取得目前下限 人數--
//	            	select COUNT("MEM_ID")as MEM_ID from GROUP_ORDER where GROUP_ID='G005' and START_TIME LIKE '15-MAR-19%';
//
//	            	--取的揪團人數下限 拿出後比對下限人數--
//	            	SELECT LOWER_LIMIT FROM GROUP_BAND  where GROUP_ID='G005';
//
//	            	--java比對--
//	            	--if(MEM_ID>LOWER_LIMIT)--
//
//	            	--trun 成單--
//	            	update GROUP_BAND set GROUP_STATUS=1  where GROUP_ID='G005';
//	            	update GROUP_ORDER set STATE=1  where GROUP_ID='G005';
//
//	            	--false 流單--
//	            	update GROUP_BAND set GROUP_STATUS=2  where SGROUP_ID='G005';
//	            	update GROUP_ORDER set STATE=8  where GROUP_ID='G005';

	    	
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
				
	        timer.scheduleAtFixedRate(task, today,24*60*60*1000); 
//	        
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