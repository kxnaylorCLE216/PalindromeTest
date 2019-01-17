package AutomatedTests.AutomatedTests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.logging.Logger;

public class PalindromeTests extends BeforeAndAfter {
	
	Logger logger = 
			Logger.getLogger(PalindromeTests.class.getName());
		
	@Before
	public void redirectedValidCred() {

		driver.navigate().to(strURL);
		
		loginPage.loginIntoPage();

	}
		
	@Test
	public void dynamicPalindrome() {
		
		List<String> firstList 
		   = palindromeTestPage.getAllPalindromes();
		
		loginPage.backToLogin();
		
		loginPage.loginIntoPage();
		
		List<String> secondList 
		   = palindromeTestPage.getAllPalindromes();
		
		boolean isDynamic = (!firstList.equals(secondList));
		
		assertTrue(isDynamic);

	}

	@Test
	public void buttonDisplaysMorePalindrome() {
		
		boolean isPalindrome 
		= palindromeTestPage.isMorePalindromesButton();
		
		assertTrue(isPalindrome);
				
	}
	
	@Test
	public void countPalindromeNum() {
		
		 List<String> palindromesList 
		   = palindromeTestPage.getAllPalindromes();
			 		  
		 printPalindromeCount(palindromesList);
				 		
	}
	
}
