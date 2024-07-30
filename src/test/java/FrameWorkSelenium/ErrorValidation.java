package FrameWorkSelenium;

import java.io.IOException;

import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import testComponents.Retry;  

import testComponents.BaseClass;

public class ErrorValidation extends BaseClass {

	@Test(groups = {"Errorhandling"}, retryAnalyzer = Retry.class)
	public void ProductErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";

		landingPage.loginApplication("homsai@gmail.com", "Selenium@20245");

		Assert.assertEquals("incorrect email or password", landingPage.getErrorMessage());

	}

}
