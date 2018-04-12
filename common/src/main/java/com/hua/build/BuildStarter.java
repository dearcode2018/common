/**
 * 描述: 
 * BuildStarter.java
 * @author	qye.zheng
 * 
 *  version 1.0
 */
package com.hua.build;

import org.junit.Test;

/**
 * 描述: 项目构建 - 启动器
 * @author  qye.zheng
 * 
 * BuildStarter
 */
public final class BuildStarter
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
		
		// 删除临时文件
		BuildDriver.deleteTemporaryFile();
		
		/** ===== end of 驱动参数设置 ===== */

		// 启动驱动
		
		
	}

}
