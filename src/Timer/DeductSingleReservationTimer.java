package Timer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.singleOrder.model.SingleOrderService;
import com.singleOrder.model.SingleOrderVO;
import com.util.BroadcastServer;
import com.util.TimeConverter;





public class DeductSingleReservationTimer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Timer timer;
	int port;
	String serveletName;
	Session session;
    
    public DeductSingleReservationTimer() {
        super();
       
    }
	public void init(ServletConfig config) throws ServletException {
		timer=new Timer();
		
		SimpleDateFormat tFormat=new SimpleDateFormat("yyyy/MM/dd a hh:mm:ss ");
		TimeConverter timeConverter=new TimeConverter();
		Date firstime=timeConverter.getThisHourToday(0);//開始時間為伺服器啟動的當天0點
		long period=1000*60*60*24; //每小時執行一次
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
				
				
				//Q2.或者 連線到  broadcastServer如何sendMessage?
//				String MyPoint="/BroadcastServer/"+allUnpaidOrders.getMemID();
//			    String contextPath=getServletContext().getContextPath();
//			    String endPointURL="ws://"+contextPath+MyPoint;
//			    Socket socket=null;
//			    
//			    try {
//					URI url = new URI(endPointURL);
//					SocketAddress socketAddress=new InetSocketAddress(url.getHost(),port);
//					socket =new Socket();
//					socket.connect(socketAddress);
//					
//					BufferedReader s_in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
//					
//					OutputStream os= socket.getOutputStream();
//					
//					
//				} catch (URISyntaxException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}catch (UnknownHostException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				
				
				//Q1.從排程器推播 如何得到同一個 與接收端同一個 broadcastServer物件
				BroadcastServer broadcastServer=new BroadcastServer();
				Session onOpenSession=broadcastServer.getOnOpenSession();
				
				System.out.println("排成器有沒有拿到onOpensesssion:"+(onOpenSession!=null));
				
				for(SingleOrderVO allUnpaidOrders:allUnpaid) {
					String memID=allUnpaidOrders.getMemID();
//					String message= "訂單編號"+allUnpaidOrders.getOrderID()+"已為您扣款";
					String toJsonMessage= "{\"message\":\"" +"已為您扣款"+"\"}";
					
					if(onOpenSession!=null) {
						broadcastServer.onMessage(memID, onOpenSession, toJsonMessage);
					}
				}
				
				
			
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
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		doPos(req,res);
	}
	
    protected void doPos(HttpServletRequest req, HttpServletResponse res) {
    	serveletName=req.getServerName();
		port=req.getServerPort();
		session=(Session) req.getSession();
	}

}
