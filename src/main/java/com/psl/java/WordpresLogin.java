package com.psl.java;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.psl.java.*;

public class WordpresLogin {
	static String username;
	static String password;
	static WebDriver driver;
	
	Logger log = Logger.getLogger(WordpresLogin.class);
	@Test
	public void login() throws Exception {
		DatabaseConnection db = new DatabaseConnection();
		String credential = db.database();
		String username = credential.split("_")[0];
		String password = credential.split("_")[1];
		System.out.println("DB - username "+username);
		System.out.println("DB password "+password);
		log.info("Calling CSV file");
		ReadCSVconfig config = new ReadCSVconfig();
		WebDriver driver = config.configCsv();
		log.info("entering application URL");
		log.warn("Hey this just a warning message");
		log.fatal("hey this is just fatal error message");
		log.debug("this is debug message");
		log.info("Login Page");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	    driver.findElement(By.id("user_login")).sendKeys(username);
		driver.findElement(By.id("user_pass")).sendKeys("root123");
		driver.findElement(By.id("wp-submit")).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		String actualTitle = driver.getTitle();
		System.out.println("Title : "+actualTitle);
		log.info("Home Page title : "+actualTitle);
		String expectedTitle = "Dashboard ‹ Wordpress Site — WordPress";
		if (actualTitle .equalsIgnoreCase(expectedTitle))
			System.out.println("home page - Title Matched");
		else
			System.out.println("home page :Title didn't match");
		
		logout();
		
		driver.close();
		driver.quit();
		
	}
	
	public void logout() throws Exception {
		System.out.println("calling logout- method");
		//WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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

