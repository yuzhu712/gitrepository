package com.lc.sofa.core.framework.basis.log;

import java.lang.reflect.Method;

import com.lc.sofa.core.framework.basis.log.SofaLogger;

import com.lc.sofa.container.util.osgi.OsgiServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 平台日志工厂服务抽象类
 * 
 * @author YZ
 * @version 1.0, 2013-10-21
 * @since 1.0, 2013-10-21
 */
public abstract class SofaLoggerFactory {

	/**
	 * 实例化 日志工厂服务实例,如果获取不到日志工厂服务，就返回平台核心框架默认的日志记录器
	 * 这样方便日志扩展组件的顺利接入。(在osgi运行环境下可以使用)
	 * 
	 * @return
	 */
	public static SofaLogger getSofaLog(Class clazz) {

		SofaLoggerFactory loggerFactory = OsgiServiceUtil.getOsgiService(SofaLoggerFactory.class);
		if (loggerFactory == null) {
			return getDefaultSofaLogger(clazz);
		}
		return loggerFactory.getOtherSofaLog(clazz);
	}

	/**
	 * 获取默认实现的日志器实例,在非web环境也可以使用
	 * 
	 * @param clazz
	 * @return
	 */
	public static SofaLogger getDefaultSofaLogger(Class clazz) {

		return new DefaultSofaLogger(clazz);

	}

	/**
	 * 获取平台日志服务接口
	 * 
	 * @return
	 */
	public abstract SofaLogger getOtherSofaLog(Class clazz);

	/**
	 * 平台核心框架日志类的默认实现
	 * 
	 * @author Administrator
	 * @version 1.0, 2013-10-23
	 * @since 1.0, 2013-10-23
	 */
	static class DefaultSofaLogger implements SofaLogger {

		private Logger logger;

		DefaultSofaLogger(Class clazz) {

			logger = LoggerFactory.getLogger(clazz);
		}

		public void debug(String msg) {

			logger.debug(msg);

		}

		public void debug(String format, Object... arguments) {

			logger.debug(format, arguments);

		}

		public void info(String msg) {

			logger.info(msg);

		}

		public void info(String format, Object... arguments) {

			logger.info(format, arguments);

		}

		public void warn(String msg) {

			logger.warn(msg);

		}

		public void warn(String format, Object... arguments) {

			logger.warn(format, arguments);

		}

		public void error(String msg) {

			logger.error(msg);

		}

		public void error(String format, Object... arguments) {

			logger.error(format, arguments);

		}

		public void error(String msg, Throwable throwable) {

			logger.error(msg, throwable);

		}

		/**
		 * 该方法由日志组件 实现 {@inheritDoc}
		 * 
		 * @see com.lc.sofa.core.framework.basis.log.SofaLogger#info(java.lang.Class, java.lang.reflect.Method,
		 *      java.lang.Object[])
		 *      @deprecated
		 */
		public void info(Class targetClass, Method targetMethod, Object[] arguments) {

			return;

		}

		/**
		 * 该方法由日志组件 实现 {@inheritDoc}
		 * 
		 * @see com.lc.sofa.core.framework.basis.log.SofaLogger#error(java.lang.Class, java.lang.reflect.Method,
		 *      java.lang.Object[], java.lang.Throwable)
		 *       @deprecated
		 */
		public void error(Class targetClass, Method targetMethod, Object[] arguments, Throwable throwable) {

			return;

		}

	}

}
