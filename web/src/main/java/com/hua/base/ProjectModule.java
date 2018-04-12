/**
  * @filename ProjectModule.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.base;

 /**
 * @type ProjectModule
 * @description  
 * @author qye.zheng
 */
public final class ProjectModule extends BaseModule {

	public static final String PROPERTY_KEY = "project-version";
	
	private String id;
	
	private String projectVersion = "1.5.0";
	
	private WebModule webModule;
	
	
	/**
	 * @description 构造方法
	 * @author qye.zheng
	 */
	public ProjectModule() {
		super("project-modules");
	}

	/**
	 * @return the id
	 */
	public final String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the projectVersion
	 */
	public final String getProjectVersion() {
		return projectVersion;
	}

	/**
	 * @param projectVersion the projectVersion to set
	 */
	public final void setProjectVersion(String projectVersion) {
		this.projectVersion = projectVersion;
	}

	/**
	 * @return the webModule
	 */
	public final WebModule getWebModule() {
		return webModule;
	}

	/**
	 * @param webModule the webModule to set
	 */
	public final void setWebModule(WebModule webModule) {
		this.webModule = webModule;
	}
	
}
