/**
 * 描述: 
 * MaintainParam.java
 * @author	qye.zheng
 *  version 1.0
 */
package com.hua.bean.maintain;

import com.hua.constant.Constant;
import com.hua.util.ArrayUtil;
import com.hua.util.ReadProperties;
import com.hua.util.StringUtil;

/**
 * 描述: 
 * @author  qye.zheng
 * MaintainParam
 */
public final class MaintainParam
{
	/* 列入维护的项目名称数组 (可以包括 <default> 工作集中的项目) */
	private String[] projects;
	
	/* 列入维护的工作集数组 (不包括 <default> 工作集) */
	private String[] workingSets;
	
	/* 新增/更新: 覆盖路径 */
	private String overridePath;

	/* 删除路径 */
	private String deletePath;
	
	/* 覆盖源文件存放位置，选择在当前项目 doc目录下 */
	private String sourcePath;
	
	private static MaintainParam instance;
	
	private static final String CONFIG_PATH = "/conf/properties/maintain.properties";
	
	private static final ReadProperties props = new ReadProperties(CONFIG_PATH);
	
	static
	{
		instance = new MaintainParam();
		
		// 项目
		final String projectValues = props.getProperty("project.maintain.range.projects");
		// 拦截空设置
		if (!StringUtil.isEmpty(projectValues))
		{
			final String[] projects = projectValues.split(Constant.COMMA);
			ArrayUtil.trim(projects);
			instance.setProjects(projects);
		}
		
		// 工作集
		final String workingSetValues = props.getProperty("project.maintain.range.working.sets");
		// 拦截空设置
		if (!StringUtil.isEmpty(workingSetValues))
		{
			final String[] workingSets = workingSetValues.split(Constant.COMMA);
			ArrayUtil.trim(workingSets);
			instance.setWorkingSets(workingSets);
		}
		
		instance.setOverridePath(props.getProperty("project.maintain.override.path"));
		instance.setDeletePath(props.getProperty("project.maintain.delete.path"));
		instance.setSourcePath(props.getProperty("project.maintain.source.path"));
	}
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 */
	private MaintainParam()
	{
	}

	/**
	 * @return the overridePath
	 */
	public final String getOverridePath()
	{
		return overridePath;
	}

	/**
	 * @param overridePath the overridePath to set
	 */
	private final void setOverridePath(String overridePath)
	{
		this.overridePath = overridePath;
	}

	/**
	 * @return the deletePath
	 */
	public final String getDeletePath()
	{
		return deletePath;
	}

	/**
	 * @param deletePath the deletePath to set
	 */
	private final void setDeletePath(String deletePath)
	{
		this.deletePath = deletePath;
	}

	/**
	 * @return the instance
	 */
	public static final MaintainParam getInstance()
	{
		return instance;
	}

	/**
	 * @return the projects
	 */
	public final String[] getProjects()
	{
		return projects;
	}

	/**
	 * @param projects the projects to set
	 */
	private final void setProjects(String[] projects)
	{
		this.projects = projects;
	}

	/**
	 * @return the workingSets
	 */
	public final String[] getWorkingSets()
	{
		return workingSets;
	}

	/**
	 * @param workingSets the workingSets to set
	 */
	private final void setWorkingSets(String[] workingSets)
	{
		this.workingSets = workingSets;
	}

	/**
	 * @return the sourcePath
	 */
	public final String getSourcePath()
	{
		return sourcePath;
	}

	/**
	 * @param sourcePath the sourcePath to set
	 */
	private final void setSourcePath(String sourcePath)
	{
		this.sourcePath = sourcePath;
	}

}
