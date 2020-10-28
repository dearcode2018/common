/**
 * 描述: 
 * StringUtil.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import com.hua.constant.Constant;
import com.hua.constant.RegexConstant;

/**
 * 描述: 字符串 工具类
 * 
 * @author qye.zheng
 * StringUtil
 */
public final class StringUtil extends org.apache.commons.lang3.StringUtils {

	/** 禁止实例化 */
	private StringUtil() {}
	
	/**
	 * 
	 * 描述: 字符串是否为空
	 * trim 之后""或null 
	 * @author qye.zheng
	 * 
	 * @param target
	 * @return
	 */
	public static final boolean isEmpty(final String target) {
		// 为null 或 trim 之后为空字符串
		if (null == target || Constant.WHITE_SPACE.equals(target.trim())) {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * 描述: 字符串是否不为空
	 * trim 之后""或null 
	 * @author qye.zheng
	 * 
	 * @param target
	 * @return
	 */
	public static final boolean isNotEmpty(final String target) {
		// 为null 或 trim 之后为空字符串
		if (null != target && !Constant.WHITE_SPACE.equals(target.trim())) {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * @description 是否所有字符串都为空
	 * @param target
	 * @return
	 * @author qye.zheng
	 */
	public static final boolean isAllEmpty(final String... target)
	{
		if (null != target)
		{
			for (int i = 0; i < target.length; i++)
			{
				// 存在不为空
				if (!isEmpty(target[i]))
				{
					return false;
				} else
				{
					continue;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * 描述: 生成一个1024大小的StringBuilder 
	 * @author qye.zheng
	 * 
	 * @return
	 */
	public static StringBuilder getBuilder() {
		
		return getBuilder(Constant.DEFAULT_BYTE);
	}

	/**
	 * 
	 * 描述: 生成一个指定大小的StringBuilder 
	 * @author qye.zheng
	 * @param size
	 * @return
	 */
	public static StringBuilder getBuilder(final int size) {
		
		return new StringBuilder(size);
	}
	
	/**
	 * 
	 * 描述: 生成一个1024大小的StringBuffer
	 * @author qye.zheng
	 * 
	 * @return
	 */
	public static StringBuffer getBuffer() {
		
		return getBuffer(Constant.DEFAULT_BYTE);
	}
	
	/**
	 * 
	 * 描述: 生成一个指定大小的StringBuffer
	 * @author qye.zheng
	 * 
	 * @return
	 */
	public static StringBuffer getBuffer(final int size) {
		
		return new StringBuffer(size);
	}
	
	/**
	 * 
	 * 描述: 将流转换成字符串
	 * @author qye.zheng
	 * 
	 * @param input
	 * @return
	 */
	public static String streamToString(final InputStream input) 
	{      
		final StringBuilder builder = getBuilder();     
        String line = null;      
        try 
        {      
        	final BufferedReader reader = new BufferedReader(new InputStreamReader(input, Constant.CHART_SET_UTF_8));      
            while (null != (line = reader.readLine())) {  
                builder.append(line + "\n");      
            }      
        } catch (IOException e) 
        {      
            e.printStackTrace();      
        } finally 
        {      
            try 
            {      
                input.close();      
            } catch (IOException e) 
            {      
               e.printStackTrace();      
            }      
        }      
        
        return builder.toString();      
    }  
	
	/**
	 * 
	 * 描述: 数字字符串比较
	 例如: 3 和 123，现实的排序逻辑，应该是3排在
	 123的前面; 而java.lang.String.compareTo方法是
	 按照unicode编码的位置逐个去比较，而没有去甄别
	 多个数字字符的存在
	 * @author qye.zheng
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static int digitStringCompare(final String str1, final String str2)
	{
		// 将字符串 转成字符串数字，逐个甄别数字字符
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		// 最小的数组长度
		int minLength = (chs1.length > chs2.length) ? chs2.length : chs1.length;
		StringBuilder builder1 = null;
		StringBuilder builder2 = null;
		int n1 = 0;
		int n2 = 0;
		for (int i = 0; i < minLength; i++)
		{
			if (isNumeric(String.valueOf(chs1[i])))
			{
				builder1 = getBuilder();
				builder2 = getBuilder();
				// 把 chs1 连续数字找出来
				for (int j = i; j < minLength; j++)
				{
					if (!isNumeric(String.valueOf(chs1[j])))
					{
						break;
					}
					builder1.append(chs1[j]);
				}
				// 把 chs2 连续数字找出来
				for (int j = i; j < minLength; j++)
				{
					if (!isNumeric(String.valueOf(chs2[j])))
					{
						break;
					}
					builder2.append(chs2[j]);
				}
				if (!StringUtil.isEmpty(builder2.toString()))
				{
					// 转成 整型数字
					n1 = Integer.valueOf(builder1.toString());
					n2 = Integer.valueOf(builder2.toString());
					if (n1 == n2)
					{
						// 修改 i 的位置，并继续下次 (消除返回后的 i++)
						i += builder1.length() - 1;
						continue;
					} else {
						// 不等
						// 返回两者的差值
						return n1 - n2;
					}
				} else
				{
					// builder2 为空，没有数字
					
					return str1.compareTo(str2);
				}
			}
		}
		// 比较之后，还是不能区分大小
		// 用长度差来区分
		return str1.length() - str2.length();
	}
	
	/**
	 * 
	 * 描述: 是否包含数字
	 * @author qye.zheng
	 * 
	 * @param value
	 * @return
	 */
	public static boolean hasNumber(final String value)
	{
		
		return value.matches(RegexConstant.HAS_NUMBER);
	}
	
	/**
	 * 
	 * @description 是否是手机号码
	 * 可以修改规则来满足当前  手机号码 规则
	 * @param phoneNumber
	 * @return
	 * @author qye.zheng
	 */
	public static final boolean isPhoneNumber(final String phoneNumber)
	{
		// 11位
		// String regex = "1[3,5,8]\\d{9}";
		String regex = "1\\d{10}";
		
		return phoneNumber.matches(regex);
	}
	
	/**
	 * 
	 * 描述: 前置补0
	 * @author qye.zheng
	 * @param target 目标数字
	 * @param totalLength 总长度
	 * @return
	 */
	public static String addZero(final int target, final int totalLength)
	{
		// 强转为long型
		final long temp = (long) target;
		
		return addZero(temp, totalLength);
	}
	
	/**
	 * 
	 * 描述: 前置补0
	 * @author qye.zheng
	 * @param target 目标数字
	 * @param totalLength 总长度
	 * @return
	 */
	public static String addZero(final long target, final int totalLength)
	{
		String result = null;
		result = String.valueOf(target);
		if (result.length() >= totalLength)
		{
			// 长度已满足总位数
			
			return result;
		}
		final StringBuilder builder = StringUtil.getBuilder();
		// 0 的最多个数 (总位数 减一)
		final int wantAdd = totalLength - result.length();
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
	 * 描述: 前置补0
	 * @author qye.zheng
	 * @param target
	 * @param totalLength 总长度
	 * @return
	 */
	public static String addZero(final String target, final int totalLength)
	{
		if (target.length() >= totalLength)
		{
			// 长度已满足总位数
			
			return target;
		}
		String result = null;
		final StringBuilder builder = StringUtil.getBuilder();
		final int wantAdd = totalLength - target.length();
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
			{	/* 格式化excel中 数值类型为整数，消除小数点 */
				final DecimalFormat decimalFormat = new DecimalFormat("#");
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
		/*
		 * 0: 八进制的前缀
		 * 0X: 十六进制的前缀
		 * 二进制、十进制: 无前缀
		 * (Binary 2进制)1111 = (OctaSystem 8进制)017 = (Decimal 10进制)15 = (Hexadecimal 16进制)0XF
		 * 16进制单个数字最大值为F: 4位二进制，3位八进制，2位十进制
		 * 单个字节: 最大值对应8位二进制，对应2位16进制
		 */
		final StringBuilder builder = StringUtil.getBuilder();
		int value = 0;
		for (int i = 0; i < array.length; i++)
		{
			String hex = null;
			// 8位(包含符号位)字节 转换 为 32位整型
			value = array[i] & 0XFF;
			// 转成16进制格式的字符串 (0 ~ f)
			hex = Integer.toHexString(value);
			if (hex.length() == 1)
			{
				// 补 0
				hex = '0' + hex;
			}
			// 大小写转换
			if (upperCase) {// 大写
				builder.append(hex.toUpperCase());
			} else {// 小写
				builder.append(hex.toLowerCase());
			}
		}
		
		return builder.toString();
	}
	
	/**
	 * 
	 * @description 字节数组转成16进制大写字符串
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
	 * @param hexString 16进制字符串，支持任意位数的16进制数，奇数/偶数位均可，偶数位为标准的16进制数
	 * @return
	 * @author qye.zheng
	 */
	public static final byte[] parseHexString2Byte(String hexString)
	{
		byte[] array = null;
		if (StringUtil.isEmpty(hexString))
		{
			return array;
		}
		// 基数
		final int radix = 16;
		if (0 != (hexString.length() % 2)) { // 奇数位，最高位补0
			hexString = "0" + hexString;
		}
		// 字节数量，每2个字符对应一个字节
		final int count = hexString.length() / 2;
		array = new byte[count];
		Integer decimal = -1;
		for (int i = 0; i < count; i++)
		{
			final int start = i * 2;
			decimal = Integer.parseInt(hexString.substring(start, start + 2), radix);
			//
			array[i] = decimal.byteValue();
		}
		
		return array;
	}
	
	/**
	 * 
	 * @description 中文字符转成unicode编码
	 * 例如，中文 对应的unicode编码为: \u4e2d\u6587
	 * 便于在有些环境，将字符快速转成unicode编码
	 * @param value
	 * @return
	 * @author qianye.zheng
	 */
	public static final String chineseToUnicode(final String value) {
		final StringBuilder builder = getBuilder();
		final int len = value.length();
		for (int i = 0; i < len; i++) {
			builder.append("\\u" + Integer.toHexString(value.codePointAt(i)));
		}
		
		return builder.toString();
	}
	
}
