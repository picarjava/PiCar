package com.broadcast.model;

import java.io.IOException;

public class TestBroadcastJDBCDAO {

	public static void main(String[]args)throws IOException{
		
		BroadcastJDBCDAO broadcastJDBCDAO  = new BroadcastJDBCDAO();
//		TestBroadcastJDBCDAO testJDBCDAO = new TestBroadcastJDBCDAO();
//		insert
		BroadcastVO broadcastVOtest = new BroadcastVO();
		broadcastVOtest.setMsgID("MSG002");
		broadcastVOtest.setMemID("M001");
		broadcastVOtest.setMessage("天氣冷了，記得保暖(測試用)");
		broadcastVOtest.setConfirmed(0);
//		System.out.println(broadcastVOtest);
		broadcastJDBCDAO.insert(broadcastVOtest);
		System.out.println("insert success~");
	}
}
