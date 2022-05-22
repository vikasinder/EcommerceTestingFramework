package com.ecommerce.Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ecommerce.Base.BasePage;
import com.ecommerce.PageObjects.IndexPage;
import com.ecommerce.utility.Logs;

public class IndexPageTest extends BasePage {

	private IndexPage indexPage;
    
	//@Parameters("browser")
	
	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup(String browser) {
		launchApp(browser); 
	}
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	@Test(groups = "Smoke")
	public void verifyLogo() throws Throwable {
		Logs.startTestCase("verifyLogo");
		indexPage= new IndexPage();
		boolean result=indexPage.validateLogo();
		Assert.assertTrue(result);
		Logs.endTestCase("verifyLogo");
	}
	
	@Test(groups = "Smoke")
	public void verifyTitle() {
		Logs.startTestCase("verifyTitle");
		String actTitle=indexPage.getMyStoreTitle();
		Assert.assertEquals(actTitle, "My Store1");
		Logs.endTestCase("verifyTitle");
	}
	
	
	



}
