package com.testngframework;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pageobjects.CartPage;
import com.pageobjects.CheckOutPage;
import com.pageobjects.ConfirmationPage;
import com.pageobjects.LandingPage;
import com.pageobjects.OrderPage;
import com.pageobjects.ProductCatalogue;
import com.testcomponents.BaseTest;

public class E_Cart_Product_Selection_JsonData extends BaseTest {
	
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData")
	public void SubmitOrder(HashMap<String, String> input) throws Throwable {	
	
	ProductCatalogue products = landingPage.loginApplication(input.get("email"), input.get("password")); 

		List<WebElement> product = products.getProductsList();// get all products

		products.addProductsToCart(input.get("productName"));// get desired products

		CartPage cartPage = products.goToCart();// click cart and returning cartpage in go to cart method.
		
		
		boolean match = cartPage.verifyProductDisplay(input.get("productName"));// verify the products

		Assert.assertTrue(match);

		CheckOutPage CheckOutPage = cartPage.goToCheckOut();// used to do payment

		CheckOutPage.selectCountry("Ind");

		ConfirmationPage ConfirmationPage = CheckOutPage.submitOrder();


		String confirmMessage = ConfirmationPage.getConfirmMessage();

		Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
		

	}
	
	@Test(dependsOnMethods="SubmitOrder")
	public void orderHistoryTest() {
		

		
		//BeforeMethod check in base class

		ProductCatalogue products = landingPage.loginApplication("jackmavles@gmail.com", "Selvam@18"); // encapsulate
		OrderPage orderpage=products.goToOrders();
		orderpage.verifyOrderDisplay(productName);
		Assert.assertTrue(orderpage.verifyOrderDisplay(productName));

		
		
	}
	@DataProvider
	public Object[][] getData() throws Throwable {
		
		
		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\data\\purchaseorder.json");
		
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
			
		};

	}


