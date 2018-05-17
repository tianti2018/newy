package com.zklc.framework.service;
import java.io.File;

public interface IUploadService {
	
  /**
   * 
   * <p>
   * 功能: 上传文件
   * </p>
   * @author Administrator 时间 2012-8-23 下午5:20:38
   * @param file
   * @param pictureFileName
   * @return
   * @throws Exception
   */
  public String uploadFileByFile (File file, String pictureFileName) throws Exception;
  
  /**
   * 
   * <p>
   * 功能
   * </p>
   * @author Administrator 时间 2012-8-23 下午5:22:03
   * @param fileName
   * @param bpmn20Xml
   * @return
   * @throws Exception
   */
  public String uploadFileByXmlString (String fileName,String bpmn20Xml) throws Exception;
}