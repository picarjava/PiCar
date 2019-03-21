package Timer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupBand.model.GroupBandService;
import com.groupBand.model.GroupBandVO;
import com.groupOrder.model.GroupOrderService;

public class DelayTimer extends HttpServlet{
    Timer timer=new Timer();
    long renewTime;//當日晚間10點更新
	boolean isRenew= false; //司機評價一天只更新一次即可， 效能較佳

	
	private static final long serialVersionUID = 1L;
	int count = 0;      
	String strDate;
	
	public void init() {
	     timer = new Timer();
	        Calendar cal = new GregorianCalendar(2011, Calendar.MARCH, 5, 0, 0, 0); 
//	        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
//        	Date date = new Date();
//        	String strDate = sdFormat.format(date);
	        Calendar calendar2 = Calendar.getInstance();
	        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        calendar2.add(Calendar.DATE, 3);
	        String three_days_after = sdf2.format(calendar2.getTime());
	        String START_TIME = three_days_after +" 00:00:00";
	        
	        String START_TIME_End = three_days_after +" 23:59:59";
	        TimerTask task = new TimerTask(){
	        	
	        	
//	        	main
//	    		GregorianCalendar gc= new GregorianCalendar();
//	    		java.util.Date date =gc.getTime(); //拿到目前時間
//	    		System.out.println("date:"+date);
//	    		SimpleDateFormat tFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //格式化成資料庫出來的形式
//	    		String strDate=tFormat.format(date); //格式化成資料庫出來的形式
//	    		System.out.println("strDate:"+strDate); 	
	                   
	            public void run(){
	            	
	            
	            	GroupOrderService groupOrderService = new GroupOrderService();
	            	GroupBandService groupBandService =new GroupBandService();
	            	
	            	
	            	//--取得那些揪團--
	            	List<String> groupIDList =new ArrayList<String>();
//	            	System.out.println("有獨到這一行");
	            	groupIDList = groupOrderService.get_group_id__start_time(START_TIME,START_TIME_End);
	            	
	            	int countmemid;
	            	GroupBandVO groupBandVO;
//	            	判斷是否有到期限的單
	            	if(groupIDList!=null) {
	            		//迴圈把所有單拿出來比對
	            	for(String groupID:groupIDList) {
	            		countmemid = groupOrderService.getMemID_groupID_startTime(groupID,START_TIME,START_TIME_End);
	            		groupBandVO = groupBandService.getOneGroupBand(groupID);
	            		
	            		if(countmemid>=groupBandVO.getLowerLimit()) {
	            			//改揪團狀態碼為已成團
	            			groupBandService.UPDATE_GROUP_STATUS__GROUP_ID(1, groupID);
	            			
	            			//改訂單為已成團
	            			groupOrderService.UPDATE_STATE__GROUP_ID(3,groupID);
	            		}else
	            		{
	            			
	            			//改揪團狀態碼為已成團
	            			groupBandService.UPDATE_GROUP_STATUS__GROUP_ID(2, groupID);
	            			
	            			//改訂單為已成團
	            			groupOrderService.UPDATE_STATE__GROUP_ID(8,groupID);
	            		}
	            		
	            		
//	            		System.out.println(groupID+"人數"+countmemid);
//	            		System.out.println(groupID+"比較人數"+groupBandVO.getLowerLimit());
	            	}
	            	}
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
				
	        timer.schedule(task, 24*60*60*1000); 
//	        timer.schedule(task, three_days_after); 
//	        24*60*60*1000
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public  DelayTimer() {
        super();
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
	
	//傳入當日欲更新的hour
	public long getRenewTime(int renewHour){
		//取得現在時間
		Date date =new java.util.Date();
		SimpleDateFormat tFormat=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		String strDate=tFormat.format(date);
				
		//取得當日的日年月日
		String stryear=strDate.substring(0, 4);
		String strmonth=strDate.substring(5, 7);
		String strday=strDate.substring(8, 10);
						
		int year=Integer.parseInt(stryear);
		int month=Integer.parseInt(strmonth);
		int day=Integer.parseInt(strday);
		
		GregorianCalendar gc=new GregorianCalendar(2019, 2, 13, renewHour, 00);
		GregorianCalendar gc1=new GregorianCalendar(2019, 2, 13);
		long hour=gc.getTime().getTime()-gc1.getTime().getTime();
		long today=new GregorianCalendar(year,month-1,day).getTime().getTime();
		renewTime=today+hour; //當日晚間10點的long
		return renewTime;
	}



}
