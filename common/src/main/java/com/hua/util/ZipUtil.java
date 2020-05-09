/**
 * ZipUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import com.hua.constant.Constant;
import com.hua.constant.ZipConstant;

/**
 * ZipUtil 描述: 文件压缩 - 工具类
 * 
 * @author qye.zheng
 */
public final class ZipUtil
{

	private static ZipOutputStream zipOutputStream;

	private static ZipEntry zipEntry;

	private static Charset charset = Charset.forName("UTF-8");

	private static InputStream inputStream = null;

	private static OutputStream outputStream = null;

	private static BufferedInputStream bufferedInputStream = null;

	private static BufferedOutputStream bufferedOutputStream = null;

	private static ZipFile zipFile = null;

	private static final boolean close = false;

	/**
	 * 构造方法 描述: 私有 - 禁止实例化
	 * 
	 * @author qye.zheng
	 */
	private ZipUtil()
	{
	}

	/**
	 * 
	 * 描述: 文件/目录压缩
	 * 
	 * @author qye.zheng
	 * @param sourceFile
	 *            原文件/目录对象
	 * @param destZipPath
	 *            目的zip路径，为空，压缩到当前目录 若是文件则以文件前缀名命名，若是目录则以目录名称命名
	 * @return
	 */
	public static boolean zip(final File sourceFile, String destZipPath)
	{
		boolean flag = false;
		try
		{
			if (StringUtil.isEmpty(destZipPath))
			{
				destZipPath = getDefaultZipPath(sourceFile);
			}
			// 压缩任务 - 初始化
			initZip(destZipPath);

			// 路径截取下标 
			final int startTrimIndex = getStartTrimIndex(sourceFile);
			/*
			 解决空目录无法压缩问题
			 */
			// 目标目录为空，直接创建
			if (EmptyUtil.isEmpty(sourceFile.listFiles()))
			{
				final String pathName = sourceFile.getPath().substring(startTrimIndex) + Constant.SLASH;
				System.out.println("目录，递归调用处理: " + pathName);
				zipEntry = new ZipEntry(pathName);
				// 放入 ZipOutputStream 下一个项
				zipOutputStream.putNextEntry(zipEntry);
				flag = true;
				
				return flag;
			}
			// 调用核心压缩逻辑
			zipFile(sourceFile, startTrimIndex);
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			closeZipStream();
		}

		return flag;
	}

	/**
	 * 
	 * 描述: 文件/目录压缩
	 * 
	 * @author qye.zheng
	 * @param sourceFiles
	 *            原文件/目录对象数组
	 * @param destZipPath
	 *            目的zip路径，必须指定压缩文件存放路径
	 * @return
	 */
	public static boolean zip(final File[] sourceFiles, final String destZipPath)
	{
		boolean flag = false;
		try
		{
			// 压缩任务 - 初始化
			initZip(destZipPath);
			int startTrimIndex = -1;
			// 循环调用
			for (File sourceFile : sourceFiles)
			{
				// 路径截取下标 
				startTrimIndex = getStartTrimIndex(sourceFile);
				/*
				 解决空目录无法压缩问题
				 */
				// 目标目录为空，直接创建
				if (EmptyUtil.isEmpty(sourceFile.listFiles()))
				{
					final String pathName = sourceFile.getPath().substring(startTrimIndex) + Constant.SLASH;
					System.out.println("目录，递归调用处理: " + pathName);
					zipEntry = new ZipEntry(pathName);
					// 放入 ZipOutputStream 下一个项
					zipOutputStream.putNextEntry(zipEntry);
					
					continue;
				}
				// 调用核心压缩逻辑
				zipFile(sourceFile, startTrimIndex);
			}
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			closeZipStream();
		}

		return flag;
	}

	/**
	 * 
	 * 描述: 文件/目录压缩
	 * 
	 * @author qye.zheng
	 * @param source
	 *            原文件/目录
	 * @param destZipPath
	 *            目的zip路径，为空，压缩到当前目录 若是文件则以文件前缀名命名，若是目录则以目录名称命名
	 * @return
	 */
	public static boolean zip(final String source, String destZipPath)
	{
		boolean flag = false;
		try
		{
			// 构造文件对象，然后调用File参数的方法
			final File sourceFile = new File(source);
			if (StringUtil.isEmpty(destZipPath))
			{
				destZipPath = getDefaultZipPath(sourceFile);
			}
			flag = zip(sourceFile, destZipPath);
		} finally
		{
			closeZipStream();
		}

		return flag;
	}

	/**
	 * 
	 * 描述: 文件/目录压缩
	 * 
	 * @author qye.zheng
	 * @param sources
	 *            原文件/目录数组
	 * @param destZipPath
	 *            目的zip路径，必须指定压缩文件存放路径
	 * @return
	 */
	public static boolean zip(final String[] sources, final String destZipPath)
	{
		boolean flag = false;
		try
		{
			// 压缩任务 - 初始化
			initZip(destZipPath);
			int startTrimIndex = -1;
			File sourceFile = null;
			for (String source : sources)
			{
				sourceFile = new File(source);
				// 路径截取下标 
				startTrimIndex = getStartTrimIndex(sourceFile);
				/*
				 解决空目录无法压缩问题
				 */
				// 目标目录为空，直接创建
				if (EmptyUtil.isEmpty(sourceFile.listFiles()))
				{
					final String pathName = sourceFile.getPath().substring(startTrimIndex) + Constant.SLASH;
					System.out.println("目录，递归调用处理: " + pathName);
					zipEntry = new ZipEntry(pathName);
					// 放入 ZipOutputStream 下一个项
					zipOutputStream.putNextEntry(zipEntry);
					
					continue;
				}
				// 调用核心压缩逻辑
				zipFile(sourceFile, startTrimIndex);
			}
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			closeZipStream();
		}

		return flag;
	}

	/**
	 * 
	 * 描述: 压缩 - 共同方法
	 * 供对外多个方法调用
	 * @author  qye.zheng
	 * @param destZipPath 压缩后的文件路径
	 */
	private static void initZip(final String destZipPath)
	{
		// 每次压缩任务 调用该方法进行初始化
		try
		{
			outputStream = new FileOutputStream(destZipPath);
			bufferedOutputStream = new BufferedOutputStream(outputStream);
			zipOutputStream = new ZipOutputStream(bufferedOutputStream, charset);	
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 描述: 将指定的[文件对象]压缩入 .zip文件
	 * 
	 * @author qye.zheng
	 * @param sourceFile
	 * @param startTrimIndex
	 */
	private static void zipFile(final File sourceFile, final int startTrimIndex)
	{
		try
		{
			String pathName = null;
			File[] files = null;
			if (sourceFile.isDirectory())
			{
				// 目录
				files = sourceFile.listFiles();
			} else
			{
				// 文件，构造大小为1的文件数组
				files = new File[] { sourceFile };
			}
			for (File file : files)
			{
				// System.out.println(file.getName());
				if (file.isDirectory())
				{
					// 目录，递归调用处理
					/**
					 * pathName = file.getPath().substring(startTrimIndex) +
					 * File.separator; -- 会出现空文件 pathName =
					 * file.getPath().substring(startTrimIndex) + "/"; -- 正常
					 * pathName = file.getPath().substring(startTrimIndex); --
					 * 所有目录都会呈现空白状态
					 */
					// pathName = file.getPath().substring(startTrimIndex) + File.separator;
					// pathName = file.getPath().substring(startTrimIndex);
					pathName = file.getPath().substring(startTrimIndex) + Constant.SLASH;
					System.out.println("目录，递归调用处理: " + pathName);
					zipEntry = new ZipEntry(pathName);
					// 放入 ZipOutputStream 下一个项
					zipOutputStream.putNextEntry(zipEntry);
					// 递归调用
					zipFile(file, startTrimIndex);
				} else
				{
					// 文件 - 直接压缩处理
					// 相对路径名称: a.txt 或 b/a.txt (+1 表示 排除 前路径分隔符)
					//pathName = file.getPath().substring(startTrimIndex);
					pathName = file.getPath().substring(startTrimIndex);
					// 把反斜杠替换为斜杠
					pathName = pathName.replaceAll("\\\\", "/");
					// pathName = file.getPath().substring(basePath.length() +1);
					System.out.println("文件: " + pathName);
					inputStream = new FileInputStream(file);
					bufferedInputStream = new BufferedInputStream(inputStream);
					zipEntry = new ZipEntry(pathName);
					zipOutputStream.putNextEntry(zipEntry);
					// 输出
					IOUtil.transport(bufferedInputStream, zipOutputStream, close);
				}
			} 
			// 刷新缓存
			zipOutputStream.flush();
			bufferedOutputStream.flush();
			outputStream.flush();
			// 关闭输入流
			if (null != bufferedInputStream)
			{
				bufferedInputStream.close();
			}
			if (null != inputStream)
			{
				inputStream.close();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 描述: 获取每次压缩目录开始截取的下标
	 * @author  qye.zheng
	 * @param sourceFile
	 * @return
	 */
	private static int getStartTrimIndex(final File sourceFile)
	{
		// 获取基本目录
		String basePath = null;
		int startTrimIndex = Constant.NEGATIVE_ONE;
		if (sourceFile.isDirectory())
		{
			/*
			 基本目录指向父级目录，把当前指定的目录包含在压缩包内
			 */
			basePath = sourceFile.getParent();
			/*
			 解决windows 盘符是英文字母和放斜杠构成
			 当parent是盘符时，后面以反斜杠结束
			 */
			// 判断是否以斜杠结束
			if (basePath.endsWith(File.separator))
			//if (basePath.endsWith("/"))
			{
				// 以斜杠结束，无需 +1排除 前路径分割符
				startTrimIndex = basePath.length();	
			} else
			{
				// 父路径没有以斜杠结束，因此需要+1 排除 前路径分割符
				startTrimIndex = basePath.length() + Constant.ONE;	
			}
			System.out.println("basePath = " + basePath);
		} else
		{
			// 文件 - 获取父级目录
			basePath = sourceFile.getParent();
			// 路径截取下标 (+1 表示排除 前路径分割符)
			startTrimIndex = basePath.length() + Constant.ONE;	
		}
		
		return startTrimIndex;
	}
	
	/**
	 * 
	 * 描述: 关闭zip压缩的流
	 * @author  qye.zheng
	 */
	private static void closeZipStream()
	{
		try
		{
			if (null != zipOutputStream)
			{
				// 结束操作
				//zipOutputStream.finish();
				zipOutputStream.close();
			}
			if (null != bufferedOutputStream)
			{
				bufferedOutputStream.close();
			}
			if (null != outputStream)
			{
				outputStream.close();
			}
		} catch (IOException e2)
		{
			e2.printStackTrace();
		}
	}
	
	/**
	 * 直接将zip文件中的内容解压到指定的目录 描述: zip文件解压
	 * 
	 * @author qye.zheng
	 * @param sourceFile
	 *            zip文件对象
	 * @param destUnzipDir
	 *            目的zip解压路径，为空，解压到当前目录
	 * @return
	 */
	public static boolean unzip(final File sourceFile, String destUnzipDir)
	{
		boolean flag = false;
		try
		{
			if (StringUtil.isEmpty(destUnzipDir))
			{
				destUnzipDir = sourceFile.getParent();
			}
			// 调用核心解压缩逻辑
			unzipFile(sourceFile, destUnzipDir);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 直接将zip文件中的内容解压到指定的目录 描述: zip文件解压
	 * 
	 * @author qye.zheng
	 * @param sourceFiles
	 *            zip文件对象数组
	 * @param destUnzipDir
	 *            目的zip解压路径，必须指定解压后文件的存放路径
	 * @return
	 */
	public static boolean unzip(final File[] sourceFiles,
			final String destUnzipDir)
	{
		boolean flag = false;
		try
		{
			// 循环遍历，将多个zip文件对象解压到一个公共目录
			for (File sourceFile: sourceFiles)
			{
				// 调用核心解压缩逻辑
				unzipFile(sourceFile, destUnzipDir);
			}
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 直接将zip文件中的内容解压到指定的目录 描述: zip文件解压
	 * 
	 * @author qye.zheng
	 * @param zipPath
	 *            zip文件
	 * @param destUnzipDir
	 *            目的zip解压路径，为空，解压到当前目录
	 * @return
	 */
	public static boolean unzip(final String zipPath, String destUnzipDir)
	{
		boolean flag = false;
		try
		{
			final File sourceFile = new File(zipPath);
			if (StringUtil.isEmpty(destUnzipDir))
			{
				destUnzipDir = sourceFile.getParent();
			}
			// 调用核心解压缩逻辑
			unzipFile(sourceFile, destUnzipDir);
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 直接将zip文件中的内容解压到指定的目录 描述: zip文件解压
	 * 
	 * @author qye.zheng
	 * @param zipPaths
	 *            zip文件数组
	 * @param destUnzipDir
	 *            目的zip解压路径，必须指定解压后文件的存放路径
	 * @return
	 */
	public static boolean unzip(final String[] zipPaths,
			final String destUnzipDir)
	{
		boolean flag = false;
		try
		{
			File sourceFile = null;
			// 循环遍历，将多个zip文件对象解压到一个公共目录
			for (String zipPath: zipPaths)
			{
				sourceFile = new File(zipPath);
				// 调用核心解压缩逻辑
				unzipFile(sourceFile, destUnzipDir);
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
	 * 描述: 核心解压缩逻辑
	 * 将一个指定的zip文件所有文件都解压出来
	 * @author  qye.zheng
	 * @param sourceFile
	 * @param destUnzipDir
	 */
	private static void unzipFile(final File sourceFile, final String destUnzipDir)
	{
		try
		{
			// 指定编码，解决中文命名解析失败问题
			charset = Charset.forName("GB2312");
			// 指定编码，解决中文无法解析问题 (必须指定为GB2312, UTF-8解析中文失败)
			zipFile = new ZipFile(sourceFile, charset);
			File targetFile = null;
			final Enumeration<? extends ZipEntry> zipEntries = zipFile
					.entries();
			while (zipEntries.hasMoreElements())
			{
				zipEntry = zipEntries.nextElement();
				System.out.println("zipEntry: " + zipEntry.getName());
/*				targetFile = new File(destUnzipDir + File.separator
						+ zipEntry.getName());*/
				targetFile = new File(destUnzipDir + "/"
						+ zipEntry.getName());
				if (zipEntry.isDirectory())
				{
					// 创建多级目录，不再往下执行
					if (!targetFile.exists())
					{
						targetFile.mkdirs();
					}

					continue;
				} else
				{
					if (!targetFile.getParentFile().exists())
					{
						System.out.println("创建多级父目录");
						// 创建多级父目录
						targetFile.getParentFile().mkdirs();
					}
				}
				System.out.println(targetFile.getAbsolutePath());
				if (zipEntry.getSize() > 0)
				{
					outputStream = new FileOutputStream(targetFile);
					bufferedOutputStream = new BufferedOutputStream(
							outputStream);
					inputStream = zipFile.getInputStream(zipEntry);
					// 输出流
					IOUtil.transport(inputStream, bufferedOutputStream,
							close);
				} else
				{
					// 空文件
					System.out.println("空文件...");

					// 防止空文件存在，而目录没有创建的情况
					if (!targetFile.exists())
					{
						targetFile.mkdirs();
					}

					continue;
				}

				// 关闭每次循环创建的输出流
				bufferedOutputStream.flush();
				bufferedOutputStream.close();
				outputStream.flush();
				outputStream.close();
				inputStream.close();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @description 获取默认的 [压缩] 路径
	 * @param sourceFile
	 * @return
	 * @author qye.zheng
	 */
	private static final String getDefaultZipPath2(final File sourceFile)
	{
		String destZipPath = null;
		if (sourceFile.isDirectory())
		{
			// 目录
			destZipPath = sourceFile.getParent() + "/" + sourceFile.getName() +Constant.DOT_MARK + ZipConstant.ZIP_FILE_SUFFIX;
		} else
		{
			// 文件
			destZipPath = sourceFile.getParent() + "/" + FileUtil.getPrefix(sourceFile.getName()) +Constant.DOT_MARK + ZipConstant.ZIP_FILE_SUFFIX;
		}
		
		return destZipPath;
	}
	
	/**
	 * 
	 * @description 获取默认的 [压缩] 路径
	 * @param sourceFile
	 * @return
	 * @author qye.zheng
	 */
	//@Deprecated
	private static final String getDefaultZipPath(final File sourceFile)
	{
		String destZipPath = null;
		if (sourceFile.isDirectory())
		{
			// 目录
			destZipPath = sourceFile.getParent() + File.separator + sourceFile.getName() +Constant.DOT_MARK + ZipConstant.ZIP_FILE_SUFFIX;
		} else
		{
			// 文件
			destZipPath = sourceFile.getParent() + File.separator + FileUtil.getPrefix(sourceFile.getName()) +Constant.DOT_MARK + ZipConstant.ZIP_FILE_SUFFIX;
		}
		
		return destZipPath;
	}
	
	/**
	 * 
	 * @description 判断压缩文件是否损坏
	 * @param zipFile 压缩文件
	 * .zip .jar 等压缩文件
	 * @return
	 * @author qianye.zheng
	 */
	public static final boolean isDamage(final File zipFile)
	{
		/*
		 * 通过构造方法是否有异常来判断文件是否损坏
		 */
		boolean flag = false;
		ZipFile zipF = null;
		try
		{
			zipF = new ZipFile(zipFile);
		} catch (Exception e)
		{ // 有异常，说明压缩文件可能损坏了
			flag = true;
		} finally
		{ // 关闭资源
			if (null != zipF)
			{
				try
				{
					zipF.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		return flag;
	}
	
	/**
	 * 
	 * @description 判断压缩文件是否损坏
	 * @param zipFilePath 压缩文件路径
	 * @return
	 * @author qianye.zheng
	 */
	public static final boolean isDamage(final String zipFilePath)
	{
		
		return isDamage(new File(zipFilePath));
	}

}
