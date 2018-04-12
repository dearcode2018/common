/**
 * 描述: 
 * Week.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.constant.ext;

import com.hua.face.IEnumCommon;
import com.hua.util.StringUtil;

/**
 * 描述:  枚举
 * 
 * @author qye.zheng
 * Week
 */
public enum Week implements IEnumCommon<Week> {

	/*
	 * 枚举公开实例
	 */
	
	// 星期日
	SUNDAY("星期日", "Sunday"), 
	
	// 星期一
	MONDAY("星期一", "Monday"),
	
	// 星期二
	TUESDAY("星期二", "Tuesday"),
	
	// 星期三
	WEDNESDAY("星期三", "Wednesday"),
	
	// 星期四
	THURSDAY("星期四", "Thursday"),
	
	// 星期五
	FRIDAY("星期五", "Friday"),
	
	// 星期六
	SATURDAY("星期六", "Saturday");
		
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
	private Week()
	{
	}
	
	/**
	 * 
	 * 构造方法 - 私有化
	 * 描述: 
	 * @author qye.zheng
	 * @param name
	 */
	private Week(final String name) {
		this.name = name;
	}
	
	private Week(String name, String value)
	{
		this.name = name;
		this.value = value;
	}

	private Week(String name, String value, String description)
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
	public static Week getInstance(final String value)
	{
		Week instance = null;
		if (StringUtil.isEmpty(value))
		{
			return instance;
		}
		final Week[] array = Week.values();
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
