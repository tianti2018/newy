package com.zklc.weishangcheng.member.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.zklc.framework.action.BaseAction;
import com.zklc.framework.util.io.FileUtil;
import com.zklc.weishangcheng.member.service.UsersService;


@SuppressWarnings("all")
@ParentPackage("json")
@Namespace("/file")
@Action(value="fileUpload")
public class UploadAction extends BaseAction {
	
	@Autowired
	private UsersService userService;
	private static final long serialVersionUID = 572146812454l;  
	private List<File> filedata; // 默认的客户端文件对象,命名不符合java规范fileData
	private List<String> filedataFileName; // 客户端文件名
	private List<String> fileContentType; // 客户端文件名类型
	private String allowType = "jpg;png";//定义该Action允许上传的文件类型 
	private String allowType_video = "mp4;swf";//定义该Action允许上传的文件类型 

	/**
	 * 附件上传
	 * @throws IOException 
	 */
	public void upload() throws IOException{
		JSONObject json = new JSONObject();
		json.put("success", false);
		try{
			if (filedata == null || filedata.size() == 0) {
				json.put("success", false);
			}else{
				//获取配置的上传目录
				for (int i = 0; i < filedata.size(); ++i) {
					String fileName = filedataFileName.get(i); // 文件真名
					String fileSize = String.valueOf(filedata.get(i).length()/1024); // 文件的真实大小 KB
					// 将上传的文件保存到服务器的硬盘上
					InputStream is = new BufferedInputStream(new FileInputStream(filedata.get(i)));	
					String fileExtensionName = FileUtil.getExtension(fileName); //获取扩展名
					if(check(fileExtensionName)){
						String noExtensionFileName = FileUtil.trimExtension(fileName);       
						
						String fileUploadConfigPath = ServletActionContext.getServletContext().getRealPath("/upload/images/");
						long prefix = System.currentTimeMillis();
						String fileName_ = (noExtensionFileName.replaceAll("/", "")+"_"+prefix+"."+fileExtensionName);
						File tempFile = new File((returnFilePath(fileUploadConfigPath)+File.separator+fileName_).replaceAll("\r|\n|%0d|%0D|%0a|%0A", ""));//文件存放路径
						logger.info("path:"+tempFile.getPath());
						FileUtils.forceMkdir(tempFile.getParentFile()); // 创建上传文件所在的父目录
						OutputStream os = new BufferedOutputStream( new FileOutputStream(tempFile));
						int len = 0;
						byte[] buffer = new byte[500];
						while (-1 != (len = is.read(buffer))){
							os.write(buffer, 0, len);				
						}					
			            json.put("success", true);
			            json.put("path","/upload/images/"+fileName_);
			            ServletActionContext.getResponse().getWriter().print(json.toString());
						is.close();
						os.flush();
						os.close(); 
					}
				}
			}
			
		}catch(Exception e){
		}
	}
	
	public void videoUpload() throws IOException{
		JSONObject json = new JSONObject();
		json.put("success", false);
		try{
			if (filedata == null || filedata.size() == 0) {
				json.put("success", false);
			}else{
				//获取配置的上传目录
				for (int i = 0; i < filedata.size(); ++i) {
					String fileName = filedataFileName.get(i); // 文件真名
					String fileSize = String.valueOf(filedata.get(i).length()/1024); // 文件的真实大小 KB
					// 将上传的文件保存到服务器的硬盘上
					InputStream is = new BufferedInputStream(new FileInputStream(filedata.get(i)));	
					String fileExtensionName = FileUtil.getExtension(fileName); //获取扩展名
					if(checkVideo(fileExtensionName)){
						String noExtensionFileName = FileUtil.trimExtension(fileName);       
						String fileUploadConfigPath = ServletActionContext.getServletContext().getRealPath("/upload/video/");
						long prefix = System.currentTimeMillis();
						String fileName_ = (noExtensionFileName.replaceAll("/", "")+"_"+prefix+"."+fileExtensionName);
						File tempFile = new File((returnFilePath(fileUploadConfigPath)+File.separator+fileName_).replaceAll("\r|\n|%0d|%0D|%0a|%0A", ""));//文件存放路径
						logger.info("path:"+tempFile.getPath());
						FileUtils.forceMkdir(tempFile.getParentFile()); // 创建上传文件所在的父目录
						OutputStream os = new BufferedOutputStream( new FileOutputStream(tempFile));
						int len = 0;
						byte[] buffer = new byte[500];
						while (-1 != (len = is.read(buffer))){
							os.write(buffer, 0, len);				
						}					
			            json.put("success", true);
			            json.put("path","/upload/video/"+fileName_);
			            ServletActionContext.getResponse().getWriter().print(json.toString());
						is.close();
						os.flush();
						os.close(); 
					}
				}
			}
			
		}catch(Exception e){
		}
	}
	
	public void voiceUpload() throws IOException{
		JSONObject json = new JSONObject();
		json.put("success", false);
		try{
			if (filedata == null || filedata.size() == 0) {
				json.put("success", false);
			}else{
				//获取配置的上传目录
				for (int i = 0; i < filedata.size(); ++i) {
					String fileName = filedataFileName.get(i); // 文件真名
					String fileSize = String.valueOf(filedata.get(i).length()/1024); // 文件的真实大小 KB
					// 将上传的文件保存到服务器的硬盘上
					InputStream is = new BufferedInputStream(new FileInputStream(filedata.get(i)));	
					String fileExtensionName = FileUtil.getExtension(fileName); //获取扩展名
					if(checkVideo(fileExtensionName)){
						String noExtensionFileName = FileUtil.trimExtension(fileName);       
						String fileUploadConfigPath = ServletActionContext.getServletContext().getRealPath("/upload/voice/");
						long prefix = System.currentTimeMillis();
						String fileName_ = (noExtensionFileName.replaceAll("/", "")+"_"+prefix+"."+fileExtensionName);
						File tempFile = new File((returnFilePath(fileUploadConfigPath)+File.separator+fileName_).replaceAll("\r|\n|%0d|%0D|%0a|%0A", ""));//文件存放路径
						logger.info("path:"+tempFile.getPath());
						FileUtils.forceMkdir(tempFile.getParentFile()); // 创建上传文件所在的父目录
						OutputStream os = new BufferedOutputStream( new FileOutputStream(tempFile));
						int len = 0;
						byte[] buffer = new byte[500];
						while (-1 != (len = is.read(buffer))){
							os.write(buffer, 0, len);				
						}					
			            json.put("success", true);
			            json.put("path","/upload/voice/"+fileName_);
			            ServletActionContext.getResponse().getWriter().print(json.toString());
						is.close();
						os.flush();
						os.close(); 
					}
				}
			}
			
		}catch(Exception e){
		}
	}
	
	public boolean check(String type){ 
        String[] types = allowType.split(";"); 
        for(String s:types){ 
            if(s.toLowerCase().equals(type.toLowerCase())){ 
                return true; 
            } 
        } 
        return false; 
          
    }
	
	public boolean checkVideo(String type){
//		String[] types = allowType_video.split(";"); 
//        for(String s:types){ 
//            if(s.toLowerCase().equals(type.toLowerCase())){ 
//                return true; 
//            } 
//        } 
//        return false;
		return true;
	}
	
	private String returnFilePath(String path){
		return path;
	}

	public List<File> getFiledata() {
		return filedata;
	}

	public void setFiledata(List<File> filedata) {
		this.filedata = filedata;
	}

	public List<String> getFiledataFileName() {
		return filedataFileName;
	}

	public void setFiledataFileName(List<String> filedataFileName) {
		this.filedataFileName = filedataFileName;
	}

	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getAllowType() {
		return allowType;
	}

	public void setAllowType(String allowType) {
		this.allowType = allowType;
	}
	
}
