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

import java.util.List;

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

    @Test @Ignore
    public void selectableMouseAction1() throws InterruptedException{
        driver.get("http://demoqa.com/");
        driver.manage().window();
        Thread.sleep(5000);
        WebElement selectableMouse = driver.findElement(By.id("menu-item-142"));
        Thread.sleep(2000);
        selectableMouse.click();
        Thread.sleep(2000);

        Actions selectItOnebyOne = new Actions(driver);
        selectItOnebyOne.moveToElement(driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[1]"))).click().perform();
        Thread.sleep(1000);
        selectItOnebyOne.moveToElement(driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[2]"))).click().perform();
        Thread.sleep(1000);
        selectItOnebyOne.moveToElement(driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[3]"))).click().perform();
        Thread.sleep(1000);
        selectItOnebyOne.moveToElement(driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[4]"))).click().perform();
        Thread.sleep(1000);
        selectItOnebyOne.moveToElement(driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[5]"))).click().perform();
        Thread.sleep(1000);
        selectItOnebyOne.moveToElement(driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[6]"))).click().perform();
        Thread.sleep(1000);
        selectItOnebyOne.moveToElement(driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[7]"))).click().perform();
        Thread.sleep(3000);
    }

    @Test
    public void selectableMouseAction2() throws InterruptedException{
        driver.get("http://demoqa.com/");
        driver.manage().window();
        Thread.sleep(5000);
        WebElement selectableMouse = driver.findElement(By.id("menu-item-142"));
        Thread.sleep(2000);
        selectableMouse.click();
        Thread.sleep(2000);
        driver.findElement(By.id("ui-id-3")).click();
        Thread.sleep(2000);

        Actions selectItOnebyOne = new Actions(driver);
        selectItOnebyOne.moveToElement(driver.findElement(By.xpath("//*[@id=\"selectable-serialize\"]/li[1]"))).click().perform();
        Thread.sleep(1000);
        selectItOnebyOne.moveToElement(driver.findElement(By.xpath("//*[@id=\"selectable-serialize\"]/li[2]"))).click().perform();
        Thread.sleep(1000);
        selectItOnebyOne.moveToElement(driver.findElement(By.xpath("//*[@id=\"selectable-serialize\"]/li[3]"))).click().perform();
        Thread.sleep(1000);
        selectItOnebyOne.moveToElement(driver.findElement(By.xpath("//*[@id=\"selectable-serialize\"]/li[4]"))).click().perform();
        Thread.sleep(1000);
        selectItOnebyOne.moveToElement(driver.findElement(By.xpath("//*[@id=\"selectable-serialize\"]/li[5]"))).click().perform();
        Thread.sleep(1000);
        selectItOnebyOne.moveToElement(driver.findElement(By.xpath("//*[@id=\"selectable-serialize\"]/li[6]"))).click().perform();
        Thread.sleep(3000);
    }




    @After
    public void tearDown(){
        driver.quit();
    }
}
