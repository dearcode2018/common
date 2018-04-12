/**
 * 描述: 
 * ChinaArea.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.constant.ext;

import com.hua.face.IEnumCommon;
import com.hua.util.StringUtil;

/**
 * 描述:  中国区域 - 枚举
 * 
 * @author qye.zheng
 * ChinaArea
 */
public enum ChinaArea implements IEnumCommon<ChinaArea> {

	/*
	 * 枚举公开实例
	 */
	
	EAST_CHINA("华东", "East China"),
	
	WEST_CHINA("华西", "West China"),
	
	SOUTH_CHINA("华南", "South China"),
	
	NORTH_CHINA("华北", "North China"),
	
	CENTRAL_CHINA("华中", "Central China");
	
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
	private ChinaArea()
	{
	}
	
	/**
	 * 
	 * 构造方法 - 私有化
	 * 描述: 
	 * @author qye.zheng
	 * @param name
	 */
	private ChinaArea(final String name) {
		this.name = name;
	}
	
	private ChinaArea(String name, String value)
	{
		this.name = name;
		this.value = value;
	}

	private ChinaArea(String name, String value, String description)
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
	public static ChinaArea getInstance(final String value)
	{
		ChinaArea instance = null;
		if (StringUtil.isEmpty(value))
		{
			return instance;
		}
		final ChinaArea[] array = ChinaArea.values();
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
