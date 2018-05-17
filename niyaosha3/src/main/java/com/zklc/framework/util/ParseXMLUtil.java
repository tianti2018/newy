package com.zklc.framework.util;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * 
 * @author DuanSH
 * 2012-09-26
 * 解析xml文件
 *
 */
public class ParseXMLUtil {
	private String global_id;
	private String urlStr;
	private String post;
	private String preTag = null;//作用是记录解析时的上一个节点名称 
	private String login_name;
	private String user_name;

	/**
	 * 解析xml数据
	 * @param urlStr
	 * 需要解析的xml地址
	 */
	public void  parserXml() {
		
		SAXParserFactory saxfac = SAXParserFactory.newInstance();
		try {
			URL url = new URL(urlStr);
			SAXParser saxparser = saxfac.newSAXParser();
			InputStream inputStream=url.openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
			InputSource is = new InputSource(reader);
			saxparser.parse(is, new MySAXHandler());
		} catch (ParserConfigurationException e) {
			//e.printStackTrace();
		} catch (SAXException e) {
			//e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class MySAXHandler extends DefaultHandler {

	
		
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes att) throws SAXException {
			preTag = qName;//将正在解析的节点名称赋给preTag  
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			  
			preTag = null;/**当解析结束时置为空。这里很重要。*/
		}
		
		@Override
	    public void characters(char[] ch, int start, int length) throws SAXException {  
	        if(preTag!=null){  
	            if("global_id".equals(preTag)){
	            	  String content = new String(ch,start,length);  
	            	  global_id = global_id+ content;
	            	setGlobal_id(global_id);
	            }
	            if("post".equals(preTag)){
	            	setPost(new String(ch,start,length));
	            }
	            if("user_name".equals(preTag)){
	            	setUser_name(new String(ch,start,length));
	            }
	            if("login_name".equals(preTag)){
	            	setLogin_name(new String(ch,start,length));
	            }
	        }  
	    }  
	}
	
	public String getPreTag() {
		return preTag;
	}

	public void setPreTag(String preTag) {
		this.preTag = preTag;
	}

	public String getGlobal_id() {
		return global_id;
	}

	public void setGlobal_id(String global_id) {
		this.global_id = global_id;
	}
	
	public String getUrlStr() {
		return urlStr;
	}

	public void setUrlStr(String urlStr) {
		this.urlStr = urlStr;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getPost() {
		return post;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
}


