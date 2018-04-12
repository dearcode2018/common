/**
 * ExceptionUtil.java
 * @author qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.util;

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
	public static String getAllCauseInfo(final Throwable throwable)
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
}
