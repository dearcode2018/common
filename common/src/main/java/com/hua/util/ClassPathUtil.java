/**
 * 描述: 
 * ClassPathUtil.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import com.hua.constant.Constant;

/**
 * 描述: 类路径 工具类
 * 获取多种绝对路径
 * 类路径(根路径)、目标类的路径、指定的类路径
 * 
* 路径参数 : 统一以 / 开头
 * 
 * @author qye.zheng
 * ClassPathUtil
 */
public final class ClassPathUtil {
	
	
	/**
	 * 类路径 : 在类的文件基础
	 */
	
	/** 无参构造方法 */
	private ClassPathUtil() {}
	
	/**
	 * 
	 * 描述: 获取类路径(根路径)
	 * 
	 *返回 :  .../WEB-INF/classes/
	 * @author qye.zheng
	 * 
	 * @return
	 */
	public static String getClassRootPath() {
		final URL url = ClassPathUtil.class.getResource("/");
		final String path = url.getPath();
		
		return path;
	}
	
	/**
	 * 
	 * 描述: 获取目标类所在的路径
	 * @author qye.zheng
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getClassCurrentPath(final Class<?> clazz) {
		final URL url = clazz.getResource("");
		final String path = url.getPath();
		
		return path;
	}
	
	/**
	 * 
	 * 描述: 获取类根路径下的子路径
	 * 
	 *  必须以 / 开头，子路径必须存在（不存在则抛异常）- 以路径分隔符结尾
	 * @author qye.zheng
	 * 
	 * @param subpath包路径 (examp: /com/sun/java/)
	 * @return 返回 /com/sun/java/xx.txt 或 /com/sun/java/
	 */
	public static String getClassSubpath(final String subpath) {
		final URL url = ClassPathUtil.class.getResource(subpath);
		final String path = url.getPath();
		
		return path;
	}
	
	/**
	 * 
	 * 描述: 获取类根路径下的子路径
	 * 
	 *  必须以 / 开头，子路径必须存在（不存在则抛异常）- 以路径分隔符结尾
	 * @author qye.zheng
	 * 
	 * @param subpath包路径 (examp: /com/sun/java/)
	 * @param isDecode 是否解码 (含有中文或其他非通用字符会进行编码)
	 * @return 返回 /com/sun/java/xx.txt 或 /com/sun/java/
	 */
	public static String getClassSubpath(final String subpath, final boolean isDecode) {
		final URL url = ClassPathUtil.class.getResource(subpath);
		final String path = url.getPath();
		
		// 解码
		if (isDecode)
		{
			String pathDecode = null;
			try
			{
				pathDecode = URLDecoder.decode(path, Constant.CHART_SET_UTF_8);
			} catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
			// 不需要编码，执行解码操作
			return pathDecode;
		}
		
		return path;
	}
	
	/**
	 * 
	 * 描述: 获取类路径下文件 输入流
	 * @author qye.zheng
	 * 
	 * @param subpath 文件路径
	 *  传参 : 必须以 / 开头，子路径必须存在（不存在则抛异常）
	 *  返回 : - 以路径分隔符结尾
	 * @return
	 */
	public static InputStream getInputStream(final String subpath) {
		final InputStream input = ClassPathUtil.class.getResourceAsStream(subpath);
		
		return input;
	}
	
}
