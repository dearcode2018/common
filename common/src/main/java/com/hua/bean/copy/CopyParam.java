/**
 * 描述: 
 * CopyParam.java
 * @author	qye.zheng
 *  version 1.0
 */
package com.hua.bean.copy;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.hua.util.ReadProperties;

/**
 * 描述: 公共 - 拷贝参数
 * @author  qye.zheng
 * CopyParam
 */
public class CopyParam
{
	
	/*
	 名称(由子类来提供值) - 正则
	 */
	private String nameRegex;
	
	/*
	 名称(由子类来提供值) - 符合正则条件，替换值
	 */
	private String nameValue;
	
	/*
	 	类名 -包含式匹配
	 */
	private String classNameMatch;
	
	/*
	 	类名 - 符合匹配条件，替换值
	 */
	private String classNameValue;
	
	/*
	 包名 - 包含式匹配
	 */
	private String packageNameMatch;
	
	/*
	 包名 - 符合匹配条件，替换值
	 */
	private String packageNameValue;
	
	/*
	 描述信息 - 正则
	 */
	private String descriptionRegex;
	
	/*
	 描述信息  - 符合正则条件，替换值
	 */
	private String descriptionValue;
	
	/*
	 所属分支  - 正则
	 */
	private String belongBranchRegex;	
	
	/*
	 所属分支 -符合正则条件，替换值
	 */
	private String belongBranchValue;
	
	/**
	 自定义正则 应该和 替换内容 一一对应
	 */
	
	/*
	 自定义正则(文件内容，多个用逗号隔开)
	 */
	private String[] customRegexs;
	
	/*
	 自定义正则(文件内容，多个用逗号隔开)  - 符合正则条件，替换值
	 */
	private String[] customValues;
	
	private static final String CONFIG_PATH = "/conf/properties/copy.properties";
	
	private static final ReadProperties props = new ReadProperties(CONFIG_PATH);
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 */
	public CopyParam()
	{
	}

	/**
	 * @return the classNameRegex
	 */
	public final String getClassNameMatch()
	{
		return classNameMatch;
	}

	/**
	 * @param classNameMatch the classNameRegex to set
	 */
	public final void setClassNameMatch(String classNameMatch)
	{
		this.classNameMatch = classNameMatch;
	}

	/**
	 * @return the classNameValue
	 */
	public final String getClassNameValue()
	{
		return classNameValue;
	}

	/**
	 * @param classNameValue the classNameValue to set
	 */
	public final void setClassNameValue(String classNameValue)
	{
		this.classNameValue = classNameValue;
	}

	/**
	 * @return the packageNameMatch
	 */
	public final String getPackageNameMatch()
	{
		return packageNameMatch;
	}

	/**
	 * @param packageNameMatch the packageNameMatch to set
	 */
	public final void setPackageNameMatch(String packageNameMatch)
	{
		this.packageNameMatch = packageNameMatch;
	}

	/**
	 * @return the packageNameValue
	 */
	public final String getPackageNameValue()
	{
		return packageNameValue;
	}

	/**
	 * @param packageNameValue the packageNameValue to set
	 */
	public final void setPackageNameValue(String packageNameValue)
	{
		this.packageNameValue = packageNameValue;
	}

	/**
	 * @return the descriptionRegex
	 */
	public final String getDescriptionRegex()
	{
		return descriptionRegex;
	}

	/**
	 * @param descriptionRegex the descriptionRegex to set
	 */
	public final void setDescriptionRegex(String descriptionRegex)
	{
		this.descriptionRegex = descriptionRegex;
	}

	/**
	 * @return the descriptionValue
	 */
	public final String getDescriptionValue()
	{
		return descriptionValue;
	}

	/**
	 * @param descriptionValue the descriptionValue to set
	 */
	public final void setDescriptionValue(String descriptionValue)
	{
		this.descriptionValue = descriptionValue;
	}

	/**
	 * @return the belongBranchRegex
	 */
	public final String getBelongBranchRegex()
	{
		return belongBranchRegex;
	}

	/**
	 * @param belongBranchRegex the belongBranchRegex to set
	 */
	public final void setBelongBranchRegex(String belongBranchRegex)
	{
		this.belongBranchRegex = belongBranchRegex;
	}

	/**
	 * @return the belongBranchValue
	 */
	public final String getBelongBranchValue()
	{
		return belongBranchValue;
	}

	/**
	 * @param belongBranchValue the belongBranchValue to set
	 */
	public final void setBelongBranchValue(String belongBranchValue)
	{
		this.belongBranchValue = belongBranchValue;
	}

	/**
	 * @return the customRegexs
	 */
	public final String[] getCustomRegexs()
	{
		return customRegexs;
	}

	/**
	 * @param customRegexs the customRegexs to set
	 */
	public final void setCustomRegexs(String[] customRegexs)
	{
		this.customRegexs = customRegexs;
	}

	/**
	 * @return the customValues
	 */
	public final String[] getCustomValues()
	{
		return customValues;
	}

	/**
	 * @param customValues the customValues to set
	 */
	public final void setCustomValues(String[] customValues)
	{
		this.customValues = customValues;
	}

	/**
	 * @return the configPath
	 */
	public static final String getConfigPath()
	{
		return CONFIG_PATH;
	}

	/**
	 * @return the props
	 */
	public static final ReadProperties getProps()
	{
		return props;
	}
	
	 /**
	 * @return the nameRegex
	 */
	public final String getNameRegex()
	{
		return nameRegex;
	}

	/**
	 * @param nameRegex the nameRegex to set
	 */
	public final void setNameRegex(String nameRegex)
	{
		this.nameRegex = nameRegex;
	}

	/**
	 * @return the nameValue
	 */
	public final String getNameValue()
	{
		return nameValue;
	}

	/**
	 * @param nameValue the nameValue to set
	 */
	public final void setNameValue(String nameValue)
	{
		this.nameValue = nameValue;
	}

	/**
	 * 描述: 
	 * @author qye.zhenge
	 * 
	 * @return
	 */
	@Override
	public String toString()
	{
		return new ReflectionToStringBuilder(this).toString();
	}

}
