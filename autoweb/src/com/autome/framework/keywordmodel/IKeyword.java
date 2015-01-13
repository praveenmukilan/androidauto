package com.autome.framework.keywordmodel;



public abstract interface IKeyword
{
  public abstract boolean isSupported(String paramString, Object[] paramArrayOfObject);
  
  public abstract void execute(String paramString, Object[] paramArrayOfObject);
}



/* Location:           C:\Users\VandP\Dropbox\Office\mig\Automation Framework\testautomationframework-0.0.1.jar

 * Qualified Name:     com.menonvarun.test.automation.framework.keywordmodel.keywords.IKeyword

 * JD-Core Version:    0.7.0.1

 */