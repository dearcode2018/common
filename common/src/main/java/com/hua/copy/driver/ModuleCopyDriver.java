/**
 * ModuleCopyDriver.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.copy.driver;

import java.io.File;

import com.hua.bean.copy.ModuleCopyParam;
import com.hua.util.ProjectUtil;

/**
 * ModuleCopyDriver
 * 描述: 模块 - 拷贝驱动器
 * @author  qye.zheng
 */
public final class ModuleCopyDriver extends CopyDriver
{

	private static final String PATH = ProjectUtil.getAbsolutePath("/doc/module/", true);
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	private ModuleCopyDriver()
	{
	}
	
	/**
	 * 
	 * 描述: 模板文件拷贝
	 * @author  qye.zheng
	 * @return
	 */
	public static final boolean templateCopy()
	{
		System.out.println("模块 - 模板拷贝...");
		final File directory = new File(PATH);
		final ModuleCopyParam copyParam = ModuleCopyParam.getInstance();
		boolean flag = false;
		try
		{
			// 调用目录处理器
			flag = directoryHandler(directory, copyParam);
			
			// 调用文件处理器
			flag =  fileHandler(directory, copyParam);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
}
