package base;

import java.io.IOException;

import org.testng.annotations.*;

import utils.Utility;

public class ProjectSpecificationMethod extends Utility{

	@Parameters({"browser","url"})
	@BeforeMethod
	public void browserLaunch(String browser, String url) {
		launchBrowser(browser, url);
	}
	
	@AfterMethod
	public void browserClose() {
		closeBrowser(); 
	}
	
	//call the method to fetch the data from respective excel sheet
	@DataProvider
	public String [][] Valid_SignUpCredentials() throws IOException{
		
		return userCredentials("Valid_SignUpDetails"); 
	}

	@DataProvider
	public String [][] Invalid_SignUpCredentials() throws IOException{
		
		return userCredentials("Invalid_SignUpDetails"); 
	}

	@DataProvider
	public String [][] Valid_LogInCredentials() throws IOException{
		
		return userCredentials("Valid_LogInDetails");
	}
	
	@DataProvider
	public String [][] Invalid_LogInCredentials() throws IOException{
		
		return userCredentials("Invalid_LogInDetails"); 
	}
	

}
