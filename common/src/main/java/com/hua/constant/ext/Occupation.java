/**
 * 描述: 
 * Occupation.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.constant.ext;

import com.hua.face.IEnumCommon;
import com.hua.util.StringUtil;

/**
 * 描述:  职业 - 枚举
 * 
 * @author qye.zheng
 * Occupation
 */
public enum Occupation implements IEnumCommon<Occupation> {

	/*
	 * 枚举公开实例
	 */
	
	STUDENT("在校学生", "Student"),
	
	JOB_HOLDER("固定工作者", "Job Holder"),
	
	FREE_LANCER("自由职业者", "Free Lancer"),
	
	UNEMPLOYED("待业/无业/失业", "Unemployed"),
	
	RETIRE("退休", "Retire"),
	
	OTHER("其他", "Other");
	
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
	private Occupation()
	{
	}
	
	/**
	 * 
	 * 构造方法 - 私有化
	 * 描述: 
	 * @author qye.zheng
	 * @param name
	 */
	private Occupation(final String name) {
		this.name = name;
	}
	
	private Occupation(String name, String value)
	{
		this.name = name;
		this.value = value;
	}

	private Occupation(String name, String value, String description)
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
	public static Occupation getInstance(final String value)
	{
		Occupation instance = null;
		if (StringUtil.isEmpty(value))
		{
			return instance;
		}
		final Occupation[] array = Occupation.values();
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
