/**
 * 描述: 读取 [类路径] 下的properties属性文件
 * ReadProperties.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * ReadProperties
 */
public final class ReadProperties {

	/* 数据操作工具，可单例 */
	private static Properties props;
	
	/* 数据来源，对象化 */
	private InputStream input;
	
	static {
		// 仅实例化一次
		props = new Properties();
	}
	
	public ReadProperties(final String filePath) {
		input = ClassPathUtil.getInputStream(filePath);
		try {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 描述: 根据key获取相应的值 
	 * @author qye.zheng
	 * 
	 * @param key
	 * @return
	 */
	public String getProperty(final String key) {
		
		return props.getProperty(key);
	}
	
	/**
	 * 
	 * 描述: 根据key获取相应的值 
	 * 替换值中的{0} {1} ... {n}
	 * @author qye.zheng
	 * 
	 * @param key
	 * @param value 默认用 , 隔开
	 * @return
	 */
	public String getProperty(final String key, final String value) {
		
		return getProperty(key, value, ",");
	}
	
	/**
	 * 
	 * 描述: 根据key获取相应的值 
	 * 替换值中的{0} {1} ... {n}
	 * @author qye.zheng
	 * 
	 * @param key
	 * @param value
	 * @param separator 分隔符
	 * @return
	 */
	public String getProperty(final String key, final String value, final String separator) {
		final String orignalValue = props.getProperty(key);
		final Object[] arr = value.split(separator);
		final String message = MessageFormat.format(orignalValue, arr);
		
		return message;
	}
	
	   /**
     * 描述: 根据key获取相应的值 
     * @author qye.zheng
     * 
     * @param key
     * @return
     */
    public String getPropertyOrDefault(final String key, final String defaultValue) {
        
        return props.getProperty(key, defaultValue);
    }

	/**
	 * @return the props
	 */
	public Properties getProps() {
		
		return props;
	}
	
	
	
}
