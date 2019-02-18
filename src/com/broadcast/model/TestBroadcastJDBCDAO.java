package com.broadcast.model;

import java.io.IOException;
import java.util.List;

import com.broadcast.model.BroadcastVO;

public class TestBroadcastJDBCDAO {

	public static void main(String[]args)throws IOException{
		
		BroadcastJDBCDAO broadcastJDBCDAO  = new BroadcastJDBCDAO();
//		TestBroadcastJDBCDAO testJDBCDAO = new TestBroadcastJDBCDAO();
//		insert test_ok
//		BroadcastVO broadcastVOtest1 = new BroadcastVO();
//		broadcastVOtest1.setMsgID("MSG004");
//		broadcastVOtest1.setMemID("M001");
//		broadcastVOtest1.setMessage("天氣冷了，記得保暖(測試用)");
//		broadcastVOtest1.setConfirmed(0);
//		broadcastJDBCDAO.insert(broadcastVOtest1);
//		System.out.println("insert success~");
		
//		update ok
//		BroadcastVO broadcastVOtest2= new BroadcastVO();
//		broadcastVOtest2.setMsgID("MSG002");
//		broadcastVOtest2.setMemID("M002");
//		broadcastVOtest2.setMessage("測試推播資訊");
//		broadcastVOtest2.setConfirmed(1);
//		broadcastJDBCDAO.update(broadcastVOtest2);
//		System.out.println("broadcast has updated");
		///////////
//		delete test  ok
//		broadcastJDBCDAO.delete("MSG003");
//		System.out.println("broadcast has deleted");
		
//		search for one //ok
		BroadcastVO broadcastVOtest3= broadcastJDBCDAO.findByPrimaryKey("MSG002");
		System.out.println(broadcastVOtest3.getMsgID());
		System.out.println(broadcastVOtest3.getMemID());
		System.out.println(broadcastVOtest3.getMessage());
		System.out.println(broadcastVOtest3.getConfirmed());
		System.out.println("broadcast has searched");
//		
//		search for all //ok
//		List<BroadcastVO> list= broadcastJDBCDAO.getAll();
//		for(BroadcastVO broadcastVOtest4:list) {
//		System.out.println(broadcastVOtest4.getMsgID()+"，");
//		System.out.println(broadcastVOtest4.getMemID()+"，");
//		System.out.println(broadcastVOtest4.getMessage()+"，");
//		System.out.println(broadcastVOtest4.getConfirmed()+"，");
//		System.out.println("all of broadcast has searched");
//	
//	}
}
}
