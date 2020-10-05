package com.psl.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import com.psl.java.WordpresLogin;

public class LoginTest {
	
	/*public static void main(String args[]) throws Exception {
		WordpresLogin login = new WordpresLogin();
		String username=login.userDetails();
		String password=new String("root123");
		System.out.println("DB Username : "+username);
		System.out.println("DB Password : "+password);
		login.login(username, password);
		login.logout();
	
	}*/
	@Test
	public void sucessfullyTest() throws Exception
	{
		WordpresLogin login = new WordpresLogin();
		String username=login.userDetails();
		String password=new String("root123");
		System.out.println("DB Username : "+username);
		System.out.println("DB Password : "+password);
		login.login(username, password);
		login.logout();
	}
	
	@Test
	public void failuerTest() throws Exception
	{
		String prtext="Unknown username. Check again or try your email address.";
		WordpresLogin login = new WordpresLogin();
		String username="root123";
		String password="root123";
		login.login(username, password);
		//login.logout();
		String str=login.verfication();
		if (str==prtext)
		System.out.println("failuerTest pass");
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		WordpresLogin login = new WordpresLogin();
		login.closeBrowser();
	}

}
