package com.zklc.framework.exception;

/**
 * 运行时异常处理基类
 * @author qf
 */
public class DbAppRunTimeException extends AppRunTimeException {
	
	private static final long serialVersionUID = 3447217244633725672L;

	public DbAppRunTimeException(){
		super("数据库操作异常");
	}
	
	public DbAppRunTimeException(String msgcode){
		super(msgcode);
	}
	
	public DbAppRunTimeException(String msgcode,Throwable cause){
		super(msgcode,cause);
		setCause(cause);
	}
	
	public DbAppRunTimeException(Throwable cause){
		super("数据库操作异常",cause);
		setCause(cause);
	}
}
