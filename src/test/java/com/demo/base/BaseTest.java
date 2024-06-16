package com.demo.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.demo.factory.DriverFactory;
import com.demo.pages.HomePage;
import com.demo.pages.SimpleFormPage;

public class BaseTest {
	
	protected DriverFactory df;
	protected WebDriver driver;
	protected Properties prop;
	
	protected HomePage homePage;
	protected SimpleFormPage simpleFormPage;
	
	
		
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) {
		df = new DriverFactory();
		prop = df.initProp();
		
		driver = df.initDriver(prop, browserName);
		homePage = new HomePage(driver);
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
