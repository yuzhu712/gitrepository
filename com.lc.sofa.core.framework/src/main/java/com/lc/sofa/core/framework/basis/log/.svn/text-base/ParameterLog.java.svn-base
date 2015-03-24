package com.lc.sofa.core.framework.basis.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 日志记录数据参数的标记注解,在有多个参数的情况下，只有标记了该注解的参数才进行日志的数据参数记录
 * @author yuzhu
 * @version 1.0, 2014-2-11
 * @since 1.0, 2014-2-11
 */

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParameterLog {

	/**
	 * 参数名称
	 * @return
	 */
	String paramName() default "";
}
