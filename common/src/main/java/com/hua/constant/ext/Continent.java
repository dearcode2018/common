/**
 * 描述: 
 * Continent.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.constant.ext;

import com.hua.face.IEnumCommon;
import com.hua.util.StringUtil;

/**
 * 描述:  地理 洲 - 枚举
 * 
 * @author qye.zheng
 * Continent
 */
public enum Continent implements IEnumCommon<Continent> {

	/*
	 * 枚举公开实例
	 */
	
	ASIA("亚洲", "Asia"),
	
	OCEANIA("大洋洲", "Oceania"),
	
	NORTH_AMERICA("北美洲", "North America"),
	
	SOUTH_AMERICA("南美洲", "Soth America"),
	
	EUROPE("欧洲", "Europe"),
	
	AFRICA("非洲", "Africa"),
	
	ANTARCTICA("南极洲", "Antarctica");
	
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
	private Continent()
	{
	}
	
	/**
	 * 
	 * 构造方法 - 私有化
	 * 描述: 
	 * @author qye.zheng
	 * @param name
	 */
	private Continent(final String name) {
		this.name = name;
	}
	
	private Continent(String name, String value)
	{
		this.name = name;
		this.value = value;
	}

	private Continent(String name, String value, String description)
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
	public static Continent getInstance(final String value)
	{
		Continent instance = null;
		if (StringUtil.isEmpty(value))
		{
			return instance;
		}
		final Continent[] array = Continent.values();
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
