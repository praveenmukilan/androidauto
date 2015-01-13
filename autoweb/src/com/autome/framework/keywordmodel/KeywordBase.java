package com.autome.framework.keywordmodel;



import com.autome.framework.keywordmodel.KeywordException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class KeywordBase
  implements IKeyword
{
  Map<String, Map<Integer, List<Method>>> methods = new HashMap();
  private Object executingObject = null;
  private boolean initialized = false;
  
  protected KeywordBase()
  {
    initialize();
  }
  
  private void initialize()
  {
    this.executingObject = this;
    initialize(this.executingObject);
  }
  
  protected void initialize(Object obj)
  {
    Class<?> cls = obj.getClass();
    this.methods.clear();
    Iterator<Method> it = Arrays.asList(cls.getMethods()).iterator();
    while (it.hasNext())
    {
      Method method = (Method)it.next();
      
      Method methodInfo = new Method(method);
      

      String methodName = method.getName();
      int noOfParams = method.getParameterTypes().length;
      Map<Integer, List<Method>> paramMethodMap;
      if (this.methods.containsKey(methodName))
      {
        Map<Integer, List<Method>> paramMethodMap = (Map)this.methods.get(methodName);
        List<Method> infoList;
        List<Method> infoList;
        if (paramMethodMap.containsKey(Integer.valueOf(noOfParams))) {
          infoList = (List)paramMethodMap.get(Integer.valueOf(noOfParams));
        } else {
          infoList = new ArrayList();
        }
        infoList.add(methodInfo);
        paramMethodMap.put(Integer.valueOf(noOfParams), infoList);
      }
      else
      {
        paramMethodMap = new HashMap();
        List<Method> infoList = new ArrayList();
        infoList.add(methodInfo);
        paramMethodMap.put(Integer.valueOf(noOfParams), infoList);
      }
      this.methods.put(methodName, paramMethodMap);
    }
    this.initialized = true;
  }
  
  public boolean isSupported(String methodName, Object[] args)
  {
    if (!this.initialized) {
      initialize();
    }
    if (!this.methods.containsKey(methodName)) {
      return false;
    }
    Map<Integer, List<Method>> paramMethods = (Map)this.methods.get(methodName);
    boolean supported = false;
    if (paramMethods.containsKey(Integer.valueOf(args.length))) {
      supported = true;
    }
    return supported;
  }
  
  public void execute(String keyword, Object[] args)
  {
    Map<Integer, List<Method>> paramMethods = (Map)this.methods.get(keyword);
    boolean executed = false;
    boolean canBeExecuted = false;
    if (paramMethods.containsKey(Integer.valueOf(args.length)))
    {
      List<Method> methodInfos = (List)paramMethods.get(Integer.valueOf(args.length));
      for (Method methodInfo : methodInfos)
      {
        Object[] convertedParams = convertParamTypes(methodInfo.getParameterTypes(), args);
        if (convertedParams != null) {
          canBeExecuted = true;
        }
        if (canBeExecuted)
        {
          Method method = methodInfo.getMethodObject();
          try
          {
            method.invoke(this.executingObject, convertedParams);
            executed = true;
          }
          catch (IllegalAccessException|IllegalArgumentException escape) {}catch (InvocationTargetException e)
          {
            throw new KeywordException(e);
          }
        }
      }
    }
    if (!executed) {
      throw new KeywordException("Unable to find any key word with name: \"" + keyword + "\" and arguments: \"" + args.toString() + " in class: \"" + getClass().getName() + "\".");
    }
  }
  
  private Object[] convertParamTypes(Class<?>[] methodParams, Object[] passedParams)
  {
    List<Object> paramTypes = new ArrayList();
    for (int itr = 0; itr < methodParams.length; itr++)
    {
      Class<?> paramType = methodParams[itr];
      Object data = getConvertedParam(paramType, passedParams[itr]);
      if (data == null) {
        return null;
      }
      paramTypes.add(data);
    }
    return paramTypes.toArray();
  }
  
  private Object getConvertedParam(Class<?> fieldType, Object obj)
  {
    String value = (String)obj;
    
    Object data = null;
    try
    {
      if ((fieldType == Integer.TYPE) || (fieldType == Integer.class)) {
        data = value.length() == 0 ? null : Integer.valueOf(Integer.parseInt(value));
      } else if (fieldType == String.class) {
        data = value.length() == 0 ? null : value;
      } else if ((fieldType == Boolean.class) || (fieldType == Boolean.TYPE)) {
        data = value.length() == 0 ? null : Boolean.valueOf(Boolean.parseBoolean(value));
      } else if ((fieldType == Float.TYPE) || (fieldType == Float.class)) {
        data = value.length() == 0 ? null : Float.valueOf(Float.parseFloat(value));
      } else if ((fieldType == Long.TYPE) || (fieldType == Long.class)) {
        data = value.length() == 0 ? null : Long.valueOf(Long.parseLong(value));
      } else if ((fieldType == Double.TYPE) || (fieldType == Double.class)) {
        data = value.length() == 0 ? null : Double.valueOf(Double.parseDouble(value));
      } else if ((fieldType == Short.TYPE) || (fieldType == Short.class)) {
        data = value.length() == 0 ? null : Short.valueOf(Short.parseShort(value));
      } else if ((fieldType == Character.TYPE) || (fieldType == Character.class)) {
        data = value.length() == 0 ? null : Character.valueOf(value.charAt(0));
      } else if ((fieldType == Byte.TYPE) || (fieldType == Byte.class)) {
        data = value.length() == 0 ? null : Byte.valueOf(value);
      }
    }
    catch (NumberFormatException e) {}
    return data;
  }
}


