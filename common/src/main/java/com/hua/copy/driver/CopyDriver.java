/**
 * 描述: 
 * CopyDriver.java
 * @author	qye.zheng
 *  version 1.0
 */
package com.hua.copy.driver;

import java.io.File;

import com.hua.bean.copy.CopyParam;
import com.hua.constant.ProjectConstant;
import com.hua.util.EmptyUtil;
import com.hua.util.FileUtil;
import com.hua.util.StringUtil;

/**
 * 描述: (公共)拷贝 - 驱动器
 * @author  qye.zheng
 * CopyDriver
 */
public class CopyDriver
{

	/**
	 调用流程:
	 1) 处理目录: 调用目录处理器
	 
	 2) 文件处理: 先处理文件内容，然后再对文件进行重命名
	 
	 说明: 文件内容处理器是通用的，可以在符合拷贝参数的条件下，
	 调用此方法进行单独的处理.
	 
	 */
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 */
	public CopyDriver()
	{
	}
	
	/**
	 * 
	 * 描述: 目录处理器
	 * 包名(目录名)-处理
	 * @author  qye.zheng
	 * @param directory
	 * @param copyParam
	 * @return
	 */
	public static final boolean directoryHandler(final File directory, final CopyParam copyParam)
	{
		boolean flag = false;
		try
		{
			/*
			 对符合条件的目录进行重命名
			 1) constains(regex) 普通字符串，replace
			 2) 否则，正则表达式，replaceAll
			 */
			final File[] files = directory.listFiles();
			String diretoryName = null;
			if (!EmptyUtil.isEmpty(files))
			{
				for (File file : files)
				{
					if (file.isDirectory())
					{
						// 目录
						diretoryName = file.getName();
						if (diretoryName.contains(copyParam.getPackageNameMatch()) 
								&& !StringUtil.isEmpty(copyParam.getPackageNameValue()))
						{
							System.out.print("对目录: " + diretoryName + " 进行重命名");
							// 普通字符串，replace
							diretoryName = diretoryName.replace(copyParam.getPackageNameMatch(), 
									copyParam.getPackageNameValue());
							System.out.println(", 重命名为: " + diretoryName);
						}/* else
						{
							// 正则表达式，replaceAll
							diretoryName = diretoryName.replaceAll(copyParam.getPackageNameMatch(), 
									copyParam.getPackageNameValue());
						}*/
						// 执行重命名
						FileUtil.rename(file, diretoryName);
						// 继续递归调用
						directoryHandler(file, copyParam);
					}
				}
			}
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}

	/**
	 * 
	 * 描述: 文件处理器
	 * 文件内容/文件名-处理
	 * 搜索名称符合条件
	 * 对内容执行 replaceAll 正则替换
	 * 执行完毕之后，对该文件名进行重命名
	 * 1) constains(regex) 则是普通字符串，replace即可
	 * 2) 否则，视为正则表达式，replaceAll - 放弃实现
	 * @author  qye.zheng
	 * @param file
	 * @param copyParam
	 * @return
	 */
	public static final boolean fileHandler(final File file, final CopyParam copyParam)
	{
		boolean flag = false;
		try
		{
			if (file.isDirectory())
			{
				// 目录
				final File[] files = file.listFiles();
				for (File f : files)
				{
					// 递归调用
					fileHandler(f, copyParam);
				}
			} else
			{
				// 文件，直接进行处理
				/*
			 	1) constains(regex) 则是普通字符串，replace
  				2) 否则，视为正则表达式，replaceAll (放弃实现)
				 */
				String filename = file.getName();
				// 将 package-info.java 文件 纳入处理目标范围
				if (filename.contains(copyParam.getClassNameMatch()) || 
						ProjectConstant.FILE_PACKAGE_INFO.equals(filename))
				{
					System.out.println("原文件名 : " + filename);
					// 普通字符串，replace
					filename = filename.replace(copyParam.getClassNameMatch(), 
							copyParam.getClassNameValue());
					
					System.out.println("调用文件内容处理器...");
					// 文件内容处理器
					contentHandler(file, copyParam);
					
					System.out.println("重命名该文件为: " + filename);
					// 重命名该文件
					FileUtil.rename(file, filename);
				}
				// 放弃实现以下
				/* else
				{
					// 正则表达式，replaceAll
					filename = filename.replaceAll(copyParam.getClassNameMatch(), 
							copyParam.getClassNameValue());
					// 文件内容处理器
					contentHandler(file, copyParam);
					// 重命名该文件
					FileUtil.rename(file, filename);
				}*/
			}
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 文件内容处理器
	 * 有特殊需求，只需遵循拷贝参数配置，即可实施对
	 * 所提供文件的内容进行正则替换
	 * @author  qye.zheng
	 * @param file 文件对象
	 * @param copyParam
	 */
	public static final void contentHandler(final File file, final CopyParam copyParam)
	{
		/** begin 异常拦截段 */
		// 拦截目录
		if (file.isDirectory())
		{
			System.out.println("文件内容处理器: 不支持对目录直接处理!");
			
			return;
		}
		/** end of 异常拦截段 */

		String content = FileUtil.getString(file);
		// 执行正则替换
		content = content.replaceAll(copyParam.getNameRegex(), copyParam.getNameValue());
		content = content.replaceAll(copyParam.getPackageNameMatch(), copyParam.getPackageNameValue());
		content = content.replaceAll(copyParam.getClassNameMatch(), copyParam.getClassNameValue());
		content = content.replaceAll(copyParam.getDescriptionRegex(), copyParam.getDescriptionValue());
		content = content.replaceAll(copyParam.getBelongBranchRegex(), copyParam.getBelongBranchValue());
		// 自定义正则
		if (!EmptyUtil.isEmpty(copyParam.getCustomRegexs()) && !EmptyUtil.isEmpty(copyParam.getCustomValues()))
		{
			final int length = copyParam.getCustomRegexs().length;
			int countCustomRegex = 0;
			for (int i = 0; i < length; i++)
			{
				content = content.replaceAll(copyParam.getCustomRegexs()[i], copyParam.getCustomValues()[i]);
				countCustomRegex++;
			}
			System.out.println("使用自定义正则[ " + countCustomRegex + " ]次");
		}
		// 输出到文件
		FileUtil.writeString(file, content);
	}
	
}
