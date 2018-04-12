/**
  * @filename WebProjectFilter.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.base;

import java.io.File;
import java.io.FileFilter;

import com.hua.util.ProjectUtil;

 /**
 * @type WebProjectFilter
 * @description  
 * @author qye.zheng
 */
public final class WebProjectFilter implements FileFilter {

	/**
	 * @description 
	 * @param file
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public boolean accept(File file) {
		
		return ProjectUtil.isWeb(file);
	}

}
