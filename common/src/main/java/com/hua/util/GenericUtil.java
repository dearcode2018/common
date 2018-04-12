/**
  * @filename GenericUtil.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

 /**
 * @type GenericUtil
 * @description 泛型工具
 * @author qye.zheng
 */
public final class GenericUtil {
	
	/**
	 * @description 构造方法
	 * @author qye.zheng
	 */
	private GenericUtil() {
	}
	
	/**
	 * 
	 * @description 
	 * @param clazz
	 * @param index
	 * @return
	 * @author qye.zheng
	 */
	public static final Class<?> getGenericSuperClass(final Class<?> clazz, final int index)
	{
		// 获取泛型父类
		final Type type = clazz.getGenericSuperclass();
		if (!(type instanceof ParameterizedType))
		{
			return Object.class;
		}
		
		final Type[] params = ((ParameterizedType) type).getActualTypeArguments();
		if (params.length < 1 || index > params.length - 1)
		{
			throw new IllegalArgumentException("参数的索引超出范围: " + (params.length - 1));
		}
		
		if (!(params[index] instanceof Class<?>))
		{
			return Object.class;
		}
		
		return (Class<?>) params[index];
	}
}
