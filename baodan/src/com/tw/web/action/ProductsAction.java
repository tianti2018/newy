package com.tw.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.Namespace;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionChainResult;
import com.tw.web.dao.ProductsDAO;
import com.tw.web.hibernate.persistent.AdminUser;
import com.tw.web.hibernate.persistent.Products;
import com.tw.web.util.SystemConfigUtil;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
@ParentPackage("app-default")
@Namespace("")
@Results(
		{
			@Result(name="initAdd", 			value="/WEB-INF/jsp/products/products_edit.jsp"),
			@Result(name="initModify", 			value="/WEB-INF/jsp/products/products_edit.jsp"),
			@Result(name="product", 			value="/WEB-INF/jsp/products/product.jsp"),
			@Result(name="listAll", 			value="/WEB-INF/jsp/products/productsList.jsp"),
			@Result(name="goBackList",  		params = {"method", "listAll"},
				type=ActionChainResult.class,	value="products" )
		}
)
public class ProductsAction extends ExtJSONActionSuport {
	
	@Autowired
	private ProductsDAO productsDAO;
	
	private List<File> headFile;
	
	private List<String> headFileFileName;
	
	private Products product;
	private Integer productId;
	private String transFee;
	
    public String product(){
    	HttpServletRequest request = ServletActionContext.getRequest();
    	Products product=(Products) productsDAO.findById(productId);
		List<Products> prodList=productsDAO.findEntityByPropertiName("prodType", product.getProdType());
		request.setAttribute("prod", product);
		request.setAttribute("typelist", prodList);
		request.setAttribute("typeqty", prodList.size());
    	return "product";
    }
    
    public String shangJia(){
    	HttpServletRequest request = ServletActionContext.getRequest();
    	if(productId != null)
    		product = (Products) productsDAO.findById(productId);
    	if(product == null){
    		request.setAttribute("message", "此商品不存在");
    	}else {
			product.setStatus(1);
			productsDAO.saveOrUpdate(product);
			request.setAttribute("message", "上架成功");
		}
    	return "goBackList";
    }
    
    public String xiaJia(){
    	HttpServletRequest request = ServletActionContext.getRequest();
    	if(productId != null)
    		product = (Products) productsDAO.findById(productId);
    	if(product == null){
    		request.setAttribute("message", "此商品不存在");
    	}else {
			product.setStatus(0);
			productsDAO.saveOrUpdate(product);
			request.setAttribute("message", "下架成功");
		}
    	return "goBackList";
    }
    
	public String initAdd () throws Exception {
		return "initAdd";
	}
	
	@SuppressWarnings("unchecked")
	public String listAll(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer count = productsDAO.cout_size_Commen(null, null);		
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count));
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
		List<Products> litPager =  productsDAO.findAllPagerList(null,null, null, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		
		request.setAttribute("litPager", litPager);
		return "listAll";
	}
	
	public String initModify () throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if(productId != null)
			product = (Products)productsDAO.findById(productId);
		if (null == product) { 
			request.setAttribute("message", "此商品不存在");
			return "goBackList";
		}
		return "initModify";
	}
	
	public String upLoadHeadUrl() throws Exception {
		ServletContext application = ServletActionContext.getServletContext();
		HttpServletResponse response = ServletActionContext.getResponse();
    	//建根目录
//        String savePath = application.getRealPath("")+"/images/shopImgs/productsimgs/";// 保存路径
		String savePath =SystemConfigUtil.getString("prudoct_upload");
//        String saveUrl= SystemConfigUtil.getString("houtai")+ application.getContextPath()+"/images/shopImgs/productsimgs/";// 回显路径
		String saveUrl=SystemConfigUtil.getString("houtai");
        File dir = new File(savePath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File dirFile = new File(savePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        String fileName = "";
        JSONObject json = new JSONObject();
        if(headFile.size()>0){
	        fileName = System.currentTimeMillis()+getExtention(headFileFileName.get(0));
			OutputStream os = new FileOutputStream(new File(savePath, fileName));
			InputStream is = new FileInputStream(headFile.get(0));  
			byte[] buf = new byte[1024];  
			int length = 0 ; 
			while(-1 != (length = is.read(buf) ) ) {
				os.write(buf, 0, length) ; 
			}
			is.close();  
			os.close();  
			json.put("message", "true");
			json.put("oldName", headFileFileName.get(0));
			json.put("fileName", fileName);
			json.put("headUrl", saveUrl+fileName);
        }else{
        	json.put("message", "false");
        }
        response.setContentType("text/json;charset=utf-8");
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	@SuppressWarnings("deprecation")
	public void upLoadProductInfo() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ServletContext application = ServletActionContext.getServletContext();
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
//        PrintWriter out = response.getWriter();
        //文件保存目录路径
//        String savePath = application.getRealPath("")+"/images/shopImgs/productsimgs/";
        String savePath =SystemConfigUtil.getString("prudoct_upload");
//        String savePath ="C:/";
        //文件保存目录URL
//        String saveUrl=SystemConfigUtil.getString("houtai")+ application.getContextPath()+"/images/shopImgs/productsimgs/";// 回显路径
        String saveUrl=SystemConfigUtil.getString("houtai");
        File dir = new File(savePath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        //定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,mp4,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

        //最大文件大小
        long maxSize = 500*1024*1024;

        response.setContentType("text/html; charset=UTF-8");

        if(!ServletFileUpload.isMultipartContent(request)){
            out.println(getError("请选择文件。"));
            return;
        }
        //检查目录
        //检查目录
        File uploadDir = new File(savePath);
        if(!uploadDir.isDirectory()){
            out.println(getError("上传目录不存在。"));
        }
        //检查目录写权限
        if(!uploadDir.canWrite()){
            out.println(getError("上传目录没有写权限。"));
        }

        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }
        if(!extMap.containsKey(dirName)){
            out.println(getError("目录名不正确。"));
            return;
        }
        //创建文件夹
        savePath += dirName + "/";
        saveUrl += dirName + "/";
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        String ymd = sdf.format(new Date());
//        savePath += ymd + File.separator;
//        saveUrl += ymd + File.separator;
        File dirFile = new File(savePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

      MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;   
      //获得上传的文件名   
      String fileName = wrapper.getFileNames("imgFile")[0];//imgFile,imgFile,imgFile   
      //获得文件过滤器   
      File file = wrapper.getFiles("imgFile")[0];   
          
      //检查扩展名   
      String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();   
      if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){   
          out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));   
          return;   
      }   
      //检查文件大小   
      if (file.length() > maxSize) {   
              out.println(getError("上传文件大小超过限制（最大限制："+maxSize/1024/1024+"M）"));   
              return;   
      }   
          
          
      //重构上传图片的名称    
      SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");   
      String newImgName = df.format(new Date()) + "_"  
                      + new Random().nextInt(1000) + "." + fileExt;   
      byte[] buffer = new byte[1024];   
      //获取文件输出流   
      FileOutputStream fos = new FileOutputStream(savePath + newImgName);   
      //获取内存中当前文件输入流   
      InputStream in = new FileInputStream(file);   
      try {   
              int num = 0;   
              while ((num = in.read(buffer)) > 0) {   
                      fos.write(buffer, 0, num);   
              }   
              
      } catch (Exception e) {   
              e.printStackTrace(System.err);   
      } finally {   
              in.close();   
              fos.close();   
      }   
       //发送给 KE    
      JSONObject obj = new JSONObject();   
      obj.put("error", 0);   
      obj.put("url", saveUrl + newImgName);
      obj.put("fileName",fileName);
      obj.put("title",fileName);   
      obj.put("name",fileName);   
      out.println(obj.toString()); 
	}
	
	public String saveOrUpdate() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		AdminUser loginUser = (AdminUser)session.getAttribute("user");
		Date date = new Date();
		if(product!=null){
			if(product.getUserId()==null){
				product.setUserId(loginUser.getUserId());
			}
			if(product.getUserName()==null){
				product.setUserName(loginUser.getUserName());
			}
			if(product.getCreateDate()==null){
				product.setCreateDate(date);
			}
			if(product.getStatus()==null){
				product.setStatus(0);
			}
			if(product.getLevelone() == null||product.getLevelone().equals("")){
				product.setLevelone(0.0);
			}else {
				product.setLevelone(product.getLevelone());
			}
			if(product.getLeveltwo() == null||product.getLeveltwo().equals("")){
				product.setLeveltwo(0.0);
			}else {
				product.setLeveltwo(product.getLeveltwo());
			}
			if(product.getLevelthr() == null||product.getLevelthr().equals("")){
				product.setLevelthr(0.0);
			}else {
				product.setLevelthr(product.getLevelthr());
			}if(product.getLevelfor() == null||product.getLevelfor().equals("")){
				product.setLevelfor(0.0);
			}else {
				product.setLevelfor(product.getLevelthr());
			}
			if(product.getProdType()==null||product.getProdType().equals("")){
				SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss");
				Integer prodtype = Integer.parseInt(format.format(date));
				product.setProdType(prodtype);
			}
			product.setUserId(loginUser.getUserId());
			product.setUserName(loginUser.getUserName());
			product.setTransFee(Double.valueOf(transFee));
			productsDAO.saveOrUpdate(product);
		}else {
			return "listAll";
		}
		return "initModify";
	}
	
	public String delete() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if(productId != null)
			product = (Products)productsDAO.findById(productId);
		if (null == product) { 
			request.setAttribute("message", "此商品不存在");
			return "goBackList";
		}else{
			productsDAO.delete(product);
		}
		request.setAttribute("message", "删除成功");
		return "goBackList";
	}
	
	private JSONObject getError(String message) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj;
    }
	
	private static String getExtention(String fileName) {  
		int pos = fileName.lastIndexOf(".");  
		return fileName.substring(pos);  
	}

	public List<File> getHeadFile() {
		return headFile;
	}

	public void setHeadFile(List<File> headFile) {
		this.headFile = headFile;
	}

	public List<String> getHeadFileFileName() {
		return headFileFileName;
	}

	public void setHeadFileFileName(List<String> headFileFileName) {
		this.headFileFileName = headFileFileName;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getTransFee() {
		return transFee;
	}

	public void setTransFee(String transFee) {
		this.transFee = transFee;
	}

}
