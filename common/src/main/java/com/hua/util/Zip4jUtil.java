/**
 * Zip4jUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.io.File;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import com.hua.constant.Constant;

/**
 * Zip4jUtil
 * 描述: Zip4j 压缩解压工具
 * @author  qye.zheng
 */
public final class Zip4jUtil
{

	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	private Zip4jUtil()
	{
	}
	
	/**
	 * 
	 * @description 无密码压缩文件或 目录
	 * @param srcPath 源路径 例如 /local/sy 或 /local/sy/abc.txt
	 * @param destPath 目标压缩路径 (例如 /a/b)
	 * @author qianye.zheng
	 */
	public static final void zip(final String srcPath, final String destPath)
	{
		zip(srcPath, destPath, null);
	}
	
	/**
	 * 
	 * @description 带密码压缩文件或 目录
	 * @param srcPath 源路径 例如 /local/sy 或 /local/sy/abc.txt
	 * @param destPath 目标压缩路径 (例如 /a/b)
	 * @param password
	 * @author qianye.zheng
	 */
	public static final void zip(final String srcPath, final String destPath, final String password)
	{
		final File srcFile = new File(srcPath);
		
		final ZipParameters zipParameters = new ZipParameters();
		// 设置压缩方法: 默认
		zipParameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		// 设置压缩级别: 默认正常
		zipParameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
		
		// 加密
		if (StringUtil.isNotEmpty(password))
		{
			// 使用密码压缩
			zipParameters.setEncryptFiles(true);
			// 加密方法
			zipParameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
			// 设置密码
			zipParameters.setPassword(password);
		}
        ZipFile zipFile = null;
		String zipFilename = null;
		try
		{
			if (srcFile.isFile())
			{ // 压缩单个文件 获取文件前缀作为压缩文件名
				zipFilename = srcFile.getName().substring(0, srcFile.getName().lastIndexOf('.'));
				zipFilename += ".zip";
				zipFile = new ZipFile(destPath + "/" + zipFilename);
				zipFile.addFile(srcFile, zipParameters);
			} else
			{ // 压缩目录
				zipFile = new ZipFile(destPath + "/" + srcFile.getName() + ".zip");  
				// 注意这里是 addFolder
				zipFile.addFolder(srcFile, zipParameters);
			}		
		} catch (ZipException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @description 解压文件
	 * @param zipPath zip文件路径，例如 /a/b/good.zip
	 * @param destPath 目标解压路径，例如 /local/mood
	 * @author qianye.zheng
	 */
	public static final void unzip(final String zipPath, final String destPath)
	{
		// 默认使用 UTF-8编码
		unzip(zipPath, destPath, null, Constant.CHART_SET_UTF_8);
	}	
	
	/**
	 * 
	 * @description 解压文件
	 * @param zipPath zip文件路径，例如 /a/b/good.zip
	 * @param destPath 目标解压路径，例如 /local/mood
	 * @param password
	 * @author qianye.zheng
	 */
	public static final void unzip(final String zipPath, final String destPath, final String password)
	{
		// 默认使用 UTF-8编码
		unzip(zipPath, destPath, password, Constant.CHART_SET_UTF_8);
	}
	
	/**
	 * 
	 * @description 解压文件
	 * @param zipFilePath zip文件路径，例如 /a/b/good.zip
	 * @param destPath 目标解压路径，例如 /local/mood
	 * @param password
	 * @param filenameCharset 文件名编码
	 * @author qianye.zheng
	 */
	public static final void unzip(final String zipFilePath, final String destPath, final String password, final String filenameCharset)
	{
		try
		{
			final ZipFile zipFile = new ZipFile(new File(zipFilePath));
			/*
			 * 在中文系统中，需要设置为GB2312
			 * setFileNameCharset 需要在构造ZipFile对象之后立即调用才能有效执行
			 */
			zipFile.setFileNameCharset(filenameCharset);
			//zipFile.setFileNameCharset(Constant.CHART_SET_GB2312);
			if (!zipFile.isValidZipFile())
			{ // 检查zip文件是否合法
				throw new ZipException("压缩文件不合法,可能被损坏.");
			}
			if (zipFile.isEncrypted())
			{ // 检查是需要密码
				if (StringUtil.isEmpty(password))
				{
					throw new ZipException("解压密码不能为空");
				}
				zipFile.setPassword(password);
			}
			zipFile.extractAll(destPath);			
		} catch (ZipException e)
		{
			e.printStackTrace();
		}
	}

}
