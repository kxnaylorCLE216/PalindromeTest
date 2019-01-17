package AutomatedTests.AutomatedTests;

import static org.junit.Assert.*;
import org.junit.Test;

public class LoginTests extends BeforeAndAfter {
		
	@Test
	public void redirectedValidCred() {
		
		driver.navigate().to(strURL);
				
		loginPage.loginIntoPage();
		
		boolean isRedirected = palindromeTestPage.isExpectedPageTitle();
				
		assertTrue(isRedirected);
	}
	
	@Test
	public void userNameRequired() {
		
		driver.navigate().to(strURL);
				
		boolean boolRequired =loginPage.loginNoUserName();
				
		assertTrue(boolRequired);
	}
	
	@Test
	public void passwordRequired() {
		
		driver.navigate().to(strURL);
				
		boolean boolRequired =loginPage.loginNoPassword();
		
		assertTrue(boolRequired);
	}
	
	@Test
	public void invalidUsernameCred() {
		
		driver.navigate().to(strURL);
		
		loginPage.loginIntoPage("tester", "password");
		
		boolean isAlert = loginPage.isExpectedAlert();
				
		assertTrue(isAlert);
	}
	
	@Test
	public void invalidPasswordCred() {
		
		driver.navigate().to(strURL);
		
		loginPage.loginIntoPage("test", "1234");
				
		boolean isAlert = loginPage.isExpectedAlert();
		
		assertTrue(isAlert);
	}

}
