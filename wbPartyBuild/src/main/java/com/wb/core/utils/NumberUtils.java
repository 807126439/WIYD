package com.wb.core.utils;

public class NumberUtils {

	/**
	 * 为数字前面填充0
	 * @param max		返回几位数
	 * @param number	数字值
	 * @return
	 * @author wb_java_zjr
	 */
	public static String convertNumber(int max,Long number){
		
		if(number == null){
			number = 0l;
		}
		number++;		
		int length = number.toString().length();
		if(length>max){
			return number.toString();
		}
		int cha = max-length;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cha; i++) {
			sb.append("0");
		}
		sb.append(number.toString());
		
		return sb.toString();
	}
	
	
}
