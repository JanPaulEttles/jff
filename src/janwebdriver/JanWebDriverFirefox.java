package janwebdriver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JanWebDriverFirefox 
	extends JanBaseWebDriverFirefox {
	
	@Test
	public void Ahome() throws InterruptedException {
		navigate(JanEnumTestData.HOME.getValue());
		
		List<WebElement> links = webDriver.findElements(By.tagName("a"));
		
		links.forEach(link->{
				System.out.println(link.getText());
		});
		assertEquals(6, links.size());
	}
	
	@Test
	public void Bregister() throws InterruptedException {	
		
		Map<String, String> map = Helpers.populateRegistration();
	    
		postForm(JanEnumTestData.REGISTRATION_FORM.getValue(), map, "submitregister");
		assertEquals("User: 74n has been registered.\nAn email has been sent to: 74n@74n.com\nPlease login to continue.", waitForResult());
	}
	
	@Test
	public void Cforgotten() throws InterruptedException {								
		navigate(JanEnumTestData.FORGOTTEN_FORM.getValue());
		populate("email", JanEnumTestData.EMAIL.getValue());
		
		submit("submitforgotten");
		
		assertEquals("Your password will be sent to: 74n@74n.com", waitForResult());
	}
	
	@Test
	public void DloginGet() throws InterruptedException {
		
		navigate(JanEnumTestData.LOGIN_GET_URL.getValue());
		
		assertEquals("Logged In As: 74n", waitForResult("loggedinstate"));
	}

	@Test
	public void EloginPost() throws InterruptedException {	
		
		performLogin();
		
		assertEquals("Logged In As: 74n", waitForResult("loggedinstate"));
	}
	
	@Test
	public void Fviewprofile() throws InterruptedException {
		
		performLogin();			

		assertEquals("Logged In As: 74n", waitForResult("loggedinstate"));

		navigate(JanEnumTestData.VIEWPROFILE_URL.getValue() + "?username=" + JanEnumTestData.USERNAME.getValue());		
		assertEquals("username : 74n\npassword : password\nchange\n\nemail : 74n@74n.com\nfirstname : Jan\nlastname : Ettles\nchange", waitForResult());
	}

	@Test
	public void Gsearch() throws InterruptedException {
		navigate(JanEnumTestData.SEARCH_FORM.getValue());
		populate("search", JanEnumTestData.SEARCH_TERM.getValue());
		
		submit("submitsearch");

		assertEquals("You searched for: " + JanEnumTestData.SEARCH_TERM.getValue(), waitForResult());
	}
	
	@Test
	public void Hauthorised() throws InterruptedException {
		
		performLogin();				
		assertEquals("Logged In As: 74n", waitForResult("loggedinstate"));

		navigate(JanEnumTestData.AUTHENTICATED_URL.getValue());

		assertEquals("You are authenticated", waitForResult());
	}

	
	@Test
	public void Iunauthorised() throws InterruptedException {

		navigate(JanEnumTestData.LOGOUT_URL.getValue());
		assertEquals("Logged In As: Not Logged In", waitForResult("loggedinstate"));

		navigate(JanEnumTestData.AUTHENTICATED_URL.getValue());

		assertEquals("You are NOT authenticated", waitForResult());
	}	
	
	@Test
	public void Jchangepassword() throws InterruptedException {
		
		performLogin();
		assertEquals("Logged In As: 74n", waitForResult("loggedinstate"));
		
		Map<String, String> map = new HashMap<String, String>();    
	    map.put("password", JanEnumTestData.NEWPASSWORD.getValue());  
	    
		postForm(JanEnumTestData.CHANGEPASSWORD_FORM.getValue(), map, "submitchangepassword");
		assertEquals("Your password has been changed.", waitForResult());
		
		//change it back
	    map.put("password", JanEnumTestData.PASSWORD.getValue());  
	    
		postForm(JanEnumTestData.CHANGEPASSWORD_FORM.getValue(), map, "submitchangepassword");
		assertEquals("Your password has been changed.", waitForResult());			
	}

	@Test
	public void KloginLogout() throws InterruptedException {	
		performLogin();		
		assertEquals("Logged In As: 74n", waitForResult("loggedinstate"));

		navigate(JanEnumTestData.LOGOUT_URL.getValue());
		assertEquals("Logged In As: Not Logged In", waitForResult("loggedinstate"));
	}

	@Test
	public void Lupdateprofile() throws InterruptedException {	
		performLogin();		
		assertEquals("Logged In As: 74n", waitForResult("loggedinstate"));
		
		Map<String, String> map = new HashMap<String, String>();    
	    map.put("email", JanEnumTestData.NEWEMAIL.getValue());  
	    map.put("firstname", JanEnumTestData.NEWFIRSTNAME.getValue());  
	    map.put("lastname", JanEnumTestData.NEWLASTNAME.getValue());  
	    
		postForm(JanEnumTestData.UPDATEPROFILE_FORM.getValue() + "?username=" + JanEnumTestData.USERNAME.getValue(), map, "submitupdateprofile");		
		assertEquals("Your profile has been updated.", waitForResult());

		//change it back
	    map.put("email", JanEnumTestData.EMAIL.getValue());  
	    map.put("firstname", JanEnumTestData.FIRSTNAME.getValue());  
	    map.put("lastname", JanEnumTestData.LASTNAME.getValue());  
	    
		postForm(JanEnumTestData.UPDATEPROFILE_FORM.getValue() + "?username=" + JanEnumTestData.USERNAME.getValue(), map, "submitupdateprofile");
		assertEquals("Your profile has been updated.", waitForResult());
	
	}

	@Test
	public void Mcreatenote() throws InterruptedException {	
		performLogin();		
		assertEquals("Logged In As: 74n", waitForResult("loggedinstate"));
		
		Map<String, String> map = new HashMap<String, String>();    
	    map.put("title", JanEnumTestData.NOTETITLE.getValue());  
	    map.put("note", JanEnumTestData.NOTENOTE.getValue());  
		postForm(JanEnumTestData.NOTE_FORM.getValue(), map, "submitnote");				
		assertEquals("Title : " + JanEnumTestData.NOTETITLE.getValue() + "\nNote : " + JanEnumTestData.NOTENOTE.getValue(), waitForResult());
	}

	@Test
	public void Nlistnotes() throws InterruptedException {	
		performLogin();		
		assertEquals("Logged In As: 74n", waitForResult("loggedinstate"));
		
		navigate(JanEnumTestData.NOTESLIST_URL.getValue());
		
		assertThat(waitForResult(), containsString(JanEnumTestData.NOTETITLE.getValue()));
	}
	
	private void performLogin() throws InterruptedException {

		//make sure you're logged out
		navigate(JanEnumTestData.LOGOUT_URL.getValue());
		assertEquals("Logged In As: Not Logged In", waitForResult("loggedinstate"));

		Map<String, String> map = new HashMap<String, String>();    
	    map.put("username", JanEnumTestData.USERNAME.getValue());  
	    map.put("password", JanEnumTestData.PASSWORD.getValue());  
	    
		postForm(JanEnumTestData.LOGIN_FORM.getValue(), map, "submitlogin");
	}
}