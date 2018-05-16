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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;


public class phpTest {

    private static WebDriver driver;

    @Before
    public void setup(){
        //System.setProperty("Webdriver.chrome.driver", "C:/Users/QAC/ownloads/chromedriver_win32");
        driver = new ChromeDriver();
    }

    @Test 
    public void exampleSelenium() throws InterruptedException{
        driver.manage().window();
        Thread.sleep(1000);
        driver.get("https://www.phptravels.net/en");
        Thread.sleep(3000);
        WebElement searchHotel = driver.findElement(By.tagName("input"));
        Thread.sleep(1000);
        searchHotel.sendKeys("London");
        Thread.sleep(2000);
        searchHotel.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul")).sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
        Thread.sleep(2000);
        searchHotel.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul")).click();
        Thread.sleep(2000);
        driver.findElement(By.className("form input-lg dpd1")).click();

        Thread.sleep(2000);
        //driver.findElement(By.id("adults")).findElement(By.name("3")).click();
//      Thread.sleep(2000);
//      driver.findElement(By.className("form input-lg dpd2")).click();



//        WebElement submitQuery = driver.findElement(By.className("btn-danger btn btn-lg btn-block pfb0 loader"));
//        submitQuery.click();
//        Thread.sleep(2000);

        //assertEquals("Grand Plaza Apartments", driver.findElement(By.partialLinkText("Grand Plaza Apartments")).getText());

    }

    @Test @Ignore
    public void exampleSelenium2() throws InterruptedException{
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
