/**
 * 描述: 
 * CustomStatus.java
 * @author qye.zheng
 * 
 *  version 1.0
 */
package com.hua.constant.ext;

/**
 * 描述: 客户状态
 * @author qye.zheng
 * 
 * CustomStatus
 */
public enum CustomStatus
{
	/* 客户状态 (0-无效, 1-未激活, 2-正常) */
	
	// 0-无效
	INVALIDE(0), 
	
	// 1-未激活
	NOTACTIVATED(1), 
	
	// 2-正常
	NORMAL(2);
	
	/* 整型值 */
	private int value;
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	private CustomStatus(final int value)
	{
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public int getValue()
	{
		return value;
	}
	
}
