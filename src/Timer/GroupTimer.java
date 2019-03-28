package Timer;

import com.util.*;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.groupBand.model.GroupBandService;
import com.groupBand.model.GroupBandVO;
import com.groupOrder.model.*;

import java.util.*;
/**
 * Servlet implementation class GroupTimer
 */
//@WebServlet("/GroupTimer")
public class GroupTimer extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	int count = 0;      
	Timer timer;
	String strDate;
	
	public void init() {
	     timer = new Timer();
	        Calendar cal = new GregorianCalendar(2011, Calendar.MARCH, 5, 0, 0, 0); 
//	        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
//        	Date date = new Date();
//        	String strDate = sdFormat.format(date);
	        TimerTask task = new TimerTask(){
	                   
	            public void run(){
	            	
	            	
    				//推播:先拿到執行時間
    				Date date = new Date(this.scheduledExecutionTime());
    				SimpleDateFormat tFormat = new SimpleDateFormat("yyyy/MM/dd a hh:mm:ss ");
    				String excutedTime = tFormat.format(date);
	            	
	            	
	            	Calendar calendar2 = Calendar.getInstance();
	            	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	            	calendar2.add(Calendar.DATE, 3);
	            	String three_days_after = sdf2.format(calendar2.getTime());
	            
	            	GroupOrderService groupOrderService = new GroupOrderService();
	            	GroupBandService groupBandService =new GroupBandService();
	            	
	            	String START_TIME = three_days_after +" 00:00:00";
	            	
	            	String START_TIME_End = three_days_after +" 23:59:59";
	            	
	            	//--取得那些揪團--
	            	List<String> groupIDList =new ArrayList<String>();
//	            	System.out.println("有獨到這一行");
	            	groupIDList = groupOrderService.get_group_id__start_time(START_TIME,START_TIME_End);
	            	
	            	int countmemid;
	            	
	            	GroupBandVO groupBandVO;
//	            	判斷是否有到期限的單
	            	if(groupIDList!=null) {
	            		//迴圈把所有單拿出來比對
	            	for(String groupID:groupIDList) {
	            		countmemid = groupOrderService.getMemID_groupID_startTime(groupID,START_TIME,START_TIME_End);
	            		groupBandVO = groupBandService.getOneGroupBand(groupID);
	            		
	            		
	            		
	            		//測試	            		
	            		
	            		if(groupBandVO.getGroupStatus()==0) {
	            		if(countmemid>=groupBandVO.getLowerLimit()) {
	            			
	            			//改揪團狀態碼為已成團
	            			groupBandService.UPDATE_GROUP_STATUS__GROUP_ID(1, groupID);
	            			
	            			//改訂單為已成團
	            			groupOrderService.UPDATE_STATE__GROUP_ID(1,groupID);
	            			
	            			//為NULL的單
	            			groupOrderService.updateState_GroupID_mem_ID(groupID,2);
	            			
	            			//取出目前揪團金額
	            			int totalAmout = groupBandService.getOneTotalAmoutGroupID(groupID);
	            			
	            			//目前揪團金額除以人數和天數
	            			int Amout = totalAmout/groupOrderService.getstateGrouID_Memid_Notnull(groupID);
	            			
	            			//填入金額
	            			groupOrderService.UPDATE_Total_AmoutGroupIDState(Amout, groupID,1);
	            			
//	            			List<GroupOrderVO> lsit  = groupOrderService.getALL_GroupID_State(groupID, 1);   //用鳩團ID 狀態碼(1)  找出揪團訂單(TABLE) 揪團訂單ID 訂單金額 會員ID
		            		
	            			
	            			
	            			/*加入成團與扣款成功推播*/
	            			//推播:得到memID
	            			List<String> lista =new ArrayList<String>();
	            			lista=groupOrderService.getMemID_groupID(groupID);
	            			for(String listss :lista){
	            				String memID=listss;
	            				
	            				// 每次都重抓目前在線會員放進MAP
								@SuppressWarnings("unchecked")
								Map<String, Session> broadcastMap = (Map<String, Session>) (getServletContext()
										.getAttribute("broadcastMap"));
								System.out.println("是否有會員在線上:" + (broadcastMap != null));
	            				if (broadcastMap != null) { // 若有會員在線，則可以進入推播對象的篩選
									Session isOnline = broadcastMap.get(memID);
									if (isOnline != null) { // 若此會員有在線，則對此會員進行推播
										String message = "揪團編號" + groupID + "已於" + excutedTime + "揪團成功並且扣款成功";
										String toJsonMessage = "{\"message\":\"" + message + "\"}";
										try {
											isOnline.getBasicRemote().sendText(toJsonMessage);
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}
	            			}
	            		}else
	            		{
	            			
	            			//改揪團狀態碼為已成團
	            			groupBandService.UPDATE_GROUP_STATUS__GROUP_ID(2, groupID);
	            			
	            			//改訂單為已流團
	            			groupOrderService.UPDATE_STATE__GROUP_ID(8,groupID);
	            		}
	            		}
	            		
//	            		System.out.println(groupID+"人數"+countmemid);
//	            		System.out.println(groupID+"比較人數"+groupBandVO.getLowerLimit());
	            	}
	            	}
//
//	            	--取得目前下限 人數--
//	            	select COUNT("MEM_ID")as MEM_ID from GROUP_ORDER where GROUP_ID='G005' and START_TIME LIKE '15-MAR-19%';
//
//	            	--取的揪團人數下限 拿出後比對下限人數--
//	            	SELECT LOWER_LIMIT FROM GROUP_BAND  where GROUP_ID='G005';
//
//	            	--java比對--
//	            	--if(MEM_ID>LOWER_LIMIT)--
//
//	            	--trun 成單--
//	            	update GROUP_BAND set GROUP_STATUS=1  where GROUP_ID='G005';
//	            	update GROUP_ORDER set STATE=1  where GROUP_ID='G005';
//
//	            	--false 流單--
//	            	update GROUP_BAND set GROUP_STATUS=2  where SGROUP_ID='G005';
//	            	update GROUP_ORDER set STATE=8  where GROUP_ID='G005';

	    	
	            }
	        };
			GregorianCalendar gc= new GregorianCalendar();
			java.util.Date date =gc.getTime();
			SimpleDateFormat tFormat=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			strDate=tFormat.format(date);
			
			//取得當天的日年月日
					String stryear=strDate.substring(0, 4);
					String strmonth=strDate.substring(5, 7);
					String strday=strDate.substring(8, 10);
					
					int year=Integer.parseInt(stryear);
					int month=Integer.parseInt(strmonth);
					int day=Integer.parseInt(strday);
			 
			//取得當天00:00:00的GregorianCalendar物件，getTime()取得date物件
				GregorianCalendar gcToday=new GregorianCalendar(year, month-1, day, 0, 0, 0);
				java.util.Date today=gcToday.getTime();
				
	        timer.scheduleAtFixedRate(task, today,15*60*1000); 
//	        24*60*60*1000
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupTimer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	public void destroy() {
		timer.cancel();
		
	}

}