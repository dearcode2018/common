/**
 * FileNameUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import com.hua.constant.Constant;

/**
 * FileNameUtil
 * 描述: 文件名 - 工具类
 * @author  qye.zheng
 */
public final class FileNameUtil
{

	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	private FileNameUtil()
	{
	}

	/**
	 * 
	 * 描述: 获取文件名 - 前缀 (已排除路径部分)
	 * @author qye.zheng
	 * @param filePath
	 * @return
	 */
	public static final  String getPrefix(final String filePath)
	{
		/*
		 从文件名后开始找，找到第一个 . 的位置作为endIndex
		 含有斜杠或反斜杠，则startIndex为最后一个 斜杠或反斜杠的位置
		 */
		String result = null;
		int startIndex = 0;
		int endIndex = filePath.lastIndexOf(Constant.DOT_MARK);
		if (filePath.contains(Constant.SLASH))
		{
			// 含有斜杠
			startIndex = filePath.lastIndexOf(Constant.SLASH);
			// 下一个位置
			startIndex++;
		} else if (filePath.contains(Constant.BACK_SLASH))
		{
			// 含有反斜杠
			startIndex = filePath.lastIndexOf(Constant.BACK_SLASH);
			// 下一个位置
			startIndex++;
		}
		result = filePath.substring(startIndex, endIndex);
		
		return result;
	}
	
	/**
	 * 
	 * 描述: 获取文件名 - 后缀
	 * @author qye.zheng
	 * @param filePath
	 * @return
	 */
	public static final  String getSuffix(final String filePath)
	{
		/*
		 从文件名后开始找，找到第一个 . 的位置 +1 作为 startIndex
		 */
		String result = null;
		int startIndex = filePath.lastIndexOf(Constant.DOT_MARK) + 1;
		result = filePath.substring(startIndex);
		
		return result;
	}
	
	/**
	 * 
	 * 描述: 给文件名加盖时间戳 
	 * @author  qye.zheng
	 * @param filename (支持 有后缀名、无后缀名)，文件名不能携带路径部分
	 * 没有后缀名，时间戳直接加盖在文件名后面，有时间戳则先拆开再拼接
	 * @return
	 */
	public static final  String coverTimestamp(final String filename)
	{
		String result = null;
		// 时间戳 yyyyMMddHHmmssSSS
		String tsString = DateTimeUtil.getTsString();
		final int index = filename.lastIndexOf(Constant.DOT_MARK);
		if (Constant.NEGATIVE_ONE == index)
		{
			// 没有后缀名，直接用文件名盖上时间戳
			result = filename + tsString;
		} else {
			// 有后缀名
			final String prefix = filename.substring(Constant.ZERO, index);
			final String suffix = filename.substring(index + 1);
			// 拼接 - 盖上时间戳
			result = prefix + tsString + Constant.DOT_MARK + suffix;
		}
		
		return result;
	}
	
	/**
	 * 
	 * 描述: 给文件名 添加前缀
	 * @author qye.zheng
	 * @param preffix 前缀
	 * @param fileName
	 * @return
	 */
	public static final String addPrefix(final String preffix, final String fileName)
	{
		return preffix + fileName;
	}
	
	/**
	 * 
	 * 描述: 给文件名 添加后缀
	 * @author qye.zheng
	 * @param fileName
	 * @param suffix 后缀
	 * @return
	 */
	public static final String addSuffix(final String fileName, final String suffix)
	{
		int index = Constant.NEGATIVE_ONE;
		// 先判断是否有后缀名
		if (Constant.NEGATIVE_ONE != (index = fileName.lastIndexOf(Constant.DOT_MARK)))
		{
			// 有 . 后缀名部分
			String result = fileName.substring(Constant.ZERO, index) + suffix;
			result += Constant.DOT_MARK + fileName.substring(index + Constant.ONE);
			
			return result;
		} else
		{
			// 没有 . 后缀名部分，直接拼接在文件名之后
			return fileName + suffix;
		}
	}
}
