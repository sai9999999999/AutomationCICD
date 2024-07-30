package abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import FrameWorkSelenium.pageObjects.CartPage;
import FrameWorkSelenium.pageObjects.OrderPage;

public class AbstractComponents {

	WebDriver driver;
    WebDriverWait wait;


	public AbstractComponents(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		
	}

	@FindBy(css = "button[routerlink*='/dashboard/cart']")
	WebElement cartPage;

	@FindBy(css = "button[routerlink*='myorders']")
	WebElement orderPage;


	public void waitForElementToBeVisible(By findBy) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	
	
	public void waitForElementToBeVisible(WebElement findBy) {

		wait.until(ExpectedConditions.visibilityOf(findBy));

	}

	public void waitForElementInvisible(WebElement ele) {

		wait.until(ExpectedConditions.invisibilityOf(ele));

	}

	public CartPage goToCartPage() {

		cartPage.click();
		CartPage cartPage = new CartPage(driver);

		return cartPage;

	}
	
	public OrderPage goToOrderPage() {

		orderPage.click();
		OrderPage orderPage = new OrderPage(driver);

		return orderPage;

	}
	
	

}
