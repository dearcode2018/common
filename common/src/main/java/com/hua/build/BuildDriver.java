/**
 * BuildDriver.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.build;

import java.io.File;

/**
 * BuildDriver
 * 描述: 项目构建驱动
 * 用于完成项目构建的各种任务
 * @author  qye.zheng
 */
public final class BuildDriver
{

	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	private BuildDriver()
	{
	}
	
	/**
	 * 
	 * 描述: 项目手动构建结束后，需要删除
	 * 一些临时文件
	 * @author  qye.zheng
	 * @return
	 */
	public static boolean deleteTemporaryFile()
	{
		/**
		通过String projectRootPath = System.getProperty("user.dir");
		获取项目的根路径
		 删除 目录根路径下的目录
		/src/com/hua/templateAdd
		/src/com/hua/util/templateAdd
		/test/com/hua/test/templateAdd
		 
		 */
		String projectRootPath = System.getProperty("user.dir");
		boolean flag = false;
		// 路径数组 (若有变动，可以调整数组)
		final String[] pathArray = {"/src/com/hua/templateAdd",
				"/src/com/hua/util/templateAdd", "/test/com/hua/test/templateAdd"};
		int successCount = 0;
		int failureCount = 0;
		File file = null;
		for (int i = 0; i < pathArray.length; i++)
		{
			// 构造文件对象
			file = new File(projectRootPath + pathArray[i]);
			
			// 执行删除
			flag = file.delete();
			if (flag)
			{
				System.out.println("文件/目录 [" + file.getAbsolutePath() + "]  删除成功");
				successCount++;
			} else 
			{
				System.out.println("文件/目录 [" + file.getAbsolutePath() + "]  删除失败");
				failureCount++;
			}
		}
		System.out.println("删除 " + successCount + " 个文件/目录成功");
		System.out.println("删除 " + failureCount + " 个文件/目录失败");
		
		return flag;
	}

}
