package com.zklc.framework.log;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class SystemLogger
{
  private Logger logger;

  public SystemLogger(Logger logger)
  {
    this.logger = logger;
  }

  public void debug(String message)
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

  public void info(String message, Throwable ex)
  {
    if (this.logger.isInfoEnabled())
      this.logger.info(message, ex);
  }

  public void warn(String message)
  {
    if (this.logger.isEnabledFor(Priority.WARN))
      this.logger.warn(message);
  }

  public void warn(String message, Throwable ex)
  {
    if (this.logger.isEnabledFor(Priority.WARN))
      this.logger.warn(message, ex);
  }

  public void error(String message)
  {
    if (this.logger.isEnabledFor(Priority.ERROR))
      this.logger.error(message);
  }

  public void error(String message, Throwable ex)
  {
    if (this.logger.isEnabledFor(Priority.ERROR))
      this.logger.error(message, ex);
  }

  public void fatal(String message)
  {
    if (this.logger.isEnabledFor(Priority.FATAL))
      this.logger.fatal(message);
  }

  public void fatal(String message, Throwable ex)
  {
    if (this.logger.isEnabledFor(Priority.ERROR))
      this.logger.fatal(message, ex);
  }
}