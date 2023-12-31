package com.abstractcomponets;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pageobjects.CartPage;
import com.pageobjects.OrderPage;

public class AbstractComponents {

	public WebDriver driver;

	public AbstractComponents(WebDriver driver) {

		this.driver = driver;

	}

	@FindBy(css = "button[routerlink*=cart]")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink='/dashboard/myorders']")
	WebElement orderHeader;

	public void WaitforElementToAppear(By locator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	
	public void WaitforWebElementToAppear(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}


	public void WaitforElementToDisAppear(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));

	}

	public CartPage goToCart() {

		cartHeader.click();

		CartPage cart = new CartPage(driver);

		return cart;

	}
	
	public OrderPage goToOrders() {
		
		orderHeader.click();
		
		OrderPage orderpage=new OrderPage(driver);
		
		return orderpage;
		
		
	}

}
