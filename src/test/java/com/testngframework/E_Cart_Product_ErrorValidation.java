package com.testngframework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageobjects.CartPage;
import com.pageobjects.CheckOutPage;
import com.pageobjects.ConfirmationPage;
import com.pageobjects.LandingPage;
import com.pageobjects.ProductCatalogue;
import com.sun.net.httpserver.Authenticator.Retry;
import com.testcomponents.BaseTest;

public class E_Cart_Product_ErrorValidation extends BaseTest {

	@Test
	public void loginErrorValidation() throws Throwable {

		

		// BeforeMethod check in base class

		landingPage.loginApplication("jackmavles@gml.com", "Selvam@18");

		landingPage.getErrorMessage();

		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");

	
	}

	@Test
	public void productErrorValidation() {

		String productName = "ZARA COAT 3";

		ProductCatalogue products = landingPage.loginApplication("jackmavles@gmail.com", "Selvam@18"); 
		List<WebElement> product = products.getProductsList();// get all products

		products.addProductsToCart(productName);// get desired products

		CartPage cartPage = products.goToCart();// click cart and returning cartpage in go to cart method.

		boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");// verify the products

		Assert.assertTrue(match);

	}

}
