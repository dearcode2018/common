/**
 * ProjectUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import com.hua.constant.ClassPathConstant;
import com.hua.constant.Kind;
import com.hua.constant.ProjectConstant;

/**
 * ProjectUtil
 * 描述: 项目规划 - 工具类
 * @author  qye.zheng
 */
public final class ProjectUtil
{

	/**
	 * 构造方法
	 * 描述: 私有 - 禁止实例化
	 * @author  qye.zheng
	 */
	private ProjectUtil()
	{
	}
	
	/**
	 * 
	 * 描述: 获取当前项目的根路径 
	 * @author  qye.zheng
	 * @return
	 */
	public static String getProjectRootPath()
	{
		/*
		 例如
		 C:\Workspace\project
		 */
		
		return System.getProperty("user.dir");
	}

	/**
	 * 
	 * 描述: 获取项目所在的工作空间路径
	 * @author  qye.zheng
	 * @return
	 */
	public static String getWorkspacePath()
	{
		/*
		 例如
		 C:\Workspace
		 先获取当前项目所在的路径，然后再获取其Parent File对象
		 */
		final File projectFile = new File(getProjectRootPath());
		final File workspaceFile = projectFile.getParentFile();
		
		return workspaceFile.getAbsolutePath();
	}
	
	/**
	 * 
	 * 描述: 基于工作空间级别
	 * 根据资源的相对路径获取绝对路径
	 * 相对路径从工作空间、项目的根路径开始
	 * @author  qye.zheng
	 * @param relativePath 例如 : /a/b/
	 * @return 返回例如: C:/Worksapce/a/b
	 */
	public static String getAbsolutePath(final String relativePath)
	{
		return getAbsolutePath(relativePath, true);
	}
	
	/**
	 * 
	 * 描述: 基于工作空间级别
	 * 根据资源的相对路径获取绝对路径
	 * 相对路径从工作空间、项目的根路径开始
	 * @author  qye.zheng
	 * @param relativePath 例如 : /a/b/
	 * @param project 相对路径是否是从当前项目开始
	 * @return 返回例如: C:/Worksapce/a/b
	 */
	public static String getAbsolutePath(final String relativePath, final boolean project)
	{
		String result = null;
		if (project)
		{
			// 从当前项目的根路径开始
			result = getProjectRootPath() + relativePath;
		} else
		{
			// 从当前项目所在的工作空间的根路径开始
			result = getWorkspacePath() + relativePath;
		}
		/*
		 构造文件对象，对输出本地化路径有帮助作用
		 输出的路径的格式将和操作系统相关
		 */
		final File resultPath = new File(result);
		// 获取绝对路径
		result = resultPath.getAbsolutePath();
		
		return result;
	}
	
	/**
	 * 
	 * 描述: 获取当前项目名称
	 * @author  qye.zheng
	 * @return
	 */
	public static String getProjectName()
	{
		final String projectDir = getProjectRootPath();
		final File projectFile = new File(projectDir);
		final String projectName = projectFile.getName();
		
		return projectName;
	}
	
	/**
	 * 
	 * 描述: 是否是web项目
	 * 以其.classpath 文件作为依据
	 * @author  qye.zheng
	 * @param file
	 * @return
	 */
	public static final boolean isWeb(final File file)
	{
		boolean flag = false;
		try
		{
			/**
			 直接通过 .classpath 文件 无法准确判断是否是 web 项目，改为使用 .settings/.jsdtscope
			 */
			final File jsdtFile = new File(file.getAbsolutePath() +
					ProjectConstant.SETTING_JSDTSCOPE_RELATIVE_PATH);
			// 不存在则一定是 java 项目
			if (!jsdtFile.exists())
			{
				return flag;
			}
			// 建立构造器  
			final SAXBuilder saxBuilder = new SAXBuilder();
			// 读入指定文件  
			final Document doc = saxBuilder.build(new FileInputStream(jsdtFile));
			
			// 获得根节点  
			final Element root = doc.getRootElement();
			final List<Element> classpaths = root.getChildren();
			String kind = null;
			String path = null;
			for (final Element classpath : classpaths)
			{
				kind = classpath.getAttributeValue(ClassPathConstant.ATTRIBUTE_KIND);
				path = classpath.getAttributeValue(ClassPathConstant.ATTRIBUTE_PATH);
				if (Kind.CON.equals(kind) && ClassPathConstant.CON_PATH_LAUNCHING_WEB_PROJECT.equals(path))
				{
					/*
					 web项目的标识
					 <classpathentry kind="con" path="org.eclipse.jst.j2ee.internal.web.container"/> 
					 */
					flag = true;
					
					return flag;
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 是否是web项目
	 * 以其.classpath 文件作为依据
	 * @author  qye.zheng
	 * @param projectDirectory
	 * @return
	 */
	public static final boolean isWeb(final String projectDirectory)
	{
		final File file = new File(projectDirectory);
		
		return isWeb(file);
	}
	
}
