package com.zklc.framework.log;
import org.apache.log4j.Logger;

public class LoggerFactory
{
  public static SystemLogger getSystemLogger(Class clazz)
  {
    return new SystemLogger(Logger.getLogger(clazz));
  }

  public static BusinessAppLogger getBusinessAppLogger(Class clazz)
  {
    return new BusinessAppLogger(Logger.getLogger(clazz));
  }

}