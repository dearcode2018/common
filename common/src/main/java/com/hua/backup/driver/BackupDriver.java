/**
 * BackupDriver.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.backup.driver;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import com.hua.bean.backup.BackupTip;
import com.hua.constant.BackupConstant;
import com.hua.constant.Constant;
import com.hua.constant.ProjectConstant;
import com.hua.constant.WorkspaceConstant;
import com.hua.constant.ZipConstant;
import com.hua.util.DateTimeUtil;
import com.hua.util.ProjectUtil;
import com.hua.util.StringUtil;
import com.hua.util.ZipUtil;

/**
 * BackupDriver
 * 描述: 备份 - 驱动
 * @author  qye.zheng
 */
public final class BackupDriver
{

	// working set 文件路径
	private static final String WORKING_SET_FILE_PATH = ProjectUtil.getAbsolutePath(ProjectConstant.WORKING_SET_OUTPUT_RELATIVE_PATH 
			+ ProjectConstant.WORKING_SET_WORKING_SET, false);
	
	// 压缩文件基本路径
	private static String destZipBasePath = ProjectUtil.getWorkspacePath() + File.separator + BackupConstant.FILE_BACK_UP + File.separator;
	
	private static Map<String, List<String>> workingSetMap;
	
	/*
	 压缩黑名单，即忽略名单中的目录
	 */
	private static final String[] ZIP_BLACK_LIST = {WorkspaceConstant.FILE_METADATA, BackupConstant.FILE_BACK_UP};
	
	private static BackupTip backupTip;
	
	static
	{
		// 加载 working set 配置参数
		loadWorkingSet();
		// 创建目录
		final File backupFile = new File(destZipBasePath);
		if (!backupFile.exists())
		{
			backupFile.mkdirs();
		}
	}
	
	/**
	 备份文件命名规则
	 工作集名_项目名_当前日期(格式: yyyy-MM-dd).zip
	 例如:
	 orm_mybatis3_2014-09-10.zip
	 */
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	private BackupDriver()
	{
	}
	
	/**
	 * 
	 * 描述: 备份当前项目
	 * @author  qye.zheng
	 * @return
	 */
	public static boolean backupProject()
	{
		boolean flag = false;
		final String projectName = ProjectUtil.getProjectName();
		flag = backupProject(projectName);
		
		return flag;
	}
	
	/**
	 * 
	 * 描述:  根据项目名称备份指定的项目
	 * 可以备份当前工作空间的任何一个项目
	 * @author  qye.zheng
	 * @param projectName
	 * @return
	 */
	public static boolean backupProject(final String projectName)
	{
		backupTip = new BackupTip();
		backupTip.setDate(DateTimeUtil.getDate());
		backupTip.setRemark("备份项目 : " + projectName);
		// 自增1
		backupTip.increase();
		backupTip.start();
		boolean flag = false;
		try
		{
			final String wsName = getWsName(projectName);
			final String destPath = destZipBasePath +getZipFilename(wsName, projectName);
			final String projectPath = getProjectPath(projectName);
			
			// 执行压缩备份
			flag = ZipUtil.zip(projectPath, destPath);
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		backupTip.over();
		// 输出备份信息
		finishTip(backupTip);
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 备份当前项目所在的工作集的所有项目
	 * @author  qye.zheng
	 * @return
	 */
	public static boolean backupWorkingsets()
	{
		boolean flag = false;
		final String projectName = ProjectUtil.getProjectName();
		final String wsName = getWsName(projectName);
		flag = backupWorkingsets(wsName);
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 备份指定工作集的所有项目
	 * @author  qye.zheng
	 * @param wsName 工作集名称
	 * @return
	 */
	public static boolean backupWorkingsets(final String wsName)
	{
		backupTip = new BackupTip();
		backupTip.setDate(DateTimeUtil.getDate());
		backupTip.setRemark("备份工作集: " + wsName+ " 的所有项目");
		backupTip.start();
		boolean flag = false;
		try
		{
			final List<String> projectList = workingSetMap.get(wsName);
			String projectPath = null;
			String destPath = null;
			for (String projectName : projectList)
			{
				// 自增
				backupTip.increase();
				projectPath = getProjectPath(projectName);
				destPath = destZipBasePath +getZipFilename(wsName, projectName);
				
				// 调用压缩
				ZipUtil.zip(projectPath, destPath);
				
			}
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		backupTip.over();
		// 输出备份信息
		finishTip(backupTip);
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 备份所有工作集的项目(不包括系统默认工作集)
	 * @author  qye.zheng
	 * @return
	 */
	public static boolean backupAllWs()
	{
		backupTip = new BackupTip();
		backupTip.setDate(DateTimeUtil.getDate());
		backupTip.setRemark("备份所有工作集的项目(不包括系统默认工作集)");
		backupTip.start();
		boolean flag = false;
		String wsName = null;
		List<String> projectList = null;
		String projectPath = null;
		String destPath = null;
		final Set<String> keySet = workingSetMap.keySet();
		final Iterator<String> iterator = keySet.iterator();
		try
		{
			while (iterator.hasNext())
			{
				wsName = iterator.next();
				projectList = workingSetMap.get(wsName);
				for (String projectName : projectList)
				{
					projectPath = getProjectPath(projectName);
					destPath = destZipBasePath +getZipFilename(wsName, projectName);
					// 自增
					backupTip.increase();
					// 调用压缩
					ZipUtil.zip(projectPath, destPath);
				}
			}
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		backupTip.over();
		// 输出备份信息
		finishTip(backupTip);
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 备份所有的项目(包括系统默认工作集)
	 * @author  qye.zheng
	 * @return
	 */
	public static boolean backupAll()
	{
		backupTip = new BackupTip();
		backupTip.setDate(DateTimeUtil.getDate());
		backupTip.setRemark("备份所有的项目(包括系统默认工作集)");
		backupTip.start();
		boolean flag = false;
		try
		{
			String wsName = null;
			String projectPath = null;
			String destPath = null;
			String projectName = null;
			final File workspace = new File(ProjectUtil.getWorkspacePath());
			final String[] names = workspace.list();
			// 是否是黑名单成员
			boolean isBlack = false;
			for (String name : names)
			{
				for (String black : ZIP_BLACK_LIST)
				{
					if (black.equals(name))
					{
						isBlack = true;
						break;
					}
				}
				if (isBlack)
				{
					// 将标识置为false
					isBlack = false;
					// 黑名单成员，忽略
					continue;
				}
				projectName = name;
				wsName = getWsName(projectName);
				projectPath = getProjectPath(projectName);
				destPath = destZipBasePath +getZipFilename(wsName, projectName);
				backupTip.increase();
				// 调用压缩
				ZipUtil.zip(projectPath, destPath);
			}
			
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		backupTip.over();
		// 输出备份信息
		finishTip(backupTip);
		
		return flag;
	}

	/**
	 * 
	 * 描述: 加载工作集配置 
	 * @author  qye.zheng
	 */
	public static void loadWorkingSet()
	{
		// private static Map<String, List<String>> workingSetMap;
		workingSetMap = new HashMap<String, List<String>>();
		List<String> projectList = null;
		try
		{
			// 建立构造器  
			final SAXBuilder saxBuilder = new SAXBuilder();
			// 读入指定文件  
			final Document doc = saxBuilder.build(new FileInputStream(WORKING_SET_FILE_PATH));
			
			// 获得根节点  
			final Element root = doc.getRootElement();
			String wsName = null;
			String projectName = null;
			// 子节点集合
			List<Element> children = root.getChildren();
			List<Element> nextChildren = null;
			for (Element e : children)
			{
				nextChildren = e.getChildren();
				if (nextChildren.size() > Constant.ZERO)
				{
					// 有item的才放入map中
					wsName = e.getAttributeValue("name");
					/*
					 若 name 为空，可以使用 label
					 */
					if (StringUtil.isEmpty(wsName))
					{
						wsName = e.getAttributeValue("label");
					}
					projectList = new ArrayList<String>();
					for (Element nextE : nextChildren)
					{
						// 不含有斜杠的，为无效项目
						/*
						 打开的项目没有 path属性，关闭的项目才有，
						 打开的项目 elementID="=projectName" 通过这个来获取，
						 关闭的项目用 path="projectName"来获取
						 去掉 =或/ 即可
						 */
						//System.out.println("item path = " + nextE.getAttributeValue("elementID"));
						projectName = nextE.getAttributeValue("path");
						if (StringUtil.isEmpty(projectName))
						{
							// 尝试用 elementID 来获取
							projectName = nextE.getAttributeValue("elementID");
						}
						if (!StringUtil.isEmpty(projectName))
						{
							//  去掉=号
							projectName = projectName.substring(Constant.ONE);
							projectList.add(projectName);
							System.out.println("projectName = " + projectName);
						}
					}
					// 将list放入map中
					workingSetMap.put(wsName, projectList);
					System.out.println("==========================");
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 描述: 根据项目名称，获取其工作集名称
	 * 工作集不存在或为默认，则返回 default
	 * @author  qye.zheng
	 * @param projectName
	 * @return
	 */
	public static String getWsName(final String projectName)
	{
		String wsName = null;
		final Set<String> keySet = workingSetMap.keySet();
		final Iterator<String> iterator = keySet.iterator();
		List<String> projectList = null;
		 while (iterator.hasNext())
		 {
			 wsName = iterator.next();
			 projectList = workingSetMap.get(wsName);
			 for (String name : projectList)
			 {
				 if (name.equals(projectName))
				 {
					 
					 return wsName;
				 }
			 }
		 }
		 // 没有则使用默认值
		 wsName = "default";
		 
		return wsName;
	}
	
	/**
	 * 
	 * 描述: 获取压缩文件名
	 * @author  qye.zheng
	 * @param wsName
	 * @param projectName
	 * @return
	 */
	public static String getZipFilename(final String wsName, final String projectName)
	{
		/**
		 备份文件命名规则
		 工作集名_项目名_当前日期(格式: yyyy-MM-dd).zip
		 例如:
		 orm_mybatis3_2014-09-10.zip
		 */
		String result = wsName + Constant.UNDERLINE + projectName;
		result += Constant.UNDERLINE + DateTimeUtil.format(DateTimeUtil.getDate());
		result += Constant.DOT_MARK + ZipConstant.ZIP_FILE_SUFFIX;
		
		return result;
	}
	
	/**
	 * 
	 * 描述: 根据项目名称获取项目的路径
	 * @author  qye.zheng
	 * @param projectName
	 * @return
	 */
	private static String getProjectPath(final String projectName)
	{
		final String projectPath = ProjectUtil.getWorkspacePath() + File.separator + projectName;
		
		return projectPath;
	}
	
	/**
	 * 
	 * 描述: 备份结束提示信息
	 * @author  qye.zheng
	 * @param backupTip
	 */
	public static void finishTip(final BackupTip backupTip)
	{
		final StringBuilder builder = StringUtil.getBuilder();
		builder.append("[备份信息]" + Constant.LINE_BREAK);
		builder.append("日期: " + DateTimeUtil.format(backupTip.getDate()) + Constant.LINE_BREAK);
		builder.append("数量: " + backupTip.getCount() + Constant.LINE_BREAK);
		builder.append("耗时(毫秒数): " + backupTip.getTimeConsume() + Constant.LINE_BREAK);
		builder.append("备注: " + backupTip.getRemark());
		// 输出到控制台
		System.out.println(builder.toString());
	}

	/**
	 * @return the workingSetMap
	 */
	public static final Map<String, List<String>> getWorkingSetMap()
	{
		return workingSetMap;
	}
	
}
