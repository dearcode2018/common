/**
 * 描述: 
 * AddOrOverrideStarter.java
 * @author	qye.zheng
 * 
 *  version 1.0
 */
package com.hua.maintain.starter;

import org.junit.Test;

import com.hua.maintain.driver.MaintainDriver;

/**
 * 描述: 启动器
 * @author  qye.zheng
 * 
 * AddOrOverrideStarter
 */
public final class AddOrOverrideStarter
{


	

	// 启动器模板
	/**
	 * 
	 * 描述: 
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
		MaintainDriver.addOrOverride();
	}

}
