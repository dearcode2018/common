/**
  * @filename YesNo.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.constant;

import java.util.Arrays;
import java.util.List;

/**
 * @type YesNo
 * @description 是否状态: yest/no
 * @author qianye.zheng
 */
public enum YesNo {
    
    YES("Y", Arrays.asList("yes", "是", "1", "true", "open")),
    NO("N", Arrays.asList("no", "否", "0", "false", "close"));
    
    private String code;
    
    /* 别名 */
    private List<String> aliases;

    /**
     * @description 构造方法
     * @param code
     * @param alias
     * @author qianye.zheng
     */
    private YesNo(String code, final List<String> aliases) {
        this.code = code;
        this.aliases = aliases;
    }

    /**
    * @return the code
    */
    public final String getCode() {
        return code;
    }
    
    /**
     * 
     * @description 是否是 是
     * @param code
     * @return
     * @author qianye.zheng
     */
    public static final boolean isYes(final String code) {
        return YES.code.equalsIgnoreCase(code) || YES.aliases.contains(null != code ? code.toLowerCase() : null);
    }
    
    /**
     * 
     * @description 是否是 否
     * @param code
     * @return
     * @author qianye.zheng
     */
    public static final boolean isNo(final String code) {
        return NO.code.equalsIgnoreCase(code) || NO.aliases.contains(null != code ? code.toLowerCase() : null);
    }
    
    /**
     * 
     * @description 是否是 是
     * @param code
     * @return
     * @author qianye.zheng
     */
    public static final boolean isYes(final char code) {
        return isYes(String.valueOf(code));
    }
    
    /**
     * 
     * @description 是否是 否
     * @param code
     * @return
     * @author qianye.zheng
     */
    public static final boolean isNo(final char code) {
        return isNo(String.valueOf(code));
    }
    
    /**
     * 
     * @description 
     * @param value
     * @return
     * @author qianye.zheng
     */
    public static final boolean isYes(final int value) {
        return 1 == value;
    }
    
    /**
     * 
     * @description 
     * @param value
     * @return
     * @author qianye.zheng
     */
    public static final boolean isNo(final int value) {
        return 0 == value;
    }
    
}
