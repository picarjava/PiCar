package com.driver.model;

import java.util.List;

public interface DriverDAO_interface {
	
	
	
    public void insert(DriverVO driverVO);
    public void update(DriverVO driverVO);
    public void delete(Integer driverVO);
    public DriverVO findByPrimaryKey(Integer empno);
    public List<DriverVO> getAll(); 



}
