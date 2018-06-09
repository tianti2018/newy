package com.tw.web.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class SystemMessage {
	private static final String BUNDLE_NAME = "system";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	public static final String NGINX_URL = getString("nginx");//换成相应路径
	
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
		}
		return '!' + key + '!';
	}
}