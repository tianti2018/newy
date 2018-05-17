package com.zklc.framework.exception;

/**
 * 运行时异常处理基类
 * @author qf
 */
public class BusinessAppRunTimeException extends AppRunTimeException {

	private static final long serialVersionUID = 2049210383451509617L;

	public BusinessAppRunTimeException(){
		super();
	}
	
	public BusinessAppRunTimeException(String msgcode){
		super(msgcode);
	}
	
	public BusinessAppRunTimeException(String msgcode,Throwable cause){
		super(msgcode,cause);
		setCause(cause);
	}
	
	public BusinessAppRunTimeException(Throwable cause){
		super(cause);
		setCause(cause);
	}
}
