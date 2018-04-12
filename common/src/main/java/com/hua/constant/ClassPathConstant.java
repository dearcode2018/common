/**
 * 描述: 
 * ClassPathConstant.java
 * @author	qye.zheng
 *  version 1.0
 */
package com.hua.constant;

/**
 * 描述: 类路径 - 常量
 * @author  qye.zheng
 * ClassPathConstant
 */
public interface ClassPathConstant
{
	String ATTRIBUTE_KIND = "kind";
	
	String ATTRIBUTE_PATH = "path";
	
	String ATTRIBUTE_SOURCE_PATH = "sourcepath";
	
	String ATTRIBUTE_NAME = "name";
	
	String ATTRIBUTE_VALUE = "value";
	
	// 类库的相对路径
	String RELATIVE_PATH_LIB = "lib/";
	
	// Web 项目类库的相对路径
	String WEB_RELATIVE_PATH_LIB = "WebRoot/WEB-INF/lib/";
	
	// 源代码的相对路径
	String RELATIVE_PATH_SOURCE = "doc/source/";
	
	// 源码文件的后缀 zip
	String SOURCE_SUFFIX_ZIP = "-sources.zip";
	
	// 源码文件的后缀 jar
	String SOURCE_SUFFIX_JAR = "-sources.jar";
	
	String CON_PATH_INTERNAL_WEB = "org.eclipse.jst.j2ee.internal.web.container";
	
	String CON_PATH_LAUNCHING_WEB_PROJECT = "org.eclipse.wst.jsdt.launching.WebProject";
	
	String WEB_APP_CLASSPATH_FILE = "/conf/classpath/WebApp.classpath";
	
	String PROJECT_CLASSPATH = ".classpath";
}
