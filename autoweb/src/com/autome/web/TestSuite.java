package com.autome.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestSuite {
	
	public static WebDriver driver;
	public static Properties webprop;
	@BeforeClass
	public static void init(){
		
		try{
		System.setProperty("webdriver.chrome.driver", "src//resources//chromedriver");

		   driver = new ChromeDriver();
		   webprop= new Properties();
		   FileInputStream webFis = new FileInputStream("//src//resources//OR.properties");
           webprop.load(webFis);
		}catch(FileNotFoundException e){
			
			System.out.println(e.getMessage());
		}catch(IOException e){
			
			System.out.println(e.getMessage());
		}
	
	}
	
	@Test
	public static void runTest(){
		
		driver.get(webprop.getProperty("URL"));
		
		driver.findElement(By.cssSelector(webprop.getProperty("lnk_SignIn")));
		
		driver.findElement(By.cssSelector(webprop.getProperty("txt_UserName")));
		driver.findElement(By.cssSelector(webprop.getProperty("txt_Password")));
		
		driver.findElement(By.cssSelector(webprop.getProperty("lnk_SignIn")));
		
		driver.findElement(By.cssSelector(webprop.getProperty("lnk_SignIn")));
		
		
		
	}
	
	
}
