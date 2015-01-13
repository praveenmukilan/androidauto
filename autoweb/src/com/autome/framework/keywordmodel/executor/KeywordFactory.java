package com.autome.framework.keywordmodel.executor;




import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.autome.framework.config.DefaultConfig;
import com.autome.framework.keywordmodel.IKeyword;
import com.autome.framework.keywordmodel.KeywordException;

public class KeywordFactory
{
  DefaultConfig config = DefaultConfig.getDefaultConfig();
  List<IKeyword> keywordClassObjects;
  WebDriver driver;
  
  public KeywordFactory(WebDriver driver)
  {
    this.driver = driver;
    this.keywordClassObjects = getKeywordImplementations();
  }
  
  private List<IKeyword> getKeywordImplementations()
  {
    String[] keywordClasses = this.config.getConfigValue("listeners").split(",");
    ClassLoader cloader = getClass().getClassLoader();
    List<Class<?>> listenerClasses = new ArrayList();
    List<IKeyword> keywordClsObjs = new ArrayList();
    for (String keywordClass : keywordClasses) {
      try
      {
        Class<?> cls = cloader.loadClass(keywordClass);
        listenerClasses.add(cls);
      }
      catch (ClassNotFoundException e)
      {
        throw new KeywordException("Unable to find class with name as mentione in listner property: " + keywordClass + " Make sure the class is present in the classpath.");
      }
    }
    for (Class<?> cls : listenerClasses) {
      if (IKeyword.class.isAssignableFrom(cls))
      {
        IKeyword obj = null;
        try
        {
          Constructor<?> constructor = cls.getConstructor(new Class[] { WebDriver.class });
          
          obj = (IKeyword)constructor.newInstance(new Object[] { this.driver });
        }
        catch (IllegalAccessException|InstantiationException|SecurityException|NoSuchMethodException|IllegalArgumentException|InvocationTargetException e)
        {
          throw new KeywordException("Unable to find a constructor that accepts the WebDriver object for the keyword class:" + cls.getName());
        }
        if (obj != null) {
          keywordClsObjs.add(obj);
        }
      }
    }
    return keywordClsObjs;
  }
  
  public void executeKeyword(String keyword, Object[] args)
  {
    boolean executed = false;
    for (IKeyword keywordCls : this.keywordClassObjects)
    {
      boolean supported = keywordCls.isSupported(keyword, args);
      if (supported)
      {
        keywordCls.execute(keyword, args);
        executed = true;
      }
    }
    if (!executed) {
      throw new KeywordException("Unable to find any keyword class that support keyword: \"" + keyword + "\" and arguments: \"" + args + "\".");
    }
  }
}
