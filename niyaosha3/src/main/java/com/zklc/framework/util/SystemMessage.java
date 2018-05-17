package com.zklc.framework.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 读取文件名为system.properties的配置文件
 * 
 * @author YaoJuxian
 */
public class SystemMessage {
	private static final String BUNDLE_NAME = "system"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private SystemMessage() {
	}

	/**
	 * get the value from the properties file
	 * 
	 * @param key
	 *            the key in the properties file
	 * @return
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
