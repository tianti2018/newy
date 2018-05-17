package com.zklc.framework.log;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class BusinessAppLogger
{
  private Logger logger;

  public BusinessAppLogger(Logger logger)
  {
    this.logger = logger;
  }

  public void debug(String message)
  {
    if (this.logger.isDebugEnabled())
      this.logger.debug(message);
  }

  public void debug(Object message)
  {
    if (this.logger.isDebugEnabled())
      this.logger.debug(message);
  }

  public void debug(String message, Throwable ex)
  {
    if (this.logger.isDebugEnabled())
      this.logger.debug(message, ex);
  }

  public void info(String message)
  {
    if (this.logger.isInfoEnabled())
      this.logger.info(message);
  }

  public void info(Object message)
  {
    if (this.logger.isInfoEnabled())
      this.logger.info(message);
  }

  public void info(String message, Throwable ex)
  {
    if (this.logger.isInfoEnabled())
      this.logger.info(message, ex);
  }

  public void error(String message)
  {
    if (this.logger.isEnabledFor(Priority.ERROR))
      this.logger.error(message);
  }

  public void error(String message, Throwable ex)
  {
    if (this.logger.isEnabledFor(Priority.ERROR))
      this.logger.error("业务操作异常："+message, ex);
  }

  public void error(Object obj)
  {
    if (this.logger.isEnabledFor(Priority.ERROR))
      this.logger.error(obj);
  }
}