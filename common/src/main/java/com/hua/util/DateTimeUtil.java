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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hua.constant.Constant;
import com.hua.constant.DateTimeFormat;
import com.hua.constant.FormatConstant;

/**
 * 描述: 日期/时间/时间戳 工具类
 *日历/日期/时间/时区 统一封装在此类，对外提供服务
 * 
 * @author qye.zheng
 * DateTimeUtil
 */
public final class DateTimeUtil extends org.apache.commons.lang3.time.DateUtils {

	protected static final Logger log = LogManager.getLogger(DateTimeUtil.class.getName());
	
	/**
	 * 1.毫秒数
	 * 起点: 1970-01-01, 00:00:00 GMT+0 (Greenwich Mean Time 格林威治标准时间)
	 * 正数: 之后的日期时间
	 * 负数: 之前的日期时间
	 * 
	 * 
	 日期、时间、时间戳，统一称呼为时间(time)
	 
	 时间有多种类型，包含有 世纪/年/月/日/星期/时/分/秒/毫秒/纳秒/时区/日历////////////////
	 
	 1) 日期  yyyy-MM-dd
	 
	 2) 时间  HH:mm:ss.SSS
	 
	 3) 时区
	 
	 4) 世纪
	 
	 5) 日历
	 
	 */
	
	// 1分钟 毫秒数
	public static final long MILLISECOND_PER_MINUTE = 60L * 1000;
	// 1小时 毫秒数
	public static final long MILLISECOND_PER_HOUR = 60L * MILLISECOND_PER_MINUTE;
	// 1天 毫秒数
	public static final long MILLISECOND_PER_DAY = 24L * MILLISECOND_PER_HOUR;
	// 1周 毫秒数
	public static final long MILLISECOND_PER_WEEK = 7L * MILLISECOND_PER_DAY;
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 * 
	 */
	private DateTimeUtil() {}
	
	/**
	 * 
	 * 描述: 获取当前日期 java.sql.Date
	 * 只含日期，不含时间
	 * 
	 * @author qye.zheng
	 * @return
	 */
	public static final java.sql.Date getDate() {
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
	public static final java.sql.Time getTime() {
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		
		return new java.sql.Time(calendar.getTimeInMillis());
	}
	
	/**
	 * 
	 * 描述: 获取当前日期时间
	 * 日期 + 时间 (java.util.Date)
	 * @author  qye.zheng
	 * 
	 * @return
	 */
	public static final java.util.Date getDateTime()
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
	public static final java.sql.Timestamp getTimestamp()
	{
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		
		return new java.sql.Timestamp(calendar.getTimeInMillis());
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
	public static final String getTsString()
	{
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		final DateFormat dateFormat = new SimpleDateFormat(FormatConstant.TIMESTAMP_FORMAT_yyyyMMddHHmmssSSS);
		final Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
		
		return dateFormat.format(timestamp);
	}
	
	/**
	 * 
	 * 描述:  获取当前时间毫秒数
	 * 从[起止时间] 到 [当前时间]的毫秒数
	 * 
	 * @author qye.zheng
	 * @return
	 */
	public static final long getTimeInMillis() {
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = Calendar.getInstance();
		
		return calendar.getTimeInMillis();
	}
	
	/**
	 * 
	 * 描述:  获取Unix timestamp (秒数)
	 * 距离1970-01-01 00:00:00 的秒数
	 * @author qye.zheng
	 * @return
	 */
	public static final long getUnixTs() {
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
	public static final TimeZone getTimeZone()
	{
		return TimeZone.getDefault();
	}
	
	/**
	 * 
	 * 描述: 获取id获取时区
	 * @author  qye.zheng
	 * @param id 时区id，例如 中国东八区( id == GMT+8:00 或 GMT+8 )
	 * @return
	 */
	public static final TimeZone getTimeZone(final String id)
	{
		return TimeZone.getTimeZone(id);
	}
	
	/**
	 * 
	 * 描述: 获取中国所在的时区
	 * @author  qye.zheng
	 * @param id 时区id，例如 中国东八区( id == GMT+8:00 或 GMT+8 )
	 * @return
	 */
	public static final TimeZone getChinaTimeZone()
	{
		return TimeZone.getTimeZone(Constant.TIME_ZONE_ID_CHINA);
	}
	
	/**
	 * 
	 * 描述: 获取中时区 (格林威治)
	 * 毫秒数从 中时区 (格林威治) 1970-01-01 00:00:00.000 算起
	 * @author  qye.zheng
	 * @return
	 */
	public static final TimeZone getMedialTimeZone()
	{
		return TimeZone.getTimeZone(Constant.TIME_ZONE_ID_MEDIAL);
	}
	
	/**
	 * 
	 * 描述: 获取当前时区最新日历 
	 * @author  qye.zheng
	 * 
	 * @return
	 */
	public static final Calendar getCalendar()
	{
		// 每次都获取一个最新的日历 - 当前时间
		return Calendar.getInstance();
	}
	
	/**
	 * 
	 * 描述: 获取中时区(格林威治) 最新日历 
	 * @author  qye.zheng
	 * 
	 * @return
	 */
	public static final Calendar getGMTCalendar()
	{
		// 每次都获取一个最新的日历 - 当前时间
		return Calendar.getInstance(getMedialTimeZone());
	}
	
	/**
	 * 
	 * 描述: 获取当前 世纪值 
	 * @author  qye.zheng
	 * @return
	 */
	public static final int getCentury()
	{
		// 每次都获取一个最新的日历 - 当前时间
		final Calendar calendar = getCalendar();
		final int year = calendar.get(Calendar.YEAR);
		// 100 年为一个世纪，世纪值从1开始
		
		return year / 100 + Constant.ONE;
	}
	
	/**
	 * 
	 * 描述: 是否是闰年 
	 * @see IsoChronology.isLeapYear 
	 * @author  qye.zheng
	 * @param year
	 * @return
	 */
	public static final boolean isLeapYear(final int year)
	{
		/*
		 * IsoChronology.isLeapYear: (被4整除) 且 (不能被100整除 或 被400整除)
		 * 0 == year % 4 等同于 0 == (year & 3)
		 */
		/*
		 满足如下条件之一，即为闰年
		1)  普通闰年: 能被4整除且又不能被100整除
		 2) 世纪闰年: 能被400整除
		 */
		boolean flag = false;
		if ((0 == year % 4) && (0 != year % 100 || 0 == year % 400)) 
		{ // 1) 被4整除 2) 不能被100整除 或被400整除
			flag = true;
		}
		
		return flag;
	}
	
	/**
	 * 
	 * @description 是否为平年
	 * @param year
	 * @return
	 * @author qianye.zheng
	 */
	public static final boolean isNonleapYear(final int year) {
		return !isLeapYear(year);
	}
	
	/**
	 * 
	 * 描述: 获取当前年份
	 * @author  qye.zheng
	 * @return
	 */
	public static final int getYear()
	{
		return getCalendar().get(Calendar.YEAR);
	}
	
	/**
	 * 
	 * 描述: 获取年份
	 * @author  qye.zheng
	 * @param date
	 * @return
	 */
	public static final int getYear(final java.sql.Date date)
	{
		 final Calendar.Builder builder = new Calendar.Builder();
		 builder.setInstant(date);
		
		return builder.build().get(Calendar.YEAR);
	}
	
	/**
	 * 
	 * 描述: 获取年份
	 * @author  qye.zheng
	 * @param dateTime
	 * @return
	 */
	public static final int getYear(final java.util.Date dateTime)
	{
		 final Calendar.Builder builder = new Calendar.Builder();
		 builder.setInstant(dateTime);
		
		return builder.build().get(Calendar.YEAR);
	}
	
	/**
	 * 
	 * 描述: 获取年份
	 * @author  qye.zheng
	 * @param timestamp
	 * @return
	 */
	public static final int getYear(final java.sql.Timestamp timestamp)
	{
		 final Calendar.Builder builder = new Calendar.Builder();
		 builder.setInstant(timestamp);
		
		return builder.build().get(Calendar.YEAR);
	}
	
	/**
	 * 
	 * 描述: 获取当前月份数( [1 - 12] )
	 * @author  qye.zheng
	 * 
	 * @param date
	 * @return
	 */
	public static final int getMonth()
	{
		// 获取的月份基数是从0开始的，例如 7月份，返回数字 6, 因此数字需要+1
		return getCalendar().get(Calendar.MONTH) + Constant.ONE;
	}
	
	/**
	 * 
	 * 描述: 获取月份数( [1 - 12] )
	 * @author  qye.zheng
	 * 
	 * @param date
	 * @return
	 */
	public static final int getMonth(final java.sql.Date date)
	{
		 final Calendar.Builder builder = new Calendar.Builder();
		 builder.setInstant(date);
		// 获取的月份基数是从0开始的，例如 7月份，返回数字 6, 因此数字需要+1
		return builder.build().get(Calendar.MONTH) + Constant.ONE;
	}
	
	/**
	 * 
	 * 描述: 获取月份数( [1 - 12] )
	 * @author  qye.zheng
	 * 
	 * @param dateTime
	 * @return
	 */
	public static final int getMonth(final java.util.Date dateTime)
	{
		 final Calendar.Builder builder = new Calendar.Builder();
		 builder.setInstant(dateTime);
		// 获取的月份基数是从0开始的，例如 7月份，返回数字 6, 因此数字需要+1
		return builder.build().get(Calendar.MONTH) + Constant.ONE;
	}
	
	/**
	 * 
	 * 描述: 获取月份数( [1 - 12] )
	 * @author  qye.zheng
	 * 
	 * @param timestamp
	 * @return
	 */
	public static final int getMonth(final java.sql.Timestamp timestamp)
	{
		 final Calendar.Builder builder = new Calendar.Builder();
		 builder.setInstant(timestamp);
		// 获取的月份基数是从0开始的，例如 7月份，返回数字 6, 因此数字需要+1
		return builder.build().get(Calendar.MONTH) + Constant.ONE;
	}
	
	/**
	 * 
	 * 描述: 获取当前月的一天 [1, 31]
	 * @author  qye.zheng
	 * @param date
	 * @return
	 */
	public static final int getDayOfMonth()
	{
		// 每次都获取一个最新的日历 - 当前时间
		return  getCalendar().get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 
	 * 描述: 获取某月的一天  [1, 31]
	 * @author  qye.zheng
	 * @param date
	 * @return
	 */
	public static final int getDayOfMonth(final java.sql.Date date)
	{
		 final Calendar.Builder builder = new Calendar.Builder();
		 builder.setInstant(date);
		 
		return builder.build().get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 
	 * 描述: 获取某月的一天  [1, 31]
	 * @author  qye.zheng
	 * @param date
	 * @return
	 */
	public static final int getDayOfMonth(final java.util.Date dateTime)
	{
		 final Calendar.Builder builder = new Calendar.Builder();
		 builder.setInstant(dateTime);
		 
		return builder.build().get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 
	 * 描述: 获取某月的一天  [1, 31]
	 * @author  qye.zheng
	 * @param date
	 * @return
	 */
	public static final int getDayOfMonth(final java.sql.Timestamp timestamp)
	{
		 final Calendar.Builder builder = new Calendar.Builder();
		 builder.setInstant(timestamp);
		 
		return builder.build().get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 
	 * 描述: 获取当前年的一天 
	 * @author  qye.zheng
	 * @return
	 */
	public static final int getDayOfYear()
	{
		// 每次都获取一个最新的日历 - 当前时间
		return  getCalendar().get(Calendar.DAY_OF_YEAR);
	}
	
	/**
	 * 
	 * 描述: 获取某年的一天 [1, 366]
	 * @author  qye.zheng
	 * @param date
	 * @return
	 */
	public static final int getDayOfYear(final java.sql.Date date)
	{
		 final Calendar.Builder builder = new Calendar.Builder();
		 builder.setInstant(date);
		 
		return builder.build().get(Calendar.DAY_OF_YEAR);
	}
	
	/**
	 * 
	 * 描述: 获取某年的一天  [1, 366]
	 * @author  qye.zheng
	 * @param dateTime
	 * @return
	 */
	public static final int getDayOfYear(final java.util.Date dateTime)
	{
		 final Calendar.Builder builder = new Calendar.Builder();
		 builder.setInstant(dateTime);
		 
		return builder.build().get(Calendar.DAY_OF_YEAR);
	}
	
	/**
	 * 
	 * 描述: 获取某年的一天  [1, 366]
	 * @author  qye.zheng
	 * @param timestamp
	 * @return
	 */
	public static final int getDayOfYear(final java.sql.Timestamp timestamp)
	{
		 final Calendar.Builder builder = new Calendar.Builder();
		 builder.setInstant(timestamp);
		 
		return builder.build().get(Calendar.DAY_OF_YEAR);
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
	public static final String format(final java.sql.Date date)
	{
		return defaultDateFormat().format(date);
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
	public static final String format(final Time time)
	{
		return defaultTimeFormat().format(time);
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
	public static final String format(final java.util.Date dateTime)
	{
		return defaultDateTimeFormat().format(dateTime);
	}
	
	/**
	 * 
	 * 描述: 时间戳格式化
	 * yyyy-MM-dd HH:mm:ss.SSS
	 * @author  qye.zheng
	 * @param timestamp
	 * @return
	 */
	public static final String format(final Timestamp timestamp)
	{
		return defaultTimeFormat().format(timestamp);
	}
	
	/**
	 * 
	 * 描述: 根据 yyyy-MM-dd 将[日期字符串] 转成 [日期对象]
	 * @author  qye.zheng
	 * @param dateStr
	 * @return
	 */
	public static final java.sql.Date parseDate(final String dateStr)
	{
		java.sql.Date resultDate = null;
		try
		{
			resultDate = new java.sql.Date(defaultDateFormat().parse(dateStr).getTime());
		} catch (ParseException e)
		{
			log.error("将[日期字符串]: {} 转成 [日期对象] 异常: {}", dateStr, e);
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
	public static final java.sql.Time parseTime(final String timeStr)
	{
		java.sql.Time resultTime = null;
		try
		{
			resultTime = new java.sql.Time(defaultTimeFormat().parse(timeStr).getTime());
		} catch (ParseException e)
		{
			log.error("将[时间字符串]: {} 转成 [时间对象] 异常: {}", timeStr, e);
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
	public static final java.util.Date parseDateTime(final String dateTimeStr)
	{
		java.util.Date resultDateTime = null;
		try
		{
			resultDateTime = defaultDateTimeFormat().parse(dateTimeStr);
		} catch (ParseException e)
		{
			log.error("将[日期时间字符串]: {} 转成 [日期时间对象] 异常: {}", dateTimeStr, e);
		}
		
		return resultDateTime;
	}
	
	/**
	 * 
	 * @description 转换时间
	 * @param date 时间
	 * @return
	 * @author qianye.zheng
	 */
	public static final LocalTime fromTime(final String time){
		return LocalTime.parse(time, DateTimeFormatter.ofPattern(FormatConstant.TIME_FORMAT_HH_mm_ss));
	}
	
	/**
	 * 
	 * @description 转换日期
	 * @param date 日期
	 * @return
	 * @author qianye.zheng
	 */
	public static final LocalDate fromDate(final String date) {
		return LocalDate.parse(date, DateTimeFormatter.ofPattern(FormatConstant.DATE_FORMAT_yyyy_MM_dd));
	}
	
	/**
	 * 
	 * @description 转换日期时间
	 * @param dateTime 日期时间
	 * @return
	 * @author qianye.zheng
	 */
	public static final LocalDateTime fromDateTime(final String dateTime) {
		return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss));
	}
	
	/**
	 * 
	 * 描述: 根据 yyyy-MM-dd HH:mm:ss.SSS 将 [时间戳字符串] 转成 [时间戳对象]
	 * @author  qye.zheng
	 * @param timestampStr
	 * @return
	 */
	public static final java.sql.Timestamp parseTimestamp(final String timestampStr)
	{
		java.sql.Timestamp resultTimestamp = null;
		try
		{
			resultTimestamp = new java.sql.Timestamp(defaultDateTimeFormat().parse(timestampStr).getTime());
		} catch (ParseException e)
		{
			log.error("将[时间戳字符串]: {} 转成 [时间戳对象] 异常: {}", timestampStr, e);
		}
		
		return resultTimestamp;
	}
	
	/**
	 * 
	 * @description 
	 * @param dateTime 日期时间
	 * @param format 格式
	 * @return
	 * @author qye.zheng
	 */
	public static final java.util.Date convertToDate(final String dateTime, final String format)
	{
		if (StringUtil.isEmpty(dateTime) || StringUtil.isEmpty(format))
		{
			return null;
		}
		try
		{
			return new SimpleDateFormat(format).parse(dateTime);
		} catch (Exception e)
		{
			log.error("将[日期时间字符串]: {} 转成格式: {} [日期时间对象] 异常: {}", dateTime, format, e);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @description 自动 日期时间转换 (支持多种自定义格式)
	 * @param value
	 * @return
	 * @author qianye.zheng
	 */
	public static final Date autoParse(final String value)
	{
		final int length = value.length();
		try
		{
			final DateTimeFormat[] values = DateTimeFormat.values();
			for (DateTimeFormat e : values) {
				if (length == e.getValue().length()) {
					
					return e.getDateFormat().parse(value);
				}
			}
		} catch (ParseException e)
		{
			log.error("自动转换 [字符串]: {} 转换[日期时间对象] 异常: {}", value, e);
		}
		
		return null;
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
	
	/**
	 * 
	 * @description 默认时间格式
	 * @return
	 * @author qianye.zheng
	 */
	private static final DateFormat defaultTimeFormat() {
		return new SimpleDateFormat(FormatConstant.TIME_FORMAT_HH_mm_ss);
	}
	
	/**
	 * 
	 * @description 默认日期格式
	 * @return
	 * @author qianye.zheng
	 */
	private static final DateFormat defaultDateFormat() {
		return new SimpleDateFormat(FormatConstant.DATE_FORMAT_yyyy_MM_dd);
	}
	
	/**
	 * 
	 * @description 默认日期时间格式
	 * @return
	 * @author qianye.zheng
	 */
	private static final DateFormat defaultDateTimeFormat() {
		return new SimpleDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
	}
	
	
	/* ================================================================================= */
	
	/**
	 * 
	 * @description 本地当前时间
	 * @return
	 * @author qianye.zheng
	 */
	public static final LocalTime localTime() {
		return LocalTime.now();
	}
	
	/**
	 * 
	 * @description 本地当前日期
	 * @return
	 * @author qianye.zheng
	 */
	public static final LocalDate localDate() {
		return LocalDate.now();
	}
	
	/**
	 * 
	 * @description 本地当前日期时间
	 * @return
	 * @author qianye.zheng
	 */
	public static final LocalDateTime localDateTime() {
		return LocalDateTime.now();
	}

	/**
	 * 
	 * @description 获取当周的第一天（周日）的日期
	 * @return 当周的第一天（周日）的日期
	 * @author qianye.zheng
	 */
	public static final LocalDate getStartDateOfCurrentWeek(){
		return LocalDate.now().with(WeekFields.of(Locale.getDefault()).dayOfWeek(),1);
	}

	/**
	 * 
	 * @description 获取当周的最后一天（周六）的日期
	 * @return 当周的最后一天（周六）的日期
	 * @author qianye.zheng
	 */
	public static final LocalDate getEndDateOfCurrentWeek(){
		return LocalDate.now().with(WeekFields.of(Locale.getDefault()).dayOfWeek(),7);
	}

	/**
	 * 
	 * @description 获取当月第一天的日期
	 * @return
	 * @author qianye.zheng
	 */
	public static final LocalDate getStartDateOfCurrentMonth(){
		return LocalDate.now().withDayOfMonth(1);
	}

	/**
	 * 
	 * @description 获取当月最后一天的日期
	 * @return
	 * @author qianye.zheng
	 */
	public static final LocalDate getEndDateOfCurrentMonth(){
		return LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
	}

	/**
	 * 
	 * @description  根据给出的时间，获取其处于一年中的第几周
	 * @param accessor
	 * @return 一年中的第几周 [1,52]
	 * @author qianye.zheng
	 */
	public static final Integer getWeekOfYear(final TemporalAccessor accessor) {
		int weekNum = accessor.get(WeekFields.of(Locale.getDefault()).weekOfYear());
		return weekNum > 52 ? weekNum - 52 : weekNum;
	}

	/**
	 * 获取给定LocalDateTime的毫秒数
	 * @param time 时间
	 * @return the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this date
	 */
	public static final long getTime(final LocalDateTime time) {
		return java.util.Date.from(time.atZone(ZoneId.systemDefault()).toInstant()).getTime();
	}

	
}
