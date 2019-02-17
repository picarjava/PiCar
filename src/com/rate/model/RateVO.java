package com.rate.model;

public class RateVO implements java.io.Serializable{
	
	private Integer rateID;
	private String rateName;
	private Double ratePrice;
	private Integer rateBasic;
	
	public Integer getRateID() {
		return rateID;
	}
	
	public void setRateID(Integer rateID) {
		this.rateID = rateID;
	}
	
	public String getRateName() {
		return rateName;
	}
	
	public void setRateName(String rateName) {
		this.rateName = rateName;
	}
	
	public Double getRatePrice() {
		return ratePrice;
	}
	
	public void setRatePrice(Double ratePrice) {
		this.ratePrice = ratePrice;
	}
	
	public Integer getRateBasic() {
		return rateBasic;
	}
	
	public void setRateBasic(Integer rateBasic) {
		this.rateBasic = rateBasic;
	}
	
	
	
	
	

}
