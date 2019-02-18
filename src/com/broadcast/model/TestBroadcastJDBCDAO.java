package com.broadcast.model;

import java.io.IOException;

public class TestBroadcastJDBCDAO {

	public static void main(String[]args)throws IOException{
		
		BroadcastJDBCDAO broadcastJDBCDAO  = new BroadcastJDBCDAO();
//		TestBroadcastJDBCDAO testJDBCDAO = new TestBroadcastJDBCDAO();
//		insert test_ok
//		BroadcastVO broadcastVOtest1 = new BroadcastVO();
//		broadcastVOtest1.setMsgID("MSG002");
//		broadcastVOtest1.setMemID("M001");
//		broadcastVOtest1.setMessage("天氣冷了，記得保暖(測試用)");
//		broadcastVOtest1.setConfirmed(0);
//		broadcastJDBCDAO.insert(broadcastVOtest1);
//		System.out.println("insert success~");
		
//		update ok
		BroadcastVO broadcastVOtest2= new BroadcastVO();
		broadcastVOtest2.setMsgID("MSG002");
		broadcastVOtest2.setMemID("M002");
		broadcastVOtest2.setMessage("測試推播資訊");
		broadcastVOtest2.setConfirmed(1);
		broadcastJDBCDAO.update(broadcastVOtest2);
		System.out.println("broadcast has updated");
		///////////
//		delete test  ok
//		broadcastJDBCDAO.delete("MSG003");
//		System.out.println("broadcast has deleted");
		
	
	
	}
}
