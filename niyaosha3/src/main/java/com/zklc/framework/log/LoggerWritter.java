package com.zklc.framework.log;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.core.io.ClassPathResource;

public class LoggerWritter
{
  private static final String LOG_CONFIG_FILE = "log4j.properties";

  public static void debug(String message, Class curClass)
  {
    if (curClass != null) {
      Logger logger = Logger.getLogger(curClass);
      if (logger.isDebugEnabled())
        logger.debug(message);
    }
  }

  public static void debug(String message, String loggerName)
  {
    if ((loggerName != null) && (!("".equals(loggerName.trim())))) {
      Logger logger = Logger.getLogger(loggerName.trim());
      if (logger.isDebugEnabled())
        logger.debug(message);
    }
  }

  public static void debug(String message, Throwable ex, String loggerName)
  {
    if ((loggerName != null) && (!("".equals(loggerName.trim())))) {
      Logger logger = Logger.getLogger(loggerName.trim());
      if (logger.isDebugEnabled())
        logger.debug(message, ex);
    }
  }

  public static void debug(String message, Throwable ex, Class curClass)
  {
    if (curClass != null) {
      Logger logger = Logger.getLogger(curClass);
      if (logger.isDebugEnabled())
        logger.debug(message, ex);
    }
  }

  public static void info(String message, String loggerName)
  {
    if ((loggerName != null) && (!("".equals(loggerName.trim())))) {
      Logger logger = Logger.getLogger(loggerName.trim());
      if (logger.isInfoEnabled())
        logger.info(message);
    }
  }

  public static void info(String message, Class curClass)
  {
    if (curClass != null) {
      Logger logger = Logger.getLogger(curClass);
      if (logger.isInfoEnabled())
        logger.info(message + "   in class : " + curClass.getName());
    }
  }

  public static void info(String message, Throwable ex, String loggerName)
  {
    if ((loggerName != null) && (!("".equals(loggerName.trim())))) {
      Logger logger = Logger.getLogger(loggerName.trim());
      if (logger.isInfoEnabled())
        logger.info(message, ex);
    }
  }

  public static void info(String message, Throwable ex, Class curClass)
  {
    if (curClass != null) {
      Logger logger = Logger.getLogger(curClass);
      if (logger.isInfoEnabled())
        logger.info(message + "   in class : " + curClass.getName(), ex);
    }
  }

  public static void warn(String message, String loggerName)
  {
    if ((loggerName != null) && (!("".equals(loggerName.trim())))) {
      Logger logger = Logger.getLogger(loggerName.trim());
      if (logger.isEnabledFor(Priority.WARN))
        logger.warn(message);
    }
  }

  public static void warn(String message, Class curClass)
  {
    if (curClass != null) {
      Logger logger = Logger.getLogger(curClass);
      if (logger.isEnabledFor(Priority.WARN))
        logger.warn(message);
    }
  }

  public static void warn(String message, Throwable ex, String loggerName)
  {
    if ((loggerName != null) && (!("".equals(loggerName.trim())))) {
      Logger logger = Logger.getLogger(loggerName.trim());
      if (logger.isEnabledFor(Priority.WARN))
        logger.warn(message, ex);
    }
  }

  public static void warn(String message, Throwable ex, Class curClass)
  {
    if (curClass != null) {
      Logger logger = Logger.getLogger(curClass);
      if (logger.isEnabledFor(Priority.WARN))
        logger.warn(message, ex);
    }
  }

  public static void error(String message, String loggerName)
  {
    if ((loggerName != null) && (!("".equals(loggerName.trim())))) {
      Logger logger = Logger.getLogger(loggerName.trim());
      if (logger.isEnabledFor(Priority.ERROR))
        logger.error(message);
    }
  }

  public static void error(String message, Class curClass)
  {
    if (curClass != null) {
      Logger logger = Logger.getLogger(curClass);
      if (logger.isEnabledFor(Priority.ERROR))
        logger.error(message);
    }
  }

  public static void error(String message, Throwable ex, String loggerName)
  {
    if ((loggerName != null) && (!("".equals(loggerName.trim())))) {
      Logger logger = Logger.getLogger(loggerName.trim());
      if (logger.isEnabledFor(Priority.ERROR))
        logger.error(message, ex);
    }
  }

  public static void error(String message, Throwable ex, Class curClass)
  {
    if (curClass != null) {
      Logger logger = Logger.getLogger(curClass);
      if (logger.isEnabledFor(Priority.ERROR))
        logger.error(message, ex);
    }
  }

  public static void fatal(String message, String loggerName)
  {
    if ((loggerName != null) && (!("".equals(loggerName.trim())))) {
      Logger logger = Logger.getLogger(loggerName.trim());
      if (logger.isEnabledFor(Priority.FATAL))
        logger.fatal(message);
    }
  }

  public static void fatal(String message, Class curClass)
  {
    if (curClass != null) {
      Logger logger = Logger.getLogger(curClass);
      if (logger.isEnabledFor(Priority.FATAL))
        logger.fatal(message);
    }
  }

  public static void fatal(String message, Throwable ex, String loggerName)
  {
    if ((loggerName != null) && (!("".equals(loggerName.trim())))) {
      Logger logger = Logger.getLogger(loggerName.trim());
      if (logger.isEnabledFor(Priority.FATAL))
        logger.fatal(message, ex);
    }
  }

  public static void fatal(String message, Throwable ex, Class curClass)
  {
    if (curClass != null) {
      Logger logger = Logger.getLogger(curClass);
      if (logger.isEnabledFor(Priority.FATAL))
        logger.fatal(message, ex);
    }
  }

  static
  {
    ClassPathResource resource;
    try
    {
      resource = new ClassPathResource("log4j.properties");
      PropertyConfigurator.configure(resource.getURL());
    } catch (IOException ex) {
      System.out.println("LogWritter IoException");
    }
  }
}