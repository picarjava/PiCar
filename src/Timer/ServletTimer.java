//package Timer;
//
//	import java.io.IOException;
//	import java.io.PrintWriter;
//	import java.text.SimpleDateFormat;
//	import java.util.Date;
//	import java.util.GregorianCalendar;
//	import java.util.Timer;
//	import java.util.TimerTask;
//
//	import javax.servlet.ServletConfig;
//	import javax.servlet.ServletException;
//	import javax.servlet.annotation.WebServlet;
//	import javax.servlet.http.HttpServlet;
//	import javax.servlet.http.HttpServletRequest;
//	import javax.servlet.http.HttpServletResponse;
//
////定期select DB 比對資料 若 真的則update
//	/**
//	 * Servlet implementation class SchedulServlet
//	 */
////	@WebServlet("/ServletTimer")
//	public class ServletTimer extends HttpServlet {
//		Timer timer;
//		String strDate;
//
//		private static final long serialVersionUID = 1L;
//	       
//	    /**
//	     * @see HttpServlet#HttpServlet()
//	     */
//	    public ServletTimer() {
//	        super();
//	    }
//
//		/**
//		 * @see Servlet#init(ServletConfig)
//		 */
//		public void init(ServletConfig config) throws ServletException {
//			
////			java.util.Date today = null;
//			
//			timer=new Timer();
//			TimerTask task=new TimerTask(){//想做的事情 置入timertask
//				 int i = 1;
//	//主控台印出時間
//				 public void run(){
//					 
//					// 修改訂單狀態碼
//					//通知課服，利用websocket及js
//
//					 // 用TimerTask物件 呼叫scheduledExecutionTime()得到long的指定執行時間
//					//再利用Date建構子即可取得  指定執行時間的Date物件 
//					 Date date =new Date(this.scheduledExecutionTime());
//						SimpleDateFormat tFormat=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
//						String strDate1=tFormat.format(date);
//						System.out.println(strDate1);
//						System.out.println("Hello World! " + i);
//						i++;
//						return; //或是看看有沒有其他方法
//				}
//			};
//			
//			//GregorianCalendar 物件getTime()取得 date物件
//			//取得現在時間
//			GregorianCalendar gc= new GregorianCalendar();
//			java.util.Date date =gc.getTime();
//			SimpleDateFormat tFormat=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
//			strDate=tFormat.format(date);
//			
//			//取得當天的日年月日
//					String stryear=strDate.substring(0, 4);
//					String strmonth=strDate.substring(5, 7);
//					String strday=strDate.substring(8, 10);
//					
//					int year=Integer.parseInt(stryear);
//					int month=Integer.parseInt(strmonth);
//					int day=Integer.parseInt(strday);
//			 
//			//取得當天00:00:00的GregorianCalendar物件，getTime()取得date物件
//				GregorianCalendar gcToday=new GregorianCalendar(year, month-1, day, 0, 0, 0);
//				java.util.Date today=gcToday.getTime();
//				timer.scheduleAtFixedRate(task, today, 60*60*1000); //第一個參數放入想要執行的結果
//												//↑這邊要用動態抓取預約訂單的時間	
//				
//				// 先做出一個，再想想怎麼用多個排程器
//				
//		}
//			public void destroy() {
//			timer.cancel();
//		}
//	
//}
