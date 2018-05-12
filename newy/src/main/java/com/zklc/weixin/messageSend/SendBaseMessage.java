package com.zklc.weixin.messageSend;

/**
 * ��Ϣ���ࣨ�����ʺ� -> ��ͨ�û���
 * 
 * @author liufeng
 * @date 2013-05-19
 */
public class SendBaseMessage {
    // ���շ��ʺţ��յ���OpenID��
    private String touser;
    // ��Ϣ����
    private String msgtype;
    
    public String getTouser(){
    
        return touser;
    }
    
    public void setTouser(String touser){
    
        this.touser = touser;
    }
    
    public String getMsgtype(){
    
        return msgtype;
    }
    
    public void setMsgtype(String msgtype){
    
        this.msgtype = msgtype;
    }

}
