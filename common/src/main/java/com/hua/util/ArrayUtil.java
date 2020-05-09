/**
 * ArrayUtil.java
 * @author qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.util;

/**
 * ArrayUtil
 * 描述: 数组 工具类
 * @author qye.zheng
 * 
 */
public final class ArrayUtil extends org.apache.commons.lang3.ArrayUtils
{
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	private ArrayUtil()
	{
	}
	
	/**
	 * 
	 * 描述: 将数字字符串数组拷贝为整型数组
	 * @author  qye.zheng
	 * @param strArray
	 * @return
	 */
	public static final Integer[] copyStringToInteger(final String[] strArray)
	{
		final Integer[] resultArray = new Integer[strArray.length];
		for (int i = 0; i < strArray.length; i++)
		{
			resultArray[i] = Integer.parseInt(strArray[i]);
		}
		
		return resultArray;
	}
	
	/**
	 * 
	 * 描述: 计算整型数组的乘法结果
	 * @author  qye.zheng
	 * @param intArray
	 * @return
	 */
	public static final int multipleResult(final Integer[] intArray)
	{
		int result = 1;
		for (int i = 0; i < intArray.length; i++)
		{
			result *= intArray[i];
		}
		
		return result;
	}
	
	/**
	 * 
	 * 描述: 将字符串数组所有元素进行trim
	 * @author  qye.zheng
	 * @param array
	 */
	public static final void trim(final String[] array)
	{
		if (EmptyUtil.isNotEmpty(array))
		{
			for (int i = 0; i < array.length; i++)
			{// 反向赋值，需用数组下标访问方式
				array[i] = array[i].trim();
			}
		}
	}
	
}
