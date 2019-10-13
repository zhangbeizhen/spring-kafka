package com.zbz.utils;

import org.springframework.web.context.ContextLoader;

/**
 * 
 * @ClassName: CommonUtils
 */
public class CommonUtils {

	/**
	 * 获取spring容器中的bean对象
	 */
	public static Object getBean(String beanName) {
		return ContextLoader.getCurrentWebApplicationContext().getBean(beanName);
	}
}
