package com.zklc.framework.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;

import com.zklc.framework.exception.BusinessAppRunTimeException;
import com.zklc.framework.log.LoggerFactory;
import com.zklc.framework.log.SystemLogger;
import com.zklc.framework.service.IUploadService;


@Service
public class UploadServiceImpl implements IUploadService {
  
  public static SystemLogger logger = LoggerFactory.getSystemLogger(UploadServiceImpl.class);
  
  public String uploadFileByFile(File file, String pictureFileName) throws Exception {
    return null;
  }

  @SuppressWarnings("unused")
  public String uploadFileByXmlString(String fileName,String bpmn20Xml) {
    logger.info("获取的文件名为【"+fileName+"】获取的文件内容为【"+bpmn20Xml+"】");
    try {
      String path = this.getClass().getResource("/deployments/").getPath();
      System.out.println(">>>>>>>>> path "+path);
      logger.info("获取文件路径"+path);
      Date date = new Date();
      SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
      String titleDate = sf.format(date);
      String newFileName = path+fileName+"."+titleDate+".bpmn20.xml";
      logger.info("获取的新的文件名为"+newFileName);
      
      //FileWriter out = new FileWriter( newFileName );
      Document document = DocumentHelper.createDocument();
      document = DocumentHelper.parseText(bpmn20Xml);
        
      OutputFormat xmlFormat = new OutputFormat();
      xmlFormat.setEncoding("UTF-8");
      //创建写文件方法   
      //XMLWriter xmlWriter = new XMLWriter(out,xmlFormat);
      XMLWriter xmlWriter = new XMLWriter(new OutputStreamWriter(new FileOutputStream(newFileName),"utf-8"),xmlFormat);  

      xmlWriter.write(document);   
      xmlWriter.close();
      logger.info("上传文件"+newFileName+"成功");
      return "success";
    } 
    catch (IOException e1) {
      throw new BusinessAppRunTimeException("文档输入输出异常");
    }
    catch (DocumentException e2) {
      throw new BusinessAppRunTimeException("文档写入异常，请查看您的文档内容是否符合xml的格式");
    }
  }
  
  public static void main(String[] args) throws IOException {
    StringBuilder sb = new StringBuilder();
    
    sb.append("<definitions id=\"definitions\"");
    sb.append(" targetNamespace=\"http://activiti.org/bpmn20\"");
    sb.append(" xmlns:activiti=\"http://activiti.org/bpmn\""); 
    sb.append("  xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" ");
    sb.append(" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\"");
    sb.append(" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" ");
    sb.append(" xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" >");
    sb.append(" <process id=\"financialReport\" name=\"Monthly financial report process\">"); 
    sb.append(" <startEvent id=\"theStart\" name=\"需求申请\" />");
    sb.append("<sequenceFlow id=\"flow1\" sourceRef=\"theStart\" targetRef=\"writeReportTask\" />");
    sb.append("<userTask  id=\"writeReportTask\" name=\"需求申请2\" >");
    sb.append("<documentation>");
    sb.append("Write monthly financial report for publication to shareholders."); 
    sb.append(" </documentation>");
    sb.append(" <potentialOwner>");
    sb.append("<resourceAssignmentExpression>");
    sb.append("<formalExpression>accountancy</formalExpression>");
    sb.append(" </resourceAssignmentExpression>"); 
    sb.append("</potentialOwner>");
    sb.append(" </userTask>");
    sb.append(" <sequenceFlow id=\"flow2\" sourceRef=\"writeReportTask\" targetRef=\"verifyReportTask\" />");
    sb.append("<userTask id=\"verifyReportTask\" name=\"Verify monthly financial report\" >");
    sb.append(" <documentation>"); 
    sb.append("Verify monthly financial report composed by the accountancy department.This financial report is going to be sent to all the company shareholders.  ");
    sb.append("</documentation><potentialOwner><resourceAssignmentExpression><formalExpression>management</formalExpression></resourceAssignmentExpression></potentialOwner></userTask>");
    sb.append(" <sequenceFlow id=\"flow3\" sourceRef=\"verifyReportTask\" targetRef=\"theEnd\" /><endEvent id=\"theEnd\" /></process> </definitions>");
    System.out.println("sb "+sb.toString());
    UploadServiceImpl impl = new UploadServiceImpl();
    impl.uploadFileByXmlString("4444", sb.toString());
    
    System.out.println(">>>>>>>>>>>>>> 开始  ");
  }

}
