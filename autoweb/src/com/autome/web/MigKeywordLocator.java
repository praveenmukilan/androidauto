package com.autome.web;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MigKeywordLocator {

	 @FindBy(how=How.CSS,using="URL") 
	public String URL;

	 @FindBy(how=How.CSS,using="lnk_SignIn") 
	public WebElement lnk_SignIn;

	 @FindBy(how=How.CSS,using="txt_UserName") 
	public WebElement txt_UserName;

	 @FindBy(how=How.CSS,using="txt_Password") 
	public WebElement txt_Password;

	 @FindBy(how=How.CSS,using="btn_SignIn") 
	public WebElement btn_SignIn;

	 @FindBy(how=How.CSS,using="txt_newPost") 
	public WebElement txt_newPost;

	 @FindBy(how=How.CSS,using="drpdwnPostPrivacy") 
	public WebElement drpdwnPostPrivacy;

	 @FindBy(how=How.CSS,using="rdPublic") 
	public WebElement rdPublic;

	 @FindBy(how=How.CSS,using="rdFriends") 
	public WebElement rdFriends;

	 @FindBy(how=How.CSS,using="ckbAllowReplies") 
	public WebElement ckbAllowReplies;

	 @FindBy(how=How.CSS,using="btnpostboxShare") 
	public WebElement btnpostboxShare;

	 @FindBy(how=How.CSS,using="mentionsPage") 
	public WebElement mentionsPage;

	 @FindBy(how=How.CSS,using="watchlistPage") 
	public WebElement watchlistPage;

	 @FindBy(how=How.CSS,using="stories") 
	public WebElement stories;

	 @FindBy(how=How.CSS,using="contests") 
	public WebElement contests;

	 @FindBy(how=How.CSS,using="people") 
	public WebElement people;

	 @FindBy(how=How.CSS,using="updates") 
	public WebElement updates;

	 @FindBy(how=How.CSS,using="dpd_Logout") 
	public WebElement dpd_Logout;

	 @FindBy(how=How.CSS,using="optLogout") 
	public WebElement optLogout;



}
