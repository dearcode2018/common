/**
  * @filename WebModule.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.base;

import java.util.ArrayList;
import java.util.List;

 /**
 * @type WebModule
 * @description  
 * @author qye.zheng
 */
public final class WebModule extends BaseModule {

	public static final String PROPERTY_KEY = "deploy-name";
	
	private List<WebResource> webResources = new ArrayList<WebResource>();
	
	private List<Property>properties = new ArrayList<Property>();
	
	private String deployName;
	
	/**
	 * @description 构造方法
	 * @author qye.zheng
	 */
	public WebModule() {
		super("wb-module");
	}

	/**
	 * @return the webResources
	 */
	public final List<WebResource> getWebResources() {
		return webResources;
	}

	/**
	 * @param webResources the webResources to set
	 */
	public final void setWebResources(List<WebResource> webResources) {
		this.webResources = webResources;
	}

	/**
	 * @return the properties
	 */
	public final List<Property> getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public final void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	/**
	 * @return the deployName
	 */
	public final String getDeployName() {
		return deployName;
	}

	/**
	 * @param deployName the deployName to set
	 */
	public final void setDeployName(String deployName) {
		this.deployName = deployName;
	}
	
}
