package com.migme.android;

import org.apache.commons.lang3.RandomStringUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.bouncycastle.asn1.cms.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import com.migme.util.Constants;

public class AndroidDriverScript{

	public static AppiumDriver driver;
	public static Process appium;
	public static Properties OR;
	
	static DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
	static DefaultExecutor executor = new DefaultExecutor();

	
	public static void test01() throws InterruptedException, IOException {
//		System.out.println("Jesus");
		//AndroidDriver andy = (AndroidDriver)driver;
		System.out.println("****************test01 Starts****************");

		signIn();

//		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.projectgoth:id/main_button")));

		
		privateChat();
//	
		postText();
		postImage();
		postEmoticons();
		newGroupChat();

		signOut();


		System.out.println("****************test01 Ends****************");

		 }
	
	public static void setUp() throws Exception {
		Properties prop = new Properties();
		Properties apkFile = new Properties();
		OR = new Properties();
		//From the build, the apkFile.properties will reside in $WORSPACE/android
		FileInputStream apk = new FileInputStream("apkFile.properties");
		FileInputStream fis = new FileInputStream("src//config//androidauto.properties");
		FileInputStream orFis = new FileInputStream("src//config//OR_Android.txt");
		
		apkFile.load(apk);
		prop.load(fis);
		OR.load(orFis);
		
		System.out.println("****************$$$ setUP Starts****************");
		
//		System.out.println("ANDROID_HOME : "+System.getenv("ANDROID_HOME"));
//		System.out.println("PATH : "+System.getenv("PATH"));
		
/*
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
*/
//		startAppium();
	
		/*
	System.out.println("<<>>*********** Please wait for 15 seconds ***********");
		
		Thread.sleep(15000);
		*/
		
		String androidApkPath = apkFile.getProperty("APKPATH");
		
		System.out.println("androidApkPath : "+androidApkPath);
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("platformName", "Android");		
//		capabilities.setCapability("platformVersion", "4.4.2");
//		capabilities.setCapability("browserName", "android");
		
		/* appPackage & appActivity is not mandatory as the Appium will inspect the .apk file for the default package and the activity
		 * Issues if any, please set it explicitly.
		 * */
//		capabilities.setCapability("appPackage", "com.migmeplayground");
//		capabilities.setCapability("appActivity", "com.projectgoth.activity.MainActivity");
//		https://tools.projectgoth.com/jenkins/view/3.%20Mobile/job/QA-CI%20androidV5/ws/target/
//		capabilities.setCapability("app", "/Users/Praveen/APPIUM/mig33Droidv5.00.020.apk");
		capabilities.setCapability("app", androidApkPath);
	
//		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "device");		
		

//      The device name could be found by running the command adb in the terminal window.
		/*
		 * 
		 * android-sdk-macosx$ adb devices
							   List of devices attached 
							   192.168.56.101:5555	device
		 * 
		 * */
		capabilities.setCapability("deviceName", "device");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

		System.out.println("****");
	
	
		
		
	//	driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
	
	
//		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		

		System.out.println("****************setUp Ends****************");
}
	

	
	@Test
	public static void main(){
		
		
		try{
			
			System.out.println("****************main Starts****************");
//			killNodeAdbPlayer();
//		launchEmulator();
//		Thread.sleep(20000);
		setUp();
		test01();
// As the server start through code is having issues, commenting stopAppium()
//		stopAppium();
		System.out.println("****************main Ends****************");
		}
		catch(Exception e){
			
			System.out.println(e);
		}
		
	}
	


@AfterMethod
public static void tearDown() throws Exception {
	System.out.println("****************tearDown Starts****************");
/*
	if(!driver.equals(null))
	       driver.quit();
	killNodeAdbPlayer();
	*/
	System.out.println("****************tearDown Ends****************");
}

public static void postText(){
	
			
	driver.findElementByAccessibilityId(OR.getProperty("mainBtn")).click();	
	driver.findElementByAccessibilityId(OR.getProperty("postBtn")).click();		
	driver.findElementById(OR.getProperty("postTextField")).sendKeys(RandomStringUtils.randomAlphabetic(100));
	driver.findElementByAccessibilityId(OR.getProperty("postSendBtn")).click();		
	
}

public static void postImage(){
	
	driver.findElementByAccessibilityId(OR.getProperty("mainBtn")).click();	
	driver.findElementByAccessibilityId(OR.getProperty("postBtn")).click();		
	driver.findElementByAccessibilityId(OR.getProperty("photoBtn")).click();
	driver.findElementByXPath(OR.getProperty("cameraOption")).click();
	
	driver.findElementById(OR.getProperty("shutterBtn")).click();
	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	driver.findElementById(OR.getProperty("doneBtn")).click();
	driver.findElementById(OR.getProperty("postTextField")).sendKeys(RandomStringUtils.randomAlphabetic(100));
	driver.findElementByAccessibilityId(OR.getProperty("postSendBtn")).click();	
}

public static void postEmoticons(){
	

	driver.findElementByAccessibilityId(OR.getProperty("mainBtn")).click();	
	driver.findElementByAccessibilityId(OR.getProperty("postBtn")).click();		
	driver.findElementByAccessibilityId(OR.getProperty("emoticonBtn")).click();	
	driver.findElementById(OR.getProperty("postTextField")).sendKeys(RandomStringUtils.randomAlphabetic(200));
	driver.findElementByXPath(OR.getProperty("emoticonItem")).click();

//	driver.findElementById(OR.getProperty("postTextField")).sendKeys(OR.getProperty("postTextLT300"));
	driver.findElementByAccessibilityId(OR.getProperty("postSendBtn")).click();		
	
}




public static void chatToFeedPage(){
	System.out.println("*****************chatToFeedPage*********************");
	//to navigate back from chat window
	driver.findElementByAccessibilityId(OR.getProperty("chatBackBtn")).click();

	//to navigate back to feed screen, click main button & click on feed button
	driver.findElementByAccessibilityId(OR.getProperty("mainBtn")).click();

	driver.findElementByAccessibilityId(OR.getProperty("feedBtn")).click();

}
public static void startNewChat(){
	System.out.println("*****************startNewChat*********************");
	driver.findElementByAccessibilityId(OR.getProperty("mainBtn")).click();


	driver.findElementByAccessibilityId(OR.getProperty("chatBtn")).click();
	//main button click to view the new private group chat icon
	driver.findElementByAccessibilityId(OR.getProperty("mainBtn")).click();
	
	driver.findElementByAccessibilityId(OR.getProperty("newChatBtn")).click();

	//driver.findElementByAccessibilityId("chat_list_tab").click();

	
}


public static void privateChat(){
	System.out.println("*****************privateChat starts*********************");
    
 startNewChat();

 driver.findElementById(OR.getProperty("chatUserNamesList")).click();
//	//chat list
//	driver.findElementById("com.projectgoth:id/label").click();
//	//driver.findElementById("com.projectgoth:id/container").click();
//
//	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
 postEmoticonInChat();

 sendGiftInPrivateChat();

	driver.findElementById(OR.getProperty("chatTextField")).sendKeys("hi @ "+getCurrentTimeStamp());

	driver.findElementByAccessibilityId(OR.getProperty("chatSend")).click();
	
	chatToFeedPage();
	System.out.println("*****************privateChat ends*********************");

}

public static void chooseGiftInChat(){
	
	driver.findElementByAccessibilityId(OR.getProperty("chatGiftIcon")).click();
	waitForSecs(5);
	chooseGiftInOrder();
//	chooseGiftInRandom();
	
}

public static void chooseGiftInOrder(){
	driver.findElementById(OR.getProperty("chatGiftPrice3Cents")).click();
	
}

public static void chooseGiftInRandom(){
	
	List<WebElement> gifts = driver.findElementsById(OR.getProperty("chatGiftPrice3Cents"));
	
	//randomly select a gift
	gifts.get(new Random().nextInt(gifts.size())).click();
}


public static void selectUserForGift(){
	

	if(driver.findElementByXPath(OR.getProperty("chatGiftUserSelect"))!=null)
		driver.findElementByXPath(OR.getProperty("chatGiftUserSelect")).click();
	

}

public static void clickNextButtonChatGift(){
	
	driver.findElementById(OR.getProperty("chatGiftNextBtn")).click();
}

public static void sendGiftInChat(){
	

	driver.findElementById(OR.getProperty("chatGiftSend")).click();
	waitForSecs(3);
	driver.findElementByAccessibilityId(OR.getProperty("chatGiftSentCloseBtn")).click();
	
}

public static void sendGiftInPrivateChat(){
	
	chooseGiftInChat();
	sendGiftInChat();
}

public static void sendGiftInGroupChat(){
	chooseGiftInChat();
	selectUserForGift();
	clickNextButtonChatGift();
	sendGiftInChat();
}

public static void postEmoticonInChat(){
	
	driver.findElementByAccessibilityId(OR.getProperty("chatEmoticon")).click();
	driver.findElementByAccessibilityId(OR.getProperty("chatEmoteItem")).click();
}

public static void newGroupChat(){
	System.out.println("*****************newGroupChat starts*********************");
	startNewChat();
	   // 1. Click Main Button
	   // 2. Click the New Chat Icon
	
	
//	List<WebElement> chatUsers= driver.findElementsById(OR.getProperty("chatUsersList"));
	List<WebElement> chatUsers = driver.findElementsById(OR.getProperty("chatUserNamesList"));

	if(chatUsers.size()<2)
		System.out.println("The user is not having two friends to do a group chat");

//	driver.findElementById(OR.getProperty("catName")).click();


	
	//add only two users of the list for a group chat
	for(int i=0; i<2;i++){
		chatUsers.get(i).click();
	}
	
	

	
	
	sendGiftInGroupChat();

	postEmoticonInChat();
	
	
	driver.findElementById(OR.getProperty("chatTextField")).sendKeys("hi @ "+getCurrentTimeStamp());

	driver.findElementByAccessibilityId(OR.getProperty("chatSend")).click();

	chatToFeedPage();
	System.out.println("*****************newGroupChat ends*********************");
}

public static void waitForSecs(int seconds){
	
	try {
		Thread.sleep(seconds*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static void signOut(){
	
	
	System.out.println("*****************SignOut*********************");
	// to navigate to menu settings window
	driver.findElement(By.id("com.projectgoth:id/button_icon")).click();


	//Steps to sign out

	//Button Icon: com.projectgoth:id/button_icon
	driver.findElementByAccessibilityId("menu_settings").click();
	//driver.findElement(By.id("com.projectgoth:id/button_icon")).click();

	//Menu Settings: com.projectgoth:id/menu_settings

	//driver.findElement(By.id("com.projectgoth:id/menu_settings"));
	//driver.findElementByAccessibilityId("setting").click();



	//Click on Sign out Link
	//driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.projectgoth:id/menu_settings' and @index='2']")).click();

	//List <WebElement> elmnts = driver.findElement("com.projectgoth:id/label");
	//driver.findElement(By.name("setting")).click();
	// Thread.sleep(500000);

	driver.findElement(By.xpath("//android.widget.TextView[@text='Sign out']")).click();


	
}

public static String getCurrentTimeStamp(){
	
	 java.util.Date date= new java.util.Date();
//	 System.out.println(new Timestamp(date.getTime()));
	 
	 Timestamp ts = new Timestamp(date.getTime());
	 return ts.toString();
}



public static void signIn() throws IOException, InterruptedException{
	

/*
WebDriverWait wait = new WebDriverWait(driver, 30); 
wait.until(ExpectedConditions.elementToBeClickable(By.id("com.projectgoth:id/txt_username")));
*/

//driver.findElement(By.xpath("//android.view.View[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.EditText[1]")).sendKeys("praveenmukilan");
//driver.findElementById("com.projectgoth:id/txt_username").click();
String username = new String(Base64.getDecoder().decode(OR.getProperty("username")));
String password = new String(Base64.getDecoder().decode(OR.getProperty("password")));


//***************************

//alternative approach instead of sendkeys //192.168.56.101:5555
//driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.projectgoth:id/txt_username\")")).click();
//sendKeysUsingADB(username);
//Process user = Runtime.getRuntime().exec("adb -s 192.168.56.101:5555 shell input text "+username);
//ProcessBuilder pb = new ProcessBuilder("adb", "-s", "192.168.56.101:5555", "shell","input", "text", username);
//Process pc = pb.start();
//Thread.sleep(15000);
//pc.waitFor();
//CommandLine enterUser = new CommandLine("/Users/Praveen/APPIUM/android-sdk-macosx/platform-tools/adb");
//enterUser.addArgument("-s",false);
//enterUser.addArguments( "192.168.56.101:5555", false);
//enterUser.addArguments( "shell input text", false);
//enterUser.addArguments(username, false);
////CommandLine enterpass = new CommandLine("adb -s 192.168.56.101:5555 shell input text "+password);
//executor.execute(enterUser, resultHandler);



System.out.println("Done");
//***************************
//driver.findElementByAccessibilityId("txt_username").clear();

//driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.projectgoth:id/txt_username\")")).clear();

//The below code is not working in API level <19
//driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.projectgoth:id/txt_username\")")).sendKeys(username);
//The above code is not working in API level <19

driver.findElementById("com.projectgoth:id/txt_username").sendKeys(username);

//(MobileElement)driver.findElementById(OR.getProperty("username_id")).
//driver.findElementById(OR.getProperty("username_id")).sendKeys(username);

//driver.findElementByAccessibilityId("txt_username").sendKeys(username);

//driver.executeScript("try{var el = document.getElementById('com.projectgoth:id/txt_username');el.value = 'praveenmukilan';return 0;}catch{return 1;}");
//driver.findElement(By.id("com.projectgoth:id/txt_username")).sendKeys("praveenmukilan");
//driver.findElementByAccessibilityId("txt_username").sendKeys("praveenmukilan");
//driver.findElement(By.xpath("//android.view.View[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.EditText[2]")).sendKeys("60se!inMS");
//driver.findElementById(OR.getProperty("password_id")).sendKeys(password);
//driver.findElementByAccessibilityId("txt_password").sendKeys(password);

//The below code is not working in API level <19  -- added 16Feb2015
//driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.projectgoth:id/txt_password\")")).sendKeys(password);
//The above code is not working in API level <19  -- added 16Feb2015

driver.findElementById("com.projectgoth:id/txt_password").sendKeys(password);

//MobileElement me = (MobileElement)driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.projectgoth:id/txt_password\")"));
//me.click();
//sendKeysUsingADB(password);
//***************************

//alternative approach instead of sendkeys //192.168.56.101:5555
//driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.projectgoth:id/txt_password\")")).click();
//Process user = Runtime.getRuntime().exec("adb -s 192.168.56.101:5555 shell input text "+username);
//System.out.println("password : "+password);
//ProcessBuilder pbPass = new ProcessBuilder("adb", "-s", "192.168.56.101:5555", "shell", "input","text", "60se!inMS");
//Process pcPass = pbPass.start();
//
//Thread.sleep(15000);
////pcPass.waitFor();


System.out.println("Done");
//***************************



/*
MobileElement password = (MobileElement)driver.findElement(By.id("com.projectgoth:id/txt_password"));
password.setValue("60se!inMS");
*/
//driver.findElement(By.xpath("//android.view.View[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.Button[2]")).click();
driver.findElement(By.id("com.projectgoth:id/btn_signin")).click();

//driver.findElement(By.id("com.projectgoth:id/txt_username"));
//driver.findElement(MobileBy.ByAndroidUIAutomator.)
//WebDriverWait wait = new WebDriverWait(driver, 180);
Thread.sleep(20000);
	
}

public static void sendKeysUsingADB(String textString) throws ExecuteException, IOException{
	
	CommandLine enterpass = new CommandLine("/bin/sh -c");
	 enterpass.addArgument(" /Users/Praveen/APPIUM/android-sdk-macosx/platform-tools/adb -s 192.168.56.101:5555 shell input text "+textString);
	//CommandLine enterpass = new CommandLine("adb -s 192.168.56.101:5555 shell input text "+password);
	executor.execute(enterpass, resultHandler);
}


/*
 * Method to launch the emulator programmatically
 * */

public static void launchEmulator() throws ExecuteException, IOException, InterruptedException{
	
	
	System.out.println("****************launchEmulator Starts****************");
	
	CommandLine launchEmul = new CommandLine("/Applications/Genymotion.app/Contents/MacOS/player");

	


	launchEmul.addArgument("--vm-name", false);
	launchEmul.addArgument("SamsungGalaxyS4");
	
	  

	executor.setExitValue(1);
	
	executor.execute(launchEmul, resultHandler);
	Thread.sleep(20000);
	
	System.out.println("****************launchEmulator Ends****************");
}

private static void killNodeAdbPlayer() throws ExecuteException, IOException, Exception{
	System.out.println("****************kill Node Adb Player Starts****************");
	
	CommandLine killNode = new CommandLine("kill -9 $(lsof -i | grep 6723 | awk '{print $2}')");
	CommandLine killPlayer = new CommandLine("kill -9 $(lsof -i | grep 6724 | awk '{print $2}')");

	executor.setExitValue(1);
	executor.execute(killNode,resultHandler);
	executor.execute(killPlayer,resultHandler);

	System.out.println("****************kill Node Adb Player Ends****************");
}


public static void startAppium() {
    //start appium instance
    try {
    	System.out.println("*************Start Appium*****************");
        Thread.sleep((long)(Math.random() * 10000)); //wait from 0 to 10 sec for parallel process run
        ProcessBuilder builder = new ProcessBuilder(getCmd());
        
//        builder.redirectOutput("path to log file"); //here you can find logs of appium
//        builder.redirectErrorStream(true);
        appium = builder.start();
        Thread.sleep(3000); //wait 3 sec until server started
        System.out.println("Server Started");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
private static List<String> getCmd(){
//create cmd by adding each param
List<String>  cmd = new ArrayList<String>();

cmd.add("/Applications/Appium.app/Contents/Resources/node/bin/node");

cmd.add("/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js");
cmd.add("--address");
cmd.add("127.0.0.1");
cmd.add("--port");
cmd.add("6723");
cmd.add("--bootstrap-port");
cmd.add("6724 ");


cmd.add(" --log");
cmd.add(" /Users/Praveen/APPIUM/AppiumServer.log");
//cmd.append("--webhook");
//cmd.append("localhost:9876");
cmd.add(" --log-timestamp");
cmd.add(" --local-timezone");

StringBuffer sb = new StringBuffer();

sb.append("/Applications/Appium.app/Contents/Resources/node/bin/node ");

sb.append("/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js ");
sb.append("--address ");
sb.append("127.0.0.1 ");
sb.append("--port ");
sb.append("6723 ");
sb.append("--bootstrap-port ");
sb.append("6724 ");


sb.append(" --log ");
sb.append(" /Users/Praveen/APPIUM/AppiumServer.log ");
//cmd.append("--webhook");
//cmd.append("localhost:9876");
sb.append(" --log-timestamp ");
sb.append(" --local-timezone ");

System.out.println("Command : "+sb.toString());

return cmd;
}

public static void stopAppium(){
	System.out.println("****************Stop Appium****************");
//stop appium instance
appium.destroy();
}

/*

public static void main(String args[]){
	
	
	try{
		
		System.out.println("****************main Starts****************");
		killNodeAdbPlayer();
	launchEmulator();
//	Thread.sleep(20000);
	setUp();
	test01();
	tearDown();
	stopAppium();
	System.out.println("****************main Ends****************");
	}
	catch(Exception e){
		
		System.out.println(e);
	}

	
}

*/
	
}