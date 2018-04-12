/**
  * @filename Property.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.base;

 /**
 * @type Property
 * @description  
 * @author qye.zheng
 */
public final class Property extends BaseModule {

	public static final String PROPERTY_NAME_KEY = "name";
	
	public static final String PROPERTY_VALUE_KEY = "value";
	
	private String name;
	
	private String value;
	
	/**
	 * @description 构造方法
	 * @param tagName
	 * @author qye.zheng
	 */
	public Property() {
		super("property");
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public final String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public final void setValue(String value) {
		this.value = value;
	}

}
