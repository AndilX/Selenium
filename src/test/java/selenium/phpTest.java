package selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class phpTest {

    private static WebDriver driver;

    @Before
    public void setup(){
        //System.setProperty("Webdriver.chrome.driver", "C:/Users/QAC/ownloads/chromedriver_win32");
        driver = new ChromeDriver();
    }

    @Test
    public void testPHP() throws InterruptedException{
        driver.manage().window();
        Thread.sleep(1000);
        driver.get("https://www.phptravels.net/en");
        Thread.sleep(3000);
        WebElement searchHotel = driver.findElement(By.tagName("input"));
        Thread.sleep(3000);
        searchHotel.sendKeys("London");
        Thread.sleep(3000);
          Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).perform();
        Thread.sleep(3000);

        driver.findElement(By.id("adults")).click();
        Thread.sleep(1000);
        new Select(driver.findElement(By.id("adults"))).selectByVisibleText("3");
        Thread.sleep(1000);
        driver.findElement(By.id("adults")).click();
        Thread.sleep(1000);
        //check in-out
        searchHotel.findElement(By.xpath("//*[@id=\"dpd1\"]/input")).click();
        Thread.sleep(2000);
        searchHotel.findElement(By.xpath("//tr[3]/td[6]")).click();
        Thread.sleep(2000);

        List<WebElement> checkout = driver.findElements(By.className("day"));

        for (int i = 1; i< checkout.size(); i++){

            //System.out.println("20".equals(checkout.get(i).getText()));
            if ("20".equals(checkout.get(i).getText())){
                checkout.get(i).click();
                break;
            }
        }
        Thread.sleep(2000);

        WebElement submitQuery = driver.findElement(By.xpath("//*[@id=\"HOTELS\"]/form/div[3]/div[3]/button"));
        submitQuery.click();
        Thread.sleep(2000);

        assertEquals("Grand Plaza Apartments", driver.findElement(By.partialLinkText("Grand Plaza Apartments")).getText());

    }

    @Test @Ignore
    public void testPHP2() throws InterruptedException{
        driver.manage().window().fullscreen();
        Thread.sleep(1000);
        driver.get("https://www.phptravels.net/");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"body-section\"]/div[1]/div/div/div[1]/div/ul/li[2]/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"hotels-destination-fdbae5783aeaa4277f46df6021768e64\"]")).sendKeys("London");
        Thread.sleep(2000);
        driver.findElement(By.className("mewtwo-autocomplete-list-item_text")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"hotels-form-fdbae5783aeaa4277f46df6021768e64\"]/div[4]/div")).click();
        Thread.sleep(2000);
        driver.findElement(By.className("mewtwo-popup-ages-counter__plus")).click();
        Thread.sleep(2000);
        assertEquals("3 guests",driver.findElement(By.xpath("//*[@id=\"hotels-form-fdbae5783aeaa4277f46df6021768e64\"]/div[4]/div")).getText());
        Thread.sleep(2000);
        //
        driver.findElement(By.xpath("//*[@id=\"hotels-form-fdbae5783aeaa4277f46df6021768e64\"]/div[4]/div")).click();

        driver.findElement(By.xpath("//*[@id=\"hotels-form-fdbae5783aeaa4277f46df6021768e64\"]/div[5]/button")).click();

//        WebElement submitQuery = driver.findElement(By.className("btn-danger btn btn-lg btn-block pfb0 loader"));
//        submitQuery.click();
//        Thread.sleep(2000);

        //assertEquals("Grand Plaza Apartments", driver.findElement(By.partialLinkText("Grand Plaza Apartments")).getText());

    }

    @Test @Ignore
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
