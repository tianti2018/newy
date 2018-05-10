package com.tw.web.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class SystemConfigUtil {
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("url");
	
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
		return '!' + key + '!';
	}
}