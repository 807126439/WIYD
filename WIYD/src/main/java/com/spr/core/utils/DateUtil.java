package com.spr.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {

	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToStrByPattern(Date date, String pattern) {
		// yyyy-MM-dd HH:mm:ss
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		String str = format.format(date);

		return str;

	}

	/**
	 * 日期转换成字符串
	 * 
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

			result = format.parse(str);

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
	 * 
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
	 * 
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
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDateByShort(String str) {
		if (StringUtils.isBlank(str)) {
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
	 * 
	 * @return
	 */
	public static String getNowYear() {

		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		String str = format.format(new Date());
		return str;

	}

	/**
	 * 比较两个日期是否相差在范围内
	 * 
	 * @param late
	 * @param early
	 * @param range
	 * @return
	 * @author wb_java_zjr
	 */
	public static boolean checkDatesIsInRange(Date late, Date early, Long range) {

		long cha = late.getTime() - early.getTime();

		if (cha <= range) {
			return true;
		} else {

			return false;
		}

	}

	/**
	 * 加减法日期
	 * 
	 * @param date
	 *            日期
	 * @param sl
	 *            加减量
	 * @return
	 */
	public static String countDate(Date date, int sl) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, sl);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		return df.format(calendar.getTime());

	}

	/**
	 * 根据月份加减日期
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static String countDateByMonth(Date date, int months) {

		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(date);
		aCalendar.add(Calendar.MONTH, months);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		return df.format(aCalendar.getTime());
	}

	public static Date plusDate(Date date, int sl) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, sl);

		return calendar.getTime();

	}

	/**
	 * 两个日期相减（oDate-fDate）
	 * 
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static int daysOfTwo(Date fDate, Date oDate) {

		Calendar aCalendar = Calendar.getInstance();

		aCalendar.setTime(fDate);

		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

		aCalendar.setTime(oDate);

		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

		return day2 - day1;

	}

	public static int daysBetween(Date smdate, Date bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 设置日期的天数
	 * 
	 * @param day
	 * @param date
	 * @return
	 */
	public static Date getDateBySetDay(int day, Date date) {

		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(date);
		aCalendar.set(Calendar.DAY_OF_MONTH, day);

		return aCalendar.getTime();
	}

	/**
	 * 获取星期中的天数：星期日=1，星期一=2，星期二=3，星期三=4，星期四=5，星期五=6，星期六=7
	 * 
	 * @param date
	 * @return
	 */
	public static int dayInWeek(Date date) {
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(date);
		return aCalendar.get(Calendar.DAY_OF_WEEK);
	}

}
