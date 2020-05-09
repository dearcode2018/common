/**
 * 描述: 
 * FileUtil.java
 * @author	qye.zheng
 * 
 *  version 1.0
 */
package com.hua.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.hua.constant.Constant;

/**
 * 描述: 文件工具类
 * @author  qye.zheng
 * 
 * FileUtil
 */
public final class FileUtil
{

	/**
	 
	 文件(File): 一个存放数据的单独实体.
	 
	 目录(Directory): 也称 文件夹，存放文件的实体.
	 
	 路径(Path): 文件路径(filePath)、目录路径(directoryPath).
	 
	 文件名: filename，单一的名称，不包括路径
	 
	 目录名: directory
	 
	 路径名: filePath / directoryPath
	 
	 绝对路径 - abstract path, 相对路径 - relative path
	 
	 */
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	private FileUtil()
	{
	}

	/**
	 * 文件不存在则创建一个空文件
	 * 描述: 创建文件
	 * @author  qye.zheng
	 * @param filePath 文件路径
	 * @return
	 */
	public static final boolean createFile(final String filePath)
	{
		boolean flag = false;
		// 构造文件对象
		final File file = new File(filePath);
		try
		{
			if (!file.exists())
			{
				// 文件不存在则创建一个空文件
				flag = file.createNewFile();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 目录不存在则创建一个空目录
	 * 描述: 创建(多级)目录
	 * @author  qye.zheng
	 * @param directoryPath 文件路径
	 * @return
	 */
	public static final boolean createDirectory(final String directoryPath)
	{
		boolean flag = false;
		// 构造文件对象
		final File file = new File(directoryPath);
		try
		{
			flag = file.mkdirs();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 删除文件
	 * @author  qye.zheng
	 * @param filePath 文件路径
	 * @return
	 */
	public static final boolean deleteFile(final String filePath)
	{
		boolean flag = false;
		// 构造文件对象
		final File file = new File(filePath);
		try
		{
			flag = file.delete();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 删除目录
	 * 若不是空目录，则递归调用该目录下的所有文件
	 * file.delete() 只能删除文件或者空目录
	 * @author  qye.zheng
	 * @param directoryPath 目录路径
	 * @return
	 */
	public static final boolean deleteDirectory(final String directoryPath)
	{
		boolean flag = false;
		// 构造文件对象
		final File file = new File(directoryPath);
		// 文件/目录 并不存在
		if (!file.exists())
		{
			return flag;
		}
		// 列出该目录下的所有文件对象
		final File[] files = file.listFiles();
		try
		{
			if (!EmptyUtil.isEmpty(files))
			{
				for (File f : files)
				{
					// 存在且是文件
					if (f.exists() && f.isFile())
					{
						// 文件，直接删除
						f.delete();
					} else if (f.isDirectory())
					{
						// 目录，递归调用
						deleteDirectory(f.getAbsolutePath());
					}
				}
			}
			// 删除该目录下的所有文件/目录，然后再删除此目录
			file.delete();
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 删除文件
	 * @author  qye.zheng
	 * @param file 文件对象
	 * @return
	 */
	public static final boolean delete(final File file)
	{
		boolean flag = false;
		try
		{
			// 存在且是文件
			if (file.exists() && file.isFile())
			{
				// 文件，直接删除
				flag = file.delete();
				
				return flag;
			} else if (file.isDirectory())
			{
				// 调用，删除目录 方法
				flag = deleteDirectory(file.getAbsolutePath());
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 文件/目录 - 重命名
	 * 直接在当前路径下操作
	 * @author  qye.zheng
	 * @param file 文件对象
	 * @param newName 新的名称
	 * @return
	 */
	public static final boolean rename(final File file, final String newName)
	{
		boolean flag = false;
		try
		{
			final File destFile = new File(file.getParent() + File.separator + newName);
			flag = file.renameTo(destFile);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 文件/目录 - 重命名
	 * 直接在当前路径下操作
	 * @author  qye.zheng
	 * @param path 文件/目录 路径
	 * @param newName 新的名称
	 * @return
	 */
	public static final boolean rename(final String path, final String newName)
	{
		boolean flag = false;
		try
		{
			final File file = new File(path);
			final File destFile = new File(file.getParent() + File.separator + newName);
			flag = file.renameTo(destFile);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 将File对象拷贝到指定目录
	 * @author  qye.zheng
	 * @param file 文件或目录对象
	 * @param destDirectory 目的目录 (可以不存在)
	 * @return
	 */
	public static final  boolean copy(final File file, String destDirectory)
	{
		boolean flag = false;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try
		{
			if (file.isFile())
			{
				// 文件，直接复制到指定路径
				inputStream = new FileInputStream(file);
				final File destFile = new File(destDirectory);
				if (!destFile.exists())
				{
					// 创建目录
					destFile.mkdirs();
				}
				outputStream = new FileOutputStream(destDirectory + File.separator + file.getName());
				IOUtil.transport(inputStream, outputStream);
			} else
			{
				// 目录，递归处理
				final File[] files = file.listFiles();
				destDirectory += File.separator + file.getName();
				final File destDir = new File(destDirectory);
				// 在目的目录的基础上，创建新的目录
				destDir.mkdir();
				if (!EmptyUtil.isEmpty(files))
				{
					for (File f : files)
					{
						copy(f, destDirectory);
					}
				}
			}
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			// 关闭流
			IOUtil.close(outputStream);
			IOUtil.close(inputStream);
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 将File对象数组拷贝到指定目录
	 * @author  qye.zheng
	 * @param files 文件或目录对象数组
	 * @param destDirectory 目的目录
	 * @return
	 */
	public static final  boolean copy(final File[] files, final String destDirectory)
	{
		boolean flag = false;
		try
		{
			for (File file : files)
			{
				copy(file, destDirectory);
			}
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 将指定位置的文件拷贝的指定目录
	 * @author  qye.zheng
	 * @param path 文件/目录(绝对)路径
	 * @param destDirectory 目的目录
	 * @return
	 */
	public static final  boolean copy(final String path, final String destDirectory)
	{
		boolean flag = false;
		try
		{
			final File file = new File(path);
			flag = copy(file, destDirectory);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 将指定位置的文件拷贝的指定目录
	 * @author  qye.zheng
	 * @param paths 文件/目录(绝对)路径数组
	 * @param destDirectory 目的目录
	 * @return
	 */
	public static final  boolean copy(final String[] paths, final String destDirectory)
	{
		boolean flag = false;
		try
		{
			for (String path : paths)
			{
				copy(path, destDirectory);
			}
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 
	 * @description 拷贝文件
	 * @param file
	 * @param filePath 文件路径
	 * @return
	 * @author qye.zheng
	 */
	public static final boolean copyFile(final File file, final String filePath)
	{
		boolean flag = false;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try
		{
			inputStream = new FileInputStream(file);
			outputStream = new FileOutputStream(filePath);
			IOUtil.transport(inputStream, outputStream);
			
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			// 关闭流
			IOUtil.close(outputStream);
			IOUtil.close(inputStream);
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 移动文件对象
	 * 先拷贝到目标目录，然后再执行删除
	 * @author  qye.zheng
	 * @param file 文件/目录对象
	 * @param destDirectory 目的目录
	 * @return
	 */
	public static final boolean move(final File file, final String destDirectory)
	{
		boolean flag = false;
		try
		{
			// 1.执行拷贝
			flag = copy(file, destDirectory);
			if (flag)
			{
				// 拷贝成功，再执行删除
				delete(file);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 移动文件对象
	 * 先拷贝到目标目录，然后再执行删除
	 * @author  qye.zheng
	 * @param path 文件/目录路径
	 * @param destDirectory 目的目录
	 * @return
	 */
	public static final boolean move(final String path, final String destDirectory)
	{
		final File file = new File(path);
		
		return move(file, destDirectory);
	}
	
	
	/**
	 * 
	 * 描述: 获取文件/目录 大小
	 * 单位: byte
	 * @author  qye.zheng
	 * @param file 文件对象
	 * @return
	 */
	public static final long getSize(final File file)
	{
		return file.length();
	}
	
	/**
	 * 
	 * 描述: 获取文件数据 - 二进制数据 - 字节
	 * @author  qye.zheng
	 * @param file 文件对象
	 * @return
	 */
	public static final byte[] getByteArray(final File file)
	{
		byte[] data = null;
		try
		{
			final InputStream inputStream = new FileInputStream(file);
			data = IOUtil.getByteArray(inputStream);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		return data;
	}

	/**
	 * 
	 * 描述: 获取文件数据 - 二进制数据 - 字节
	 * @author  qye.zheng
	 * @param filePath 文件路径
	 * @return
	 */
	public static final byte[] getByteArray(final String filePath)
	{
		final File file = new File(filePath);
		
		return getByteArray(file);
	}
	
	/**
	 * 
	 * 描述: 获取(文本)文件内容(字符数组)
	 * @author  qye.zheng
	 * @param file 文件对象
	 * @return
	 */
	public static final char[] getCharArray(final File file)
	{
		char[] data = null;
		try
		{
			final InputStream inputStream = new FileInputStream(file);
			final Reader reader = IOUtil.streamToReader(inputStream);
			data = IOUtil.getCharArray(reader);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		return data;
	}
	
	/**
	 * 
	 * 描述: 获取(文本)文件内容(字符数组)
	 * @author  qye.zheng
	 * @param filePath 文件路径
	 * @return
	 */
	public static final char[] getCharArray(final String filePath)
	{
		final File file = new File(filePath);
		
		return getCharArray(file);
	}
	
	/**
	 * 
	 * 描述: 获取(文本)文件内容(字符串)
	 * @author  qye.zheng
	 * @param file 文件对象
	 * @return
	 */
	public static final String getString(final File file)
	{
		String data = null;
		try
		{
			final InputStream inputStream = new FileInputStream(file);
			data = IOUtil.getString(inputStream);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		return data;
	}
	
	/**
	 * 
	 * 描述: 获取(文本)文件内容(字符串)
	 * @author  qye.zheng
	 * @param file 文件对象
	 * @param charset 字符集
	 * @return
	 */
	public static final String getString(final File file, final Charset charset)
	{
		String data = null;
		try
		{
			final InputStream inputStream = new FileInputStream(file);
			data = IOUtil.getString(inputStream, charset);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		return data;
	}
	
	/**
	 * 
	 * 描述: 获取(文本)文件内容(字符串)
	 * @author  qye.zheng
	 * @param filePath 文件路径
	 * @param charset 字符集
	 * @return
	 */
	public static final String getString(final String filePath, final Charset charset)
	{
		final File file = new File(filePath);
		
		return getString(file, charset);
	}
	
	/**
	 * 
	 * 描述: 获取(文本)文件内容(字符串)
	 * @author  qye.zheng
	 * @param filePath 文件路径
	 * @return
	 */
	public static final String getString(final String filePath)
	{
		final File file = new File(filePath);
		
		return getString(file);
	}
	
	/**
	 * 
	 * 描述: 输出字符串到文件
	 * @author  qye.zheng
	 * @param file
	 * @param data
	 * @param append 是否追加
	 * @param charset 字符集
	 * @return
	 */
	public static final boolean writeString(final File file, final String data, final String charset, final boolean append)
	{
		boolean flag = false;
		OutputStream outputStream = null;
		Writer writer =null;
		BufferedWriter bufferedWriter = null;
		try
		{
			outputStream = new FileOutputStream(file, append);
			writer = IOUtil.streamToWriter(outputStream, StandardCharsets.UTF_8);
			bufferedWriter = IOUtil.bufferedWriter(writer);
			
			// 输出字符串
			bufferedWriter.write(data);
			
			// 刷新缓存
			bufferedWriter.flush();
			
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			IOUtil.close(bufferedWriter);
			IOUtil.close(writer);
			IOUtil.close(outputStream);
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 输出字符串到文件
	 * @author  qye.zheng
	 * @param file
	 * @param data
	 * @return
	 */
	public static final boolean writeString(final File file, final String data, final boolean append)
	{
		return writeString(file, data, Constant.CHART_SET_UTF_8, append);
	}
	
	/**
	 * 
	 * 描述: 输出字符串到文件
	 * @author  qye.zheng
	 * @param filePath
	 * @param data
	 * @param charset 字符集
	 * @param append 是否追加
	 * @return
	 */
	public static final boolean writeString(final String filePath, final String data, final String charset, final boolean append)
	{
		final File file = new File(filePath);
		
		return writeString(file, data, charset, append);
	}
	
	/**
	 * 
	 * 描述: 输出字符串到文件
	 * @author  qye.zheng
	 * @param filePath
	 * @param data
	 * @return
	 */
	public static final boolean writeString(final String filePath, final String data, final boolean append)
	{
		final File file = new File(filePath);
		
		return writeString(file, data, append);
	}
	
	/**
	 * 
	 * 描述: 输出字符数组到文件
	 * @author qye.zheng
	 * @param file
	 * @param data
	 * @param append
	 * @return
	 */
	public static final boolean writeCharArray(final File file, final char[] data, final boolean append)
	{
		boolean flag = false;
		OutputStream outputStream = null;
		Writer writer =null;
		BufferedWriter bufferedWriter = null;
		try
		{
			outputStream = new FileOutputStream(file, append);
			writer = IOUtil.streamToWriter(outputStream);
			bufferedWriter = IOUtil.bufferedWriter(writer);
			
			// 输出字符串
			bufferedWriter.write(data);
			
			// 刷新缓存
			bufferedWriter.flush();
			
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			IOUtil.close(bufferedWriter);
			IOUtil.close(writer);
			IOUtil.close(outputStream);
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 输出字符数组到文件
	 * @author qye.zheng
	 * @param filePath
	 * @param data
	 * @return
	 */
	public static final boolean writeCharArray(final String filePath, final char[] data, final boolean append)
	{
		final File file = new File(filePath);
		
		return writeCharArray(file, data, append);
	}
	
	/**
	 * 
	 * 描述: 输出字节数组到文件
	 * @author qye.zheng
	 * @param file
	 * @param data
	 * @param append
	 * @return
	 */
	public static final boolean writeByteArray(final File file, final byte[] data, final boolean append)
	{
		boolean flag = false;
		OutputStream outputStream = null;
		try
		{
			outputStream = new FileOutputStream(file, append);
			outputStream.write(data);
			
			// 刷新缓存
			outputStream.flush();
			
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			IOUtil.close(outputStream);
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 输出字节数组到文件
	 * @author qye.zheng
	 * @param filePath
	 * @param data
	 * @return
	 */
	public static final boolean writeByteArray(final String filePath, final byte[] data, final boolean append)
	{
		final File file = new File(filePath);
		
		return writeByteArray(file, data, append);
	}
	
	/**
	 * 
	 * 描述: 递归打印输出文件信息
	 * 目录路径-
	 * --文件名: 绝对路径
	 * 树形 格式化输出
	 * @author  qye.zheng
	 * @param directoryPath 目录路径
	 */
	public static final void recursivePrint(final String directoryPath)
	{
		/*
		 -a
		 --a/b
		 a/b/info1.txt
		  a/b/info2.txt
		  
		  --a/c
		  
		 */
		int level = 1;
		final File directory = new File(directoryPath);
		printFile(directory, level);
	}
	
	/**
	 * 
	 * 描述: 
	 * @author  qye.zheng
	 * @param directory
	 * @param level
	 */
	private static final void printFile(final File directory, int level)
	{
		final File[] files = directory.listFiles();
		int i = 0;
		String info = "d";
		String blank = "";
		while (i < level)
		{
			info += "+";
			blank += "-";
			i++;
		}
		System.out.println(info + directory.getPath());
		
		// 空格
		for (File f : files)
		{
			if (f.isFile())
			{
				// 文件，直接输出
				System.out.println(blank + f.getAbsolutePath());
			} else
			{
				System.out.println();
				// 目录，递归调用，级别增1
				level++;
				printFile(f, level);
			}
		}
	}
	
	/**
	 * 
	 * 描述: 获取存储文件名
	 * @author qye.zheng
	 * 
	 * @param uploadFilename
	 * @return
	 */
	public static String getStoreFilename(final String uploadFilename) {
		// 根据上传文件的后缀名，用随机数生成新文件名
		/**
		 * 规则 : 
		 * 时间戳  + 5位随机数 + "." + 原文件名后缀
		 */
		final String timestamp = String.valueOf(DateTimeUtil.getTimeInMillis());
		final String numStr = RandomUtil.randomNumeric(5);
		// 文件后缀，包含 .
		final String suffix = uploadFilename.substring(uploadFilename.lastIndexOf(Constant.CHAR_DOT));
		
		return timestamp + numStr + suffix;
	}
	
	/**
	 * 
	 * 描述: 构造缓冲读取器
	 * @param path 文件路径
	 * @author  qye.zheng
	 * @param reader
	 * @return
	 */
	public static final BufferedReader bufferedReader(final String path)
	{
		InputStream inputStream = null;
		try
		{
			inputStream = new FileInputStream(path);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		final Reader reader = new InputStreamReader(inputStream);
		
		return  new BufferedReader(reader);
	}
	
	/* ###################### 文件名 ####################### */
	
	
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
		int index = 0;
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
