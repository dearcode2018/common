/**
* WebUtil.java
* 
* @author qye.zheng
* 	version 1.0
 */
package com.hua.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.hua.entity.FileInfo;

/**
 * 描述: web 工具类
 * @author qye.zheng
 * WebUtil
 */
public final class WebUtil {
	
	/** 无参构造方法 */
	private WebUtil() {}
	
	/**
	 * 
	 * 描述: 获取请求uri的后缀 
	 * 
	 * @author qye.zheng
	 * @param request
	 * @return
	 */
	public static String getRequestUriSuffix(final HttpServletRequest request) {
		String uri = request.getRequestURI();
		// 获取后缀 xx.do xx.action ..
		uri = uri.substring(uri.lastIndexOf("/") + 1);
		return  uri;
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param info
	 * @return
	 */
	public static boolean store(final List<FileInfo> infos, final byte[] requestBody) {
		OutputStream output = null;
		try
		{
			System.out.println("size = " + infos.size());
			if (!EmptyUtil.isEmpty(infos))
			{
				for (FileInfo info : infos)
				{
					System.out.println("storePath --> " + info.getStorePath());
					output = new FileOutputStream(info.getStorePath());
					output.write(requestBody,
							info.getFilePosition().getBegin(),
							info.getFileSize());
					// 刷新缓存
					output.flush();
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();

			return false;
		} finally
		{
			if (null != output)
			{
				try
				{
					output.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		return true;
	}
	
	/**
	 * 
	 * 描述: 存储文件
	 * 
	 * 将文件数据从内存存储到硬盘
	 * 
	 * @author qye.zheng
	 * 
	 * @param path 文件存放路径
	 */
	public static boolean store(final HttpServletRequest request, final byte[] fileData, String path)
	{
		System.out.println("fileSize = " + fileData.length);
		OutputStream output = null;
		path = WebPathUtil.getWebSubpath(request.getServletContext(), path);
		try
		{
			output = new FileOutputStream(path);
			output.write(fileData);
			// 刷新缓存
			output.flush();
		} catch (Exception e)
		{
			e.printStackTrace();
			
			return false;
		} finally {
			if (null != output) {
				try
				{
					output.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 * @description 
	 * @param request
	 * @param name
	 * @return
	 * @author qye.zheng
	 */
	public static final Cookie findCookie(HttpServletRequest request, String name)
	{
		if (request != null)
		{
			Cookie[] cookies = request.getCookies();
			if (cookies != null && cookies.length > 0)
			{
				for (Cookie cookie : cookies)
				{
					if (cookie.getName().equals(name))
					{
						return cookie;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @description 输出普通字符串到客户端
	 * @param response
	 * @param value
	 * @author qye.zheng
	 */
	public static final void write(final ServletResponse response, final String value)
	{
		Writer writer = null;
		try {
			writer = response.getWriter();
			writer.write(value);
			// 刷新缓存
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally 
		{
			IOUtil.close(writer);
		}
	}

}
