package Timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServlet;

import com.driver.model.DriverService;
import com.groupOrder.model.GroupOrderService;
import com.singleOrder.model.SingleOrderService;

public class RenewDriverRate extends HttpServlet {
	Timer timer;
	private static final long serialVersionUID = 1L;
		
	   
		 //司機評價一天只更新一次即可， 效能較佳
	
	public void init()  {
		timer=new Timer();    
		SimpleDateFormat tFormat=new SimpleDateFormat("yyyy/MM/dd a hh:mm:ss ");
			TimerTask task=new TimerTask(){
				
				 int count=0;//計TimerTask被執行的次數
				 public void run(){
					
				 count++;
				 //取得指定執行時間的Date物件 
				 Date date =new Date(this.scheduledExecutionTime());
				 String strDate1=tFormat.format(date);
				
				 @SuppressWarnings("unchecked")
				 HashSet<String> ratingHashSet=(HashSet<String>)getServletContext().getAttribute("ratingHashSet");
				 Iterator<String> ratingIterator=((HashSet<String>) ratingHashSet).iterator();
				 //若有司機評價，則抓出此司機的單人與揪團訂單做平均後，更新評價至司機表格
				     int countDriver=0;
				     if(ratingIterator.hasNext()) {
				     countDriver++;
					 DriverService driverSvc=new DriverService();
				     SingleOrderService singleOrderSvc= new SingleOrderService();
					 GroupOrderService groupOrderSvc= new GroupOrderService();
						 
					 String driverID=(String)ratingIterator.next();
					 
					 int singleRateAve=singleOrderSvc.findRateAveByDriverID(driverID);
					 int groupRateAve=groupOrderSvc.getOneDriversAve(driverID);
					 int rateAve=(int)(singleRateAve+groupRateAve)/2;
					 driverSvc.updateDriverRate(rateAve, driverID);
					 
					 System.out.println("系統已於" +strDate1+"做第"+count+"次更新:"+"將司機編號"+driverID+"的評價更新為:" +rateAve+ "分");
					 }else {
					 System.out.println("系統已於" +strDate1+"做第"+count+"次更新");	 
					 }//if
				     System.out.println("======本次總共更新"+countDriver+"位司機的評價=====");
			    }//run
			};//TimerTask
			timer.scheduleAtFixedRate(task,getRenewTime(0),1000*60*60);
			System.out.println("司機評價更新排成器於"+ tFormat.format(new Date(getRenewTime(0)))+"開始每小時執行一次");
		}//init
	
	public void destroy() {
		timer.cancel();
	}
	 
	//傳入當日欲更新的hour ，得到當天當時的long 
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

		long renewTime=new GregorianCalendar(year,month-1,day).getTime().getTime()+(1000*60*60*renewHour);
		return renewTime;
	  }
	
}

