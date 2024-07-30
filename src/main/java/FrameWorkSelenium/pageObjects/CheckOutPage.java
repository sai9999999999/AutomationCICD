package FrameWorkSelenium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[text()='CVV Code ']/following-sibling::input")
	WebElement cvv;

	@FindBy(xpath = "//div[text()='Name on Card ']/following-sibling::input")
	WebElement name;

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	WebElement selectCountry;

	@FindBy(css = ".action__submit")
	WebElement submit;

	By result = By.cssSelector(".list-group-item");

	public void selectCountry(String countryName, String cvvCode, String nameOfPurchaser) {

		Actions action = new Actions(driver);

		action.sendKeys(cvv, cvvCode).build().perform();

		action.sendKeys(name, nameOfPurchaser).build().perform();

		action.sendKeys(country, countryName).build().perform();

		waitForElementToBeVisible(result);

		action.click(selectCountry).build().perform();

	}
	
	public ConfirmationPage submitOrder() {
		
		submit.click();
		return new ConfirmationPage(driver);
	}

}
