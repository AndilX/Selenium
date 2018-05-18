package selenium;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.*;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class ShopTest {

    private static WebDriver driver;
    private static final String FILE_NAME = System.getProperty("user.dir") + "\\shopDataTest.xlsx";


    private String email ="";
    private String fname ="";
    private String lname ="";
    private String password ="";
    private String address = "";
    private String city = "";
    private int zip;
    private int phone;
    private String alias = "";




    @Before
    public void setup(){
        //System.setProperty("Webdriver.chrome.driver", "C:/Users/QAC/ownloads/chromedriver_win32");
        driver = new ChromeDriver();
    }

    @Test @Ignore
    public void shopTest1() throws InterruptedException{
        driver.manage().window().fullscreen();
        Thread.sleep(1000);
        driver.get("http://automationpractice.com/index.php");
        assertEquals("Printed Summer Dress", driver.findElement(By.partialLinkText("Printed Summer Dress")).getText());
        assertEquals("Printed Chiffon Dress", driver.findElement(By.partialLinkText("Printed Chiffon Dress")).getText());
        Thread.sleep(1000);
    }


    @Test
    public void shopTestIO() throws InterruptedException{
        driver.manage().window();
        Thread.sleep(1000);
        driver.get("http://automationpractice.com/index.php");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
        Thread.sleep(1000);

        //get first item in spreadsheet

        try {
            FileInputStream dataFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(dataFile);
            Sheet datatypeSheet = workbook.getSheet("Test Data");
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        if ("email".equals(currentCell.getStringCellValue())) {
                            email = cellIterator.next().getStringCellValue();
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("email_create")).sendKeys(email);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]/span")).click();
        Thread.sleep(5000);
        //
        driver.findElement(By.id("id_gender1")).click();
        Thread.sleep(5000);
        //reading all data

        try {
            FileInputStream dataFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(dataFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        if ("First name".equals(currentCell.getStringCellValue())) {
                            fname = cellIterator.next().getStringCellValue();
                            System.out.println(fname);
                        }
                        if ("Last name".equals(currentCell.getStringCellValue())) {
                            lname = cellIterator.next().getStringCellValue();
                        }
                        if ("Password".equals(currentCell.getStringCellValue())) {
                            password = cellIterator.next().getStringCellValue();
                        }
                        if ("Address".equals(currentCell.getStringCellValue())) {
                            address = cellIterator.next().getStringCellValue();
                        }
                        if ("City".equals(currentCell.getStringCellValue())) {
                            city = cellIterator.next().getStringCellValue();
                        }
                        if ("Alias".equals(currentCell.getStringCellValue())) {
                            alias = cellIterator.next().getStringCellValue();
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        Thread.sleep(2000);
        driver.findElement(By.id("customer_firstname")).sendKeys(fname);
        Thread.sleep(500);
        driver.findElement(By.id("customer_lastname")).sendKeys(lname);
        Thread.sleep(500);
        driver.findElement(By.id("passwd")).sendKeys(password);
        Thread.sleep(500);
        driver.findElement(By.id("address1")).sendKeys(address);
        Thread.sleep(500);
        driver.findElement(By.id("city")).sendKeys(city);
        Thread.sleep(500);
        driver.findElement(By.id("id_state")).click();
        new Select(driver.findElement(By.id("id_state"))).selectByVisibleText("Idaho");
        Thread.sleep(500);
        driver.findElement(By.id("postcode")).sendKeys("00055");//(String.valueOf(zip));//zip
        Thread.sleep(500);
        driver.findElement(By.id("phone_mobile")).sendKeys("555888888999");//(String.valueOf(phone)); //phone
        Thread.sleep(500);
        driver.findElement(By.id("alias")).clear();
        Thread.sleep(500);
        driver.findElement(By.id("alias")).sendKeys(alias);
        Thread.sleep(500);
        //submit data after read
        driver.findElement(By.xpath("//*[@id=\"submitAccount\"]/span")).click();
        Thread.sleep(2000);
        //to confirm and then write 'Success' on file
        assertEquals(fname + " " + lname,driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")).getText());

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.getSheet("Test Data");
        Row row = sheet.getRow(15);
        Cell cell = row.getCell(2);
        cell.setCellType(CellType.STRING);
        cell.setCellValue("Pass");

        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    @After
    public void tearDown(){
        driver.quit();
    }


}
