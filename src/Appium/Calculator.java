package Appium;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
//import org.junit.BeforeClass;
//import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
//import junit.framework.Assert;
public class Calculator {
	
	WebDriver driver;
	
	@BeforeClass
	public void setUp() throws MalformedURLException{
		//Set up the desired capabilities and pass the Android app-activity and app-package to Appium
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("VERSION", "6.0.1");
		capabilities.setCapability("deviceName", "Emulator");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.sec.android.app.popupcalculator");
		//This is package name of your app (you can get it from ApplicationReader app)
		capabilities.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");
		//This is launcher activity of your app (you can get it from ApplicationReader app
		//Create RemoteWebDriver instance and connest to the Appium server
		//It will launch the Calculator app in Android Device using configurations specified
		//in Desired Capabilities
		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}
	
	@Test
	public void testCal() throws Exception{
		//locate the text on the calculator by using By.name()
		WebElement two = driver.findElement(By.name("2"));
		two.click();
		WebElement plus = driver.findElement(By.name("+"));
		plus.click();
		WebElement four = driver.findElement(By.name("4"));
		four.click();
		WebElement equal = driver.findElement(By.name("="));
		equal.click();
		//Locate the edit box of the calculator by using By.tagName()
		WebElement results = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/txtCalc"));
		//Check the calculator value on the edit box
		//assert results.getText().equals("6"):"Actual value is : "+results.getText()+" did not match with expected value: 6";
		Assert.assertEquals("6", results.getText());
		
	}
	
	@AfterClass
	public void tearDown(){
		//close the app
		driver.quit();
	}
	
}
