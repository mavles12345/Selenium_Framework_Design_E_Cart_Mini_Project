package com.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abstractcomponets.AbstractComponents;

public class CartPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		
		super(driver);
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
			
	}
	
	@FindBy(css=".totalRow button[class='btn btn-primary']")
	WebElement checkoutTitle;
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cartProducts;
	
	public boolean verifyProductDisplay(String productName) {
		
		boolean match = cartProducts.stream().anyMatch(cartitem -> cartitem.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goToCheckOut() {
		
		checkoutTitle.click();
		
		return new CheckOutPage(driver);
	}
	
		

}
