package com.qa.linkedin.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.qa.linkedin.base.TestBase;

public class LinkedinLoggedinPage extends TestBase{
	private Logger log=Logger.getLogger(LinkedinLoggedinPage.class);
public LinkedinLoggedinPage() {
	PageFactory.initElements(driver, this);
}

@FindBy(xpath="//div[contains(@class,'profile-rail-card')]")
private WebElement profileRailCard;

@FindBy(xpath="//input[@placeholder='Search' and @type='text']")
private WebElement searchEditbox;

@FindBy(xpath="//*[contains(@class,'nav-item__profile-member')]")
private WebElement profileIcon;

@FindBy(xpath="//*[contains(@data-control-name,'nav.settings_signout')]")
private WebElement signOutLink;

public void verifyProfileRailCard(){
 	log.debug("wait and verify the profile image in the linkedin loggedin pagepage");
 	wait.until(ExpectedConditions.visibilityOf(profileRailCard));
 	Assert.assertTrue(profileRailCard.isDisplayed(),"Loggedin page is not displayed");
 	 }
public SearchResultsPage doPeopleSearch(String keyword) throws InterruptedException {
	log.debug("started executing the doPeopleSearch() for "+keyword);
	log.debug("clear the content of search editbox");
	searchEditbox.clear();
	log.debug("type the search keyword in the search editbox--"+keyword);
	searchEditbox.sendKeys(keyword);
	log.debug("press the enter key on search editbox");
	searchEditbox.sendKeys(Keys.ENTER);
	Thread.sleep(2000);
	return new SearchResultsPage();
	}
public void doLogOut() {
	log.debug("wait for the profile image icon");
	wait.until(ExpectedConditions.visibilityOf(profileIcon));
	log.debug("click on profile icon");
	profileIcon.click();
	wait.until(ExpectedConditions.visibilityOf(signOutLink));
	log.debug("click on signOutLink");
	signOutLink.click();
}




}
