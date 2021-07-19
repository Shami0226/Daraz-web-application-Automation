package co.daraz;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Daraz {

	WebDriver driver;

	@BeforeTest
	@Parameters({"browserName"})
	public void setUpBrowser(String browserName) {
		
		if(browserName.equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("edge")) 
		{
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"//Drivers//msedgedriver.exe");
			driver=new EdgeDriver();
		}else 
		{
			System.out.println("check your drivers");
		}
	}

	@Test(priority = 0)
	@Parameters({ "itemName", "url" })
	public void homePage(String itemName, String url) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.findElement(By.id("q")).sendKeys(itemName);
		driver.findElement(By.xpath("//button[contains(text(),'SEARCH')]")).click();

	}

	@Test(priority = 1)
	@Parameters({ "minPrice", "maxPrice" })
	public void selectWatchSpecs(String minPrice, String maxPrice) {
		driver.findElement(By.xpath("//span[contains(text(),'NAVIFORCE')]")).click();
		System.out.println("Naviforce Item selected");

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,200)");
		System.out.println("Scrolled by 200 pix");
		driver.findElement(By.xpath("//span[contains(text(),'Rolex')]")).click();
		System.out.println("Rolex Item selected");

		jse.executeScript("window.scrollBy(0,500)");
		System.out.println("Scrolled by 500 pix");
		driver.findElement(By.xpath("//span[contains(text(),'Cash On Delivery')]")).click();
		System.out.println("Selected Cod option");

		jse.executeScript("window.scrollBy(0,800)");
		driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[4]/div[2]/div[1]/div[1]/label[1]/span[1]/input[1]")).click();
		System.out.println("Selected location Sri Lanka");

		jse.executeScript("window.scrollBy(0,1000)");
		System.out.println("window scrolled by 1000 pix");
		
		  
		WebElement min=driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[5]/div[2]/div[1]/input[1]")); 
		min.click(); 
		min.sendKeys(minPrice);
		  
		WebElement max=driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[5]/div[2]/div[1]/input[2]")); 
		max.click(); 
		max.sendKeys(maxPrice); 
		  
		driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[5]/div[2]/div[1]/button[1]")).click(); 
		System.out.println("set watch price between 5000-10000 ");
		 

		driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[6]/div[2]/div[2]/span[1]")).click();
		System.out.println("Selected 4 star and above rating");
		
		jse.executeScript("window.scrollBy(0,1200)");
		System.out.println("window scrolled by 1200 pix");
		
		driver.findElement(By.xpath("//span[contains(text(),'Dark Brown')]")).click();
		System.out.println("Selected watch strap color Drak Brown");
		
		jse.executeScript("window.scrollBy(0,1300)");
		System.out.println("window scrolled by 1300 pix");
		driver.findElement(By.xpath("//span[contains(text(),'1 Year')]")).click();
		System.out.println("Warranty period 1 year selected");
		
		driver.findElement(By.linkText("Naviforce Dual Time Waterproof Alarm Back light Genuine leather vintage gents watch")).click();
		System.out.println("Item Selected");
	}
	
	@Test(priority=2)
	@Parameters({"email","password"})
	public void buyItem(String email, String password) 
	{
		driver.findElement(By.xpath("//button[@class='add-to-cart-buy-now-btn  pdp-button pdp-button_type_text pdp-button_theme_bluedaraz pdp-button_size_xl']")).click();
		System.out.println("\"Buy Now Pressed\"");
		
		/*
		 * driver.findElement(By.
		 * xpath("//input[@placeholder='Please enter your Phone Number or Email']")).
		 * sendKeys(email); System.out.println("email provided");
		 * 
		 * driver.findElement(By.
		 * xpath("//input[@placeholder='Please enter your password']")).sendKeys(
		 * password); System.out.println("password provided");
		 */
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
