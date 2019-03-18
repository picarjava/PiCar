package Timer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import com.singleOrder.model.SingleOrderDAO;

public class Timerdo {
	
	public static void main(String[] args) {
		ServletTimer2 st2 = new ServletTimer2();
		
		
		
//		訂單編號  開始時間 起始秒數
		
//		List<String> x =  new SingleOrderDAO().get_start_time("2019-03-18 00:00:00"," 2019-03-18 23:59:59");
		long renewTime;// 當日晚間10點更新

//			timer = new Timer();
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DATE, 1);    //3天
//		calendar2.add(Calendar.SECOND, 5);
		String three_days_after = sdf2.format(calendar2.getTime());

		String START_TIME_Start = three_days_after + " 00:00:00";
		String START_TIME_End   = three_days_after + " 23:59:59";
//		('2019-03-18 05:20:00', 'YYYY-MM-DD HH24:MI:SS')
//		每半小時掃描排程訂單狀態碼 放LIST中放VO 1.第一個每天定時掃描隔天全部資料存LIST 2.每半小時查訂單狀態碼
		System.out.println("2.");
		System.out.println(START_TIME_Start);
		System.out.println(START_TIME_End );
		List<String> startTimeList = new ArrayList<String>();
//		Set<SingleOrderVO> startTimeList = new LinkedHashSet();
		System.out.println("3.");
		startTimeList = new SingleOrderDAO().get_start_time(START_TIME_Start, START_TIME_End);// 拿出一群訂單的集合
		
		
		
		st2.put("7001", "5", 5000);
		st2.put("7002", "10", 5000);
		st2.put("7003", "15", 6000);
		
		st2.map.get("7001").cancel();//接單改變
		
		
		
		
		
		
		
//		Date date = new Date();
//		
//		ServletTimer2 st1 = new ServletTimer2();
//		ServletTimer2 st2 = new ServletTimer2();
//		ServletTimer2 st3 = new ServletTimer2();
//		st1.dotime("1");
//		st2.dotime("2");
//		st3.dotime("3");
//		
//		
//		st1.cancel();

//		ServletTimer2[] st = new ServletTimer2[10];
//		for (int i = 0; i < st.length; i++) {
//			st[i] = new ServletTimer2();
//
//		}
//
//		st[0].dotime("0", 1000);
//		st[1].dotime("1", 2000);
//		st[2].dotime("2", 10000);
//		st[3].dotime("3", 5000);
//		st[4].dotime("4", 6000);
//		st[5].dotime("5", 7000);
//		st[6].dotime("6", 8000);
//		
//		st[3].cancel();

//		map.get(3).cancel();
//
//		try {
//			Thread.sleep(25000);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		st2.destroy();

//		ServletTimer2 st2 = new ServletTimer2();
//		ServletTimer2 st3 = new ServletTimer2();
//		ServletTimer2 st4 = new ServletTimer2();
//		st2.doTimeCancle("a");
//		st2.doTimeCancle("b");

//		st2.dotime("b司機", date);	
//		st4.dotime("c司機", date);
//		st3.dotime("a司機", date);	
//		

//		st2.cancel();			
//		st3.cancel();

	}

}
