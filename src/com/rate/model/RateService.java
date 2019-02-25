package com.rate.model;

public class RateService {
	
	private RateDAO_interface dao ;
	
	public RateService() {
		dao = new RateDAO();
	}
	
	public RateVO addRate(String rateName, Double ratePrice, Integer rateBasic) {
		
		RateVO rateVO = new RateVO();
		
		rateVO.setRateName(rateName);
		rateVO.setRatePrice(ratePrice);
		rateVO.setRateBasic(rateBasic);
		
		dao.insert(rateVO);
		
		return rateVO;
	}
	
	public RateVO updateRate(Integer rateID, String rateName, Double ratePrice, Integer rateBasic) {
		
		RateVO rateVO = new RateVO();
		
		rateVO.setRateID(rateID);
		rateVO.setRateName(rateName);
		rateVO.setRatePrice(ratePrice);
		rateVO.setRateBasic(rateBasic);
		
		dao.update(rateVO);
		
		return rateVO;
	}
	
	public RateVO getOneRate(Integer rateID) {
		return dao.findByPrimaryKey(rateID);
	}
	
	public void deleteRate(Integer rateID) {
		dao.delete(rateID);
	}
	
	
	
	
	
	
	
	
	
	
	
}
