package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeConverter{
	//傳入小時數，回傳今日此小時的Date物件
	public Date getThisHourToday(int renewHour){
		//取得現在時間
		Date date =new java.util.Date();
		SimpleDateFormat tFormat=new SimpleDateFormat("yyyy-MM-dd");
		String strDate=tFormat.format(date);
				
		//取得當日的日年月日
		String stryear=strDate.substring(0, 4);
		String strmonth=strDate.substring(5, 7);
		String strday=strDate.substring(8, 10);
						
		int year=Integer.parseInt(stryear);
		int month=Integer.parseInt(strmonth);
		int day=Integer.parseInt(strday);

		Date thisHourToday=new GregorianCalendar(year,month-1,day,renewHour,0).getTime();
		return thisHourToday;
	}
	
}
