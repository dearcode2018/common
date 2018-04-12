/**
  * @filename BaseModule.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.base;

 /**
 * @type BaseModule
 * @description  
 * @author qye.zheng
 */
public abstract class BaseModule {

	private String tagName;
	
	/**
	 * @description 构造方法
	 * @author qye.zheng
	 */
	public BaseModule(final String tagName) {
		this.tagName = tagName;
	}

	/**
	 * @return the tagName
	 */
	public final String getTagName() {
		return tagName;
	}

	/**
	 * @param tagName the tagName to set
	 */
	public final void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
}
