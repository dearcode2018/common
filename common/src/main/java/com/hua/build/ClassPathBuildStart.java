/**
 * ClassPathBuildStart.java
 * @author qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.build;

import org.junit.Test;

import com.hua.classpath.ClassPathDriver;

/**
 * ClassPathBuildStart
 * 描述: 
 * @author qye.zheng
 * 
 */
public final class ClassPathBuildStart
{
	
	/**
	 * 
	 * 描述: 通过 Test 来加载生成 .classpath 文件
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void start() {
		// 调用启动
		ClassPathDriver.classPathHandler();
	}
	
}
