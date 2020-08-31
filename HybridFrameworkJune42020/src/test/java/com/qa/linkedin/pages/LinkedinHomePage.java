package com.qa.linkedin.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.qa.linkedin.base.TestBase;

public class LinkedinHomePage extends TestBase{
	
private Logger log=Logger.getLogger(LinkedinHomePage.class);
private String loginPageTitle="LinkedIn Login, Sign in | LinkedIn";
 public LinkedinHomePage() {
	 PageFactory.initElements(driver, this);
 }
 
 
  
 @FindBy(xpath="//*[@class='nav__logo-link']")
 private WebElement linkedInLogo;
 @FindBy(css="a.nav__button-secondary")
 private WebElement signInLink;
 @FindBy(xpath="//h1[text()='Welcome Back']")
 private WebElement welcomeBackHeading ;
 @FindBy(id="username")
 private WebElement emailEditbox;
 @FindBy(name="session_password")
 private WebElement passwordEditbox;
 @FindBy(xpath="//*[@type='submit' and @aria-label='Sign in']")
 private WebElement signInBtn;

 public void verifyLinkedinHomePageTitle() {
 	log.debug("wait for the linkedin home page title");
 	wait.until(ExpectedConditions.titleContains("LinkedIn: Log In or Sign Up"));
 	Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up");
 	
 }
 public void verifyLinkedInLogo(){
 	log.debug("wait and verify the linkedin logo in homepage");
 	wait.until(ExpectedConditions.visibilityOf(linkedInLogo));
 	Assert.assertTrue( linkedInLogo.isDisplayed(),"Linkedin homepage not displayed");
 	
 }
 public void clickSignInLink() {
	 
  log.debug("check if signin link is visible and click on it");
 	if(signInLink.isDisplayed()&&signInLink.isEnabled()){
 	wait.until(ExpectedConditions.elementToBeClickable(signInLink));
 	signInLink.click();
 	}
 }
 public void verifyWelcomeBackHeaderText(){
	 	log.debug("wait and verify the Welcome Back heading");
	 	wait.until(ExpectedConditions.visibilityOf(welcomeBackHeading));
	 	Assert.assertTrue(welcomeBackHeading.isDisplayed(),"Welcome Back heading is not present");
	 	
	 }
 public void verifyLinkedinLoginPageTitle(){
	 	log.debug("wait and verify the linkedin logo in homepage");
	 	wait.until(ExpectedConditions.titleContains(loginPageTitle));
	 	Assert.assertEquals(driver.getTitle(),loginPageTitle);
	 	
	 }
 
 public void clickSignInBtn() {
	 if(signInBtn.isDisplayed()&&signInBtn.isEnabled()){
		 	wait.until(ExpectedConditions.elementToBeClickable(signInBtn));
		 	signInBtn.click();
		 	}
 }
 public LinkedinLoggedinPage doLogin(String email,String pwd) throws IOException, InterruptedException{
	  wait.until(ExpectedConditions.visibilityOf(emailEditbox));
	 	log.debug("started executing the doLogin()");
	 	Thread.sleep(4000);
	 	log.debug("clear the content of email editbox");
	 	emailEditbox.clear();
	 	Thread.sleep(4000);
	 	log.debug("type the email--"+email);
	 	emailEditbox.sendKeys(email);
	 	Thread.sleep(4000);
	 	log.debug("clear the content of password editbox");
	 	passwordEditbox.clear();
	 	log.debug("type the password--"+pwd);
	 	passwordEditbox.sendKeys(pwd);
	 	log.debug("perform click on sign in button");
	 	clickSignInBtn();
	 	return new LinkedinLoggedinPage();
	 	
	 }


}
