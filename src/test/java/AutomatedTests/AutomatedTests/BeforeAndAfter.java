package AutomatedTests.AutomatedTests;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import data.provider.ConfigReader;

public class BeforeAndAfter {
	
	 static WebDriver driver;
	 static pages.LoginPage loginPage;
	 static pages.PalindromeTestPage palindromeTestPage;
	 static test.utility.CommonLib common;
	 static String headless = null;
	 static	ConfigReader reader = new ConfigReader();
	 static final String HEADLESSPROP = "headless";
	 static String strURL = reader.getURL();
	 
	 protected BeforeAndAfter() {
	      //not called
	   }
	 	 
	@BeforeClass
	public static void setUp(){		
		
		String strBrowserType = reader.getBrowserType();
		
		String strIsHeadLess = reader.getIsHeadLess();
		
		String strDriverPath = reader.getDriverPath();
				
		System.setProperty(strBrowserType, strDriverPath);
			
		System.setProperty(HEADLESSPROP, strIsHeadLess);
					        
        if (strBrowserType.contains("chrome")) {
        	startChromeDriver();
        }
        else {
        	startFireFoxDriver();
        }
         	               	
		loginPage = new pages.LoginPage(driver);
		
		palindromeTestPage = new pages.PalindromeTestPage(driver);
			
	}
	
	@AfterClass
	public static void tearDown(){
		
		 if(null != driver) {
	            driver.close();
	          
	        }
	
	}
	
	private static void startChromeDriver() {
		
		ChromeOptions chromeOptions = new ChromeOptions();
		
		headless = System.getProperty(HEADLESSPROP);
		
		if(headless.equals("true")) {
            chromeOptions.addArguments("--headless");
        } else {
        	chromeOptions.addArguments("--disable-infobars");
        } 
		
		driver = new ChromeDriver(chromeOptions);
	}
	
	private static void startFireFoxDriver() {
		
		FirefoxOptions ffOptions = new FirefoxOptions();
		   
		headless = System.getProperty(HEADLESSPROP);
		
		if(headless.equals("true")) {
            ffOptions.addArguments("--headless");
        } else {
        	ffOptions.addArguments("--disable-infobars");
        } 
		
        driver = new FirefoxDriver(ffOptions);
	}
	
	public static void printPalindromeCount(List<String> palindromesList) {
				
		 List<String> lines = Arrays.asList("The count of the visible Palindromes", 
				 String.valueOf(palindromesList.size()));
		 
		 Path file = Paths.get("countPalindromeNum.txt");
		 
		 try {
			 
			Files.write(file, lines, Charset.forName("UTF-8"));
			
		} catch (IOException e) {
			
			String message = "Unable to write to countPalindromeNum.txt!";
			
			Logger logger = Logger.getLogger(BeforeAndAfter.class.getName());
			
			logger.log(Level.SEVERE, message, e);
   		
		}
	}
}
