package Timer;

import java.util.List;

import com.singleOrder.model.SingleOrderDAO;

public class Timerdo {
	
	public static void main(String[] args) {
		ServletTimer2 st2 = new ServletTimer2();
		
		
		
//		訂單編號  開始時間 起始秒數
		
//		List<String> x =  new SingleOrderDAO().get_start_time("2019-03-18 00:00:00"," 2019-03-18 23:59:59");
		
		st2.put("7001", "5", 5000);
		st2.put("7002", "10", 5000);
		st2.put("7003", "15", 6000);
		
		st2.map.get("7001").cancel();
		
		
		
		
		
		
		
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
