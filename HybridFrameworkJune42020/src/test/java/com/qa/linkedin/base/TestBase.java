package com.qa.linkedin.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.log4testng.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static WebDriverWait wait;
	private static Logger log = Logger.getLogger(TestBase.class);

	public static String readProperty(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\config\\config.properties");
		prop.load(fis);
		log.debug("read the given property value");
		return prop.getProperty(key);
	}

	@BeforeSuite
	public void setUp() throws IOException {
		String browser = readProperty("browser");
		log.debug("launching the browser :" + browser);
		log.debug("launching the chrome browser");
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			log.debug("launching the firefox browser");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("IE")) {
			log.debug("launching the ie browser");
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			log.debug("launching the edge browser");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		log.debug("launching the application url : " + readProperty("url"));
		driver.get(readProperty("url"));
		log.debug("add implicitwait time :");
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(readProperty("implicitWait")), TimeUnit.SECONDS);
		log.debug("create object for WebDriverWait");
		wait = new WebDriverWait(driver, Long.parseLong(readProperty("explicitWait")));
	}

	@AfterSuite
	public void tearDown() {
		log.debug("closing the browser --after all my testcase execution ");
		if (driver != null) {
			driver.close();
		}
	}
}
