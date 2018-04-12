/**
 * 描述: 
 * FileInfo.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.entity;

import java.io.File;

import com.hua.util.FileUtil;
import com.hua.util.StringUtil;

/**
 * 描述: 文件信息
 * 
 * @author qye.zheng
 * FileInfo
 */
public final class FileInfo
{

	/* 对应 <input name="" type="file" /> 中的name属性值 */
	private String name;
	
	/* 文件位置 (在整个消息体中的起始/结束位置 - 字节) */
	private FilePosition filePosition;

	/* 原始文件名 */
	private String originalFilename;
	
	/* 存储文件名 */
	private String storeFilename;

	/* 存储路径 (完整路径 : 前缀路径 + / + 存储文件名) */
	private String storePath;
	
	/* 文件大小 */
	private int fileSize;

	/* 媒体类型 (比文件类型还宽泛) 例如 : image/png */
	private String mediaType;
	
	/* 文件数据 (文件头 + 文件主体 + 文件尾) */
	private byte[] fileData;

	/**
	 * @return the filePosition
	 */
	public FilePosition getFilePosition()
	{
		return filePosition;
	}

	/**
	 * @param filePosition the filePosition to set
	 */
	public void setFilePosition(FilePosition filePosition)
	{
		this.filePosition = filePosition;
	}

	/**
	 * @return the originalFilename
	 */
	public String getOriginalFilename()
	{
		return originalFilename;
	}

	/**
	 * @param originalFilename the originalFilename to set
	 */
	public void setOriginalFilename(String originalFilename)
	{
		this.originalFilename = originalFilename;
	}

	/**
	 * @return the storeFilename
	 */
	public String getStoreFilename()
	{
		if (StringUtil.isEmpty(storeFilename)) {
			storeFilename = FileUtil.getStoreFilename(originalFilename);
		}
		
		return storeFilename;
	}

	/**
	 * @param storeFilename the storeFilename to set
	 */
	public void setStoreFilename(String storeFilename)
	{
		this.storeFilename = storeFilename;
	}

	/**
	 * @return the storePath
	 */
	public String getStorePath()
	{
		// 存储路径前缀 + / + 存储文件名
		return storePath + File.separator + getStoreFilename();
	}

	/**
	 * @param storePath the storePath to set
	 */
	public void setStorePath(String storePath)
	{
		this.storePath = storePath;
	}

	/**
	 * @return the fileSize
	 */
	public int getFileSize()
	{
		return fileSize;
	}

	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(int fileSize)
	{
		this.fileSize = fileSize;
	}

	/**
	 * @return the mediaType
	 */
	public String getMediaType()
	{
		return mediaType;
	}

	/**
	 * @param mediaType the mediaType to set
	 */
	public void setMediaType(String mediaType)
	{
		this.mediaType = mediaType;
	}

	/**
	 * @return the fileData
	 */
	public byte[] getFileData()
	{
		return fileData;
	}

	/**
	 * @param fileData the fileData to set
	 */
	public void setFileData(byte[] fileData)
	{
		this.fileData = fileData;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
}
