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
	public static final boolean isEmpty(final String target) {
		// 为null 或 trim 之后为空字符串
		return null == target || Constant.WHITE_SPACE.equals(target.trim());
	}
	
	/**
	 * 
	 * 描述: StringBuilder 是否为空
	 * @author qye.zheng
	 * 
	 * @param builder
	 * @return
	 */
	public static final boolean isEmpty(final StringBuilder builder) {
		return null == builder || 0 ==builder.length();
	}
	
	/**
	 * 
	 * 描述: 数组是否为空
	 * @author qye.zheng
	 * 
	 * @param objArr
	 * @return
	 */
	public static final boolean isEmpty(final Object[] objArr) {
		return null == objArr || objArr.length == 0;
	}
	
	/**
	 * 
	 * 描述: 集合是否为空
	 * @author qye.zheng
	 * 
	 * @param collection
	 * @return
	 */
	public static final boolean isEmpty(final Collection<?> collection) {
		return null == collection || collection.isEmpty();
	}
	
	/**
	 * 
	 * 描述: map是否为空
	 * @author qye.zheng
	 * 
	 * @param map
	 * @return
	 */
	public static final boolean isEmpty(final Map<?, ?> map) {
		return null == map || map.size() == 0;
	}
	
	/**
	 * 
	 * 描述: Short 值是否为空
	 * @author qye.zheng
	 * 
	 * @param value
	 * @return
	 */
	public static final boolean isEmpty(final Short value) {
		return null == value || 0 == value;
	}
	
	/**
	 * 
	 * 描述: Integer 值是否为空
	 * @author qye.zheng
	 * 
	 * @param value
	 * @return
	 */
	public static final boolean isEmpty(final Integer value) {
		return null == value || 0 == value;
	}
	
	/**
	 * 
	 * 描述: Long 值是否为空
	 * @author qye.zheng
	 * 
	 * @param value
	 * @return
	 */
	public static final boolean isEmpty(final Long value) {
		return null == value || 0 == value;
	}
	
	/**
	 * 
	 * 描述: Float 值是否为空
	 * @author qye.zheng
	 * 
	 * @param value
	 * @return
	 */
	public static final boolean isEmpty(final Float value) {
		return null == value || Constant.ZERO_FLOAT == value;
	}
	
	/**
	 * 
	 * 描述: Double 值是否为空
	 * @author qye.zheng
	 * 
	 * @param value
	 * @return
	 */
	public static final boolean isEmpty(final Double value) {
		return null == value || Constant.ZERO_DOUBLE == value;
	}
	
	/**
	 * 
	 * 描述: Byte 值是否为空
	 * @author qye.zheng
	 * 
	 * @param value
	 * @return
	 */
	public static final boolean isEmpty(final Byte value) {
		return null == value || 0 == value;
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
	public static final boolean isNotEmpty(final String target) {
		return !isEmpty(target);
	}
	
	/**
	 * 
	 * 描述: StringBuilder 是否为空
	 * @author qye.zheng
	 * 
	 * @param builder
	 * @return
	 */
	public static final boolean isNotEmpty(final StringBuilder builder) {
		return !isEmpty(builder);
	}
	
	/**
	 * 
	 * 描述: 数组是否为空
	 * @author qye.zheng
	 * 
	 * @param objArr
	 * @return
	 */
	public static final boolean isNotEmpty(final Object[] objArr) {
		return !isEmpty(objArr);
	}
	
	/**
	 * 
	 * 描述: 集合是否为空
	 * @author qye.zheng
	 * 
	 * @param collection
	 * @return
	 */
	public static final boolean isNotEmpty(final Collection<?> collection) {
		return !isEmpty(collection);
	}
	
	/**
	 * 
	 * 描述: map是否为空
	 * @author qye.zheng
	 * 
	 * @param map
	 * @return
	 */
	public static final boolean isNotEmpty(final Map<?, ?> map) {
		return !isEmpty(map);
	}
	
	/**
	 * 
	 * 描述: Short 值是否为空
	 * @author qye.zheng
	 * 
	 * @param value
	 * @return
	 */
	public static final boolean isNotEmpty(final Short value) {
		return !isEmpty(value);
	}
	
	/**
	 * 
	 * 描述: Integer 值是否为空
	 * @author qye.zheng
	 * 
	 * @param value
	 * @return
	 */
	public static final boolean isNotEmpty(final Integer value) {
		return !isEmpty(value);
	}
	
	/**
	 * 
	 * 描述: Long 值是否为空
	 * @author qye.zheng
	 * 
	 * @param value
	 * @return
	 */
	public static final boolean isNotEmpty(final Long value) {
		return !isEmpty(value);
	}
	
	/**
	 * 
	 * 描述: Float 值是否为空
	 * @author qye.zheng
	 * 
	 * @param value
	 * @return
	 */
	public static final boolean isNotEmpty(final Float value) {
		return !isEmpty(value);
	}
	
	/**
	 * 
	 * 描述: Double 值是否为空
	 * @author qye.zheng
	 * 
	 * @param value
	 * @return
	 */
	public static final boolean isNotEmpty(final Double value) {
		return !isEmpty(value);
	}
	
	/**
	 * 
	 * 描述: Byte 值是否为空
	 * @author qye.zheng
	 * 
	 * @param value
	 * @return
	 */
	public static final boolean isNotEmpty(final Byte value) {
		return !isEmpty(value);
	}
	
	/**
	 * 
	 * @description 
	 * @param value
	 * @return
	 * @author qianye.zheng
	 */
	public static final boolean isNotEmpty(final Object value) {
		return !isEmpty(value);
	}
	
	/**
	 * 
	 * @description 
	 * @param object
	 * @return
	 * @author qianye.zheng
	 */
	public static final boolean isEmpty(final Object object) {
		return (null == object);
	}

}
