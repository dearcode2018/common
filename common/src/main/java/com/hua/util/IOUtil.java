/**
 * IOUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.hua.constant.Constant;

/**
 * IOUtil
 * 描述: 输入输出 - 工具类
 * @author  qye.zheng
 */
public final class IOUtil extends org.apache.commons.io.IOUtils
{

	/**
	 	标准流
	 	InputStream System.in 标准输入流
		PrintStream System.out标准输出流
		PrintStream System.err标准出错流 (也是一种输出流)
	 */
	
	/**
	 * 构造方法
	 * 描述: 私有 - 禁止实例化
	 * @author  qye.zheng
	 */
	private IOUtil()
	{
	}

	/**
	 * 
	 * 描述: 获取标准输入流
	 * @author qye.zheng
	 * @return
	 */
	public static final InputStream getStandardInputStream()
	{
		return System.in;
	}
	
	/**
	 * 
	 * 描述: 获取标准输出流
	 * @author qye.zheng
	 * @return
	 */
	public static final OutputStream getStandardOutputStream()
	{
		return System.out;
	}
	
	/**
	 * 
	 * 描述: 获取标准出错输出流
	 * @author qye.zheng
	 * @return
	 */
	public static final OutputStream getStandardErrorStream()
	{
		return System.err;
	}
	
	/**
	 * 
	 * 描述: 输入流 -传输-> 输出流
	 * @author  qye.zheng
	 * @param inputStream
	 * @param outputStream
	 * @return
	 */
	public static final boolean transport(final InputStream inputStream, final OutputStream outputStream)
	{
		final int size = 1024;
		final boolean close = true;
		
		return transport(inputStream, outputStream, size, close);
	}
	
	/**
	 * 
	 * 描述: 输入流 -传输-> 输出流
	 * @author  qye.zheng
	 * @param inputStream
	 * @param outputStream
	 * @param close 是否关闭流
	 * @return
	 */
	public static final boolean transport(final InputStream inputStream, final OutputStream outputStream, final boolean close)
	{
		final int size = 1024;
		
		return transport(inputStream, outputStream, size, close);
	}
	
	/**
	 * 
	 * 描述: 输入流 -传输-> 输出流
	 * @author  qye.zheng
	 * @param inputStream
	 * @param outputStream
	 * @param size 缓冲区大小 (单位: 字节byte)
	 * @param close 是否关闭流
	 */
	public static final boolean transport(final InputStream inputStream, final OutputStream outputStream, final int size, final boolean close)
	{
		boolean flag = false;
		final byte[] data = new byte[size];
		int length = -1;
		try
		{
			while (-1 != (length = inputStream.read(data)))
			{
				outputStream.write(data, 0, length);
			}
			// 刷新缓存
			outputStream.flush();
			flag = true;
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally {
			// 关闭流
			if (close)
			{
				close(outputStream);
				close(inputStream);
			}
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 输入流 -传输-> 输出流
	 * @author  qye.zheng
	 * @param reader
	 * @param writer
	 * @return
	 */
	public static final boolean transport(final Reader reader, final Writer writer)
	{
		final int size = 1024;
		final boolean close = true;
		
		return transport(reader, writer, size, close);
	}
	
	/**
	 * 
	 * 描述: 输入流 -传输-> 输出流
	 * @author  qye.zheng
	 * @param reader
	 * @param writer
	 * @param close 是否关闭流
	 * @return
	 */
	public static final boolean transport(final Reader reader, final Writer writer, final boolean close)
	{
		final int size = 1024;
		
		return transport(reader, writer, size, close);
	}
	
	/**
	 * 
	 * 描述: 输入流 -传输-> 输出流
	 * @author  qye.zheng
	 * @param reader
	 * @param writer
	 * @param size 缓冲区大小 (单位: 字节byte)
	 * @param close 是否关闭流
	 */
	public static final boolean transport(final Reader reader, final Writer writer, final int size, final boolean close)
	{
		boolean flag = false;
		final char[] data = new char[size];
		int length = -1;
		try
		{
			while (-1 != (length = reader.read(data)))
			{
				writer.write(data, 0, length);
			}
			// 刷新缓存
			writer.flush();
			flag = true;
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally {
			// 关闭流
			if (close)
			{
				close(writer);
				close(reader);
			}
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 将字节输入流 转化为 字符读取器 (UTF-8格式编码)
	 * @author  qye.zheng
	 * @param inputStream
	 * @return
	 */
	public static final Reader streamToReader(final InputStream inputStream)
	{
		return new InputStreamReader(inputStream, StandardCharsets.UTF_8);
	}
	
	/**
	 * 
	 * 描述: 将字节输出流 转化为 字符写出器 (UTF-8格式编码)
	 * @author  qye.zheng
	 * @param outputStream
	 * @return
	 */
	public static final Writer streamToWriter(final OutputStream outputStream)
	{
		return streamToWriter(outputStream, StandardCharsets.UTF_8);
	}
	
	/**
	 * 
	 * 描述: 将字节输出流 转化为 字符写出器 (UTF-8格式编码)
	 * @author  qye.zheng
	 * @param outputStream
	 * @param charset 字符集
	 * @return
	 */
	public static final Writer streamToWriter(final OutputStream outputStream, final Charset charset)
	{
		return new OutputStreamWriter(outputStream, charset);
	}
	
	/**
	 * 
	 * 描述: 构造缓冲输入流
	 * @author  qye.zheng
	 * @param inputStream
	 * @return
	 */
	public static final BufferedInputStream bufferedStream(final InputStream inputStream)
	{
		return new BufferedInputStream(inputStream);
	}
	
	/**
	 * 
	 * 描述: 构造缓冲输出流
	 * @author  qye.zheng
	 * @param outputStream
	 * @return
	 */
	public static final BufferedOutputStream bufferedStream(final OutputStream outputStream)
	{
		return new BufferedOutputStream(outputStream);
	}
	
	/**
	 * 
	 * 描述: 构造缓冲读取器
	 * @author  qye.zheng
	 * @param reader
	 * @return
	 */
	public static final BufferedReader bufferedReader(final Reader reader)
	{
		return new BufferedReader(reader);
	}
	
	/**
	 * 
	 * 描述: 构造缓冲写出器
	 * @author  qye.zheng
	 * @param writer
	 * @return
	 */
	public static final BufferedWriter bufferedWriter(final Writer writer)
	{
		return new BufferedWriter(writer);
	}
	
	/**
	 * 
	 * 描述: 从输入流中获取全部字节数组
	 * @author qye.zheng
	 * @param inputStream
	 * @return
	 */
	public static final byte[] getByteArray(final InputStream inputStream)
	{
		byte[] data = null;
		try
		{
			final int size = inputStream.available();
			// 构造一个和输入流数据长度相等的字节数组
			data = new byte[size];
			// 一次性读取到字节数组
			inputStream.read(data);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return data;
	}
	
	/**
	 * 
	 * 描述: 从输入流中获取全部字符数组
	 * @author qye.zheng
	 * @param reader
	 * @return
	 */
	public static final char[] getCharArray(final Reader reader)
	{
		final StringBuilder builder = StringUtil.getBuilder();
		final char[] charArray = new char[1024];
		int length = -1;
        try
		{
            while (-1 != (length = reader.read(charArray)))
            {
            	builder.append(charArray, 0, length);
            }
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			close(reader);
		}
        // 转成 字符数组
		return builder.toString().toCharArray();
	}
	
	/**
	 * 
	 * 描述: 从输入流中获取字符串
	 * @author qye.zheng
	 * @param inputStream
	 * @return
	 */
	public static final String getString(final InputStream inputStream)
	{
        return getString(inputStream, StandardCharsets.UTF_8);
	}
	
	/**
	 * 
	 * 描述: 从输入流中获取字符串
	 * @author qye.zheng
	 * @param inputStream
	 * @param charset 字符集
	 * @return
	 */
	public static final String getString(final InputStream inputStream, final Charset charset)
	{
		final StringBuilder builder = StringUtil.getBuilder();     
        String line = null;      
    	final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset));      
        try 
        {
            while (null != (line = reader.readLine())) {
                builder.append(line + Constant.LINE_BREAK);      
            }      
        } catch (IOException e) 
        {      
            e.printStackTrace();      
        } finally 
        {
        	close(reader);
        	close(inputStream);
        }      
        
        return builder.toString();      
	}
	
	/**
	 * 
	 * 描述: 从输入流中获取字符串
	 * @author qye.zheng
	 * @param reader
	 * @return
	 */
	public static final String getString(final Reader reader)
	{
		final StringBuilder builder = StringUtil.getBuilder();
		final char[] charArray = new char[1024];
		int length = -1;
        try
		{
            while (-1 != (length = reader.read(charArray)))
            {
            	builder.append(charArray, 0, length);
            }
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			close(reader);
		}
		
		return builder.toString();
	}
	
	/**
	 * 
	 * 描述: 关闭流
	 * Closeable 继承 AutoCloseable，覆盖close方法，
	 * 抛出的异常变小
	 * @author  qye.zheng
	 * @param autoCloseable
	 */
	public static final void close(final AutoCloseable autoCloseable)
	{
		try
		{
			if (null != autoCloseable)
			{
				autoCloseable.close();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 描述: 关闭流
	 * Closeable 继承 AutoCloseable，覆盖close方法，
	 * 抛出的异常变小
	 * @author  qye.zheng
	 * @param autoCloseable
	 */
	public static final void closeA(final AutoCloseable autoCloseable)
	{
		try
		{
			if (null != autoCloseable)
			{
				autoCloseable.close();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
