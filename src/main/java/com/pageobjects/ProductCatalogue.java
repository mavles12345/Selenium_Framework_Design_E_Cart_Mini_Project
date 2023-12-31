package com.pageobjects;

import java.util.Collection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abstractcomponets.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {

		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animated")
	WebElement spinner;
	

	By productsBy = By.cssSelector(".mb-3");

	By addToCart = By.cssSelector(".card-body button:last-of-type");// this is not from driver, so using this for prod
	
	By toast_message=By.cssSelector("#toast-container");
	
	

	public List<WebElement> getProductsList() {

		WaitforElementToAppear(productsBy);

		return products;
	}

	//Get all products through filter
	public WebElement getProductByName(String productname) {

		// getProductsList

		WebElement prod = getProductsList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst()
				.orElse(null);

		return prod;
	}

	//get required products
	public void addProductsToCart(String productName) {

		WebElement prod = getProductByName(productName);

		prod.findElement(addToCart).click();
		
		WaitforElementToAppear(toast_message);
		
		WaitforElementToDisAppear(spinner);

	}

	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
}
