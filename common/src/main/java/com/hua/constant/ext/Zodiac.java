/**
 * 描述: 
 * Zodiac.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.constant.ext;

import com.hua.face.IEnumCommon;
import com.hua.util.StringUtil;

/**
 * 描述:  12生肖 - 枚举
 * 
 * @author qye.zheng
 * Zodiac
 */
public enum Zodiac implements IEnumCommon<Zodiac> {

	/*
	 * 枚举公开实例
	 */
	
	MOUSE("鼠", "Mouse"),
	
	CATTLE("牛", "Cattle"),
	
	TIGER("虎", "Tiger"),
	
	RABBIT("兔", "Rabbit"),
	
	DRAGON("龙", "Dragon"),
	
	SNAKE("蛇", "Snake"),
	
	HORSE("马", "Horse"),
	
	SHEEP("羊", "Sheep"),
	
	MONKEY("猴", "Monkey"),
	
	CHICK("鸡", "Chick"),
	
	DOG("狗", "Dog"),
	
	PIG("猪", "Pig");
	
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
	private Zodiac()
	{
	}
	
	/**
	 * 
	 * 构造方法 - 私有化
	 * 描述: 
	 * @author qye.zheng
	 * @param name
	 */
	private Zodiac(final String name) {
		this.name = name;
	}
	
	private Zodiac(String name, String value)
	{
		this.name = name;
		this.value = value;
	}

	private Zodiac(String name, String value, String description)
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
	public static Zodiac getInstance(final String value)
	{
		Zodiac instance = null;
		if (StringUtil.isEmpty(value))
		{
			return instance;
		}
		final Zodiac[] array = Zodiac.values();
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
