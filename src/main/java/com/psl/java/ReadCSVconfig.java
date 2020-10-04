package com.psl.java;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class ReadCSVconfig {
	public static WebDriver driver;
	static String[] row = null;
	static String browserName;
	public static String driverPath;
	static String url;
	
	public static WebDriver configCsv() throws Exception {
		
		String csvFilename = "C:\\GTT\\MavenProject\\Wordpress\\TestData\\properties.csv";
				
		CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
		while((row = csvReader.readNext())!=null){
			browserName = row[0];
			driverPath = row[1];
			url = row[2];
			String os = row[3];
			System.out.println("Browser : "+browserName);
			System.out.println("Operating system : "+os);
			System.out.println("Application url : "+url);
			if(browserName.equalsIgnoreCase("chrome")){
				System.out.println("Launch chrome");
				System.setProperty("webdriver.chrome.driver", driverPath);
				driver = new ChromeDriver();
				driver.get(url);
			}
				
			if(browserName.equalsIgnoreCase("firefox")){
				System.out.println("Launch firefox");
				System.setProperty("webdriver.firefox.driver", driverPath);
				driver = new FirefoxDriver();
			}
			csvReader.close();
		
	}
		return driver;
		}

	
	}


