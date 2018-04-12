/**
 * EmptyUtil.java
 * @author qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.util;

import java.util.Collection;
import java.util.Map;

import com.hua.constant.Constant;

/**
 * EmptyUtil
 * 描述: 是否为空工具类
 * @author qye.zheng
 * 
 */
public final class EmptyUtil
{

	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	private EmptyUtil()
	{
	}
	
	/**
	 * 
	 * 描述: 字符串是否为空
	 * trim 之后""或null 
	 * @author qye.zheng
	 * 
	 * @param target
	 * @return
	 */
	public static boolean isEmpty(final String target) {
		// 为null 或 trim 之后为空字符串
		if (null == target || Constant.WHITE_SPACE.equals(target.trim())) {

			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * 描述: StringBuilder 是否为空
	 * @author qye.zheng
	 * 
	 * @param builder
	 * @return
	 */
	public static boolean isEmpty(final StringBuilder builder) {
		if (null == builder || Constant.WHITE_SPACE.equals(builder.toString())) {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * 描述: StringBuffer 是否为空
	 * @author qye.zheng
	 * 
	 * @param buffer
	 * @return
	 */
	public static boolean isEmpty(final StringBuffer buffer) {
		if (null == buffer || Constant.WHITE_SPACE.equals(buffer.toString())) {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * 描述: 数组是否为空
	 * @author qye.zheng
	 * 
	 * @param objArr
	 * @return
	 */
	public static boolean isEmpty(final Object[] objArr) {
		if (null == objArr || objArr.length == Constant.ZERO) {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * 描述: 集合是否为空
	 * @author qye.zheng
	 * 
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(final Collection<?> collection) {
		if (null == collection || collection.size() == Constant.ZERO) {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * 描述: map是否为空
	 * @author qye.zheng
	 * 
	 * @param map
	 * @return
	 */
	public static boolean isEmpty(final Map<?, ?> map) {
		if (null == map || map.size() == Constant.ZERO) {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * 描述: Short 值是否为空
	 * @author qye.zheng
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(final Short value) {
		if (null == value || Constant.ZERO == value) {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * 描述: Integer 值是否为空
	 * @author qye.zheng
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(final Integer value) {
		if (null == value || Constant.ZERO == value) {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * 描述: Long 值是否为空
	 * @author qye.zheng
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(final Long value) {
		if (null == value || Constant.ZERO == value) {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * 描述: Float 值是否为空
	 * @author qye.zheng
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(final Float value) {
		if (null == value || Constant.ZERO_FLOAT == value) {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * 描述: Double 值是否为空
	 * @author qye.zheng
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(final Double value) {
		if (null == value || Constant.ZERO_DOUBLE == value) {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * 描述: Byte 值是否为空
	 * @author qye.zheng
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(final Byte value) {
		if (null == value || Constant.ZERO == value) {
			
			return true;
		}
		
		return false;
	}
	
	
	// 模板
	public static boolean isEmpty(final Object object) {
		if (null == object)
		{
			
			return true;
		}
		
		return false;
	}
	
	// 模板
	public static boolean isEmpty() {
		
		return false;
	}
}
