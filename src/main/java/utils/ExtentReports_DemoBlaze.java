package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReports_DemoBlaze {
	
	//create the html file in the defined path
	public static ExtentReports getReport() {
	String reportPath = "C:\\Users\\Admin\\Desktop\\Indhu_ZenClass\\JavaPrograms\\eclipse-workspace\\Task24_DemoBlaze\\reports\\DemoBlazeReport.html";
	ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
	reporter.config().setReportName("DemoBlaze Report");
	
	//attach the data to the created html file
	ExtentReports extentReports = new ExtentReports();
	extentReports.attachReporter(reporter);
	return extentReports;

	}
}
