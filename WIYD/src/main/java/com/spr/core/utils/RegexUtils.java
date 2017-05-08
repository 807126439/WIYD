package com.spr.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

	private final static String USER_NAME = "[a-zA-Z0-9_]{6,16}";
	private final static String PHONE = "13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}|17[0-9]{9}";
	public final static String USER_NAME_ERROR_TIP = "用户名长度允许6-18位,只能包含数字字母和下划线,不能包含特殊字符或中文";
	public final static String PHONE_ERROR_TIP = "手机号码格式不正确";
	
	
	/**
	 * 校验用户名
	 * @param username
	 * @return
	 */
	public static boolean checkUserName(String username){
		
		return username.matches(USER_NAME);
	}
	
	
	/**
	 * 校验手机号码
	 * @param phone
	 * @return
	 */
	public static boolean checkPhone(String phone){
		
		return phone.matches(PHONE);
	}
	
	public static boolean isNumeric(String str){ 
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
		}
	
}
