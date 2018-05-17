package selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class mouseActions {

    private static WebDriver driver;

    @Before
    public void setup(){
        //System.setProperty("Webdriver.chrome.driver", "C:/Users/QAC/ownloads/chromedriver_win32");
        driver = new ChromeDriver();
    }

    @Test
    @Ignore
    public void draggableMouseActionOne() throws InterruptedException {
        driver.get("http://demoqa.com/");
        driver.manage().window().fullscreen();

        WebElement draggableMenuButton = driver.findElement(By.id("menu-item-140"));
        draggableMenuButton.click();
        Thread.sleep(2000);
        WebElement draggableBox = driver.findElement(By.id("draggable"));

        Actions builder = new Actions(driver);
        builder.clickAndHold(draggableBox).moveByOffset(50,50).perform();
        Thread.sleep(3000);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
