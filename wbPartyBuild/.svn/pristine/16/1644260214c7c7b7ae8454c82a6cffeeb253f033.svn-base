package com.wb.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {

	
	
	/**
	* 日期转换成字符串
	* @param date
	* @return str
	*/
	public static String DateToStrByPattern(Date date,String pattern) {
	  //yyyy-MM-dd HH:mm:ss
	   SimpleDateFormat format = new SimpleDateFormat(pattern);
	   String str = format.format(date);
	   
	   
	   return str;	   
	   
	}
	
	
	
	/**
	* 日期转换成字符串
	* @param date
	* @return str
	*/
	public static String DateToStr(Date date) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String str = format.format(date);
	   return str;
	}
	
	
	public static Date Date2Date(Date date) {
		  
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		   String str = format.format(date);
		   Date result = null;
		   try {
			   
			   result =  format.parse(str);
			   
		   } catch (ParseException e) {
				
			   e.printStackTrace();
		   }
		   
		   return result;
	}
	
	
	public static String Date2Str(Date date) {
		  
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		   String str = format.format(date);
		   return str;
	}
	
	
	public static String Date2ChineseStr(Date date) {
		  
		   SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		   String str = format.format(date);
		   return str;
	}
	
	public static String Date2StrByMD(Date date) {
		  
	   SimpleDateFormat format = new SimpleDateFormat("MM-dd");
	   String str = format.format(date);
	   return str;
	}

	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date StrToDate(String str) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   Date date = null;
	   try {
	      date = format.parse(str);
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return date;
	}

	
	
	public static Date Str2Date(String str) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	   Date date = null;
	   try {
		   date = format.parse(str);
	   } catch (ParseException e) {
		   e.printStackTrace();
	   }
	   
	   return date;
   
	   
	}

	
	/**
	* 日期转换成字符串
	* @param date
	* @return str
	*/
	public static String DateToStrByShort(Object date) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	   String str = format.format(date);
	   return str;
	}

	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date StrToDateByShort(String str) {
	   if(StringUtils.isBlank(str)){
		  return null;
	   }
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	   Date date = null;
	   try {
	    date = format.parse(str.toString());
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return date;
	}
	
	/**
	 * 获取当前年份
	 * @return
	 */
	public static String getNowYear() {
		  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy");
	   String str = format.format(new Date());
	   return str;
	   
   }
	
	
	/**
	 * 比较两个日期是否相差在范围内
	 * @param late
	 * @param early
	 * @param range
	 * @return
	 * @author wb_java_zjr
	 */
	public static boolean checkDatesIsInRange(Date late,Date early,Long range){
		
		long cha = late.getTime() - early.getTime();
		
		if(cha<=range){
			return true;
		}else{
			
			return false;
		}
		
	}
}
