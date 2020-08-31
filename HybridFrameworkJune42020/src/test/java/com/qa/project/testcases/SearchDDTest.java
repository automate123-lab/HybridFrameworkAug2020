package com.qa.project.testcases;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedinHomePage;
import com.qa.linkedin.pages.LinkedinLoggedinPage;
import com.qa.linkedin.pages.SearchResultsPage;
import com.qa.linkedin.util.ExcelUtil;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.io.IOException;

import org.testng.annotations.AfterClass;

public class SearchDDTest extends TestBase{
	private Logger log=Logger.getLogger(SearchDDTest.class);
	private LinkedinHomePage lhmpg=null;
	private LinkedinLoggedinPage llpg=null;
	private SearchResultsPage srpg=null;
	private String excelpath=System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\data\\LinkedinTestData.xlsx";
	
 @DataProvider
 public Object[][] testData() throws IOException{
	 //create 2 dimensional object array
	 Object[][] data=new ExcelUtil().getTestData(excelpath, "Sheet1");
	 return data;
 }
	
  @Test(dataProvider="testData")
  public void searchPeopleTest(String keyword) throws InterruptedException {
	  log.debug("started executing datadriven test");
	  log.debug("started executing the searchPeopleTest...");
	  srpg=llpg.doPeopleSearch(keyword);
	  log.debug("fetch the results count for: "+keyword);
	  long count=srpg.getResultsCount();
	  log.debug("The results count for "+keyword+" is :"+count);
	  log.debug("click on home tab");
	  srpg.clickHomeTab();
  }
  @BeforeClass
  public void beforeClass() throws IOException, InterruptedException {
	  log.debug("initializing the page class objects");
	  lhmpg=new LinkedinHomePage();
	  llpg=new LinkedinLoggedinPage();
	  srpg=new SearchResultsPage();
	  log.debug("verifying the linkedin home page title");
	  lhmpg.verifyLinkedinHomePageTitle();
	  log.debug("verifying the linkedin logo");
	  lhmpg.verifyLinkedInLogo();
	  log.debug("click on sign in link");
	  lhmpg.clickSignInLink();
	  lhmpg.verifyLinkedinLoginPageTitle();
	  lhmpg.verifyWelcomeBackHeaderText();
	  log.debug("perform login once only");
	  llpg=lhmpg.doLogin(readProperty("email"), readProperty("pwd"));
	  
  }

  @AfterClass
  public void afterClass() {
	  log.debug("performing the logout operation");
	  llpg.doLogOut();
  }

}
