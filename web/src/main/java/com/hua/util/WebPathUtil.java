/**
 * 描述: 
 * WebPathUtil.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.util;

import java.io.InputStream;
import java.net.URL;

import javax.servlet.ServletContext;

/**
 * 描述: web 路径 工具类
 * 获取多种绝对路径
 * web路径(根路径)、目标web的路径、指定的web路径
 * 
 * 路径参数 : 统一以 / 开头
 * 
 * @author qye.zheng
 * WebPathUtil
 */
public final class WebPathUtil {
	
	
	/**
	 * web路径 : web项目 webroot一层的资源(除去WEB-INF)
	 */
	
	/** 无参构造方法 */
	private WebPathUtil() {}
	
	/**
	 * ServletRequest
	 * 
	 * ServletContext
	 * 
	 * Class getResousce(String) --> URL
	 * 
	 * 
	 * URL
	 * 
	 * ServletContext - getRealPath(String) - 真实物理路径
	 * ServletContext - getResource(String) --> URL
	 * URL - getPath()
	 * 
	 * 
	// request.getRealPath("/front"); 方式已经过时，不再使用此方式
	
	final ServletContext context = request.getServletContext();
	
	// web 根路径 (没有 路径分隔符)  - 建议返回采用此种方式
	log.info("doPost =====> " + context.getRealPath(""));
	
	// web 根路径 (有 路径分隔符)
	log.info("doPost =====> " + context.getRealPath("/"));
	
	// web 根路径下的子路径，子路径可以不存在  (有 路径分隔符) - 建议传参采用这种方式
	log.info("doPost =====> " + context.getRealPath("/front/test"));
	
	// web 根路径下的子路径，子路径可以不存在  (有 路径分隔符)
	log.info("doPost =====> " + context.getRealPath("front/test2"));
	
	
	// 获取资源的 url 对象 （以根路径为参） - 获取的是相对路径 (/localhost/appName/)
	// 一般不使用 URL 对象来获取 web 的路径信息
	URL url = null;
	url = context.getResource("/");
	log.info("doPost =====> url.getPath1() = " + url.getPath());
	
	// 子路径为参 (获取失败 - 原因未详)
	//url = context.getResource("/front/test");
	//log.info("doPost =====> url.getPath2() = " + url.getPath());
	
	
	// Class (获取指定类的所在路径)
	Class<?> clazz = getClass();
	// 参数 : 空字符串 - 获取指定类的所在路径 ( 传指定的 Class<?> )
	url = clazz.getResource("");
	log.info("doPost =====> url.getPath3() = " + url.getPath());
	
	// 获取类的根路径 (.../WEB-INF/classes/) ( 不用传参 )
	url = clazz.getResource("/");
	log.info("doPost =====> url.getPath4() = " + url.getPath());
	
	// 获取类的根路径下的子路径 (.../WEB-INF/classes/com/hua/) - 子路径不存在会抛异常 ( 传子路径 )
	// 必须以 / 开头，子路径必须存在（不存在则抛异常）- 以路径分隔符结尾
	url = clazz.getResource("/com/hua");
	log.info("doPost =====> url.getPath5() = " + url.getPath());
	
	//  ( 传子路径 )
	// 必须以 / 开头，子路径必须存在 （不存在则抛异常）- 以路径分隔符结尾
	url = clazz.getResource("/com/hua/");
	log.info("doPost =====> url.getPath6() = " + url.getPath());
	 * 
	 * 
	 * 
	 * 
	 */
	
	/**
	 * web 根路径
	 * 
	 * web根路径下的子路径 (不存在 - 不抛异常)
	 * 
	 * 类根路径
	 * 
	 * 类根路径下的子路径 (不存在 - 抛异常)
	 * 
	 * 当前类所在的路径
	 * 
	 * 
	 */
	
	/**
	 * 
	 * 描述: 获取 web 根路径
	 * (末尾没有 路径分隔符)
	 * @author qye.zheng
	 * 
	 * @param context
	 * @return
	 */
	public static String getWebRootPath(final ServletContext context) {
		final String path = context.getRealPath("");
		
		return path;
	}
	
	/**
	 * 
	 * 描述: 获取 web 根路径下的子路径
	 *  (末尾没有 路径分隔符)
	 *  
	 *  传参方式 : /front/test
	 * @author qye.zheng
	 * 
	 * @param context
	 * @param subpath
	 * @return
	 */
	public static String getWebSubpath(final ServletContext context, final String subpath) {
		final String path = context.getRealPath(subpath);
		
		return path;
	}
	
	/**
	 * 
	 * 描述: 获取类的根路径
	 * 
	 *   (.../WEB-INF/classes/) ( 不用传参 )
	 * @author qye.zheng
	 * 
	 * @return
	 */
	public static String getClassRootPath() {
		final Class<?> clazz = WebPathUtil.class;
		final URL url = clazz.getResource("/");
		final String path = url.getPath();
		
		return path;
	}
	
	/**
	 * 
	 * 描述: 获取类根路径下的子路径
	 * 子路径不存在会抛异常 ( 传子路径 )
	 * 必须以 / 开头，子路径必须存在（不存在则抛异常）- 以路径分隔符结尾
	 * 
	 * 规范传值 : /com/hua/
	 * 
	 * 返回 :  (.../WEB-INF/classes/com/hua/)
	 * @author qye.zheng
	 * 
	 * @param subpath
	 * @return
	 */
	public static String getClassSubpath(final String subpath) {
		final Class<?> clazz = WebPathUtil.class;
		final URL url = clazz.getResource(subpath);
		final String path = url.getPath();
		
		return path;
		
	}
	
	/**
	 * 
	 * 描述: 获取指定类的所在路径
	 * 
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
	 * 描述: 获取web路径下文件 输入流
	 * @author qye.zheng
	 * 
	 * @param subpath 文件路径
	 *  传参 : 必须以 / 开头，子路径必须存在（不存在则抛异常）
	 *  返回 : - 以路径分隔符结尾
	 * @return
	 */
	public static InputStream getInputStream(final ServletContext context, final String subpath) {
		final InputStream input = context.getResourceAsStream(subpath);
		
		return input;
	}
	
}
