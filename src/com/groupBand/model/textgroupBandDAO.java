package com.groupBand.model;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class textgroupBandDAO {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		GroupBandJDBCDAO DAO =new GroupBandJDBCDAO();
		
		//增加
//		GroupBandVO groupBandVO =new GroupBandVO();
//		
//		groupBandVO.setContent("XX");
//		groupBandVO.setIntroduction("XXX");
//		groupBandVO.setGroupStatus(1);
//		groupBandVO.setCurrenTnum(1);
//		groupBandVO.setUpperLimit(2);
//		groupBandVO.setLowerLimit(4);
//		groupBandVO.setGroupName("五月天演唱會");
//		groupBandVO.setGroupLeader(5121);
//		groupBandVO.setStartLoc("桃園火車站");
//		groupBandVO.setEndLoc("中壢火車站");
//		groupBandVO.setPrivates(1);
//		groupBandVO.setPhoto(textgroupBandDAO.getPictureByteArray("WebContent/activity/img/team-1.jpg"));
//		groupBandVO.setGroupType("演唱會");
//		groupBandVO.setTotalAmout(5000);
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		groupBandVO.setStartTime(new Date(simpleDateFormat.parse("2019-02-14").getTime()));
//		groupBandVO.setRate(5);
//		groupBandVO.setNote("無");
//		
//		DAO.insert(groupBandVO);
//		System.out.println("成功拉");

		
		
		
		//修改		
//		GroupBandVO groupBandVO =new GroupBandVO();
//		
//		groupBandVO.setGroupID("G001");
//		groupBandVO.setContent("X4x55");
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		groupBandVO.setLaunchTime(new Timestamp(simpleDateFormat.parse("2019-02-14").getTime()));
//		groupBandVO.setIntroduction("G001");
//		groupBandVO.setGroupStatus(1);
//		groupBandVO.setCurrenTnum(1);
//		groupBandVO.setUpperLimit(2);
//		groupBandVO.setLowerLimit(4);
//		groupBandVO.setGroupName("五月天演唱會");
//		groupBandVO.setGroupLeader(5121);
//		groupBandVO.setStartLoc("桃園火車站");
//		groupBandVO.setEndLoc("中壢火車站");
//		groupBandVO.setPrivates(1);
//		groupBandVO.setPhoto(textgroupBandDAO.getPictureByteArray("WebContent/activity/img/team-1.jpg"));
//		groupBandVO.setGroupType("演唱會");
//		groupBandVO.setTotalAmout(5000);
//		
//		groupBandVO.setStartTime(new Date(simpleDateFormat.parse("2019-02-14").getTime()));
//		groupBandVO.setRate(5);
//		groupBandVO.setNote("無");
//		
//		DAO.update(groupBandVO);
//		System.out.println("成功拉");
//		
		
		
	}
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}

}
