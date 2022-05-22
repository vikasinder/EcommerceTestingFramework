package com.ecommerce.Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ecommerce.Base.BasePage;
import com.ecommerce.DataProvider.DataProviders;
import com.ecommerce.PageObjects.HomePage;
import com.ecommerce.PageObjects.IndexPage;
import com.ecommerce.PageObjects.LoginPage;
import com.ecommerce.utility.Logs;

public class HomePageTest extends BasePage {
	private IndexPage indexPage;
	private LoginPage loginPage;
	private HomePage homePage;

	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup(String browser) {
		launchApp(browser); 
	}
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(groups = "Smoke",dataProvider = "credentials", dataProviderClass = DataProviders.class)
	public void wishListTest(String uname, String pswd) throws Throwable {
		Logs.startTestCase("wishListTest");
		indexPage= new IndexPage();
		loginPage=indexPage.clickOnSignIn();
		homePage=loginPage.login(uname,pswd,homePage);
		boolean result=homePage.validateMyWishList();
		Assert.assertTrue(result);
		Logs.endTestCase("wishListTest");
	}
	
	@Test(groups = "Smoke",dataProvider = "credentials", dataProviderClass = DataProviders.class)
	public void orderHistoryandDetailsTest(String uname, String pswd) throws Throwable {
		Logs.startTestCase("orderHistoryandDetailsTest");
		indexPage= new IndexPage();
		loginPage=indexPage.clickOnSignIn();
		homePage=loginPage.login(uname,pswd,homePage);
		boolean result=homePage.validateOrderHistory();
		Assert.assertTrue(result);
		Logs.endTestCase("orderHistoryandDetailsTest");
	}

}
