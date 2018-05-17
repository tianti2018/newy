package com.zklc.framework.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MethodInvoker
{
  public static final VoidType VOID = new VoidType();
  private Class targetClass;
  private Object targetObject;
  private String targetMethod;
  private Object[] arguments;
  private Method methodObject;

  public void setTargetClass(Class targetClass)
  {
    this.targetClass = targetClass;
  }

  public Class getTargetClass()
  {
    return this.targetClass;
  }

  public void setTargetObject(Object targetObject)
  {
    this.targetObject = targetObject;
  }

  public Object getTargetObject()
  {
    return this.targetObject;
  }

  public void setTargetMethod(String targetMethod)
  {
    this.targetMethod = targetMethod;
  }

  public String getTargetMethod()
  {
    return this.targetMethod;
  }

  public void setStaticMethod(String staticMethod)
    throws ClassNotFoundException
  {
    int lastDotIndex = staticMethod.lastIndexOf(46);
    if ((lastDotIndex == -1) || (lastDotIndex == staticMethod.length())) {
      throw new IllegalArgumentException("staticMethod must be a fully qualified class plus method name: e.g. 'example.MyExampleClass.myExampleMethod'");
    }

    String className = staticMethod.substring(0, lastDotIndex);
    String methodName = staticMethod.substring(lastDotIndex + 1);
    setTargetClass(Class.forName(className, true, Thread.currentThread().getContextClassLoader()));

    setTargetMethod(methodName);
  }

  public void setArguments(Object[] arguments)
  {
    this.arguments = arguments;
  }

  public Object[] getArguments() {
    return this.arguments;
  }

  public void prepare()
    throws ClassNotFoundException, NoSuchMethodException
  {
    if ((this.targetClass == null) && (this.targetObject == null)) {
      throw new IllegalArgumentException("Either targetClass or targetObject is required");
    }

    if (this.targetMethod == null) {
      throw new IllegalArgumentException("targetMethod is required");
    }

    if (this.arguments == null)
      this.arguments = new Object[0];

    Class[] types = new Class[this.arguments.length];
    for (int i = 0; i < this.arguments.length; ++i) {
      types[i] = this.arguments[i].getClass();
    }

    Class targetClass = (this.targetObject != null) ? this.targetObject.getClass() : this.targetClass;
    try
    {
      this.methodObject = targetClass.getMethod(this.targetMethod, types);
    } catch (NoSuchMethodException ex) {
      int matches = 0;

      Method[] methods = targetClass.getMethods();
      for (int i = 0; i < methods.length; ++i) {
        Method method = methods[i];
        if ((method.getName().equals(this.targetMethod)) && (method.getParameterTypes().length == types.length))
        {
          this.methodObject = method;
          ++matches;
        }
      }

      if ((this.methodObject == null) || (matches > 1))
        throw ex;
    }

    if ((this.targetObject == null) && (!(Modifier.isStatic(this.methodObject.getModifiers()))))
    {
      throw new IllegalArgumentException("Target method must not be static without a target");
    }
  }

  public Method getPreparedMethod()
  {
    return this.methodObject;
  }

  public Object invoke()
    throws InvocationTargetException, IllegalAccessException
  {
    Object result = this.methodObject.invoke(this.targetObject, this.arguments);

    return ((result == null) ? VOID : result);
  }

  public static class VoidType
  {
  }
}