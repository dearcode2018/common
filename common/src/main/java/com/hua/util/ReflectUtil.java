/**
 * ReflectUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hua.annotation.FieldCheck;

/**
 * ReflectUtil
 * 描述: 反射 - 工具类
 * @author  qye.zheng
 */
public final class ReflectUtil
{

	private static final String SETTER_PREFIX = "set";

	private static final String GETTER_PREFIX = "get";

	private static final String CGLIB_CLASS_SEPARATOR = "$$";
	
	private static final Logger logger = LoggerFactory.getLogger(ReflectUtil.class);
	
	/**
	 * 构造方法
	 * 描述: 私有 - 禁止实例化
	 * @author  qye.zheng
	 */
	private ReflectUtil()
	{
	}
	
	/**
	 * 
	 * 描述: 搜索参数 - 预警
	 * 对基本类型不敏感
	 * @author  qye.zheng
	 * @param methodName
	 * @param object
	 * @param log
	 */
	public static void alertSearch(final String methodName, final Object object, final Log log)
	{
		final Class<?> clazz = object.getClass();
		// 获取所有声明的属性
		final Field[] fields = clazz.getDeclaredFields();
		Field field = null;
		String temp = null;
		java.util.Date dateTime = null;
		try
		{
			for (int i = 0; i < fields.length; i++)
			{
				field = fields[i];
				// 给指定的属性设置值
				// 设置为可以访问(若不设置，则访问将抛异常)
				/*
				 java.lang.IllegalAccessException: Class com.hua.test.reflect.ReflectTest can not access a 
				 member of class com.hua.entity.User with modifiers "private"
				 */
				field.setAccessible(true);
				
				// 对字符串单独做警告
				if (field.get(object) instanceof String)
				{
					temp = (String) field.get(object);
					if (StringUtil.isEmpty(temp))
					{
						log.info(methodName + " =====> 参数: " + field.getName() + " 不参与搜索!");
					} else 
					{
						log.info(methodName + " =====> 参数: " + field.getName() + " = " + temp);
					}
					continue;
				}
				// 非字符串类型的警告
				if (null == field.get(object))
				{
					log.info(methodName + " =====> 参数: " + field.getName() + " 不参与搜索!");
				} else
				{
					// 格式化日期时间类型 java.util.Date
					if (field.get(object) instanceof java.util.Date)
					{
						dateTime = (java.util.Date) field.get(object);
						temp = DateTimeUtil.format(dateTime);
						log.info(methodName + " =====> 参数: " + field.getName() + " = " + temp);
					} else
					{
						log.info(methodName  + " =====> 参数: " + field.getName() + " = " + field.get(object));
					}
				}

			}
		} catch (Exception e)
		{
			log.error("methodName " + " =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 非空验证
	 * @author  qye.zheng
	 * @param target 需要验证的对象
	 * @param log
	 * @return true - 验证通过，false - 存在为空数据，验证不通过
	 * 字符串为空单独验证
	 */
	public static boolean notEmptyValidate(final Object target, final Log log)
	{
		boolean flag = false;
		final Class<?> clazz = target.getClass();
		// 获取所有声明的属性
		final Field[] fields = clazz.getDeclaredFields();
		Field field = null;
		FieldCheck fieldCheck = null;
		String temp = null;
		try
		{
			for (int i = 0; i < fields.length; i++)
			{
				field = fields[i];
				// 判断是否施加 FieldCheck 注解
				fieldCheck = field.getAnnotation(FieldCheck.class);
				if (null == fieldCheck)
				{
					// 没有施加指定的注解，结束当前循环，进入下一次循环
					continue;
				}
				
				if (!fieldCheck.nullable())
				{
					// 不能为空，需要非空验证
					// 给指定的属性设置值
					// 设置为可以访问(若不设置，则访问将抛异常)
					/*
					 java.lang.IllegalAccessException: Class com.hua.test.reflect.ReflectTest can not access a 
					 member of class com.hua.entity.User with modifiers "private"
					 */
					field.setAccessible(true);
					// 对字符串单独做验证
					if (field.get(target) instanceof String)
					{
						temp = (String) field.get(target);
						if (StringUtil.isEmpty(temp))
						{
							log.info("notEmptyValidate =====> " + field.getName() + " 为空，校验不通过!");
							
							return false;
						} 
						
						continue;
					}
					// 非字符串类型的验证
					if (null == field.get(target))
					{
						log.info("notEmptyValidate =====> " + field.getName() + " 为空，校验不通过!");
						
						return false;
					}
				}
			}
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 
	 * @description 获取setter方法的参数类型
	 * 符合JavaBean规范的setter方法只有一个参数
	 * @param clazz
	 * @param setterName
	 * @return
	 * @author qye.zheng
	 */
	public static final Class<?> getSetterParameterType(final Class<?> clazz, final String setterName)
	{
		Class<?> result = null;
		if (null == clazz || StringUtil.isEmpty(setterName))
		{
			return result;
		}
		final Method[] ms = clazz.getMethods();
		Class<?>[] clazzs = null;
		for (Method m : ms)
		{
			clazzs = m.getParameterTypes();
			if (!EmptyUtil.isEmpty(clazzs))
			{
				result = clazzs[0];
				if (setterName.equals(m.getName()))
				{
					
					return result;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * 调用Getter方法.
	 * 支持多级，如：对象名.对象名.方法
	 */
	public static Object invokeGetter(Object obj, String propertyName) {
		Object object = obj;
		for (String name : StringUtils.split(propertyName, ".")){
			String getterMethodName = GETTER_PREFIX + StringUtils.capitalize(name);
			object = invokeMethod(object, getterMethodName, new Class[] {}, new Object[] {});
		}
		return object;
	}

	/**
	 * 调用Setter方法, 仅匹配方法名。
	 * 支持多级，如：对象名.对象名.方法
	 */
	public static void invokeSetter(Object obj, String propertyName, Object value) {
		Object object = obj;
		String[] names = StringUtils.split(propertyName, ".");
		for (int i=0; i<names.length; i++){
			if(i<names.length-1){
				String getterMethodName = GETTER_PREFIX + StringUtils.capitalize(names[i]);
				object = invokeMethod(object, getterMethodName, new Class[] {}, new Object[] {});
			}else{
				String setterMethodName = SETTER_PREFIX + StringUtils.capitalize(names[i]);
				invokeMethodByName(object, setterMethodName, new Object[] { value });
			}
		}
	}

	/**
	 * 直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
	 */
	public static Object getFieldValue(final Object obj, final String fieldName) {
		Field field = getAccessibleField(obj, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
		}

		Object result = null;
		try {
			result = field.get(obj);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常{}", e.getMessage());
		}
		return result;
	}

	/**
	 * 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.
	 */
	public static void setFieldValue(final Object obj, final String fieldName, final Object value) {
		Field field = getAccessibleField(obj, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
		}

		try {
			field.set(obj, value);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}
	}

	/**
	 * 直接调用对象方法, 无视private/protected修饰符.
	 * 用于一次性调用的情况，否则应使用getAccessibleMethod()函数获得Method后反复调用.
	 * 同时匹配方法名+参数类型，
	 */
	public static Object invokeMethod(final Object obj, final String methodName, final Class<?>[] parameterTypes,
			final Object[] args) {
		Method method = getAccessibleMethod(obj, methodName, parameterTypes);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
		}

		try {
			return method.invoke(obj, args);
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}
	}

	/**
	 * 直接调用对象方法, 无视private/protected修饰符，
	 * 用于一次性调用的情况，否则应使用getAccessibleMethodByName()函数获得Method后反复调用.
	 * 只匹配函数名，如果有多个同名函数调用第一个。
	 */
	public static Object invokeMethodByName(final Object obj, final String methodName, final Object[] args) {
		Method method = getAccessibleMethodByName(obj, methodName);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
		}

		try {
			return method.invoke(obj, args);
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredField, 并强制设置为可访问.
	 * 
	 * 如向上转型到Object仍无法找到, 返回null.
	 */
	public static Field getAccessibleField(final Object obj, final String fieldName) {
		Validate.notNull(obj, "object can't be null");
		Validate.notBlank(fieldName, "fieldName can't be blank");
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				Field field = superClass.getDeclaredField(fieldName);
				makeAccessible(field);
				return field;
			} catch (NoSuchFieldException e) {//NOSONAR
				// Field不在当前类定义,继续向上转型
				continue;// new add
			}
		}
		return null;
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredMethod,并强制设置为可访问.
	 * 如向上转型到Object仍无法找到, 返回null.
	 * 匹配函数名+参数类型。
	 * 
	 * 用于方法需要被多次调用的情况. 先使用本函数先取得Method,然后调用Method.invoke(Object obj, Object... args)
	 */
	public static Method getAccessibleMethod(final Object obj, final String methodName,
			final Class<?>... parameterTypes) {
		Validate.notNull(obj, "object can't be null");
		Validate.notBlank(methodName, "methodName can't be blank");

		for (Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType.getSuperclass()) {
			try {
				Method method = searchType.getDeclaredMethod(methodName, parameterTypes);
				makeAccessible(method);
				return method;
			} catch (NoSuchMethodException e) {
				// Method不在当前类定义,继续向上转型
				continue;// new add
			}
		}
		return null;
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredMethod,并强制设置为可访问.
	 * 如向上转型到Object仍无法找到, 返回null.
	 * 只匹配函数名。
	 * 
	 * 用于方法需要被多次调用的情况. 先使用本函数先取得Method,然后调用Method.invoke(Object obj, Object... args)
	 */
	public static Method getAccessibleMethodByName(final Object obj, final String methodName) {
		Validate.notNull(obj, "object can't be null");
		Validate.notBlank(methodName, "methodName can't be blank");

		for (Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType.getSuperclass()) {
			Method[] methods = searchType.getDeclaredMethods();
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					makeAccessible(method);
					return method;
				}
			}
		}
		return null;
	}

	/**
	 * 改变private/protected的方法为public，尽量不调用实际改动的语句，避免JDK的SecurityManager抱怨。
	 */
	public static void makeAccessible(Method method) {
		if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers()))
				&& !method.isAccessible()) {
			method.setAccessible(true);
		}
	}

	/**
	 * 改变private/protected的成员变量为public，尽量不调用实际改动的语句，避免JDK的SecurityManager抱怨。
	 */
	public static void makeAccessible(Field field) {
		if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()) || Modifier
				.isFinal(field.getModifiers())) && !field.isAccessible()) {
			field.setAccessible(true);
		}
	}

	/**
	 * 通过反射, 获得Class定义中声明的泛型参数的类型, 注意泛型必须定义在父类处
	 * 如无法找到, 返回Object.class.
	 * eg.
	 * public UserDao extends HibernateDao<User>
	 *
	 * @param clazz The class to introspect
	 * @return the first generic declaration, or Object.class if cannot be determined
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getClassGenricType(final Class<T> clazz) {
		return  (Class<T>) getClassGenricType(clazz, 0);
	}

	/**
	 * 通过反射, 获得Class定义中声明的父类的泛型参数的类型.
	 * 如无法找到, 返回Object.class.
	 * 
	 * 如public UserDao extends HibernateDao<User,Long>
	 *
	 * @param clazz clazz The class to introspect
	 * @param index the Index of the generic ddeclaration,start from 0.
	 * @return the index generic declaration, or Object.class if cannot be determined
	 */
	public static Class<?> getClassGenricType(final Class<?> clazz, final int index) {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
			return Object.class;
		}

		return (Class<?>) params[index];
	}
	
	public static Class<?> getUserClass(Object instance) {
		Class<?> clazz = instance.getClass();
		if (clazz != null && clazz.getName().contains(CGLIB_CLASS_SEPARATOR)) {
			Class<?> superClass = clazz.getSuperclass();
			if (superClass != null && !Object.class.equals(superClass)) {
				return superClass;
			}
		}
		return clazz;

	}
	
	/**
	 * 将反射时的checked exception转换为unchecked exception.
	 */
	public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
		if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
				|| e instanceof NoSuchMethodException) {
			return new IllegalArgumentException(e);
		} else if (e instanceof InvocationTargetException) {
			return new RuntimeException(((InvocationTargetException) e).getTargetException());
		} else if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		}
		return new RuntimeException("Unexpected Checked Exception.", e);
	}	

}
