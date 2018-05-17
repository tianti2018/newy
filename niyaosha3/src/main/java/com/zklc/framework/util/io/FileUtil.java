package com.zklc.framework.util.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 功能 文件工具类
 * </p>
 * 
 * <p>
 * Copyright 北京海辉高科软件有限公司 2012 All right reserved.
 * </p>
 * 
 * @author zhshg 时间 2012-9-13 下午6:42:04
 * @version 1.0 </br> 最后修改人 无
 * 
 */
public class FileUtil {

	/**
	 * 
	 * <p>
	 * * 功能 获取文件扩展名
	 * </p>
	 * @author zhshg 时间 2012-9-13 下午6:44:31
	 * @param f
	 * @return
	 */
	public static String getExtension(File f) {
		return (f != null) ? getExtension(f.getName()) : "";
	}
	
	/**
	 * 
	 * <p>
	 * * 功能 获取文件扩展名
	 * </p>
	 * @author zhshg 时间 2012-9-13 下午6:44:31
	 * @param f
	 * @return
	 */
	public static String getExtension(String filename) {
		return getExtension(filename, "");
	}

	/**
	 * 
	 * <p>
	 * * 功能 获取文件扩展名
	 * </p>
	 * @author zhshg 时间 2012-9-13 下午6:44:31
	 * @param f
	 * @return
	 */
	public static String getExtension(String filename, String defExt) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');
			if ((i > -1) && (i < (filename.length() - 1))) {
				return filename.substring(i + 1);
			}
		}
		return defExt;
	}

	/**
	 * 
	 * <p> 
	 * 功能 获取文件名 不包括扩展名
	 * </p>
	 * @author zhshg 时间 2012-9-13 下午6:44:06
	 * @param filename
	 * @return
	 */
	public static String trimExtension(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');
			if ((i > -1) && (i < (filename.length()))) {
				return filename.substring(0, i);
			}
		}
		return filename;
	}
	
	
	
	
    // 返回一个byte数组

    public static byte[] getBytesFromFile(File file) throws IOException {

        InputStream is = new FileInputStream(file);
        // 获取文件大小
        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // 文件太大，无法读取
        throw new IOException("File is to large "+file.getName());
        }

        // 创建一个数据来保存文件数据
        byte[] bytes = new byte[(int)length];
        // 读取数据到byte数组中
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }
        is.close();
        return bytes;
    }
}

