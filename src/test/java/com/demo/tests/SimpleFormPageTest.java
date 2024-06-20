package com.demo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.demo.base.BaseTest;
import com.demo.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;



@Epic("EPIC - 100: Desgin of the home page for demo project")
@Story("US - 200: implement home page features for demo project")
public class SimpleFormPageTest extends BaseTest {
	
	
	
//	@Description("simple form page title test......")
//	@Severity(SeverityLevel.NORMAL)
	@Test
	public void simpleFormLinkTest() {
		simpleFormPage = homePage.clickOnSimpleFormLink();
		String pageTitle = simpleFormPage.getPageTitle();
		System.out.println("Simple Form Page Title is: " + pageTitle);
		Assert.assertEquals(pageTitle, AppConstants.SIMPLE_FORM_PAGE_TITLE);		
	}
	
	
	
	@Description("Inpput Text validation text......")
	@Severity(SeverityLevel.NORMAL)
	@Test(dependsOnMethods = {"simpleFormLinkTest"})
	public void inputTextValidationTest() {
		simpleFormPage.typeTextIntoEnterMessageField(AppConstants.TEXT_MESSAGE);
		simpleFormPage.clickOnGetCheckedValueButton();
		String outputText = simpleFormPage.retrieveEnteredText();
		Assert.assertEquals(AppConstants.TEXT_MESSAGE, outputText);
	}
	
	
	
	
}
