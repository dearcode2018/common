/**
 * 描述: 
 * Month.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.constant.ext;

import com.hua.face.IEnumCommon;
import com.hua.util.StringUtil;

/**
 * 描述:  月份 - 枚举
 * 
 * @author qye.zheng
 * Month
 */
public enum Month implements IEnumCommon<Month> {

	/*
	 * 枚举公开实例
	 */
	
	// 一月
	JANUARY("一月", "January"), 
	
	// 二月
	FEBRUARY("二月", "February"),
	
	// 三月
	MARCH("三月", "March"),
	
	// 四月
	APRIL("四月", "April"), 
	
	// 五月
	MAY("五月", "May"),
	
	// 六月
	JUNE("六月", "June"),
	
	// 七月
	JULY("七月", "July"), 
	
	// 八月
	AUGUST("八月", "August"),
	
	// 九月
	SEPTEMBER("九月", "September"),
	
	// 十月
	OCTOBER("十月", "October"), 
	
	// 十一月
	NOVEMBER("十一月", "November"),
	
	// 十二月
	DECEMBER("十二月", "December");
	
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
	private Month()
	{
	}
	
	/**
	 * 
	 * 构造方法 - 私有化
	 * 描述: 
	 * @author qye.zheng
	 * @param name
	 */
	private Month(final String name) {
		this.name = name;
	}
	
	private Month(String name, String value)
	{
		this.name = name;
		this.value = value;
	}

	private Month(String name, String value, String description)
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
	public static Month getInstance(final String value)
	{
		Month instance = null;
		if (StringUtil.isEmpty(value))
		{
			return instance;
		}
		final Month[] array = Month.values();
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
