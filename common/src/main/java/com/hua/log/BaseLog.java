/**
* BaseLog.java
* 
* @author qye.zheng
* 	version 1.0
 */
package com.hua.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 描述: 
 * @author qye.zheng
 * BaseLog
 */
public class BaseLog {
	
	protected final Logger log = LogManager.getLogger(this.getClass().getName());
	

}
