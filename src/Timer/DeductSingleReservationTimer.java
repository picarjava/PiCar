package Timer;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.singleOrder.model.SingleOrderService;
import com.singleOrder.model.SingleOrderVO;
import com.util.TimeConverter;



public class DeductSingleReservationTimer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Timer timer;   
    
    public DeductSingleReservationTimer() {
        super();
       
    }
	public void init(ServletConfig config) throws ServletException {
		timer=new Timer();
		
		SimpleDateFormat tFormat=new SimpleDateFormat("yyyy/MM/dd a hh:mm:ss ");
		TimeConverter timeConverter=new TimeConverter();
		Date firstime=timeConverter.getThisHourToday(0);//開始時間為伺服器啟動的當天0點
		long period=1000*60*60; //每小時執行一次
		SingleOrderService singleOrderSvc=new SingleOrderService();
		HashSet<SingleOrderVO> allUnpaid =new HashSet<SingleOrderVO>();//待付款訂單
		TimerTask task=new TimerTask(){
			int count;
			String excutedTime;
			@Override
			public void run(){
				 Date date =new Date(this.scheduledExecutionTime());
				 excutedTime=tFormat.format(date);
				 count++;
				 System.out.println("=======系統於"+excutedTime+" 做第"+count+"次預約訂單扣款=======");
				
				//1.=======撈出三日前未完成付款的訂單=======
				//將單程的部分加入待付款訂單
				 
				for(SingleOrderVO unpaidReservationOrder :singleOrderSvc.listAllUnpaidReservationOrder()) {
					allUnpaid.add(unpaidReservationOrder);
					System.out.println("待扣款的單程預約:"+ unpaidReservationOrder.getOrderID());
				}
				
				//將長期的部分加入待付款訂單 
				Timestamp checkLaunchTime=null;
				for(SingleOrderVO earlierPartUnpaidOrderFromLongtermSets :singleOrderSvc.listEarlierPartUnpaidFromLongtermSets()) {
					Timestamp thisLaunchTime=earlierPartUnpaidOrderFromLongtermSets.getLaunchTime();//檢查是否為同一批長期訂單
					if(!thisLaunchTime.equals(checkLaunchTime)) { //不同批者，則進入查整組
						 for(SingleOrderVO unpaidlongtermOrder:singleOrderSvc.listOneSetOfLongterm(thisLaunchTime)) { //得到整組長期訂單
							 allUnpaid.add(unpaidlongtermOrder);
							 System.out.println("待扣款的長期預約:"+unpaidlongtermOrder.getOrderID());
						 }; 
					 checkLaunchTime=earlierPartUnpaidOrderFromLongtermSets.getLaunchTime();
					}
				}
				
//				
				//2.=======呼叫小蔣扣款方法=======
				//3.=======扣款成功後狀態碼1；失敗改為8=======
//				try{
//					//此處用for迴圈一一呼叫扣款方法
//				}catch(SQLException e) {
//					e.printStackTrace();
//		            throw new RuntimeException(e.getMessage(), e);
//				}
			}//run
			
		};//timerTask
		timer.scheduleAtFixedRate(task,firstime, period);
		System.out.println("預約訂單扣款排程器於"+ tFormat.format(firstime)+"開始每小時執行更新一次");
		
	}//init

	
	public void destroy() {
		timer.cancel();
	}

}
