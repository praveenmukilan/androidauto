package com.autome.framework.config;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DefaultConfig
{
  private static DefaultConfig defaultConfig = null;
  Properties configData = null;
  
  private DefaultConfig()
  {
    loadConfig();
  }
  
  public static DefaultConfig getDefaultConfig()
  {
    if (defaultConfig == null) {
      defaultConfig = new DefaultConfig();
    }
    return defaultConfig;
  }
  
  public String getConfigValue(String configuration)
  {
    return this.configData.getProperty(configuration, "");
  }
  
  public void setConfigValue(String configuration, String value)
  {
    this.configData.setProperty(configuration, value);
  }
  
  private void loadConfig()
  {
    String path = System.getProperty("taf.config");
    this.configData = new Properties();
    if (path == null)
    {
      String rootDir = new File("").getAbsolutePath();
      path = rootDir + "/src/test/resources/taf.properties".replace("/", File.separator);
    }
    File configFile = new File(path);
    if ((configFile.exists()) && (configFile.isFile())) {
      try
      {
        this.configData.load(new FileInputStream(configFile));
      }
      catch (IOException e)
      {
        throw new ConfigException("Unable to load default properties file.Please make sure that the propertis taf.properties is deifned under src/test/resources folder of your project.\n Or the path to the properties is set to the property 'taf.config'.");
      }
      catch (Exception e)
      {
        throw new ConfigException("Unable to load file because of: " + e.getMessage());
      }
    } else {
      throw new ConfigException("Unable to load default properties file.Please make sure that the propertis taf.properties is deifned under src/test/resources folder of your project.\n Or the path to the properties is set to the property 'taf.config'.");
    }
  }
}



/* Location:           C:\Users\VandP\Dropbox\Office\mig\Automation Framework\testautomationframework-0.0.1.jar

* Qualified Name:     com.praveenms.test.automation.framework.config.DefaultConfig

* JD-Core Version:    0.7.0.1

*/