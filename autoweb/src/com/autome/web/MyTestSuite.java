package com.autome.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.*;
import org.migme.test.automation.framework.keywordmodel.executor.KeywordExecutor;
import org.migme.test.automation.framework.keywordmodel.suite.ISimpleTest;
import org.migme.test.automation.framework.keywordmodel.suite.TestSuite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class MyTestSuite {
	
	public static WebDriver driver;
	public static Properties OR;
	public static Properties webprop;
	
	@BeforeClass
	public static void init(){
		
		try{
			System.out.println("************init**************");
		System.setProperty("webdriver.chrome.driver", "src//resources//chromedriver");

		   driver = new ChromeDriver();
		   OR= new Properties();
		   webprop = new Properties();
		   
		   FileInputStream orFIS = new FileInputStream("src//resources//OR.properties");
		   FileInputStream webFIS = new FileInputStream("src//resources//web.properties");
           OR.load(orFIS);
           webprop.load(webFIS);
           
		}catch(FileNotFoundException e){
			
			System.out.println(e.getMessage());
		}catch(IOException e){
			
			System.out.println(e.getMessage());
		}
	
	}
	
//	@Test
	public static void runTest(){
		
		try {
		
		System.out.println("******************runTest********************");
		
		driver.get(OR.getProperty("URL"));
		
		driver.findElement(By.cssSelector(OR.getProperty("lnk_SignIn"))).click();
		String username=new String(Base64.getDecoder().decode(webprop.getProperty("username")));
		String password=new String(Base64.getDecoder().decode(webprop.getProperty("password")));
		
		driver.findElement(By.cssSelector(OR.getProperty("txt_UserName"))).sendKeys(username);
		driver.findElement(By.cssSelector(OR.getProperty("txt_Password"))).sendKeys(password);		
		driver.findElement(By.cssSelector(OR.getProperty("btn_SignIn"))).click();

		//user logged in
		Assert.assertEquals(driver.getTitle(), "migme");
		
		driver.findElement(By.cssSelector(OR.getProperty("dpd_Logout"))).click();
        driver.findElement(By.cssSelector(OR.getProperty("optLogout"))).click();
        
        
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if(driver!=null)
        	driver.quit();
		
		
	}
	
	@Test(dataProvider="Data")
	public void webTestSuite(ISimpleTest simpleTest){
		File file = new File(simpleTest.getTestFilePath());
		KeywordExecutor keyExecutor = new KeywordExecutor(driver,file);
		keyExecutor.execute();		
	}
	
	@DataProvider(name="Data")
	public Object[][] getTestData(){
		File file = new File("src/test/resources/MigTestSuite.xls");
		ArrayList<String> args = new ArrayList<String>();
		args.add("Suite");
		
		TestSuite suiteReader = new TestSuite(file, args);
		return suiteReader.getTobeExecutedTests();
	
	
}
	
	@AfterSuite
	public void tearDown(){
		if(driver!=null) driver.quit();
		
	}
	
}
