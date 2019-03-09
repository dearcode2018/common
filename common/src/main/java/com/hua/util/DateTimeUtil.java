/**
 * 描述: 
 * DateTimeUtil.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.hua.constant.Constant;
import com.hua.constant.FormatConstant;

/**
 * 描述: 日期/时间/时间戳 工具类
 *日历/日期/时间/时区 统一封装在此类，对外提供服务
 * 
 * @author qye.zheng
 * DateTimeUtil
 */
public final class DateTimeUtil extends org.apache.commons.lang3.time.DateUtils {

	/**
	 日期、时间、时间戳，统一称呼为时间(time)
	 
	 时间有多种类型，包含有 世纪/年/月/日/星期/时/分/秒/毫秒/纳秒/时区/日历////////////////
	 
	 1) 日期  yyyy-MM-dd
	 
	 2) 时间  HH:mm:ss.SSS
	 
	 3) 时区
	 
	 4) 世纪
	 
	 5) 日历
	 
	 
	 */
	
	// 1分钟 毫秒数
	public final static long MILLISECOND_PER_MINUTE = 60L * 1000L;
	// 1分钟 毫秒数
	public final static long MILLISECOND_PER_HOUR = 60L * MILLISECOND_PER_MINUTE;
	// 1分钟 毫秒数
	public final static long MILLISECOND_PER_DAY = 24L * MILLISECOND_PER_HOUR;
	public final static long MILLISECOND_PER_WEEK = 7L * MILLISECOND_PER_DAY;
	
	public static DateFormat dateFormat;
	
	// 日历对象 (不同公共化，一旦设置为公共，日历对象伴随类加载完成后，将永远保持不变)
	//private static Calendar calendar = Calendar.getInstance();
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 * 
	 */
	private DateTimeUtil()
	{
	}
	
	/**
	 * 
	 * 描述: 获取当前日期 java.sql.Date
	 * 只含日期，不含时间
	 * 
	 * @author qye.zheng
	 * @return
	 */
	public static java.sql.Date getDate() {
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		
		return new java.sql.Date(calendar.getTimeInMillis());
	}
	
	/**
	 * 
	 * 描述:  获取当前时间数值
	 * 
	 * @author qye.zheng
	 * @return
	 */
	public static Time getTime() {
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		
		return new Time(calendar.getTimeInMillis());
	}
	
	/**
	 * 
	 * 描述: 获取当前日期时间
	 * 日期 + 时间 (java.util.Date)
	 * @author  qye.zheng
	 * 
	 * @return
	 */
	public static java.util.Date getDateTime()
	{
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		
		return calendar.getTime();
	}
	
	/**
	 * 
	 * 描述: 获取当前时间戳
	 * java.sql.Timestamp
	 * @author  qye.zheng
	 * 
	 * @return
	 */
	public static Timestamp getTimestamp()
	{
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		
		return new Timestamp(calendar.getTimeInMillis());
	}
	
	/**
	 * 
	 * 描述: 获取当前时间戳字符串
	 * yyyyMMddHHmmssSSS
	 * 为了防止重名，可作为文件名后缀的某部分
	 * java.sql.Timestamp
	 * @author  qye.zheng
	 * 
	 * @return
	 */
	public static String getTsString()
	{
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		dateFormat = new SimpleDateFormat(FormatConstant.TIMESTAMP_FORMAT_yyyyMMddHHmmssSSS);
		final Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
		
		return dateFormat.format(timestamp);
	}
	
	/**
	 * 
	 * 描述:  获取当前时间数值
	 * 从[起止时间] 到 [当前时间]的毫秒数
	 * 
	 * @author qye.zheng
	 * @return
	 */
	public static long getTimeInMillis() {
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		
		return calendar.getTimeInMillis();
	}
	
	
	/**
	 * 
	 * 描述:  获取Unix timestamp
	 * 距离1970-01-01 00:00:00 的秒数
	 * @author qye.zheng
	 * @return
	 */
	public static long getUnixTs() {
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		//  距离1970-01-01 00:00:00.000 的毫秒数
		final long millis = calendar.getTimeInMillis();
		
		// 除以 1000 计算出 距离1970-01-01 00:00:00 的秒数
		return millis / 1000;
	}	
	
	/**
	 * 
	 * 描述: 获取当前所在的时区
	 * @author  qye.zheng
	 * 
	 * @return
	 */
	public static TimeZone getTimeZone()
	{
		final TimeZone timeZone = TimeZone.getDefault();
		
		return timeZone;
	}
	
	/**
	 * 
	 * 描述: 获取id获取时区
	 * @author  qye.zheng
	 * 
	 * @param id 时区id，例如 中国东八区( id == GMT+8:00 或 GMT+8 )
	 * @return
	 */
	public static TimeZone getTimeZone(final String id)
	{
		final TimeZone timeZone = TimeZone.getTimeZone(id);
		
		return timeZone;
	}
	
	/**
	 * 
	 * 描述: 获取中国所在的时区
	 * @author  qye.zheng
	 * 
	 * @param id 时区id，例如 中国东八区( id == GMT+8:00 或 GMT+8 )
	 * @return
	 */
	public static TimeZone getChinaTimeZone()
	{
		final TimeZone timeZone = TimeZone.getTimeZone(Constant.TIME_ZONE_ID_CHINA);
		
		return timeZone;
	}
	
	/**
	 * 
	 * 描述: 获取当前最新日历 
	 * @author  qye.zheng
	 * 
	 * @return
	 */
	public static Calendar getCalendar()
	{
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		
		return calendar;
	}
	
	/**
	 * 
	 * 描述: 获取当前 世纪值 
	 * @author  qye.zheng
	 * @return
	 */
	public static int getCentury()
	{
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		final int year = calendar.get(Calendar.YEAR);
		// 100 年为一个世纪，世纪值从1开始
		final int result = year / 100 + Constant.ONE;
		
		return result;
	}
	
	/**
	 * 
	 * 描述: 是否是闰年 
	 * @author  qye.zheng
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(final int year)
	{
		/*
		 满足如下条件之一，即为闰年
		1)  能被4整除且又不能被100整除
		 2) 能直接被400整除
		 
		 */
		
		boolean flag = false;
		if ((Constant.ZERO == year % 4 && Constant.ZERO != year % 100) || (Constant.ZERO == year % 400))
		{
			flag = true;
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 获取当前年份
	 * @author  qye.zheng
	 * @return
	 */
	public static int getYear()
	{
		final Calendar calendar = Calendar.getInstance();
		final int year = calendar.get(Calendar.YEAR);
		
		return year;
	}
	
	/**
	 * 
	 * 描述: 获取年份
	 * @author  qye.zheng
	 * @param date
	 * @return
	 */
	public static int getYear(final java.sql.Date date)
	{
		final Calendar calendar = Calendar.getInstance();
		// 设置时间
		calendar.setTime(date);
		final int year = calendar.get(Calendar.YEAR);
		
		return year;
	}
	
	/**
	 * 
	 * 描述: 获取年份
	 * @author  qye.zheng
	 * @param dateTime
	 * @return
	 */
	public static int getYear(final java.util.Date dateTime)
	{
		final Calendar calendar = Calendar.getInstance();
		// 设置时间
		calendar.setTime(dateTime);
		final int year = calendar.get(Calendar.YEAR);
		
		return year;
	}
	
	/**
	 * 
	 * 描述: 获取年份
	 * @author  qye.zheng
	 * @param timestamp
	 * @return
	 */
	public static int getYear(final java.sql.Timestamp timestamp)
	{
		final Calendar calendar = Calendar.getInstance();
		// 设置时间
		calendar.setTime(timestamp);
		final int year = calendar.get(Calendar.YEAR);
		
		return year;
	}
	
	/**
	 * 
	 * 描述: 获取当前月份数( [1 - 12] )
	 * @author  qye.zheng
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth()
	{
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		// 获取的月份基数是从0开始的，例如 7月份，返回数字 6, 因此数字需要+1
		final int month = calendar.get(Calendar.MONTH) + Constant.ONE;
		
		return month;
	}
	
	/**
	 * 
	 * 描述: 获取月份数( [1 - 12] )
	 * @author  qye.zheng
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(final java.sql.Date date)
	{
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 获取的月份基数是从0开始的，例如 7月份，返回数字 6, 因此数字需要+1
		final int month = calendar.get(Calendar.MONTH) + Constant.ONE;
		
		return month;
	}
	
	/**
	 * 
	 * 描述: 获取月份数( [1 - 12] )
	 * @author  qye.zheng
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(final java.util.Date date)
	{
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 获取的月份基数是从0开始的，例如 7月份，返回数字 6, 因此数字需要+1
		final int month = calendar.get(Calendar.MONTH) + Constant.ONE;
		
		return month;
	}
	
	/**
	 * 
	 * 描述: 获取月份数( [1 - 12] )
	 * @author  qye.zheng
	 * 
	 * @param timestamp
	 * @return
	 */
	public static int getMonth(final java.sql.Timestamp timestamp)
	{
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(timestamp);
		// 获取的月份基数是从0开始的，例如 7月份，返回数字 6, 因此数字需要+1
		final int month = calendar.get(Calendar.MONTH) + Constant.ONE;
		
		return month;
	}
	
	/**
	 * 
	 * 描述: 获取当前月的一天 [1, 31]
	 * @author  qye.zheng
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth()
	{
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		final int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		return  day;
	}
	
	/**
	 * 
	 * 描述: 获取某月的一天  [1, 31]
	 * @author  qye.zheng
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(final java.sql.Date date)
	{
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		final int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		return  day;
	}
	
	/**
	 * 
	 * 描述: 获取某月的一天  [1, 31]
	 * @author  qye.zheng
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(final java.util.Date dateTime)
	{
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateTime);
		final int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		return  day;
	}
	
	/**
	 * 
	 * 描述: 获取某月的一天  [1, 31]
	 * @author  qye.zheng
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(final java.sql.Timestamp timestamp)
	{
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(timestamp);
		final int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		return  day;
	}
	
	/**
	 * 
	 * 描述: 获取当前年的一天 
	 * @author  qye.zheng
	 * @return
	 */
	public static int getDayOfYear()
	{
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		final int year = calendar.get(Calendar.DAY_OF_YEAR);
		
		return  year;
	}
	
	/**
	 * 
	 * 描述: 获取某年的一天 [1, 366]
	 * @author  qye.zheng
	 * @param date
	 * @return
	 */
	public static int getDayOfYear(final java.sql.Date date)
	{
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		final int year = calendar.get(Calendar.DAY_OF_YEAR);
		
		return  year;
	}
	
	/**
	 * 
	 * 描述: 获取某年的一天  [1, 366]
	 * @author  qye.zheng
	 * @param dateTime
	 * @return
	 */
	public static int getDayOfYear(final java.util.Date dateTime)
	{
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateTime);
		final int year = calendar.get(Calendar.DAY_OF_YEAR);
		
		return  year;
	}
	
	/**
	 * 
	 * 描述: 获取某年的一天  [1, 366]
	 * @author  qye.zheng
	 * @param timestamp
	 * @return
	 */
	public static int getDayOfYear(final java.sql.Timestamp timestamp)
	{
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(timestamp);
		final int year = calendar.get(Calendar.DAY_OF_YEAR);
		
		return  year;
	}
	
	/**
	 * 
	 * 描述: 日期格式化
	 * yyyy-MM-dd
	 * @author  qye.zheng
	 * 
	 * @param time
	 * @return
	 */
	public static String format(final java.sql.Date date)
	{
		dateFormat = new SimpleDateFormat(FormatConstant.DATE_FORMAT_yyyy_MM_dd);
		
		return dateFormat.format(date);
	}
	
	/**
	 * 
	 * 描述: 时间格式化
	 * HH:mm:ss 
	 * @author  qye.zheng
	 * 
	 * @param date
	 * @return
	 */
	public static String format(final Time time)
	{
		dateFormat = new SimpleDateFormat(FormatConstant.TIME_FORMAT_HH_mm_ss);
		
		return dateFormat.format(time);
	}
	
	/**
	 * 
	 * 描述: 日期时间格式化
	 * yyyy-MM-dd HH:mm:ss
	 * @author  qye.zheng
	 * 
	 * @param dateTime
	 * @return
	 */
	public static String format(final java.util.Date dateTime)
	{
		dateFormat = new SimpleDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
		
		return dateFormat.format(dateTime);
	}
	
	/**
	 * 
	 * 描述: 时间戳格式化
	 * yyyy-MM-dd HH:mm:ss.SSS
	 * @author  qye.zheng
	 * @param timestamp
	 * @return
	 */
	public static String format(final Timestamp timestamp)
	{
		dateFormat = new SimpleDateFormat(FormatConstant.TIMESTAMP_FORMAT_yyyy_MM_dd_HH_mm_ss_SSS);
		
		return dateFormat.format(timestamp);
	}
	
	/**
	 * 
	 * 描述: 根据 yyyy-MM-dd 将[日期字符串] 转成 [日期对象]
	 * @author  qye.zheng
	 * @param dateStr
	 * @return
	 */
	public static java.sql.Date parseDate(final String dateStr)
	{
		dateFormat = new SimpleDateFormat(FormatConstant.DATE_FORMAT_yyyy_MM_dd);
		java.sql.Date resultDate = null;
		try
		{
			resultDate = new java.sql.Date(dateFormat.parse(dateStr).getTime());
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		return resultDate;
	}
	
	/**
	 * 
	 * 描述: 根据 HH:mm:ss 将 [时间字符串] 转成 [时间对象]
	 * @author  qye.zheng
	 * @param timeStr
	 * @return
	 */
	public static java.sql.Time parseTime(final String timeStr)
	{
		dateFormat = new SimpleDateFormat(FormatConstant.TIME_FORMAT_HH_mm_ss);
		java.sql.Time resultTime = null;
		try
		{
			resultTime = new java.sql.Time(dateFormat.parse(timeStr).getTime());
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		return resultTime;
	}
	
	/**
	 * 
	 * 描述: 根据 yyyy-MM-dd HH:mm:ss 将 [日期时间字符串] 转成 [日期时间对象]
	 * @author  qye.zheng
	 * @param dateTimeStr
	 * @return
	 */
	public static java.util.Date parseDateTime(final String dateTimeStr)
	{
		dateFormat = new SimpleDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
		java.util.Date resultDateTime = null;
		try
		{
			resultDateTime = dateFormat.parse(dateTimeStr);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		return resultDateTime;
	}
	
	/**
	 * 
	 * 描述: 根据 yyyy-MM-dd HH:mm:ss.SSS 将 [时间戳字符串] 转成 [时间戳对象]
	 * @author  qye.zheng
	 * @param timestampStr
	 * @return
	 */
	public static java.sql.Timestamp parseTimestamp(final String timestampStr)
	{
		dateFormat = new SimpleDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
		java.sql.Timestamp resultTimestamp = null;
		try
		{
			resultTimestamp = new java.sql.Timestamp(dateFormat.parse(timestampStr).getTime());
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		return resultTimestamp;
	}
	
	/**
	 * 
	 * @description 
	 * @param date
	 * @param format
	 * @return
	 * @author qye.zheng
	 */
	public static Date stringToDate(String date, String format)
	{
		if (date == null || format == null || date == "")
		{
			return null;
		}
		try
		{
			return new SimpleDateFormat(format).parse(date);
		} catch (ParseException ex)
		{
			return null;
		}
	}
	
	/**
	 * 
	 * @description 
	 * @param value
	 * @return
	 * @author qianye.zheng
	 */
	public static Date parseStandardDate(String value)
	{
		Date date = null;
		int n = value.length();
		try
		{
			if (n >= "yyyy-MM-dd HH:mm:ss.S".length())
			{
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(value);
			} else if (n == "yyyy-MM-dd HH:mm:ss".length())
			{
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
			} else if (n == "yyyy-MM-dd HH:mm".length())
			{
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(value);
			} else if (n == "yyyy-MM-dd HH".length())
			{
				date = new SimpleDateFormat("yyyy-MM-dd HH").parse(value);
			} else if (n == "yyyy-MM-dd".length())
			{
				date = new SimpleDateFormat("yyyy-MM-dd").parse(value);
			} else if (n == "yyyy-MM".length())
			{
				date = new SimpleDateFormat("yyyy-MM").parse(value);
			}
		} catch (ParseException e)
		{
			//Log log = LogFactory.getLog(DateTimeUtil.class.getName());
			//log.warn("Can not parse [" + value + "] to java.util.Date");
		}
		return date;
	}
	
	/**
	 * 
	 * @description 获取 [今天] 凌晨时间
	 * @return
	 * @author qianye.zheng
	 */
	public static final Date getTodayBeforeDawn()
	{
		final Calendar cal = getCalendar();
		// 时间清零
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}
	
	/**
	 * 
	 * @description 获取 [昨天] 凌晨时间
	 * @return
	 * @author qianye.zheng
	 */
	public static final Date getYesterdayBeforeDawn()
	{
		final Calendar cal = getCalendar();
		
		// 昨天
		cal.add(Calendar.DAY_OF_MONTH, -1);
		
		// 时间清零
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}
	
}
