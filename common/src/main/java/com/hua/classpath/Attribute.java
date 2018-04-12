/**
 * 描述: 
 * Attribute.java
 * @author	qye.zheng
 *  version 1.0
 */
package com.hua.classpath;

/**
 * 描述: 
 * @author  qye.zheng
 * Attribute
 */
public final class Attribute
{
	// 标签名称
	public static final String TAG_NAME = "attribute";
	
	private String name;
	
	private String value;

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value)
	{
		this.value = value;
	}
	
}
