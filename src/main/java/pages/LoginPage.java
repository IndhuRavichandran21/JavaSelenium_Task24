package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethod;

public class LoginPage extends ProjectSpecificationMethod{
	WebDriver driver;
	String result;
	
	//driver initialization
	public LoginPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="loginusername")
	WebElement loginElement_UserName;
	
	@FindBy(id="loginpassword")
	WebElement loginElement_Password;
	
	@FindBy(xpath="//div/button[text()='Log in']")
	WebElement element_LoginBtn;
	
	@FindBy(id="nameofuser")
	WebElement element_UserProfile;
	
	@FindBy(xpath="//div/button[text()='Log in']/preceding-sibling::button")
	WebElement closeLoginWindow;
	
	public String login(String emailID, String password, boolean alertPresent) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(loginElement_UserName));
		
		System.out.println("Email ID: "+emailID+" ; Password: "+password);
		
		loginElement_UserName.clear();
		loginElement_UserName.sendKeys(emailID); //send value to the email Id field
		
		loginElement_Password.clear();
		loginElement_Password.sendKeys(password); //send value to the password field
		
		element_LoginBtn.click(); //click Login btn
		
		boolean isAlertAppeared = handleAlert(alertPresent);
		
		//if alert appeared print the message and click ok and close the login window
		if(isAlertAppeared) {
			System.out.println("Alert message is "+driver.switchTo().alert().getText()); 
			driver.switchTo().alert().accept(); 
			closeLoginWindow.click(); 
		}
		
		//if alert doesnot appear, check for the profile to be displayed and print login successful
		if(element_UserProfile.isDisplayed()) {
			 result =   "Login Successful";
			System.out.println(result);
		}
		 
		else {
			 result =   "Login not Successful";
			System.out.println(result);
		}
		
		return result;
			
	}

}

