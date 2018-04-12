/**
 * 描述: 
 * Degree.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.constant.ext;

import com.hua.face.IEnumCommon;
import com.hua.util.StringUtil;

/**
 * 描述:  学历 - 枚举
 * 
 * @author qye.zheng
 * Degree
 */
public enum Degree implements IEnumCommon<Degree> {

	/*
	 * 枚举公开实例
	 */
	
	PRIMARY_AND_BELOW("小学及以下", "Primary And Below"),
	
	JUNIOR("初中", "Junior"),
	
	SENIOR("高中", "Senior"),
	
	TECHNICAL("中专", "Technical"),
	
	JUNIOR_COLLEGE("大专", "Junior College"),
	
	BACHELOR("本科", "Bachelor"),
	
	MASTER("硕士", "Master"),
	
	DOCTOR("博士", "Doctor"),
	
	POST_DOCTOR("博士后", "Post Doctor");
	
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
	private Degree()
	{
	}
	
	/**
	 * 
	 * 构造方法 - 私有化
	 * 描述: 
	 * @author qye.zheng
	 * @param name
	 */
	private Degree(final String name) {
		this.name = name;
	}
	
	private Degree(String name, String value)
	{
		this.name = name;
		this.value = value;
	}

	private Degree(String name, String value, String description)
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
	public static Degree getInstance(final String value)
	{
		Degree instance = null;
		if (StringUtil.isEmpty(value))
		{
			return instance;
		}
		final Degree[] array = Degree.values();
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
