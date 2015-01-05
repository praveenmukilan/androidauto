

import io.appium.java_client.android.AndroidDriver;







import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.ProcessDestroyer;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AndroidAppium{


//	public static RemoteWebDriver driver;
	public static AndroidDriver driver;
	
	static DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
	static DefaultExecutor executor = new DefaultExecutor();
	@BeforeMethod
	
	public static void setUp() throws Exception {
		
		System.out.println("****************setUP Starts****************");

//		System.out.println(Runtime.getRuntime().exec("/bin/sh export ANDROID_HOME=/Users/Praveen/APPIUM/android-sdk-macosx"));
//		System.out.println(Runtime.getRuntime().exec("/bin/sh export PATH=$PATH:$ANDROID_HOME/build-tools/:$ANDROID_HOME/tools/:$ANDROID_HOME/platform-tools/"));

		
		System.out.println("ANDROID_HOME : "+System.getenv("ANDROID_HOME"));
		System.out.println("PATH : "+System.getenv("PATH"));
		
//		CommandLine killNode = new CommandLine("killall -9 node");
//		CommandLine killPlayerEmulator = new CommandLine("killall -9 player");
//		CommandLine killADB = new CommandLine("killall -9 adb");
		resultHandler = new DefaultExecuteResultHandler();
		executor = new DefaultExecutor();
		executor.setExitValue(1);
//		executor.execute(killNode,resultHandler);
//		executor.execute(killPlayerEmulator,resultHandler);
//		executor.execute(killADB,resultHandler);
		
	//	CommandLine command = new CommandLine("/bin/sh -c");
	//	command.addArgument("/Applications/Appium.app/Contents/Resources/node/bin/node",false);
		
		CommandLine command = new CommandLine("/Applications/Appium.app/Contents/Resources/node/bin/node");

		
		command.addArgument("/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js", false);
		command.addArgument("--address", false);
		command.addArgument("127.0.0.1");
		command.addArgument("--port", false);
		command.addArgument("6723");
		command.addArgument("--bootstrap-port", false);
		command.addArgument("6724");
//		command.addArgument("--no-reset", false);

		executor.execute(command, resultHandler);

	
		
		Thread.sleep(20000);
		

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("platformName", "Android");
//		capabilities.setCapability("platformVersion", "4.4.2");
//		capabilities.setCapability("browserName", "android");
//		capabilities.setCapability("appPackage", "com.migmeplayground");
//		capabilities.setCapability("appPackage", "com.migmeplayground");
//		capabilities.setCapability("appActivity", "com.projectgoth.activity.MainActivity");
//		
		capabilities.setCapability("app", "/Users/Praveen/APPIUM/mig33Droidv5.00.020.apk");
//		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "device");		
		
//		capabilities.setCapability("app", "/Users/Praveen/APPIUM/mig33Droidv5.00.020.apk");
//        capabilities.setCapability("app", "/Users/Praveen/Downloads/mig33Droid-5.00.14-SNAPSHOT.apk");
//		capabilities.setCapability("avd", "SamsungGalaxyS4");

		capabilities.setCapability("deviceName", "device");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:6723/wd/hub"),capabilities);
//		driver = new RemoteWebDriver(new URL("http://127.0.0.1:6723/wd/hub"),capabilities);
		System.out.println("****");
	
		
		
	//	driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
	
	
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
//		driver.findElement(By.name("Continue")).click();
		System.out.println("****************setUp Ends****************");
}
	
	public static void main(String args[]){
		
		
		try{
			
			System.out.println("****************main Starts****************");
			killNodeAdbPlayer();
		launchEmulator();
		Thread.sleep(20000);
		setUp();
		test01();
		tearDown();
		System.out.println("****************main Ends****************");
		}
		catch(Exception e){
			
			System.out.println(e);
		}
		
	}
	

@AfterMethod
public static void tearDown() throws Exception {
	System.out.println("****************tearDown Starts****************");
	Thread.sleep(10000);
	driver.quit();
	killNodeAdbPlayer();
	System.out.println("****************tearDown Ends****************");
}

@Test
public static void test01() throws InterruptedException {
System.out.println("Jesus");
//AndroidDriver andy = (AndroidDriver)driver;
System.out.println("****************test01 Starts****************");

driver.findElement(By.xpath("//android.view.View[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.EditText[1]")).sendKeys("praveenmukilan");
driver.findElement(By.xpath("//android.view.View[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.EditText[2]")).sendKeys("60se!inMS");
driver.findElement(By.xpath("//android.view.View[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.Button[2]")).click();

//driver.findElement(By.id("com.projectgoth:id/txt_username"));
//driver.findElement(MobileBy.ByAndroidUIAutomator.)

//Steps to sign out

//Button Icon: com.projectgoth:id/button_icon

driver.findElement(By.id("com.projectgoth:id/button_icon")).click();

//Menu Settings: com.projectgoth:id/menu_settings

//driver.findElement(By.id("com.projectgoth:id/menu_settings"));
driver.findElementByAccessibilityId("setting").click();



//Click on Sign out Link
//driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.projectgoth:id/menu_settings' and @index='2']")).click();

//List <WebElement> elmnts = driver.findElement("com.projectgoth:id/label");
//driver.findElement(By.name("setting")).click();
// Thread.sleep(500000);

driver.findElement(By.xpath("//android.widget.TextView[@text='Sign out']")).click();


System.out.println("****************test01 Ends****************");

 }

public static void launchEmulator() throws ExecuteException, IOException{
	
	System.out.println("Jesus Launch");
	
	System.out.println("****************launchEmulator Starts****************");
	
	CommandLine launchEmul = new CommandLine("/Applications/Genymotion.app/Contents/MacOS/player");

	

//	command.addArgument("--address", false);
//	command.addArgument("127.0.0.1");
	launchEmul.addArgument("--vm-name", false);
	launchEmul.addArgument("SamsungGalaxyS4");
	
	  

	executor.setExitValue(1);
	
	executor.execute(launchEmul, resultHandler);
	System.out.println("****************launchEmulator Ends****************");
}

private static void killNodeAdbPlayer() throws ExecuteException, IOException, Exception{
	System.out.println("****************kill Node Adb Player Starts****************");
	
	CommandLine killNode = new CommandLine("kill -9 $(lsof -i | grep 6723 | awk '{print $2}')");
	CommandLine killPlayer = new CommandLine("kill -9 $(lsof -i | grep 6724 | awk '{print $2}')");
//	CommandLine killAdb = new CommandLine("kill -9 6724");

//	ps aux | grep '\s*adb*\s' | awk '{print $2}'
//kill -9 $(ps aux | grep '\s*player*\s' | awk '{print $2}')	
//	kill -9 $(lsof -i | grep 6723 | awk '{print $2}')
	executor.setExitValue(1);
	executor.execute(killNode,resultHandler);
	executor.execute(killPlayer,resultHandler);
//	executor.execute(killAdb,resultHandler);
	//System.out.println("**************killNodeAdbPlayer : 	"+resultHandler.getExitValue());
//Thread.sleep(500000);
	System.out.println("****************kill Node Adb Player Ends****************");
}


	
}
