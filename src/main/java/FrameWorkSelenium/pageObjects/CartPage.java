package FrameWorkSelenium.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	@FindBy(css = "li[class='totalRow'] button")
	WebElement checkoutEle;

	public Boolean VerifyProductDisplay(String productName) {

		Boolean match = cartProducts.stream().anyMatch(s -> s.getText().equals(productName));
		return match;
	}

	public CheckOutPage goToCheckoutPage() {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutEle);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutEle);
		
		return new CheckOutPage(driver);
	}

}
