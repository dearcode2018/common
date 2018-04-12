/**
  * @filename WebParam.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.base;

import com.hua.util.ReadProperties;

 /**
 * @type WebParam
 * @description  
 * @author qye.zheng
 */
public final class WebParam {

	/** 部署路径 */
	public static final String DEPLOY_PATH = "/WEB-INF/classes";
	
	/** 源码路径 */
	public static final String[] SOURCE_PATH = {"/src/main/java", "/src/main/resources"};
	
	/** 测试源码路径 */
	public static final String[] TEST_SOURCE_PATH = {"/src/test/java", "/src/test/resources"};
	
	/** web部署 根 */
	public static final String WEB_DEPLOY_PATH = "/";
	
	/** web源码路径 */
	public static final String WEB_SOURCE_PATH = "/src/main/webapp";
	
	/** 属性: 上下文 根 */
	public static final String PROPERTY_CONTEXT_ROOT = "context-root";
	
	/** 属性: java 输出路径 */
	public static final String PROPERTY_OUTPUT_PATH = "java-output-path";

	/** 普通 java 项目类文件路径 */
	public static final String NORMAL_PROJECT_CLASSPATH_PATH = "/conf/xml/normal-classpath.xml";
	
	
	/** java web项目类文件路径 */
	public static final String WEB_PROJECT_CLASSPATH_PATH = "/conf/xml/web-classpath.xml";
	
	/** 类路径 文件名 */
	public static final String CLASSPATH_NAME = ".classpath";
	
	/* 项目名称 */
	private String projectName;
	
	/* 排除掉某些项目 */
	private String projectExcludeRegex;
	
	/* 是否包含测试 */
	private boolean includeTest = false;

	private static final String CONFIG_PATH = "/conf/properties/web.properties";
	
	private static final ReadProperties props = new ReadProperties(CONFIG_PATH);
	
	// 单例
	private static WebParam instance;
	
	// 初始化加载配置
	static
	{
		instance = new WebParam();
		
		instance.setProjectName(props.getProperty("web.project.name"));
		instance.setIncludeTest(Boolean.valueOf(props.getProperty("deploy.path.include.test")));
	}
		
	/**
	 * @description 构造方法
	 * @author qye.zheng
	 */
	private WebParam() {
	}
		
	/**
	 * @return the projectName
	 */
	public final String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public final void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the includeTest
	 */
	public final boolean isIncludeTest() {
		return includeTest;
	}

	/**
	 * @param includeTest the includeTest to set
	 */
	public final void setIncludeTest(boolean includeTest) {
		this.includeTest = includeTest;
	}

	/**
	 * @return the instance
	 */
	public static final WebParam getInstance() {
		return instance;
	}
	
}
