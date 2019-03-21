package com.singleOrder.model;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public interface SingleOrder_interface {
    SingleOrderVO findByPrimaryKey(String primaryKey);
    void insert(SingleOrderVO singleOrderVO);
    void update(SingleOrderVO singleOrderVO);
    void updateDriverIDAndStateByOrderID(String driverID, Integer state, String orderID);
    void updateDriverIDAndStateByOrderID(String driverID, Integer state, List<String> orderIDs);
    List<SingleOrderVO> getAll();
    List<SingleOrderVO> findByStateAndOrderType(Integer state, Integer orderType);
    void delete(String orderID);
    void insert(LinkedList<SingleOrderVO> singleOrderVOList); //長期訂單新增用
    int findRateAveByDriverID(String driverID);//小編司機查評價平均
    List<SingleOrderVO> findByStateAndDriverID(Integer State, String driverID);
  	HashSet<String> getRatedDrivers(); //小編得到已評價司機
	void update_state_to_delay();
	/*小編預約訂單排程器用*/
	public HashSet<SingleOrderVO> listAllUnpaidReservationOrder();//撈單程訂單:三天前 可撈單程
	public HashSet<SingleOrderVO> listEarlierPartUnpaidFromLongtermSets();//撈長期訂單1:三天前撈出長期單的前幾筆) 
	public HashSet<SingleOrderVO> listOneSetOfLongterm(Timestamp launchtime);//撈長期訂單2:再透過 撈長期第一筆LAUNCH_TIME ，撈此長期預約的整組
	List<String> getDelayOrder();//逾時訂單
	void updateOrderIDToDelay(String orderID);
} // interface SingleOrderInterface
