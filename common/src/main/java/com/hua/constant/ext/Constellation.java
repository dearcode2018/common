/**
 * 描述: 
 * Constellation.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.constant.ext;

import com.hua.face.IEnumCommon;
import com.hua.util.StringUtil;

/**
 * 描述: 星座 - 枚举
 * 
 * @author qye.zheng
 * Constellation
 */
public enum Constellation implements IEnumCommon<Constellation> {

	/*
	 * 枚举公开实例
	 */
	
	// 星座(水瓶座/双鱼座/白羊座/金牛座/双子座/巨蟹座/狮子座/处女座/天秤座/天蝎座/射手座/摩羯座)
	
	AQUARIUS("水瓶座", "Aquarius"),
	
	PISCES("双鱼座", "Pisces"),
	
	ARIES("白羊座", "Aries"),
	
	TAURUS("金牛座", "Taurus"),
	
	GEMINI("双子座", "Gemini"),
	
	CANCER("巨蟹座", "Cancer"),
	
	LEO("狮子座", "Leo"),
	
	VIRGO("处女座", "Virgo"),
	
	LIBRA("天秤座", "Libra"),
	
	SCORPIUS("天蝎座", "Scorpius"),
	
	SAGITTARIUS("射手座", "Sagittarius"),
	
	CAPRICORN("摩羯座", "Capricorn");
	
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
	private Constellation()
	{
	}
	
	/**
	 * 
	 * 构造方法 - 私有化
	 * 描述: 
	 * @author qye.zheng
	 * @param name
	 */
	private Constellation(final String name) {
		this.name = name;
	}
	
	private Constellation(String name, String value)
	{
		this.name = name;
		this.value = value;
	}

	private Constellation(String name, String value, String description)
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
	public static Constellation getInstance(final String value)
	{
		Constellation instance = null;
		if (StringUtil.isEmpty(value))
		{
			return instance;
		}
		final Constellation[] array = Constellation.values();
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
