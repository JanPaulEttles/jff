package janwebdriver;

import java.util.Map;
import java.util.logging.Level;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class JanBaseWebDriverFirefox {

	private static final int WAIT_TIME = 3000;
	
	protected WebDriver webDriver;
	protected WebDriverWait wait;
	
	@Before 
	public void setUp() {
		
		System.setProperty("webdriver.gecko.driver", "/home/jan/janunit/geckodriver-0.19.0");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");

		
		ProfilesIni profileIni = new ProfilesIni();
	    FirefoxProfile profile = profileIni.getProfile("default"); 
	    profile.setPreference("network.proxy.type", 1);
	    profile.setPreference("network.proxy.http", "localhost.ssl");
	    profile.setPreference("network.proxy.http_port", 8080);
	    profile.setPreference("network.proxy.ssl", "localhost.ssl");
	    profile.setPreference("network.proxy.ssl_port", 8080);
	    profile.setPreference("network.proxy.no_proxies_on", "");

	    //profile.setPreference("webdriver.log.browser.file", "/dev/null");

        
	    FirefoxOptions options = new FirefoxOptions();
		options.setProfile(profile);
		//options.setHeadless(true);		
	
		//options.setLogLevel(FirefoxDriverLogLevel.WARN);
	    //options.addPreference("log", "{level: info}");
	    //desiredCapabilities.setCapability("moz:firefoxOptions", options);

		webDriver = new FirefoxDriver(options);	
		wait = new WebDriverWait(webDriver, WAIT_TIME);
	}

	@After 
	public void tearDown() {
		webDriver.close();
	}

	protected String waitForResult() {
		return 	waitForResult("content");
	}

	protected String waitForResult(String id) {
		return 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id))).getText();
	}

	protected void populate(String key, String value) {
		webDriver.findElement(By.id(key)).sendKeys(value);
	}
	
	protected void navigate(String path) {
		webDriver.navigate().to(JanEnumTestData.BASE_URL.getValue() + path);
	}
	
	protected void navigateAndWaitFor(String path, String id) {
		webDriver.navigate().to(JanEnumTestData.BASE_URL.getValue() + path);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
	}

	protected void submit(String submit) {
		webDriver.findElement(By.id(submit)).submit();
	}

	protected void submitForm(String form, Map<String, String> inputs, String submit) throws InterruptedException {
		navigate(form);	
		
	    for(Map.Entry<String, String> entry:inputs.entrySet()){    
		    try {
		    	populate(entry.getKey(), entry.getValue());
		    } 
		    catch(Exception e) {
		    	System.out.println("skipping element: " + entry.getKey());
		    }
	    }	        
		submit(submit);
		
		//just in case of staleness... shouldn't need this but we do... grrr
		Thread.sleep(1000);
	}

	protected void postForm(String form, Map<String, String> inputs, String submit) throws InterruptedException {
		navigate(form);	
		
	    for(Map.Entry<String, String> entry:inputs.entrySet()){    
	        populate(entry.getKey(), entry.getValue());
	    }
	        
		submit(submit);
		
		//just in case of staleness... shouldn't need this but we do... grrr
		Thread.sleep(1000);
	}
	
	protected String checkAlert() {
		String message = "NO_ALERT_MESSAGE";
	    try {
	        wait.until(ExpectedConditions.alertIsPresent());
	        Alert alert = webDriver.switchTo().alert();
	        message = alert.getText();
	        alert.accept();
	        
	    } 
	    catch (Exception e) {
	        //exception handling
	    }
	    return message;
	}

}