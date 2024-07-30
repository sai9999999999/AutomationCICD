package FrameWorkSelenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.TakesScreenshot;

import testComponents.BaseClass;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.*;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import FrameWorkSelenium.pageObjects.*;

public class SubmitOrderTest extends BaseClass {

	// String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "submitOrder" })
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalog.getProductList();

		Thread.sleep(5000);

		WebElement prod = productCatalog.getProductName(input.get("product"));

		// wait.until(ExpectedConditions.elementToBeClickable(prod.findElement(By.cssSelector(".card-body
		// button:last-of-type"))));

		productCatalog.addProductToCart(input.get("product"));

		CartPage cartPage = productCatalog.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));

		Assert.assertTrue(match);

		CheckOutPage checkOutPage = cartPage.goToCheckoutPage();

		checkOutPage.selectCountry("India", "999", "sai");

		ConfirmationPage confirmationPage = checkOutPage.submitOrder();

		String message = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));

	}

	@Test(dependsOnMethods = { "SubmitOrder" })
	public void orderHistoryTest() {
		String productName = "ZARA COAT 3";

		ProductCatalog productCatalog = landingPage.loginApplication("homsai@gmail.com", "Selenium@2024");

		OrderPage orderPage = productCatalog.goToOrderPage();
		Assert.assertTrue(orderPage.VerifyorderDisplay(productName));

		System.out.println("succesfull");

	}

	
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//data//submitOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "homsai@gmail.com");
//	map.put("password", "Selenium@2024");
//	map.put("product", "ZARA COAT 3");
//
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "homsai@gmail.com");
//	map1.put("password", "Selenium@2024");
//	map1.put("product", "ADIDAS ORIGINAL");
}
