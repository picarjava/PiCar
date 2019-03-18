package Timer;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

//	@WebServlet("/ServletTimer")
public class ServletTimer2 {
	Timer timer	= new Timer();;
	String strDate;
	TimerTask task;
	Map<String , TimerTask> map = new TreeMap<String , TimerTask>();
	
	private static final long serialVersionUID = 1L;

	
	public ServletTimer2() {
		super();
	}

	

	// GregorianCalendar 物件getTime()取得 date物件
	// 取得現在時間
//	GregorianCalendar gc = new GregorianCalendar();
//	java.util.Date date = gc.getTime();
//	SimpleDateFormat tFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
//	strDate=tFormat.format(date);
//
//	// 取得當天的日年月日
//	String stryear = strDate.substring(0, 4);
//	String strmonth = strDate.substring(5, 7);
//	String strday = strDate.substring(8, 10);
//
//	int year = Integer.parseInt(stryear);
//	int month = Integer.parseInt(strmonth);
//	int day = Integer.parseInt(strday);
//
//	// 取得當天00:00:00的GregorianCalendar物件，getTime()取得date物件
//	GregorianCalendar gcToday = new GregorianCalendar(year, month - 1, day, 0, 0, 0);
//	java.util.Date today = gcToday.getTime();timer.scheduleAtFixedRate(task,today,60*60*1000); // 第一個參數放入想要執行的結果
	// ↑這邊要用動態抓取預約訂單的時間

	// 先做出一個，再想想怎麼用多個排程器

	public TimerTask dotime(String str, int i) {
	
		task = new TimerTask() {

			@Override
			public void run() {

				System.out.println("司機遲到了" + str);

			}

		};

		timer.schedule(task, i);
		
		return task;

	}
	
	public void put(String order, String str, int i) {
		map.put(order, dotime(str,i));
	}
	

	public void cancel() {
		task.cancel();
	}
	
	public void destroy() {
		timer.cancel();
	}
	
}
