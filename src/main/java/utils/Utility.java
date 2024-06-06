package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {
	
	public static WebDriver driver;
	
	
    //Launch the browser and yrl
	public void launchBrowser(String browser, String url) {
		if(browser.equals("chrome"))
		driver = new ChromeDriver();
		if(browser.equals("firefox"))
			driver = new FirefoxDriver();
		
		driver.get(url);
		driver.manage().window().maximize(); //maximize the webpage
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	
	}
	
	//close the browser
	public void closeBrowser() {
		driver.close();
	}
	
	//get the data from the excel based on the sheetName
	public String[][] userCredentials(String sheetName) throws IOException {
		String value;
		FileInputStream fis = new FileInputStream("C:\\Users\\Admin\\Desktop\\Indhu_ZenClass\\JavaPrograms\\eclipse-workspace\\task24_demoBlaze\\data\\DemoBlazeData.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workBook.getSheet(sheetName);
		
		int rowCount = sheet.getLastRowNum(); //rowCount = 3 (in excel 4 including header)
		int colCount = sheet.getRow(0).getLastCellNum();
		
		String [][]arr = new String[rowCount][colCount];
		
		for(int i=0;i<rowCount;i++) {
			//if(i==rowCount)
			//	break;
			XSSFRow row = sheet.getRow(i+1);
			//System.out.print(i+ "   ");
			
			for(int j=0;j<colCount;j++) {
				XSSFCell cell = row.getCell(j,MissingCellPolicy.CREATE_NULL_AS_BLANK);
				//System.out.print(j+" ");
				//System.out.print(cell.getStringCellValue());
				if(cell.getStringCellValue()==null) {
					 value="";
				}
				else
				    value = cell.getStringCellValue();
				arr[i][j] = value;
			}
			
			//System.out.println("");
		}
		workBook.close();
		return arr;
			
	}
	
	public boolean handleAlert(boolean alertPresent) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			alertPresent = true;
			//System.out.println("Alert message is "+driver.switchTo().alert().getText());
			//driver.switchTo().alert().accept();
		}
		catch (Exception e) {
			alertPresent=false;
			System.out.println("No alert appeared");
		}
		return alertPresent;
	}
	
	//method to take screenshot and put the file in the destination folder.
	public String takeScreenshot(String screenshotName) throws IOException {
		
		String screenshotPath = "C:\\Users\\Admin\\Desktop\\Indhu_ZenClass\\JavaPrograms\\eclipse-workspace\\Task24_DemoBlaze\\screenshot\\"+screenshotName+".jpg";
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File(screenshotPath);
		FileUtils.copyFile(source, destination);
		return screenshotPath;
	}
}
