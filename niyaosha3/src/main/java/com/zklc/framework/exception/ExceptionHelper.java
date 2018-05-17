package com.zklc.framework.exception;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import org.springframework.core.io.ClassPathResource;
public class ExceptionHelper {	
	public static String nullPointer_Exception="NullPointerException";
	public static String unsupportedOperation_Exception="UnsupportedOperationException";
	public static String stringIndexOutOfBounds_Exception="StringIndexOutOfBoundsException";
	public static String arithmetic_Exception= "ArithmeticException";
	public static String illegalArgument_Exception="IllegalArgumentException";
	public static String arrayIndexOutOfBounds_Exception="ArrayIndexOutOfBoundsException";
	public static String classCast_Exception="ClassCastException";
	public static String sQL_Exception="SQLException";
	public static String hibernate_Exception="HibernateException";
	public static Map confMap;
	
	public void init()
    {
		try {
			Properties prop = new Properties(); 
		    prop.load(new ClassPathResource("exception.properties").getInputStream());
		    confMap=prop;	    
		} catch (IOException e) {	
			e.printStackTrace();			
		}
    }
	
	public static String getExMessage(String excode) {
		return "异常代码:"+excode+"    "+(String)confMap.get(excode);
	}
	public static void main(String[] args) throws IOException {
		getExMessage("00000001");
	}
}
