/**
 * 描述: 
 * TelecomOperator.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.constant.ext;

import com.hua.face.IEnumCommon;
import com.hua.util.StringUtil;

/**
 * 描述:  电信运营商 - 枚举
 * 
 * @author qye.zheng
 * TelecomOperator
 */
public enum TelecomOperator implements IEnumCommon<TelecomOperator> {

	/*
	 * 枚举公开实例
	 */
	
	TELECOM("电信", "Telecom"),
	
	UNICOM("联通", "Unicom"),
	
	MOBILE("移动", "Mobile");
	
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
	private TelecomOperator()
	{
	}
	
	/**
	 * 
	 * 构造方法 - 私有化
	 * 描述: 
	 * @author qye.zheng
	 * @param name
	 */
	private TelecomOperator(final String name) {
		this.name = name;
	}
	
	private TelecomOperator(String name, String value)
	{
		this.name = name;
		this.value = value;
	}

	private TelecomOperator(String name, String value, String description)
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
	public static TelecomOperator getInstance(final String value)
	{
		TelecomOperator instance = null;
		if (StringUtil.isEmpty(value))
		{
			return instance;
		}
		final TelecomOperator[] array = TelecomOperator.values();
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
