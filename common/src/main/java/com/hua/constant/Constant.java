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
public interface Constant {
	
	
	/** begin 字符常量 */
	
	// 空字符
	String WHITE_SPACE = "";
	
	// 空格
	String BLANK_SPACE = " ";
	
	// 单引号
	String SINGLE_QUOTE = "'";
	
	// 双引号
	String DOUBLE_QUOTE = "\"";
	
	// 逗号
	String COMMA = ",";
	
	// 分号
	String SEMICOLON = ";";
	
	// 冒号
	String COLON = ":";
	
	// 点号 (英文句号)
	String DOT_MARK = ".";
	
	// 问号
	String QUESTION_MARK = "?";
	
	// 感叹号
	String EXCLAMATORY_MARK = "!";
	
	// 斜杠
	String SLASH = "/";
	
	// 反斜杠
	String BACK_SLASH = "\\";
	
	// 波浪线
	String WAVY_LINE = "~";
	
	// 中划线
	String MIDDLINE = "-";
	
	// 下划线
	String UNDERLINE = "_";
	
	// 百分号
	String PERCENT = "%";
	
	// 井号
	String POUND_SIGN = "#";
	
	// 美元符
	String DOLLAR = "$";
	
	// 与号
	String AND_MARK = "&";
	
	// 或号
	String OR_MARK = "|";
	
	// 星号
	String ASTERISK = "*";
	
	// 等号
	String EQUAL_SIGN = "=";
	
	// 加号
	String PLUS_SIGN = "+";
	
	// 减号
	String MINUS_SIGN = "-";
	
	// 乘号
	String TIMES_SIGN = "*";
	
	// 除号
	String DIVIDE_SIGN = "/";
	
	// 大于号
	String GREATER_THAN_SIGN = ">";
	
	// 大于等于号
	String GREATER_THAN_EQUAL_SIGN = ">=";
	
	// 小于号
	String LESS_THAN_SIGN = "<";
	
	// 小于等于号
	String LESS_THAN_EQUAL_SIGN = "<=";
	
	// 左小括号
	String LEFT_LITTLE_BRACKET = "(";
	
	// 右小括号
	String RIGHT_LITTLE_BRACKET = ")";
	
	// 左中括号
	String LEFT_MIDDLE_BRACKET = "[";
	
	// 右中括号
	String RIGHT_MIDDLE_BRACKET = "]";
	
	// 左大括号
	String LEFT_BIG_BRACKET = "{";
	
	// 右大括号
	String RIGHT_BIG_BRACKET = "}";
	
	String LINE_BREAK = "\n";
	
	/** end of 字符常量 */
	
	/** begin 字符串常量 */
	

	
	/** end of 字符串常量 */
	
	/** begin 数字常量 */
	
	// -1
	int NEGATIVE_ONE = -1;
	
	// 0
	int ZERO = 0;
	
	// 0.0F
	double ZERO_FLOAT = 0.0F;
	
	// 0.0
	double ZERO_DOUBLE = 0.0;
	
	// 1
	int ONE = 1;
	
	// 2
	int TWO = 2;
	
	// 3
	int THREE = 3;
	
	// 4
	int FOUR = 4;
	
	// 5
	int FIVE = 5;
	
	// 6
	int SIX = 6;
	
	// 7
	int SEVEN = 7;
	
	// 8
	int EIGHT = 8;
	
	// 9
	int NINE = 9;
	
	// 10
	int TEN = 10;
	
	// 16进制 数字 - 数组
	String[] HEXDIGITS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",  "A", "B", "C", "D", "E", "F" };
	
	// 16进制 数字 - 数组
	 char[] HEXDIG = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',  'B', 'C', 'D', 'E', 'F' };
	 
	// 16进制 数字 - 大写数组
	String[] HEXDIGITS_UPPER_CASE = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",  "A", "B", "C", "D", "E", "F" };
	
	// 16进制 数字 - 小写数组
	String[] HEXDIGITS_LOWER_CASE = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",  "a", "b", "c", "d", "e", "f" };
	
	// 16进制 数字 - 大写数组
	 char[] HEXDIG_UPPER_CASE = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',  'B', 'C', 'D', 'E', 'F' };
	
	// 16进制 数字 - 小写数组
	 char[] HEXDIG_LOWER_CASE = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',  'b', 'c', 'd', 'e', 'f' };
	
	 int OXF = 0XF;
	 
	 char CHAR_ONE = '0';
	 
	 // 点号 (英文句号)
	 char CHAR_DOT = '.';
	 
	/** end of 数字常量 */
	
	/** begin 编码常量 */
	
	String CHART_SET_ISO_8859_1 = "ISO-8859-1";
	
	String CHART_SET_UTF_8 = "UTF-8";
	
	String CHART_SET_UTF_16 = "UTF-16";
	
	String CHART_SET_UTF_16_BE = "UTF-16BE";
	
	String CHART_SET_UTF_16_LE = "UTF-16LE";
	
	// US-ASCII
	String CHART_SET_US_ASCII = "US-ASCII";
	
	/* GB3212 */
	String CHART_SET_GB2312 = "GB2312";
	
	String CHART_SET_GBK = "GBK";
	
	/** end of 编码常量 */
	
	/** begin 容量设置 */
	
	// 集合大小
	int COLLECTION_SIZE = 10;
	
	// 默认字节大小 (例如 : new StringBuilder 可以使用此设置)
	int DEFAULT_BYTE = 1024;
	
	/** end of 容量设置 */

	char CHAR_ZERO = '0';
	
	/** begin 时间常量 */
	String TIME_ZONE_ID_CHINA = "GMT+08:00";
	
	// 中时区 (格林威治)
	String TIME_ZONE_ID_MEDIAL = "GMT";
	
	/** end of 时间常量 */
	
	/** begin 加密/解密 */
	String EN_DECRY_MD5 = "MD5";
	
	String EN_DECRY_SHA_1 = "SHA-1";
	
	String EN_AES = "AES";
	
	String EN_SHA1PRNG = "SHA1PRNG";
	
	String EN_HMAC = "HmacMD5";
	
	String EN_DES = "DES";
	
	/** end of 加密/解密 */	
	
	/** begin 日期格式 */
	
	/* 日期格式 :  yyyy-MM-dd */
	String DATE_FORMAT_yyyy_MM_dd = "yyyy-MM-dd";

	/* 日期格式 :  yyyyMMdd */
	String DATE_FORMAT_yyyyMMdd = "yyyyMMdd";
	
	/* 时间格式 :  HH:mm:ss */
	String TIME_FORMAT_HH_mm_ss = "HH:mm:ss";
	
	/* 时间格式 :  HHmmss */
	String TIME_FORMAT_HHmmss = "HHmmss";
	
	/* 日期时间格式 : yyyy-MM-dd HH:mm:ss */
	String DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss= "yyyy-MM-dd HH:mm:ss";
	
	/* 日期时间格式 :  yyyyMMddHHmmss */
	String DATE_TIME_FORMAT_yyyyMMddHHmmss = "yyyyMMddHHmmss";
	
	/* 时间戳(精确到毫秒)格式: yyyy-MM-dd HH:mm:ss.SSS */
	String TIMESTAMP_FORMAT_yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	
	/* 时间戳(精确到毫秒)格式 :  yyyyMMddHHmmssSSS */
	String TIMESTAMP_FORMAT_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
	
	/** end of 日期格式 */
	
}
