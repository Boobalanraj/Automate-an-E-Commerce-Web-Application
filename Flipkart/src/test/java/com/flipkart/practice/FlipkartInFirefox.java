package com.flipkart.practice;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterMethod;

public class FlipkartInFirefox {
	WebDriver driver;
  @Test
  public void FlipkartFirefox() throws InterruptedException {
	  try {
			Timeouts time = driver.manage().timeouts();
			time.pageLoadTimeout(60, TimeUnit.SECONDS);
			long start = System.currentTimeMillis(); 
			driver.get("https://www.flipkart.com/");
			long end = System.currentTimeMillis();
			long loadingTime =end-start;
			System.out.println("loading Time : " +loadingTime);
			 System.out.println("Title : "+driver.getTitle());
			} catch(NoSuchElementException e) {
				e.printStackTrace();
			}
				
			try {
				System.out.println("--------------------------------------------------------------");
				Thread.sleep(1000);
				driver.findElement(By.xpath("/html/body/div[2]/div/div/button")).click();
				System.out.println("close the login button");
			}catch(NoSuchElementException e) {
				e.printStackTrace();
			}
			
			Thread.sleep(1000);
			WebElement productname= driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[2]/form/div/div/input"));
			productname.sendKeys("iphone13");
			System.out.println("Search bar enter product");
			
			Thread.sleep(1000);
			WebElement search = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[2]/form/div/button"));
			search.click();
			System.out.println("search button click successfully");
			
			Thread.sleep(2000);
			long begin = System.currentTimeMillis();
			WebElement element = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[4]/div/div/div/a/div[2]/div[1]/div[3]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			long endprocess = System.currentTimeMillis();
			long processTime = endprocess - begin;
			System.out.println("Scroll Frequency in millisecs - " + processTime);
			
			
			Thread.sleep(2000);
			WebElement load = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[4]/div/div/div/a/div[1]/div[1]/div/div/img"));
			long start = System.currentTimeMillis();
			load.click();
			long end = System.currentTimeMillis();
			long performanceTime = end - start;
			System.out.println("Time to mobile open - " + performanceTime);
			System.out.println("slected product successfully");

			String url ="https://www.flipkart.com/apple-iphone-13-starlight-256-gb/p/itm37b8de852304e?pid=MOBG6VF5NFYJ8KQX&lid=LSTMOBG6VF5NFYJ8KQX7FK2GA&marketplace=FLIPKART&q=iphone13&store=tyy%2F4io&srno=s_1_8&otracker=search&otracker1=search&fm=organic&iid=3b46de92-30f5-43ee-9453-12747a1cff80.MOBG6VF5NFYJ8KQX.SEARCH&ppt=hp&ppn=homepage&ssid=z5xfs3x3gg0000001665489456626&qH=7d4afaedfc628b80";
			driver.get(url);
			Thread.sleep(3000);
				//driver.navigate().refresh();

				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS)
						.pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class); 
				wait.until(new Function<WebDriver, WebElement>() {

					public WebElement apply(WebDriver driver) {
						WebElement img = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div[1]/div/div[1]/div[2]/div[1]/div[2]/img"));

						if (img.isDisplayed()) {
							System.out.println("Image Loaded");
							return img;
						} else {
							System.out.println("Fluent Wait Fail!, Element Not Loaded Yet");
							return null;
						}
					}
					
				});
				
				
				WebElement image = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div[1]/div/div[1]/div[2]/div[1]/div[2]/img"));
				Boolean p = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete "
						+ "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0", image);

				if (p) {
					System.out.println("Image present");
				} else {
					System.out.println("Image not present");
				}
				
				Thread.sleep(1000);
				Dimension dimension = new Dimension(720, 1080);
				driver.manage().window().setSize(dimension);
				Thread.sleep(3000);

				Dimension dimension1 = new Dimension(1280, 800);
				driver.manage().window().setSize(dimension1);
				Thread.sleep(3000);

				Dimension dimension2 = new Dimension(2256, 1504);
				driver.manage().window().setSize(dimension2);

				JavascriptExecutor js = (JavascriptExecutor) driver;
				int contentHeight = ((Number) js.executeScript("return window.innerHeight")).intValue();
				int contentWidth = ((Number) js.executeScript("return window.innerWidth")).intValue();
				System.out.println("Height: " + contentHeight + " Width: " + contentWidth);
				
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.setProperty("webdriver.gecko.driver", "F:\\Java FSD Software\\geckodriver\\geckodriver.exe");
	  driver =new FirefoxDriver();
	  driver.manage().window().maximize();
  }

  @AfterMethod
  public void afterMethod() {
	  driver =null;
  } 

}
