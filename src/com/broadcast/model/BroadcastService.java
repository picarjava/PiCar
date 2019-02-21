package com.broadcast.model;


import java.util.List;

public class BroadcastService {
	private BroadcastDAO_interface dao;

	//創建DAO 實體保存在DAO介面的實體變數
public BroadcastService() {
	dao = new BroadcastJNDIDAO();
		}
public BroadcastVO addBroadcast(String msgID,String memID , String message, Integer confirmed ) {
	BroadcastVO broadcastVO = new BroadcastVO( );

	//比較JDBCDAO。利用多型 將VO變數傳入參數 用VO.set存
	broadcastVO.setMsgID(msgID);
	broadcastVO.setMemID( memID);
	broadcastVO.setMessage(message);
	broadcastVO.setConfirmed(confirmed);
    dao.insert(broadcastVO);

	return broadcastVO;
}
public BroadcastVO updateDriver(String msgID,String memID , String message, Integer confirmed ) {
	
	BroadcastVO broadcastVO = new BroadcastVO( );
	broadcastVO.setMsgID(msgID);
	broadcastVO.setMemID( memID);
	broadcastVO.setMessage(message);
	broadcastVO.setConfirmed(confirmed);

	//
	
	dao.update(broadcastVO);
	return broadcastVO;
	
}

////keep for Struts2
//public void AddBroadcastVO(BroadcastVO broadcastVO) {
//dao.insert(broadcastVO);
//}
public List<BroadcastVO> getAll() {
	return dao.getAll();
}

public BroadcastVO getOneBroadcast(String broadcastVO) {
	return dao.findByPrimaryKey(broadcastVO);
}

public void deleteBroadcast(String broadcastVO) {
	dao.delete(broadcastVO);
}

}
