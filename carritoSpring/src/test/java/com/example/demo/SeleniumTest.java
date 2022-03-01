
package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SeleniumTest {
	
	private WebDriver driver;
	
	@BeforeEach
	void init() {
		System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver");
//		ChromeOptions options = new ChromeOptions();
		
//		driver = new ChromeDriver(options);

		FirefoxOptions options = new FirefoxOptions();
		driver = new FirefoxDriver(options);
	
	
	}
	
	@Test
	public void Home() throws InterruptedException {
		driver.get("http://localhost:8080");
		
		WebElement user = driver.findElement(By.id("user"));
		
		WebElement password = driver.findElement(By.id("password"));
		
		WebElement submit = driver.findElement(By.id("boton"));
		
		Thread.sleep(4000);
		user.sendKeys("admin");
		Thread.sleep(4000);
		password.sendKeys("admin");
		submit.click();
		
		String titulo = driver.getCurrentUrl();
		System.out.println(titulo);
		assertTrue(titulo.equals("http://localhost:8080/login/seleccion"));
//		driver.quit();
		
	}

	

}