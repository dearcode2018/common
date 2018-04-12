/**
 * RegexConstant.java
 * @author qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.constant;

/**
 * RegexConstant
 * 描述: 正则常量
 * @author qye.zheng
 * 
 */
public interface RegexConstant
{

	// 数字
	String NUMBER_REGEX = "^[1-9]\\d*$";
	
	// 英文
	
	// MAC 地址
	 String MAC_REGEX = "^([0-9a-fA-F]{2})(([0-9a-fA-F]{2}){5})$";
	 
	 // 标签 (<a></a>)
	 String TAG_REGEX = "</?\\w+.*?>";
	 
	 // 包含数字
	 String HAS_NUMBER = ".*\\d+.*";
	 
	 String BINARY_FIGURE = "[0|1]";
}
