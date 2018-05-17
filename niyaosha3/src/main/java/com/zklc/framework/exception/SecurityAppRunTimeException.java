package com.zklc.framework.exception;

/**
 * 运行时异常处理基类
 * @author qf
 */
public class SecurityAppRunTimeException extends AppRunTimeException {

	private static final long serialVersionUID = 7433157888218520065L;

	public SecurityAppRunTimeException(){
		super();
	}
	
	public SecurityAppRunTimeException(String msgcode){
		super(msgcode);
	}
	
	public SecurityAppRunTimeException(String msgcode,Throwable cause){
		super(msgcode,cause);
		setCause(cause);
	}
	
	public SecurityAppRunTimeException(Throwable cause){
		super(cause);
		setCause(cause);
	}
}
