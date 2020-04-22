/**
 * ExceptionUtil.java
 * @author qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.util;

import java.util.Date;

import com.hua.constant.Constant;

/**
 * ExceptionUtil
 * 描述: 异常 工具类
 * @author qye.zheng
 * 
 */
public final class ExceptionUtil extends org.apache.commons.lang3.exception.ExceptionUtils
{
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	private ExceptionUtil()
	{
	}
	
	/**
	 * 
	 * @description 
	 * @param throwable
	 * @return
	 * @author qye.zheng
	 */
	public static final String getAllCauseInfo(final Throwable throwable)
	{
		if (null == throwable)
		{
			return null;
		}
		final StringBuilder builder = StringUtil.getBuilder();
		Throwable cause = throwable;
		while (null != cause)
		{
			builder.append(cause.toString());
			// 加一个换行
			builder.append(Constant.LINE_BREAK);
			// 获取链的上一个异常
			cause = cause.getCause();
		}
		
		return builder.toString();
	}
	
	/**
	 * 
	 * @description 获取异常踪迹
	 * @param throwable
	 * @author qianye.zheng
	 */
	public static final String getExceptionTrace(final Throwable throwable)
	{
		final StringBuilder builder = StringUtil.getBuilder();
		// 时间
		builder.append(DateTimeUtil.format(new Date()) + Constant.COLON + Constant.LINE_BREAK);
		builder.append(throwable.toString());
		final StackTraceElement[] traces = throwable.getStackTrace();
		if (null != traces)
		{
			for (StackTraceElement e : traces)
			{
				// 加一个换行
				builder.append(Constant.LINE_BREAK);
				builder.append(e.toString());
			}
		}
		
		return builder.toString();
	}
	
}
