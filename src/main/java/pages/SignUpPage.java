package pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethod;

public class SignUpPage extends ProjectSpecificationMethod{
	
	WebDriver driver;
    String status;
    String messageAlert1="Sign up successful.";
    String messageAlert2="This user already exist.";
    boolean isAlertValid;
    
    //driver initialization
	public SignUpPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); //driver indicates parent driver ; this indicates current class obj
	}
	
	@FindBy(id="sign-username")
	WebElement element_UserName;
	
	@FindBy(id="sign-password")
	WebElement element_Password;
	
	@FindBy(xpath="//div/button[text()='Sign up']")
	WebElement element_SignUpBtn;
	
	@FindBy(xpath="//div/button[text()='Sign up']/preceding-sibling::button")
	WebElement element_CloseBtn;
	
	public boolean enterCredentials(String emailID, String password, boolean alertPresent) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element_UserName));
		System.out.println("Email ID: "+emailID+" ; Password: "+password);
		
		element_UserName.clear();
		element_UserName.sendKeys(emailID); //send value to the email Id field
		
		element_Password.clear();
		element_Password.sendKeys(password); //send value to the password field
		
		element_SignUpBtn.click();	
		boolean isAlertAppeared = handleAlert(alertPresent);
		
		//checks for the alert message is valid. If valid set true
		if(isAlertAppeared) {
			String alertText = driver.switchTo().alert().getText();
			System.out.println(alertText);
			if(alertText.equals(messageAlert1) || alertText.equals(messageAlert2))
			{
				isAlertValid=true;
				driver.switchTo().alert().accept();
			   
			}
			else {
				driver.switchTo().alert().accept();
			    element_CloseBtn.click();
			}
		}
		return isAlertValid;
	
	}

}