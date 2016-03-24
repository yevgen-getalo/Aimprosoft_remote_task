package com.aimprosoft.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

public class Test0001 {
	private String testId = "01.0001";
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
	public void test0001() throws Exception {
		driver.get(baseUrl + "/?gfe_rd=cr&ei=Oy7xVpu6F6Ot8weTmYGYAg&gws_rd=ssl");
		driver.findElement(By.id("lst-ib")).clear();
		driver.findElement(By.id("lst-ib")).sendKeys("robo");
		driver.findElement(By.name("btnG")).click();
		for (int i = 0; i < 10; i++) {
			if (existsElement(By.linkText("Robohunter: Роботы в России и СНГ. Трудоустройство в ..."))) {
				driver.findElement(
						By.linkText("Robohunter: Роботы в России и СНГ. Трудоустройство в ..."))
						.click();
				break;
			} else {
				driver.findElement(By.cssSelector("#pnnext > span.csb.ch"))
						.click();
			}
		}
		String originalWindow = driver.getWindowHandle();
		final Set<String> oldWindowsSet = driver.getWindowHandles();
		driver.get("http://www.robo-hunter.com/");
		ArrayList<WebElement> sites = new ArrayList<WebElement>();
		sites.add(driver.findElement(By.cssSelector("a.fa.fa-geek-times")));
		sites.add(driver.findElement(By.cssSelector("a.fa.fa-star")));
		sites.add(driver.findElement(By
				.xpath("//a[contains(@href, 'https://www.facebook.com/robohunter.ru')]")));
		sites.add(driver.findElement(By
				.xpath("//a[contains(@href, 'https://twitter.com/RoboHunter_ru')]")));
		sites.add(driver.findElement(By
				.xpath("//a[@href='http://vk.com/robohunter']")));
		sites.add(driver.findElement(By.cssSelector("a.fa.fa-linkedin")));
		sites.add(driver.findElement(By.cssSelector("a.fa.fa-youtube")));
		sites.add(driver.findElement(By.cssSelector("a.fa.fa-google-plus")));
		sites.add(driver.findElement(By.cssSelector("a.fa.fa-instagram")));
		for (WebElement item : sites) {
			item.click();
			String newWindow = (new WebDriverWait(driver, 10))
					.until(new ExpectedCondition<String>() {
						public String apply(WebDriver driver) {
							Set<String> newWindowsSet = driver
									.getWindowHandles();
							newWindowsSet.removeAll(oldWindowsSet);
							return newWindowsSet.size() > 0 ? newWindowsSet
									.iterator().next() : null;
						}
					});
			driver.switchTo().window(newWindow);
			System.out.println(driver.getCurrentUrl());
			driver.close();
			driver.switchTo().window(originalWindow);

		}
		System.out.println("Test: " + testId + " PASSED!!!");
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

	private boolean existsElement(By by) {
		try {
			driver.findElement(by);
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
