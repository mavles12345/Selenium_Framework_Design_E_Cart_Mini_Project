package com.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abstractcomponets.AbstractComponents;

public class OrderPage extends AbstractComponents{
	
WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		
		super(driver);
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
			
	}
	
	@FindBy(css="table tr td:nth-child(3)")
	List<WebElement> productsNames;
	
	public boolean verifyOrderDisplay(String productName) {
		
		boolean match = productsNames.stream().anyMatch(cartitem -> cartitem.getText().equalsIgnoreCase(productName));
		return match;
	}
	
			


}
