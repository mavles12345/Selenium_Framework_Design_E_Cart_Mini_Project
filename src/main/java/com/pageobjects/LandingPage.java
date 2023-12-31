package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abstractcomponets.AbstractComponents;

public class LandingPage extends AbstractComponents {

	public LandingPage(WebDriver driver) {

		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "userEmail")

	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCatalogue loginApplication(String email, String password) {

		userEmail.sendKeys(email);

		userPassword.sendKeys(password);

		login.submit();

		ProductCatalogue products = new ProductCatalogue(driver);

		return products;

	}

	public void goTo() {

		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getErrorMessage() {
		
		WaitforWebElementToAppear(errorMessage);
		 
		return errorMessage.getText();
	}

}
