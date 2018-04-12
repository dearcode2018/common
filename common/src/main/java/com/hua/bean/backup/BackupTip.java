/**
 * BackupTip.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.bean.backup;

import java.sql.Date;

/**
 * BackupTip
 * 描述: 备份完成 - 提示信息
 * @author  qye.zheng
 */
public final class BackupTip
{

	/* 备份数 */
	private int count;
	
	/* 备份日期 */
	private Date date;
	
	/* 消耗时间 单位: 毫秒 */
	private int timeConsume;
	
	/* 备注信息 */
	private String remark;
	
	private long start;
	
	private long over;
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	public BackupTip()
	{
	}

	/**
	 * @return the count
	 */
	public int getCount()
	{
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void increase()
	{
		this.count++;
	}

	/**
	 * @return the date
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date)
	{
		this.date = date;
	}

	/**
	 * @return the remark
	 */
	public String getRemark()
	{
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	/**
	 * 根据起止时间，计算消耗的时间 (毫秒数)
	 * @return the timeConsume
	 */
	public int getTimeConsume()
	{
		timeConsume = (int) (over - start);
		
		return timeConsume;
	}
	
	/**
	 * 
	 * 描述: 开始备份
	 * @author  qye.zheng
	 */
	public void start()
	{
		start = System.currentTimeMillis();
	}
	
	
	/**
	 * 
	 * 描述: 结束备份
	 * @author  qye.zheng
	 */
	public void over()
	{
		over = System.currentTimeMillis();
	}

}
