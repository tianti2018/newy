package com.tw.web.util;

import java.util.Date;

/**
 * ΢��ͨ�ýӿ�ƾ֤
 * 
 * @author liufeng
 * @date 2013-08-08
 */
public class AccessToken {
	// ��ȡ����ƾ֤
	private String token;
	// ƾ֤��Чʱ�䣬��λ����
	private int expiresIn;
	// ƾ֤����ʱ��
    private Date createDate;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

    
    public Date getCreateDate(){
    
        return createDate;
    }

    
    public void setCreateDate(Date createDate){
    
        this.createDate = createDate;
    }
	
}