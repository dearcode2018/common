/**
 * BeanUtil.java
 * @author qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * BeanUtil 描述:
 * 
 * @author qye.zheng
 * 
 */
public final class BeanUtil extends org.apache.commons.beanutils.BeanUtils {

	/**
	 * 构造方法 描述:
	 * 
	 * @author qye.zheng
	 * 
	 */
	private BeanUtil() {
	}

	/**
	 * 
	 * 描述: bean 转成 map，忽略空值
	 * 
	 * @author qye.zheng
	 * @param bean
	 * @return
	 */
	public static final Map<String, Object> bean2MapIgnoreNull(final Object bean) {
		final boolean ignoreNull = true;

		return bean2Map(bean, ignoreNull);
	}

	/**
	 * 
	 * 描述: bean 转成 map，包括空值
	 * 
	 * @author qye.zheng
	 * @param bean
	 * @return
	 */
	public static final Map<String, Object> bean2MapIncludeNull(
			final Object bean) {
		final boolean ignoreNull = false;

		return bean2Map(bean, ignoreNull);
	}

	/**
	 * 
	 * 描述: 将 javabean (除了 class 属性) 转成 map
	 * 
	 * @author qye.zheng
	 * @param bean
	 * @param ignoreNull
	 *            是否忽略空值 忽略: 空值将不放入map中
	 * @return
	 */
	public static final Map<String, Object> bean2Map(final Object bean,
			final boolean ignoreNull) {
		Map<String, Object> resultMap = null;
		try {
			//
			final Class<?> clazz = bean.getClass();
			resultMap = new HashMap<String, Object>();
			// bean 信息
			final BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			// 属性 描述器
			final PropertyDescriptor[] descriptors = beanInfo
					.getPropertyDescriptors();
			PropertyDescriptor descriptor = null;
			String propertyName = null;
			// get 方法
			Method readMethod = null;
			Object result = null;
			final Object[] emptyArgs = new Object[0];
			if (EmptyUtil.isEmpty(descriptors)) {
				System.out.println("属性描述器为空，无法将bean转成map!");
			} else {
				for (int i = 0; i < descriptors.length; i++) {
					descriptor = descriptors[i];
					// 属性名称
					propertyName = descriptor.getName();
					// 过滤 java.lang.Object.getClass() --> class 虚拟属性
					if (!"class".equals(propertyName)) {
						// 非 class 属性 (将class属性过滤掉)
						readMethod = descriptor.getReadMethod();
						// 反射 : 调用相应属性的 get 方法 获取值 < new Object[0]
						// 创建一个空的Object数组 >
						result = readMethod.invoke(bean, emptyArgs);
						if (ignoreNull) {
							// 忽略空值
							if (null != result) {
								// 将get方法的返回值作为value放入map
								resultMap.put(propertyName, result);
							}
						} else {
							// 不管是否为空，都放入map
							// 将get方法的返回值作为value放入map
							resultMap.put(propertyName, result);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultMap;
	}

	/**
	 * 
	 * 描述: 将 map 转成 javabean 将map中Object类的value通过反射注入到bean中
	 * 
	 * @author qye.zheng
	 * @param clazz
	 * @param map
	 * @return
	 */
	public static <T> T map2Bean(final Class<T> clazz,
			final Map<String, Object> map) {
		T resultObj = null;
		try {
			// bean 信息
			final BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			// 属性 描述器
			final PropertyDescriptor[] descriptors = beanInfo
					.getPropertyDescriptors();
			PropertyDescriptor descriptor = null;
			String propertyName = null;
			final Object[] oneArgs = new Object[1];
			// set 方法
			Method writeMethod = null;
			/*
			 * 构建一个 bean 对象，然后往该bean中填充 map 的 value值 必须有默认构造方法
			 */
			resultObj = clazz.newInstance();
			if (EmptyUtil.isEmpty(descriptors)) {
				System.out.println("属性描述器为空，无法将map转成bean!");
			} else {
				for (int i = 0; i < descriptors.length; i++) {
					descriptor = descriptors[i];
					// 属性名称
					propertyName = descriptor.getName();
					if (map.containsKey(propertyName)) {
						// 含有该属性
						// set 方法
						writeMethod = descriptor.getWriteMethod();
						// 获取属性的值
						oneArgs[0] = map.get(propertyName);
						// 反射调用
						writeMethod.invoke(resultObj, oneArgs);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultObj;
	}

}
