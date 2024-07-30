package FrameWorkSelenium;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import FrameWorkSelenium.pageObjects.*;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.edgedriver().setup();

		WebDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage = new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("homsai@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Selenium@2024");
		driver.findElement(By.id("login")).click();
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);
		
		Thread.sleep(5000);

		wait.until(ExpectedConditions.elementToBeClickable(prod.findElement(By.cssSelector(".card-body button:last-of-type"))));
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
				
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("button[routerlink*='/dashboard/cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean match = cartProducts.stream().anyMatch(s -> s.getText().equals("ZARA COAT 3"));
		Assert.assertTrue(match);
		
		//driver.findElement(By.cssSelector(".totalRow button")).click();
		 WebElement totalRowButton = driver.findElement(By.cssSelector("li[class='totalRow'] button"));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", totalRowButton);
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", totalRowButton);
	        
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.xpath("//div[text()='CVV Code ']/following-sibling::input")), "999")
				.build().perform();
		action.sendKeys(driver.findElement(By.xpath("//div[text()='Name on Card ']/following-sibling::input")), "sai")
				.build().perform();
		action.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build()
				.perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector(".list-group-item"))));
		action.sendKeys(driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]"))).click().build()
				.perform();

		driver.findElement(By.className("action__submit")).click();
		String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));

		driver.close();

	}

}
