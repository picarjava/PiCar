package Timer;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.SortedSet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import com.singleOrder.model.SingleOrderService;
import com.singleOrder.model.SingleOrderVO;
import com.util.CountToken;
import com.util.CountToken1;
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

	public void init() throws ServletException {
		timer = new Timer();

		SimpleDateFormat tFormat = new SimpleDateFormat("yyyy/MM/dd a hh:mm:ss ");
		TimeConverter timeConverter = new TimeConverter();
		Date firstime = timeConverter.getThisHourToday(0);// 開始時間為伺服器啟動的當天0點
<<<<<<< HEAD
		long period = 1000 * 60 *2    ; // 每12小時執行一次
=======
		long period = 1000 * 30    ; // 每12小時執行一次
>>>>>>> eb7f4071e7a06e42b27a951c4576ebb69892f226
		SingleOrderService singleOrderSvc = new SingleOrderService();
		HashSet<SingleOrderVO> allUnpaid = new HashSet<SingleOrderVO>();// 待付款訂單
		TimerTask task = new TimerTask() {
			int count;
			String excutedTime;

			@Override
			public void run() {

				Date date = new Date(this.scheduledExecutionTime());
				excutedTime = tFormat.format(date);
				count++;
				System.out.println("=======系統於" + excutedTime + " 做第" + count + "次預約訂單扣款=======");

				// 1.=======撈出三日前未完成付款的訂單=======
				// 將單程的部分加入待付款訂單

				for (SingleOrderVO unpaidReservationOrder : singleOrderSvc.listAllUnpaidReservationOrder()) {
					allUnpaid.add(unpaidReservationOrder);
					System.out.println("待扣款的單程預約:" + unpaidReservationOrder.getOrderID());
				}

				// 將長期的部分加入待付款訂單
				Timestamp checkLaunchTime = null;
				for (SingleOrderVO earlierPartUnpaidOrderFromLongtermSets : singleOrderSvc
						.listEarlierPartUnpaidFromLongtermSets()) {
					Timestamp thisLaunchTime = earlierPartUnpaidOrderFromLongtermSets.getLaunchTime();// 檢查是否為同一批長期訂單
					if (!thisLaunchTime.equals(checkLaunchTime)) { // 不同批者，則進入查整組
						for (SingleOrderVO unpaidlongtermOrder : singleOrderSvc.listOneSetOfLongterm(thisLaunchTime)) { // 得到整組長期訂單
							allUnpaid.add(unpaidlongtermOrder);
							System.out.println("待扣款的長期預約:" + unpaidlongtermOrder.getOrderID());
						}
						;
						checkLaunchTime = earlierPartUnpaidOrderFromLongtermSets.getLaunchTime();
					}
				}

				// 先拿到目前在線會員
				@SuppressWarnings("unchecked")
				Map<String, Session> broadcastMap = (Map<String, Session>) (getServletContext()
						.getAttribute("broadcastMap"));
				System.out.println("是否有會員在線上:" + (broadcastMap != null));
				int i = allUnpaid.size(); //蔣 改的
				// 開始扣款
				SingleOrderService singleOrderSvc = new SingleOrderService();
				
				for (SingleOrderVO allUnpaidOrders : allUnpaid) {
					SingleOrderVO singleOrderVO = new SingleOrderVO();
					CountToken1 countToken = new CountToken1(); //蔣 改的
					String memID = allUnpaidOrders.getMemID();
					int amount = allUnpaidOrders.getTotalAmount();
					String orderID = allUnpaidOrders.getOrderID();
					

					try {
						countToken.countToken(memID, amount, orderID, i);
						i--; //蔣 改的
						System.out.println("扣款成功"); // 成功則改狀代碼為1

						singleOrderVO = singleOrderSvc.getOneSingleOrder(orderID);
						singleOrderVO.setState(1);
						singleOrderSvc.updateSingleOrder(orderID, singleOrderVO.getDriverID(), singleOrderVO.getState(),
								singleOrderVO.getStartTime(), singleOrderVO.getEndTime(), singleOrderVO.getStartLoc(),
								singleOrderVO.getEndLoc(), singleOrderVO.getStartLng(), singleOrderVO.getStartLat(),
								singleOrderVO.getEndLng(), singleOrderVO.getEndLat(), singleOrderVO.getTotalAmount(),
								singleOrderVO.getRate());

						if (broadcastMap != null) { // 若有會員在線，則可以進入推播對象的篩選
							Session isOnline = broadcastMap.get(memID);
							if (isOnline != null) { // 若此會員有在線，則對此會員進行推播
								String message = "訂單編號" + orderID + "已於" + excutedTime + "為您扣款";
								String toJsonMessage = "{\"message\":\"" + message + "\"}";
								if (isOnline.isOpen())
									isOnline.getAsyncRemote().sendText(toJsonMessage);
							}
						}

					} catch (Exception e) {
						System.out.println("扣款失敗");// 失敗則改狀代碼為8
						singleOrderVO = singleOrderSvc.getOneSingleOrder(orderID);

						singleOrderVO.setState(8);
						singleOrderVO = singleOrderSvc.updateSingleOrder(orderID, singleOrderVO.getDriverID(),
								singleOrderVO.getState(), singleOrderVO.getStartTime(), singleOrderVO.getEndTime(),
								singleOrderVO.getStartLoc(), singleOrderVO.getEndLoc(), singleOrderVO.getStartLng(),
								singleOrderVO.getStartLat(), singleOrderVO.getEndLng(), singleOrderVO.getEndLat(),
								singleOrderVO.getTotalAmount(), singleOrderVO.getRate());
						if (broadcastMap != null) { // 若有會員在線，則可以進入推播對象的篩選
							Session isOnline = broadcastMap.get(memID);
							if (isOnline != null) { // 若此會員有在線，則對此會員進行推播
								 String message= "訂單編號" + orderID + "因扣款失敗已流單";
								 String toJsonMessage = "{\"message\":\"" + message + "\"}";
								 try {
									isOnline.getBasicRemote().sendText(toJsonMessage);
									} catch (IOException ee) {
										// TODO Auto-generated catch block
										ee.printStackTrace();
								}
							}
						}
					}
				}//for迴圈
				allUnpaid.clear(); //清空容器

				// 推播成功架構
//				if(broadcastMap!=null) {
//					for(SingleOrderVO allUnpaidOrders:allUnpaid) {
//						String memID=allUnpaidOrders.getMemID();
//						Session isOnline=broadcastMap.get(memID);
//						
//						if(isOnline!=null) { //若此會員在線，則推播
//					    String message= "訂單編號"+allUnpaidOrders.getOrderID()+"已為您扣款";
//						String toJsonMessage= "{\"message\":\"" +message+"\"}";	
//							try {
//								isOnline.getBasicRemote().sendText(toJsonMessage);
//							} catch (IOException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//					}
//				}

				// 2.=======呼叫小蔣扣款方法=======

				// 3.=======扣款成功後狀態碼1；失敗改為8=======

			}// run

		};// timerTask
		timer.scheduleAtFixedRate(task, firstime, period);
		System.out.println("預約訂單扣款排程器於" + tFormat.format(firstime) + "開始每小時執行更新一次");

	}// init

	public void destroy() {
		timer.cancel();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		doPos(req, res);
	}

	protected void doPos(HttpServletRequest req, HttpServletResponse res) {
		serveletName = req.getServerName();
		port = req.getServerPort();
		session = (Session) req.getSession();
	}

}
