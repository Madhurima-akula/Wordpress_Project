package com.psl.project;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.psl.java.DatabaseConnection;
import com.psl.java.ReadCSVconfig;

public class WordpressLoginTest {
	static String username;
	static String password;
	static WebDriver driver;
	Logger log = Logger
	
	@Test
	public void login() throws Exception {
		DatabaseConnection db = new DatabaseConnection();
		String credential = db.database();
		String username = credential.split("_")[0];
		String password = credential.split("_")[1];
		System.out.println("DB - username "+username);
		System.out.println("DB password "+password);
		
		ReadCSVconfig config = new ReadCSVconfig();
		WebDriver driver = config.configCsv();
	    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	    driver.findElement(By.id("user_login")).sendKeys(username);
		driver.findElement(By.id("user_pass")).sendKeys("root123");
		driver.findElement(By.id("wp-submit")).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		String actualTitle = driver.getTitle();
		System.out.println("Title : "+actualTitle);
		String expectedTitle = "Dashboard ‹ Wordpress Site — WordPress";
		if (actualTitle .equalsIgnoreCase(expectedTitle))
			System.out.println("home page - Title Matched");
		else
			System.out.println("home page :Title didn't match");
		/*driver.findElement(By.xpath("//span[@class='display-name'][contains(text(),'administrator')]")).click();
		WebElement logout = driver.findElement(By.xpath("//a[@class='ab-item'][contains(text(),'Log Out')]"));
	    JavascriptExecutor exe = (JavascriptExecutor)driver;
	    exe.executeScript("arguments[0].click();", logout);
	    driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	    System.out.println("Logout from Wordpress");
	    System.out.println("Logout from Wordpress");
	    String actualTitle1 = driver.getTitle();
		System.out.println("Title : "+actualTitle1);
		String expectedTitle1 = "Dashboard ‹ Wordpress Site — WordPress";
		if (actualTitle .equalsIgnoreCase(expectedTitle1))
			System.out.println("Logout : Title Matched");
		else
			System.out.println("Logout : Title didn't match");*/
			
		logout();
		
		driver.close();
		driver.quit();
		
	}

	public void logout() {
		driver.findElement(By.xpath("//span[@class='display-name'][contains(text(),'administrator')]")).click();
		WebElement logout = driver.findElement(By.xpath("//a[@class='ab-item'][contains(text(),'Log Out')]"));
	    JavascriptExecutor exe = (JavascriptExecutor)driver;
	    exe.executeScript("arguments[0].click();", logout);
	    driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	    System.out.println("Logout from Wordpress");
	    String actualTitle = driver.getTitle();
		System.out.println("Title : "+actualTitle);
		String expectedTitle = "Dashboard ‹ Wordpress Site — WordPress";
		if (actualTitle .equalsIgnoreCase(expectedTitle))
			System.out.println("Title Matched");
		else
			System.out.println("Title didn't match");
	    
		
		
	}
}

