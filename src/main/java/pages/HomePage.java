package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ProjectSpecificationMethod;

public class HomePage extends ProjectSpecificationMethod{
	WebDriver driver;
	
	//driver initialization
	public HomePage(WebDriver driver) {
		this.driver= driver;
		
		PageFactory.initElements(driver, this); 
	}
	
	//Page Factory
	@FindBy (id = "signin2")
	WebElement element_SignUp;
	
	@FindBy (id = "login2")
	WebElement element_Login;
	
	@FindBy(id="logout2")
	WebElement element_LogOut;
	
	public void signUpNewUser() {
		
		element_SignUp.click(); //click signUp btn
		System.out.println("Enter the credential for Sign Up");	
		
	}

	public void logInUser() {
		
		element_Login.click(); //click login btn
		System.out.println("Enter the credential for Login");
	}
	
	public void logOutUser() {
		
		element_LogOut.click(); //click logout btn
		System.out.println("The user has been logged out");
	}


}

