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

	
	/*public static void main(String args[]) throws Exception {
		WordpresLogin login = new WordpresLogin();
		//login.login();
		//login.logout();
		
	}*/
	Logger log = Logger.getLogger(WordpresLogin.class);
	
	public String userDetails() throws Exception
	{
		DatabaseConnection db = new DatabaseConnection();
		String credential = db.database();
		String username = credential.split("_")[0];
		String password = credential.split("_")[1];
	//	System.out.println("DB - username "+username);
	//	System.out.println("DB password "+password);
		return username;
	}
	@Test
	public void login(String username,String password) throws Exception  {
		
		String username1 = username;
		String password1 = password;
		System.out.println("DB - username "+username1);
		System.out.println("DB password "+password1);
		log.info("---Execution started ----");
		log.info("Navigating to URL");
	
		WebDriver driver = ReadCSVconfig.configCsv();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		driver.get("http://localhost/wordpress/wp-login.php");
		driver.manage().window().maximize();
		log.info("Window Maximized");
		
	    driver.findElement(By.id("user_login")).sendKeys(username1);
	    log.info("Enter Username...");
	    driver.findElement(By.id("user_pass")).sendKeys("root123");
	    log.info("Enter Password");
	//	driver.findElement(By.id("user_pass")).sendKeys("password1");
		driver.findElement(By.id("wp-submit")).click();
		log.info("Click submit button to login to workpress application ....");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String actualTitle = driver.getTitle();
		System.out.println("Title : "+actualTitle);
		String expectedTitle = "Dashboard ‹ Wordpress Site — WordPress";
		log.info("Wordpress howe page title"+actualTitle);
		if (actualTitle .equalsIgnoreCase(expectedTitle))
			System.out.println("home page - Title Matched");
		else
			System.out.println("home page :Title didn't match");

	}
	
	public void logout() throws Exception {
		log.info("calling logout- method");
		WebDriver driver = ReadCSVconfig.configCsv();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[@class='display-name'][contains(text(),'administrator')]")).click();
		log.info("click on administrator to logout from application");
		WebElement logout = driver.findElement(By.xpath("//a[@class='ab-item'][contains(text(),'Log Out')]"));
	    JavascriptExecutor exe = (JavascriptExecutor)driver;
	    exe.executeScript("arguments[0].click();", logout);
	    log.info("Click on logout");
	    driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	    System.out.println("Logout from Wordpress");
	    log.info("Logout from wordpress");
	    String actualTitle = driver.getTitle();
		System.out.println("Title : "+actualTitle);
		String expectedTitle = "Dashboard ‹ Wordpress Site — WordPress";
		log.info("logout page title "+actualTitle);
		if (actualTitle .equalsIgnoreCase(expectedTitle))
			System.out.println("Title Matched");
		else
			System.out.println("Title didn't match");
	    
		//driver.close();
		//driver.quit();
		
		
	}
	public String verfication() throws Exception {
		WebDriver driver = ReadCSVconfig.configCsv();
		String text=driver.findElement(By.xpath("//div[@id='login_error']")).getText();
		return text;
	}
	public void closeBrowser() throws Exception
	{
		WebDriver driver = ReadCSVconfig.configCsv();
		driver.close();
		driver.quit();
	}
}

