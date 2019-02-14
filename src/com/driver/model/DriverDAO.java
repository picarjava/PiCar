package com.driver.model;

import java.util.List;

//import model.DAOInterface;

public class DriverDAO {
	
    public void insert(DriverVO driverVO);
    public void update(DriverVO driverVO);
    public void delete(Integer driverVO);
    public DriverVO findByPrimaryKey(Integer empno);
    public List<DriverVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 

	

}
