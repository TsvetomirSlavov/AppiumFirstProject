package Appium;

import java.net.MalformedURLException;
import java.net.URL;

//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
//import org.junit.BeforeClass;
//import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
//import junit.framework.Assert;
//random string by java
import java.util.UUID;
import java.util.concurrent.TimeUnit;



public class Fammy {
	
	String randomName = UUID.randomUUID().toString().substring(0, 5);
	WebDriver driver;
	
	@BeforeClass
	public void setUp() throws MalformedURLException{
		//Set up the desired capabilities and pass the Android app-activity and app-package to Appium
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("VERSION", "6.0.1");
		capabilities.setCapability("deviceName", "Emulator");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.spacosa.android.famy.global");
		//This is package name of your app (you can get it from ApplicationReader app)
		capabilities.setCapability("appActivity", "com.spacosa.android.famy.global.IntroActivity");
		//This is launcher activity of your app (you can get it from ApplicationReader app
		//Create RemoteWebDriver instance and connest to the Appium server
		//It will launch the Calculator app in Android Device using configurations specified
		//in Desired Capabilities
		//BUILD IN APPIUM ACCEPT ALERTS OR DISMISS TO CLICK SAVE IN DIALOG AUTOMATICALLY 
		capabilities.setCapability("autoAcceptAlerts", true);
		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void testCal() throws Exception{
		WebElement initialName = driver.findElement(By.id("com.spacosa.android.famy.global:id/text_name"));
		String initialNameValue = initialName.getText();
		//locate the text on the calculator by using By.name()
		WebElement pen = driver.findElement(By.id("com.spacosa.android.famy.global:id/btn_profile"));
		pen.click();
		WebElement nameField = driver.findElement(By.id("com.spacosa.android.famy.global:id/name"));
		nameField.clear();
		nameField.sendKeys(randomName);
		driver.navigate().back();
		WebElement save = driver.findElement(By.id("com.spacosa.android.famy.global:id/btn_save"));
		save.click();
		//Alert alert = driver.switchTo().alert();
		//alert.accept();
		//Wait.until(ExpectedConditions.alertIsPresent());
		//Alert alert = driver.switchTo().alert();
		//alert.accept();
		WebElement saveNotification = driver.findElement(By.id("android:id/button1"));
		saveNotification.click();
		//IT UPDATES PROGRESS BAR AND FIRST DISPLAYS THE OLD NAME AND AFTER A SECOND THE NEW NAME
		Thread.sleep(10000);
		String newNameValue = initialName.getText();
		Assert.assertNotEquals(initialNameValue, newNameValue);
		
	}
	
	@AfterClass
	public void tearDown(){
		//close the app
		driver.quit();
	}

}
