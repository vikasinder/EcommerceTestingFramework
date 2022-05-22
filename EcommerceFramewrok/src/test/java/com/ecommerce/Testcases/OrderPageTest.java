package com.ecommerce.Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ecommerce.Base.BasePage;
import com.ecommerce.DataProvider.DataProviders;
import com.ecommerce.PageObjects.AddToCartPage;
import com.ecommerce.PageObjects.IndexPage;
import com.ecommerce.PageObjects.OrderPage;
import com.ecommerce.PageObjects.SearchResultPage;
import com.ecommerce.utility.Logs;

public class OrderPageTest extends BasePage {
	
	private IndexPage index;
	private SearchResultPage searchResultPage;
	private AddToCartPage addToCartPage;
	private OrderPage orderPage;

	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup(String browser) {
		launchApp(browser); 
	}
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(groups = "Regression",dataProvider = "getProduct", dataProviderClass = DataProviders.class)
	public void verifyTotalPrice(String productName, String qty, String size) throws Throwable {
		Logs.startTestCase("verifyTotalPrice");
		index= new IndexPage();
		searchResultPage=index.searchProduct(productName);
		addToCartPage=searchResultPage.clickOnProduct();
		addToCartPage.enterQuantity(qty);
		addToCartPage.selectSize(size);
		addToCartPage.clickOnAddToCart();
		orderPage=addToCartPage.clickOnCheckOut();
		Double unitPrice=orderPage.getUnitPrice();
		Double totalPrice=orderPage.getTotalPrice();
		Double totalExpectedPrice=(unitPrice*(Double.parseDouble(qty)))+2;
		Assert.assertEquals(totalPrice, totalExpectedPrice);
		Logs.endTestCase("verifyTotalPrice");
	}

}
