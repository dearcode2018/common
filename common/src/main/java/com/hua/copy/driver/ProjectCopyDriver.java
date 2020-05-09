/**
 * ProjectCopyDriver.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.copy.driver;

import java.io.File;

import com.hua.bean.copy.ProjectCopyParam;
import com.hua.constant.ProjectConstant;
import com.hua.util.FileUtil;
import com.hua.util.ProjectUtil;
import com.hua.util.StringUtil;

/**
 * ProjectCopyDriver
 * 描述: 项目 - 拷贝驱动器
 * @author  qye.zheng
 */
public final class ProjectCopyDriver extends CopyDriver
{
	
	private static final String PATH = ProjectUtil.getProjectRootPath();
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	private ProjectCopyDriver()
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
		System.out.println("项目 -模板拷贝...");
		boolean flag = false;
		final ProjectCopyParam copyParam = ProjectCopyParam.getInstance();
		// src 目录
		final File srcDirectory = new File(PATH +ProjectConstant.PROJECT_RELATIVE_PATH_SRC);
		
		// test 目录
		final File testDirectory = new File(PATH +ProjectConstant.PROJECT_RELATIVE_PATH_TEST);
		try
		{
			// 调用目录处理器
			flag = directoryHandler(srcDirectory, copyParam);
			flag = directoryHandler(testDirectory, copyParam);
			
			// 调用文件处理器
			flag = fileHandler(srcDirectory, copyParam);
			flag = fileHandler(testDirectory, copyParam);
			
			// project 处理器
			flag = projectHandler(copyParam);
			
			// web 项目处理器
			if (ProjectUtil.isWeb(PATH))
			{
				System.out.println("web 项目处理器...");
				// web 项目
				flag = webHandler(copyParam);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: project 处理器
	 * 对拷贝后的项目进行处理
	 * @author  qye.zheng
	 * @return
	 */
	public static final boolean projectHandler(final ProjectCopyParam copyParam)
	{
		boolean flag = false;
		try
		{
			// 处理 doc 目录 (仅到doc下一级)
			final File docFile = new File(PATH + ProjectConstant.PROJECT_RELATIVE_PATH_DOC);
			
			// 处理 project.txt 文件
			final File projectTxt = new File(docFile.getAbsolutePath() + File.separator + 
					ProjectConstant.PROJECT_TXT_DESCRIPTION_FILE);
			// 调用内容处理器
			contentHandler(projectTxt, copyParam);
			
			// pom.xml 	<artifactId>ProjectNameTemplate</artifactId>
			final File pom = new File(PATH + "/pom.xml");
			// 调用内容处理器
			contentHandler(pom, copyParam);
			
			// 处理 doc 下除了 source 的目录，选择不递归
			renameHandler(docFile, false, copyParam);
			
			// 处理 resource 目录
			final File resource = new File(PATH + ProjectConstant.PROJECT_RELATIVE_PATH_RESOURCE);
			// 属性(*.properties)文件
			final File propertiesFile = new File(resource.getAbsolutePath() +
					ProjectConstant.PROJECT_RELATIVE_PATH_CONF_PROPERTIES);
			renameHandler(propertiesFile, true, copyParam);
			
			// xml 配置文件
			final File xmlFile = new File(resource.getAbsolutePath() +
					ProjectConstant.PROJECT_RELATIVE_PATH_CONF_XML);
			renameHandler(xmlFile, true, copyParam);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: web 处理器
	 * 专门针对web项目进行处理
	 * @author  qye.zheng
	 * @return
	 */
	public static final boolean webHandler(final ProjectCopyParam copyParam)
	{
		boolean flag = false;
		try
		{
			// common component 配置文件
			final File file = new File(PATH + ProjectConstant.SETTING_COMMON_COMPONENT_RELATIVE_PATH);
			String content = FileUtil.getString(file);
			// 替换内容
			content = content.replaceAll(copyParam.getProjectNameRegex(), copyParam.getProjectNameValue());
			// 输出文件
			flag = FileUtil.writeString(file, content, false);
			
			// 处理 web.xml 文件 TODO
			
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 文件重命名处理器
	 * @author  qye.zheng
	 * @param file
	 * @param ifRecursive 是否递归，如果不递归，将直接处理该目录下所有文件，忽略掉目录
	 * @param copyParam
	 */
	private static final void renameHandler(final File file, final boolean ifRecursive, final ProjectCopyParam copyParam)
	{
		File[] files = null;
		if (file.isDirectory())
		{
			files = file.listFiles();
		} else
		{
			files = new File[] {file};
		}
		String filename = null;
		for (File f : files)
		{
			if (f.isDirectory())
			{
				if (ifRecursive)
				{
					System.out.println("递归处理...");
					// 递归调用
					renameHandler(f, ifRecursive, copyParam);
				}
			} else
			{
				// 文件，直接处理
				filename = f.getName();
				// 只处理doc下的文件
				if (filename.contains(copyParam.getBelongBranchRegex()))
				{
					// 分支名称 - 处理
					System.out.println("分支文件 - 处理");
					if (StringUtil.isEmpty(copyParam.getBelongBranchValue()))
					{
						System.out.println("分支配置值为空，删除掉分支模板文件");
						f.delete();
						
						continue;
					}
					// 普通字符串，replace
					filename = filename.replace(copyParam.getBelongBranchRegex(), 
							copyParam.getBelongBranchValue());
					// 重命名该文件
					FileUtil.rename(f, filename);
				} else if (filename.contains(copyParam.getProjectNameRegex()))
				{
					// 项目名称 - 处理
					System.out.println("项目文件 - 处理");
					if (StringUtil.isEmpty(copyParam.getProjectNameValue()))
					{
						System.out.println("项目配置值为空，删除掉项目模板文件");
						f.delete();
						
						continue;
					}
					// 普通字符串，replace
					filename = filename.replace(copyParam.getProjectNameRegex(), 
							copyParam.getProjectNameValue());
					// 重命名该文件
					FileUtil.rename(f, filename);
				}
			}
		}
	}

}
