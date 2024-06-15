package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.demo.constants.AppConstants;
import com.demo.utils.ElementUtil;

public class SimpleFormPage {
	
	WebDriver driver;
	ElementUtil eleUtil;
	
	private By enterMessageField = By.id("user-message");
	private By getCheckedValueButton = By.xpath("//button[text()='Get Checked Value']");
	private By outputText = By.xpath(" //p[@id='message']");
	
	
	
	public SimpleFormPage(WebDriver driver) {
		
		this.driver = driver;
		eleUtil = new ElementUtil(driver);	
	}
	
	
	public String getPageTitle() {
		return eleUtil.waitForTitleIs(AppConstants.SIMPLE_FORM_PAGE_TITLE, AppConstants.SHORT_TIME_OUT);
	}
	
	public void typeTextIntoEnterMessageField(String textMessage) {
		eleUtil.doSendKeys(enterMessageField, textMessage);
	}
	
	public void clickOnGetCheckedValueButton() {
		eleUtil.doClick(getCheckedValueButton);
	}
	
	public String retrieveEnteredText() {
		return eleUtil.doElementGetText(outputText);
	}
 
}

