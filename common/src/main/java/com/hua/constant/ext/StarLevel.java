/**
 * 描述: 
 * StarLevel.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.constant.ext;

import com.hua.face.IEnumCommon;
import com.hua.util.StringUtil;

/**
 * 描述: 星级  - 枚举
 * 
 * @author qye.zheng
 * StarLevel
 */
public enum StarLevel implements IEnumCommon<StarLevel> {

	// 星等级: 1- 5 星
	
	/*
	 * 枚举公开实例
	 */
	
	ONE("一星", "One"),
	
	TWO("二星", "Two"),
	
	THREE("三星", "Three"),
	
	FOUR("四星", "Four"),
	
	FIVE("五星", "Five");
	
	/*
	 父类Enum也有一个name属性，
	 父类name的值存储的是每个枚举
	 实例的字面值
	 */
	// 枚举实例的名称 (中文)
	private String name;
	
	// 值，code (英文/英文缩写)
	private String value;
	
	// 描述信息 (中文+ 英文描述)
	private String description;
	
	/**
	 * 无参构造方法 - 私有化
	 * 描述: 
	 * @author qye.zheng
	 */
	private StarLevel()
	{
	}
	
	/**
	 * 
	 * 构造方法 - 私有化
	 * 描述: 
	 * @author qye.zheng
	 * @param name
	 */
	private StarLevel(final String name) {
		this.name = name;
	}
	
	private StarLevel(String name, String value)
	{
		this.name = name;
		this.value = value;
	}

	private StarLevel(String name, String value, String description)
	{
		this.name = name;
		this.value = value;
		this.description = description;
	}

	/**
	 * 
	 * 描述: 根据value值获取枚举实例
	 * @author qye.zheng
	 * @param value
	 * @return
	 */
	public static StarLevel getInstance(final String value)
	{
		StarLevel instance = null;
		if (StringUtil.isEmpty(value))
		{
			return instance;
		}
		final StarLevel[] array = StarLevel.values();
		for (int i = 0; i < array.length; i++)
		{
			instance = array[i];
			if (instance.getValue().equals(value))
			{
				return instance;
			}
		}
		
		return instance ;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}
	
}
