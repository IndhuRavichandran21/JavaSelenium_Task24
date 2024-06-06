package utils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.ProjectSpecificationMethod;

public class Listener extends ProjectSpecificationMethod implements ITestListener{
	
	ExtentReports extentReports = ExtentReports_DemoBlaze.getReport();
	ExtentTest test; //class used to categorize the data captured by extentReports with the help of ITestListener
	String screenshotFilePath;

	//get the method name of each test
	@Override
	public void onTestStart(ITestResult result) {
		test = extentReports.createTest(result.getMethod().getMethodName());
	}

	//log the status in the report
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test is Passed");
	}

	//take and place the screenshot to the report if test fails
	@Override
	public void onTestFailure(ITestResult result) {
		
		test.fail(result.getThrowable());
			
		try {
			screenshotFilePath = takeScreenshot(result.getMethod().getMethodName());
		} catch (IOException e) {
			
			e.printStackTrace();
		}		
		
		test.addScreenCaptureFromPath(screenshotFilePath,result.getMethod().getMethodName());
	}

	//log the status
	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Test is skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();//it will append all the information in the report
	}

}
