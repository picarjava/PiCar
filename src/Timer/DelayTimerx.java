package Timer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.naming.Context;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.singleOrder.model.SingleOrderDAO;

import oracle.jdbc.OracleResultSetMetaData.SecurityAttribute;

//@WebServlet(loadOnStartup = 1)
public class DelayTimerx extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Timer timer;
	long renewTime;// 當日晚間10點更新
	boolean isRenew = false; // 司機評價一天只更新一次即可， 效能較佳

//			 Calendar cal = new GregorianCalendar(2011, Calendar.MARCH, 5, 0, 0, 0); 
//		        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
//	        	Date date = new Date();
//	        	String strDate = sdFormat.format(date);

	
//	--//逾時訂單
//	--條件1:開始時間+五分鐘>=現在時間
//	--條件2:STATE=1
//
//	--撈單人訂單
//	SELECT * FROM SINGLE_ORDER WHERE STATE=1 AND START_TIME+(1/24/60)*5 >= CURRENT_TIMESTAMP;
//
//	--撈揪團訂單
	
	
	public void init() {//A.初始化一次排成器
		timer = new Timer();
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		TimerTask task = new TimerTask() {
			public void run() {

				Calendar calendar2 = Calendar.getInstance();
				calendar2.add(Calendar.DATE, 1);    //3天
//				calendar2.add(Calendar.SECOND, 5);
				String three_days_after = sdf2.format(calendar2.getTime());

				String START_TIME_Start = three_days_after + " 00:00:00";
				String START_TIME_End   = three_days_after + " 23:59:59";
//				('2019-03-18 05:20:00', 'YYYY-MM-DD HH24:MI:SS')
				System.out.println("1.");
				System.out.println(START_TIME_Start);
				System.out.println(START_TIME_End );
				List<String> startTimeList = new ArrayList<String>();
//				Set<SingleOrderVO> startTimeList = new LinkedHashSet();
				System.out.println("2.");
				startTimeList = new SingleOrderDAO().get_start_time(START_TIME_Start, START_TIME_End);// 拿出一群時間的集合
				System.out.println(startTimeList);
				ServletContext context ;
				getServletContext().setAttribute("futureOrderMAP", startTimeList);
				//拿到一堆訂單
				System.out.println("3:"+getServletContext().getAttribute("futureOrderMAP"));
//	給JSP
//				if (startTimeList != null) {
//					for (String starttime : startTimeList) {// 滾出一群時間
////					 System.out.println(starttime);
//						long num = Long.parseLong(starttime);// 動態計算出到開始時間
//						System.out.println(num);
//						System.out.println("=================");
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
//					}
//				}
				
				
				System.out.println("4.訂單集合");
//				sharetimer(startTimeList);//此行跑不到。B.將一群時間傳到另一個方法
			}
		};
//		this.getRenewTime(22);// 當日22點更新 ，取得當天22點的Long
//		timer.schedule(task, renewTime);
//		timer.schedule(task, new GregorianCalendar().getTimeInMillis(), 1000*30); //TEST
		timer.scheduleAtFixedRate(task, new java.sql.Timestamp(System.currentTimeMillis()), 1000*20); //甲. 每半小時執行一次 搜出隔天訂單 
		System.out.println("1.現在時間"+new java.sql.Timestamp(System.currentTimeMillis()));   //TEST
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
