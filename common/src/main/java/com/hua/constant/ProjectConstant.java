/**
 * ProjectConstant.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.constant;

/**
 * ProjectConstant
 * 描述: 项目规划 - 常量 
 * @author  qye.zheng
 */
public interface ProjectConstant
{

	
	/** begin 项目 - 工作空间 */
	
	// 工作集 - 配置 -  输入 相对路径 (类相对路径)
	String WORKING_SET_INPUT_RELATIVE_PATH = "/conf/xml/ws/";
	
	// 工作集 - 配置 -  输出 相对路径
	String WORKING_SET_OUTPUT_RELATIVE_PATH = "/.metadata/.plugins/org.eclipse.ui.workbench/";
	
	// workbench.xml 配置文件名
	String WORKING_SET_WORK_BENACH = "workbench.xml";
	
	// workingsets.xml配置文件名
	String WORKING_SET_WORKING_SET = "workingsets.xml";
	
	/** end of 项目 - 工作空间 */
	
	String SETTING_COMMON_COMPONENT_RELATIVE_PATH = "/.settings/org.eclipse.wst.common.component";
	
	String SETTING_JSDTSCOPE_RELATIVE_PATH = "/.settings/.jsdtscope";
	
	//String WEB_INF_WEB_XML_RELATIVE_PATH = "/";

	String PROJECT_RELATIVE_PATH_SRC = "/src/main/java";
	
	String PROJECT_RELATIVE_PATH_RESOURCE = "/src/main/resources";
	
	String PROJECT_RELATIVE_PATH_TEST = "/src/test/java";
	
	String PROJECT_RELATIVE_PATH_DOC = "/doc";

	String PROJECT_RELATIVE_PATH_CONF_PROPERTIES = "/conf/properties";
	
	String PROJECT_RELATIVE_PATH_CONF_XML = "/conf/xml";
	
	String PROJECT_TXT_DESCRIPTION_FILE = "project.txt";
	
	String PROJECT_RELATIVE_PATH_ = "";
	
	String FILE_PACKAGE_INFO = "package-info.java";
	
}
