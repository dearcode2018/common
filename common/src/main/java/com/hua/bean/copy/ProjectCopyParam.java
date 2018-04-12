/**
 * ProjectCopyParam.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.bean.copy;

import com.hua.constant.Constant;
import com.hua.util.ArrayUtil;
import com.hua.util.StringUtil;

/**
 * ProjectCopyParam
 * 描述: 项目 - 拷贝参数
 * @author  qye.zheng
 */
public final class ProjectCopyParam extends CopyParam
{

	/*
	 项目名称 - 正则
	 */
	private String projectNameRegex;
	
	/*
	 项目名称 - 符合正则条件，替换值
	 */
	private String projectNameValue;

	// 单例
	private static ProjectCopyParam instance;
	
	// 初始化加载配置
	static
	{
		instance = new ProjectCopyParam();
		
		// 项目名
		instance.setProjectNameRegex(getProps().getProperty("copy.template.project.name.regex"));
		instance.setProjectNameValue(getProps().getProperty("copy.template.project.name.value"));
		// 给父类名称属性赋值
		instance.setNameRegex(getProps().getProperty("copy.template.project.name.regex"));
		instance.setNameValue(getProps().getProperty("copy.template.project.name.value"));
		
		// 类名
		instance.setClassNameMatch(getProps().getProperty("copy.template.project.class.name.match"));
		instance.setClassNameValue(getProps().getProperty("copy.template.project.class.name.value"));
		
		// 包名
		instance.setPackageNameMatch(getProps().getProperty("copy.template.project.package.name.match"));
		instance.setPackageNameValue(getProps().getProperty("copy.template.project.package.name.value"));
		
		// 描述信息
		instance.setDescriptionRegex(getProps().getProperty("copy.template.project.description.regex"));
		instance.setDescriptionValue(getProps().getProperty("copy.template.project.description.value"));
		
		// 所属分支
		instance.setBelongBranchRegex(getProps().getProperty("copy.template.project.belong.branch.regex"));
		instance.setBelongBranchValue(getProps().getProperty("copy.template.project.belong.branch.value"));
		
		// 自定义正则
		final String customRegex = getProps().getProperty("copy.template.project.custom.regexs");
		// 拦截空设置
		if (!StringUtil.isEmpty(customRegex))
		{
			final String[] custumRegexs = customRegex.split(Constant.COMMA);
			ArrayUtil.trim(custumRegexs);
			instance.setCustomRegexs(custumRegexs);
		}
		
		final String customValue = getProps().getProperty("copy.template.project.custom.values");
		// 拦截空设置
		if (!StringUtil.isEmpty(customValue))
		{
			final String[] custumValues = customValue.split(Constant.COMMA);
			ArrayUtil.trim(custumValues);
			instance.setCustomValues(custumValues);
		}
	}
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	public ProjectCopyParam()
	{
	}

	/**
	 * @return the projectNameRegex
	 */
	public final String getProjectNameRegex()
	{
		return projectNameRegex;
	}

	/**
	 * @param projectNameRegex the projectNameRegex to set
	 */
	public final void setProjectNameRegex(String projectNameRegex)
	{
		this.projectNameRegex = projectNameRegex;
	}

	/**
	 * @return the projectNameValue
	 */
	public final String getProjectNameValue()
	{
		return projectNameValue;
	}

	/**
	 * @param projectNameValue the projectNameValue to set
	 */
	public final void setProjectNameValue(String projectNameValue)
	{
		this.projectNameValue = projectNameValue;
	}

	/**
	 * @return the instance
	 */
	public static final ProjectCopyParam getInstance()
	{
		return instance;
	}
	
}
