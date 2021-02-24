/**
* Constant.java
* 
* @author qye.zheng
* 	version 1.0
 */
package com.hua.constant;

/**
 * 描述: 通用常量
 * @author qye.zheng
 * Constant
 */
public final class Constant {
	
    private Constant() {}
	
	/** begin 字符常量 */
	
	// 空字符
	public static final String WHITE_SPACE = "";
	
	// 空格
	public static final String BLANK_SPACE = " ";
	
	// 单引号
	public static final String SINGLE_QUOTE = "'";
	
	// 双引号
	public static final String DOUBLE_QUOTE = "\"";
	
	// 逗号
	public static final String COMMA = ",";
	
	// 分号
	public static final String SEMICOLON = ";";
	
	// 冒号
	public static final String COLON = ":";
	
	// 点号 (英文句号)
	public static final String DOT_MARK = ".";
	
	// 问号
	public static final String QUESTION_MARK = "?";
	
	// 感叹号
	public static final String EXCLAMATORY_MARK = "!";
	
	// 斜杠
	public static final String SLASH = "/";
	
	// 反斜杠
	public static final String BACK_SLASH = "\\";
	
	// 波浪线
	public static final String WAVY_LINE = "~";
	
	// 中划线
	public static final String MIDDLINE = "-";
	
	// 下划线
	public static final String UNDERLINE = "_";
	
	// 百分号
	public static final String PERCENT = "%";
	
	// 井号
	public static final String POUND_SIGN = "#";
	
	// 美元符
	public static final String DOLLAR = "$";
	
	// 与号
	public static final String AND_MARK = "&";
	
	// 或号
	public static final String OR_MARK = "|";
	
	// 星号
	public static final String ASTERISK = "*";
	
	// 等号
	public static final String EQUAL_SIGN = "=";
	
	// 加号
	public static final String PLUS_SIGN = "+";
	
	// 减号
	public static final String MINUS_SIGN = "-";
	
	// 乘号
	public static final String TIMES_SIGN = "*";
	
	// 除号
	public static final String DIVIDE_SIGN = "/";
	
	// 大于号
	public static final String GREATER_THAN_SIGN = ">";
	
	// 大于等于号
	public static final String GREATER_THAN_EQUAL_SIGN = ">=";
	
	// 小于号
	public static final String LESS_THAN_SIGN = "<";
	
	// 小于等于号
	public static final String LESS_THAN_EQUAL_SIGN = "<=";
	
	// 左小括号
	public static final String LEFT_LITTLE_BRACKET = "(";
	
	// 右小括号
	public static final String RIGHT_LITTLE_BRACKET = ")";
	
	// 左中括号
	public static final String LEFT_MIDDLE_BRACKET = "[";
	
	// 右中括号
	public static final String RIGHT_MIDDLE_BRACKET = "]";
	
	// 左大括号
	public static final String LEFT_BIG_BRACKET = "{";
	
	// 右大括号
	public static final String RIGHT_BIG_BRACKET = "}";
	
	public static final String LINE_BREAK = "\n";
	
	/** end of 字符常量 */
	
	/** begin 字符串常量 */
	

	
	/** end of 字符串常量 */
	
	/** begin 数字常量 */
	
	// -1
	public static final int NEGATIVE_ONE = -1;
	
	// 0
	public static final int ZERO = 0;
	
	// 0.0F
	public static double ZERO_FLOAT = 0.0F;
	
	// 0.0
	public static double ZERO_DOUBLE = 0.0;
	
	// 1
	public static final int ONE = 1;
	
	// 2
	public static final int TWO = 2;
	
	// 3
	public static final int THREE = 3;
	
	// 4
	public static final int FOUR = 4;
	
	// 5
	public static final int FIVE = 5;
	
	// 6
	public static final int SIX = 6;
	
	// 7
	public static final int SEVEN = 7;
	
	// 8
	public static final int EIGHT = 8;
	
	// 9
	public static final int NINE = 9;
	
	// 10
	public static final int TEN = 10;
	
	// 16进制 数字 - 数组
	public static final String[] HEXDIGITS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",  "A", "B", "C", "D", "E", "F" };
	
	// 16进制 数字 - 数组
	public static char[] HEXDIG = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',  'B', 'C', 'D', 'E', 'F' };
	 
	// 16进制 数字 - 大写数组
	public static final String[] HEXDIGITS_UPPER_CASE = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",  "A", "B", "C", "D", "E", "F" };
	
	// 16进制 数字 - 小写数组
	public static final String[] HEXDIGITS_LOWER_CASE = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",  "a", "b", "c", "d", "e", "f" };
	
	// 16进制 数字 - 大写数组
	public static char[] HEXDIG_UPPER_CASE = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',  'B', 'C', 'D', 'E', 'F' };
	
	// 16进制 数字 - 小写数组
	public static char[] HEXDIG_LOWER_CASE = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',  'b', 'c', 'd', 'e', 'f' };
	
	 public static final int OXF = 0XF;
	 
	 public static final char  CHAR_ONE = '0';
	 
	 // 点号 (英文句号)
	 public static final char  CHAR_DOT = '.';
	 
	/** end of 数字常量 */
	
	/** begin 编码常量 */
	
	public static final String CHART_SET_ISO_8859_1 = "ISO-8859-1";
	
	public static final String CHART_SET_UTF_8 = "UTF-8";
	
	public static final String CHART_SET_UTF_16 = "UTF-16";
	
	public static final String CHART_SET_UTF_16_BE = "UTF-16BE";
	
	public static final String CHART_SET_UTF_16_LE = "UTF-16LE";
	
	// US-ASCII
	public static final String CHART_SET_US_ASCII = "US-ASCII";
	
	/* GB3212 */
	public static final String CHART_SET_GB2312 = "GB2312";
	
	public static final String CHART_SET_GBK = "GBK";
	
	/** end of 编码常量 */
	
	/** begin 容量设置 */
	
	// 集合大小
	public static final int COLLECTION_SIZE = 10;
	
	// 默认字节大小 (例如 : new public static final StringBuilder 可以使用此设置)
	public static final int DEFAULT_BYTE = 1024;
	
	/** end of 容量设置 */

	public static final char  CHAR_ZERO = '0';
	
	/** begin 时间常量 */
	public static final String TIME_ZONE_ID_CHINA = "GMT+08:00";
	
	// 中时区 (格林威治)
	public static final String TIME_ZONE_ID_MEDIAL = "GMT";
	
	// 默认地区位移
	public static final String DEFAULT_ZONE_OFFSET = "+8";
	
	/** end of 时间常量 */
	
	/** begin 加密/解密 */
	public static final String EN_DECRY_MD5 = "MD5";
	
	public static final String EN_DECRY_SHA_1 = "SHA-1";
	
	public static final String EN_AES = "AES";
	
	public static final String EN_SHA1PRNG = "SHA1PRNG";
	
	public static final String EN_HMAC = "HmacMD5";
	
	public static final String EN_DES = "DES";
	
	/** end of 加密/解密 */	
	
	/** begin 日期格式 */
	
	/* 日期格式 :  yyyy-MM-dd */
	public static final String DATE_FORMAT_yyyy_MM_dd = "yyyy-MM-dd";

	/* 日期格式 :  yyyyMMdd */
	public static final String DATE_FORMAT_yyyyMMdd = "yyyyMMdd";
	
	/* 时间格式 :  HH:mm:ss */
	public static final String TIME_FORMAT_HH_mm_ss = "HH:mm:ss";
	
	/* 时间格式 :  HHmmss */
	public static final String TIME_FORMAT_HHmmss = "HHmmss";
	
	/* 日期时间格式 : yyyy-MM-dd HH:mm:ss */
	public static final String DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss= "yyyy-MM-dd HH:mm:ss";
	
	/* 日期时间格式 :  yyyyMMddHHmmss */
	public static final String DATE_TIME_FORMAT_yyyyMMddHHmmss = "yyyyMMddHHmmss";
	
	/* 时间戳(精确到毫秒)格式: yyyy-MM-dd HH:mm:ss.SSS */
	public static final String TIMESTAMP_FORMAT_yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	
	/* 时间戳(精确到毫秒)格式 :  yyyyMMddHHmmssSSS */
	public static final String TIMESTAMP_FORMAT_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
	
	/** end of 日期格式 */
	
	  public static final String TIME_ZONE = "GMT+8";
}
