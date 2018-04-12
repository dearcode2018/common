/**
 * 描述: 
 * FilePosition.java
 * @author qye.zheng
 * 
 *  version 1.0
 */
package com.hua.entity;

/**
 * 描述: 文件 - 开始与结束位置
 * @author qye.zheng
 * 
 * FilePosition
 */
public final class FilePosition
{
	private int begin;
	
	private int end;

	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param begin
	 * @param end
	 */
	public FilePosition(int begin, int end)
	{
		super();
		this.begin = begin;
		this.end = end;
	}

	/**
	 * @return the begin
	 */
	public int getBegin()
	{
		return begin;
	}

	/**
	 * @param begin the begin to set
	 */
	public void setBegin(int begin)
	{
		this.begin = begin;
	}

	/**
	 * @return the end
	 */
	public int getEnd()
	{
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(int end)
	{
		this.end = end;
	}

	
}
