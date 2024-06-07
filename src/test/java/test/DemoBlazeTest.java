package test;

import org.testng.Assert;
import org.testng.annotations.*;
import base.ProjectSpecificationMethod;
import pages.HomePage;
import pages.LoginPage;
import pages.SignUpPage;


public class DemoBlazeTest extends ProjectSpecificationMethod{	
	
	//validate title of the homepage
	@Parameters({"homePageTitle"})
	@Test(priority=1)
	public void getTitle(String homePageTitle) {

		System.out.println(driver.getTitle());
		Assert.assertEquals(driver.getTitle(), homePageTitle);	//verifying the page tile with assertion
	}	
	
	//Testing the Sign Up functionality with valid credentials
	@Test(priority=2,dataProvider = "Valid_SignUpCredentials")
	public void validSignUp(String emailID, String password) {
		HomePage homePageObj = new HomePage(driver);
		SignUpPage signUpPageObj = new SignUpPage(driver);
		
		homePageObj.signUpNewUser();
		boolean isAlertValid = signUpPageObj.enterCredentials(emailID, password, false);
		Assert.assertTrue(isAlertValid); //verifying based on the alert message display with asserion
	}
	
	//Testing the Sign Up functionality with Invalid credentials
	@Test(priority=3,dataProvider = "Invalid_SignUpCredentials")
	public void InValidSignUp(String emailID, String password) {
		HomePage homePageObj = new HomePage(driver);
		SignUpPage signUpPageObj = new SignUpPage(driver);
		
		homePageObj.signUpNewUser();
		boolean isAlertValid = signUpPageObj.enterCredentials(emailID, password, false);
		Assert.assertFalse(isAlertValid); //verifying based on the alert message display with asserion
	}
	
	//Testing the LogIn functionality with valid credentials
	@Test(priority=4,dataProvider = "Valid_LogInCredentials")
	public void validLogin(String emailID, String password) { 
		HomePage homePageObj = new HomePage(driver);
		LoginPage loginPageObj = new LoginPage(driver);
		
		homePageObj.logInUser();
		String result = loginPageObj.login(emailID, password, false);
		Assert.assertEquals(result, "Login Successful"); //verifying the successful login based on the profile display with assertion
		if(result=="Login Successful")
		homePageObj.logOutUser();
	}
	
	//Testing the Login functionality with Invalid credentials
	@Test(priority=5,dataProvider = "Invalid_LogInCredentials")
	public void InvalidLogin(String emailID, String password) { 
		HomePage homePageObj = new HomePage(driver);
		LoginPage loginPageObj = new LoginPage(driver);
		
		homePageObj.logInUser();
		String result = loginPageObj.login(emailID, password, false);
		Assert.assertEquals(result, "Login not Successful"); //verifying based on the alert message display with assertion
		if(result=="Login Successful")
		homePageObj.logOutUser();
	}
	

}
