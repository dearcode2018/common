/**
  * @filename WebResource.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.base;

 /**
 * @type WebResource
 * @description  
 * @author qye.zheng
 */
public final class WebResource extends BaseModule {
	
	public static final String PROPERTY_DEPLOY_KEY = "deploy-path";
	
	public static final String PROPERTY_SOURCE_KEY = "source-path";
	
	private String deployPath;
	
	private String sourcePath;
	
	/**
	 * @description 构造方法
	 * @author qye.zheng
	 */
	public WebResource() {
		super("wb-resource");
	}

	/**
	 * @return the deployPath
	 */
	public final String getDeployPath() {
		return deployPath;
	}

	/**
	 * @param deployPath the deployPath to set
	 */
	public final void setDeployPath(String deployPath) {
		this.deployPath = deployPath;
	}

	/**
	 * @return the sourcePath
	 */
	public final String getSourcePath() {
		return sourcePath;
	}

	/**
	 * @param sourcePath the sourcePath to set
	 */
	public final void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
}
