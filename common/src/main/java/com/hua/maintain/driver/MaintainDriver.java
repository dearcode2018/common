/**
 * MaintainDriver.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.maintain.driver;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hua.backup.driver.BackupDriver;
import com.hua.bean.maintain.MaintainParam;
import com.hua.constant.Constant;
import com.hua.util.EmptyUtil;
import com.hua.util.FileUtil;
import com.hua.util.ProjectUtil;

/**
 * MaintainDriver
 * 描述: 项目维护 - 驱动器
 * @author  qye.zheng
 */
public final class MaintainDriver
{
	private static final MaintainParam param = MaintainParam.getInstance();
	
	private static String absolutePath;

	private static final boolean noneProject = false;
	
	private static final boolean project = true;
	
	private static final Map<String, List<String>> 
	workingSetMap = BackupDriver.getWorkingSetMap();
	
	private static File[] sourceFiles;
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	private MaintainDriver()
	{
	}
	
	/**
	 * 
	 * 描述: 仅覆盖(即存在才覆盖，不存在则不做操作) 
	 * @author qye.zheng
	 * @return
	 */
	public static final boolean onlyOverride()
	{
		final boolean onlyOverride = true;
		
		return override(onlyOverride);
	}
	
	/**
	 * 
	 * 描述: 存在则覆盖，不存在则新增
	 * @author qye.zheng
	 * @return
	 */
	public static final boolean addOrOverride()
	{
		final boolean onlyOverride = false;
		
		return override(onlyOverride);
	}
	
	/**
	 * 
	 * 描述: 新增或覆盖(单个文件/目录)
	 * @author qye.zheng
	 * @param onlyOverride 是否仅覆盖
	 * @return
	 */
	private static final boolean override(final boolean onlyOverride)
	{
		System.out.println("开始执行覆盖...");
		boolean flag = false;
		try
		{
			final String sourcePath = ProjectUtil.getAbsolutePath(param.getSourcePath(), project);
			final File sourceFile = new File(sourcePath);
			// 覆盖的来源 (文件对象 数组)
			sourceFiles = sourceFile.listFiles();
			if (EmptyUtil.isEmpty(sourceFiles))
			{
				System.out.println("路径: " + sourcePath + " 为空，新增或覆盖无法进行!");
				
				return flag;
			}
			final String[] projects = param.getProjects();
			final String[] workingSets = param.getWorkingSets();
			List<String> projectList = null;
			if (!EmptyUtil.isEmpty(projects))
			{
				// 删除指定项目下的指定文件对象
				for (String projectName : projects)
				{
					override(projectName, onlyOverride);
				}
			} else if (!EmptyUtil.isEmpty(workingSets))
			{
				// 删除指定工作集下所有项目的指定文件对象
				for (String wsName : workingSets)
				{
					projectList = workingSetMap.get(wsName);
					// 删除指定项目下的指定文件对象
					if (!EmptyUtil.isEmpty(projectList))
					{
						for (String projectName : projectList)
						{
							override(projectName, onlyOverride);
						}
					} else
					{
						System.out.println("工作集不存在，导致项目列表为空!");
					}
				}
			} else
			{
				// 删除所有在工作集中的所有项目的指定文件对象(不包括 default 工作集)
				final Set<String> keySet = workingSetMap.keySet();
				for (String wsName : keySet)
				{
					projectList = workingSetMap.get(wsName);
					// 删除指定项目下的指定文件对象
					for (String projectName : projectList)
					{
						override(projectName, onlyOverride);
					}
				}
			}
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("覆盖完毕!");
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 根据项目名称删除相应的文件/目录 
	 * @author qye.zheng
	 * @param projectName
	 * @param onlyOverride
	 */
	private static final void override(final String projectName, final boolean onlyOverride)
	{
		final String destinationPath = ProjectUtil.getAbsolutePath(
				Constant.SLASH + projectName + param.getOverridePath(), noneProject);
		// 目的文件，用来判断目的文件是否已经存在
		File destinationFile = null;
		if (onlyOverride)
		{
			for (File file : sourceFiles)
			{
				// 仅做覆盖，需要判断目录是否存在
				destinationFile = new File(destinationPath + File.separator + file.getName());
				if (!destinationFile.exists())
				{
					System.out.println(destinationFile.getAbsolutePath() + " 不存在，放弃拷贝操作");
					// 不存在，则放弃当前拷贝操作
					continue;
				}
				FileUtil.copy(file, destinationPath);
			}
		} else
		{
			// 一次性拷贝
			FileUtil.copy(sourceFiles, destinationPath);
		}
	}	
	
	/**
	 * 
	 * 描述: 删除(单个文件/目录)
	 * @author qye.zheng
	 * @return
	 */
	public static final boolean delete()
	{
		System.out.println("开始执行删除...");
		boolean flag = false;
		// 删除总数
		int deleteCount = 0;
		try
		{
			final String[] projects = param.getProjects();
			final String[] workingSets = param.getWorkingSets();
			List<String> projectList = null;
			if (!EmptyUtil.isEmpty(projects))
			{
				// 删除指定项目下的指定文件对象
				for (String projectName : projects)
				{
					if (delete(projectName))
					{
						deleteCount++;
					}
				}
			} else if (!EmptyUtil.isEmpty(workingSets))
			{
				// 删除指定工作集下所有项目的指定文件对象
				for (String wsName : workingSets)
				{
					projectList = workingSetMap.get(wsName);
					// 删除指定项目下的指定文件对象
					if (!EmptyUtil.isEmpty(projectList))
					{
						for (String projectName : projectList)
						{
							if (delete(projectName))
							{
								deleteCount++;
							}
						}
					} else
					{
						System.out.println("工作集不存在，导致项目列表为空!");
					}
				}
			} else
			{
				// 删除所有在工作集中的所有项目的指定文件对象(不包括 default 工作集)
				final Set<String> keySet = workingSetMap.keySet();
				for (String wsName : keySet)
				{
					projectList = workingSetMap.get(wsName);
					// 删除指定项目下的指定文件对象
					for (String projectName : projectList)
					{
						if (delete(projectName))
						{
							deleteCount++;
						}
					}
				}
			}
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("删除完毕，总共删除 " + deleteCount + " 个文件/目录");
		
		return flag;
	}

	/**
	 * 
	 * 描述: 根据项目名称删除相应的文件/目录 
	 * @author qye.zheng
	 * @param projectName
	 */
	private static final boolean delete(final String projectName)
	{
		absolutePath = ProjectUtil.getAbsolutePath(
				Constant.SLASH + projectName + param.getDeletePath(), noneProject);
		File file = new File(absolutePath);
		return FileUtil.delete(file);
	}
	
}
