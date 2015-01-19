package com.autome.web;

import java.io.File;
import java.util.Base64;

import org.migme.test.automation.framework.keywordmodel.keywords.KeywordBase;
import org.migme.test.automation.framework.locator.CustomPageFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class MigKeyWords extends KeywordBase {
	
	private WebDriver driver;
	private MigKeywordLocator mkl;
	
	public MigKeyWords(WebDriver driver){
		
		System.out.println("*************MigKeyWords constructor************");
		
		this.driver = driver;
		File file = new File("src/resources/OR.properties");
		mkl = CustomPageFactory.initElements(driver, MigKeywordLocator.class, file);		

	}
	
	
	public void signInMigMe(String URL){
		System.out.println("*************MigKeyWords signInMigMe************");
		String username=new String(Base64.getDecoder().decode(MyTestSuite.webprop.getProperty("username")));
		String password=new String(Base64.getDecoder().decode(MyTestSuite.webprop.getProperty("password")));
		this.driver.get(URL);
		mkl.lnk_SignIn.click();
		mkl.txt_UserName.sendKeys(username);
		mkl.txt_Password.sendKeys(password);
		mkl.btn_SignIn.click();
		
	}
	
	public void logout(){
		
		mkl.dpd_Logout.click();
		mkl.optLogout.click();
		
	}
	
	public void waitForSeconds(long time){
		
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	
	public void navigateToUrl(String url){
        this.driver.get(url);
    }
     
    ///
     / Searches for a string on google home page
     / @param searchString
     //
    public void searchForString(String searchString){
        mkl.searchField.sendKeys(searchString);
        mkl.submitButton.click();
        mkl.
    }
     
    ///
     / Clicks on the first result in the google results list.
     //
    public void clickOnResult(){
        clickOnResult(0);
    }
     
    ///
     / Clicks on a particular item number in the list.
     / @param index
     //
    public void clickOnResult(int index){
        mkl.searchResult.get(index).click();
    }
     
    ///
     / Waits for the results to be displayed.
     //
    public void waitForResultsToDisplay(){
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                 
                return mkl.searchResult.size()>0;
            }
 
            @Override
            public String toString() {
                return "Condition not fullfilled";
            }
        }
        );
    }
     
    ///
     / Wait for the said title to appear on the page.
     / @param title
     //
    public void waitForTitle(String title){
        WebDriverWait wait = new WebDriverWait(this.driver, 60);
        wait.until(ExpectedConditions.titleContains("Software testing"));
    }
	
	
	*/

}
