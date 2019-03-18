package Timer;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.driver.model.DriverService;
import com.groupOrder.model.GroupOrderService;
import com.singleOrder.model.SingleOrderService;
import com.util.TimeConverter;

public class RenewDriverRateTimer extends HttpServlet {
	Timer timer;
	String strDate1;
	int count;//計TimerTask被執行的次數
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config) throws ServletException  {
		TimeConverter timeConverter=new TimeConverter();
		timer=new Timer();    
		SimpleDateFormat tFormat=new SimpleDateFormat("yyyy/MM/dd a hh:mm:ss ");
		TimerTask task=new TimerTask(){
				 
				 public void run(){
				//取得指定執行時間的Date物件 
				 Date date =new Date(this.scheduledExecutionTime());
				 strDate1=tFormat.format(date);
				 count++;
				 System.out.println("=======系統於"+strDate1+" 做第"+count+"次司機評價更新=======");
					 
				 HashSet<String> ratedDriver=new HashSet<String>();
				 
				 SingleOrderService singleOrderSvc= new SingleOrderService();
			     GroupOrderService groupOrderSvc= new GroupOrderService();
			     for(String driverID :singleOrderSvc.getRatedDrivers()) {
			    	 ratedDriver.add(driverID);//把預約訂單中已評價的司機加入set，以實現不重複入加入
			    	 System.out.println("單人訂單已評價司機名單:"+driverID);
			     }
			     
			     for(String driverID :groupOrderSvc.getRatedDrivers()) {
			    	 ratedDriver.add(driverID);//把揪團訂單中已評價的司機加入set，以實現不重複入加入
			    	 System.out.println("揪團訂單已評價司機名單:"+driverID);
			     }
			     
				 //開始更新司機評價
				 int countDriver=0;
				 if(!ratedDriver.isEmpty()) {
					 DriverService driverSvc=new DriverService();
					 for(String driverID :ratedDriver) {
						 countDriver++;
						 int singleRateAve=singleOrderSvc.findRateAveByDriverID(driverID);
						 int groupRateAve=groupOrderSvc.getOneDriversAve(driverID);
						 int rateAve=(int)(singleRateAve+groupRateAve)/2;
						 System.out.println("單人訂單分數="+singleRateAve+"揪團訂單分數="+groupRateAve+"平均分數="+rateAve);
						 driverSvc.updateDriverRate(rateAve, driverID);
						 System.out.println("將"+driverID+"的評價更新為:" +rateAve+ "分");
					 }//for
				 }else {
				 System.out.println("目前無人給予評價司機");	 
				 }//if
				 System.out.println("本次總共更新"+countDriver+"位司機的評價");
 		       }//run
			};//TimerTask
			
			timer.scheduleAtFixedRate(task,timeConverter.getThisHourToday(0),1000*60*60*24);
			System.out.println("司機評價更新排程器於"+ tFormat.format(timeConverter.getThisHourToday(0))+"開始每天執行更新一次");
		}//init
	
	public void destroy() {
		timer.cancel();
	}
}

