/**
 * FormatUtil.java
 * @author qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.util;

import java.text.DecimalFormat;

import com.hua.constant.Constant;

/**
 * FormatUtil
 * 描述: 格式 工具类
 * @author qye.zheng
 * 
 */
public final class FormatUtil
{
	
	/* 格式化excel中 数值类型为整数，消除小数点 */
	public static final DecimalFormat decimalFormat = new DecimalFormat("#");
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	private FormatUtil()
	{
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * @param target 目标数字
	 * @param maxLength 最大长度
	 * @return
	 */
	public static String addZero(final int target, final int maxLength)
	{
		// 强转为long型
		final long temp = (long) target;
		
		return addZero(temp, maxLength);
	}
	
	/**
	 * 
	 * 描述: 补0
	 * @author qye.zheng
	 * @param target 目标数字
	 * @param maxLength 最大长度
	 * @return
	 */
	public static String addZero(final long target, final int maxLength)
	{
		String result = null;
		result = String.valueOf(target);
		if (result.length() >= maxLength)
		{
			// 长度已满足总位数
			
			return result;
		}
		final StringBuilder builder = StringUtil.getBuilder();
		// 0 的最多个数 (总位数 减一)
		//final int maxZero = digit - 1;
		final int wantAdd = maxLength - result.length();
		for (int i = 0; i < wantAdd; i++)
		{
			builder.append(Constant.ZERO);
		}
		// 加上目标部分
		builder.append(result);
		result = builder.toString();
		
		return result;
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * @param target
	 * @param maxLength
	 * @return
	 */
	public static String addZero(final String target, final int maxLength)
	{
		if (target.length() >= maxLength)
		{
			// 长度已满足总位数
			
			return target;
		}
		String result = null;
		final StringBuilder builder = StringUtil.getBuilder();
		final int wantAdd = maxLength - target.length();
		for (int i = 0; i < wantAdd; i++)
		{
			builder.append(Constant.ZERO);
		}
		// 加上目标部分
		builder.append(target);
		result = builder.toString();
		
		return result;
	}
	
	/**
	 * 
	 * @description 将小数格式化为整数，用于读取excel单元格小数数据，输出整数部分
	 * @param obj
	 * @return
	 * @author qye.zheng
	 */
	public static final String format(final Object obj)
	{
		String result = null;
		if (obj instanceof String)
		{
			result = (String) obj;
		} else
		{
			if (null != obj)
			{
				result = decimalFormat.format(obj);
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @description 字节数组转成16进制字符串
	 * @param array
	 * @param upperCase 是否转成大写
	 * @return
	 * @author qye.zheng
	 */
	public static final String parseByte2HexString(final byte[] array, final boolean upperCase)
	{
		final StringBuilder builder = StringUtil.getBuilder();
		String hex = null;
		int value = 0;
		for (int i = 0; i < array.length; i++)
		{
			value = array[i] & 0XFF;
			hex = Integer.toHexString(value);
			if (hex.length() == 1)
			{
				// 补 0
				hex = '0' + hex;
			}
			// 大小写转换
			if (upperCase)
			{
				builder.append(hex.toUpperCase());
			} else
			{
				builder.append(hex.toLowerCase());
			}
		}
		
		return builder.toString();
	}
	
	/**
	 * 
	 * @description 字节数组转成16进制字符串
	 * @param array
	 * @return
	 * @author qye.zheng
	 */
	public static final String parseByte2HexString(final byte[] array)
	{
		return parseByte2HexString(array, true);
	}
	
	/**
	 * 
	 * @description 16进制字符串转成字节数组
	 * @param hexString
	 * @return
	 * @author qye.zheng
	 */
	public static final byte[] parseHexString2Byte(final String hexString)
	{
		byte[] array = null;
		if (StringUtil.isEmpty(hexString))
		{
			return array;
		}
		array = new byte[hexString.length() / 2];
		int high = -1;
		int low = -1;
		for (int i = 0; i < hexString.length() / 2; i++)
		{
			// 高位 A(高位)B(低位)
			high = Integer.parseInt(hexString.substring(i * 2,  i * 2 + 1), 16);
			// 低位
			low = Integer.parseInt(hexString.substring(i * 2 + 1,  i * 2 + 2), 16);
			//
			array[i] = (byte) (high * 16 + low);
		}
		
		return array;
	}
}
