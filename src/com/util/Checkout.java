package com.util;

public class Checkout {
	
	//行車距離(公尺)以及訂單種類(0-6)，即回傳單筆訂單總金額($NT)
	public Integer checkout(int distance, int orderType) {
		int totalAmount=0;
		double baseFee=40; //基本費
		double y=24.5; //費率
		double discount=0.85;//折扣 
		distance=distance/1000; //換算公里
		
		switch(orderType){
			case 0: //一般叫車
				totalAmount= (int) (baseFee+y*distance);
			 break;
			case 3: //預約叫車
				totalAmount= (int) (baseFee+y*distance);
			 break;
			case 4: //長期預約
				totalAmount= (int) (baseFee+y*distance*discount);
			 break;
			case 5: //糾團叫車
				totalAmount= (int) (baseFee+y*distance);
			 break;
			case 6: //長期揪團
				totalAmount= (int) (baseFee+y*distance*discount);
			 break;
			 default:
				 System.out.println("訂單種類輸入錯誤");
		}
		System.out.println("總金額="+totalAmount);
		return totalAmount;
	}
	
//	public static void main(String[]args){
//		Checkout checkout=new Checkout();
//		checkout.checkout(2000, 1);
//	}

}
