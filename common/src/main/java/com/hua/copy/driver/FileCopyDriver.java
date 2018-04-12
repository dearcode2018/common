/**
 * FileCopyDriver.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.copy.driver;

import java.io.File;

import com.hua.bean.copy.FileCopyParam;
import com.hua.util.ProjectUtil;

/**
 * FileCopyDriver
 * 描述: 文件 - 拷贝驱动器
 * @author  qye.zheng
 */
public final class FileCopyDriver extends CopyDriver
{
	private static final String PATH = ProjectUtil.getAbsolutePath("/doc/file/", true);
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	private FileCopyDriver()
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
		System.out.println("文件 - 模板拷贝...");
		final File directory = new File(PATH);
		final FileCopyParam copyParam = FileCopyParam.getInstance();
		
		return fileHandler(directory, copyParam);
	}
	
}
