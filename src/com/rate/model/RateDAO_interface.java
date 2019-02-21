package com.rate.model;

import java.util.List;

public interface RateDAO_interface {

	public void insert(RateVO rateVO);
	public void update(RateVO rateVO);
	public void delete(Integer rateID);	
	public RateVO findByPrimaryKey(Integer rateID);
	public List<RateVO> getAll();
	public RateVO findPic(Integer rateID);
	public void insertPic(RateVO rateVO);

}
