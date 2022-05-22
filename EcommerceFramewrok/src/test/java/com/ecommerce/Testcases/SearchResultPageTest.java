package com.ecommerce.Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ecommerce.Base.BasePage;
import com.ecommerce.DataProvider.DataProviders;
import com.ecommerce.PageObjects.IndexPage;
import com.ecommerce.PageObjects.SearchResultPage;
import com.ecommerce.utility.Logs;

public class SearchResultPageTest extends BasePage {

	private IndexPage index;
	private SearchResultPage searchResultPage;
	
	
	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup(String browser) {
		launchApp(browser); 
	}
	
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(groups = "Smoke",dataProvider = "searchProduct", dataProviderClass = DataProviders.class)
	public void productAvailabilityTest(String productName) throws Throwable {
		Logs.startTestCase("productAvailabilityTest");
		index= new IndexPage();
		searchResultPage=index.searchProduct(productName);
		boolean result=searchResultPage.isProductAvailable();
		Assert.assertTrue(result);
		Logs.endTestCase("productAvailabilityTest");
	}
}
