package com.activity.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

public class TestActivityJDBCDAO {
	/*JDBC DAO 測試*/
	public static void main(String[] args) throws IOException {
		
		ActivityJDBCDAO activityJDBCDAO=new ActivityJDBCDAO();
		TestActivityJDBCDAO testJDBCDAO=new TestActivityJDBCDAO();
		
//		/*新增*//*測試成功*/
//		ActivityVO activityVO1= new ActivityVO();
//
//		activityVO1.setActivityName("測試活動名稱");
//		activityVO1.setActivityInfo("測試活動資訊");
//		activityVO1.setActivityStart(testJDBCDAO.getSQLDate(2019,1,15,10,10));
//		activityVO1.setActivityEnd(testJDBCDAO.getSQLDate(2019,3,15,10,10));
//		activityVO1.setActivityCode("Picar");
//		activityVO1.setTokenAmount(500);
//		activityVO1.setActivityPost(testJDBCDAO.getBytePost("WebContent/activity/img/team-1.jpg"));
//		activityJDBCDAO.insert(activityVO1);
//		System.out.println("已新增一筆活動");
		
		
//		/*修改*//*測試成功*/
//		ActivityVO activityVO2= new ActivityVO();
//		activityVO2.setActivityID("AC001");
//		activityVO2.setActivityName("測試活動名稱1");
//		activityVO2.setActivityInfo("測試活動資訊1");
//		activityVO2.setActivityStart(testJDBCDAO.getSQLDate(2019,4,15,10,10));
//		activityVO2.setActivityEnd(testJDBCDAO.getSQLDate(2019,5,15,10,10));
//		activityVO2.setActivityCode("Bicar");
//		activityVO2.setTokenAmount(600);
//		activityVO2.setActivityPost(testJDBCDAO.getBytePost("WebContent/activity/img/team-2.jpg"));
//		activityJDBCDAO.update(activityVO2);
//		System.out.println("已修改一筆活動");
		
		/*刪除*//*測試成功*/
//		activityJDBCDAO.delete("AC001");
//		System.out.println("已刪除一筆活動");
	
//		/*查詢一筆*//*測試成功*/
//		ActivityVO activityVO3=activityJDBCDAO.findByPrimaryKey("AC002");
//		System.out.println(activityVO3.getActivityID());
//		System.out.println(activityVO3.getActivityName());
//		System.out.println(activityVO3.getActivityInfo());
//		System.out.println(activityVO3.getActivityStart());
//		System.out.println(activityVO3.getActivityEnd());
//		System.out.println(activityVO3.getActivityCode());
//		System.out.println(activityVO3.getTokenAmount());
//		System.out.println(activityVO3.getActivityPost());
//		System.out.println("已查詢一筆活動");
		
		
		/*查全部*//*測試成功*/
//		List<ActivityVO> list=activityJDBCDAO.getAll();
//		int count=1;
//		for(ActivityVO activityVO4:list) {
//			
//			System.out.println(activityVO4.getActivityID());
//			System.out.println(activityVO4.getActivityName());
//			System.out.println(activityVO4.getActivityInfo());
//			System.out.println(activityVO4.getActivityStart());
//			System.out.println(activityVO4.getActivityEnd());
//			System.out.println(activityVO4.getActivityCode());
//			System.out.println(activityVO4.getActivityPost());
//			System.out.println("=====已查詢第"+ count++ +"筆活動====");
//		}
//		System.out.println("=====已查詢全部活動====");
	}

	public java.sql.Date getSQLDate(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
		GregorianCalendar gc=new GregorianCalendar( year,  month, dayOfMonth,  hourOfDay,  minute);
		long longDate=gc.getTime().getTime();
		java.sql.Date sqldate=new java.sql.Date(longDate);
		return sqldate;
	}
	

	public byte[] getBytePost(String path) throws IOException {
		File file=new File(path);
		FileInputStream fis;
		ByteArrayOutputStream baos;
		byte[] bytePost;
		
			fis = new FileInputStream(file);
			baos=new ByteArrayOutputStream();
			bytePost=new byte[fis.available()];
			while((fis.read(bytePost))!=-1) {
				baos.write(bytePost);
			}
			baos.close();
			fis.close();
		return bytePost;
	}
	
}
