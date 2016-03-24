package com.aimprosoft.tests;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import javax.lang.model.util.Elements;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test0002 {
	String testId = "01.0002";
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.out.println("Test: " + testId + " START");
		driver = new FirefoxDriver();
		baseUrl = "https://www.google.com.ua/";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void test0002() throws Exception {
		driver.get(baseUrl + "/?gfe_rd=cr&ei=Oy7xVpu6F6Ot8weTmYGYAg&gws_rd=ssl");
		driver.findElement(By.id("lst-ib")).clear();
		driver.findElement(By.id("lst-ib")).sendKeys("robo");
		driver.findElement(By.name("btnG")).click();
		for (int i = 0; i < 10; i++) {
			if (existsElement("Robohunter: Роботы в России и СНГ. Трудоустройство в ...") == true) {
				driver.findElement(
						By.linkText("Robohunter: Роботы в России и СНГ. Трудоустройство в ..."))
						.click();
				break;
			} else {
				driver.findElement(By.cssSelector("#pnnext > span.csb.ch"))
						.click();
			}
		}
		driver.findElement(By.cssSelector("input.search-button")).click();
		Assert.assertEquals("Robohunter", driver.getTitle());
		System.out.println("Search page - OK");
		driver.findElement(
				By.cssSelector("#search-all > input[name=\"params[term]\"]"))
				.click();
		driver.findElement(
				By.cssSelector("#search-all > input[name=\"params[term]\"]"))
				.clear();
		driver.findElement(
				By.cssSelector("#search-all > input[name=\"params[term]\"]"))
				.sendKeys("tesla motors");
		System.out.println("Field filled - OK");
		driver.findElement(By.cssSelector("#search-all > input.search-button"))
				.click();
		if (existsElement("TESLA MOTORS")) {
			System.out.println("Test: " + testId + " PASSED!!!");
		} else {
			System.out.println("Test: " + testId + " FAILED!!!");
		}

	}

	@After
	public void tearDown() throws Exception {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean existsElement(String linkText) {
		try {
			driver.findElement(By.linkText(linkText));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
