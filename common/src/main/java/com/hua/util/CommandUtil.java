/**
 * CommandUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * CommandUtil
 * 描述: 指令
 * @author  qye.zheng
 */
public final class CommandUtil
{
	
	protected static final Logger logger = LogManager.getLogger(CommandUtil.class);

	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	private CommandUtil()
	{
	}

	/**
	 * 
	 * @description 执行 OS 指令
	 * @param command 指令
	 * @return
	 * @author qianye.zheng
	 */
	public static final String execute(final String command) {
		final Runtime rt = Runtime.getRuntime();
		try
		{
			final Process ps = rt.exec(command);
			// 等待执行完成
			ps.waitFor();
			final InputStream inputStream = ps.getInputStream();
			final byte[] data = new byte[inputStream.available()];
			// 返回读取到的字节数
			final int n = inputStream.read(data);
			if (n > 0) {
				
				return new String(data).trim();
			}
		} catch (Exception e)
		{
			logger.error("执行 OS 指令异常: {}", e);
		}
		
		return "command result unknow!";
	}
	
}
