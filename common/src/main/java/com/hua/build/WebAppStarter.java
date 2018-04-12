/**
 * 描述: 
 * WebAppStarter.java
 * @author	qye.zheng
 * 
 *  version 1.0
 */
package com.hua.build;

import org.junit.Test;

import com.hua.classpath.ClassPathDriver;

/**
 * 描述: 启动器
 * @author  qye.zheng
 * 
 * WebAppStarter
 */
public final class WebAppStarter
{


	

	// 启动器模板
	/**
	 * 
	 * 描述: 将类路径转换为 Web App Libraries 方式
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void start()
	{
		/** ===== begin 驱动参数设置  ===== */
		// 设置例子
		
		
		/** ===== end of 驱动参数设置 ===== */

		// 启动驱动
		ClassPathDriver.transformToWebApp();
		
	}

}
