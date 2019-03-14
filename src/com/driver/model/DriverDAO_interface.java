package com.driver.model;

import java.util.List;

public interface DriverDAO_interface {
	
	
	
    public void insert(DriverVO driverVO);
    public void update(DriverVO driverVO);
    /*delete建構子argument是放欲刪除資料的PK*/
    public void updatedri(DriverVO driverVO);
    public void delete(String driver_id);
    public DriverVO findByPrimaryKey(String driverID);
    public List<DriverVO> getAll(); 
    public DriverVO findByMemID(String memID);
    public DriverVO findDriverByMemID(String memID);
    public DriverVO updateBanned(String driverID);
    public void updatePermitted(DriverVO driverVO);
  //小編更新司機評價
  	public void updateDriverRate(int score,String driverID) ;
//	萬用複合查詢(傳入參數型別Map)(回傳List)
	
//	public List<DriverVO> getAll(Map<String, String> map);


}
