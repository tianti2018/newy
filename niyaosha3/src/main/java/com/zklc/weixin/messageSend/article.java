package com.zklc.weixin.messageSend;

/**
 * 
 * <p>
 * 功能
 * </p>
 * <p>
 * Copyright 北京中科联诚软件有限公司 2014 All right reserved.
 * </p>
 * 
 * @author jazz 时间 2014-3-1 下午11:32:12
 * @version 1.0 </br> 最后修改人 无
 */
public class article {
    // 图文消息名称
    private String title;
    // 图文消息描述
    private String description;
    // 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致
    private String picurl;
    // 点击图文消息跳转链接
    private String Url;
    
    public String getTitle(){
    
        return title;
    }
    
    public void setTitle(String title){
    
        this.title = title;
    }
    
    public String getDescription(){
    
        return description;
    }
    
    public void setDescription(String description){
    
        this.description = description;
    }
    
    public String getPicurl(){
    
        return picurl;
    }
    
    public void setPicurl(String picurl){
    
        this.picurl = picurl;
    }
    
    public String getUrl(){
    
        return Url;
    }
    
    public void setUrl(String url){
    
        Url = url;
    }


}
