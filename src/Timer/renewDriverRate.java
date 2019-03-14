package Timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import com.driver.model.DriverService;
import com.groupOrder.model.GroupOrderService;
import com.singleOrder.model.SingleOrderService;

public class renewDriverRate {
	    Timer timer=new Timer();
	    long renewTime;//當日晚間10點更新
		boolean isRenew= false; //司機評價一天只更新一次即可， 效能較佳
	
		
		public renewDriverRate(String driverID){
			if (!isRenew) {
			TimerTask task=new TimerTask(){
				//若有新增評價，當日晚間10點司機評價
				//Schedules the specified task for execution after the specified delay
				 public void run(){
				 DriverService driverSvc=new DriverService();
				 SingleOrderService singleOrderSvc= new SingleOrderService();
				 GroupOrderService groupOrderSvc= new GroupOrderService();
				 int singleRateAve=singleOrderSvc.findRateAveByDriverID(driverID);
				 //1. 等待增全 dao 和service得到揪團司機	平均
				 //2. 揪團跟單程在平均後 才能更新
	//			 driverSvc.updateDriverRate(rateAve, driverID);
	//			 System.out.println("更新日期:"+new java.util.Date()+"司機編號"+driverID+"最新評價為:"+rateAve+ "分");
				 }
				};
			this.getRenewTime(22);//當日22點更新 ，取得當天22點的Long
			timer.schedule(task,renewTime);
			isRenew=true; //呼叫此方法則改為true，讓當日的所有評價於當日10點一次更新，更新後destroy 
			}
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
