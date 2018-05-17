package com.zklc.weishangcheng.member.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;

import com.zklc.framework.action.BaseAction;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.service.UserService;

@ParentPackage("json")
@Namespace("/file")
@Action(value = "fileUploadAction")
public class FileUploadAction extends BaseAction {
	
	@Autowired
	private UserService userService;
	
	private static final long serialVersionUID = 572146812454l;  
	private List<File> file1;    
	private List<String> file1ContentType;  
	private List<String> file1FileName;    //文件名  
	
	public String uploadFile() throws IOException {  
		Users user = (Users) request.getSession().getAttribute("loginUser");
		user = userService.findById(user.getUserId());
		for(int i = 0; i < file1.size(); i++ ) {	
			try { 
	            ClientGlobal.init(this.getClass().getResource("/").getPath()+"/fdfs_client.conf");
	 
	            TrackerClient tracker = new TrackerClient(); 
	            TrackerServer trackerServer = tracker.getConnection(); 
	            StorageServer storageServer = null;
	 
	            StorageClient storageClient = new StorageClient(trackerServer, storageServer); 
//	            NameValuePair nvp [] = new NameValuePair[]{ 
//	            		new NameValuePair("width", "750"),
//	            	    new NameValuePair("heigth", "1850"),
//	            	    new NameValuePair("author", "bai")
//	            }; 
	            byte[] buff = getBytes(file1.get(i));
	            if(buff!=null){
	            	String fileIds[] = storageClient.upload_file(buff, "jpg", null);
		            System.out.println(fileIds.length); 
		            System.out.println("组名：" + fileIds[0]); 
		            System.out.println("路径: " + fileIds[1]);
//		            user.setEwmPictureUrl(fileIds[0]+"/"+fileIds[1]);
	            }
	 
	        } catch (FileNotFoundException e) { 
	            e.printStackTrace(); 
	        } catch (IOException e) { 
	            e.printStackTrace(); 
	        } catch (MyException e) { 
	            e.printStackTrace(); 
	        }
		}
		userService.update(user);
		request.getSession().setAttribute("loginUser", user);
	    
	   return "phoneFamily";
	 }
	
	public void uploadFile1() throws IOException {  
		JSONObject json = new JSONObject();
		json.put("success", false);
		for(int i = 0; i < file1.size(); i++ ) {	
			try { 
	            ClientGlobal.init(this.getClass().getResource("/").getPath()+"/fdfs_client.conf");
	 
	            TrackerClient tracker = new TrackerClient(); 
	            TrackerServer trackerServer = tracker.getConnection(); 
	            StorageServer storageServer = null;
	 
	            StorageClient storageClient = new StorageClient(trackerServer, storageServer); 
//	            NameValuePair nvp [] = new NameValuePair[]{ 
//	            		new NameValuePair("width", "750"),
//	            	    new NameValuePair("heigth", "1850"),
//	            	    new NameValuePair("author", "bai")
//	            }; 
	            byte[] buff = getBytes(file1.get(i));
	            if(buff!=null){
	            	String fileIds[] = storageClient.upload_file(buff, "jpg", null);
		            System.out.println(fileIds.length); 
		            System.out.println("组名：" + fileIds[0]); 
		            System.out.println("路径: " + fileIds[1]);
		            json.put("success", true);
		            json.put("path", fileIds[0]+"/"+fileIds[1]);
		            ServletActionContext.getResponse().getWriter().print(json.toString());
	            }
	 
	        } catch (FileNotFoundException e) { 
	            e.printStackTrace(); 
	        } catch (IOException e) { 
	            e.printStackTrace(); 
	        } catch (MyException e) { 
	            e.printStackTrace(); 
	        }
		}
	    
	 }
	
	//获得指定文件的byte数组 
    public static byte[] getBytes(File file){  
        byte[] buffer = null;  
        try {  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer;  
    }

	public List<File> getFile1() {
		return file1;
	}

	public void setFile1(List<File> file1) {
		this.file1 = file1;
	}

	public List<String> getFile1ContentType() {
		return file1ContentType;
	}

	public void setFile1ContentType(List<String> file1ContentType) {
		this.file1ContentType = file1ContentType;
	}

	public List<String> getFile1FileName() {
		return file1FileName;
	}

	public void setFile1FileName(List<String> file1FileName) {
		this.file1FileName = file1FileName;
	}

}
