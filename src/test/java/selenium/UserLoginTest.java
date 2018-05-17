package selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class UserLoginTest {
	private static WebDriver driver;

	@Before
	public void setUp(){
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testUserLogin() throws InterruptedException {
		driver.manage().window();
		driver.get("http://thedemosite.co.uk/");
		Thread.sleep(1000);
		// ERROR: Caught exception [unknown command [#]]
		driver.findElement(By.linkText("3. Add a User")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("Wakanda");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("forever");
		driver.findElement(By.name("FormsButton2")).click();
		driver.findElement(By.linkText("4. Login")).click();
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("Wakanda");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("forever");
		driver.findElement(By.name("FormsButton2")).click();
        Thread.sleep(3000);

    }

	@After
	public void tearDown(){
		driver.quit();
	}
}
