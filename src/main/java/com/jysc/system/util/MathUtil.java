package com.jysc.system.util;


public class MathUtil {
    
	/**
	 * Description:{ 保留两位小数 }
	 * @param num
	 * @return
	 */
	public static float twoDecimals(float num){
		try {
			num = (float) (Math.round(num * 100)/100.0);
		} catch (Exception e) {
			num = 0;
		}
		return num;
	}
	public static float twoDecimals(String num){
		if (num==null||num.equals("")) {
			num="0.00";
		}
		try {
			float number = Float.parseFloat(num);
			return twoDecimals(number);
		} catch (Exception e) {
			return 0;
		}
	}
	
//	public static void main(String[] args) {
//		System.out.println(twoDecimals(null));
//		System.out.println(twoDecimals(-2));
//	}
}