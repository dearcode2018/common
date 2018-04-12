/**
 * RandomUtil.java
 * @author qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.util;

import java.util.Random;

/**
 * RandomUtil
 * 描述: 随机 工具类
 * @author qye.zheng
 * 
 */
public final class RandomUtil extends org.apache.commons.lang3.RandomStringUtils
{
	
	private static Random rd = new Random();
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	private RandomUtil()
	{
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param n
	 * @return
	 */
	public static int nextInt(final int n) {
		
		return rd.nextInt(n);
	}
}
