package com.psl.java;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WordpresLogin extends DatabaseConnection{
	static String username;
	static String password;
	WebDriver driver;
	
	public static void main(String[] args) throws Exception {
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
		driver.findElement(By.id("user_pass")).sendKeys(password);
		driver.findElement(By.id("wp-submit")).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	
		//System.out.println("testing "+ driver);

	}

}
