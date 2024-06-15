package com.demo.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.demo.utils.ElementUtil;

import io.qameta.allure.Step;


public class HomePage {

	WebDriver driver;
	private ElementUtil eleUtil;

	// 1. private By locators - page locators
	private By simpleFormLink = By.linkText("Simple Form Demo");



	public HomePage(WebDriver driver) {
		
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	@Step("----Clicking on the Simple Form Demo link----")
	public SimpleFormPage clickOnSimpleFormLink() {
		eleUtil.doClick(simpleFormLink);
		return new SimpleFormPage(driver);
	}

}
