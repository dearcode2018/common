/**
 * 描述: 
 * ClassPath.java
 * @author	qye.zheng
 *  version 1.0
 */
package com.hua.classpath;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述: 类路径 - 标签类
 * @author  qye.zheng
 * ClassPath
 */
public final class ClassPath
{
	// 标签名称
	public static final String TAG_Name = "classpath";

	private List<ClassPathEntry> classPathEntries = new ArrayList<ClassPathEntry>();
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 */
	public ClassPath()
	{
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * @param entry
	 * @return
	 */
	public boolean addClassPathEntry(final ClassPathEntry entry)
	{
		return classPathEntries.add(entry);
	}

	/**
	 * @return the classPathEntries
	 */
	public List<ClassPathEntry> getClassPathEntries()
	{
		return classPathEntries;
	}
	
}
