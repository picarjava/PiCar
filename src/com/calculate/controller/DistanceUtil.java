package com.calculate.controller;

import java.text.DecimalFormat;

/**
 * @author Lix@jchvip.com
 * @date 2018/6/7 10:46
 */
public class DistanceUtil {

	public static void main(String[] args) {
		// 根據兩點間的經緯度計算距離，單位：km
		Double s = algorithm(115.21221, 1.5, 114.21221, 0);
		System.out.println(s);
	}

	private static double rad(double d) {
		return d * Math.PI / 180.00; // 角度轉換成弧度
	}

	/*
	 * 根據經緯度計算兩點之間的距離（單位米）
	 */
	public static Double algorithm(double longitude1, double latitude1, double longitude2, double latitude2) {

		double lat1 = rad(latitude1); // 乘客的緯度
		double lat2 = rad(latitude2);	//司機的緯度
		double lon1 = rad(longitude1); // 乘客的經度
		double lon2 = rad(longitude2); //司機的經度
 
		double a = lat1 - lat2;// 兩點緯度之差
		double b = lon1 - lon2 ;  // 經度之差
		double s = 2 * Math.asin(Math

				.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));// 計算兩點距離的公式
		s = s * 6378137.0;// 弧長乘地球半徑（半徑為米）
		s = Math.round(s * 10000d) / 10000d;// 精確距離的數值
		s = s / 1000;// 將單位轉換為km，如果想得到以米為單位的資料 就不用除以1000
						// 四捨五入 保留一位小數
		DecimalFormat df = new DecimalFormat("#.0");

		return Double.valueOf(df.format(s));

	}
}
