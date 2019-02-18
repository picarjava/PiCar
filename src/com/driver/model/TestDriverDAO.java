package com.driver.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.GregorianCalendar;

public class TestDriverDAO {
	
	public static void main(String[] args) throws IOException{
		DriverJDBCDAO DAO = new DriverJDBCDAO();
		TestDriverDAO testJDBCDAO = new TestDriverDAO();
//		----------------------------
//		insert ok
//		DriverVO driverdao= new DriverVO();
//		driverdao.setMemID("M006");
//		driverdao.setDriverID("DR003");
//		driverdao.setPlateNum("PCI-0001");
//		driverdao.setLicence(testJDBCDAO.getBytePost("WebContent/driver/img/licence1.jpg"));
//		driverdao.setCriminal(testJDBCDAO.getBytePost("WebContent/driver/img/criminal1.jpg"));
//		driverdao.setTrafficRecord(testJDBCDAO.getBytePost("WebContent/driver/img/trafficrecord1.jpg"));
//		driverdao.setIdNum(testJDBCDAO.getBytePost("WebContent/driver/img/idnum1.jpg"));
//		driverdao.setPhoto(testJDBCDAO.getBytePost("WebContent/driver/img/photo1.jpg"));
//		driverdao.setVerified(1);
//		driverdao.setBanned(1);
//		driverdao.setDeadline(testJDBCDAO.getSQLDate(2019,3,29,8,10));
//		driverdao.setOnlineCar(1);
//		driverdao.setScore(80);
//		driverdao.setCarType("AA");
//		driverdao.setSharedCar(1);
//		driverdao.setPet(1);
//		driverdao.setSmoke(1);
//		driverdao.setBabySeat(1);
//		DAO.insert(driverdao);
//		System.out.println("insert success");
				
        //delete ok
//		DAO.delete("DR003");
//		System.out.println("driver has deleted");

		
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
