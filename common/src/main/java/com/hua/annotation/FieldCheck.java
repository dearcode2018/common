/**
 * FieldCheck.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * FieldCheck
 * 描述: 类的属性/方法参数 数据校验
 * 可以使用在类的属性/字段，方法的参数上
 * @author  qye.zheng
 */
@Documented
//在施加对象中是否可继承
@Inherited
@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldCheck
{

	/**
	 * 
	 * 描述:  字段/属性名称
	 * @author  qye.zheng
	 * @return
	 */
	String name() default "";
	
	/**
	 * 
	 * 描述: 是否可以为空
	 * 字符串类型应该单独验证
	 * @author  qye.zheng
	 * @return
	 */
	boolean nullable() default true;
	
	/**
	 * 
	 * 描述: 类型 - 备用字段(可以区分多个业务共用一个实体)
	 * @author  qye.zheng
	 * @return
	 */
	String type() default "0";
	
}
