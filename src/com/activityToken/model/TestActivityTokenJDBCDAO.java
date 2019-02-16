package com.activityToken.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

import com.activity.model.ActivityJDBCDAO;
import com.activity.model.TestActivityJDBCDAO;

public class TestActivityTokenJDBCDAO {

	public static void main(String[] args) {
		ActivityTokenJDBCDAO ActivityTokenJDBCDAO=new ActivityTokenJDBCDAO();
		TestActivityTokenJDBCDAO useMethod=new TestActivityTokenJDBCDAO();
//		/*新增*//*測試成功*/
//		ActivityTokenVO activityTokenVO1=new ActivityTokenVO();
//		activityTokenVO1.setMemID("M002");
//		activityTokenVO1.setActivityID("AC003");
//		activityTokenVO1.setTokenAmount(100);
//		activityTokenVO1.setDeadline(useMethod.getSQLDate(2019, 1, 16, 12, 12));
//		ActivityTokenJDBCDAO.insert(activityTokenVO1);
//		System.out.println("已新增一筆活動代幣");
		
//		/*修改*//*測試成功*/
//		ActivityTokenVO activityTokenVO2=new ActivityTokenVO();
//		activityTokenVO2.setMemID("M002");
//		activityTokenVO2.setActivityID("AC003");
//		activityTokenVO2.setTokenAmount(500);
//		activityTokenVO2.setDeadline(useMethod.getSQLDate(2019, 9, 16, 12, 12));
//		ActivityTokenJDBCDAO.update(activityTokenVO2);
//		System.out.println("已修改一筆活動代幣");
		
//		/*刪除*//*測試成功*/
//		ActivityTokenJDBCDAO.delete("M002", "AC003");
//		System.out.println("已刪除一筆活動代幣");
		
//		/*查詢一筆*//*測試成功*/
//		ActivityTokenVO activityTokenVO3=new ActivityTokenVO();
//		ActivityTokenVO3=ActivityTokenJDBCDAO.findByPrimaryKey("M005", "AC005");
//		System.out.println(activityTokenVO3.getActivityID());
//		System.out.println(activityTokenVO3.getActivityID());
//		System.out.println(activityTokenVO3.getTokenAmount());
//		System.out.println(activityTokenVO3.getDeadline());
//		System.out.println("已查詢一筆活動代幣");
		
		/*查全部*//*測試成功*/
		 List<ActivityTokenVO> list=ActivityTokenJDBCDAO.getAll();
		 int count=1;
		 for(ActivityTokenVO activityTokenVO4:list) {
			 System.out.println(activityTokenVO4.getActivityID());
			 System.out.println(activityTokenVO4.getActivityID());
			 System.out.println(activityTokenVO4.getTokenAmount());
			 System.out.println(activityTokenVO4.getDeadline());
			 System.out.println("已查詢一筆活動代幣");
			 System.out.println("=====已查詢第"+ count++ +"筆活動====");
		 }
		 System.out.println("=====已查詢全部活動代幣====");
		
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
