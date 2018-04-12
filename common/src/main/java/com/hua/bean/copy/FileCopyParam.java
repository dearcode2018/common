/**
 * FileCopyParam.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.bean.copy;

import com.hua.constant.Constant;
import com.hua.util.ArrayUtil;
import com.hua.util.StringUtil;

/**
 * FileCopyParam
 * 描述: 文件 - 拷贝参数
 * @author  qye.zheng
 */
public final class FileCopyParam extends CopyParam
{

	/*
	 文件主题  - 正则
	 */
	private String fileThemeRegex;
	
	/*
	 文件主题 - 符合正则条件，替换值
	 */
	private String fileThemeValue;
	
	// 单例
	private static FileCopyParam instance;
	
	// 初始化加载配置
	static
	{
		instance = new FileCopyParam();
		
		// 文件主题
		instance.setFileThemeRegex(getProps().getProperty("copy.template.file.theme.regex"));
		instance.setFileThemeValue(getProps().getProperty("copy.template.file.theme..value"));
		// 给父类名称属性赋值
		instance.setNameRegex(getProps().getProperty("copy.template.file.theme.regex"));
		instance.setNameValue(getProps().getProperty("copy.template.file.theme.value"));
		
		// 类名
		instance.setClassNameMatch(getProps().getProperty("copy.template.file.class.name.match"));
		instance.setClassNameValue(getProps().getProperty("copy.template.file.class.name.value"));
		
		// 包名
		instance.setPackageNameMatch(getProps().getProperty("copy.template.file.package.name.match"));
		instance.setPackageNameValue(getProps().getProperty("copy.template.file.package.name.value"));
		
		// 描述信息
		instance.setDescriptionRegex(getProps().getProperty("copy.template.file.description.regex"));
		instance.setDescriptionValue(getProps().getProperty("copy.template.file.description.value"));
		
		// 所属分支
		instance.setBelongBranchRegex(getProps().getProperty("copy.template.file.belong.branch.regex"));
		instance.setBelongBranchValue(getProps().getProperty("copy.template.file.belong.branch.value"));

		// 自定义正则
		final String customRegex = getProps().getProperty("copy.template.file.custom.regexs");
		// 拦截空设置
		if (!StringUtil.isEmpty(customRegex))
		{
			final String[] custumRegexs = customRegex.split(Constant.COMMA);
			ArrayUtil.trim(custumRegexs);
			instance.setCustomRegexs(custumRegexs);
		}
		
		final String customValue = getProps().getProperty("copy.template.file.custom.values");
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
	public FileCopyParam()
	{
	}

	/**
	 * @return the fileThemeRegex
	 */
	public final String getFileThemeRegex()
	{
		return fileThemeRegex;
	}

	/**
	 * @param fileThemeRegex the fileThemeRegex to set
	 */
	public final void setFileThemeRegex(String fileThemeRegex)
	{
		this.fileThemeRegex = fileThemeRegex;
	}

	/**
	 * @return the fileThemeValue
	 */
	public final String getFileThemeValue()
	{
		return fileThemeValue;
	}

	/**
	 * @param fileThemeValue the fileThemeValue to set
	 */
	public final void setFileThemeValue(String fileThemeValue)
	{
		this.fileThemeValue = fileThemeValue;
	}

	/**
	 * @return the instance
	 */
	public static final FileCopyParam getInstance()
	{
		return instance;
	}

}
