package com.groupOrder.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class GroupOrderService {
	private GroupOrderDAO_interface dao;

	public GroupOrderService() {
		dao = new GroupOrderDAO();
	}

	public GroupOrderVO addGroupOrder(String driverID, String memID, Integer state, Integer totalAmout, Date startTime,
			Date endTime, Double startLng, Double startLat, Double endLng, Double endLat, Integer orderType,
			Integer rate, String note

	) {
		GroupOrderVO groupOrderVO = new GroupOrderVO();
		groupOrderVO.setDriverID(driverID);
		groupOrderVO.setMemID(memID);
		groupOrderVO.setState(state);
		groupOrderVO.setTotalAmout(totalAmout);
		groupOrderVO.setStartTime(startTime);
		groupOrderVO.setEndTime(endTime);
		groupOrderVO.setStartLng(startLng);
		groupOrderVO.setStartLat(startLat);
		groupOrderVO.setEndLng(endLng);
		groupOrderVO.setEndLat(endLat);
		groupOrderVO.setOrderType(orderType);
		groupOrderVO.setRate(rate);
		groupOrderVO.setNote(note);
		dao.insert(groupOrderVO);
		return groupOrderVO;
	}

	public GroupOrderVO updategroupOrderVO(String driverID, String memID, Integer state, Integer totalAmout,
			Date startTime, Date endTime, Double startLng, Double startLat, Double endLng, Double endLat,
			Integer orderType, Integer rate, String note) {
		GroupOrderVO groupOrderVO = new GroupOrderVO();
		groupOrderVO.setDriverID(driverID);
		groupOrderVO.setMemID(memID);
		groupOrderVO.setState(state);
		groupOrderVO.setTotalAmout(totalAmout);
		groupOrderVO.setStartTime(startTime);
		groupOrderVO.setEndTime(endTime);
		groupOrderVO.setStartLng(startLng);
		groupOrderVO.setStartLat(startLat);
		groupOrderVO.setEndLng(endLng);
		groupOrderVO.setEndLat(endLat);
		groupOrderVO.setOrderType(orderType);
		groupOrderVO.setRate(rate);
		groupOrderVO.setNote(note);
		dao.update(groupOrderVO);
		return groupOrderVO;
	}

	public GroupOrderVO getOneGroupOrder(String groupOrderno) {
		return dao.findByPrimaryKey(groupOrderno);
	}

	public List<GroupOrderVO> getAll() {
		return dao.getAll();
	}

}
