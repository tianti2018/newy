package com.zklc.framework.exception;


import java.io.PrintStream;
import java.io.PrintWriter;



/**
 * 运行时异常处理基类
 * @author qf
 */
public class AppRunTimeException extends RuntimeException {
	
	private static final long serialVersionUID = -6462192943975934000L;
	private ExceptionHelper exHelper=new ExceptionHelper();
	public ExceptionHelper getExHelper() {
		return exHelper;
	}

	public void setExHelper(ExceptionHelper exHelper) {
		this.exHelper = exHelper;
	}

	private Throwable cause;
	private String content = "";
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public AppRunTimeException(){
		super();
	}
	
	public AppRunTimeException(String msgcode){
		super(msgcode);
		this.content = this.exHelper.getExMessage(msgcode);
	}
	
	public AppRunTimeException(String msgcode,Throwable cause){
		super(msgcode,cause);
		setCause(cause);
	    this.content = this.exHelper.getExMessage(msgcode);
	    //logger.error(content, cause);
	}
	
	public AppRunTimeException(Throwable cause){
		super(cause.getMessage(),cause);
		setCause(cause);
	}
    public void printStackTrace(PrintStream stream)
    {
        if(cause != null)
        {
            cause.printStackTrace(stream);
            stream.println("rethrow as exception " + getClass().getName());
        }
        super.printStackTrace(stream);
    }

    public void printStackTrace(PrintWriter writer)
    {
        if(cause != null)
        {
            cause.printStackTrace(writer);
            writer.println("rethrow as exception " + getClass().getName());
        }
        super.printStackTrace(writer);
    }
	
	public Throwable getCause() {
		return cause;
	}
	
	public void setCause(Throwable cause) {
		this.cause = cause;
	}
	
	public String getMessage() {
	    return super.getMessage();
	  }	  
}
