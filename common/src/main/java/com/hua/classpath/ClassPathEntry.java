/**
 * ClassPathEntry.java
 * @author qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.classpath;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassPathEntry
 * 描述: 类路径项 - 标签类
 * @author qye.zheng
 * 
 */
public final class ClassPathEntry
{
	// 标签名称
	public static final String TAG_NAME = "classpathentry";
	
	// 标签名称
	public static final String TAG_NAME_ATTRIBUTES = "attributes";
	
	/* 类型 : con / lib / src / output 默认是lib */
	private String kind = Kind.LIB;
	
	/* 路径 */
	private String path;
	
	/* 源代码路径 */
	private String sourcePath;
	
	/* 属性集合 */
	private List<Attribute> attributes = new ArrayList<Attribute>();
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	public ClassPathEntry()
	{
	}

	/**
	 * @return the kind
	 */
	public String getKind()
	{
		return kind;
	}

	/**
	 * @param kind the kind to set
	 */
	public void setKind(String kind)
	{
		this.kind = kind;
	}

	/**
	 * @return the path
	 */
	public String getPath()
	{
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path)
	{
		this.path = path;
	}

	/**
	 * @return the attributes
	 */
	public List<Attribute> getAttributes()
	{
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public boolean addAttribute(Attribute attribute)
	{
		return this.attributes.add(attribute);
	}

	/**
	 * @return the sourcePath
	 */
	public String getSourcePath()
	{
		return sourcePath;
	}

	/**
	 * @param sourcePath the sourcePath to set
	 */
	public void setSourcePath(String sourcePath)
	{
		this.sourcePath = sourcePath;
	}
	
}
