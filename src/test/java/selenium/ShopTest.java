package selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class ShopTest {

    private static WebDriver driver;

    @Before
    public void setup(){
        //System.setProperty("Webdriver.chrome.driver", "C:/Users/QAC/ownloads/chromedriver_win32");
        driver = new ChromeDriver();
    }

    @Test
    public void exampleSelenium() throws InterruptedException{
        driver.manage().window().fullscreen();
        Thread.sleep(1000);
        driver.get("http://automationpractice.com/index.php");
        //WebElement test1 = driver.findElement(By.partialLinkText("DRESSES"));
        //test1.click();
        //Thread.sleep(1000);
        //assertEquals("Printed Dress", driver.findElement(By.id("name")).getText());
        assertEquals("Printed Summer Dress", driver.findElement(By.partialLinkText("Printed Summer Dress")).getText());
        assertEquals("Printed Chiffon Dress", driver.findElement(By.partialLinkText("Printed Chiffon Dress")).getText());
        Thread.sleep(1000);


    }

    @After
    public void tearDown(){
        driver.quit();
    }


}
