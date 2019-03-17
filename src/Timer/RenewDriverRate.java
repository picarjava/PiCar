package Timer;

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

public class RenewDriverRate extends HttpServlet {
	Timer timer;
	String strDate1;
	int count;
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config) throws ServletException  {
		
		timer=new Timer();    
		SimpleDateFormat tFormat=new SimpleDateFormat("yyyy/MM/dd a hh:mm:ss ");
		TimerTask task=new TimerTask(){
				 //計TimerTask被執行的次數
				 public void run(){
				//取得指定執行時間的Date物件 
				 Date date =new Date(this.scheduledExecutionTime());
				 strDate1=tFormat.format(date);
				 count++;
				 System.out.println("=======系統於"+strDate1+" 做第"+count+"次更新=======");
					 
				 HashSet<String> ratedDriver=new HashSet<String>();
				 
				 
				 SingleOrderService singleOrderSvc= new SingleOrderService();
			     GroupOrderService groupOrderSvc= new GroupOrderService();
			     
			     Iterator<String> iteratorSingle=singleOrderSvc.getRatedDrivers().iterator();
			     System.out.println( "singleOrderSvc.getRatedDrivers()拿到那些司機:"+singleOrderSvc.getRatedDrivers().toString() );
			     System.out.println("iteratorSingle.hasNext()"+iteratorSingle.hasNext());
			     if(iteratorSingle.hasNext()) {
					 ratedDriver.add(iteratorSingle.next());//把預約訂單中已評價的司機加入set，以實現不重複入加入
				}
				 
			    
				 //等增銓新增
//				 Iterator<String> iteratorGroup=groupOrderSvc.getRatedDrivers().iterator();
//				 if(iteratorSingle.hasNext()) {
//					 ratedDriver.add(iteratorSingle.next());//把揪團訂單中已評價的司機加入set，以實現不重複入加入
//				 }
				 
				 //開始更新司機評價
				 int countDriver=0;
				 Iterator<String> iteratorAll=((HashSet<String>)ratedDriver).iterator();
				 if(iteratorAll.hasNext()) {
				 countDriver++;
			     DriverService driverSvc=new DriverService();
				 String driverID =iteratorAll.next();
				 int singleRateAve=singleOrderSvc.findRateAveByDriverID(driverID);
				 int groupRateAve=groupOrderSvc.getOneDriversAve(driverID);
				 int rateAve=(int)(singleRateAve+groupRateAve)/2;
				 driverSvc.updateDriverRate(rateAve, driverID);
				 System.out.println("將"+driverID+"的評價更新為:" +rateAve+ "分");
				 }else {
				 System.out.println("目前無人給予評價司機");	 
				 }//if
				 
				 System.out.println("本次總共更新"+countDriver+"位司機的評價");
			  
 		       }//run
			};//TimerTask
			
			timer.scheduleAtFixedRate(task,getStartDate(0),1000*60*60);
			System.out.println("司機評價更新排程器於"+ tFormat.format(getStartDate(0))+"開始每小時執行一次");
		}//init
	
	public void destroy() {
		timer.cancel();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		doPost(req,res);
	}
	
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
    	 
    	 
	}
	 
	//傳入當日欲更新的hour ，得到當天當時的long 
	public Date getStartDate(int renewHour){
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

		Date renewTime=new GregorianCalendar(year,month-1,day,renewHour,0).getTime();
		return renewTime;
	  }
	
}

