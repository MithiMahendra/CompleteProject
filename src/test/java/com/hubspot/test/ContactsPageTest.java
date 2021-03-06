package com.hubspot.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.homepage.base.BasePage;
import com.hubspot.pages.ContactsPage;
import com.hubspot.pages.HomePage;
import com.hubspot.pages.LogInPage;
import com.hubspot.util.Credentials;
import com.hubspot.util.ElementUtil;
import com.hubspot.util.ExcelUtil;

public class ContactsPageTest {

	WebDriver driver;
	BasePage basepage;
	Properties properties;
	LogInPage loginpage;
	HomePage homepage;
	Credentials credentials;
	ContactsPage contactsPage;
	com.hubspot.util.ExcelUtil excelutil;
	ElementUtil elementutil;

	@BeforeMethod
	public void setup() throws InterruptedException {
		basepage = new BasePage();
		properties = basepage.driverProperties();
		String browsername = properties.getProperty("browser");
		driver = basepage.driver_init(browsername);
		driver.get(properties.getProperty("url"));
		loginpage = new LogInPage(driver);
		// homepage = new HomePage(driver);

		credentials = new Credentials(properties.getProperty("username"), properties.getProperty("password"));
		homepage = loginpage.logIn(credentials);
		contactsPage = homepage.getContactsPage();
		elementutil =new ElementUtil(driver);
		// excelutil = new com.hubspot.util.ExcelUtil();

	}

	//

	@DataProvider()
	public Object[][] getContactData() {
		Object data[][] = ExcelUtil.getTestData("EmpData");
		return data;
	}

	@Test(priority = 0, dataProvider = "getContactData")
	public void getFilling(String empnam, String username, String pwd, String confpwd) {
		elementutil=contactsPage.filling(empnam, username, pwd, confpwd);
		
		
	}

}
