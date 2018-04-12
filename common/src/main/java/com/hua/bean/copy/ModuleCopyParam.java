/**
 * ModuleCopyParam.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.bean.copy;

import com.hua.constant.Constant;
import com.hua.util.ArrayUtil;
import com.hua.util.StringUtil;

/**
 * ModuleCopyParam
 * 描述: 模块 - 拷贝参数
 * @author  qye.zheng
 */
public final class ModuleCopyParam extends CopyParam
{

	/*
	 模块名称  - 正则
	 */
	private String moduleNameRegex;
	
	/*
	 模块名称 - 符合正则条件，替换值
	 */
	private String moduleNameValue;
	
	// 单例
	private static ModuleCopyParam instance;
	
	// 初始化加载配置
	static
	{
		instance = new ModuleCopyParam();

		// 模块名
		instance.setModuleNameRegex(getProps().getProperty("copy.template.module.name.regex"));
		instance.setModuleNameValue(getProps().getProperty("copy.template.module.name.value"));
		// 给父类名称属性赋值
		instance.setNameRegex(getProps().getProperty("copy.template.module.name.regex"));
		instance.setNameValue(getProps().getProperty("copy.template.module.name.value"));
		
		// 类名
		instance.setClassNameMatch(getProps().getProperty("copy.template.module.class.name.match"));
		instance.setClassNameValue(getProps().getProperty("copy.template.module.class.name.value"));
		
		// 包名
		instance.setPackageNameMatch(getProps().getProperty("copy.template.module.package.name.match"));
		instance.setPackageNameValue(getProps().getProperty("copy.template.module.package.name.value"));
		
		// 描述信息
		instance.setDescriptionRegex(getProps().getProperty("copy.template.module.description.regex"));
		instance.setDescriptionValue(getProps().getProperty("copy.template.module.description.value"));
		
		// 所属分支
		instance.setBelongBranchRegex(getProps().getProperty("copy.template.module.belong.branch.regex"));
		instance.setBelongBranchValue(getProps().getProperty("copy.template.module.belong.branch.value"));
		
		// 自定义正则
		final String customRegex = getProps().getProperty("copy.template.module.custom.regexs");
		// 拦截空设置
		if (!StringUtil.isEmpty(customRegex))
		{
			final String[] custumRegexs = customRegex.split(Constant.COMMA);
			ArrayUtil.trim(custumRegexs);
			instance.setCustomRegexs(custumRegexs);
		}
		
		final String customValue = getProps().getProperty("copy.template.module.custom.values");
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
	public ModuleCopyParam()
	{
	}

	/**
	 * @return the moduleNameRegex
	 */
	public final String getModuleNameRegex()
	{
		return moduleNameRegex;
	}

	/**
	 * @param moduleNameRegex the moduleNameRegex to set
	 */
	public final void setModuleNameRegex(String moduleNameRegex)
	{
		this.moduleNameRegex = moduleNameRegex;
	}

	/**
	 * @return the moduleNameValue
	 */
	public final String getModuleNameValue()
	{
		return moduleNameValue;
	}

	/**
	 * @param moduleNameValue the moduleNameValue to set
	 */
	public final void setModuleNameValue(String moduleNameValue)
	{
		this.moduleNameValue = moduleNameValue;
	}

	/**
	 * @return the instance
	 */
	public static final ModuleCopyParam getInstance()
	{
		return instance;
	}

}
