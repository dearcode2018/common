/**
  * @filename DateTimeFormat.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @type DateTimeFormat
 * @description 
 * @author qianye.zheng
 */
public enum DateTimeFormat {

	FORMAT1("hh:mm:ss", "12小时制，2位"),
	
	FORMAT2("HH:mm:ss", "24小时制，2位"),
	
	FORMAT10("yyyyMMdd", "年月日"),
	FORMAT15("yyyyMMddHHmmssS", "年月时分秒毫秒"),
	FORMAT16("yyyyMMddHHmmssSSS", "年月时分秒毫秒"),
	
	FORMAT21("yyyy-MM", "年-月"),
	FORMAT22("yyyy-MM-dd", "年-月-日"),
	
	FORMAT25("yyyy-MM-dd HH:mm:ss", "年-月-日 时:分:秒"),
	FORMAT26("yyyy-MM-dd HH:mm:ss.SSS", "年-月-日 时:分:秒.毫秒"),
	
	// 可任意扩展多种格式
	;
	
	private String value;
	
	private String remark;
	
	private DateFormat dateFormat;

	/**
	 * 
	 * @description 构造方法
	 * @param value
	 * @param remark
	 * @author qianye.zheng
	 */
	private DateTimeFormat(final String value, final String remark) {
		this.value = value;
		this.remark = remark;
		this.dateFormat = new SimpleDateFormat(value);
	}

	/**
	* @return the value
	*/
	public String getValue() {
		return value;
	}

	/**
	* @return the remark
	*/
	public String getRemark() {
		return remark;
	}

	/**
	* @return the dateFormat
	*/
	public DateFormat getDateFormat() {
		return dateFormat;
	}
	
}
