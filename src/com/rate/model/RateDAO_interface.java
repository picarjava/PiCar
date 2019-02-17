package com.rate.model;

import java.util.List;

public interface RateDAO_interface {

	public abstract void insert(RateVO rateVO);
	public abstract void update(RateVO rateVO);
	public abstract void delete(Integer rateID);	
	public abstract RateVO findByPrimaryKey(Integer rateID);
	public abstract List<RateVO> getAll();

}
