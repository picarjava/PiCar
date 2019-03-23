package Timer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javax.naming.Context;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.websocket.Session;

import com.admin.model.AdminVO;
import com.groupOrder.model.GroupOrderDAO;
import com.groupOrder.model.GroupOrderService;
import com.singleOrder.model.SingleOrderDAO;
import com.singleOrder.model.SingleOrderService;
import com.singleOrder.model.SingleOrderVO;

//@WebServlet(loadOnStartup = 1)
public class DelayTimerx extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Timer timer;
	long renewTime;// 當日晚間10點更新
	boolean isRenew = false; // 司機評價一天只更新一次即可， 效能較佳
//
//	--//	--//逾時訂單
//	--//	--條件1:開始時間+五分鐘<=現在時間
//	--//	--條件2:STATE=1
//	--//
//	--//	--撈單人訂單 
//	  SELECT * FROM SINGLE_ORDER WHERE STATE=1 AND START_TIME+(1/24/60)*5 <= CURRENT_TIMESTAMP;
//	--DAO。SELECT * FROM SINGLE_ORDER WHERE STATE=1 AND (START_TIME+(1/24/60)*5) = ? <= CURRENT_TIMESTAMP
//	--//	--撈揪團訂單
//	  SELECT * FROM GROUP_ORDER WHERE STATE=1 AND START_TIME+(1/24/60)*5 <= CURRENT_TIMESTAMP; 
//	  
//	 -- 排成器: 以上的單放到lIST裡面，一一取出來 UPTDATE STAET=6;
	
	public void init() {//A.初始化一次排成器
		timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {

				Calendar calendar2 = Calendar.getInstance();
				calendar2.add(Calendar.DATE, 1);    //3天
//				calendar2.add(Calendar.SECOND, 5);
//				1.拿到一堆訂單
//				('2019-03-18 05:20:00', 'YYYY-MM-DD HH24:MI:SS')
				System.out.println("1.現在時間"+new java.sql.Timestamp(System.currentTimeMillis())); 
				List<String> startTimeList = new ArrayList<String>();
//				Set<SingleOrderVO> startTimeList = new LinkedHashSet();
				System.out.println("2.");
				System.out.println(startTimeList);
				getServletContext().setAttribute("futureOrderMAP", startTimeList);//	給JSP
				System.out.println("3:"+getServletContext().getAttribute("futureOrderMAP"));
				
				System.out.println("過期的單人訂單在這喔"+new SingleOrderService().getDelayOrder());//ok
				System.out.println("過期的揪團訂單在這喔"+new GroupOrderService().getDelayGOrder());//ok
				//3/21 01:40進度。已抓到過期訂單。
//				2.狀態碼改成逾期
				System.out.println("UPDATE SINGLE_ORDER SET STATE ='6' WHERE ORDER_ID=?");
				
//				先拿到目前在線管理員
				@SuppressWarnings("unchecked")
				Map<String, Session> broadcastOrderMap = (Map<String, Session>) (getServletContext()
						.getAttribute("broadcastOrderMap"));
				System.out.println("是否有管理員在線上:" + (broadcastOrderMap != null));
				
				List<String> singleDelayList = new ArrayList<String>();
				singleDelayList= new SingleOrderService().getDelayOrder();
//				if (startTimeList != null) {
					for (String singledelay : singleDelayList) {// 滾出一群過期單人訂單
						System.out.println(singledelay);
						new SingleOrderService().updateDelayOrder(singledelay);
//						System.out.println(new SingleOrderService().get);
						System.out.println("=================");
						
						if (broadcastOrderMap != null) { // 若有會員在線，則可以進入推播對象的篩選
						String adminID= new AdminVO().getAdminID();
						Session isOnline=broadcastOrderMap.get(adminID);
						
						if(isOnline!=null) { //若此會員在線，則推播
					    String message= "訂單編號"+singledelay+"有問題";
						String toJsonMessage= "{\"message\":\"" +message+"\"}";	
							try {
								isOnline.getBasicRemote().sendText(toJsonMessage);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					}
//			}
					List<String> groupDelayList = new ArrayList<String>();
					groupDelayList = new GroupOrderService().getDelayGOrder();
					for (String groupdelay : groupDelayList) {// 滾出一群過期揪團訂單
						System.out.println("UPDATE GROUP_ORDER SET STATE ='6' WHERE GORDER_ID=?");
						System.out.println(groupdelay);
						new GroupOrderService().updateDelayGOrder(groupdelay);
//						System.out.println(new SingleOrderService().get);
						System.out.println("=================");}
///////////////////////////////////////////////////////////////////////////////
//					3.	推播websocket給管理員
					
//					@SuppressWarnings("unchecked")
//					Map<String, Session> broadcastMap = (Map<String, Session>) (getServletContext()
//							.getAttribute("broadcastMap"));
//					System.out.println("是否有會員在線上:" + (broadcastMap != null));
					// 推播成功架構
//					if(broadcastMap!=null) {
//						for(SingleOrderVO allUnpaidOrders:allUnpaid) {
//	//						String memID= new AdminVO().getAdminID();
//							Session isOnline=broadcastMap.get(memID);
//							
//							if(isOnline!=null) { //若此會員在線，則推播
//						    String message= "訂單編號"+groupdelay.getOrderID()+"已為您扣款";
//							String toJsonMessage= "{\"message\":\"" +message+"\"}";	
//								try {
//									isOnline.getBasicRemote().sendText(toJsonMessage);
//								} catch (IOException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//		//					}
//						}
//					}
					
					
////						TimerTask delaytask = new TimerTask() {//D.執行下一個排成器
////							public void run() {
////								// System.out.println("更新日期:"+new
////								// java.util.Date()+"司機編號"+driverID+"最新評價為:"+rateAve+ "分");
////								TimerTask taskdelay = null;
////								taskdelay = afterdelay();
//////								new Timer().schedule(taskdelay, 1000 * 60 * 5);// 開始五分鐘後發生的事情 各位觀眾跟我一起倒數好嗎?
////								new Timer().schedule(taskdelay, 1000 * 5);// 開始五分鐘後發生的事情 各位觀眾跟我一起倒數好嗎?
////							}
////						};
////						new Timer().schedule(delaytask, num);// 動態計算出到開始時間時 開始時要記時5分鐘
////						new Timer().schedule(delaytask, num);// 動態計算出到開始時間時 開始時要記時5分鐘//TEST
//						// 排成器delaytask 5分鐘後 1.狀態碼改成逾期2.推播websocket給管理員 
//						//
////						TimerTask delaytask = new TimerTask() {//D.執行下一個排成器
////							public void run() {
////								// System.out.println("更新日期:"+new
////								// java.util.Date()+"司機編號"+driverID+"最新評價為:"+rateAve+ "分");
////								TimerTask taskdelay = null;
////								taskdelay = afterdelay();
//////								new Timer().schedule(taskdelay, 1000 * 60 * 5);// 開始五分鐘後發生的事情 各位觀眾跟我一起倒數好嗎?
////								new Timer().schedule(taskdelay, 1000 * 5);// 開始五分鐘後發生的事情 各位觀眾跟我一起倒數好嗎?
////							}
////						};
////						new Timer().schedule(delaytask, num);// 動態計算出到開始時間時 開始時要記時5分鐘
////						new Timer().schedule(delaytask, num);// 動態計算出到開始時間時 開始時要記時5分鐘//TEST
//				}
//				sharetimer(startTimeList);//此行跑不到。B.將一群時間傳到另一個方法
			}//run
		};//timertask
//		timer.schedule(task, renewTime);
//		timer.schedule(task, new GregorianCalendar().getTimeInMillis(), 1000*30); //TEST
		timer.scheduleAtFixedRate(task, new java.sql.Timestamp(System.currentTimeMillis()), 1000*60); //甲. 每半小時執行一次 搜出隔天訂單 
//		System.out.println("C.現在毫秒數"+new GregorianCalendar().getTimeInMillis());   //TEST
	}//init
	// 傳入當日欲更新的hour
	
	
	public void sharetimer(List<String> startTimeList) {
		boolean isNew = false;
		if (!isNew) {
			// 迴圈滾出來 C.
//			if (startTimeList != null) {
//				for (String starttime : startTimeList) {// 滾出一群時間
////				 System.out.println(starttime);
//					long num = Long.parseLong(starttime);// 動態計算出到開始時間
//					System.out.println(num);
//					System.out.println("=================");
//					TimerTask delaytask = new TimerTask() {//D.執行下一個排成器
//						public void run() {
//							// System.out.println("更新日期:"+new
//							// java.util.Date()+"司機編號"+driverID+"最新評價為:"+rateAve+ "分");
//							TimerTask taskdelay = null;
//							taskdelay = afterdelay();
////							new Timer().schedule(taskdelay, 1000 * 60 * 5);// 開始五分鐘後發生的事情 各位觀眾跟我一起倒數好嗎?
//							new Timer().schedule(taskdelay, 1000 * 5);// 開始五分鐘後發生的事情 各位觀眾跟我一起倒數好嗎?
//						}
//					};
////					new Timer().schedule(delaytask, num);// 動態計算出到開始時間時 開始時要記時5分鐘
//					new Timer().schedule(delaytask, num);// 動態計算出到開始時間時 開始時要記時5分鐘//TEST
//				}
//			}
		}
	}
	private TimerTask afterdelay() {// 放入逾時的事情拉
		TimerTask taskdelay = new TimerTask() {
			@Override
			public void run() {
				new SingleOrderDAO().update_state_to_delay();
			}
		};
		return taskdelay;
	}
	public void destroy() {
		timer.cancel();
		
	}
	public long getRenewTime(int renewHour) {
		// 取得現在時間
		Date date = new java.util.Date();
		SimpleDateFormat tFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		String strDate = tFormat.format(date);

		// 取得當日的日年月日
		String stryear = strDate.substring(0, 4);
		String strmonth = strDate.substring(5, 7);
		String strday = strDate.substring(8, 10);

		int year = Integer.parseInt(stryear);
		int month = Integer.parseInt(strmonth);
		int day = Integer.parseInt(strday);

		GregorianCalendar gc = new GregorianCalendar(2019, 2, 13, renewHour, 00);
		GregorianCalendar gc1 = new GregorianCalendar(2019, 2, 13);
		long hour = gc.getTime().getTime() - gc1.getTime().getTime();
		long today = new GregorianCalendar(year, month - 1, day).getTime().getTime();
		renewTime = today + hour; // 當日晚間10點的long
		return renewTime;
	}
}
